<template>
  <div class="profile-comments">내 댓글</div>
  <div v-if="MyStudioData == null">적은 댓글이 없습니다</div>
  <div v-else class="MyFilm-result"></div>
</template>

<script>
import { getMyComment } from "@/api/users";
import { ref } from "vue";

export default {
  name: "ProfileCommentList",
  components: {},
  props: {},
  setup() {
    const MyCommentData = ref();
    getMyComment(
      { user_id: 1 },
      ({ data }) => {
        data.forEach((array) => {
          MyCommentData.value.push([array.studioId, array.studioTitle]);
        });
        console.log("내 댓글 데이터:", MyCommentData);
      },
      (error) => {
        console.log("내 댓글 찾기 에러:", error);
      }
    );
    return {};
  },
};
</script>
<style lang="scss" scoped></style>
