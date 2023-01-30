<!-- eslint-disable vuejs-accessibility/alt-text -->
<template>
    <Carousel ref="myCarousel" v-model="currentSlide" :items-to-show="1.6" :wrap-around="true" :autoplay="20000" class="event-banner" >
        <Slide v-for="slide in slideList" :key="slide" class="event-banner__slide">
            <div class="event-card">
                <img :src="require(`@/assets/images/${slide.image}`)" class="event-card__image" />
            </div>
        </Slide>
    </Carousel>
    <div class="event-banner__navigation">
        <div class="event-banner__navigation--center">
            <div>
                <span>{{currentSlide+1}} / {{ slideList.length }}</span>
                <div><Prev/><Stop/><Next/></div>
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

  export default {
    name: 'EventBanner',
    components: {
      Carousel,
      Slide,
      Prev,
      Stop,
      Next,
    },
    data() {
    return {
      slideList :[
        {url:'',image:"Main Banner01.png"},
        {url:'',image:"Main Banner02.png"},
        {url:'',image:"Main Banner03.png"},
      ],
      currentSlide: 0
    };
  },
  methods:{
    sildeTo(val){
        this.currentSlide = val
    },
  }
  }
  </script>

<style lang="scss">
.event-banner{
    width: 100%;
}

.event-banner__slide {
    background-color: antiquewhite;
    width: 1000px;
    padding-left: 7px;
    padding-right: 7px;
}

.event-card{
    height: 400px;
    width: 1240px;
    border-radius: 10px;
    background-color: #ACD3E6;
}

.event-card__image{
    width: 100%;
    height: 100%;
    border-radius: 10px;
}

.event-banner__navigation {
    display: flex;
    justify-content: center;
    background-color: #FAFAFA;
    height: 64px;
    width: 100%;
}
.event-banner__navigation--center{
    display: flex;
    flex-direction: row;
    width: 1136px;
}


</style>