<template>
  <MainNav />
  <router-view />
  <MainFooter />
</template>
<script>
import MainNav from "@/components/common/MainNav.vue";
import MainFooter from "@/components/common/MainFooter.vue";

export default {
  components: {
    MainNav,
    MainFooter,
  },
};
</script>
<style lang="scss">
.main__fullwidth {
  width: 100%;
}

.main__1136width {
  width: 1136px;
}
</style>
