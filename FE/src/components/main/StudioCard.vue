<template lang="">
  <div class="studiogate2">
    <div class="studiotitle">
      <router-link to="/main"> 내 스튜디오 </router-link>
      <HeheheIcon />
      >
    </div>
    <StudioCardList></StudioCardList>
  </div>
</template>
<script>
import HeheheIcon from "@/assets/icons/Hehehe.svg";
import StudioCardList from "./StudioCardList.vue";

export default {
  name: "StudioCard",
  components: {
    StudioCardList,
    HeheheIcon,
  },
};
</script>
<style lang="scss" scoped>
.studiotitle {
  display: flex;
  padding: 5px;
  padding-left: 4%;
  text-align: left;
  align-items: center;
  font-size: 24px;
  font-weight: bold;
}
.icon {
  margin-left: 5px;
  margin-right: 5px;
}
</style>
