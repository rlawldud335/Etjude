<template lang="">
    <div class="script-area">
       <Carousel ref="scriptCarousel" v-model="currentSlide" :wrap-around="true"  :items-to-show="1.3" class="script-carousel">
            <Slide v-for="script in allScripts" :key="script" class="script-carousel__slide">
                <div class="script-carousel__slide__card">
                    <div class="script-icons">
                        <div class="script-icon">1x</div>
                        <div class="script-icon"><Repeat/></div>
                    </div>
                    <div class="script-script">
                        {{script.roleName}}  :  
                        {{script.line}}</div>
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
import { ref, watch } from "vue";

export default {
    components: {
        Carousel, Slide, Repeat, PrevDirec, NextDirec
    },
    props: {
        'studioData': Object
    },
    emits: ['change-script-time'],
    setup(props, { emit }) {
        const currentSlide = ref(0);
        const allScripts = [];
        let index = 0;
        props.studioData.script.forEach((scene) => {
            scene.lines.forEach((line) => {
                index += 1;
                allScripts.push({
                    roleName: scene.roleName,
                    scriptNumber: index,
                    lineTimeStamp: line.lineTimeStamp,
                    line: line.line
                });
            });
        });
        console.log(allScripts);

        const slideTo = (val) => {
            if (val < 0) currentSlide.value = 0;
            else if (val >= allScripts.length) currentSlide.value = allScripts.length - 1;
            else currentSlide.value = val;
        }

        const filterTime = (time) => {
            const sptTime = time.split(':');
            const result = parseInt(sptTime[0], 10) * 60 + parseInt(sptTime[1], 10) + (parseInt(sptTime[2], 10) / 10);
            console.log(result);
            return result;
        }

        watch(currentSlide, (cur) => {
            console.log(cur);
            emit('change-script-time', filterTime(allScripts[cur].lineTimeStamp));
        })


        return {
            currentSlide,
            slideTo,
            allScripts,
        }
    }
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