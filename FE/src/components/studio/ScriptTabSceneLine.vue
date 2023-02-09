<template>
  <div class="studio-line">
    <div v-if="isFirstLine" class="studio-line__actor-name">{{ roleName }}</div>
    <div class="studio-line__line">
      <a class="studio-line__time-stamp">{{ TimeFilter }}</a>
      <span class="studio-line__text">{{ line.line }}</span>
    </div>
  </div>
</template>

<script>
import { ref, onMounted, computed } from "vue";

export default {
  name: "ScriptTabSceneLine",
  components: {},
  props: {
    line: Object,
    roleName: String,
    lineIdx: Number
  },
  setup(props) {
    const isFirstLine = ref(false);
    const lineFirst = onMounted(() => {
      if (props.lineIdx === 0) {
        isFirstLine.value = true;
      }
    });

    const TimeFilter = computed(() => props.line.lineTimeStamp.substr(0, 5))

    return {
      lineFirst,
      isFirstLine,
      TimeFilter
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
  line-height: 120%;
}

.studio-line__text {
  margin-left: 10px;
  display: block;
  word-break: break-all;
  line-height: 120%;
}
</style>
