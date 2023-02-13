<!-- eslint-disable vuejs-accessibility/media-has-caption -->
<template>
  <div class="video-area">
    <div class="video-player">
      <video :class="[
        { 'video-zero-size': state.videoMode == 0 },
        { 'video-default-size': state.videoMode == 1 },
        { 'video-full-size': state.videoMode == 2 },
      ]" ref="videoOutput" :src="studioInfo.storyVideoUrl" @timeupdate="changeTimeHandler" controls></video>
      <video :class="[
        { 'video-zero-size': state.videoMode == 2 },
        { 'video-default-size': state.videoMode == 1 },
        { 'video-full-size': state.videoMode == 0 },
      ]" :srcObject="mediaStream" autoplay></video>
      <div class="on-air" v-if="videoState.isRecording">
        <RecordCircle />
        <span>On Air - #{{ videoState.sceneIdx }} 녹화 중</span>
        <button class="bana-btn" @click="endRecording()">녹화 종료</button>
      </div>
    </div>
    <div class="video-controller">
      <button class="bana-btn" @click="toggleVideo()">
        <span>카메라</span>
        <VideoOn v-show="constraints.video" />
        <VideoOff v-show="!constraints.video" />
      </button>
      <button class="bana-btn" @click="toggleAudio()">
        <span>마이크</span>
        <MicOn v-show="constraints.audio" />
        <MicOff v-show="!constraints.audio" />
      </button>
      <button class="bana-btn" @click="state.videoMode = (state.videoMode + 1) % 3">
        <span>화면전환</span>
        <ChangeVideo2 />
      </button>
    </div>
  </div>
</template>

<script>
import { reactive, ref, onMounted, onBeforeUnmount, watch } from "vue";
import VideoOn from "@/assets/icons/VideoOn.svg";
import VideoOff from "@/assets/icons/VideoOff.svg";
import MicOn from "@/assets/icons/MicOn.svg";
import MicOff from "@/assets/icons/MicOff.svg";
import RecordCircle from "@/assets/icons/RecordCircle.svg";
import ChangeVideo2 from "@/assets/icons/ChangeVideo2.svg";
import { fileUpload } from "@/api/aws";

