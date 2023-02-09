<template>
  <div class="story-script">
    <StoryScriptScene v-for="scene in scenes" :key="scene.id" :scene="scene"></StoryScriptScene>
  </div>
</template>
<script>
import StoryScriptScene from "@/components/story/StoryScriptScene.vue";

export default {
  name: "StoryScript",
  components: {
    StoryScriptScene,
  },
  props: { scenes: Array },
  setup() {},
};
</script>
<style lang="scss"></style>
