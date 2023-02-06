<template>
  <Carousel :settings="settings" :breakpoints="breakpoints">
    <Slide v-for="slide in testdatas" :key="slide">
      <div class="carousel__item">
        <StoryCardItem></StoryCardItem>
      </div>
    </Slide>
    <template #addons>
      <Navigation />
    </template>
  </Carousel>
</template>

<script>
import { defineComponent } from "vue";
import { Carousel, Navigation, Slide } from "vue3-carousel";
import StoryCardItemData from "@/dummy/StoryCardItemData.json";
import StoryCardItem from "./StoryCardItem.vue";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "StoryCardItem",
  components: {
    Carousel,
    Slide,
    Navigation,
    StoryCardItem,
  },
  data: () => ({
    // dummy data
    testdatas: StoryCardItemData,
    // carousel settings
    settings: {
      itemsToShow: 4,
      snapAlign: "start",
      wrapAround: true,
    },
    breakpoints: {
      480: {
        itemsToShow: 1,
      },
      960: {
        itemsToShow: 2,
      },
      1440: {},
    },
  }),
});
</script>
<style scoped>
a {
  text-decoration: none;
  color: black;
}
.carousel__item {
  background-color: #fff;
  /* border-radius: 0.5rem; */
  /* box-shadow: 0.05rem 0.15rem 0.3rem -0.03rem rgba(0, 0, 0, 0.45); */
  margin: 10px;
}
</style>
