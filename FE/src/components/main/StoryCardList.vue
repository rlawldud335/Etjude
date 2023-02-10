<template>
  <Carousel :settings="settings" :breakpoints="breakpoints">
    <Slide v-for="slide in recommendStoryList" :key="slide">
      <StoryCardItem :carditem="slide"></StoryCardItem>
    </Slide>
  </Carousel>
</template>

<script>
import { defineComponent } from "vue";
import { Carousel, Slide } from "vue3-carousel";
import StoryCardItemData from "@/dummy/StoryCardItemData.json";
import StoryCardItem from "@/components/common/StoryCardItem.vue";
import { getRecommendStory } from "@/api/story";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "StoryCardList",
  components: {
    Carousel,
    Slide,
    StoryCardItem,
  },
  async created() {
    getRecommendStory(
      ({ data }) => {
        console.log("story-card-list : ", data);
        this.recommendStoryList = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  data: () => ({
    // real data
    recommendStoryList: [],
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
