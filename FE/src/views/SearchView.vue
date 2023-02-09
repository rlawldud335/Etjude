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
      <div class="search__body main__1136width">
        <div class="search__categoty-tab">
          <button
            :class="[
              state.category === 'all' ? 'search__category--active' : 'search__category--unactive',
            ]"
            @click="state.category = 'all'"
          >
            전체
          </button>
          <button
            :class="[
              state.category === 'movie'
                ? 'search__category--active'
                : 'search__category--unactive',
            ]"
            @click="state.category = 'movie'"
          >
            영화
          </button>
          <button
            :class="[
              state.category === 'drama'
                ? 'search__category--active'
                : 'search__category--unactive',
            ]"
            @click="state.category = 'drama'"
          >
            드라마
          </button>
          <button
            :class="[
              state.category === 'play' ? 'search__category--active' : 'search__category--unactive',
            ]"
            @click="state.category = 'play'"
          >
            연극
          </button>
        </div>
        <div class="search__result-section">
          <div class="search__toggle-tab">
            <button @click="state.menu = 'work'">작품</button>
            <button @click="state.menu = 'story'">스토리</button>
            <div
              :class="[
                {
                  'search__bar--work': state.menu === 'work',
                  'search__bar--story': state.menu === 'story',
                },
                'active-bar',
              ]"
            ></div>
          </div>
          <div class="search__result"></div>
        </div>
      </div>
    </div>
  </div>
  <SearchResult v-if="inputText" :keyword="inputText"></SearchResult>
</template>
<script>
import { ref, reactive } from "vue";
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
    const state = reactive({
      category: "all",
      menu: "work",
    });
    return {
      inputText,
      inputKeyword,
      blurInput,
      state,
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
  background-color: $soft-bana-pink;
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

.search__body {
  display: flex;
  flex-direction: row;
  margin-top: 50px;
}

.search__categoty-tab {
  display: flex;
  flex-direction: column;
  width: 20%;
  button {
    font-weight: 500;
    border-radius: 5px;
    padding: 10px;
    width: 80%;
    margin: 0px 15px;
    border: none;
    cursor: pointer;
  }
  .search__category--unactive {
    background-color: $white;
  }
  .search__category--active {
    background-color: $soft-bana-pink;
    color: $bana-pink;
  }
  .search__category--unactive:hover {
    background-color: $efefe-gray;
    color: black;
  }
}

.search__result-section {
  width: 80%;
  display: flex;
  flex-direction: column;
  margin-left: 50px;
}

.search__toggle-tab {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  position: relative;
  button {
    background-color: white;
    border: none;
    width: 130px;
    padding: 10px 5px 10px 5px;
  }
}

.active-bar {
  position: absolute;
  height: 6px;
  transition: 1s;
  top: 35px;
  width: 130px;
  background: #ff5775;
  transition: 0.3s ease;
}
.search__bar--work {
  left: 0px;
}
.search__bar--story {
  left: 130px;
}
</style>
