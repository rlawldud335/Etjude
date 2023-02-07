<template>
  <Carousel :settings="settings" :breakpoints="breakpoints">
    <Slide v-for="slide in testdatas" :key="slide">
      <StudioCardItem :carditem="slide"></StudioCardItem>
    </Slide>
    <template #addons>
      <Navigation />
    </template>
  </Carousel>
</template>

<script>
import { defineComponent } from "vue";
import { Carousel, Navigation, Slide } from "vue3-carousel";
import testdata from "@/dummy/testdata.json";
import StudioCardItem from "./StudioCardItem.vue";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "StuidoCardItem",
  components: {
    Carousel,
    Slide,
    Navigation,
    StudioCardItem,
  },
  data: () => ({
    // dummy data
    testdatas: testdata,
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
