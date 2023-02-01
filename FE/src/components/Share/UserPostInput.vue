<template lang="">
  <div class="borderline"></div>
  <div class="input_container">
    <div class="smile"><smile></smile></div>
    <input type="text" aria-labelledby="firstname" />
    <div class="send"><send></send></div>
  </div>
</template>
<script>
import smile from "@/assets/icons/smile.svg";
import send from "@/assets/icons/send.svg";

export default {
  name: "UserPostInput",
  components: {
    smile,
    send,
  },
};
</script>
<style lang="scss" scoped>
.borderline {
  border-top: 1px solid gray;
}
.input_container {
  display: flex;
  text-align: center;
  align-items: center;
  box-sizing: border-box;
  height: 48px;
  padding-left: 16px;
  padding-right: 16px;
  .smile {
    margin-left: 8px;
    margin-right: 16px;
  }
  .send {
    margin-left: 16px;
    margin-right: 8px;
  }
  input {
    width: 100%;
    height: 80%;
    padding-left: 16px;
    padding-right: 16px;
    box-sizing: border-box;
    border: 0;
    outline: 0;
    border-radius: 15px;
    background-color: rgb(233, 233, 233);
  }
}
</style>
