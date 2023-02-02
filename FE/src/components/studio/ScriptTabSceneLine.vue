<template>
  <div class="studio-line">
    <div v-if="isFirstLine" class="studio-line__actor-name">{{ role.name }}</div>
    <div class="studio-line__line">
      <a class="studio-line__time-stamp">{{ line.timestamp }}</a>
      <span class="studio-line__text">{{ line.script }}</span>
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
.studio-line {
  width: 90%;
  border-radius: 10px;
  background-color: $efefe-gray;
  padding: 15px 10px;
  margin-bottom: 10px;
}

.studio-line__actor-name {
  font-weight: 500;
  margin-bottom: 10px;
}
.studio-line__line {
  display: flex;
}
.studio-line__time-stamp {
  color: $newjeans-blue;
  cursor: pointer;
}

.studio-line__text {
  margin-left: 10px;
  display: block;
  word-break: break-all;
}
</style>
