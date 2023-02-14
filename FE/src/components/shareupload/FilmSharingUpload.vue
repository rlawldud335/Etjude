<template lang="">
  <vue-final-modal :value="showModal" classes="modal-contain" content-class="modal-cont">
    <div class="title_container">
      <h1>필름 공유 글 업로드</h1>
      <QuitButton></QuitButton>
    </div>
    <div class="content_container">
      <UserUpload></UserUpload>
    </div>
  </vue-final-modal>
</template>
<script>
import QuitButton from "@/assets/icons/Quit Button.svg";
import UserUpload from "./UserUpload.vue";

export default {
  name: "FilmShareUpload",
  components: {
    QuitButton,
    UserUpload,
  },
  props: {
    showModal: Boolean,
  },
  setup() {
    return {};
  },
};
</script>
<style lang="scss" scoped>
::v-deep .modal-contain {
  display: flex;
  justify-content: center;
  align-items: center;
}
::v-deep .modal-cont {
  display: flex;
  flex-direction: column;
  height: 654px;
  width: 1186px;
}
.title_container {
  display: flex;
  box-sizing: border-box;
  justify-content: space-between;
  border-top-left-radius: 20px;
  border-top-right-radius: 20px;
  align-items: center;
  width: 1186px;
  height: 100px;
  padding: 0px 60px;
  background-color: white;

  h1 {
    font-size: 24px;
    font-weight: bold;
    color: rgb(79, 79, 79);
  }
}
.content_container {
  height: 554px;
  border-bottom-left-radius: 20px;
  border-bottom-right-radius: 20px;
  padding: 0px 40px;
  display: flex;
  background-color: white;
}
</style>

<style scoped>
.dark-mode div::v-deep .modal-cont {
  border-color: #2d3748;
  background-color: #1a202c;
}
</style>
