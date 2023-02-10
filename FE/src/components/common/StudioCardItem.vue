<template lang="">
  <div class="carousel__item">
    <router-link to="/studio">
      <img class="itemimg" :src="require(`@/assets/images/NoImage.png`)" alt="" />
      <div class="itemtext">
        <div class="rightitem">
          <div class="itemtextteamname">{{ carditem.studioTitle }}</div>
          <div class="itemtexttitle">{{ carditem.storyTitle }}</div>
          <div class="itemtextdate">{{ carditem.studioCreatedDate }} ~ {{ carditem.studioEndDate}}</div>
        </div>
        <div class="leftitem">
          <div class="itemtextdday">D - {{ diffDay }}</div>
        </div>
      </div>
    </router-link>
  </div>
</template>
<script>
export default {
  name: "StudioCardItem",
  props: {
    carditem: {},
  },
  computed: {
    diffDay() {
      const masTime = new Date(this.carditem.studioEndDate);
      const todayTime = new Date();

      const diff = masTime - todayTime;

      const diffDay = String(Math.floor(diff / (1000 * 60 * 60 * 24)));

      return diffDay;
    }
  }
};
</script>
<style lang="scss" scoped>
a {
  text-decoration: none;
  color: black;
}

.carousel__item {
  background-color: #fff;
  //   border-radius: 0.5rem;
  margin: 10px;
  transition: all 0.3s ease-in-out;

  &:hover {
    transform: scale(1.05);
    box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.25);
  }
}

.itemimg {
  border-radius: 0.5rem 0.5rem 0 0;
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
  padding-top: 16px;
  padding-bottom: 16px;
  padding-left: 26px;
  padding-right: 26px;
  justify-content: space-between;
}

.itemtextteamname {
  margin-bottom: 3px;
  text-align: left;
  font-size: 16px;
  font-weight: bold;
}

.itemtexttitle,
.itemtextdate {
  margin: 2px;
  padding-left: 5px;
  text-align: left;
  font-size: 12px;
}

.itemtextdday {
  display: inline;
  padding-left: 3px;
  text-align: left;
  font-size: 16px;
}
</style>
