<template>
  <div class="film__popular">
    <h1 class="film__popular-title">인기필름</h1>
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
  margin: 60px 100px;
  display: flex;
  flex-direction: column;
}

.film__popular-title {
  min-width: 150px;
  font-weight: 600;
  font-size: 24px;
  margin-left: 7%;
}

.film__list {
  padding: 600px;
}
</style>
