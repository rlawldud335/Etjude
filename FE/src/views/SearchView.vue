<template>
  <div class="search">
    <div class="search__section">
      <div class="search__bar">
        <input
          class="search__input"
          placeholder="검색어를 입력해주세요."
          @input="inputKeyword"
          @blur="blurInput"
        />
      </div>
      <div class="search__tag">
        <button class="search__tag-button"># 드라마</button>
        <button class="search__tag-button"># 영화</button>
        <button class="search__tag-button"># 연극</button>
        <button class="search__tag-button"># 기타</button>
      </div>
    </div>
  </div>
  <SearchResult v-if="inputText" :keyword="inputText"></SearchResult>
</template>
<script>
import { ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import SearchResult from "@/components/search/SearchResult.vue";

export default {
  name: "SearchView",
  components: {
    SearchResult,
  },
  setup() {
    const router = useRouter();
    const route = useRoute();

    const inputText = ref(route.params.keyword);
    const inputKeyword = (event) => {
      inputText.value = event.target.value;
    };
    const blurInput = () => {
      if (inputText.value) {
        router.push({ name: "search-result", params: { keyword: inputText.value } });
      } else {
        router.push({ name: "search" });
      }
    };
    return {
      inputText,
      inputKeyword,
      blurInput,
    };
  },
  beforeRouteUpdate(to, from, next) {
    this.inputText = to.params.keyword;
    next();
  },
};
</script>

<style scoped lang="scss">
.search__section {
  margin: 50px 0px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.search__bar {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.search__input {
  background-color: #ffeff2;
  padding: 15px 40px;
  border-radius: 30px;
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 500px;
  border: $bana-pink solid 1px;
  justify-content: space-between;
  color: #606060;
  margin: 5px 0px;
  font-size: 14px;
}
.search__glass-icon {
  margin-left: 10px;
  cursor: pointer;
}

.search__tag {
  width: 600px;
  margin: 10px 0px;
}

.search__tag-button {
  border: none;
  color: white;
  font-size: 14px;
  background-color: $bana-pink;
  width: 85px;
  height: 32px;
  border-radius: 20px;
  margin-right: 10px;
  cursor: pointer;
}
</style>
