<template lang="">
  <div class="main">
    <div class="main__fullwidth">
      <event-banner />
    </div>

    <div class="main__1136width">
      <SearchSession />
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
      <ShortcutFlim/>
    </div>
  </div>
</template>

<script>
import EventBanner from "@/components/Home/EventBanner.vue";
import StudioCard from "@/components/Main/StudioCard.vue";
import StoryCard from "@/components/Main/StoryCard.vue";
import PieceCard from "@/components/Main/PieceCard.vue";
import ShortcutFlim from "@/components/Home/ShortcutFlim.vue";
import SearchSession from "@/components/Home/SearchSession.vue";

export default {
  name: "MainView",
  components: {
    EventBanner,
    StudioCard,
    StoryCard,
    PieceCard,
    ShortcutFlim,
    SearchSession
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
