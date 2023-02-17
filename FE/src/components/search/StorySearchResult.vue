<template>
  <div class="search-result__story">
    <StoryCardItem v-for="story in stories" :key="story.storyId" :carditem="story"></StoryCardItem>
  </div>
</template>

<script>
import StoryCardItem from "@/components/common/StoryCardItem.vue";

export default {
  name: "StorySearchResult",
  components: { StoryCardItem },
  props: { stories: Array },
  setup() {
    return {};
  },
};
</script>
<style lang="scss" scoped>
.search-result__story {
  display: flex;
  flex-wrap: wrap;
}
</style>
