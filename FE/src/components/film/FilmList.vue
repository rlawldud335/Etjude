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

<style scoped lang="scss">
.film-list {
  display: flex;
  margin: 20px 10px;
  // width: 90%;
  justify-content: space-between;
}
</style>
