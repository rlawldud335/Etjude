<template>
  <div class="story-character">
    <StoryCharacterRole
      v-for="role in storyDetailRules"
      :key="role.roleId"
      :role="role"
    ></StoryCharacterRole>
  </div>
</template>
<script>
import StoryCharacterRole from "@/components/story/StoryCharacterRole.vue";
import { ref } from "vue";
import { getDetailRules } from "@/api/story";

export default {
  name: "StoryCharacter",
  components: {
    StoryCharacterRole,
  },
  props: {
    roles: Number,
  },
  setup(props) {
    const storyDetailRules = ref({});
    getDetailRules(
      props.roles,
      ({ data }) => {
        storyDetailRules.value = data;
      },
      (error) => {
        console.log("역할 상세 오류:", error);
      }
    );
    return {
      storyDetailRules,
    };
  },
};
</script>
<style lang="scss">
.story-character {
  display: flex;
  flex-direction: column;
}
</style>
