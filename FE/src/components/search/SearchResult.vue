<template>
  <div>{{ searchWord }}에 대한 검색 결과</div>
</template>

<script>
import { ref } from "vue";
import { useRoute } from "vue-router";

export default {
  name: "SearchResult",
  components: {},
  setup() {
    const route = useRoute();
    const searchWord = ref(route.params.keyword);
    return {
      route,
      searchWord,
    };
  },
  beforeRouteUpdate(to, from, next) {
    this.searchWord = to.params.keyword;
    next();
  },
};
</script>
<style lang="scss" scoped></style>
