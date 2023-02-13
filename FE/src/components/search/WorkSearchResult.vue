<template>
  <div class="search-result__work">
    <PieceCardItem v-for="work in works" :key="work.workId" :carditem="work"></PieceCardItem>
  </div>
</template>

<script>
import PieceCardItem from "@/components/common/PieceCardItem.vue";

export default {
  name: "WorkSearchResult",
  components: { PieceCardItem },
  props: { works: Array },
  setup() {
    return {};
  },
};
</script>
<style lang="scss" scoped>
.search-result__work {
  display: flex;
  flex-wrap: wrap;
  gap: 10px 0px;

  > * {
    margin: 5px 23px;
  }
}
</style>
