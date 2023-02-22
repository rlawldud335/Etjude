<template lang="">
  <div>
    <div class="clim-contents-container">
      <div class="post_header">
        <div class="post_title">
          {{ postdata.articleTitle }}
          <div class="post_date">{{ postdata.articleCreatedDate }}</div>
        </div>
        <div class="post_menu"></div>
      </div>
      <div class="post_main">
        <div class="post_text">
          {{ postdata.articleContent }}
        </div>
        <div class="post_icon">
          <heart class="heart"></heart><talk class="talk"></talk><share class="share"></share>
        </div>
        <div class="post_favor">좋아요 0개</div>
      </div>
    </div>
  </div>
</template>
<script>
import { ref } from "vue";
// import hamburger from "@/assets/icons/hamburger.svg";
import heart from "@/assets/icons/heart.svg";
import talk from "@/assets/icons/talk.svg";
import share from "@/assets/icons/share.svg";

export default {
  components: {
    // hamburger,
    heart,
    talk,
    share,
  },
  props: {
    filmdata: Object,
  },
  setup(props) {
    const postdata = ref(props.filmdata);
    return { postdata };
  },
};
</script>
<style lang="scss" scoped>
.clim-contents-container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  padding: 20px;
  box-sizing: border-box;
}

.post_header {
  display: flex;
  //   height: 40px;
  justify-content: space-between;
  align-items: center;
  padding: 5px 3px 5px 7px;
}

.post_title {
  font-size: 1.3rem;
  font-weight: 600;

  .post_date {
    font-size: 1rem;
    font-weight: 100;
    margin-top: 10px;
    color: gray;
  }
}

.post_main {
  display: flex;
  flex-direction: column;
  //   height: 232px;
  justify-content: space-between;
  padding: 16px 3px 0 7px;
  font-weight: 300;
  height: 65%;
}

.post_menu {
  margin-bottom: 24px;
}

.post_text {
  height: 80px;
  // min-height: 100%;
  max-height: 200px;
  //   box-shadow: 0 0 0 1px #000 inset;
}

.post_icon {
  box-sizing: content-box;

  .heart,
  .talk,
  .share {
    margin: 8px;
  }
}

.post_favor {
  margin-left: 8px;
  margin-top: 8px;
}
</style>
