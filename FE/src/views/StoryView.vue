<template lang="">
  <div class="story_container">
    <div class="title_container"></div>
    <div class="main_container">
      <div class="left_content">
        <ul class="filter">
          <li>스토리 설명</li>
          <li>배역설명</li>
          <li>스크립트</li>
        </ul>
      </div>
      <div class="right_content"></div>
    </div>
  </div>
</template>
<script>
export default {
  name: "StoryView",
  components: {},
};
</script>
<style lang="scss" scoped>
.story_container {
  display: flex;
  flex-direction: column;
}
.title_container {
  width: 100%;
  height: 366px;
  background-color: antiquewhite;
}
.main_container {
  box-sizing: border-box;
  padding: 50px 100px;
}
.left_content {
  width: 80%;
  height: 1000px;
  //   background-color: aqua;
}
ul {
  list-style: none;
}
li {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 130px;
  height: 44px;
  float: left;
  margin: 5px;
  border: 1px solid gray;
}
</style>
