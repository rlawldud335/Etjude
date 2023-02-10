<template>
  <div>
    유저 아이디:
    <label for="유저아이디"><input v-model="userId" type="text" @keyup.enter="connect"/></label>
    <br />
    유저 닉네임:
    <label for="유저닉네임"><input v-model="nickname" type="text" /></label>
    <br />
    내용:
    <label for="내용"><input v-model="message" type="text" @keyup.enter="sendMessage" /></label>
    <hr />
    <div class="container">
      <div>
        채팅 내역
        <div v-for="(item, idx) in recvList" :key="idx">
          <div v-if="item.studioId === studioId">
            <div v-if="item.userId === this.userId" style="color: blue;"> 
              <div>({{ item.chatTime }}) {{ item.nickname }}: {{ item.content }}</div>
            </div>
            <div v-else>
              <div>({{ item.chatTime }}) {{ item.nickname }}: {{ item.content }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { sendMessage, send, connect } from "@/api/chat";

export default {
  name: "ChattingTab",
  data() {
    return {
      studioId: "1",
      sceneNumber: "",
      userId: "1",
      nickname: "1",
      message: "",
      attender: {},
      recvList: [],
    };
  },
  created() {
    this.connect();
  },
  methods: {
    sendMessage,
    send,
    connect,
  },
};
</script>
<style lang=""></style>
