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
        <Search class="search__search-icon" />
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
          <div class="search__result">
            <p v-if="inputText">"{{ inputText }}"의 검색 결과</p>
            <div class="search__work-result" v-if="state.menu.id == '1'">
              <WorkSearchResult :works="searchResult?.workSearchResponses"></WorkSearchResult>
              <div class="search__pagination-section" v-if="totalPage > 1">
                <v-pagination
                  v-model="pageWork"
                  :pages="totalPage"
                  :range-size="1"
                  active-color="#ffeff2"
                  @update:modelValue="updateHandler"
                  class="search__work-pagination"
                  @click="updatePage"
                />
              </div>
            </div>
            <div class="search__story-result" v-if="state.menu.id == '2'">
              <StorySearchResult :stories="searchResult?.storyListResponses"></StorySearchResult>
              <div class="search__pagination-section" v-if="totalPage > 1">
                <v-pagination
                  v-model="pageStory"
                  :pages="totalPage"
                  :range-size="1"
                  active-color="#ffeff2"
                  @update:modelValue="updateHandler"
                  class="search__story-pagination"
                  @click="updatePage"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <SearchResult :keyword="inputText"></SearchResult>
</template>
<script>
import { ref, reactive, computed, onBeforeMount, watch } from "vue";
import { useRouter, useRoute } from "vue-router";
import WorkSearchResult from "@/components/search/WorkSearchResult.vue";
import StorySearchResult from "@/components/search/StorySearchResult.vue";
import { searchWork, searchStory } from "@/api/search";
import VPagination from "@hennge/vue3-pagination";
import "@hennge/vue3-pagination/dist/vue3-pagination.css";
import Search from "@/assets/icons/search.svg";

