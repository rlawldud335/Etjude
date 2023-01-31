<template lang="">
  <div class="main">
    <div class="main__fullwidth">
      <event-banner />
<<<<<<< HEAD
      <!-- <SharingDtail></SharingDtail> -->
      <FilmSharingDtail></FilmSharingDtail>
=======
>>>>>>> 603a26635b80cd45642a2ad4d299b751b9b0aa7f
    </div>

    <div class="main__1136width">
      <SearchSession />
    </div>

    <div class="main__1136width">
      <StudioCard></StudioCard>
    </div>

    <div class="main__1136width">
      <StoryCard></StoryCard>
    </div>

    <div class="main__1136width">
      <PieceCard></PieceCard>
    </div>

    <div class="main__fullwidth">
      <ShortcutFlim/>
    </div>
  </div>
</template>

<script>
<<<<<<< HEAD
import EventBanner from "@/components/Home/EventBanner.vue";
import StudioCard from "@/components/Main/StudioCard.vue";
import StoryCard from "@/components/Main/StoryCard.vue";
import PieceCard from "@/components/Main/PieceCard.vue";
// import SharingDtail from "@/components/Share/SharingDtail.vue";
import FilmSharingDtail from "@/components/Share/FilmSharingDtail.vue";
=======
import EventBanner from "@/components/main/EventBanner.vue";
import StudioCard from "@/components/main/StudioCard.vue";
import StoryCard from "@/components/main/StoryCard.vue";
import PieceCard from "@/components/main/PieceCard.vue";
import ShortcutFlim from "@/components/main/ShortcutFlim.vue";
import SearchSession from "@/components/main/SearchSession.vue";
>>>>>>> 603a26635b80cd45642a2ad4d299b751b9b0aa7f

export default {
  name: "MainView",
  components: {
    EventBanner,
    StudioCard,
    StoryCard,
    PieceCard,
<<<<<<< HEAD
    // SharingDtail,
    FilmSharingDtail,
=======
    ShortcutFlim,
    SearchSession
>>>>>>> 603a26635b80cd45642a2ad4d299b751b9b0aa7f
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

  width: 1136px;
}

</style>
