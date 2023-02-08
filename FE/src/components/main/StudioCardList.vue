<template>
  <Carousel :settings="settings" :breakpoints="breakpoints">
    <Slide v-for="slide in myStudioList" :key="slide">
      <StudioCardItem :carditem="slide"></StudioCardItem>
    </Slide>
  </Carousel>
</template>

<script>
import { defineComponent } from "vue";
import { Carousel, Slide } from "vue3-carousel";
import { getMyStudio } from "@/api/users";
import testdata from "@/dummy/testdata.json";
import StudioCardItem from "./StudioCardItem.vue";


import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "StuidoCardItem",
  components: {
    Carousel,
    Slide,
    StudioCardItem,
  },
  created() {
    getMyStudio({
      user_id: 2
    },
      ({ data }) => {
        this.myStudioList = data;
      }, (error) => {
        console.log(error);
      }
    );
  },
  data: () => ({
    // real data
    myStudioList: [],
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
<style scoped>

</style>
