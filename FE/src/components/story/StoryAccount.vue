<template>
  <div class="story-account">
    <div class="story__describtion">
      <p class="story_decribtion-title">스토리 설명</p>
      <p class="story_decribtion-text">
        {{ describtion }}
      </p>
    </div>
  </div>
</template>
<script>
export default {
  name: "StoryAccount",
  props: {
    describtion: String,
  },
  setup() {
    return {};
  },
};
</script>
<style lang="scss">
.story__describtion {
  padding: 50px;
}
.story_decribtion-title {
  font-size: 20px;
}
.story_decribtion-text {
  margin: 20px 0px;
  font-size: 16px;
  line-height: 140%;
}
</style>
