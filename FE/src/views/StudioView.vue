<template>
  <div class="studio">
    <div class="studio__header">
      <StudioNav :studioInfo="studioData.studioInfo" />
    </div>
    <div class="studio__content">
      <div class="studio__video" :class="{ openTab: !state.isOpenTab }">
        <div class="studio__video__video">
          <VideoArea @save-recording-data="saveRecordingData" @change-video-state="changeVideoState"
            @change-current-slide="changeCurrentSlide" :videoState="videoState" :scriptState="scriptState"
            :studioInfo="studioData.studioInfo" :allLines="studioData.allLines" />
        </div>
        <div class="studio__video__script">
          <ScriptArea @change-current-time="changeCurrentTime" @change-current-slide="changeCurrentSlide"
            :scriptState="scriptState" :allLines="studioData.allLines" />
        </div>
      </div>
      <div class="studio__openTab" v-show="state.isOpenTab">
        <div class="openTab__header">
          <div class="openTab__header-text">
            <span class="openTab__header-tabName">{{ tabs[state.selectTab].tabName }}</span>
            <span class="openTab__header-notice" v-show="state.selectTab === 2">필름 만들기 권한은 팀장에게만 권한이 있습니다.</span>
          </div>
          <button class="close-btn" @click="closeTab()">
            <QuitButton />
          </button>
        </div>
        <div class="openTab__body">
          <ScriptTab v-show="state.selectTab === 0" @change-current-time="changeCurrentTime"
            @change-video-state="changeVideoState" :videoState="videoState" :storyScript="studioData.storyScript" />
          <SsinTab v-show="state.selectTab === 1" @change-video-state="changeVideoState" :videoState="videoState"
            :records="studioData.records" :storyScript="studioData.storyScript" />
          <FilmTab v-show="state.selectTab === 2" :films="studioData.films" />
          <ChatTab v-show="state.selectTab === 3" :studioInfo="studioData.studioInfo" />
          <WebRtcTab v-show="state.selectTab === 4" />
        </div>
      </div>
      <div class="studio__tab">
        <button class="studio__tab__btn" @click="clickTab(0)"
          :class="{ 'studio__tab__btn--select': state.isOpenTab && state.selectTab == '0' }">
          <Scripts />
        </button>
        <button class="studio__tab__btn" @click="clickTab(1)"
          :class="{ 'studio__tab__btn--select': state.isOpenTab && state.selectTab == '1' }">
          <Ssin />
        </button>

        <button class="studio__tab__btn" @click="clickTab(2)"
          :class="{ 'studio__tab__btn--select': state.isOpenTab && state.selectTab == '2' }">
          <Film />
        </button>
        <button class="studio__tab__btn" @click="clickTab(3)"
          :class="{ 'studio__tab__btn--select': state.isOpenTab && state.selectTab == '3' }">
          <Chatting />
        </button>

        <button class="studio__tab__btn" @click="clickTab(4)"
          :class="{ 'studio__tab__btn--select': state.isOpenTab && state.selectTab == '4' }">
          <RTCIcon />
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import Scripts from "@/assets/icons/scripts.svg";
import Ssin from "@/assets/icons/ssin.svg";
import Film from "@/assets/icons/film.svg";
import ScriptTab from "@/components/studio/ScriptTab.vue";
import SsinTab from "@/components/studio/SsinTab.vue";
import FilmTab from "@/components/studio/FilmTab.vue";
import QuitButton from "@/assets/icons/QuitButton.svg";
import StudioNav from "@/components/studio/StudioNav.vue";
import ScriptArea from "@/components/studio/ScriptArea.vue";
import VideoArea from "@/components/studio/VideoArea.vue";
import { reactive, ref, onBeforeMount } from "vue";
import { useRoute } from "vue-router";
import { getStudioInfo, getStudioStoryScript, getSceneRecordList, getFlimList } from "@/api/studio";
import Chatting from "@/assets/icons/Chatting.svg";
import RTCIcon from "@/assets/icons/RTCIcon.svg";

import ChatTab from "@/components/studio/ChattingTab.vue";
import WebRtcTab from "@/components/studio/WebRtcTab.vue";