export default {
  components: {
    VideoOn,
    VideoOff,
    MicOn,
    MicOff,
    RecordCircle,
    ChangeVideo2,
  },
  props: { videoState: Object, scriptState: Object, allLines: Array, studioInfo: Object },
  emits: ["change-video-state", "save-recording-data", "change-current-slide"],
  setup(props, { emit }) {
    const user = {
      user_id: "1",
      nickname: "user1",
      profile_url:
        "https://www.highziumstudio.com/wp-content/uploads/2023/02/%ED%95%98%EC%9D%B4%EC%A7%80%EC%9D%8C%EC%8A%A4%ED%8A%9C%EB%94%94%EC%98%A4-%EB%B0%B0%EC%9A%B0-%EA%B6%8C%EC%8A%B9%EC%9A%B0-%ED%95%98%EC%9D%B4%EC%A7%80%EC%9D%8C%EC%8A%A4%ED%8A%9C%EB%94%94%EC%98%A4%EC%99%80-%EB%A7%A4%EB%8B%88%EC%A7%80%EB%A8%BC%ED%8A%B8-%EA%B3%84%EC%95%BD-%EC%B2%B4%EA%B2%B0_230202-2-853x1280.jpg",
    };



    const state = reactive({
      videoMode: 0,
    });

    const videoOutput = ref(null);

    watch(
      () => props.scriptState.currentTime,
      (cur) => {
        videoOutput.value.currentTime = cur;
      }
    );

    const changeTimeHandler = () => {
      // eslint-disable-next-line prefer-const
      let curTime = videoOutput.value.currentTime;
      if (curTime < props.allLines[0].lineTimeStamp) {
        if (props.scriptState.currentSlide !== 0) {
          emit("change-current-slide", 0);
        }
        return;
      }
      for (let i = 1; i < props.allLines.length; i += 1) {
        if (
          props.allLines[i - 1].lineTimeStamp <= curTime &&
          curTime < props.allLines[i].lineTimeStamp
        ) {
          if (props.scriptState.currentSlide !== i - 1) {
            emit("change-current-slide", i - 1);
          }
          return;
        }
      }
      if (props.scriptState.currentSlide !== props.allLines.length - 1) {
        emit("change-current-slide", props.allLines.length - 1);
      }
    };

    const mediaStream = ref(null);
    const constraints = reactive({ video: true, audio: true });
    let mediaRecorder = null;
    const recordedMediaURL = ref(null);

    const getStream = async () => {
      const newMediaStream = await navigator.mediaDevices.getUserMedia(constraints);
      mediaStream.value = newMediaStream;
    };

    const stopStream = () => {
      mediaStream.value.getTracks().forEach((track) => {
        track.stop();
      });
      mediaStream.value = null;
    };

    const toggleVideo = () => {
      constraints.video = !constraints.video;
      mediaStream.value.getVideoTracks()[0].enabled = constraints.video;
    };

    const toggleAudio = () => {
      constraints.audio = !constraints.audio;
      mediaStream.value.getAudioTracks()[0].enabled = constraints.audio;
    };

    const startRecoding = () => {
      const recordedChunks = [];
      mediaRecorder = new MediaRecorder(mediaStream.value, {
        mimeType: "video/webm;",
      });
      mediaRecorder.ondataavailable = (event) => {
        if (event.data && event.data.size > 0) {
          recordedChunks.push(event.data);
        }
      };
      mediaRecorder.onstop = () => {
        if (recordedMediaURL.value) {
          URL.revokeObjectURL(recordedMediaURL.value);
        }
        if (recordedChunks && recordedChunks.length !== 0) {
          const blob = new Blob(recordedChunks, { type: "video/webm;" });
          recordedMediaURL.value = URL.createObjectURL(blob);
          console.log("녹화종료", recordedMediaURL.value);
          const awsUrl = fileUpload(blob, props.studioInfo, props.videoState.sceneIdx, user);
          console.log(awsUrl);
          recordedMediaURL.value = awsUrl;
          emit("save-recording-data", props.videoState.sceneIdx, recordedMediaURL.value, user);
        }
      };
      mediaRecorder.start();
    };

    const endRecording = () => {
      if (mediaRecorder) {
        mediaRecorder.stop();
      }
      emit("change-video-state", props.videoState.sceneIdx, false);
    };

    onMounted(() => getStream());
    onBeforeUnmount(() => stopStream());

    watch(
      () => props.videoState.isRecording,
      (cur) => {
        if (cur) {
          startRecoding();
        }
      }
    );

    return {
      state,
      mediaStream,
      recordedMediaURL,
      constraints,
      toggleVideo,
      toggleAudio,
      startRecoding,
      endRecording,
      videoOutput,
      changeTimeHandler,
    };
  },
};
</script>

<style lang="scss">
.video-area {
  width: 100%;
  height: 100%;
}

.video-player {
  width: 100%;
  height: 92%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: black;
  position: relative;
}

.video-player>video {
  height: 100%;
}

.video-controller {
  height: 8%;
  width: 100%;
  background-color: #474747;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}

.bana-btn {
  background-color: $bana-pink;
  color: white;
  padding: 7px 15px;
  border: none;
  border-radius: 10px;
  margin: 3px;
  font-size: 12px;
  font-weight: 300;
  display: flex;
  align-items: center;
}

.bana-btn span {
  margin-right: 6px;
}

.bana-btn:hover {
  opacity: 0.8;
}

.video-default-size {
  width: 50%;
}

.video-full-size {
  width: 100%;
}

.video-zero-size {
  width: 0%;
}

.on-air {
  background-color: #fff9fa;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 5px 10px;
  border-radius: 7px;
  position: absolute;
  top: 10px;
}

.on-air span {
  padding: 0px 10px;
  font-size: 14px;
}
</style>