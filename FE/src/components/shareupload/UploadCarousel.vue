<template>
  <Carousel :settings="settings">
    <Slide v-for="slide in dummydata" :key="slide">
      <div class="carousel__item">
        <img :src="slide.thumbnail_url" alt="thumbnail" />
      </div>
    </Slide>
    <template #addons>
      <Navigation />
    </template>
  </Carousel>
</template>

<script>
import { defineComponent, ref } from "vue";
import { Carousel, Navigation, Slide } from "vue3-carousel";
import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "UploadCarousel",
  components: {
    Carousel,
    Slide,
    Navigation,
  },
  props: {
    dummydata: {},
  },
  setup(props) {
    const dumdum = ref(props.dummydata);
    const settings = {
      itemsToShow: 3,
      snapAlign: "start",
      wrapAround: true,
    };
    return {
      settings,
      dumdum,
    };
  },
  // carousel settings
});
</script>
<style lang="scss" scoped>
img {
  width: 132px;
  height: 92px;
  object-fit: cover;
}
</style>
