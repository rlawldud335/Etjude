<template>
  <div class="script-tab">
    <ScriptTabScene
      v-for="scene in storyScript"
      :key="scene.sceneNumber"
      :scene="scene"
      @change-script-time="changeScriptTime"
    ></ScriptTabScene>
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
  },
  emits: ["change-current-time"],
  setup(props, { emit }) {
    const changeScriptTime = (time) => {
      emit("change-current-time", time);
    };

    return {
      changeScriptTime,
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
