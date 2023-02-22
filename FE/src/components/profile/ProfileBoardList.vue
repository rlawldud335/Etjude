<template>
  <div class="profile-boards">내 게시글</div>
  <div v-if="MyStudioData == null">셍성된 게시글이 없습니다</div>
  <FilmListItem
    v-else
    v-for="(item, index) in MyArticleData"
    :key="index"
    :film="item"
  ></FilmListItem>
</template>

<script>
import { getMyArticle } from "@/api/users";
import { ref } from "vue";
import FilmListItem from "../film/FilmListItem.vue";

export default {
  name: "ProfileBoardList",
  components: { FilmListItem },
  props: {},
  setup() {
    const MyArticleData = ref();
    getMyArticle(
      { user_id: 1 },
      ({ data }) => {
        data.forEach((array) => {
          MyArticleData.value.push([array.studioId, array.studioTitle]);
        });
        console.log("내 게시글 데이터:", MyArticleData);
      },
      (error) => {
        console.log("내 게시글 찾기 에러:", error);
      }
    );
    return { MyArticleData };
  },
};
</script>
<style lang="scss" scoped></style>
