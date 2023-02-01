<template>
  <div class="film-list">
    <FilmListItem v-for="film in allFilms" :key="film.id" :film="film"> </FilmListItem>
    <v-pagination
      v-model="page"
      :pages="10"
      :range-size="1"
      active-color="#DCEDFF"
      @update:modelValue="updateHandler"
    />
  </div>
</template>
<script>
import FilmListItem from "@/components/film/FilmListItem.vue";
import allFilms from "@/dummy/filmdummydata/page1.json";
import VPagination from "@hennge/vue3-pagination";
import "@hennge/vue3-pagination/dist/vue3-pagination.css";

export default {
  name: "FilmList",
  components: {
    FilmListItem,
    VPagination,
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
