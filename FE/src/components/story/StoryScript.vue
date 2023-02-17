<template>
  <div class="story-script">
    <StoryScriptScene
      v-for="scene in storyDetaileScript"
      :key="scene.sceneId"
      :scene="scene"
    ></StoryScriptScene>
  </div>
</template>
<script>
import StoryScriptScene from "@/components/story/StoryScriptScene.vue";
import { ref } from "vue";
import { getDetaileScript } from "@/api/story";

export default {
  name: "StoryScript",
  components: {
    StoryScriptScene,
  },
  props: {
    scenes: Number,
  },
  setup(props) {
    const storyDetaileScript = ref({});
    getDetaileScript(
      props.scenes,
      ({ data }) => {
        storyDetaileScript.value = data;
      },
      (error) => {
        console.log("스크립트 상세 오류:", error);
      }
    );
    return {
      storyDetaileScript,
    };
  },
};
</script>
<style scoped lang="scss">
.story-script {
  margin: 50px;
}
</style>
