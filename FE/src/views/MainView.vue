<template lang="">
    <div class="main" >

        <div class="main__fullwidth">
            <event-banner/>
        </div>

        <div class="main__1136width">
            <search-session/>
        </div>

        <div class="main__1136width">
            <div class="my-studio">My studio</div>
        </div>

        <div class="main__1136width">
            <div class="recommended-story">Recommended Story</div>
        </div>
        
        <div class="main__1136width">
            <div class="recommended-piece">Recommended Piece</div>
        </div>

        <div class="main__fullwidth">
            <ShortcutFlim/>
        </div>
    </div>
</template>

<script>
import EventBanner from '@/components/Home/EventBanner.vue';
import SearchSession from "@/components/Home/SearchSession.vue";
import ShortcutFlim from './ShortcutFlim.vue';

export default {
    name: 'MainView',
    components: {
    EventBanner,
    SearchSession,
    ShortcutFlim
  },
}
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