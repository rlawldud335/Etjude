<template>
  <div class="studio__film-tab">
    <FilmTabFilm v-for="film in films" :key="film.id" :film="film"></FilmTabFilm>
  </div>
</template>
<script>
import FilmTabFilm from "@/components/studio/FilmTabFilm.vue";

export default {
  name: "FilmTab",
  components: {
    FilmTabFilm,
  },
  props: {
    films: Array,
  },
};
</script>
<style scoped lang="scss">
.studio__film-tab {
  display: flex;
  flex-direction: column;
}
</style>
