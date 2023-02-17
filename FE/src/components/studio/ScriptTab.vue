<template>
  <div class="script-tab">
    <ScriptTabScene v-for="scene in storyScript" :key="scene.sceneNumber" :scene="scene" :videoState="videoState"
      @change-script-time="changeScriptTime" @start-recording="startRecording"></ScriptTabScene>
</div>
</template>

<script>


import ScriptTabScene from "@/components/studio/ScriptTabScene.vue";

export default {
  name: "ScriptTab",
  components: {
    ScriptTabScene,
  },
  props: {
    storyScript: Array,
    videoState: Object
  },
  emits: ["change-current-time", "change-video-state"],
  setup(props, { emit }) {
    const changeScriptTime = (time) => {
      emit("change-current-time", time);
    };

    const startRecording = (sceneNumber, sceneId) => {
      emit("change-video-state", sceneNumber, sceneId, true);
    }

    return {
      changeScriptTime,
      startRecording
    };
  },
};
</script>

<style lang="scss">
.script-tab {
  width: 100%;
  display: flex;
  flex-direction: column;
}
</style>
