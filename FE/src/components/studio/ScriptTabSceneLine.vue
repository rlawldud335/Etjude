<template>
  <div class="studio-line">
    <span v-if="isFirstLine" class="studio-line__actor">{{ actor }}</span>
    <div class="studio-line__line">
      <span class="studio-line__time-stamp">1:00</span>
      <span class="studio-line__text">안녕</span>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";

export default {
  name: "ScriptTabSceneLine",
  components: {},
  props: {
    index: Number,
    actor: String,
  },
  setup(props) {
    const isFirstLine = ref(false);
    const lineFirst = onMounted(() => {
      if (props.index === 1) {
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
  width: 100%;
  border-radius: 10px;
  background-color: $efefe-gray;
  padding: 15px 10px;
  margin-bottom: 10px;
}

.studio-line__text {
  padding: 10px;
}
</style>
