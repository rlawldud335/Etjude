<!-- eslint-disable vuejs-accessibility/media-has-caption -->
<template>
    <div class="video-area">
        <div class="video-player">
            <video
                :class="[{ 'video-zero-size': state.videoMode == 0 }, { 'video-default-size': state.videoMode == 1 }, { 'video-full-size': state.videoMode == 2 },]"
                src="https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.webm" controls></video>
            <video
                :class="[{ 'video-zero-size': state.videoMode == 2 }, { 'video-default-size': state.videoMode == 1 }, { 'video-full-size': state.videoMode == 0 },]"
                :srcObject="stream" id="my-video" autoplay></video>
        </div>
        <div class="video-controller">
            <button class="bana-btn">씬 녹화</button>
            <button class="bana-btn">마이크on/off</button>
            <button class="bana-btn">카메라on/off</button>
            <button class="bana-btn" @click="state.videoMode = (state.videoMode + 1) % 3">화면전환</button>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount, reactive } from 'vue'

const stream = ref(null)
const constraints = {
    audio: false,
    video: {
        width: { min: 500, ideal: 1280, max: 1920 },
        height: { min: 300, ideal: 720, max: 1080 },
        facingMode: 'environment',
    },
}

const stop = () => {
    stream.value.getTracks().forEach(track => {
        console.log('stopping', track)
        track.stop()
    })
    stream.value = null
}

const play = async () => {
    const frontCamStream = await navigator.mediaDevices.getUserMedia(constraints)
    console.log('streaming', frontCamStream)
    stream.value = frontCamStream
}

onMounted(() => play())
onBeforeUnmount(() => stop())

const state = reactive({
    videoMode: 0
})
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