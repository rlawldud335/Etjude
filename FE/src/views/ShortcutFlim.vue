<template>
    <div class="shortcut-flim">
        <div class="shortcut-flim--blue">
            <div class="shortcut-flim--center">
                <div class="shortcut-flim__contents">
                    <span class="shortcur-flim__contents__text">자신의 필름을 공유하세요.<br/>다른 사람의 연기를 볼 수 있습니다.</span>
                    <div class="shortcut-btn">필름공유 바로가기</div>
                </div>
                <img class="popcorn-img" src="@/assets/images/popcorn.png" alt="">
            </div>
        </div>
    </div>
</template>
<script>
export default {
    
}
</script>
<style lang="scss">
.shortcut-flim{
    width: 100%;
    height: 300px;
}

.shortcut-flim--blue{
    height: 161px;
    width: 100%;
    background-color: rgba($newjeans-blue,0.16);
    display: flex;
    justify-content: center;
    align-items: center;
}
.shortcut-flim--center{
    width: 1136px;
    height: 161px;
    display: flex;
    flex-direction: row;
    position: relative;
}

.shortcut-flim__contents{
    width: 70%;
    display: flex;
    flex-direction: column;
    align-items: left;
    padding: 20px;
}

.shortcut-btn{
    background-color: #3D5ADB;
    color: $white;
    padding: 12px 20px;
    border-radius: 4px;
    width: 170px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.shortcut-btn:hover{
    opacity: 0.8;
}

.shortcur-flim__contents__text {
    line-height: 1.2;
    font-size: 18px;
    margin:10px
}

.popcorn-img{
    width: 444px;
    height: 345px;
    position: absolute;
    right:0;
    bottom: -70px;
}
</style>