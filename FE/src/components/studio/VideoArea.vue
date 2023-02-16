<!-- eslint-disable import/no-extraneous-dependencies -->
<!-- eslint-disable vuejs-accessibility/media-has-caption -->
<template>
  <div class="video-area">
    <div class="video-player">
      <video :class="[
        { 'video-zero-size': state.videoMode == 0 },
        { 'video-default-size': state.videoMode == 1 },
        { 'video-full-size': state.videoMode == 2 },
      ]" ref="videoOutput" :src="studioInfo.storyVideoUrl" @timeupdate="changeTimeHandler" controls
        loop="loop"></video>
      <video :class="[
        { 'video-zero-size': state.videoMode == 2 },
        { 'video-default-size': state.videoMode == 1 },
        { 'video-full-size': state.videoMode == 0 },
      ]" :srcObject="mediaStream" autoplay muted></video>
      <div class="on-air" v-if="videoState.isRecording">
        <RecordCircle />
        <span>On Air - #{{ videoState.sceneIdx }} 녹화 중</span>
        <button class="bana-btn" @click="endRecording()">녹화 종료</button>
      </div>
    </div>
    <div class="video-controller">
      <button class="bana-btn" @click="toggleVideo()">
        <span>카메라</span>
        <VideoOn v-show="toggleBtn.video" />
        <VideoOff v-show="!toggleBtn.video" />
      </button>
      <button class="bana-btn" @click="toggleAudio()">
        <span>마이크</span>
        <MicOn v-show="toggleBtn.audio" />
        <MicOff v-show="!toggleBtn.audio" />
      </button>
      <button class="bana-btn" @click="state.videoMode = (state.videoMode + 1) % 3">
        <span>화면전환</span>
        <ChangeVideo2 />
      </button>
    </div>
</div>
</template>

<script>


import { reactive, ref, onMounted, onBeforeUnmount, watch, computed } from "vue";
import VideoOn from "@/assets/icons/VideoOn.svg";
import VideoOff from "@/assets/icons/VideoOff.svg";
import MicOn from "@/assets/icons/MicOn.svg";
import MicOff from "@/assets/icons/MicOff.svg";
import RecordCircle from "@/assets/icons/RecordCircle.svg";
import ChangeVideo2 from "@/assets/icons/ChangeVideo2.svg";
import { fileUpload } from "@/api/aws";
import { saveSceneRecord } from "@/api/studio";
import { webmFixDuration } from "webm-fix-duration";
import { useStore } from "vuex";

export default {
  components: {
    VideoOn,
    VideoOff,
    MicOn,
    MicOff,
    RecordCircle,
    ChangeVideo2,
  },
  props: {
    videoState: Object,
    scriptState: Object,
    allLines: Array,
    studioInfo: Object,
  },
  emits: ["change-video-state", "save-recording-data", "change-current-slide", "change-record-sync-state"],
  setup(props, { emit }) {
    const state = reactive({
      videoMode: 1,
    });

    const store = useStore();
    const user = computed(() => store.state.user);

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
    const videoResolution = {
      width: { min: 640, ideal: 640, max: 640 },
      height: { min: 480, ideal: 480, max: 480 },
    };

    const mediaStream = ref(null);
    const constraints = reactive({
      video: videoResolution,
      audio: true,
    });
    const toggleBtn = reactive({
      video: true,
      audio: true,
    });

    // const filterTime = (sec) => {
    //   let h = (sec / (1000 * 60 * 60)) % 24;
    //   let m = (sec / (1000 * 60)) % 60;
    //   let s = (sec / 1000) % 60;
    //   h = (`0${Math.floor(h)}`).slice(-2);
    //   m = (`0${Math.floor(m)}`).slice(-2);
    //   s = (`0${Math.floor(s)}`).slice(-2);
    //   return `${h}:${m}:${s}`;
    // }

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
      toggleBtn.video = !toggleBtn.video;
      mediaStream.value.getVideoTracks()[0].enabled =
        !mediaStream.value.getVideoTracks()[0].enabled;
    };

    const toggleAudio = () => {
      toggleBtn.audio = !toggleBtn.audio;
      mediaStream.value.getAudioTracks()[0].enabled =
        !mediaStream.value.getAudioTracks()[0].enabled;
    };

    const startRecoding = async () => {
      emit('change-record-sync-state', user.value.userId, props.videoState.sceneIdx, true);
      const dateStarted = new Date().getTime();
      const recordedChunks = [];
      mediaRecorder = new MediaRecorder(mediaStream.value, {
        mimeType: "video/webm;",
      });
      mediaRecorder.ondataavailable = (event) => {
        if (event.data && event.data.size > 0) {
          recordedChunks.push(event.data);
        }
      };
      mediaRecorder.onstop = async () => {
        if (recordedMediaURL.value) {
          URL.revokeObjectURL(recordedMediaURL.value);
        }
        if (recordedChunks && recordedChunks.length !== 0) {
          const dateEnd = new Date().getTime();
          const blob = await new Blob(recordedChunks, { type: "video/webm;" });
          const fixedBlob = await webmFixDuration(blob, dateEnd - dateStarted);
          recordedMediaURL.value = URL.createObjectURL(fixedBlob);
          const awsUrl = fileUpload(
            fixedBlob,
            props.studioInfo,
            props.videoState.sceneIdx,
            (data) => {
              console.log("aws 업로드 확인 ", awsUrl);
              const params = {
                recording_video_url: data.Location,
                scene_id: props.videoState.sceneIdx,
                studio_id: props.studioInfo.studio_id,
                user_id: user.value.userId,
              };
              saveSceneRecord(
                params,
                (dt) => {
                  console.log("녹화 영상 업로드 성공", dt);
                  console.log(user.value.userId, props.videoState.sceneIdx, false);
                  emit('change-record-sync-state', user.value.userId, props.videoState.sceneIdx, false);
                },
                (er) => {
                  console.log("녹화 영상 업로드 실패", er);
                }
              );
              recordedMediaURL.value = data.Location;
            },
            (err) => {
              console.log("aws 업로드 실패", err);
            }
          );
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
      toggleBtn,
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
