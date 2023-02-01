<template>
  <div class="film-list">
    <FilmListItem v-for="film in films" :key="film.id" :film="film"> </FilmListItem>
  </div>
</template>
<script>
import FilmListItem from "@/components/film/FilmListItem.vue";

export default {
  name: "FilmList",
  components: {
    FilmListItem,
  },
  props: {
    films: Array,
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
