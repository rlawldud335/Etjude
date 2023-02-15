<template>
  <div class="studio-film-tab__film">
    <div v-if="streamManager">
      <ov-video :stream-manager="streamManager" />
      <div class="nickname-section">
        {{ clientData }}
      </div>
    </div>
  </div>
</template>

<script>
import OvVideo from "@/components/studio/OvVideo.vue";

export default {
  name: "UserVideo",

  components: {
    OvVideo,
  },

  props: {
    streamManager: Object,
  },

  computed: {
    clientData() {
      const { clientData } = this.getConnectionData();
      return clientData;
    },
  },

  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    },
  },
};
</script>

<style scoped lang="scss">
.studio-film-tab__film {
  margin: 6px 5%;
  padding: 18px;
  border-radius: 15px;
  background-color: $efefe-gray;
  display: inline-block;
  flex-direction: row;
  position: relative;

  ov-video {
    position: absolute;
    top: 0;
  }
}

.video-section {
  position: absolute;
}

.nickname-section {
  position: absolute;
  bottom: 0;
  color: black;
  border-radius: 8px;
  flex-direction: column;
  align-items: center;
}
</style>
