<template lang="">
  <div class="studiogate">
    <router-link to="/main">
      <div class="studiotitle">
        추천 작품
        <YayIcon />
        >
      </div>
    </router-link>
    <PieceCardItem></PieceCardItem>
  </div>
</template>
<script>
import YayIcon from "@/assets/icons/Yay.svg";
import PieceCardItem from "./PieceCardItem.vue";

export default {
  name: "PieceCard",
  components: {
    PieceCardItem,
    YayIcon,
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
