<template lang="">
  <div>
    <div>
      <h1>추천 스토리</h1>
    </div>
    <!-- <StoryCardItem></StoryCardItem> -->
    <StoryCardItemTest></StoryCardItemTest>
  </div>
</template>
<script>
// import StoryCardItem from "./StoryCardItem.vue";
import StoryCardItemTest from "./StoryCardItemTest.vue";

export default {
  name: "StoryCard",
  components: {
    // StoryCardItem,
    StoryCardItemTest,
  },
};
</script>
<style lang=""></style>
