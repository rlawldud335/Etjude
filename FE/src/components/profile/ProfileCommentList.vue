<template>
  <div class="profile-comments">내 댓글</div>
  <div v-if="MyCommentData == null">적은 댓글이 없습니다</div>
  <div v-else class="MyFilm-result">
    {{ MyCommentData }}
    <ProfileCommentListItem
      v-for="(comment, index) in MyCommentData"
      :key="index"
      :comment="comment"
    ></ProfileCommentListItem>
  </div>
</template>

<script>
import { getMyComment } from "@/api/users";
import { ref } from "vue";
import ProfileCommentListItem from "@/components/profile/ProfileCommentListItem.vue";

export default {
  name: "ProfileCommentList",
  components: { ProfileCommentListItem },
  props: { userId: String },
  setup(props) {
    const MyCommentData = ref();
    getMyComment(
      { user_id: props.userId },
      ({ data }) => {
        MyCommentData.value = data;
      },
      (error) => {
        console.log("내 댓글 찾기 에러:", error);
      }
    );
    return {
      MyCommentData,
    };
  },
};
</script>
<style lang="scss" scoped></style>
