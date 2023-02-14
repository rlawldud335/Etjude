<template>
  <div id="main-container" class="container">
    <div id="join" v-if="!session">
      <div id="join-dialog" class="jumbotron vertical-center">
        <h1>Join a video session</h1>
        <div class="form-group">
          <div>
            <div>Participant</div>
            <label for="participants"
              ><input v-model="myUserName" class="form-control" type="text" required
            /></label>
          </div>
          <div>
            <div>Session</div>
            <label for="sessiong"
              ><input v-model="mySessionId" class="form-control" type="text" required
            /></label>
          </div>
          <p class="text-center">
            <button class="btn btn-lg btn-success" @click="joinSession()">Join!</button>
          </p>
          <p class="text-center">
            <button class="btn btn-lg btn-success" @click="connectSession()">Connect!</button>
          </p>
        </div>
      </div>
    </div>

    <div id="session" v-if="session">
      <div id="session-header">
        <h1 id="session-title">{{ mySessionId }}</h1>
        <input
          class="btn btn-large btn-danger"
          type="button"
          id="buttonLeaveSession"
          @click="leaveSession"
          value="Leave session"
        />
      </div>
      <div id="main-video" class="col-md-6">
        <user-video :stream-manager="mainStreamManager" />
      </div>
      <div id="video-container" class="col-md-6">
        <user-video :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)" />
        <user-video
          v-for="sub in subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
          @click="updateMainVideoStreamManager(sub)"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { OpenVidu } from "openvidu-browser";
import axios from "axios";
// eslint-disable-next-line import/extensions
import UserVideo from "@/components/studio/UserVideo";
// import { base64Encode } from "@firebase/util";

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
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      // Join form
      mySessionId: "SessionA",
      myUserName: `Participant${Math.floor(Math.random() * 100)}`,
    };
  },

  methods: {
    connectSession() {
      const token = this.getToken(this.mySessionId);
      this.OV = new OpenVidu();
      this.session = this.OV.initSession(this.mySessionId, token);
      this.session
        .connect()
        .then(() => {
          console.log("Client connected to the session");
        })
        .catch((error) => {
          console.error("Error connecting to the session", error);
        });
    },
    joinSession() {
      // --- 1) Get an OpenVidu object ---
      this.OV = new OpenVidu();
      // --- 2) Init a session ---
      this.session = this.OV.initSession();
      // --- 3) Specify the actions when events take place in the session ---
      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
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

      // --- 4) Connect to the session with a valid user token ---

      // Get a token from the OpenVidu deployment
      this.getToken(this.mySessionId).then((token) => {
        // First param is the token. Second param can be retrieved by every user on event
        // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
            // --- 5) Get your own camera stream with the desired properties ---

            // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
            // element: we will manage it on our own) and with the desired properties
            const publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            // Set the main video in the page to display our webcam and store our Publisher
            this.mainStreamManager = publisher;
            this.publisher = publisher;

            // --- 6) Publish your stream ---

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log("There was an error connecting to the session:", error.code, error.message);
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
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

    /**
     * --------------------------------------------
     * GETTING A TOKEN FROM YOUR APPLICATION SERVER
     * --------------------------------------------
     * The methods below request the creation of a Session and a Token to
     * your application server. This keeps your OpenVidu deployment secure.
     *
     * In this sample code, there is no user control at all. Anybody could
     * access your application server endpoints! In a real production
     * environment, your application server must identify the user to allow
     * access to the endpoints.
     *
     * Visit https://docs.openvidu.io/en/stable/application-server to learn
     * more about the integration of OpenVidu in your application server.
     */
    async getToken(mySessionId) {
      const sessionId = await this.createSession(mySessionId);
      // eslint-disable-next-line no-return-await
      return await this.createToken(sessionId);
    },

    // eslint-disable-next-line consistent-return
    async createSession(sessionId) {
      console.log(sessionId);
      const data = JSON.stringify({ customSessionId: sessionId });
      try {
        const response = await axios.post(
          `${APPLICATION_SERVER_URL}api/sessions`,
          { customSessionId: sessionId },
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: "Basic T1BFTlZJRFVBUFA6TVlfU0VDUkVU",
            },
          },
          data
        );
        console.log(response);
        return response.data.id; // The sessionId
      } catch {
        this.createToken(sessionId);
      }
    },

    async createToken(sessionId) {
      // const data = JSON.stringify({ customSessionId: sessionId });
      const data = {};
      console.log("제발요 ", sessionId);
      const openviduInstance = await axios.post(
        `${APPLICATION_SERVER_URL}api/sessions/${sessionId}/connection`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: "Basic T1BFTlZJRFVBUFA6TVlfU0VDUkVU",
          },
        },
        data
      );
      // console.log(openviduInstance);
      return openviduInstance.data.token; // The token
    },
  },
};
</script>
