<template>
    <div class="studio-nav">
        <div class="studio-nav__logo">Logo</div>
            <div class="studio-nav__title">
                <span>진양철의 초밥이 몇개고?</span> 
                <span> > </span>
                <span>밥알패밀리 스튜디오</span>
        </div>
        <CircleSetting/>

        <div>
            <img class="profile-img" src="@/assets/images/profile1.jpg" alt="">
            <img class="profile-img" src="@/assets/images/profile2.jpg" alt="">
            <img class="profile-img" src="@/assets/images/profile3.jpg" alt="">
        </div>

        <div>
            <img class="profile-img" src="@/assets/images/profile.jpg" alt=""/>
            <span>ldk_giveup</span>
            <CameraIcon/>
            <MICIcon/>
        </div>
    </div>
</template>
<script>

import CircleSetting from "@/assets/icons/CircleSetting.svg";
import CameraIcon from "@/assets/icons/camera.svg";
import MICIcon from "@/assets/icons/mic.svg";

export default {
    components:{
        CircleSetting,CameraIcon,MICIcon
    }    
}
</script>
<style lang="scss">
.studio-nav{
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: row;
    align-items: center;
    background-color: $bana-pink;
}
.studio-nav__logo{
    margin:10px 30px;
    font-weight: bold;
    color: white;
}
.studio-nav__title{
    color: white;
}
.studio-nav__title > span{
    margin: 0px 5px;
}

.profile-img{
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin: 0px 5px;
}
</style>