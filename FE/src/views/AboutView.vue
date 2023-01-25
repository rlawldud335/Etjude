<template>
  <div class="about">
    <h1>This is an about page</h1>
    <storycard></storycard>
  </div>
</template>
<script>
import storycard from "@/components/StoryCard.vue";

export default {
  name: "AboutView",
  components: {
    storycard,
  },
};
</script>
