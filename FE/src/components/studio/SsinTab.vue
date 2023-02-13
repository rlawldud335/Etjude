<!-- eslint-disable no-param-reassign -->
<!-- eslint-disable no-restricted-syntax -->
<template>
  <div class="studio__ssin-tab-list">
    <SsinTabScene v-for="scene in ssinData.ssin" :key="scene.index" :scene="scene" :videoState="videoState"
      @start-recording="startRecording">
    </SsinTabScene>
  </div>
</template>

<script>
import SsinTabScene from "@/components/studio/SsinTabScene.vue";
import { watch, reactive, onBeforeMount } from "vue";

export default {
  name: "SsinTab",
  components: { SsinTabScene },
  props: { videoState: Object, records: Array, storyScript: Array },
  emits: ["change-video-state"],
  setup(props, { emit }) {
    const startRecording = (sceneIdx) => {
      emit("change-video-state", sceneIdx, true);
    };
    const ssinData = reactive({ ssin: [] });

    onBeforeMount(() => {
      for (let i = 0; i < props.storyScript.length; i += 1) {
        ssinData.ssin.push({ ...props.records[i], ...props.storyScript[i] });
      }
    })

    watch(props.records, (cur) => {
      ssinData.ssin = [];
      for (let i = 0; i < props.storyScript.length; i += 1) {
        ssinData.ssin.push({ ...cur[i], ...props.storyScript[i] });
      }
    })

    return {
      startRecording,
      ssinData,
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
