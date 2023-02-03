<template lang="">
    <div class="script-area">
       <Carousel ref="script-carousel" v-model="currentSlide" :wrap-around="true"  :items-to-show="1.3" class="script-carousel">
            <Slide v-for="(script,idx) in scripts" :key="idx" class="script-carousel__slide">
                <div class="script-carousel__slide__card">
                    <div class="script-icons">
                        <div class="script-icon">1x</div>
                        <div class="script-icon"><Repeat/></div>
                    </div>                
                    <div class="script-script">{{script.script+idx}}</div>
                </div>
            </Slide>
       </Carousel>
       <PrevDirec class=" direction-prev" @click="slideTo(currentSlide - 1)"/>
       <NextDirec class=" direction-next" @click="slideTo(currentSlide + 1)"/>
    </div>
</template>
<script>
import { Carousel, Slide } from 'vue3-carousel';
import Repeat from "@/assets/icons/repeat.svg";
import PrevDirec from "@/assets/icons/prevDirec.svg";
import NextDirec from "@/assets/icons/nextDirec.svg";


export default {
    components: {
        Carousel, Slide, Repeat, PrevDirec, NextDirec
    },
    data() {
        return {
            scripts: [
                {
                    time: "00:12",
                    script: "안녕1",
                    role: "김지영"
                },
                {
                    time: "00:12",
                    script: "안녕1",
                    role: "김지영"
                },
                {
                    time: "00:12",
                    script: "안녕1",
                    role: "김지영"
                },
                {
                    time: "00:12",
                    script: "안녕1",
                    role: "김지영"
                }
            ],
            currentSlide: 0
        }
    },
    methods: {
        slideTo(val) {
            if (val < 0) this.currentSlide = 0;
            else if (val >= this.scripts.length) this.currentSlide = this.scripts.length - 1;
            else this.currentSlide = val;
        }
    },
    props: ['isOpenTab']
}
</script>
<style lang="scss">
.script-area {
    width: 100%;
    height: 100%;
    background-color: #D9D9D9;
    position: relative;
}

.script-carousel {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.script-carousel__slide {
    width: 100%;
    height: 100%;
}

.script-carousel__slide__card {
    width: 95%;
    height: 110px;
    background-color: $aha-gray;
    border-radius: 10px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
    padding: 12px 20px;
}

.script-icons {
    display: flex;
    flex-direction: row;
}

.script-icon {
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 10px;
    width: 45px;
    height: 24px;
    background: #FFFFFF;
    border: 0.6px solid #BEBEBE;
    border-radius: 20px;
    font-size: 12px;
    font-weight: 300;
    margin-right: 7px;
    cursor: pointer;
}

.script-icon:hover {
    background-color: #F6F6F6;
}

.script-script {
    font-size: 18px;
    height: 45px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.direction-prev {
    position: absolute;
    top: 50px;
    left: 20px;
}

.direction-next {
    position: absolute;
    top: 50px;
    right: 20px;
}
</style>