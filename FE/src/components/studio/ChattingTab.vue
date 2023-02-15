<!-- eslint-disable eqeqeq -->
<template>
  <div class="chatting">
    <div ref="messages" id="chattingContainer" class="chatting-container">
      <div v-for="item in recvListData" :key="item">
        <ChattingTabLine
          v-if="item.studioId == studioId && item.userId !== user.userId"
          :line="item"
        />
        <ChattingTabMyLine
          v-if="item.studioId == studioId && item.userId === user.userId"
          :line="item"
        />
      </div>
    </div>
    <div class="chatting-input">
      <ChattingAdd />
      <label for="chattingInput" class="chatting-input__input">
        <input id="chattingInput" v-model="state.message" type="text" @keyup.enter="sendMessage" />
      </label>
      <ChattingSend @click="test" />
    </div>
  </div>
</template>
<script>
import { reactive, watch, computed, ref, nextTick } from "vue";
import ChattingSend from "@/assets/icons/ChattingSend.svg";
import ChattingAdd from "@/assets/icons/ChattingAdd.svg";
import ChattingTabLine from "@/components/studio/ChattingTabLine.vue";
import ChattingTabMyLine from "@/components/studio/ChattingTabMyLine.vue";
import { useStore } from "vuex";

export default {
  name: "ChattingTab",
  components: { ChattingSend, ChattingAdd, ChattingTabLine, ChattingTabMyLine },
  props: { studioId: Number, flimState: Object, stompClient: Object, recvList: Array },
  setup(props) {
    const store = useStore();
    const user = computed(() => store.state.user);
    const state = reactive({
      message: "",
    });
    const recvListData = computed(() => props.recvList);
    const messages = ref("");

    watch(
      () => props.flimState.madeCnt,
      () => {
        console.log("커넥션이 있나?", props.stompClient.connected);
        if (props.stompClient && props.stompClient.connected) {
          console.log("채팅탭의 필름 상태", props.flimState);
          props.stompClient.send(
            `/pub/api/v1/studio/chat/${props.studioId}/${user.value.userId}/${user.value.myPageSimpleResponse.userNickName}`,
            {},
            `3924873`
          );
        }
      }
    );

    const test = () => {
      if (props.stompClient && props.stompClient.connected) {
        console.log("채팅탭의 필름 상태", props.flimState);
        props.stompClient.send(
          `/pub/api/v1/studio/chat/${props.studioId}/${user.value.userId}/${user.value.myPageSimpleResponse.userNickName}`,
          {},
          `3924873`
        );
      }
    };

    function send() {
      return new Promise((resolve) => {
        if (props.stompClient && props.stompClient.connected) {
          console.log(
            "sendmessage",
            `/pub/api/v1/studio/chat/${props.studioId}/${user.value.userId}/${user.value.myPageSimpleResponse.userNickName}`,
            state.message
          );
          props.stompClient.send(
            `/pub/api/v1/studio/chat/${props.studioId}/${user.value.userId}/${user.value.myPageSimpleResponse.userNickName}`,
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
      () => state.recvList,
      () => {
        nextTick(() => {
          messages.value.scrollTo({ top: messages.value.scrollHeight, behavior: "smooth" });
        });
      },
      { deep: true }
    );
    return {
      sendMessage,
      state,
      user,
      recvListData,
      messages,
      test,
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
