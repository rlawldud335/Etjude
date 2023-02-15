<template>
  <div id="main-container" class="container">
    <div id="join" v-if="!session">
      <div id="join-dialog" class="jumbotron vertical-center">
        <div class="form-group">
          <div>
            <div>NICKNAME</div>
            <label for="participants"
              ><input v-model="myUserName" class="form-control" type="text" required
            /></label>
          </div>
          <div>
            <div>STUDIO_ID</div>
            <label for="session"
              ><input v-model="mySessionId" class="form-control" type="text" required
            /></label>
          </div>
          <div class="studio-tab__button-section">
            <button @click="joinSession()">화상 회의 참여하기</button>
          </div>
          <hr />
        </div>
      </div>
    </div>

    <div id="session" v-if="session">
      <div id="session-header">
        <div class="studio-tab__button-section">
          <button @click="leaveSession">화상 회의 떠나기</button>
        </div>
      </div>
      <div v-show="false">
        <div id="main-video" class="col-md-6">
          <UserVideo :stream-manager="mainStreamManager" />
        </div>
        <div id="video-container" class="col-md-6">
          <UserVideo :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)" />
        </div>
      </div>
      <div id="video-contaniner2" class="col-md-6">
        <hr />
        <div v-for="(sub, idx) in subscribers" :key="idx">
          <div class="studio_video-section">
            <UserVideo
              :key="sub.stream.connection.connectionId"
              :stream-manager="sub"
              @click="updateMainVideoStreamManager(sub)"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { OpenVidu } from "openvidu-browser";
import axios from "axios";
import UserVideo from "@/components/studio/UserVideo.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";

const APPLICATION_SERVER_URL = "https://etjude.r-e.kr/openvidu/";

export default {
  name: "WebRtcTab",

  components: {
    UserVideo,
  },

  data() {
    return {
      // OpenVidu objects
      OV: undefined,
      session: undefined,
      sessionId: null,
      streamId: null,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      // Join form
      mySessionId: "SessionA",
      myUserName: `Participant${Math.floor(Math.random() * 100)}`,
    };
  },

  methods: {
    connection() {
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.streamId = subscriber.stream.streamId;
        this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });
      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            this.OV.getUserMedia({
              audioSource: false,
              videoSource: undefined,
              resolution: "320x240",
              frameRate: 30,
            }).then((mediaStream) => {
              const videoTrack = mediaStream.getVideoTracks()[0];

              const newPublisher = this.OV.initPublisher(this.myUserName, {
                audioSource: undefined,
                videoSource: videoTrack,
                publishAudio: true,
                publishVideo: true,
                resolution: "320x240",
                frameRate: 30,
                insertMode: "APPEND",
                mirror: false,
              });
              newPublisher.once("accessAllowed", () => {
                this.session.publish(newPublisher);
                this.publisher = newPublisher;
              });
            });
          })
          .catch((error) => {
            console.log("There was an error connecting to the session:", error.code, error.message);
          });
      });
    },
    joinSession() {
      // --- 1) Get an OpenVidu object ---
      this.OV = new OpenVidu();
      // --- 2) Init a session ---
      this.session = this.OV.initSession();
      this.connection();
    },

    leaveSession() {
      // --- 7) Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      // Empty all properties...
      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      // Remove beforeunload listener
      window.removeEventListener("beforeunload", this.leaveSession);
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    async getToken(mySessionId) {
      await this.createSession(mySessionId);
      // eslint-disable-next-line no-return-await
      return await this.createToken(mySessionId);
    },

    // eslint-disable-next-line consistent-return
    async createSession(sessionId) {
      await axios
        .post(
          `${APPLICATION_SERVER_URL}api/sessions`,
          { customSessionId: sessionId },
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: "Basic T1BFTlZJRFVBUFA6TVlfU0VDUkVU",
            },
          }
        )
        .then((response) => {
          this.sessionId = response.data.id;
        })
        // eslint-disable-next-line consistent-return
        .catch((response) => {
          const err = { ...response };
          if (err?.response?.status === 409) {
            this.sessionId = sessionId;
          }
        });
    },

    async createToken(sessionId) {
      const data = {};
      console.log("2", sessionId);
      const openviduInstance = await axios.post(
        `${APPLICATION_SERVER_URL}api/sessions/${sessionId}/connection`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: "Basic T1BFTlZJRFVBUFA6TVlfU0VDUkVU",
          },
          body: {
            type: "WEBRTC",
            data: "My Server Data",
            record: true,
            role: "PUBLISHER",
            kurentoOptions: {
              videoMaxRecvBandwidth: 1000,
              videoMinRecvBandwidth: 300,
              videoMaxSendBandwidth: 1000,
              videoMinSendBandwidth: 300,
              allowedFilters: ["GStreamerFilter", "ZBarFilter"],
            },
          },
        },
        data
      );
      return openviduInstance.data.token; // The token
    },
  },
};
</script>

<style scoped lang="scss">
.studio-tab__button-section {
  button {
    font-size: 14px;
    font-weight: 500;
    border: none;
    padding: 8px 30px;
    white-space: nowrap;
    background-color: $bana-pink;
    color: white;
    border-radius: 8px;
  }

  margin-top: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.studio_video-section {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
</style>
