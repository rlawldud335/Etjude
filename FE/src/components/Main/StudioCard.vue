<template lang="">
  <div class="studiogate">
    <router-link to="/main">
      <div class="studiotitle">
        내 스튜디오
        <div class="icon">icon</div>
        >
      </div>
    </router-link>
    <StudioCardItem></StudioCardItem>
  </div>
</template>
<script>
import StudioCardItem from "./StudioCardItem.vue";

export default {
  name: "StudioCard",
  components: {
    StudioCardItem,
  },
};
</script>
<style lang="scss">
.studiotitle {
  display: flex;
  padding: 5px;
  padding-left: 4%;
  text-align: left;
  font-size: 24px;
  font-weight: bold;
}
.icon {
  margin-left: 5px;
  margin-right: 5px;
}
</style>
