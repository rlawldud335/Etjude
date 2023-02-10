<!-- eslint-disable no-param-reassign -->
<!-- eslint-disable no-restricted-syntax -->
<template>
  <div class="studio__ssin-tab-list">
    <SsinTabScene v-for="scene in studioData.script" :key="scene.index" :scene="scene" :videoState="videoState"
      @start-recording="startRecording">
    </SsinTabScene>
  </div>
</template>

<script>
import SsinTabScene from "@/components/studio/SsinTabScene.vue";
// import { reactive } from "vue";

export default {
  name: "SsinTab",
  components: { SsinTabScene },
  props: { videoState: Object, studioData: Object },
  emits: ['change-video-state'],
  setup(props, { emit }) {

    const startRecording = (sceneIdx) => {
      emit('change-video-state', sceneIdx, true);
    }

    return {
      startRecording
    };
  }
};
</script>
<style lang="scss">
.studio__ssin-tab-list {
  display: flex;
  flex-direction: column;
}
</style>
