<template>
  <div class="film-list">
    <FilmListItem></FilmListItem>
    <FilmListItem></FilmListItem>
    <FilmListItem></FilmListItem>
    <FilmListItem></FilmListItem>
  </div>
</template>
<script>
import FilmListItem from "@/components/film/FilmListItem.vue";

export default {
  name: "FilmList",
  components: {
    FilmListItem,
  },
};
</script>

<style lang="scss">
.film-list {
  display: flex;
  margin: 200px auto;
}
</style>
