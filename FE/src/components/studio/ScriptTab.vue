<template>
  <div class="script-tab">
    <ScriptTabScene v-for="scene in studioData.script" :key="scene.sceneNumber" :scene="scene"
      @change-script-time="changeScriptTime"></ScriptTabScene>
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
    studioData: Array
  },
  emits: ['change-script-time'],
  setup(props, { emit }) {
    const changeScriptTime = (time) => {
      console.log(time);
      emit('change-script-time', time);
    }

    return {
      changeScriptTime
    }
  }
};
</script>

<style lang="scss">
.script-tab {
  width: 100%;
  display: flex;
  flex-direction: column;
}
</style>
