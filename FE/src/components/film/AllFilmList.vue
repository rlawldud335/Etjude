<template>
  <div class="all-film-list">
    <div class="film-list">
      <FilmListItem
        v-for="film in allFilmList"
        :key="film.id"
        :film="film"
        :createdDate="film.createdDate"
        :likeCount="film.articleLikeCount"
      >
      </FilmListItem>
    </div>
    <v-pagination
      v-model="page"
      :pages="pageCount"
      :range-size="1"
      active-color="#DCEDFF"
      @update:modelValue="updateHandler"
      class="film-list__pagination"
      @click="updatePage"
    />
  </div>
</template>
<script>
import { ref, computed } from "vue";
import FilmListItem from "@/components/film/FilmListItem.vue";
import dummyfilms from "@/dummy/filmdummydata/page1.json";
import VPagination from "@hennge/vue3-pagination";
import "@hennge/vue3-pagination/dist/vue3-pagination.css";
import { getFilmPage } from "@/api/films";

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
    const allFilmList = ref(null);
    const getFilmPageList = () => {
      getFilmPage(
        page.value,
        ({ data }) => {
          console.log(data);
          allFilmList.value = data;
        },
        (error) => {
          console.log(error);
          allFilmList.value = null;
        }
      );
    };
    getFilmPageList();
    const updatePage = () => {
      getFilmPageList();
    };
    const pageCount = computed(() => {
      if (allFilmList.value) {
        return Math.ceil(allFilmList.value[0].totalArticleNumber / 12);
      }
      return pageCount.value;
    });
    return {
      page,
      allFilmList,
      dummyfilms,
      updatePage,
      pageCount,
    };
  },
};
</script>

<style scoped lang="scss">
.all-film-list {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.film-list {
  display: flex;
  margin: 0px 60px;
  flex-wrap: wrap;
}

.film-list__pagination {
  margin: 20px 0px;
}
</style>
