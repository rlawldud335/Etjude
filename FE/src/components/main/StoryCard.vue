<template lang="">
  <div class="studiogate">
    <div class="studiotitle">
      <div class="">추천 스토리</div>
      <YumIcon/>
      <div class="">></div>
    </div>
    <StoryCardItem></StoryCardItem>
  </div>
</template>
<script>
import YumIcon from "@/assets/icons/Yum.svg";
import StoryCardItem from "./StoryCardItem.vue";

export default {
  name: "StoryCard",
  components: {
    StoryCardItem,YumIcon
  },
};
</script>
<style lang="scss">
.studiogate {
  // display: flex;
  // flex-direction: column;
}
.studiotitle {
  display: inline-flex;
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
