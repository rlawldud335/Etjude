<template>
  <div class="film__card" @click="showDtailModal = true" @keydown="none">
    <div class="film__card-image">
      <img :src="film.articleThumbnailUrl" alt="thumbnail" />
    </div>
    <div class="film__card-info">
      <div class="film__card-author-profile">
        <div class="film__profile-frame">
          <img class="film__profile-image" :src="film.writerPhotoUrl" alt="" />
        </div>
      </div>
      <div class="film__card-body">
        <span class="film__card-title">{{ film.articleTitle }}</span>
        <span class="film__card-author">{{ film.writerName }}</span>
        <span class="film__card-history">좋아요 {{ likeCount }}회 ▪ {{ diffCreated }}</span>
      </div>
    </div>
  </div>
  <FilmSharingDtail
    @close="showDtailModal = false"
    v-model="showDtailModal"
    :filmDetailData="film.articleId"
  ></FilmSharingDtail>
</template>
<script>
import { computed, ref } from "vue";
import FilmSharingDtail from "@/components/share/FilmSharingDtail.vue";

export default {
  name: "FilmListItem",
  props: {
    film: Object,
    createdDate: String,
    likeCount: Number,
  },
  components: {
    FilmSharingDtail,
  },
  setup(props) {
    const showDtailModal = ref(false);

    const diffCreated = computed(() => {
      const createdDate = new Date(props.createdDate);
      const now = new Date();
      const timeDiff = now.getTime() - createdDate.getTime();

      let diffText;
      if (timeDiff / (1000 * 60) < 1) {
        diffText = `${parseInt(timeDiff / 1000, 10)}초 전`;
      } else if (timeDiff / (1000 * 60 * 60) < 1) {
        diffText = `${parseInt(timeDiff / (1000 * 60), 10)}분 전`;
      } else if (timeDiff / (1000 * 60 * 60 * 24) < 1) {
        diffText = `${parseInt(timeDiff / (1000 * 60 * 60), 10)}시간 전`;
      } else if (timeDiff / (1000 * 60 * 60 * 24 * 30) < 1) {
        diffText = `${parseInt(timeDiff / (1000 * 60 * 60 * 24), 10)}일 전`;
      } else {
        diffText = `${createdDate.getFullYear()}/${
          createdDate.getMonth() + 1
        }/${createdDate.getDate()}`;
      }
      return diffText;
    });
    return {
      diffCreated,
      showDtailModal,
    };
  },
};
</script>

<style scoped lang="scss">
.film__card {
  width: 240px;
  min-width: 240px;
  margin: 15px 7px;
  cursor: pointer;
}
.film__card-image {
  width: 100%;
  height: 120px;
  border-radius: 6px;
  overflow: hidden;
  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
  img:hover {
    transform: scale(1.1);
    transition: 0.3s;
  }
}
.film__card-info {
  display: flex;
  align-items: center;
  margin-top: 3px;
}

.film__card-author-profile {
  margin: 8px;
}

.film__profile-frame {
  width: 40px;
  height: 40px;
  border-radius: 70%;
  overflow: hidden;
  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
}

.film__card-body {
  display: flex;
  flex-direction: column;
  margin-left: 5px;
  span {
    margin: 2px 0px;
  }
}
.film__card-title {
  font-size: 14px;
  font-weight: 600;
  justify-content: space-between;
  max-width: calc(100% - 15px);
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.film__card-author {
  font-weight: 600;
  font-size: 10px;
}
.film__card-history {
  font-size: 10px;
}
</style>
