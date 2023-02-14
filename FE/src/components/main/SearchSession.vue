<template>
  <div class="search-session">
    <span class="search-session__title-writing">니가 연기 하던가</span>
    <div class="search-session__search-bar">
      <span>작품 이름, 스토리 이름을 검색하세요.</span>
      <Search />
    </div>
    <div class="search-session__categorys">
      <div class="search-session__categorys__category">
        <img src="@/assets/images/category1.png" alt="" />
        <span>영화</span>
      </div>
      <div class="search-session__categorys__category">
        <img src="@/assets/images/category2.png" alt="" />
        <span>드라마</span>
      </div>
      <div class="search-session__categorys__category">
        <img src="@/assets/images/category3.png" alt="" />
        <span>연극</span>
      </div>
      <div class="search-session__categorys__category">
        <img src="@/assets/images/category4.png" alt="" />
        <span>기타</span>
      </div>
    </div>
  </div>
</template>
<script>
import Search from "../../assets/icons/search.svg";

export default {
  name: "SearchSession",
  components: {
    Search,
  },
};
</script>
<style lang="scss">
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

.search-session__search-bar {
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
  margin: 30px 0px;
  font-size: 12px;
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
}
</style>
