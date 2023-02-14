<template>
  <div class="chatting">
    <div id="chattingContainer" class="chatting-container">
      <div v-for="item in state.recvList" :key="item">
        <ChattingTabLine v-if="item.nickname !== state.nickname" :line="item" />
        <ChattingTabMyLine v-if="item.nickname === state.nickname" :line="item" />
      </div>
    </div>
    <div class="chatting-input">
      <ChattingAdd />
      <label for="chattingInput" class="chatting-input__input">
        <input id="chattingInput" v-model="state.message" type="text" @keyup.enter="sendMessage" />
      </label>
      <ChattingSend />
    </div>
  </div>
</template>
<script>
import { reactive, watch } from "vue";
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
    const socket = new SockJS(serverURL);
    const stompClient = Stomp.over(socket);
    const chattingContainer = document.getElementById("chattingContainer");

    const state = reactive({
      studioId: props.studioInfo.studio_id,
      userId: props.user.user_id,
      userPhotoUrl: props.user.profile_url,
      nickname: props.user.nickname,
      message: "",
      recvList: [],
    });

    const connect = () => {
      stompClient.connect({}, () => {
        // 소켓 연결 성공
        stompClient.connected = true;
        stompClient.attender = {
          userId: state.userId,
          userPhotoUrl: state.userPhotoUrl,
        };
        // 서버의 메시지 전송 endpoint를 구독합니다.
        stompClient.subscribe(`/sub/api/v1/studio/chat/${state.studioId}`, async (res) => {
          // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
          const temp = JSON.parse(res.body);
          state.recvList.push(temp);
          chattingContainer.scrollTop = chattingContainer.scrollHeight;
        });
      });
    };

    function send() {
      return new Promise((resolve) => {
        if (stompClient && stompClient.connected) {
          stompClient.send(
            `/pub/api/v1/studio/chat/${state.studioId}/${state.userId}/${state.nickname}`,
            {},
            state.message
          );
        }
        resolve();
      });
    }

    function sendMessage() {
      if (state.nickname !== "" && state.message !== "") {
        send().then(() => {
          state.message = "";
          console.log("받은메시지 리스트", state.recvList);
        });
      }
    }

    watch(
      () => props.studioInfo,
      () => {
        state.studioId = props.studioInfo.studio_id;
        console.log(props.studioInfo);
        connect();
      }
    );

    watch(
      () => props.user,
      () => {
        state.userId = props.user.user_id;
        state.userPhotoUrl = props.user.profile_url;
        state.nickname = props.user.nickname;
        connect();
      }
    );

    return {
      sendMessage,
      state,
    };
  },
};
</script>
<style lang="scss" scoped>
.chatting {
  height: 100%;
}

.chatting-container {
  background-color: white;
  height: 92%;
  overflow: scroll;
}

.chatting-input {
  background-color: #e9e9e9;
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