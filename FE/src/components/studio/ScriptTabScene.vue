<template>
  <div class="studio__scene">
    <!-- {{ scene.line }} -->
    <div class="studio__scene-actor-image">
      <div class="actor__profile-frame">
        <img class="actor__profile-image" :src="scene.role.photo_url" alt="" />
      </div>
    </div>
    <div class="studio__tab-line-section">
      <ScriptTabSceneLine
        v-for="line in scene.line"
        :key="line.id"
        :line="line"
        :role="scene.role"
      ></ScriptTabSceneLine>
    </div>
  </div>
</template>

<script>
import ScriptTabSceneLine from "@/components/studio/ScriptTabSceneLine.vue";

export default {
  name: "ScriptTabScene",
  components: { ScriptTabSceneLine },
  props: {
    scene: Object,
  },
  setup() {
    return {};
  },
};
</script>

<style lang="scss">
.studio__scene {
  display: flex;
  flex-direction: row;
  margin-bottom: 20px;
}
.studio__scene-actor-image {
  width: 15%;
  display: flex;
  justify-content: center;
}
.studio__tab-line-section {
  width: 85%;
}
.actor__profile-frame {
  margin-top: 12px;
  width: 40px;
  height: 40px;
  border-radius: 70%;
  overflow: hidden;
  justify-content: center;
  img {
    max-width: 40px;
    height: auto;
  }
}
</style>
