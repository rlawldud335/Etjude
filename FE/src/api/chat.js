import Stomp from "stompjs";
import SockJS from "sockjs-client";

export function sendMessage() {
  if (this.nickname !== "" && this.message !== "") {
    this.send();
    this.message = "";
  }
}
export function sendRecordingStatus(e) {
  if (this.sceneNumber !== "" && this.userId !== "") {
    if (this.stompClient && this.stompClient.connected && e === `REC`) {
      this.stompClient.send(
        `/pub/api/v1/studio/chat/status/${this.studioId}/${this.sceneNumber}/${this.userId}/${e}`,
        {}
      );
    } else if (this.stompClient && this.stompClient.connected && e === `DONE`) {
      this.stompClient.send(
        `/pub/api/v1/studio/chat/status/${this.studioId}/${this.sceneNumber}/${this.userId}/${e}`,
        {}
      );
    }
  }
}

export function sendChatParticipants(e) {
  if (this.sceneNumber !== "" && this.userId !== "") {
    if (this.stompClient && this.stompClient.connected && e === `REC`) {
      this.stompClient.send(
        `/pub/api/v1/studio/chat/status/${this.studioId}/${this.sceneNumber}/${this.userId}/${e}`,
        {}
      );
    } else if (this.stompClient && this.stompClient.connected && e === `DONE`) {
      this.stompClient.send(
        `/pub/api/v1/studio/chat/status/${this.studioId}/${this.sceneNumber}/${this.userId}/${e}`,
        {}
      );
    }
  }
}

export function send() {
  if (this.stompClient && this.stompClient.connected) {
    this.stompClient.send(
      `/pub/api/v1/studio/chat/${this.studioId}/${this.userId}/${this.nickname}`,
      {},
      JSON.stringify(this.message)
    );
  }
}

export function connect() {
  this.serverURL = `https://etjude.r-e.kr/api/v1/studio/chat`;
  const socket = new SockJS(this.serverURL);
  this.stompClient = Stomp.over(socket);
  this.stompClient.connect({}, () => {
    // 소켓 연결 성공
    this.connected = true;
    this.attender = {
      userId: this.userId,
      userPhotoUrl: this.userPhotoUrl,
    };

    // 서버의 메시지 전송 endpoint를 구독합니다.
    this.stompClient.subscribe(`/sub/api/v1/studio/chat/${this.studioId}`, (res) => {
      // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
      this.recvList.push(JSON.parse(res.body));
    });

    // 서버의 녹화 현황 endpoint
    this.stompClient.subscribe(`/status/api/v1/studio/chat/${this.studioId}`, (res) => {
      const parsedData = JSON.parse(res.body);
      if (parsedData.status === "REC") {
        for (let i = 0; i < this.recordingList.length; i += 1) {
          if (this.recordingList[i].sceneNumber === parsedData.sceneNumber) {
            this.recordingList.splice(i, 1);
          }
        }
        this.recordingList.push(parsedData);
      } else if (parsedData.status === "DONE") {
        for (let i = 0; i < this.recordingList.length; i += 1) {
          if (this.recordingList[i].sceneNumber === parsedData.sceneNumber) {
            this.recordingList.splice(i, 1);
          }
        }
      }
    });
  });
}
