<template>
  <div class="film__popular">
    <h1 class="film__popular-title">인기필름</h1>
    <FilmList></FilmList>
  </div>
  <div class="film__all">
    <h1 class="film__popular-title">전체필름</h1>
    <FilmList></FilmList>
  </div>
</template>

<script>
import FilmList from "@/components/film/FilmList.vue";

export default {
  name: "FilmView",
  components: {
    FilmList,
  },
};
</script>

<style scoped lang="scss">
.film__popular {
  margin: 60px 200px;
  display: flex;
  flex-direction: column;
  // margin-left: 9%;
}

.film__popular-title {
  min-width: 150px;
  font-weight: 600;
  font-size: 24px;
}

.film__all {
  margin: 60px 200px;
  display: flex;
  flex-direction: column;
}
</style>
