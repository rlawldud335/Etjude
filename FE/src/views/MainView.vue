<template lang="">
    <div class="main" >

        <div class="main__fullwidth">
            <EventBanner/>
        </div>

        <div class="main__1136width">
            <div class="search-session">Search Session</div>
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
            <div class="shortcut-film">Shortcut Film</div>
        </div>
    </div>
</template>

<script>
import EventBanner from '@/components/Home/EventBanner.vue';

export default {
    name: 'MainView',
    components: {
    EventBanner
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
    background-color: pink;
    width: 1136px;
}
.search-session {
    height: 250px;
}

.my-studio{
    height: 300px;
}

.recommended-story{
    height: 300px;
}
</style>