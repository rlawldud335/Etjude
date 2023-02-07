<!-- eslint-disable vuejs-accessibility/media-has-caption -->
<template>
    <div class="video-area">
        <div class="video-player">
            <video
                :class="[{ 'video-zero-size': state.videoMode == 0 }, { 'video-default-size': state.videoMode == 1 }, { 'video-full-size': state.videoMode == 2 },]"
                id="video-output" :src="recordedMediaURL" controls></video>
            <video
                :class="[{ 'video-zero-size': state.videoMode == 2 }, { 'video-default-size': state.videoMode == 1 }, { 'video-full-size': state.videoMode == 0 },]"
                :srcObject="mediaStream" autoplay></video>
        </div>
        <div class="video-controller">
            <button class="bana-btn" @click="startRecoding()">녹화 시작</button>
            <button class="bana-btn" @click="endRecording()">녹화 종료</button>
            <button class="bana-btn" @click="downloadRecording()">다운로드</button>
            <button class="bana-btn" @click="toggleVideo()">
                <VideoOn v-show="constraints.video" />
                <VideoOff v-show="!constraints.video" />
            </button>
            <button class="bana-btn" @click="toggleAudio()">
                <MicOn v-show="constraints.audio" />
                <MicOff v-show="!constraints.audio" />
            </button>
            <button class="bana-btn" @click="state.videoMode = (state.videoMode + 1) % 3">
                화면전환
            </button>
        </div>
    </div>
</template>

<script>
import { reactive, ref, onMounted, onBeforeUnmount } from 'vue'
import VideoOn from "@/assets/icons/VideoOn.svg";
import VideoOff from "@/assets/icons/VideoOff.svg";
import MicOn from "@/assets/icons/MicOn.svg";
import MicOff from "@/assets/icons/MicOff.svg";

export default {
    components: {
        VideoOn, VideoOff, MicOn, MicOff
    },
    setup() {
        const state = reactive({
            videoMode: 0
        });
        // const videoOutput = document.getElementById("video-output");
        const mediaStream = ref(null);
        const constraints = reactive({ video: true, audio: true });
        let mediaRecorder = null;
        const recordedMediaURL = ref(null);

        const getStream = async () => {
            const newMediaStream = await navigator.mediaDevices.getUserMedia(constraints);
            mediaStream.value = newMediaStream;
        }

        const stopStream = () => {
            mediaStream.value.getTracks().forEach(track => {
                console.log('stopping', track)
                track.stop()
            })
            mediaStream.value = null
        }

        const toggleVideo = () => {
            constraints.video = !constraints.video;
            mediaStream.value.getVideoTracks()[0].enabled = constraints.video;
        }

        const toggleAudio = () => {
            constraints.audio = !constraints.audio;
            mediaStream.value.getAudioTracks()[0].enabled = constraints.audio;
        }

        const startRecoding = () => {
            const recordedChunks = [];
            mediaRecorder = new MediaRecorder(mediaStream.value, {
                mimeType: "video/webm;",
            });
            mediaRecorder.ondataavailable = (event) => {
                if (event.data && event.data.size > 0) {
                    console.log("ondataavailable");
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
                    console.log()
                }
            };
            console.log("start recording");
            mediaRecorder.start();
        }

        const downloadRecording = () => {
            if (recordedMediaURL.value) {
                const link = document.createElement("a");
                document.body.appendChild(link);
                link.href = recordedMediaURL.value;
                link.download = "video.webm";
                link.click();
                document.body.removeChild(link);
            }
        }

        const endRecording = () => {
            if (mediaRecorder) {
                console.log("endRecording")
                mediaRecorder.stop();
            }
        }

        onMounted(() => getStream())
        onBeforeUnmount(() => stopStream())

        return {
            state,
            mediaStream,
            recordedMediaURL,
            constraints,
            toggleVideo,
            toggleAudio,
            startRecoding,
            endRecording,
            downloadRecording
        };
    }
}
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
}

.bana-btn:hover {
    opacity: 0.8;
}

.video-default-size {
    width: 50%;
}

.video-pull-size {
    width: 70%;
}

.video-zero-size {
    width: 0%;
}
</style>