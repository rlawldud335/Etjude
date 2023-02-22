<!-- eslint-disable vuejs-accessibility/click-events-have-key-events -->
<!-- eslint-disable vuejs-accessibility/alt-text -->
<template class="event-banner">
  <Carousel ref="event-carousel" v-model="currentSlide" :items-to-show="1.6" :wrap-around="true" :autoplay="20000"
    class="event-carousel">
    <Slide v-for="slide in eventList" :key="slide" class="event-carousel__slide">
      <div class="event-carousel__slide__card">
        <div class="slide-card" :style="{ backgroundColor: slide.backColor, color: slide.fontColor }">
          <div class="slide-card__text">
            <div class="slide-card__text__tag">{{ slide.eventTag }}</div>
            <span class="slide-card__text__title">{{ slide.title }}</span>
            <span class="slide-card__text__sub-title">{{ slide.subTitle }}</span>
          </div>
          <div class="slide-card__img">
            <img :src="require(`@/assets/images/${slide.image}`)" />
          </div>
        </div>
      </div>
    </Slide>
  </Carousel>
  <div class="event-navigation">
    <div class="event-navigation--center">
      <div class="event-navigation__controller">
        <div class="controller__page">{{ currentSlide + 1 }} / {{ eventList.length }}</div>
        <div class="controller__btns">
          <Prev class="controller__btn" @click="sildeTo(currentSlide - 1)" />
          <Stop class="controller__btn" />
          <Next class="controller__btn" @click="sildeTo(currentSlide + 1)" />
        </div>
      </div>

      <Line style="margin: 0px 30px" />

      <div class="event-navigation__tags" ref="eventTags">
        <div v-for="(event, index) in eventList" :key="index" class="event-navigation__tags__tag" @click="sildeTo(index)">
          {{ event.eventName }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "vue3-carousel/dist/carousel.css";
import { Carousel, Slide } from "vue3-carousel";
import Prev from "../../assets/icons/prev.svg";
import Stop from "../../assets/icons/stop.svg";
import Next from "../../assets/icons/next.svg";
import Line from "../../assets/icons/line.svg";

export default {
  name: "EventBanner",
  components: {
    Carousel,
    Slide,
    Prev,
    Stop,
    Next,
    Line,
  },
  data() {
    return {
      eventList: [
        {
          url: "",
          image: "bobbob.gif",
          eventName: "이벤트1",
          backColor: "#FE907C",
          fontColor: "white",
          eventTag: "고민은 이제 그만",
          title: "인기있는 작품, 스토리 특가",
          subTitle: "어디서부터 시작해야 할지 모르는 당신을 위한 이벤트",
        },
        {
          url: "",
          image: "moonwork.gif",
          eventName: "이벤트2",
          backColor: "#F8D24A",
          fontColor: "white",
          eventTag: "엄청난 기회",
          title: "필름 올리고 팬미팅 응모 하자",
          subTitle: "대배우 임두현을 만날 수 있는 기회",
        },
        {
          url: "",
          image: "face.gif",
          eventName: "이벤트3",
          backColor: "#F8EFE8",
          fontColor: "black",
          eventTag: "싸피 할인",
          title: "싸피 학생증 인증하고 할인받자",
          subTitle: "최고의 교육 기관 싸피와의 콜라보레이션",
        },
      ],
      currentSlide: 0,
    };
  },
  methods: {
    sildeTo(val) {
      if (val < 0) this.currentSlide = this.eventList.length - 1;
      else if (val > this.eventList.length - 1) this.currentSlide = 0;
      else this.currentSlide = val;
    },
  },
  watch: {
    // eslint-disable-next-line func-names, object-shorthand
    currentSlide: function (val) {
      const eventTags = this.$refs.eventTags.getElementsByClassName("event-navigation__tags__tag");
      // eslint-disable-next-line no-plusplus
      for (let i = 0; i < eventTags.length; i++) {
        if (val === i) eventTags[i].classList.add("tag__highlight");
        else eventTags[i].classList.remove("tag__highlight");
      }
    },
  },
  mounted() {
    this.$refs.eventTags
      .getElementsByClassName("event-navigation__tags__tag")[0]
      .classList.add("tag__highlight");
  },
};
</script>

<style lang="scss">
.event-carousel {
  width: 100%;
}

.event-carousel__slide {
  padding-left: 7px;
  padding-right: 7px;
}

.event-carousel__slide__card {
  width: 100%;
  max-width: 1136px;
  aspect-ratio: 2.6 / 1;
  border-radius: 10px;
}

.slide-card {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  background-color: black;
  color: white;
  border-radius: 10px;
}

.slide-card__text {
  width: 50%;
  height: 100%;
  border-radius: 10px 0px 0px 10px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  padding: 0px 20px;
}

.slide-card__text__tag {
  background-color: #00de84;
  padding: 5px 10px;
  margin: 10px;
  border-radius: 5px;
  font-size: 1rem;
  // color: black;
}

.slide-card__text__title {
  font-size: 1.5rem;
  margin: 10px;
  font-weight: 500;
}

.slide-card__text__sub-title {
  font-size: 1rem;
  font-weight: 300;
  margin: 10px;
}

.slide-card__img {
  width: 50%;
  height: 100%;
  border-radius: 0px 10px 10px 0px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.slide-card__img img {
  width: auto;
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
  background-color: #fbd1d9;
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
  cursor: pointer;
  padding: 5px;
  border-radius: 50%;
  margin: 0px 2px;
}

.controller__btn:hover {
  background-color: #e3bdc5;
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
  cursor: pointer;
  height: 30px;
  background-color: $white;
  border-radius: 20px;
  padding: 0px 20px;
  border: #8b8b9d 1px solid;
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
