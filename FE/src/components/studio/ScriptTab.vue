<template>
  <div class="script-tab">
    <ScriptTabScene></ScriptTabScene>
    <ScriptTabScene></ScriptTabScene>
  </div>
</template>

<script>
import ScriptTabScene from "@/components/studio/ScriptTabScene.vue";

export default {
  name: "ScriptTab",
  components: {
    ScriptTabScene,
  },
};
</script>

<style lang="scss">
.script-tab {
  width: 100%;
  height: 100%;
}
</style>
