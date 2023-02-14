<template>
  <div class="chatting">
    <div class="chatting-container">
      <div v-for="(item, idx) in recvList" :key="idx">
        <ChattingTabLine v-if="item.studioId === studioId && item.userId !== this.userId" :line="item" />
        <ChattingTabMyLine v-if="item.studioId === studioId && item.userId === this.userId" :line="item" />
      </div>
    </div>
    <div class="chatting-input">
      <ChattingAdd />
      <label for="chattingInput" class="chatting-input__input">
        <input id="chattingInput" v-model="message" type="text" @keyup.enter="sendMessage" />
      </label>
      <ChattingSend />
    </div>
  </div>
</template>
<script>
import { sendMessage, connect } from "@/api/chat";
import { ref, watchEffect } from "vue";
import ChattingSend from "@/assets/icons/ChattingSend.svg";
import ChattingAdd from "@/assets/icons/ChattingAdd.svg";
import ChattingTabLine from "@/components/studio/ChattingTabLine.vue";
import ChattingTabMyLine from "@/components/studio/ChattingTabMyLine.vue";

export default {
  name: "ChattingTab",
  components: { ChattingSend, ChattingAdd, ChattingTabLine, ChattingTabMyLine },
  props: { studioInfo: Object, user: Object },
  setup() {
    const message = ref('');
    const recvList = ref([]);

    const handleNewMessage = () => {
      const { messages } = this.$refs;
      messages.scrollTo({ top: messages.scrollHeight, behavior: "smooth" });
    };

    watchEffect(() => {
      handleNewMessage();
    });

    return {
      message,
      recvList,
      sendMessage,
      connect,
      handleNewMessage
    };
  }
};
</script>
<style lang="scss" scoped>
.chatting {
  height: 100%;
}

.chatting-container {
  background-color: white;
  height: 92%;
}

.chatting-input {
  background-color: #E9E9E9;
  height: 8%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding: 0px 12px;
}

.chatting-input input {
  background-color: white;
  width: 100%;
  height: 90%;
  border: none;
  border-radius: 10px;
  box-sizing: border-box;
  padding: 0px 10px;
}

.chatting-input__input {
  width: 80%;
  height: 60%;
}
</style>