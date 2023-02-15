<template>
  <div class="main">
    <div class="main__fullwidth">
      <event-banner />
    </div>

    <div class="main__1136width">
      <SearchSession />
    </div>
    <div class="main__studio-list content__1136width" v-show="showStudio">
      <StudioCard></StudioCard>
    </div>
    <div class="content__1136width">
      <StoryCard></StoryCard>
    </div>

    <div class="content__1136width">
      <PieceCard></PieceCard>
    </div>

    <div class="main__fullwidth">
      <ShortcutFlim />
    </div>
  </div>
</template>

<script>
import EventBanner from "@/components/main/EventBanner.vue";
import StudioCard from "@/components/main/StudioCard.vue";
import StoryCard from "@/components/main/StoryCard.vue";
import PieceCard from "@/components/main/PieceCard.vue";
import ShortcutFlim from "@/components/main/ShortcutFlim.vue";
import SearchSession from "@/components/main/SearchSession.vue";
import { computed, ref, watch } from "vue";
import { useStore } from "vuex";

export default {
  name: "MainView",
  components: {
    EventBanner,
    StudioCard,
    StoryCard,
    PieceCard,
    ShortcutFlim,
    SearchSession,
  },
  setup() {
    const store = useStore();
    const user = computed(() => store.state.user);
    const myStudioCount = computed(() => store.state.user.myStudioCount);
    const showStudio = ref(myStudioCount.value > 0);
    watch(myStudioCount, (newCount) => {
      showStudio.value = newCount > 0;
    });
    return {
      user,
      showStudio,
      myStudioCount,
    };
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
  margin-top: 30px;
}

.content__1136width {
  width: 1136px;
  margin: 30px 0px;
}
</style>
