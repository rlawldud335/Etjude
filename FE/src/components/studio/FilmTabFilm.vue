<template>
  <div class="studio-film-tab__film">
    <div class="studio__film-thumbnail">
      <video :src="film.film_video_url" controls class="studio__film-player">
        <track kind="captions" />
      </video>
    </div>
    <div class="studio__film-section">
      <div class="studio__film-info">
        <span class="studio__film-id">필름{{ film.film_id }}.</span>
        <span class="studio__film-created">{{ film.film_created_date }} 생성</span>
        <span class="studio__film-deleted">{{ film.film_end_date }} 삭제</span>
      </div>
      <div class="studio__film-button">
        <button @click="downloadFlim">다운로드</button>
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
  setup(props) {

    const downloadFlim = () => {
      if (props.film.film_video_url) {
        const link = document.createElement("a");
        document.body.appendChild(link);
        link.href = props.film.film_video_url;
        link.download = "video.webm";
        link.click();
        document.body.removeChild(link);
      }
    };

    return { downloadFlim };
  },
};
</script>

<style scoped lang="scss">
.studio-film-tab__film {
  margin: 6px 5%;
  padding: 18px;
  border-radius: 15px;
  background-color: $efefe-gray;
  display: flex;
  flex-direction: row;
}

.studio__film-thumbnail {
  cursor: pointer;
}

.studio__film-player {
  width: 150px;
  height: 100px;
}

.studio__film-section {
  margin-left: 15px;
  width: calc(100% - 150px - 15px);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.studio__film-info {
  display: flex;
  flex-direction: column;

  .studio__film-id {
    font-size: 16px;
    font-weight: 500;
  }

  >span {
    font-size: 14px;
    line-height: 150%;
  }
}

.studio__film-button {
  display: flex;
  justify-content: flex-end;

  button {
    font-size: 10px;
    border: none;
    color: white;
    background-color: $bana-pink;
    padding: 6px 10px;
    border-radius: 15px;
    margin-left: 5px;
    cursor: pointer;
    white-space: nowrap;
  }
}
</style>
