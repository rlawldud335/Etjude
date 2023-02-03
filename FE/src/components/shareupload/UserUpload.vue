<template lang="">
  <div class="left_container">
    <h2>제목</h2>
    <input class="input_title" placeholder="제목을 입력해주세요." />
    <h2>설명</h2>
    <input class="input_content" placeholder="내용을 입력해주세요." />
    <h2>썸네일</h2>
    <label for="img_upload">
      <input
        ref="imgUpload"
        @change="getImageFiles"
        type="file"
        class="img_upload"
        accept="image/*"
        required
        multiple
      />
    </label>
    <div class="thumbnail_upload" @click="clickthumbnail"></div>
    <img ref="preview" :src="files" alt="test" />
  </div>
  <div class="right_container">
    <Carousel :settings="settings">
      <Slide v-for="slide in 10" :key="slide">
        <div class="carousel__item">{{ slide }}</div>
      </Slide>
      <template #addons>
        <Navigation />
      </template>
    </Carousel>
  </div>
  <div class="main_container"></div>
</template>
<script>
import { ref } from "vue";
import { Carousel, Navigation, Slide } from "vue3-carousel";
import "vue3-carousel/dist/carousel.css";

export default {
  name: "UserUpload",
  components: {
    Carousel,
    Slide,
    Navigation,
  },
  setup() {
    const settings = {
      itemsToShow: 1,
      snapAlign: "center",
    };
    const imgUpload = ref();
    const preview = ref();
    let files = ref();
    const getImageFiles = (e) => {
      files = `require(${e.currentTarget.value})`;
      console.log(files);
      console.log("==========");
      console.log(files.value);
    };
    const clickthumbnail = () => {
      imgUpload.value.click();
    };

    return {
      settings,
      imgUpload,
      preview,
      files,
      clickthumbnail,
      getImageFiles,
    };
  },
};
</script>
<style lang="scss" scoped>
.left_container {
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  width: 524px;
  height: 100%;
  border-right: #606060 solid 1px;
}
.right_container {
  flex-grow: 1;
  height: 100%;
  //   background-color: red;
}
.img_upload {
  display: none;
}
.thumbnail_upload {
  width: 200px;
  height: 200px;
  background-color: rgb(247, 183, 100);
}
h2 {
  font-size: 18;
  font-weight: 400;
  box-sizing: border-box;
  margin: 10px;
  margin-left: 0;
}
.input_title,
.input_content {
  background-color: white;
  padding: 3px 8px;
  border-radius: 5px;
  display: flex;
  flex-direction: row;
  align-items: center;
  border: $bana-pink solid 2px;
  justify-content: space-between;
  color: #606060;
  margin: 0px 15px 0px 5px;
  font-size: 14px;
}
.input_title {
  height: 44px;
}
.input_content {
  height: 120px;
  align-items: flex-start;
}
</style>
