<template>
  <div class="search">
    <div class="search__section">
      <div class="search__bar">
        <input class="search__input" placeholder="검색어를 입력해주세요." v-model="searchWord" />
      </div>
      <div class="search__tag">
        <button class="search__tag-button"># 드라마</button>
        <button class="search__tag-button"># 영화</button>
        <button class="search__tag-button"># 연극</button>
        <button class="search__tag-button"># 기타</button>
      </div>
    </div>
  </div>
  <router-view />
</template>
<script>
import { ref, watchEffect } from "vue";
import { useRouter } from "vue-router";

export default {
  name: "SearchView",
  components: {},
  setup() {
    const searchWord = ref("");
    const router = useRouter();

    watchEffect(() => {
      if (searchWord.value) {
        router.push({ name: "search-result", params: { keyword: searchWord.value } });
      } else {
        router.push({ name: "search" });
      }
    });
    return {
      searchWord,
    };
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
  background-color: black;
  width: 85px;
  height: 32px;
  border-radius: 20px;
  margin-right: 10px;
  cursor: pointer;
}
</style>
