<!-- eslint-disable vuejs-accessibility/form-control-has-label -->
<template lang="">
  <div class="left_container">
    <h2>제목</h2>
    <input
      class="input_title"
      @input="uploadData.articleTitle = $event.target.value"
      placeholder="제목을 입력해주세요."
    />
    <h2>설명</h2>
    <input
      class="input_content"
      @input="uploadData.articleContent = $event.target.value"
      placeholder="내용을 입력해주세요."
    />
    <h2>썸네일</h2>
    <label for="img_upload">
      <img :src="preview" class="thumbnail_upload" alt="" />
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
  </div>
  <div class="right_container" @load="refreshcarousel">
    필름 선택하기
    <select v-model="selectedStudio" @change="onChange($event)">
      <option disabled value="">스튜디오 선택</option>
      <option v-for="item in MyStudioData" :key="item.studioId" :value="item[1]">
        {{ item[1] }}
      </option>
    </select>
    <!-- <div class="carousel">
      <UploadCarousel :dummydata="dummydata"></UploadCarousel>
    </div> -->
    <div class="studioList">
      <!-- <div class="studioNum" v-for="(item, index) in MyStudioData" :key="index">{{ item }},</div> -->
    </div>
    <div class="filmList">
      <div class="filmItem" v-for="(item, index) in MyFilmData" :key="index">
        <video :src="item.myPageFilmsResponse.filmVideoUrl" class="selectVideo" controls>
          <track kind="captions" />
        </video>
        <button class="choiceVideo" @click="selectFilm(item.myPageFilmsResponse.filmId)"></button>
      </div>
    </div>
    <div class="filmcontent">
      <video :src="FilmDetailData.filmVideoUrl" class="filmVideo" controls>
        <track kind="captions" />
      </video>
      <div class="textcontent">
        필름정보
        <div class="textcontent_info">
          <div class="">카테고리 : {{ FilmDetailData.categoryName }}</div>
          <div class="">작품 : {{ FilmDetailData.workTitle }}</div>
          <div class="">스토리 : {{ FilmDetailData.storyTitle }}</div>
          <div class="">팀원 : {{ FilmDetailData.teamMembers }}</div>
        </div>
      </div>
    </div>
    <div class="uploadcontent">
      <button class="uploadbutton" @click="createShareContent">업로드</button>
    </div>
  </div>
  <div class="main_container"></div>
</template>
<script>
import { reactive, ref } from "vue";
import { getMyStudio, getMyFilm } from "@/api/users";
import { putFilmShare } from "@/api/share";
import dummydata from "@/dummy/filmdummydata/page1.json";
// import UploadCarousel from "./UploadCarousel.vue";

export default {
  name: "UserUpload",
  components: {
    // UploadCarousel,
  },
  setup() {
    // const carouselref = ref();
    const imgUpload = ref(null);
    const preview = ref("");
    const MyStudioData = ref([]);
    const MyFilmData = ref([]);
    const selectedStudio = ref("");
    const selectedFilm = ref("");
    const FilmDetailData = reactive({
      filmVideoUrl: null,
      categoryName: null,
      workTitle: null,
      storyTitle: null,
      teamMembers: null,
    });
    const uploadData = reactive({
      userId: "2",
      filmId: "",
      articleContent: "",
      articleTitle: "",
      articleThumbnailUrl: "",
    });
    const files = ref();
    getMyStudio(
      "2",
      ({ data }) => {
        console.log("My studio data:", data);
        data.forEach((array) => {
          MyStudioData.value.push([array.studioId, array.studioTitle]);
        });
        console.log("데이터가 잘 들어갔나?", MyStudioData);
      },
      (error) => {
        console.log(error);
      }
    );
    const createShareContent = () => {
      putFilmShare(
        uploadData,
        ({ data }) => {
          console.log(data, "생성 완료");
        },
        (error) => {
          console.log(error);
          console.log(uploadData);
        }
      );
    };
    const selectStudio = (studioTitle) => {
      console.log(studioTitle);
      MyFilmData.value = [];
      FilmDetailData.value = {};
      selectedFilm.value = "";
      getMyFilm(
        "2",
        ({ data }) => {
          console.log("Get My film data:", data);
          data.forEach((array) => {
            if (array.myPageFilmsResponse.studioTitle === studioTitle) {
              MyFilmData.value.push(array);
              FilmDetailData.categoryName = array.myPageFilmsResponse.categoryName;
              FilmDetailData.workTitle = array.myPageFilmsResponse.workTitle;
              FilmDetailData.storyTitle = array.myPageFilmsResponse.storyTitle;
              FilmDetailData.teamMembers = array.teamMembers;
            }
          });
        },
        (error) => {
          console.log(error);
        }
      );
    };
    const onChange = (event) => {
      selectStudio(event.target.value);
    };
    const selectFilm = (choiceFilm) => {
      console.log(choiceFilm);
      MyFilmData.value.forEach((array) => {
        if (array.myPageFilmsResponse.filmId === choiceFilm) {
          FilmDetailData.filmVideoUrl = array.myPageFilmsResponse.filmVideoUrl;
          uploadData.filmId = choiceFilm;
        }
      });
    };
    const getImageFiles = (e) => {
      imgUpload.value = e.currentTarget.files;
      preview.value = URL.createObjectURL(imgUpload.value[0]);
    };
    // const refreshcarousel = () => {
    //   carouselref.value.refresh();
    // };
    return {
      // carouselref,
      dummydata,
      imgUpload,
      preview,
      files,
      getImageFiles,
      onChange,
      MyStudioData,
      selectedStudio,
      MyFilmData,
      FilmDetailData,
      selectStudio,
      selectFilm,
      selectedFilm,
      uploadData,
      createShareContent,
      // refreshcarousel,
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
  position: relative;
  flex-grow: 1;
  width: 524px;
  height: 100%;
  box-sizing: border-box;
  padding: 20px;
  font-size: 18px;
  font-weight: 400;
  // background-color: red;
}
.img_upload {
}
.thumbnail_upload {
  width: 200px;
  height: 200px;
  border: 1px solid gray;
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
  margin: 0px 15px 0px 0px;
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

.studioList,
.filmList {
  display: flex;
}
.selectVideo {
  width: 160px;
  height: 120px;
  margin: 10px 50px 0px 0px;
  object-fit: cover;
}
.choiceVideo {
  width: 80px;
  height: 20px;
  font-size: 14px;
  font-weight: 500;
  margin-left: 40px;
  background-color: white;
  border: 1px solid $bana-pink;
  border-radius: 10px;
  cursor: pointer;
}
.choiceVideo:focus {
  background-color: $bana-pink;
}
.filmVideo {
  width: 320px;
  height: 240px;
}
.filmcontent {
  position: absolute;
  left: 20px;
  bottom: 60px;
  box-sizing: border-box;
  display: flex;
  margin: 40px 0px;
}
.textcontent {
  box-sizing: border-box;
  padding: 10px;
  margin: 0px 10px;
  font-size: 18px;
  font-weight: 500;
  .textcontent_info {
    padding: 5px;
    margin: 5px 0px;
    font-size: 14px;
    font-weight: 400;
    line-height: 130%;
  }
}
.uploadcontent {
  margin-top: 60px;
  position: absolute;
  right: 20px;
  bottom: 40px;
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
    cursor: pointer;
  }
}
</style>
