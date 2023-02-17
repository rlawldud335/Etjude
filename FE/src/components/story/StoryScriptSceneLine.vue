<template>
  <div class="story-script__line">
    <div v-if="isFirstLine" class="story-script__actor-name">{{ role }}</div>
    <div class="story-script__text-section">
      <span class="story-script__text">{{ line.line }}</span>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";

export default {
  name: "ScriptTabSceneLine",
  components: {},
  props: {
    line: Object,
    role: String,
  },
  setup(props) {
    const isFirstLine = ref(false);
    const lineFirst = onMounted(() => {
      if (props.line.id === 1) {
        isFirstLine.value = true;
      }
    });
    return {
      lineFirst,
      isFirstLine,
    };
  },
};
</script>

<style scoped lang="scss">
.story-script__line {
  width: 90%;
  border-radius: 10px;
  background-color: $efefe-gray;
  padding: 15px 10px;
  margin-bottom: 10px;
  padding-left: 20px;
}

.story-script__actor-name {
  font-weight: 700;
  margin-bottom: 10px;
}
.story-script__text-section {
  display: flex;
}
.story-script__text {
  display: block;
  word-break: break-all;
  line-height: 120%;
}
</style>
