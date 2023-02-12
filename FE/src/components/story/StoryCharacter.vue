<template>
  <div class="story-character">
    <StoryCharacterRole v-for="role in roles" :key="role.id" :role="role"></StoryCharacterRole>
  </div>
</template>
<script>
import StoryCharacterRole from "@/components/story/StoryCharacterRole.vue";

export default {
  name: "StoryCharacter",
  components: {
    StoryCharacterRole,
  },
  props: {
    roles: Array,
  },
  setup() {
    return {};
  },
};
</script>
<style lang="scss">
.story-character {
  display: flex;
  flex-direction: column;
}
</style>
