<template>
  <Carousel ref="myCarousel" :settings="settings" :breakpoints="breakpoints">
    <Slide v-for="slide in myStudioList" :key="slide" class="Slide">
      <StudioCardItem :carditem="slide"></StudioCardItem>
    </Slide>
  </Carousel>
  <button class="prev" @click="prevSlide">&lt;</button>
  <button class="next" @click="nextSlide">></button>
</template>

<script>
import { defineComponent, ref } from "vue";
import { Carousel, Slide } from "vue3-carousel";
import { getMyStudio } from "@/api/users";
import StudioCardItem from "../common/StudioCardItem.vue";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "StuidoCardItem",
  components: {
    Carousel,
    Slide,
    StudioCardItem,
  },
  setup() {
    // real data
    const myStudioList = ref([]);
    const myCarousel = ref(null);
    // carousel settings
    const settings = {
      itemsToShow: 4,
      snapAlign: "start",
      wrapAround: true,
    };
    const breakpoints = {
      480: {
        itemsToShow: 3,
      },
      1440: {},
    };
    const nextSlide = () => myCarousel.value.next();
    const prevSlide = () => myCarousel.value.prev();
    getMyStudio(
      {
        user_id: 2,
      },
      ({ data }) => {
        myStudioList.value = data;
      },
      (error) => {
        console.log(error);
      }
    );
    return {
      myStudioList,
      settings,
      breakpoints,
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
</style>
