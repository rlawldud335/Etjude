<template>
  <div class="film full-page">
    <div class="film__popular-box full-page">
      <div class="film__popular main__1136width">
        <h1 class="film__popular-title">인기필름</h1>
        <PopularFilmList> </PopularFilmList>
      </div>
    </div>
    <div class="film__all main__1136width">
      <h1 class="film__popular-title">전체필름</h1>
      <div class="search__section">
        <div class="search__bar">
          <input class="search__input" placeholder="검색어를 입력해주세요." />
          <Search class="search__glass-icon" />
        </div>
        <div class="search__tag">
          <button class="search__tag-button"># 드라마</button>
          <button class="search__tag-button"># 영화</button>
          <button class="search__tag-button"># 연극</button>
          <button class="search__tag-button"># 기타</button>
        </div>
      </div>
      <div class="upload">
        <button class="upload__button" @click="showModal = true">업로드</button>
      </div>
      <AllFilmList> </AllFilmList>
    </div>
  </div>
  <FilmSharingUpload v-model="showModal"></FilmSharingUpload>
</template>

<script>
import PopularFilmList from "@/components/film/PopularFilmList.vue";
import AllFilmList from "@/components/film/AllFilmList.vue";
import FilmSharingUpload from "@/components/shareupload/FilmSharingUpload.vue";

import Search from "@/assets/icons/search-large.svg";

export default {
  name: "FilmView",
  components: {
    PopularFilmList,
    AllFilmList,
    Search,
    FilmSharingUpload,
  },
  data: () => ({
    showModal: false,
  }),
};
</script>

<style scoped lang="scss">
.film__popular-box {
  width: 100%;
  background-color: transparentize($color: $bana-pink, $amount: 0.9);
  margin: auto auto;
}
.film__popular {
  margin: 60px 0px;
  display: flex;
  flex-direction: column;
}

.film__popular-title {
  min-width: 150px;
  font-weight: 600;
  font-size: 24px;
  margin-bottom: 20px;
}

.film__all {
  margin: 60px 0px;
  display: flex;
  flex-direction: column;
}
.search__section {
  margin: 50px 0px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.search__bar {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.search__input {
  background-color: #ffeff2;
  padding: 15px 40px;
  border-radius: 30px;
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 500px;
  border: $bana-pink solid 1px;
  justify-content: space-between;
  color: #606060;
  margin: 5px 0px;
  font-size: 14px;
}
.search__glass-icon {
  margin-left: 10px;
  cursor: pointer;
}

.search__tag {
  width: 600px;
  margin: 10px 0px;
}

.upload {
  display: flex;
  flex-direction: row-reverse;
  padding-right: 60px;
}

.search__tag-button {
  border: none;
  color: white;
  font-size: 14px;
  background-color: black;
  width: 85px;
  height: 32px;
  border-radius: 20px;
  margin-right: 10px;
  cursor: pointer;
}
.upload__button {
  font-size: 14px;
  border: none;
  color: white;
  background-color: $bana-pink;
  width: 120px;
  height: 32px;
  border-radius: 4px;
  cursor: pointer;
}
</style>
