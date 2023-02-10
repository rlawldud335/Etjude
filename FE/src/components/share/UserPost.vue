<template lang="">
  <div class="post_container">
    <div class="post_header">
      <div class="post_title">
        {{ postdata.articleTitle }}
        <div class="post_date">{{ postdata.articleCreatedDate }}</div>
      </div>
      <div class="post_menu"><hamburger></hamburger></div>
    </div>
    <div class="post_main">
      <div class="post_text">
        {{ postdata.articleContent }}
      </div>
      <div class="post_icon">
        <heart class="heart"></heart><talk class="talk"></talk><share class="share"></share>
      </div>
      <div class="post_favor">좋아요 9321개</div>
    </div>
    <div class="post_comment">
      <div class="comment_list" v-for="(item, index) in profilelist" :key="index">
        <img :src="item.profileimg" alt="profileImg" />
        <div class="comment_nickname">{{ item.nickname }}</div>
        {{ item.content }}
      </div>
    </div>
  </div>
</template>
oo
<script>
import { ref } from "vue";
import hamburger from "@/assets/icons/hamburger.svg";
import heart from "@/assets/icons/heart.svg";
import talk from "@/assets/icons/talk.svg";
import share from "@/assets/icons/share.svg";

export default {
  name: "UserPost",
  components: {
    hamburger,
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
  data() {
    return {
      profilelist: [
        {
          // eslint-disable-next-line global-require
          profileimg: require("@/assets/images/profile.jpg"),
          nickname: "jung_DM",
          content: "농구가 하고 싶어요...",
        },
        {
          // eslint-disable-next-line global-require
          profileimg: require("@/assets/images/profile.jpg"),
          nickname: "jung_DM",
          content: "코딩도... 하고 싶어요",
        },
      ],
    };
  },
};
</script>
<style lang="scss" scoped>
.post_container {
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  padding: 24px;
}
.post_header {
  display: flex;
  //   height: 40px;
  justify-content: space-between;
  align-items: center;
  padding: 5px 3px 5px 7px;
}
.post_title {
  font-size: 24px;
  font-weight: bold;
  .post_date {
    font-size: 16px;
    font-weight: 100;
    margin-top: 6px;
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
}
.post_menu {
  margin-bottom: 24px;
}
.post_text {
  height: 184px;
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
.post_comment {
  display: flex;
  flex-direction: column;
  min-height: 272px;
  margin-top: 16px;
  padding: 16px 3px 0 7px;
  border-top: 1px solid gray;
  // background-color: coral;
  // box-shadow: 0 0 0 1px #000 inset;
}
.comment_list {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
  img {
    width: 36px;
    height: 36px;
    border-radius: 50%;
  }
  .comment_nickname {
    margin-left: 24px;
    margin-right: 24px;
  }
}
</style>
