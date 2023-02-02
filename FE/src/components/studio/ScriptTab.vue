<template>
  <div class="script-tab">
    <ScriptTabScene v-for="scene in scenes" :key="scene.id" :scene="scene"></ScriptTabScene>
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
    scenes: Array,
  },
};
</script>

<style lang="scss">
.script-tab {
  width: 100%;
  height: 100%;
}
</style>
