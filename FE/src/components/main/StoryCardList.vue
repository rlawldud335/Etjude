<template>
  <Carousel :settings="settings" :breakpoints="breakpoints">
    <Slide v-for="slide in testdatas" :key="slide">
      <StoryCardItem :carditem="slide"></StoryCardItem>
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
import StoryCardItem from "@/components/main/StoryCardItem.vue";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "StoryCardList",
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
<style scoped></style>
