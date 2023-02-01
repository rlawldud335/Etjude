<template>
  <div class="film-list">
    <FilmListItem v-for="film in allFilms" :key="film.id" :film="film"> </FilmListItem>
  </div>
</template>
<script>
import FilmListItem from "@/components/film/FilmListItem.vue";
import allFilms from "@/dummy/filmdummydata/page1.json";

export default {
  name: "FilmList",
  components: {
    FilmListItem,
  },

  setup() {
    return {
      allFilms,
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
