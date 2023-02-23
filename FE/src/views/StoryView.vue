<template lang="">
  <div class="story_container">
    <div class="title_container">
      <div class="title-body main__1136width">
        <div v-if="storydetaildata.storyVideoUrl?.includes('youtube.com')">
          <object
            width="538"
            height="308"
            :data="storydetaildata.storyVideoUrl"
            title="YouTube video player"
            frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            allowfullscreen
          ></object>
        </div>
        <div v-else>
          <video :src="storydetaildata.storyVideoUrl" class="video_content" controls>
            <track kind="captions" />
          </video>
        </div>
        <div class="text_content">
          <div class="category">
            <HOT_BUTTON class="content_icon"></HOT_BUTTON>
            {{ storydetaildata.categoryName }}
          </div>
          <div class="title">{{ storydetaildata.storyTitle }}</div>
          <div class="main_text">
            {{ storydetaildata.storySummary }}
          </div>
          <div class="util">
            <favor class="content_icon"></favor>
            {{ storydetaildata.storyLikeCount }}
            <speachbubble class="content_icon comment"></speachbubble>
            댓글
          </div>
        </div>
      </div>
    </div>
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
        <StoryAccount
          v-show="tab.tabvalue === 'storyaccount'"
          :describtion="storyinfo.story_id"
        ></StoryAccount>
        <StoryCharacter
          v-show="tab.tabvalue === 'storycharacter'"
          :roles="storyinfo.story_id"
        ></StoryCharacter>
        <StoryScript
          v-show="tab.tabvalue === 'storyscript'"
          :scenes="storyinfo.story_id"
        ></StoryScript>
      </div>
      <div class="right_content">
        <div class="createcard">
          <div class="card_title">스튜디오 생성하기</div>
          <div class="card_setting">
            <div class="setting_text">
              대여 기간
              <div class="setting_count">D-7</div>
            </div>
            <div class="setting_text">
              배역 인원
              <div class="setting_count">5명</div>
            </div>
          </div>
          <div class="card_util">
            <div class="util_favor">
              <hearticon class="content_icon"></hearticon>
              좋아요
            </div>
            <div class="util_share">
              <shareicon class="content_icon"></shareicon>
              공유
            </div>
          </div>
          <button class="modal-button" @click="clickCreatedButton">스튜디오 생성하기</button>
        </div>
      </div>
    </div>
  </div>
  <studioCreate
    @close="showModal = false"
    v-model="showModal"
    :story_id="storyinfo.story_id"
  ></studioCreate>
</template>
<script>
import { reactive, ref, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useStore } from "vuex";
import { getStoryDetail } from "@/api/story";
import StoryAccount from "@/components/story/StoryAccount.vue";
import StoryScript from "@/components/story/StoryScript.vue";
import StoryCharacter from "@/components/story/StoryCharacter.vue";
import StoryDummyData from "@/dummy/storyDummyData.json";
import HOT_BUTTON from "@/assets/icons/HOT_BUTTON.svg";
import favor from "@/assets/icons/favor.svg";
import speachbubble from "@/assets/icons/speach_bubble.svg";
import hearticon from "@/assets/icons/hearticon.svg";
import shareicon from "@/assets/icons/shareicon.svg";
import studioCreate from "@/components/story/studioCreate.vue";

export default {
  name: "StoryView",
  components: {
    StoryAccount,
    StoryScript,
    StoryCharacter,
    HOT_BUTTON,
    favor,
    speachbubble,
    hearticon,
    shareicon,
    studioCreate,
  },
  setup() {
    const route = useRoute();
    const router = useRouter();
    const store = useStore();
    const storyinfo = reactive({
      user_id: "1",
      story_id: Number.parseInt(route.params.story_id, 10),
    });
    const storydetaildata = ref({});
    getStoryDetail(
      storyinfo,
      ({ data }) => {
        storydetaildata.value = data;
      },
      (error) => {
        console.log("스토리 상세 탐색 오류:", error);
      }
    );
    const showModal = ref(false);
    const lastPath = computed(() => {
      let path;
      if (route.query.next) {
        path = route.query.next;
      } else {
        path = route.path;
      }
      return path;
    });
    const clickCreatedButton = () => {
      if (store.state.user) {
        showModal.value = true;
      } else {
        alert("로그인이 필요합니다.");
        router.push({ name: "login", query: { next: lastPath.value } });
      }
    };
    let tab = reactive({
      tabvalue: "storyaccount",
    });
    const storyaccount = () => {
      tab = "storyaccount";
    };
    const stroycharacter = () => {
      tab = "storycharacter";
    };
    const storyscript = () => {
      tab = "storyscript";
    };
    return {
      showModal,
      storyinfo,
      storydetaildata,
      tab,
      StoryDummyData,
      storyaccount,
      stroycharacter,
      storyscript,
      clickCreatedButton,
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
  display: flex;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
  background-color: $efefe-gray;
  padding: 0 10%;
}
.main_container {
  box-sizing: border-box;
  padding: 50px 0px;
  display: flex;
}
.title-body {
  display: flex;
  justify-content: center;
  align-items: center;
}
.left_content {
  display: flex;
  flex-direction: column;
  width: 80rem;
}
.video_content {
  width: 538px;
  height: 308px;
}
.text_content {
  width: 400px;
  height: 250px;
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  margin-left: 100px;
  .category {
    font-size: 14px;
    font-weight: bold;
    display: flex;
    align-items: center;
    margin-bottom: 10px;
  }
  .title {
    font-size: 30px;
    font-weight: 500;
    margin-bottom: 10px;
  }
  .main_text {
    font-size: 14px;
    font-weight: 400;
    line-height: 150%;
    margin-bottom: 10px;
    min-height: 130px;
  }
  .util {
    display: flex;
    align-items: center;
  }
  .comment {
    margin-left: 10px;
  }
}
.content_icon {
  margin-right: 10px;
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
  right: 676px;
  bottom: 0;
}
.story__storycharacter {
  right: 546px;
}
.story__storyscript {
  right: 416px;
}

.createcard {
  display: flex;
  flex-direction: column;
  align-items: center;
  box-sizing: border-box;
  width: 300px;
  height: 280px;
  padding: 0px 10px;
  border-radius: 20px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  .card_title {
    font-size: 20px;
    font-weight: 500;
    box-sizing: border-box;
    margin: 35px 0px 20px 0px;
  }
  .card_setting {
    display: flex;
    font-size: 14px;
    font-weight: 400;
    margin-bottom: 5px;
    border-bottom: 1px solid rgb(187, 187, 187);
    .setting_text {
      display: flex;
      flex-direction: column;
      align-items: center;
      margin: 15px;
    }
    .setting_count {
      box-sizing: border-box;
      font-size: 22px;
      font-weight: bold;
      margin: 10px 10px 5px 10px;
    }
  }
  .card_util {
    display: flex;
    align-items: center;
    font-size: 14px;
    font-weight: 400;
    .util_favor,
    .util_share {
      display: flex;
      align-items: center;
      margin: 15px 20px;
    }
  }
}
.modal-button {
  font-size: 14px;
  margin: 5px 0px 20px 0px;
  width: 250px;
  height: 40px;
  border: none;
  color: white;
  border-radius: 6px;
  background-color: $bana-pink;
  cursor: pointer;
}
</style>
