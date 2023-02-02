<template>
  <div class="studio-ssin-tab">
    <div class="studio-tab__scene-id">#{{ scene.id }}{{ scene.role.name }}</div>
    <div class="studio-tab__scene-icon">
      <div class="studio-tab__scene-profile-frame">
        <img :src="scene.role.photo_url" alt="" />
      </div>
      <playIcon />
      <downIcon />
    </div>
  </div>
</template>

<script>
import playIcon from "@/assets/icons/studioTabPlay.svg";
import downIcon from "@/assets/icons/down.svg";

export default {
  name: "SsinTabScene",
  components: { playIcon, downIcon },
  props: { scene: Object },
};
</script>

<style lang="scss">
.studio-ssin-tab {
  margin: 15px 5%;
  padding: 18px;
  border-radius: 10px;
  background-color: transparentize($bana-pink, 0.8);
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.studio-tab__scene-profile-frame {
  // margin-top: 12px;
  width: 25px;
  height: 25px;
  border-radius: 70%;
  overflow: hidden;
  justify-content: center;
  img {
    max-width: 40px;
    height: auto;
  }
}

.studio-tab__scene-icon {
  display: flex;
}
</style>
