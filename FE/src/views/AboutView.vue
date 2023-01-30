<template>
  <div class="about">
    <h1>This is an about page</h1>
    <studiocard></studiocard>
    <storycard></storycard>
    <piececard></piececard>
  </div>
</template>
<script>
import storycard from "@/components/main/StoryCard.vue";
import studiocard from "@/components/main/StudioCard.vue";
import piececard from "@/components/main/PieceCard.vue";

export default {
  name: "AboutView",
  components: {
    storycard,
    studiocard,
    piececard,
  },
};
</script>
<style>
a {
  text-decoration: none;
  color: black;
}
</style>
