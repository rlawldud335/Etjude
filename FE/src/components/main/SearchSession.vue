<template>
  <div class="search-session">
    <span class="search-session__title-writing">니가 연기 하던가</span>
    <div class="search-session__search">
      <input
        class="search-session__search-bar"
        placeholder="검색어를 입력해주세요."
        v-model="inputText"
        @keyup.enter="goSearchPage"
      />
      <Search class="search-session__search-icon" @click="goSearchPage" />
    </div>
    <div class="search-session__categorys">
      <div class="search-session__categorys__category" @click="goCategory(1)">
        <img src="@/assets/images/category2.png" alt="" />
        <span>드라마</span>
      </div>
      <div class="search-session__categorys__category" @click="goCategory(2)">
        <img src="@/assets/images/category4.png" alt="" />
        <span>뮤지컬</span>
      </div>
      <div class="search-session__categorys__category" @click="goCategory(3)">
        <img src="@/assets/images/category3.png" alt="" />
        <span>연극</span>
      </div>
      <div class="search-session__categorys__category" @click="goCategory(4)">
        <img src="@/assets/images/category1.png" alt="" />
        <span>영화</span>
      </div>
    </div>
  </div>
</template>
<script>
import { ref } from "vue";
import { useRouter } from "vue-router";
import Search from "../../assets/icons/search.svg";

export default {
  name: "SearchSession",
  components: {
    Search,
  },
  setup() {
    const inputText = ref(null);
    const router = useRouter();
    const goSearchPage = () => {
      router.push({
        name: "search-result",
        params: { categoryId: "0", menuId: "1", keyword: inputText.value },
      });
    };
    const goCategory = (categoryId) => {
      if (inputText.value) {
        router.push({
          name: "search-result",
          params: { categoryId, menuId: "1", keyword: inputText.value },
        });
      } else {
        router.push({
          name: "search-group",
          params: { categoryId, menuId: "1" },
        });
      }
    };
    return {
      inputText,
      goSearchPage,
      goCategory,
    };
  },
};
</script>
<style scoped lang="scss">
.search-session {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.search-session__title-writing {
  margin-top: 30px;
}
.search-session__search {
  display: flex;
  align-items: center;
  position: relative;
  margin: 30px 0px;
}
.search-session__search-bar {
  background-color: #ffeff2;
  padding: 15px 40px;
  border-radius: 30px;
  width: 500px;
  border: $bana-pink solid 1px;
  color: #606060;
  font-size: 12px;
}
.search-session__search-icon {
  cursor: pointer;
  position: absolute;
  right: 20px;
}
.search-session__categorys {
  display: flex;
  flex-direction: row;
}
.search-session__categorys__category {
  display: flex;
  flex-direction: column;
  margin: 30px;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
</style>
