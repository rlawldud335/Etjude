<template>
  <div>{{ keyword }}에 대한 검색 결과</div>
</template>

<script>
export default {
  name: "SearchResult",
  components: {},
  props: {
    keyword: String,
  },
  setup() {
    return {};
  },
};
</script>
<style lang="scss" scoped></style>
