<template lang="">
  <div class="studiogate1">
    <div class="studiotitle">
      <div class="">추천 스토리</div>
      <YumIcon />
      <div class="">></div>
    </div>
    <StoryCardList></StoryCardList>
  </div>
</template>
<script>
import YumIcon from "@/assets/icons/Yum.svg";
import StoryCardList from "./StoryCardList.vue";

export default {
  name: "StoryCard",
  components: {
    StoryCardList,
    YumIcon,
  },
};
</script>
<style lang="scss" scoped>
.studiotitle {
  display: inline-flex;
  padding: 5px;
  padding-left: 4%;
  text-align: left;
  align-items: center;
  font-size: 20px;
  font-weight: bold;
}
.icon {
  margin-left: 5px;
  margin-right: 5px;
}
</style>
