<template>
  <div class="profile-films">나의 필름</div>
  <div v-if="MyStudioData == null">셍성된 필름이 없습니다</div>
  <div v-else class="MyFilm-result">
    <FilmListItem v-for="(item, index) in MyFilmData" :key="index" :film="item"></FilmListItem>
  </div>
</template>

<script>
import { getMyFilm } from "@/api/users";
import { ref } from "vue";
import FilmListItem from "../film/FilmListItem.vue";

export default {
  name: "ProfileFilmList",
  components: { FilmListItem },
  props: {},
  setup() {
    const MyFilmData = ref();
    getMyFilm(
      { user_id: 1 },
      ({ data }) => {
        data.forEach((array) => {
          MyFilmData.value.push([array.studioId, array.studioTitle]);
        });
        console.log("내 필름 데이터:", MyFilmData);
      },
      (error) => {
        console.log("내 필름 찾기 에러:", error);
      }
    );
    return { MyFilmData };
  },
};
</script>
<style lang="scss" scoped>
.MyFilm-result {
  display: flex;
  flex-wrap: wrap;
  gap: 10px 0px;

  > * {
    margin: 5px 23px;
  }
}
</style>
