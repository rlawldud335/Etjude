<template lang="">
  <div>
    <div id="modal_test">
      <button @click="handleClick">Open ConfirmationModal</button>
      <p>{{ result }}</p>
      <ConfirmationModal ref="modal" :content="modalContent" />
    </div>
  </div>
</template>
<script>
import { ref } from "vue";
import ConfirmationModal from "./ConfirmationModal.vue";

export default {
  components: {
    ConfirmationModal,
  },
  setup() {
    const modal = ref(null);
    const modalContent = ref(["확인/취소를 누르고", "배경에 결과가 출력되는 것을", "확인해보세요"]);
    const result = ref("");

    // async-await을 사용하여, Modal로부터 응답을 기다리게 된다.
    const handleClick = async () => {
      const ok = await modal.value.show();
      if (ok) {
        result.value = "확인을 눌렀군요!";
      } else {
        result.value = "취소를 눌렀네요?";
      }
    };
    return {
      modal,
      modalContent,
      result,
      handleClick,
    };
  },
};
</script>
<style scoped>
#modal_test {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  box-sizing: border-box;
}
.modal__title {
  font-size: 1.5rem;
  font-weight: 700;
}
.modal-container {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
