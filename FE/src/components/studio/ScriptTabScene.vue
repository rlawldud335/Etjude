<template>
  <div class="studio__scene">
    <div class="studio__scene-actor-image">
      <div class="actor__profile-frame">
        <img class="actor__profile-image" src="@/assets/images/profile2.jpg" alt="" />
      </div>
    </div>
    <div class="studio__tab-line-section">
      <ScriptTabSceneLine :index="1" actor="진양철"></ScriptTabSceneLine>
      <ScriptTabSceneLine :index="2" actor="진양철"></ScriptTabSceneLine>
      <ScriptTabSceneLine :index="3" actor="진양철"></ScriptTabSceneLine>
    </div>
  </div>
</template>

<script>
import ScriptTabSceneLine from "@/components/studio/ScriptTabSceneLine.vue";

export default {
  name: "ScriptTabScene",
  components: { ScriptTabSceneLine },
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
  display: flex;
  justify-content: center;
  img {
    width: 100%;
    height: auto;
  }
}
</style>
