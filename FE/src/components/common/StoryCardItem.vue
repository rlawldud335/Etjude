<template lang="">
  <div class="carousel__item">
    <router-link
      :to="{
        name: 'story',
        params: {
          story_id: storyId,
        },
      }"
    >
      <img class="itemimg" :src="require(`@/assets/images/NoImage.png`)" alt="" />
      <div class="itemtext">
        <div class="itemtexttitle">{{ storyitem.storyTitle }}</div>
        <div class="itemtextcategory">{{ storyitem.categoryName }}</div>
        <div class="itemtextfavor">좋아요 {{ storyitem.likeCount }}</div>
      </div>
    </router-link>
  </div>
</template>
<script>
import { ref } from "vue";

export default {
  name: "StoryCardItem",
  props: {
    carditem: {},
  },
  setup(props) {
    const storyitem = ref(props.carditem);
    console.log("스토리아이템 확인");
    console.log(storyitem.value.storyId);
    const storyId = ref(storyitem.value.storyId);
    console.log(typeof storyitem.value.storyId);
    return {
      storyitem,
      storyId,
    };
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
  /* border-radius: 0.5rem; */
  margin: 10px;
  transition: all 0.3s ease-in-out;

  &:hover {
    transform: scale(1.05);
    box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.25);
  }
}

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
  margin: 3.5px 0px;
  text-align: left;
  font-size: 12px;
}
</style>
