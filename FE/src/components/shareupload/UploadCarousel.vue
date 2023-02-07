<template lang="">
  <Carousel :settings="settings">
    <Slide v-for="slide in 10" :key="slide">
      <div class="carousel__item">
        <div class="imgcard">
          {{ slide }}
        </div>
      </div>
    </Slide>
    <template #addons>
      <Navigation />
    </template>
  </Carousel>
</template>
<script>
import { Carousel, Navigation, Slide } from "vue3-carousel";
import "vue3-carousel/dist/carousel.css";

export default {
  name: "UploadCarousel",
  components: {
    Carousel,
    Slide,
    Navigation,
  },
  setup() {
    const settings = {
      itemsToShow: 1,
      snapAlign: "center",
      wrapAround: true,
    };
    return {
      settings,
    };
  },
};
</script>
<style lang="scss" scoped>
.imgcard {
  width: 100px;
  height: 100px;
  background-color: aqua;
}
</style>
