<template>
  <div class="story-account">
    <div class="story__describtion">
      <p class="story_decribtion-title">스토리 설명</p>
      <p class="story_decribtion-text">
        {{ storyDetailAccount }}
      </p>
    </div>
  </div>
</template>
<script>
import { ref } from "vue";
import { getDetailAccount } from "@/api/story";

export default {
  name: "StoryAccount",
  props: {
    describtion: Number,
  },
  setup(props) {
    console.log("스토리 설명");
    const storyDetailAccount = ref();
    getDetailAccount(
      props.describtion,
      ({ data }) => {
        console.log(data);
        storyDetailAccount.value = data;
      },
      (error) => {
        console.log(error);
      }
    );
    return {
      storyDetailAccount,
    };
  },
};
</script>
<style lang="scss">
.story__describtion {
  padding: 50px;
}
.story_decribtion-title {
  font-size: 20px;
}
.story_decribtion-text {
  margin: 20px 0px;
  font-size: 16px;
  line-height: 140%;
}
</style>
