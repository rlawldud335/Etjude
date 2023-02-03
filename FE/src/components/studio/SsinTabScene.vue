<template>
  <div class="studio-ssin-tab">
    <div class="studio-tab__scene-head">
      <div class="studio-tab__scene-id">#{{ scene.id }}.&nbsp;{{ scene.role.name }}</div>
      <div class="studio-tab__scene-icon">
        <div class="studio-tab__scene-profile-frame">
          <img :src="scene.video.user.profile_url" alt="" />
        </div>
        <playIcon />
        <button class="studio-tab__dropdown-button" @click="toggleOpen">
          <downIcon v-if="!lines.isOpened" />
          <upIcon v-else />
        </button>
      </div>
    </div>
    <div class="studio-tab__scene--opened" v-for="line in scene.line" :key="line.id">
      <div class="studio-tab-scene__line" v-if="lines.isOpened">
        <div class="studio-tab-scene__line-timestamp">
          {{ line.timestamp }}
        </div>
        <div class="studio-tab-scene__line-script">
          {{ line.script }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import playIcon from "@/assets/icons/studioTabPlay.svg";
import downIcon from "@/assets/icons/down.svg";
import upIcon from "@/assets/icons/up.svg";
import { reactive } from "vue";

export default {
  name: "SsinTabScene",
  components: { playIcon, downIcon, upIcon },
  props: { scene: Object },
  setup() {
    const lines = reactive({ isOpened: false });
    const toggleOpen = () => {
      lines.isOpened = !lines.isOpened;
    };
    return {
      lines,
      toggleOpen,
    };
  },
};
</script>

<style lang="scss">
.studio-ssin-tab {
  margin: 3px 5%;
  padding: 16px 18px 18px 18px;
  border-radius: 10px;
  background-color: transparentize($bana-pink, 0.8);
  border: 2px solid $white;
  cursor: pointer;
}

.studio-ssin-tab:hover {
  background-color: $efefe-gray;
  border: 2px solid $bana-pink;
}

.studio-tab__scene-head {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.studio-tab__scene-profile-frame {
  width: 30px;
  height: 30px;
  border-radius: 70%;
  overflow: hidden;
  justify-content: center;
  img {
    max-width: 100%;
    height: auto;
  }
}

.studio-tab__scene-id {
  font-size: 14px;
  font-weight: 500;
}

.studio-tab__dropdown-button {
  background-color: transparent;
  border: none;
  width: 30px;
  height: 30px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.studio-tab__scene-icon {
  display: flex;
  > * {
    margin: 0px 4px;
    cursor: pointer;
  }
}

.studio-tab-scene__line {
  margin-top: 10px;
  display: flex;
  transition: all 1s;
}

.studio-tab-scene__line-timestamp {
  font-size: 14px;
  color: $newjeans-blue;
  cursor: pointer;
}

.studio-tab-scene__line-script {
  word-break: break-all;
  line-height: 120%;
  font-size: 14px;
  margin-left: 10px;
}
</style>
