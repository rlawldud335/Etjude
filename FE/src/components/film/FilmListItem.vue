<template>
  <div class="film__card">
    <div class="film__card-image">
      <img :src="film.thumbnail_url" alt="thumbnail" />
    </div>
    <div class="film__card-info">
      <div class="film__card-author-profile">
        <div class="film__profile-frame">
          <img class="film__profile-image" :src="film.author.profile_url" alt="" />
        </div>
      </div>
      <div class="film__card-body">
        <span class="film__card-title">{{ film.title }}</span>
        <span class="film__card-author">{{ film.author.name }}</span>
        <span class="film__card-history">조회수 {{ film.view }}회 ▪ {{ diffCreated }}</span>
      </div>
    </div>
  </div>
</template>
<script>
import { computed } from "vue";

export default {
  name: "FilmListItem",
  props: {
    film: Object,
  },
  setup(props) {
    const diffCreated = computed(() => {
      const createdDate = new Date(props.film.created);
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
    };
  },
};
</script>

<style scoped lang="scss">
.film__card {
  width: 240px;
  min-width: 240px;
  margin: 15px 7px;
}
.film__card-image {
  width: 100%;
  height: 120px;
  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
}
.film__card-info {
  display: flex;
  align-items: center;
  margin-top: 3px;
}

.film__card-author-profile {
  margin: 8px;
  img {
    width: 40px;
    height: auto;
    width: 100%;
    height: 100%;
  }
}

.film__profile-frame {
  width: 40px;
  height: 40px;
  border-radius: 70%;
  overflow: hidden;
  display: flex;
}

.film__card-body {
  display: flex;
  flex-direction: column;
  span {
    margin: 2px 0px;
  }
}
.film__card-title {
  font-size: 14px;
  font-weight: 600;
  justify-content: space-between;
  max-width: calc(100% - 30px);
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
