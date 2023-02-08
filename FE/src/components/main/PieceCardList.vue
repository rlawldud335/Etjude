<template>
  <Carousel :settings="settings" :breakpoints="breakpoints">
    <Slide v-for="slide in testdatas" :key="slide">
      <PieceCardItem :carditem="slide"></PieceCardItem>
    </Slide>
  </Carousel>
</template>

<script>
import { defineComponent } from "vue";
import { Carousel, Slide } from "vue3-carousel";
import testdata from "@/dummy/PieceCardItemData.json";
import PieceCardItem from "./PieceCardItem.vue";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "PieceCardList",
  components: {
    Carousel,
    Slide,
    PieceCardItem,
  },
  data: () => ({
    // dummy data
    testdatas: testdata,
    // carousel settings
    settings: {
      itemsToShow: 5,
      snapAlign: "start",
      wrapAround: true,
    },
    breakpoints: {
      480: {
        itemsToShow: 1,
      },
      960: {
        itemsToShow: 3,
      },
      1440: {},
    },
  }),
});
</script>
<style scoped></style>