export default {
  name: "SearchView",
  components: {
    WorkSearchResult,
    StorySearchResult,
    VPagination,
    Search,
  },
  setup() {
    // 라우드, 라우터 사용
    const router = useRouter();
    const route = useRoute();
    // Name을 Id와 동기화시키기 위한 목록
    const categoryList = ["전체", "드라마", "뮤지컬", "연극", "영화"];
    const menuList = ["work", "story"];
    // 검색창에 입력된 텍스트
    const inputText = ref("");
    // 검색 결과
    const searchResult = ref(null);
    // 페이지네이션에 연동된 페이지 변수
    const pageWork = ref(1);
    const pageStory = ref(1);
    // 카테고리, 작품/스토리, 페이지로 구성된 현재 검색 state
    const state = reactive({
      category: {
        id: "0",
        name: "전체",
      },
      menu: {
        id: "1",
        name: "work",
      },
      page: {
        work: "1",
        story: "1",
      },
    });
    // 검색 결과에 있는 전체 데이터 값으로 전체 페이지 값을 구해서 페이지네이션에 입력
    const totalPage = computed(() => {
      if (searchResult.value) {
        return Math.ceil(searchResult.value.totalCount / 12);
      }
      return totalPage.value;
    });
    // 검색값과 state 값을 받아서 검색, 결과를 searchResult에 저장
    const search = (keyword, searchState) => {
      console.log("검색");
      if (searchState.menu.id === "1") {
        searchWork(
          {
            keyword,
            category_id: searchState.category.id,
            pageNum: searchState.page.work,
          },
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
          {
            keyword,
            category_id: searchState.category.id,
            pageNum: searchState.page.story,
          },
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
    // mount될때 parameter 정보들로 search 동작시킴
    onBeforeMount(() => {
      if (route.params.categoryId) {
        state.category.id = route.params.categoryId;
        state.category.name = categoryList[route.params.categoryId];
        state.menu.id = route.params.menuId;
        state.menu.name = menuList[route.params.menuId - 1];
        if (route.params.page) {
          if (route.params.menuId === "1") {
            state.page.work = route.params.page;
          } else {
            state.page.story = route.params.page;
          }
        }
      }
      if (route.params.keyword) {
        inputText.value = route.params.keyword;
      }
      search(inputText.value, state);
    });
    // 기본적으로 search는 path가 변화될 때 작동
    watch(
      () => route.path,
      async () => {
        search(inputText.value, state);
      }
    );
    // state의 페이지와 pagination에 쓰이는 페이지 정수형<->숫자형으로 양방향 바인딩
    watch(
      () => state.page.work,
      () => {
        pageWork.value = Number(state.page.work);
      }
    );
    watch(
      () => pageWork.value,
      () => {
        state.page.work = String(pageWork.value);
      }
    );
    watch(
      () => state.page.story,
      () => {
        pageStory.value = Number(state.page.story);
      }
    );
    watch(
      () => pageStory.value,
      () => {
        state.page.story = String(pageStory.value);
      }
    );
    // state변화에 따라 router.push하고 path를 변환
    const pushPath = (keyword, searchState) => {
      if (keyword) {
        if (searchState.page !== { work: "1", story: "1" }) {
          // 입력된 검색어가 있고, 페이지가 변경됐으며 작품을 검색하는 경우
          if (searchState.menu.id === "1") {
            router.push({
              name: "search-result-page",
              params: {
                categoryId: searchState.category.id,
                menuId: searchState.menu.id,
                keyword,
                page: searchState.page.work,
              },
            });
            // 입력된 검색어가 있고, 페이지가 변경됐으며 스토리를 검색하는 경우
          } else {
            router.push({
              name: "search-result-page",
              params: {
                categoryId: searchState.category.id,
                menuId: searchState.menu.id,
                keyword,
                page: searchState.page.story,
              },
            });
          }
          // 페이지가 디폴트 값이고 키워드는 있는 경우
        } else {
          router.push({
            name: "search-result",
            params: {
              categoryId: searchState.category.id,
              menuId: searchState.menu.id,
              keyword,
            },
          });
        }
      } else if (searchState.page !== { work: "1", story: "1" }) {
        // 페이지가 바뀌어있고 키워드가 없는 그 카테고리의 전체 작품 값
        if (searchState.menu.id === "1") {
          router.push({
            name: "search-group-page",
            params: {
              categoryId: searchState.category.id,
              menuId: searchState.menu.id,
              page: searchState.page.work,
            },
          });
          // 페이지가 바뀌어있고 키워드가 없는 그 카테고리의 전체 스토리 값
        } else {
          router.push({
            name: "search-group-page",
            params: {
              categoryId: searchState.category.id,
              menuId: searchState.menu.id,
              page: searchState.page.story,
            },
          });
        }
        // 페이지가 디폴트 값이고 키워드가 없는 그 카테고리의 전체 작품 값
      } else {
        router.push({
          name: "search-group",
          params: {
            categoryId: searchState.category.id,
            menuId: searchState.menu.id,
          },
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
    // path 즉 state가 변화될 때 외에 키워드가 입력될 때마다 검색
    const inputKeyword = (event) => {
      inputText.value = event.target.value;
      state.page.work = "1";
      state.page.story = "1";
      // replacePath();
      search(inputText.value, state);
    };
    // 검색 입력값도 history에 남기기 위해 포커스 떨어질때 path변환
    const blurInput = (event) => {
      inputText.value = event.target.value;
      pushPath(inputText.value, state);
    };
    // 메뉴 바뀌면 path변환하고 연동된 검색도 함께 실시
    const updateMenu = (menuId) => {
      state.menu.id = menuId;
      state.menu.name = menuList[state.menu.id - 1];
      pushPath(inputText.value, state);
    };
    // 카테고리 바뀌면 path변환하고 연동된 검색도 함께 실시
    const updateCategory = (categoryId) => {
      state.category.id = categoryId;
      state.category.name = categoryList[state.category.id];
      state.page.work = "1";
      state.page.story = "1";
      pushPath(inputText.value, state);
    };
    // 페이지 바뀌면 path변환하고 연동된 검색도 함께 실시
    const updatePage = () => {
      pushPath(inputText.value, state);
    };
    return {
      inputText,
      inputKeyword,
      state,
      searchResult,
      blurInput,
      updateCategory,
      updateMenu,
      totalPage,
      updatePage,
      pageStory,
      pageWork,
    };
  },
  // 같은 라우트 내에서 path값 변하면 반영 안되는 케이스 방지
  beforeRouteUpdate(to, from, next) {
    if (to.name === "search") {
      this.inputText = "";
      this.state.category.id = "0";
      this.state.menu.id = "1";
    } else {
      if (to.params.keyword) {
        this.inputText = to.params.keyword;
      }
      if (to.params.page)
        if (to.params.menuId === "1") {
          this.state.page.work = to.params.page;
        } else {
          this.state.page.story = to.params.page;
        }
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
  position: relative;
}
.search__search-icon {
  right: 20px;
  position: absolute;
}
.search__input {
  background-color: $soft-bana-pink;
  padding: 15px 40px;
  border-radius: 30px;
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

.search__story-result-card {
  margin: 10px;
}
.search__pagination-section {
  display: flex;
  justify-content: center;
  padding-top: 30px;
}
</style>
