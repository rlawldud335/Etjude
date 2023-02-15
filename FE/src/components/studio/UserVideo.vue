<template>
  <div v-if="streamManager">
    <div class="video-container">
      <div class="video-section">
        <ov-video :stream-manager="streamManager" />
      </div>
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
.video-container {
  position: relative;
}

.video-section {
  position: absolute;
}

.nickname-section {
  position: absolute;
  bottom: 0;
  background-color: white;
  color: black;
  border-radius: 8px;
  flex-direction: column;
  align-items: center;
}
</style>
