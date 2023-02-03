<!-- eslint-disable vuejs-accessibility/media-has-caption -->
<template>
    <div class="video-area">
        <div class="video-player">
            <video src="/media/cc0-videos/flower.webm" controls></video>
        </div>
    </div>
</template>

<script>
export default {
    
}
</script>

<style lang="scss">
    .video-area{
        width: 100%;
        height: 100%;
    }
    .video-player{
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .video-player > video {
        width: 100%;
        height: 100%;
    }
</style>