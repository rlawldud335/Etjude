<!-- eslint-disable no-param-reassign -->
<!-- eslint-disable no-restricted-syntax -->
<template>
  <div class="studio__ssin-tab-list">
    <SsinTabScene
      v-for="scene in ssinData"
      :key="scene.index"
      :scene="scene"
      :videoState="videoState"
      @start-recording="startRecording"
      @click="changeCurrentSlide(scene.lineNumber)"
    >
    </SsinTabScene>
  </div>
</template>

<script>
import SsinTabScene from "@/components/studio/SsinTabScene.vue";
import { computed } from "vue";

export default {
  name: "SsinTab",
  components: { SsinTabScene },
  props: { videoState: Object, records: Array, storyScript: Array },
  emits: ["change-video-state", "change-current-slide"],
  setup(props, { emit }) {
    const startRecording = (sceneNumber, sceneId) => {
      emit("change-video-state", sceneNumber, sceneId, true);
    };

    const changeCurrentSlide = (lineNumber) => {
      emit("change-current-slide", lineNumber);
    };

    const ssinData = computed(() => {
      let lineNumber = 0;
      const temp = [];
      for (let i = 0; i < props.storyScript.length; i += 1) {
        temp.push({ ...props.records[i], ...props.storyScript[i], lineNumber });
        lineNumber += props.storyScript[i].lines.length;
      }
      return temp;
    });

    return {
      startRecording,
      ssinData,
      changeCurrentSlide,
    };
  },
};
</script>
<style lang="scss">
.studio__ssin-tab-list {
  display: flex;
  flex-direction: column;
}
</style>
