<template>
  <div class="film__card" @click="showDtailModal = true" @keydown="none">
    <div class="film__card-image">
      <img src="@/assets/images/더글로리.jpg" alt="thumbnail" />
    </div>
    <div class="film__card-info">
      <div class="film__card-author-profile">
        <div class="film__profile-frame">
          <img class="film__profile-image" src="@/assets/images/profile.jpg" alt="" />
        </div>
      </div>
      <div class="film__card-body">
        <span class="film__card-title">송혜교다</span>
        <span class="film__card-author">중기</span>
        <span class="film__card-history">조회수 14K ▪ 14일전</span>
      </div>
    </div>
  </div>
  <FilmSharingDtail :changemodal="showDtailModal"></FilmSharingDtail>
</template>
<script>
import FilmSharingDtail from "@/components/share/FilmSharingDtail.vue";

export default {
  name: "FilmListItem",
  components: {
    FilmSharingDtail,
  },
  data: () => ({
    showDtailModal: false,
  }),
  methods: {},
};
</script>

<style scoped lang="scss">
.film__card {
  width: 300px;
  min-width: 300px;
  margin: 20px;
  img {
    width: 100%;
  }
}

.film__card-info {
  display: flex;
  align-items: center;
}

.film__card-author-profile {
  margin: 10px;
  img {
    width: 50px;
    height: auto;
    width: 100%;
    height: 100%;
  }
}

.film__profile-frame {
  width: 50px;
  height: 50px;
  border-radius: 70%;
  overflow: hidden;
  display: flex;
}

.film__card-body {
  display: flex;
  flex-direction: column;
  span {
    margin: 1px 0px;
  }
}
.film__card-title {
  font-size: 18px;
  font-weight: 600;
  justify-content: space-between;
}
.film__card-author {
  font-size: 16px;
}
.film__card-history {
  font-size: 14px;
}
</style>
