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
  </div>
</template>
<script>
import { computed } from "vue";

export default {
  name: "FilmCommenntItem",
  components: {},
  props: {
    comment: Object,
  },
  setup(props) {
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
    return { diffCreated };
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
  min-height: 30px;
  min-width: 30px;
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
}
.film-share__comment-nickname {
  font-size: 14px;
  line-height: 140%;
  font-weight: 500;
}
.film-share__comment-created {
  font-size: 14px;
  margin-left: 5px;
  line-height: 140%;
  font-weight: 300;
}
.film-share__comment-content {
  line-height: 140%;
  font-weight: 400;
  font-size: 14px;
}
</style>
