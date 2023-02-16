<template lang="">
    <div>
        <div class="post_comment">
      <div class="comment_list" v-for="(item, index) in profilelist" :key="index">
        <img :src="item.profileimg" alt="profileImg" />
        <div class="comment_nickname">{{ item.nickname }}</div>
        {{ item.content }}
      </div>
    </div>
    <div class="borderline"></div>
  <div class="input_container">
    <div class="smile"><smile></smile></div>
    <input type="text" aria-labelledby="firstname" />
    <div class="send"><send></send></div>
  </div>
    </div>
</template>
<script>

import smile from "@/assets/icons/smile.svg";
import send from "@/assets/icons/send.svg";

export default {
    components: {
        smile,
        send,
    }
}
</script>
<style lang="scss">
.post_comment {
    display: flex;
    flex-direction: column;
    height: 250px;
    // min-height: 100%;
    max-height: 250px;
    // height: 40%;
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

.borderline {
    border: none;
}

.input_container {
    display: flex;
    text-align: center;
    align-items: center;
    box-sizing: border-box;
    height: 48px;
    padding-left: 16px;
    padding-right: 16px;

    .smile {
        margin-left: 8px;
        margin-right: 16px;
    }

    .send {
        margin-left: 16px;
        margin-right: 8px;
    }

    input {
        width: 100%;
        height: 80%;
        padding-left: 16px;
        padding-right: 16px;
        box-sizing: border-box;
        border: 0;
        outline: 0;
        border-radius: 15px;
        background-color: rgb(233, 233, 233);
    }
}</style>