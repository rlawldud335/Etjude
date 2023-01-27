<template>
  <div class="about">
    <h1>This is an about page</h1>
    <studiocard></studiocard>
    <storycard></storycard>
  </div>
</template>
<script>
import storycard from "@/components/StoryCard.vue";
import studiocard from "@/components/StudioCard.vue";

export default {
  name: "AboutView",
  components: {
    storycard,
    studiocard,
  },
};
</script>
