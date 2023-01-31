<template>
  <teleport to="body">
    <div class="backdrop" v-if="isVisible">
      <div class="modal-container">
        <!-- slot을 통해 BaseComponent를 확장시킵니다. -->
        <slot></slot>
      </div>
    </div>
  </teleport>
</template>

<script>
import { ref } from "vue";

export default {
  name: "ModalModal",
  setup() {
    const isVisible = ref(false);

    // 부모 컴포넌트에서 접근하기 위한 함수를 선언합니다.
    const open = () => {
      isVisible.value = true;
    };

    const close = () => {
      isVisible.value = false;
    };

    // setup 함수에서 리턴해주어야, 부모 컴포넌트에서 접근이 가능합니다.
    return {
      isVisible,
      open,
      close,
    };
  },
};
</script>

<style lang="scss" scoped>
.backdrop {
  z-index: 999;
  display: flex;
  position: fixed;
  align-items: center;
  justify-content: center;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba($color: #000000, $alpha: 0.5);

  .modal-container {
    display: flex;
    border-radius: 10px;
    background: white;
    // max-width: 320px;
    // width: 100%;
    // padding: 1.5rem;
  }
}
</style>
