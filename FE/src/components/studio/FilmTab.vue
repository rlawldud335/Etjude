<template>
  <div class="studio__film-tab">
    <FilmTabFilm v-for="film in FlimData" :key="film.film_id" :film="film"></FilmTabFilm>
    <div class="studio-tab__button-section" v-if="captainId == userId">
      <button
        :class="[
          makingButton.active
            ? 'studio-tab__making-button--active'
            : 'studio-tab__making-button--disable',
        ]"
        @click="plusMakingCount"
      >
        필름 생성하기 ( {{ films.length }} / {{ makingButton.possibleCount }} )
      </button>
    </div>
  </div>
</template>
<script>
import { reactive, computed } from "vue";
import FilmTabFilm from "@/components/studio/FilmTabFilm.vue";
import { testMakeFilm } from "@/api/index";
import { saveMakedFilm } from "@/api/films";
import { useStore } from "vuex";

export default {
  name: "FilmTab",
  components: {
    FilmTabFilm,
  },
  props: {
    films: Array,
    studioInfo: Object,
  },
  emits: ["made-flim"],
  setup(props, { emit }) {
    const store = useStore();
    const userId = computed(() => store.state.user.userId);
    const captainId = computed(() => props.studioInfo.captain_id);
    const FlimData = computed(() => props.films);

    const makingButton = reactive({
      possibleCount: 3,
      active: true,
    });
    const plusMakingCount = () => {
      if (props.films.length < makingButton.possibleCount) {
        makingButton.active = false;
        testMakeFilm(
          props.studioInfo.studio_id,
          ({ data }) => {
            saveMakedFilm(
              props.studioInfo.studio_id,
              data,
              () => {
                emit("made-flim", props.studioInfo.studio_id, props.films.length + 1);
                makingButton.active = true;
              },
              (error) => {
                makingButton.active = true;
                console.log("필름 저장 오류", error);
              }
            );
          },
          (error) => {
            console.log("필름 제작 오류:", error);
            makingButton.active = true;
          }
        );
      } else {
        // eslint-disable-next-line no-alert
        alert("더이상 생성 불가");
      }
      if (props.films.length === makingButton.possibleCount) {
        makingButton.active = false;
      }
    };
    return {
      makingButton,
      plusMakingCount,
      userId,
      captainId,
      FlimData,
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