export default {
  components: {
    Scripts,
    Ssin,
    Film,
    QuitButton,
    Chatting,
    RTCIcon,
    ScriptTab,
    SsinTab,
    FilmTab,
    StudioNav,
    ScriptArea,
    VideoArea,
    ChatTab,
    WebRtcTab,
  },
  setup() {
    const route = useRoute();

    const state = reactive({
      isOpenTab: true,
      selectTab: 0,
    });

    const tabs = ref([
      { tabName: "전체 스크립트" },
      { tabName: "씬 녹화" },
      { tabName: "필름" },
      { tabName: "채팅" },
      { tabName: "화상" },
    ]);

    const clickTab = (idx) => {
      if (state.selectTab === idx) {
        state.isOpenTab = !state.isOpenTab;
      } else if (state.isOpenTab && idx !== state.selectTab) {
        state.selectTab = idx;
      } else {
        state.isOpenTab = !state.isOpenTab;
        state.selectTab = idx;
      }
    };

    const closeTab = () => {
      state.isOpenTab = false;
    };

    const studioData = reactive({
      studioInfo: {},
      storyScript: [],
      allLines: [],
      records: [],
      films: [],
    });

    const makeAllLies = (storyScript) => {
      const filterTime = (time) => {
        const sptTime = time.split(":");
        return (
          parseInt(sptTime[0], 10) * 60 + parseInt(sptTime[1], 10) + parseInt(sptTime[2], 10) / 10
        );
      };
      let index = 0;
      const newAllLines = [];
      storyScript.forEach((scene) => {
        scene.lines.forEach((line) => {
          newAllLines.push({
            roleName: scene.roleName,
            scriptNumber: index,
            lineTimeStamp: filterTime(line.lineTimeStamp),
            line: line.line,
          });
          index += 1;
        });
      });
      return newAllLines;
    };

    onBeforeMount(() => {
      if (route.params?.studioId) {
        const studioId = route.params?.studioId;
        console.log("라우트 파람", route.params.studioId);
        getStudioInfo(
          studioId,
          ({ data }) => {
            console.log("스튜디오인포", data);
            studioData.studioInfo = data;

            getSceneRecordList(
              studioId,
              data.story_id,
              ({ data: data2 }) => {
                console.log("레코드리스트", data2);
                studioData.records = data2;
              },
              (error) => {
                console.log(error);
              }
            );
          },
          (error) => {
            console.log(error);
          }
        );
        getStudioStoryScript(
          studioId,
          ({ data }) => {
            console.log("스크립트", data);
            studioData.storyScript = data;
            studioData.allLines = makeAllLies(data);
          },
          (error) => {
            console.log(error);
          }
        );
        getFlimList(
          studioId,
          ({ data }) => {
            console.log("필름리스트", data);
            studioData.films = data;
          },
          (error) => {
            console.log(error);
          }
        );
      }
    });

    const videoState = reactive({
      sceneIdx: 1,
      isRecording: false,
    });

    const scriptState = reactive({
      currentTime: 0,
      currentSlide: 0,
    });

    const changeVideoState = (sceneIdx, isRecording) => {
      videoState.sceneIdx = sceneIdx;
      videoState.isRecording = isRecording;
    };

    const saveRecordingData = (sceneIdx, recordedMediaURL, recordedUser) => {
      for (let i = 0; i < studioData.records.length; i += 1) {
        console.log(studioData.records[i].sceneId, sceneIdx);
        if (studioData.records[i].sceneId === sceneIdx) {
          studioData.records[i].recordVideoUrl = recordedMediaURL;
          studioData.records[i].nickname = recordedUser.nickname;
          studioData.records[i].profile_url = recordedUser.profile_url;
          break;
        }
      }
    };

    const changeCurrentTime = (time) => {
      scriptState.currentTime = time;
    };

    const changeCurrentSlide = (idx) => {
      scriptState.currentSlide = idx;
    };

    return {
      state,
      tabs,
      videoState,
      saveRecordingData,
      changeVideoState,
      clickTab,
      closeTab,
      studioData,
      scriptState,
      changeCurrentTime,
      changeCurrentSlide,
    };
  },
};
</script>

<style lang="scss" scoped>
.studio {
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
}

.studio__header {
  width: 100%;
  height: 7%;
}

.studio__content {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  width: 100%;
  height: 93%;
}

.studio__tab {
  height: 100%;
  width: 4%;
  min-width: 65px;
  background-color: $aha-gray;
  display: flex;
  flex-direction: column;
}

.studio__video {
  width: 70%;
  height: 100%;
}

.openTab {
  width: 96%;
}

.studio__video__script {
  width: 100%;
  height: 22%;
}

.studio__video__video {
  width: 100%;
  height: 78%;
}

.studio__openTab {
  width: 26%;
  min-width: 350px;
  height: 100%;
  background-color: white;
}

.openTab__header-tabName {
  white-space: nowrap;
}

.openTab__header-notice {
  font-size: 12px;
  color: $bana-pink;
  margin-left: 10px;
}

.studio__tab__btn {
  border: none;
  height: 65px;
  background-color: $aha-gray;
  display: flex;
  justify-content: center;
  align-items: center;
}

.studio__tab__btn:hover {
  background-color: #e7e7e7;
}

.studio__tab__btn--select {
  background-color: #e7e7e7;
}

.openTab__header {
  width: 100%;
  height: 7%;
  font-size: 18px;
  font-weight: 500;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0px 20px;
  box-sizing: border-box;
}

.openTab__body {
  height: 93%;
  width: 100%;
  overflow-y: scroll;
}

.openTab__body {
  -ms-overflow-style: none;
}

.openTab__body::-webkit-scrollbar {
  display: none;
}

.close-btn {
  cursor: pointer;
  background-color: white;
  border: none;
}
</style>
