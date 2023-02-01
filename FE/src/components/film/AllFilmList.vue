<template>
  <div class="film-list">
    <FilmListItem v-for="film in dummyfilms" :key="film.id" :film="film"> </FilmListItem>
    <v-pagination
      v-model="page"
      :pages="10"
      :range-size="1"
      active-color="#DCEDFF"
      @update:modelValue="updateHandler"
      class="film-list__pagination"
      @click="changePage"
    />
  </div>
  <span>테스트용 page 확인 : {{ page }}</span>
</template>
<script>
import { ref } from "vue";
// import axios from "axios";
import FilmListItem from "@/components/film/FilmListItem.vue";
import dummyfilms from "@/dummy/filmdummydata/page1.json";
import VPagination from "@hennge/vue3-pagination";
import "@hennge/vue3-pagination/dist/vue3-pagination.css";

/* pagination 연결
기본 페이지는 1인 상태이고 pagination 클릭시 changePage 메쏘드가 작동
그러면 페이지가 바뀌고 새롭게 axios 요청함 이때 페이지는 pagination와 v-model로 양방향 바인딩 되어있는 page 데이터 이용
films 데이터가 바뀌고 바뀌면서 업데이트가 되게 함
*/

export default {
  name: "FilmList",
  components: {
    FilmListItem,
    VPagination,
  },
  setup() {
    const page = ref(1);
    // const changeFilm = () => {
    //   axios({
    //     method: "get",
    //     url: ``,
    //     params: {
    //         page
    //       }
    //   })
    //     .then((res) => {
    //       console.log(res);
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
    // };
    return {
      page,
      dummyfilms,
      // changeFilm,
    };
  },
};
</script>

<style scoped lang="scss">
.film-list {
  display: flex;
  margin: 0px 60px;
  flex-wrap: wrap;
  justify-content: center;
}

.film-list__pagination {
  margin: 20px 0px;
}
</style>
