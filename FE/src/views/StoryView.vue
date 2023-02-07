<template lang="">
  <div class="story_container">
    <div class="title_container"></div>
    <div class="main_container">
      <div class="left_content">
        <ul class="filter">
          <li @click="tab.tabvalue = 'storyaccount'">스토리 설명</li>
          <li @click="tab.tabvalue = 'StoryCharacter'">배역설명</li>
          <li @click="tab.tabvalue = 'storyscript'">스크립트</li>
        </ul>
        <StoryAccount v-show="tab.tabvalue === 'storyaccount'"></StoryAccount>
        <StoryCharacter v-show="tab.tabvalue === 'StoryCharacter'"></StoryCharacter>
        <StoryScript v-show="tab.tabvalue === 'storyscript'"></StoryScript>
      </div>
      <div class="right_content">
        <div class="createcard">
          <router-link to="/studio">
            <button>스튜디오 생성하기</button>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { reactive } from "vue";
import StoryAccount from "@/components/story/StoryAccount.vue";
import StoryScript from "@/components/story/StoryScript.vue";
import StoryCharacter from "@/components/story/StoryCharacter.vue";

export default {
  name: "StoryView",
  components: {
    StoryAccount,
    StoryScript,
    StoryCharacter,
  },
  setup() {
    let tab = reactive({
      tabvalue: "storyaccount",
    });
    const storyaccount = () => {
      tab = "storyaccount";
      console.log(tab, "스토리설명");
    };
    const stroycharacter = () => {
      tab = "stroycharacter";
      console.log(tab, "배역설명");
    };
    const storyscript = () => {
      tab = "storyscript";
      console.log(tab, "스크립트");
    };
    return {
      tab,
      storyaccount,
      stroycharacter,
      storyscript,
    };
  },
};
</script>
<style lang="scss" scoped>
.story_container {
  display: flex;
  flex-direction: column;
}
.title_container {
  width: 100%;
  height: 366px;
  background-color: antiquewhite;
}
.main_container {
  box-sizing: border-box;
  padding: 50px 100px;
  display: flex;
}
.left_content {
  display: flex;
  flex-direction: column;
  width: 80%;
  height: 1000px;
  //   background-color: aqua;
}
ul {
  list-style: none;
}
li {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 130px;
  height: 44px;
  float: left;
  margin: 5px;
  border: 1px solid gray;
}
.createcard {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 358px;
  height: 350px;
  background-color: aliceblue;
}
button {
  width: 315px;
  height: 48px;
  border: none;
  color: white;
  border-radius: 4px;
  background-color: $bana-pink;
  cursor: pointer;
}
</style>
