<template lang="">
  <router-link to="/story">
    <img class="itemimg" :src="require(`@/assets/images/더글로리.jpg`)" alt="" />
    <div class="itemtext">
      <div class="itemtexttitle">{{ slide.title }}</div>
      <div class="itemtextcategory">{{ slide.category }}</div>
      <div class="itemtextfavor">좋아요 {{ slide.favor }}</div>
    </div>
  </router-link>
</template>
<script>
export default {
  name: "StoryCardItem",
};
</script>
<style lang="scss" scoped>
.itemimg {
  height: 134px;
  width: 257px;
  object-fit: cover;
  /* // height: max(10rem, 25vh); */
  max-height: max(10rem, 30vh);
  aspect-ratio: 4/3;
  mix-blend-mode: var(--card-blend-mode);
  /* // filter: grayscale(100); */
}
.itemtext {
  display: flex;
  flex-direction: column;
  padding-top: 16px;
  padding-bottom: 16px;
  padding-left: 26px;
  padding-right: 26px;
  font-weight: bold;
}
.itemtexttitle {
  margin-bottom: 3px;
  padding: 1px;
  text-align: left;
  font-size: 16px;
}
.itemtextcategory,
.itemtextfavor {
  margin: 3.5px;
  text-align: left;
  font-size: 12px;
}
</style>
