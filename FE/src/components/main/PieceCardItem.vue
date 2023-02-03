<template>
  <Carousel :settings="settings" :breakpoints="breakpoints">
    <Slide v-for="slide in testdatas" :key="slide">
      <div class="carousel__item">
        <router-link to="/main">
          <img class="itemimg" :src="require(`@/assets/images/헤어질결심.jpg`)" alt="" />
          <div class="itemtext">
            <div class="rightitem">
              <div class="itemtextteamname">{{ slide.team }}</div>
              <div class="itemtexttitle">{{ slide.title }}</div>
            </div>
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
import testdata from "@/dummy/PieceCardItemData.json";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "PieceCardItem",
  components: {
    Carousel,
    Slide,
    Navigation,
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
<style scoped>
a {
  text-decoration: none;
  color: black;
}
.carousel__item {
  background-color: #fff;
  box-shadow: 0.01rem 0.05rem 0.3rem -0.03rem rgba(0, 0, 0, 0.45);
  margin: 10px;
}
.itemimg {
  height: 187px;
  width: 165px;
  object-fit: fill;
  /* // height: max(10rem, 25vh); */
  max-height: max(10rem, 30vh);
  aspect-ratio: 4/3;
  mix-blend-mode: var(--card-blend-mode);
  /* // filter: grayscale(100); */
}
.itemtext {
  display: flex;
  padding-top: 8px;
  padding-bottom: 8px;
  padding-left: 12px;
  padding-right: 12px;
}
.itemtextteamname {
  margin-bottom: 5px;
  text-align: left;
  font-size: 14px;
  font-weight: bold;
}
.itemtexttitle {
  text-align: left;
  font-size: 12px;
}
</style>
