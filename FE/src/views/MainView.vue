<template lang="">
  <div class="main">
    <div class="main__fullwidth">
      <event-banner />
      <!-- <SharingDtail></SharingDtail> -->
      <FilmSharingDtail></FilmSharingDtail>
    </div>

    <div class="main__1136width">
      <search-session />
    </div>

    <div class="main__1136width">
      <div class="my-studio">
        <StudioCard></StudioCard>
      </div>
    </div>

    <div class="main__1136width">
      <div class="recommended-story">
        <StoryCard></StoryCard>
      </div>
    </div>

    <div class="main__1136width">
      <div class="recommended-piece">
        <PieceCard></PieceCard>
      </div>
    </div>

    <div class="main__fullwidth">
      <div class="shortcut-film">Shortcut Film</div>
    </div>
  </div>
</template>

<script>
import EventBanner from "@/components/Home/EventBanner.vue";
import StudioCard from "@/components/Main/StudioCard.vue";
import StoryCard from "@/components/Main/StoryCard.vue";
import PieceCard from "@/components/Main/PieceCard.vue";
// import SharingDtail from "@/components/Share/SharingDtail.vue";
import FilmSharingDtail from "@/components/Share/FilmSharingDtail.vue";

export default {
  name: "MainView",
  components: {
    EventBanner,
    StudioCard,
    StoryCard,
    PieceCard,
    // SharingDtail,
    FilmSharingDtail,
  },
};
</script>

<style lang="scss">
.main {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.main__fullwidth {
  width: 100%;
}

.main__1136width {
  background-color: pink;
  width: 1136px;
}
.search-session {
  height: 250px;
}

.my-studio {
  height: 300px;
}

.recommended-story {
  height: 300px;
}
</style>
