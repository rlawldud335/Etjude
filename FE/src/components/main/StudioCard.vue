<template lang="">
  <div class="studiogate">
    <div class="studiotitle">
      <span> 내 스튜디오 </span>
      <HeheheIcon />
      >
    </div>
    <StudioCardList></StudioCardList>
    <div class="line"></div>
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
a {
  text-decoration: none;
  color: black;
}
.studiotitle {
  display: flex;
  // padding: 5px;
  // padding-left: 4%;
  text-align: left;
  align-items: center;
  font-size: 20px;
  font-weight: bold;
}
.icon {
  margin-left: 5px;
  margin-right: 5px;
}
.studiogate {
  position: relative;
}
.line {
  width: 1136px;
  border: 1px solid rgb(211, 211, 211);
  margin: 30px 0px;
}
</style>
