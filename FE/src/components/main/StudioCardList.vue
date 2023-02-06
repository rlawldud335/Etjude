<template>
  <Carousel :settings="settings" :breakpoints="breakpoints">
    <Slide v-for="slide in testdatas" :key="slide">
      <div class="carousel__item">
        <router-link to="/main">
          <img class="itemimg" :src="require(`@/assets/images/profile.jpg`)" alt="" />
          <div class="itemtext">
            <div class="rightitem">
              <div class="itemtextteamname">{{ slide.team }}</div>
              <div class="itemtexttitle">{{ slide.title }}</div>
              <div class="itemtextdate">{{ slide.date }}</div>
            </div>
            <div class="leftitem">
              <div class="itemtextdday">{{ slide.dday }}</div>
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
import testdata from "@/dummy/testdata.json";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "StuidoCardItem",
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
  border-radius: 0.5rem;
  /* box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.25); */
  margin: 10px;
}
.itemimg {
  border-radius: 0.5rem 0.5rem 0 0;
  height: 134px;
  width: 257px;
  object-fit: cover;
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
  padding-left: 26px;
  padding-right: 26px;
  justify-content: space-between;
}
.itemtextteamname {
  margin-bottom: 3px;
  text-align: left;
  font-size: 16px;
  font-weight: bold;
}
.itemtexttitle,
.itemtextdate {
  margin: 2px;
  padding-left: 5px;
  text-align: left;
  font-size: 12px;
}

.itemtextdday {
  display: inline;
  padding-left: 3px;
  text-align: left;
  font-size: 16px;
}
</style>
