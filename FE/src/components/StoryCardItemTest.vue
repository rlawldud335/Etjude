<template>
  <Carousel :settings="settings" :breakpoints="breakpoints">
    <Slide v-for="slide in testdatas" :key="slide">
      <div class="carousel__item">
        <router-link to="/">
          <img :src="require(`@/images/profile.jpg`)" alt="" />
          <div class="itemtext">
            <div class="itemtextteamname">{{ slide.team }}</div>
            <div class="itemtextdday">{{ slide.dday }}</div>
            <div class="itemtexttitle">{{ slide.title }}</div>
            <div class="itemtextdate">{{ slide.date }}</div>
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
import testdata from "./testdata.json";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "startBreakpoints",
  components: {
    Carousel,
    Slide,
    Navigation,
  },
  data: () => ({
    testdatas: testdata,
    // carousel settings
    settings: {
      itemsToShow: 1,
      snapAlign: "center",
    },
    // breakpoints are mobile first
    // any settings not specified will fallback to the carousel settings
    breakpoints: {
      // 700px and up
      700: {
        itemsToShow: 3.5,
        snapAlign: "center",
      },
      // 1024 and up
      1024: {
        itemsToShow: 4,
        snapAlign: "start",
      },
    },
  }),
});
</script>
<style>
.carousel__item {
  background-color: #fff;
  border-radius: 0.5rem;
  box-shadow: 0.05rem 0.1rem 0.3rem -0.03rem rgba(0, 0, 0, 0.45);
}
img {
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
  padding-top: 16px;
  padding-bottom: 16px;
  padding-left: 32px;
}
.itemtextteamname {
  margin-bottom: 3px;
  text-align: left;
  font-size: 16px;
}
.itemtexttitle,
.itemtextdate {
  padding-left: 3px;
  text-align: left;
  font-size: 12px;
}

.itemtextdday {
  margin-left: auto;
  padding-left: 3px;
  text-align: left;
  font-size: 12px;
}
</style>
