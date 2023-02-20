<template>
  <div class="film-share__comment-item">
    <div class="film-share__profile-frame">
      <img :src="comment.userPicture" alt="" />
    </div>
    <div class="film-share__comment-body">
      <div>
        <span class="film-share__comment-nickname">{{ comment.userNickname }}</span>
        <span class="film-share__comment-created">{{ diffCreated }}</span>
      </div>
      <span class="film-share__comment-content">{{ comment.commentContents }}</span>
    </div>
    <div class="film-share__comment-delete" @click="clickCommentDelete" v-if="isCommentUser">
      <deleteIcon />
    </div>
  </div>
</template>
<script>
import { ref, computed } from "vue";
import { useStore } from "vuex";
import deleteIcon from "@/assets/icons/CommentDeleteButton.svg";
import { deleteComment } from "@/api/comment";

export default {
  name: "FilmCommenntItem",
  components: {
    deleteIcon,
  },
  props: {
    comment: Object,
  },
  emits: ["update-comment-list"],
  setup(props, { emit }) {
    const store = useStore();
    const diffCreated = computed(() => {
      const createdDate = new Date(props.comment.commentCreateTime);
      const now = new Date();
      const timeDiff = now.getTime() - createdDate.getTime() - 9 * 60 * 60 * 1000;

      let diffText;
      if (timeDiff / (1000 * 60) < 1) {
        diffText = `${parseInt(timeDiff / 1000, 10)}초 전`;
      } else if (timeDiff / (1000 * 60 * 60) < 1) {
        diffText = `${parseInt(timeDiff / (1000 * 60), 10)}분 전`;
      } else if (timeDiff / (1000 * 60 * 60 * 24) < 1) {
        diffText = `${parseInt(timeDiff / (1000 * 60 * 60), 10)}시간 전`;
      } else if (timeDiff / (1000 * 60 * 60 * 24 * 30) < 1) {
        diffText = `${parseInt(timeDiff / (1000 * 60 * 60 * 24), 10)}일 전`;
      } else {
        diffText = `${createdDate.getFullYear()}/${
          createdDate.getMonth() + 1
        }/${createdDate.getDate()}`;
      }
      return diffText;
    });
    const isCommentUser = ref(false);
    const setCommentUser = () => {
      isCommentUser.value = props.comment.userId === store.state.user.userId;
    };
    setCommentUser();
    const clickCommentDelete = () => {
      deleteComment(
        {
          comment_id: props.comment.commentId,
        },
        () => {
          emit("update-comment-list");
        },
        (error) => {
          console.log("댓글 삭제 오류:", error);
        }
      );
    };

    return {
      diffCreated,
      clickCommentDelete,
      isCommentUser,
    };
  },
};
</script>
<style scoped lang="scss">
.film-share__comment-item {
  display: flex;
  flex-direction: row;
  margin: 0px 20px 10px 20px;
}
.film-share__profile-frame {
  flex: 1;
  min-height: 30px;
  min-width: 30px;
  max-height: 30px;
  max-width: 30px;
  height: 30px;
  width: 30px;
  border-radius: 50%;
  overflow: hidden;
  justify-content: center;
  align-items: center;
  box-sizing: content-box;
  margin: 2px 8px;
  img {
    height: 100%;
    width: 100%;
    object-fit: cover;
  }
}
.film-share__comment-body {
  margin-left: 5px;
  display: flex;
  flex-direction: column;
  flex: 10.5;
}
.film-share__comment-nickname {
  font-size: 14px;
  line-height: 140%;
  font-weight: 500;
}
.film-share__comment-created {
  font-size: 14px;
  margin-left: 8px;
  line-height: 140%;
  font-weight: 300;
}
.film-share__comment-content {
  line-height: 140%;
  font-weight: 400;
  font-size: 14px;
}
.film-share__comment-delete {
  flex: 0.5;
  float: right;
  display: none;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}
.film-share__comment-item:hover .film-share__comment-delete {
  display: flex;
}
</style>
