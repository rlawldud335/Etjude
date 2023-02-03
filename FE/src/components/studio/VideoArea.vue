<!-- eslint-disable vuejs-accessibility/media-has-caption -->
<template>
    <div class="video-area">
        <div class="video-player">
            <video src="/media/cc0-videos/flower.webm" controls></video>
        </div>
        <div class="video-controller">
            <button class="bana-btn">씬 녹화</button>
            <button class="bana-btn">마이크on/off</button>
            <button class="bana-btn">카메라on/off</button>
            <button class="bana-btn">화면전환</button>
        </div>
    </div>
</template>

<script>
export default {

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
}

.video-player>video {
    width: 100%;
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
</style>