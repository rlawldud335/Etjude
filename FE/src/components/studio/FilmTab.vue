<template>
  <div class="studio__film-tab">
    <FilmTabFilm v-for="film in films" :key="film.id" :film="film"></FilmTabFilm>
    <div class="studio-tab__button-section">
      <button
        :class="[
          makingButton.active
            ? 'studio-tab__making-button--active'
            : 'studio-tab__making-button--disable',
        ]"
        @click="plusMakingCount"
      >
        필름 생성하기 ( {{ makingButton.count }} / {{ makingButton.possibleCount }} )
      </button>
    </div>
  </div>
</template>
<script>
import { reactive } from "vue";
import FilmTabFilm from "@/components/studio/FilmTabFilm.vue";

export default {
  name: "FilmTab",
  components: {
    FilmTabFilm,
  },
  props: {
    films: Array,
  },
  setup() {
    const makingButton = reactive({
      count: 0,
      possibleCount: 3,
      active: true,
    });
    const plusMakingCount = () => {
      if (makingButton.count < makingButton.possibleCount) {
        makingButton.count += 1;
      } else {
        // eslint-disable-next-line no-alert
        alert("더이상 생성 불가");
      }
      if (makingButton.count === makingButton.possibleCount) {
        makingButton.active = false;
      }
    };
    return {
      makingButton,
      plusMakingCount,
    };
  },
};
</script>
<style scoped lang="scss">
.studio__film-tab {
  display: flex;
  flex-direction: column;
}

.studio-tab__button-section {
  button {
    font-size: 14px;
    font-weight: 500;
    border: none;
    padding: 8px 30px;
    white-space: nowrap;
    border-radius: 8px;
  }
  .studio-tab__making-button--active {
    color: white;
    background-color: $bana-pink;
    cursor: pointer;
  }
  .studio-tab__making-button--disable {
    background-color: rgb(131, 130, 130);
    color: white;
  }
  margin-top: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
