<template>
  <MainNav/>
  <router-view />
  <MainFooter />
</template>

<script>
import MainNav from "./components/common/MainNav.vue";
import MainFooter from "./components/common/MainFooter.vue";

export default {
  components:{
    MainNav,
    MainFooter
  }
};
</script>

<style lang="scss">
@import url(//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css);
* {
  font-family: "Spoqa Han Sans Neo", "sans-serif" !important;
}
</style>
