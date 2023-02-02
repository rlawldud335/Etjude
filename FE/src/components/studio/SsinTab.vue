<template>
  <div><SsinTabScene v-for="scene in scenes" :key="scene.id" :scene="scene"></SsinTabScene></div>
</template>
<script>
import SsinTabScene from "@/components/studio/SsinTabScene.vue";

export default {
  name: "SsinTab",
  components: { SsinTabScene },
  props: { scenes: Array },
};
</script>
<style lang=""></style>
