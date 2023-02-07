<template>
  <div class="studio">
    <div class="studio__header">
      <StudioNav :studio="studioDummyData" />
    </div>
    <div class="studio__content">
      <div class="studio__video" :class="{ openTab: !state.isOpenTab }">
        <div class="studio__video__video">
          <VideoArea />
        </div>
        <div class="studio__video__script">
          <ScriptArea isOpenTab="state.isOpenTab" />
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
          <ScriptTab :scenes="studioDummyData.scene" v-show="state.selectTab === 0" />
          <SsinTab v-show="state.selectTab === 1" />
          <FilmTab :films="studioDummyData.film" v-show="state.selectTab === 2" />
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
import studioDummyData from "@/dummy/studioDummyData.json";
import { reactive, ref } from "vue";

export default {
  components: {
    Scripts,
    Ssin,
    Film,
    QuitButton,
    ScriptTab,
    SsinTab,
    FilmTab,
    StudioNav,
    ScriptArea,
    VideoArea,
  },
  setup() {
    const state = reactive({
      isOpenTab: false,
      selectTab: 0,
    });
    const tabs = ref([{ tabName: "전체 스크립트" }, { tabName: "씬 녹화" }, { tabName: "필름" }]);
    const clickTab = (idx) => {
      console.log = idx;
      if (state.selectTab === idx) state.isOpenTab = !state.isOpenTab;
      else if (state.isOpenTab) state.isOpenTab = true;
      state.selectTab = idx;
    };
    const closeTab = () => {
      state.isOpenTab = false;
    };
    return {
      studioDummyData,
      state,
      tabs,
      clickTab,
      closeTab,
    };
  },
  // data() {
  //   return {
  //     isOpenTab: false,
  //     selectTab: 0,
  //     tabs: [{ tabName: "전체 스크립트" }, { tabName: "씬 녹화" }, { tabName: "필름" }],
  //   };
  // },
  // methods: {
  //   clickTab(idx) {
  //     if (this.selectTab === idx) this.isOpenTab = !this.isOpenTab;
  //     else if (!this.isOpenTab) this.isOpenTab = true;
  //     this.selectTab = idx;
  //   },
  //   closeTab() {
  //     this.isOpenTab = false;
  //   },
  // },
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
  justify-content: end;
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
}

.close-btn {
  cursor: pointer;
  background-color: white;
  border: none;
}
</style>
