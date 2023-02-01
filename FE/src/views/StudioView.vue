<!-- eslint-disable vuejs-accessibility/click-events-have-key-events -->
<template>
  <div class="studio">
    <div class="studio__header">
      <StudioNav />
    </div>
    <div class="studio__content">
      <div class="studio__video">
        <div class="studio__video__video"><VideoArea /></div>
        <div class="studio__video__script"><ScriptArea /></div>
      </div>
      <div class="studio__openTab" v-show="isOpenTab">
        <div class="openTab__header">
          <span>{{ this.tabs[this.selectTab].tabName }}</span>
          <div class="close-btn" @click="closeTab()"><QuitButton /></div>
        </div>
        <div class="openTab__body">
          <ScriptTab v-show="this.selectTab == '0'" />
          <SsinTab v-show="this.selectTab == '1'" />
          <FilmTab v-show="this.selectTab == '2'" />
        </div>
      </div>
      <div class="studio__tab">
        <div
          class="studio__tab__btn"
          @click="clickTab(0)"
          :class="{ 'studio__tab__btn--select': isOpenTab && this.selectTab == '0' }"
        >
          <Scripts />
        </div>
        <div
          class="studio__tab__btn"
          @click="clickTab(1)"
          :class="{ 'studio__tab__btn--select': isOpenTab && this.selectTab == '1' }"
        >
          <Ssin />
        </div>
        <div
          class="studio__tab__btn"
          @click="clickTab(2)"
          :class="{ 'studio__tab__btn--select': isOpenTab && this.selectTab == '2' }"
        >
          <Film />
        </div>
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
  data() {
    return {
      isOpenTab: false,
      selectTab: 0,
      tabs: [{ tabName: "전체 스크립트" }, { tabName: "씬 녹화" }, { tabName: "필름" }],
    };
  },
  methods: {
    clickTab(idx) {
      if (this.selectTab === idx) this.isOpenTab = !this.isOpenTab;
      else if (!this.isOpenTab) this.isOpenTab = true;
      this.selectTab = idx;
    },
    closeTab() {
      this.isOpenTab = false;
    },
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
  width: 100%;
  height: 93%;
}

.studio__tab {
  height: 100%;
  background-color: $aha-gray;
  display: flex;
  flex-direction: column;
}
.studio__video {
  width: 100%;
}

.studio__video__script {
  background-color: gray;
  width: 100%;
  height: 25%;
}

.studio__video__video {
  background-color: purple;
  width: 100%;
  height: 75%;
}

.studio__openTab {
  width: 620px;
  height: 100%;
  background-color: white;
}

.studio__tab__btn {
  width: 65px;
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
}
</style>
