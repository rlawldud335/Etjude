<template lang="">
  <div class="studiogate">
    <router-link to="/">
      <div class="studiotitle">
        추천 작품
        <div class="icon">icon</div>
        >
      </div>
    </router-link>
    <PieceCardItem></PieceCardItem>
  </div>
</template>
<script>
import PieceCardItem from "./PieceCardItem.vue";

export default {
  name: "PieceCard",
  components: {
    PieceCardItem,
  },
};
</script>
<style lang="scss">
.studiotitle {
  display: flex;
  padding: 5px;
  padding-left: 4%;
  text-align: left;
  font-size: 24px;
  font-weight: bold;
}
.icon {
  margin-left: 5px;
  margin-right: 5px;
}
</style>
