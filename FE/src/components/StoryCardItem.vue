<template>
  <Carousel :settings="settings" :breakpoints="breakpoints">
    <Slide v-for="slide in testdatas" :key="slide">
      <div class="carousel__item">
        <router-link to="/">
          <img class="itemimg" :src="slide.img" alt="" />
          <div class="itemtext">
            <div class="itemtexttitle">{{ slide.title }}</div>
            <div class="itemtextcategory">{{ slide.category }}</div>
            <div class="itemtextfavor">좋아요 {{ slide.favor }}</div>
          </div>
        </router-link>
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
import StoryCardItemData from "./StoryCardItemData.json";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "StoryCardItem",
  components: {
    Carousel,
    Slide,
    Navigation,
  },
  data: () => ({
    // dummy data
    testdatas: StoryCardItemData,
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
.itemimg {
  border-radius: 0.5rem 0.5rem 0 0;
  height: 134px;
  width: 257px;
  object-fit: fill;
  /* // height: max(10rem, 25vh); */
  max-height: max(10rem, 30vh);
  aspect-ratio: 4/3;
  mix-blend-mode: var(--card-blend-mode);
  /* // filter: grayscale(100); */
}
.itemtext {
  display: flex;
  flex-direction: column;
  padding-top: 16px;
  padding-bottom: 16px;
  padding-left: 26px;
  padding-right: 26px;
}
.itemtexttitle {
  margin-bottom: 3px;
  padding: 1px;
  text-align: left;
  font-size: 16px;
  font-weight: bold;
}
.itemtextcategory,
.itemtextfavor {
  margin: 3.5px;
  text-align: left;
  font-size: 12px;
}
</style>
