<template lang="">
  <vue-final-modal v-model="showModal" classes="modal-contain" content-class="modal-cont">
    <div class="video_container">
      <h1>영상구역 테스트</h1>
    </div>
    <div class="community_container">
      <div class="profile_container"></div>
      <div class="post_container">
        <h2>커뮤니티구역 테스트</h2>
      </div>
    </div>
  </vue-final-modal>
  <button @click="showModal = true">Open modal</button>
</template>
<script>
export default {
  name: "FilmSharingDtail",
  data: () => ({
    showModal: false,
  }),
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
  // flex-direction: column;
  // margin: 0 1rem;
  // padding: 1rem;
  // border: 1px solid #e2e8f0;
  // border-radius: 0.25rem;
  // background: rgb(66, 44, 44);
}
.video_container {
  width: 738px;
  height: 786px;
  background-color: green;
}
.community_container {
  width: 582px;
}
.profile_container {
  height: 82px;
  background-color: aliceblue;
}
.post_container {
  height: 704px;
  background-color: aqua;
}
</style>

<style scoped>
.dark-mode div::v-deep .modal-cont {
  border-color: red;
  background-color: red;
}
</style>
