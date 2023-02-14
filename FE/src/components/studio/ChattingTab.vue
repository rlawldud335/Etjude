<template>
  <div class="chatting">
    <div class="chatting-container">
      <div v-for="(item, idx) in recvList" :key="idx">
        <ChattingTabLine v-if="item.studioId === studioId && item.userId !== userId" :line="item" />
        <ChattingTabMyLine v-if="item.studioId === studioId && item.userId === userId" :line="item" />
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
import { ref } from "vue";
import ChattingSend from "@/assets/icons/ChattingSend.svg";
import ChattingAdd from "@/assets/icons/ChattingAdd.svg";
import ChattingTabLine from "@/components/studio/ChattingTabLine.vue";
import ChattingTabMyLine from "@/components/studio/ChattingTabMyLine.vue";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

export default {
  name: "ChattingTab",
  components: { ChattingSend, ChattingAdd, ChattingTabLine, ChattingTabMyLine },
  props: { studioInfo: Object, user: Object },
  setup(props) {
    const serverURL = `https://etjude.r-e.kr/api/v1/studio/chat`;
    const studioId = ref(props.studioInfo.studio_id);
    const userId = ref(props.user.user_id);
    const userPhotoUrl = ref(props.user.profile_url);
    const nickname = ref(props.user.nickname);
    const message = ref('');
    const recvList = ref([]);

    const socket = new SockJS(serverURL);
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
      // 소켓 연결 성공
      stompClient.connected = true;
      stompClient.attender = {
        userId: userId.value,
        userPhotoUrl: userPhotoUrl.value,
      };

      // 서버의 메시지 전송 endpoint를 구독합니다.
      stompClient.subscribe(`/sub/api/v1/studio/chat/${studioId.value}`, (res) => {
        // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
        recvList.value.push(JSON.parse(res.body));
      });
    });

    function send() {
      if (stompClient && stompClient.connected) {
        stompClient.send(
          `/pub/api/v1/studio/chat/${studioId.value}/${userId.value}/${nickname.value}`,
          {},
          JSON.stringify(message.value)
        );
      }
    }


    function sendMessage() {
      if (nickname.value !== "" && message.value !== "") {
        send();
        message.value = "";
      }
    }

    return {
      message,
      recvList,
      sendMessage,
      studioId,
      userId,
      nickname
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