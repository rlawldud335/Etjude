<template>
  <Carousel ref="myCarousel" :settings="settings" :breakpoints="breakpoints">
    <Slide v-for="slide in myStudioList" :key="slide" class="Slide">
      <StudioCardItem :carditem="slide"></StudioCardItem>
    </Slide>
  </Carousel>
  <button class="prev" @click="prevSlide">&lt;</button>
  <button class="next" @click="nextSlide">></button>
</template>

<script>
import { defineComponent, ref, computed } from "vue";
import { Carousel, Slide } from "vue3-carousel";
import { getMyStudio } from "@/api/users";
import { useStore } from "vuex";
import StudioCardItem from "../common/StudioCardItem.vue";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "StuidoCardItem",
  components: {
    Carousel,
    Slide,
    StudioCardItem,
  },
  setup() {
    const store = useStore();
    // real data
    const myStudioList = ref([]);
    const studioCount = computed(() => myStudioList.value.length);
    const updateStudioCount = () => {
      store.dispatch("getStudioCount", studioCount.value);
    };
    const myCarousel = ref(null);
    // carousel settings
    const settings = {
      itemsToShow: 4,
      snapAlign: "start",
      wrapAround: false,
    };
    const breakpoints = {
      480: {
        itemsToShow: 3,
      },
      1440: {},
    };
    const nextSlide = () => myCarousel.value.next();
    const prevSlide = () => myCarousel.value.prev();
    const userId = computed(() => store.state.user.userId);
    if (userId.value) {
      getMyStudio(
        {
          user_id: userId.value,
        },
        ({ data }) => {
          // const allStudioData = data
          // const myStudioData = allStudioData.filter((studio)=> {
          //   return (studio.)
          // })
          myStudioList.value = data;
          updateStudioCount();
        },
        (error) => {
          console.log(error);
        }
      );
    }
    return {
      myStudioList,
      settings,
      breakpoints,
      myCarousel,
      nextSlide,
      prevSlide,
      userId,
    };
  },
});
</script>
<style scoped>
button {
  background-color: white;
  border: none;
  font-size: 30px;
  cursor: pointer;
}
.next {
  position: absolute;
  top: 46%;
  right: -5%;
}
.prev {
  position: absolute;
  top: 46%;
  left: -4.5%;
}
</style>
