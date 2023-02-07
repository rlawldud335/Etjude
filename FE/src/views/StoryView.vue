<template lang="">
  <div class="story_container">
    <div class="title_container"></div>
    <div class="main_container main__1136width">
      <div class="left_content">
        <div class="story__tab-list">
          <button
            :class="{ 'story__tab--active': tab.tabvalue === 'storyaccount' }"
            @click="tab.tabvalue = 'storyaccount'"
          >
            <span>스토리 설명</span>
          </button>
          <button
            :class="{ 'story__tab--active': tab.tabvalue === 'storycharacter' }"
            @click="tab.tabvalue = 'storycharacter'"
          >
            <span>배역설명</span>
          </button>
          <button
            :class="{ 'story__tab--active': tab.tabvalue === 'storyscript' }"
            @click="tab.tabvalue = 'storyscript'"
          >
            <span>스크립트</span>
          </button>
          <div class="story__active-bar-area">
            <div
              :class="[
                {
                  story__storyaccount: tab.tabvalue === 'storyaccount',
                  story__storycharacter: tab.tabvalue === 'storycharacter',
                  story__storyscript: tab.tabvalue === 'storyscript',
                },
                'active-bar',
              ]"
            ></div>
          </div>
        </div>
        <StoryAccount
          v-show="tab.tabvalue === 'storyaccount'"
          :describtion="StoryDummyData.describtion"
        ></StoryAccount>
        <StoryCharacter
          v-show="tab.tabvalue === 'storycharacter'"
          :roles="StoryDummyData.role"
        ></StoryCharacter>
        <StoryScript v-show="tab.tabvalue === 'storyscript'"></StoryScript>
      </div>
      <div class="right_content">
        <div class="createcard">
          <router-link to="/studio">
            <button class="modal-button">스튜디오 생성하기</button>
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
import StoryDummyData from "@/dummy/storyDummyData.json";

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
      tab = "storycharacter";
      console.log(tab, "배역설명");
    };
    const storyscript = () => {
      tab = "storyscript";
      console.log(tab, "스크립트");
    };
    return {
      tab,
      StoryDummyData,
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
  align-items: center;
}
.title_container {
  width: 100%;
  height: 366px;
  background-color: antiquewhite;
}
.main_container {
  box-sizing: border-box;
  padding: 50px 0px;
  display: flex;
}
.left_content {
  display: flex;
  flex-direction: column;
  width: 80rem;
  //   background-color: aqua;
}

.story__tab-list {
  border-bottom: 1px #757575 solid;
  display: flex;
  flex-wrap: nowrap;
  margin-right: 30px;
  position: relative;
  button {
    border: none;
    background-color: white;
    padding: 10px;
    width: 130px;
    min-width: 130px;
    display: flex;
    justify-content: center;
    align-items: center;
    white-space: nowrap;
    cursor: pointer;
  }
  .story__tab--active {
    font-weight: 700;
  }
}
.active-bar {
  position: absolute;
  height: 6px;
  top: 35px;
  transition: 1s;
  width: 130px;
  background: #ff5775;
  transition: 0.3s ease;
}
.story__storyaccount {
  right: 618px;
}
.story__storycharacter {
  right: 488px;
}
.story__storyscript {
  right: 358px;
}

.createcard {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 358px;
  height: 350px;
  background-color: aliceblue;
}
.modal-button {
  width: 315px;
  height: 48px;
  border: none;
  color: white;
  border-radius: 4px;
  background-color: $bana-pink;
  cursor: pointer;
}
</style>
