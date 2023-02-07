<template>
  <div class="story-account">
    <label for="emit-data">
      <input type="text" id="emit-data" @keyup.enter="emitEmit" v-model="emitText" />
    </label>
    <button @click="emitEmit">애밋</button>
    에밋된걸 다시 프롭스 받음 : {{ emitGet }}
  </div>
</template>
<script>
import { ref } from "vue";

export default {
  name: "StoryAccount",
  emits: ["emit-emit"],
  props: { emitGet: String },
  setup(props, { emit }) {
    const emitText = ref("");
    const emitEmit = () => {
      emit("emit-emit", emitText.value);
      emitText.value = null;
    };
    return {
      emitText,
      emitEmit,
    };
  },
};
</script>
<style lang="scss"></style>
