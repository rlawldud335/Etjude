<template lang="">
  <div>
    <vue-final-modal v-model="showModal">
      <span class="modal__title">Hello, vue-final-modal</span>
    </vue-final-modal>
    <button @click="showModal = true">Open modal</button>
  </div>
</template>
<script>
import vfmPlugin from "vue-final-modal";

export default {
  data: () => ({
    Object: vfmPlugin,
    showModal: false,
  }),
  methods: {},
};
</script>
<style scoped>
.modal__title {
  font-size: 1.5rem;
  font-weight: 700;
}
</style>
