<template>
  <div class="studio__film-tab">
    <div class="studio__film-thumbnail">
      <video src="" controls><track kind="captions" /></video>
    </div>
    <div class="studio__film-section">
      <div class="studio__film-info">
        <span class="studio__film-id">필름{{ film.id }}.</span>
        <span class="studio__film-created">{{ film.created }} 생성</span>
        <span class="studio__film-deleted">{{ film.deleted }} 삭제</span>
      </div>
      <div class="studio__film-button">
        <button>다운로드</button>
        <button>필름 삭제</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FilmTabFilm",
  components: {},
  props: {
    film: Object,
  },
  setup() {
    return {};
  },
};
</script>

<style scoped lang="scss">
.studio__film-tab {
  margin: 3px 5%;
  padding: 18px;
  border-radius: 10px;
  background-color: $efefe-gray;
}
.studio__film-thumbnail {
  width: 30px;
}
.studio__film-thumbnail {
  width: 100%;
  height: 100%;
}
</style>
