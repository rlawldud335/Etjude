<!-- eslint-disable vuejs-accessibility/media-has-caption -->
<template>
  <div class="studio-ssin-tab">
    <div class="studio-tab__scene-head">
      <div class="studio-tab__scene-id">#{{ scene.index }}.&nbsp;{{ scene.role }}</div>
      <div class="studio-tab__scene-icon">
        <div class="studio-tab__scene-profile-frame" v-if="scene.recordedMediaURL">
          <img :src="scene.recordedUser.profile_url" alt="" />
        </div>
        <RecordingIcon v-if="!scene.isRecording" @click="changeHandler" />
        <DisableRecordingIcon v-if="scene.isRecording" />
        <button class="studio-tab__dropdown-button" @click="toggleOpen">
          <downIcon v-if="!lines.isOpened" />
          <upIcon v-else />
        </button>
      </div>
    </div>
    <div class="studio-tab__scene--opened" v-if="lines.isOpened">
      <video :src="scene.recordedMediaURL" controls></video>
    </div>
  </div>
</template>

<script>
import RecordingIcon from "@/assets/icons/recordingIcon.svg";
import DisableRecordingIcon from "@/assets/icons/disableRecodingIcon.svg";
import downIcon from "@/assets/icons/down.svg";
import upIcon from "@/assets/icons/up.svg";
import { reactive } from "vue";

export default {
  name: "SsinTabScene",
  components: { RecordingIcon, downIcon, upIcon, DisableRecordingIcon },
  props: { scene: Object },
  setup(props, { emit }) {
    const lines = reactive({ isOpened: false });
    const toggleOpen = () => {
      lines.isOpened = !lines.isOpened;
    };

    const changeHandler = () => {
      emit('aaaa', 3);
    }

    return {
      lines,
      toggleOpen,
      changeHandler
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

  >* {
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

.studio-tab__scene--opened {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px 0px;
}

.studio-tab__scene--opened video {
  width: 250px;
  height: 150px;
}
</style>
