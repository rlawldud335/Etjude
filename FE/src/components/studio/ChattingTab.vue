<template>
  <div class="chatting">
    <div ref="messages" id="chattingContainer" class="chatting-container">
      <div v-for="item in state.recvList" :key="item">
        <ChattingTabLine v-if="item.studioId == state.studioId && item.userId !== user.userId" :line="item" />
        <ChattingTabMyLine v-if="item.studioId == state.studioId && item.userId === user.userId" :line="item" />
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
import { reactive, watch, computed } from "vue";
import ChattingSend from "@/assets/icons/ChattingSend.svg";
import ChattingAdd from "@/assets/icons/ChattingAdd.svg";
import ChattingTabLine from "@/components/studio/ChattingTabLine.vue";
import ChattingTabMyLine from "@/components/studio/ChattingTabMyLine.vue";
import Stomp from "stompjs";
import SockJS from "sockjs-client";
import { useStore } from "vuex";

export default {
  name: "ChattingTab",
  components: { ChattingSend, ChattingAdd, ChattingTabLine, ChattingTabMyLine },
  props: { studioInfo: Object },
  setup(props) {
    const serverURL = `https://etjude.r-e.kr/api/v1/studio/chat`;
    const socket = new SockJS(serverURL);
    const stompClient = Stomp.over(socket);
    const store = useStore();
    const user = computed(() => store.state.user);

    const state = reactive({
      studioId: props.studioInfo.studio_id,
      message: "",
      recvList: [],
    });

    const connect = () => {
      stompClient.connect({}, () => {
        console.log("사용자 정보 ", user);
        // 소켓 연결 성공
        stompClient.connected = true;
        stompClient.attender = {
          userId: user.value.userId,
          userPhotoUrl: user.value.myPageSimpleResponse.userPhotoUrl
        };
        console.log(stompClient.attender);
        // 서버의 메시지 전송 endpoint를 구독합니다.
        stompClient.subscribe(`/sub/api/v1/studio/chat/${state.studioId}`, async (res) => {
          // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
          const temp = JSON.parse(res.body);
          console.log("받은 메시지 ", temp);
          state.recvList.push(temp);
        });
      });
    };

    function send() {
      return new Promise((resolve) => {
        if (stompClient && stompClient.connected) {
          stompClient.send(
            `/pub/api/v1/studio/chat/${state.studioId}/${user.value.userId}/${user.value.myPageSimpleResponse.userNickName}`,
            {},
            state.message
          );
        }
        resolve();
      });
    }

    function sendMessage() {
      if (user.value.myPageSimpleResponse.userNickName !== "" && state.message !== "") {
        send().then(() => {
          state.message = "";
        });
      }
    }

    watch(
      () => props.studioInfo,
      () => {
        state.studioId = props.studioInfo.studio_id;
        connect();
      }
    );

    return {
      sendMessage,
      state,
      user
    };
  },
  watch: {
    recvList: {
      handler() {
        console.log(1);
        this.$nextTick(() => {
          const { messages } = this.$refs;
          messages.scrollTo({ top: messages.scrollHeight, behavior: "smooth" });
        });
      },
      deep: true,
    },
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
  overflow-y: scroll;
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
