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
  <div class="right_container" @load="refreshcarousel">
    필름 선택하기
    <div ref="carouselref" class="carousel">
      <UploadCarousel :dummydata="dummydata"></UploadCarousel>
    </div>
    <div class="filmcontent">
      <img :src="require(`@/assets/images/헤어질결심.jpg`)" alt="dummy" class="selectimg" />
      <div class="textcontent">
        <div class="">필름정보</div>
        <div class="">재벌집 막내아들</div>
      </div>
    </div>
    <div class="uploadcontent">
      <button class="uploadbutton">업로드</button>
    </div>
  </div>
  <div class="main_container"></div>
</template>
<script>
import { ref } from "vue";
import { getFilmUpload } from "@/api/share";
import dummydata from "@/dummy/filmdummydata/page1.json";
import UploadCarousel from "./UploadCarousel.vue";

export default {
  name: "UserUpload",
  components: {
    UploadCarousel,
  },
  setup() {
    const carouselref = ref();
    const imgUpload = ref();
    const preview = ref();
    let files = ref();
    getFilmUpload(
      "2",
      ({ data }) => {
        console.log("film data:", data);
      },
      (error) => {
        console.log(error);
      }
    );
    const getImageFiles = (e) => {
      files = `require(${e.currentTarget.value})`;
      console.log(files);
      console.log("==========");
      console.log(files.value);
    };
    const clickthumbnail = () => {
      imgUpload.value.click();
    };
    const refreshcarousel = () => {
      carouselref.value.refresh();
    };
    return {
      carouselref,
      dummydata,
      imgUpload,
      preview,
      files,
      clickthumbnail,
      getImageFiles,
      refreshcarousel,
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
  width: 524px;
  height: 100%;
  background-color: aliceblue;
  box-sizing: border-box;
  padding: 20px;
  font-size: 18px;
  font-weight: 400;
  // background-color: red;
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
.carousel {
  margin: 20px 0px;
  width: 100%;
}
.selectimg {
  width: 308px;
  height: 204px;
}
.filmcontent {
  box-sizing: border-box;
  display: flex;
  margin: 40px 0px;
}
.textcontent {
  box-sizing: border-box;
  margin: 0px 20px;
}
.uploadcontent {
  margin-top: 60px;
  .uploadbutton {
    width: 174px;
    height: 38px;
    background-color: $bana-pink;
    color: white;
    font-size: 16px;
    font-weight: 400;
    border: none;
    border-radius: 4px;
    float: right;
  }
}
</style>
