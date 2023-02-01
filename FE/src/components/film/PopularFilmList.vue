<template>
  <div class="film-list">
    <FilmListItem v-for="film in popularFilms" :key="film.id" :film="film"> </FilmListItem>
  </div>
</template>
<script>
import FilmListItem from "@/components/film/FilmListItem.vue";
import popularFilms from "@/dummy/popularFilmDummyData.json";

export default {
  name: "FilmList",
  components: {
    FilmListItem,
  },
  setup() {
    return {
      popularFilms,
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
