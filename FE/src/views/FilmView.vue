<template>
  <div class="film full-page">
    <div class="film__popular-box full-page">
      <div class="film__popular main__1136width">
        <h1 class="film__popular-title">인기필름</h1>
        <FilmList></FilmList>
      </div>
    </div>
    <div class="film__all main__1136width">
      <h1 class="film__popular-title">전체필름</h1>
      <FilmList></FilmList>
    </div>
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
.full-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}
.film__popular-box {
  width: 100%;
  background-color: transparentize($color: $bana-pink, $amount: 0.9);
  margin: auto auto;
}
.film__popular {
  margin: 60px 0px;
  display: flex;
  flex-direction: column;
}

.film__popular-title {
  min-width: 150px;
  font-weight: 600;
  font-size: 24px;
}

.film__all {
  margin: 60px 0px;
  display: flex;
  flex-direction: column;
}
</style>
