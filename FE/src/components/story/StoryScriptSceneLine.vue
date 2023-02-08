<template>
  <div class="story-script__line">
    <div v-if="isFirstLine" class="story-script__actor-name">{{ role.name }}</div>
    <div class="story-script__text-section">
      <a class="story-script__time-stamp">{{ line.timestamp }}</a>
      <span class="story-script__text">{{ line.script }}</span>
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
    role: Object,
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

<style lang="scss">
.story-script__line {
  width: 90%;
  border-radius: 10px;
  background-color: $efefe-gray;
  padding: 15px 10px;
  margin-bottom: 10px;
}

.story-script__actor-name {
  font-weight: 500;
  margin-bottom: 10px;
}
.story-script__text-section {
  display: flex;
}
.story-script__time-stamp {
  color: $newjeans-blue;
  cursor: pointer;
  line-height: 120%;
}

.story-script__text {
  margin-left: 10px;
  display: block;
  word-break: break-all;
  line-height: 120%;
}
</style>
