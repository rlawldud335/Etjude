<template>
  <Carousel ref="myCarousel" :settings="settings" :breakpoints="breakpoints" class="Carousel">
    <Slide v-for="slide in recommendPieceList" :key="slide" class="Slide">
      <PieceCardItem :carditem="slide" class="item"></PieceCardItem>
    </Slide>
  </Carousel>
  <button class="prev" @click="prevSlide">&lt;</button>
  <button class="next" @click="nextSlide">></button>
</template>

<script>
import { defineComponent, ref } from "vue";
import { Carousel, Slide } from "vue3-carousel";
import { getRecommendWork } from "@/api/work";
import PieceCardItem from "@/components/common/PieceCardItem.vue";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "PieceCardList",
  components: {
    Carousel,
    Slide,
    PieceCardItem,
  },
  setup() {
    // real data
    const recommendPieceList = ref([]);
    const myCarousel = ref(null);
    const settings = {
      itemsToShow: 5,
      snapAlign: "start",
      wrapAround: true,
    };
    const nextSlide = () => myCarousel.value.next();
    const prevSlide = () => myCarousel.value.prev();
    getRecommendWork(
      ({ data }) => {
        recommendPieceList.value = data;
      },
      (error) => {
        console.log("추천 작품 에러:", error);
      }
    );
    return {
      recommendPieceList,
      settings,
      myCarousel,
      nextSlide,
      prevSlide,
    };
  },
});
</script>
<style scoped>
button {
  background-color: white;
  border: none;
  font-size: 30px;
  cursor: pointer;
}
.next {
  position: absolute;
  top: 46%;
  right: -5%;
}
.prev {
  position: absolute;
  top: 46%;
  left: -4.5%;
}
.Carousel {
  margin: 0 -22px;
}
</style>
