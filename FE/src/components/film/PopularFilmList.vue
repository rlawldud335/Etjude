<template>
  <div class="film-list">
    <FilmListItem v-for="film in dummyPopularFilms" :key="film.id" :film="film"> </FilmListItem>
  </div>
</template>
<script>
import FilmListItem from "@/components/film/FilmListItem.vue";
import dummyPopularFilms from "@/dummy/popularFilmDummyData.json";

export default {
  name: "FilmList",
  components: {
    FilmListItem,
  },
  setup() {
    return {
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
