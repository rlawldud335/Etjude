<template>
  <div class="event-banner">Banner</div>
  <div class="search-session">Search Session</div>
  <div class="my-studio">My studio</div>
  <div class="recommended-story">Recommended Story</div>
  <div class="recommended-piece">Recommended Piece</div>
  <div class="shortcut-film">Shortcut Film</div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue';

export default {
  name: 'HomeView',
  components: {
  },
};
</script>

<style lang="scss">
.event-banner{
    height: 400px;
    background-color: aquamarine;
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
