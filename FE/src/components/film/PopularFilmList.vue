<template>
  <div class="film-list">
    <FilmListItem
      v-for="film in popularFilmList"
      :key="film.id"
      :film="film"
      :createdDate="film.articleCreatedDate"
    >
    </FilmListItem>
  </div>
</template>
<script>
import FilmListItem from "@/components/film/FilmListItem.vue";
import dummyPopularFilms from "@/dummy/popularFilmDummyData.json";
import { getPopularFilm } from "@/api/films";
import { ref } from "vue";

export default {
  name: "FilmList",
  components: {
    FilmListItem,
  },
  setup() {
    const popularFilmList = ref(null);
    getPopularFilm(
      ({ data }) => {
        console.log(data);
        popularFilmList.value = data;
      },
      (error) => {
        console.log(error);
      }
    );
    return {
      popularFilmList,
      dummyPopularFilms,
    };
  },
};
</script>

<style scoped lang="scss">
.film-list {
  display: flex;
  margin: 0px 60px;
  flex-wrap: wrap;
}
</style>
