<!-- eslint-disable vuejs-accessibility/click-events-have-key-events -->
<!-- eslint-disable vuejs-accessibility/alt-text -->
<template class="event-banner">
    <Carousel ref="event-carousel" v-model="currentSlide" :items-to-show="1.6" :wrap-around="true" :autoplay="20000"
        class="event-carousel">
        <Slide v-for="slide in eventList" :key="slide" class="event-carousel__slide">
            <div class="event-carousel__slide__card">
                <img :src="require(`@/assets/images/${slide.image}`)" class="event-carousel__slide__card__img" />
            </div>
        </Slide>
    </Carousel>
    <div class="event-navigation">
        <div class="event-navigation--center">

            <div class="event-navigation__controller">
                <div class="controller__page">{{ currentSlide+ 1}} / {{ eventList.length }}</div>
                <div class="controller__btns">
                    <Prev class="controller__btn" @click="sildeTo(currentSlide - 1)" />
                    <Stop class="controller__btn" />
                    <Next class="controller__btn" @click="sildeTo(currentSlide + 1)" />
                </div>
            </div>

            <Line style="margin:0px 30px" />

            <div class="event-navigation__tags" ref="eventTags">
                <div v-for="(event, index) in eventList" :key="index" class="event-navigation__tags__tag"
                    @click="sildeTo(index)">{{ event.eventName }}</div>
            </div>

        </div>
    </div>
</template>
  
<script>
import 'vue3-carousel/dist/carousel.css'
import { Carousel, Slide } from 'vue3-carousel'
import Prev from '../../assets/icons/prev.svg';
import Stop from '../../assets/icons/stop.svg';
import Next from '../../assets/icons/next.svg';
import Line from '../../assets/icons/line.svg';

export default {
    name: 'EventBanner',
    components: {
        Carousel,
        Slide,
        Prev,
        Stop,
        Next,
        Line
    },
    data() {
        return {
            eventList: [
                { url: '', image: "beforechicken.svg", eventName: "이벤트1" },
                { url: '', image: "Main Banner02.png", eventName: "이벤트2" },
                { url: '', image: "Main Banner03.png", eventName: "이벤트3" },
            ],
            currentSlide: 0
        };
    },
    methods: {
        sildeTo(val) {
            if (val < 0) this.currentSlide = this.eventList.length - 1;
            else if (val > this.eventList.length - 1) this.currentSlide = 0;
            else this.currentSlide = val;
        }
    },
    watch: {
        // eslint-disable-next-line func-names, object-shorthand
        currentSlide: function (val) {
            const eventTags = this.$refs.eventTags.getElementsByClassName("event-navigation__tags__tag");
            // eslint-disable-next-line no-plusplus
            for (let i = 0; i < eventTags.length; i++) {
                if (val === i) eventTags[i].classList.add('tag__highlight');
                else eventTags[i].classList.remove('tag__highlight');
            }
        }
    },
    mounted() {
        this.$refs.eventTags.getElementsByClassName("event-navigation__tags__tag")[0].classList.add('tag__highlight');
    }
}
</script>

<style lang="scss">
.event-carousel {
    width: 100%;
}

.event-carousel__slide {
    width: 1000px;
    padding-left: 7px;
    padding-right: 7px;
}

.event-carousel__slide__card {
    height: 400px;
    width: 1240px;
    border-radius: 10px;
}

.event-carousel__slide__card__img {
    width: 100%;
    height: 100%;
    border-radius: 10px;
}

.event-navigation {
    display: flex;
    justify-content: center;
    background-color: $aha-gray;
    height: 64px;
    width: 100%;
}

.event-navigation--center {
    display: flex;
    flex-direction: row;
    align-items: center;
    width: 1136px;
}

.event-navigation__controller {
    background-color: #FBD1D9;
    border-radius: 15px;
    height: 30px;

    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    padding-left: 10px;
    padding-right: 5px;
    margin-left: 15px;
}

.controller__btns {
    margin-left: 10px;
}

.controller__btn {
    padding: 5px;
    border-radius: 50%;
    margin: 0px 2px;
}

.controller__btn:hover {
    background-color: #E3BDC5;
}

.controller__page {
    margin: 0px 10px;
}

.event-navigation__tags {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
}

.event-navigation__tags__tag {
    display: flex;
    flex-direction: center;
    align-items: center;
    height: 30px;
    background-color: $white;
    border-radius: 20px;
    padding: 0px 20px;
    border: #8B8B9D 1px solid;
    margin: 0px 7px;
}

.tag__highlight {
    border: $bana-pink 3px solid;
    font-weight: bold;
    color: $bana-pink;
}

.event-navigation__tags__tag:hover {
    background-color: $aha-gray;
}
</style>