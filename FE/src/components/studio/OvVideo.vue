<template>
  <video autoplay><track kind="captions" /></video>
</template>

<script>
export default {
  name: "OvVideo",

  props: {
    streamManager: Object,
  },

  mounted() {
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>

<style scoped lang="scss">
video {
  padding-bottom: 10px;
}
</style>
