<template lang="">
  <div class="carousel__item">
    <router-link to="/main">
      <img class="itemimg" :src="require(`@/assets/images/헤어질결심.jpg`)" alt="" />
      <div class="itemtext">
        <div class="rightitem">
          <div class="itemtextteamname">{{ carditem.team }}</div>
          <div class="itemtexttitle">{{ carditem.title }}</div>
        </div>
      </div>
    </router-link>
  </div>
</template>
<script>
export default {
  name: "PieceCardItem",
  props: {
    carditem: {},
  },
};
</script>
<style lang="scss" scoped>
a {
  text-decoration: none;
  color: black;
}
.carousel__item {
  background-color: #fff;
  margin: 10px;
  transition: all 0.3s ease-in-out;
  &:hover {
    transform: scale(1.05);
    box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.25);
  }
}
.itemimg {
  height: 187px;
  width: 165px;
  object-fit: cover;
  /* // height: max(10rem, 25vh); */
  max-height: max(10rem, 30vh);
  aspect-ratio: 4/3;
  mix-blend-mode: var(--card-blend-mode);
  /* // filter: grayscale(100); */
}
.itemtext {
  display: flex;
  padding-top: 8px;
  padding-bottom: 8px;
  padding-left: 12px;
  padding-right: 12px;
}
.itemtextteamname {
  margin-bottom: 5px;
  text-align: left;
  font-size: 14px;
  font-weight: bold;
}
.itemtexttitle {
  text-align: left;
  font-size: 12px;
}
</style>
