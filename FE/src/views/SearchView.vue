<template>
  <div class="search">
    <div class="search__section">
      <div class="search__bar">
        <input
          class="search__input"
          placeholder="검색어를 입력해주세요."
          :value="inputText"
          @input="inputKeyword"
          @blur="blurInput"
        />
      </div>
      <div class="search__body main__1136width">
        <div class="search__categoty-tab">
          <button
            :class="[
              state.category.id === '0' ? 'search__category--active' : 'search__category--unactive',
            ]"
            @click="updateCategory('0')"
          >
            전체
          </button>
          <button
            :class="[
              state.category.id === '1' ? 'search__category--active' : 'search__category--unactive',
            ]"
            @click="updateCategory('1')"
          >
            드라마
          </button>
          <button
            :class="[
              state.category.id === '2' ? 'search__category--active' : 'search__category--unactive',
            ]"
            @click="updateCategory('2')"
          >
            뮤지컬
          </button>
          <button
            :class="[
              state.category.id === '3' ? 'search__category--active' : 'search__category--unactive',
            ]"
            @click="updateCategory('3')"
          >
            연극
          </button>
          <button
            :class="[
              state.category.id === '4' ? 'search__category--active' : 'search__category--unactive',
            ]"
            @click="updateCategory('4')"
          >
            영화
          </button>
        </div>
        <div class="search__result-section">
          <div class="search__toggle-tab">
            <button @click="updateMenu('1')">작품</button>
            <button @click="updateMenu('2')">스토리</button>
            <div
              :class="[
                {
                  'search__bar--work': state.menu.id === '1',
                  'search__bar--story': state.menu.id === '2',
                },
                'active-bar',
              ]"
            ></div>
          </div>
          <div class="search__result" v-if="inputText">
            <p>"{{ inputText }}"의 검색 결과</p>
            <div class="search__work-result">
              <WorkSearchResult
                v-if="state.menu.id == '1'"
                :works="searchResult"
              ></WorkSearchResult>
            </div>
            <div class="search__story-result">
              <StorySearchResult
                v-if="state.menu.id == '2'"
                :stories="searchResult"
              ></StorySearchResult>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <SearchResult v-if="inputText" :keyword="inputText"></SearchResult>
</template>
<script>
import { ref, reactive, onBeforeMount } from "vue";
import { useRouter, useRoute } from "vue-router";
import WorkSearchResult from "@/components/search/WorkSearchResult.vue";
import StorySearchResult from "@/components/search/StorySearchResult.vue";
import { searchWork, searchStory } from "@/api/search";

export default {
  name: "SearchView",
  components: {
    WorkSearchResult,
    StorySearchResult,
  },
  setup() {
    const router = useRouter();
    const route = useRoute();
    const categoryList = ["전체", "드라마", "뮤지컬", "연극", "영화"];
    const menuList = ["work", "story"];
    const inputText = ref(null);
    const searchResult = ref(null);
    const state = reactive({
      category: {
        id: "0",
        name: "전체",
      },
      menu: {
        id: "1",
        name: "work",
      },
    });
    const search = (keyword, categoryId, menuId) => {
      if (menuId === "1") {
        searchWork(
          keyword,
          categoryId,
          ({ data }) => {
            console.log(data);
            searchResult.value = data;
          },
          (error) => {
            console.log(error);
          }
        );
      } else {
        searchStory(
          keyword,
          categoryId,
          ({ data }) => {
            console.log(data);
            searchResult.value = data;
          },
          (error) => {
            console.log(error);
          }
        );
      }
    };
    onBeforeMount(() => {
      if (route.params?.categoryId) {
        inputText.value = route.params.keyword;
        search(inputText.value, state.category.id, state.menu.id);
        state.category.id = route.params.categoryId;
        state.category.name = categoryList[route.params.categoryId];
        state.menu.id = route.params.menuId;
        state.menu.name = menuList[route.params.menuId - 1];
      }
    });
    const pushPath = () => {
      if (inputText.value) {
        router.push({
          name: "search-result",
          params: {
            categoryId: state.category.id,
            menuId: state.menu.id,
            keyword: inputText.value,
          },
        });
      } else {
        router.push({
          name: "search-group",
          params: { categoryId: state.category.id, menuId: state.menu.id },
        });
      }
    };
    // const replacePath = () => {
    //   if (inputText.value) {
    //     router.replace({
    //       name: "search-result",
    //       params: {
    //         categoryId: state.category.id,
    //         menuId: state.menu.id,
    //         keyword: inputText.value,
    //       },
    //     });
    //   } else {
    //     router.replace({
    //       name: "search-group",
    //       params: { categoryId: state.category.id, menuId: state.menu.id },
    //     });
    //   }
    // };

    if (inputText.value) {
      search(inputText.value, state.category.id, state.menu.id);
    }
    const inputKeyword = (event) => {
      inputText.value = event.target.value;
      // replacePath();
      search(inputText.value, state.category.id, state.menu.id);
    };
    const blurInput = (event) => {
      inputText.value = event.target.value;
      pushPath();
    };
    const updateMenu = (menuId) => {
      state.menu.id = menuId;
      state.menu.name = menuList[state.menu.id - 1];
      pushPath();
      search(inputText.value, state.category.id, state.menu.id);
    };
    const updateCategory = (categoryId) => {
      state.category.id = categoryId;
      state.category.name = categoryList[state.category.id];
      pushPath();
      search(inputText.value, state.category.id, state.menu.id);
    };
    return {
      inputText,
      inputKeyword,
      state,
      searchResult,
      blurInput,
      updateCategory,
      updateMenu,
    };
  },
  beforeRouteUpdate(to, from, next) {
    if (to.name === "search") {
      this.inputText = null;
      this.state.category.id = "0";
      this.state.menu.id = "1";
    } else {
      this.inputText = to.params.keyword;
      this.state.category.id = to.params.categoryId;
      this.state.menu.id = to.params.menuId;
    }
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
  width: 100%;
  display: flex;
  flex-direction: column;
  margin-left: 50px;
  min-height: 500px;
}
.search__result {
  div {
    margin-top: 10px;
  }
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

.search__result {
  margin: 30px;
}
</style>
