<template>
  <div class="film-list">
    <FilmListItem v-for="film in popularFilms" :key="film.id" :film="film"> </FilmListItem>
  </div>
</template>
<script>
import FilmListItem from "@/components/film/FilmListItem.vue";
import films from "@/dummy/filmDummyData.json";
import popularFilms from "@/dummy/popularFilmDummyData.json";

export default {
  name: "FilmList",
  components: {
    FilmListItem,
  },
  setup() {
    return {
      films,
      popularFilms,
    };
  },
};
</script>

<style scoped lang="scss">
.film-list {
  display: flex;
  margin: 20px 60px;
  justify-content: space-between;
  flex-wrap: wrap;
}
</style>
