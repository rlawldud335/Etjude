<template lang="">
  <div class="studiogate">
    <div class="studiotitle">
      <router-link to="/main"> 추천 작품 </router-link>
      <YayIcon />
      >
    </div>
    <PieceCardList></PieceCardList>
  </div>
</template>
<script>
import YayIcon from "@/assets/icons/Yay.svg";
import PieceCardList from "./PieceCardList.vue";

export default {
  name: "PieceCard",
  components: {
    PieceCardList,
    YayIcon,
  },
};
</script>
<style lang="scss" scoped>
a {
  text-decoration: none;
  color: black;
}
.studiotitle {
  display: flex;
  padding: 5px;
  padding-left: 4%;
  text-align: left;
  align-items: center;
  font-size: 20px;
  font-weight: bold;
}
.icon {
  margin-left: 5px;
  margin-right: 5px;
}
</style>
