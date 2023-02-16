<!-- eslint-disable vuejs-accessibility/label-has-for -->
<template lang="">
  <vue-final-modal :value="showModal" classes="modal-contain" content-class="modal-cont">
    <div class="film-upload-container">
      <div class="film-upload-container__header">
        <span>필름 공유 글 업로드</span>
        <div @click="$emit('close')">
          <QuitButton></QuitButton>
        </div>
      </div>
      <div class="film-upload-container__main">
        <div class="left_container">
          <div class="left_container__input">
            <h2>제목</h2>
            <input
              class="left_container__input__input_title"
              @input="uploadData.articleTitle = $event.target.value"
              placeholder="제목을 입력해주세요."
            />
          </div>
          <div class="left_container__input">
            <h2>설명</h2>
            <input
              class="left_container__input__input_content"
              @input="uploadData.articleContent = $event.target.value"
              placeholder="설명을 입력해주세요."
            />
          </div>
          <div class="left_container__input">
            <h2>썸네일</h2>
            <div>
              <input
                ref="imgUpload"
                id="upload-image"
                @change="getImageFiles"
                type="file"
                class="img_upload"
                accept="image/*"
                required
                multiple
                hidden
              />
              <label for="upload-image">
                <img
                  id="thumbnailsibal"
                  ref="uploadImageEle"
                  src="@/assets/images/SelectImage.png"
                  class="thumbnail_upload"
                  alt=""
                />
              </label>
            </div>
          </div>
        </div>
        <div class="right_container">
          <div class="select-studio">
            <span>스튜디오 선택하기</span>
            <label for="studioSelect">
              <select id="studioSelect" v-model="selectedStudio" @change="onChangeStudio($event)">
                <option disabled value="">스튜디오 선택</option>
                <option v-for="item in MyStudioData" :key="item.studioId" :value="item[0]">
                  {{ item[1] }}
                </option>
              </select>
            </label>
          </div>

          <div class="select-film">
            <span>필름 선택하기</span>
            <label for="filmSelect">
              <select id="filmSelect" v-model="selectedFilm" @change="onChangeFlim($event)">
                <option disabled value="">필름 선택</option>
                <option
                  v-for="item in MyFilmData"
                  :key="item.myPageFilmsResponse.filmId"
                  :value="item.myPageFilmsResponse.filmId"
                >
                  {{ item.myPageFilmsResponse.filmId }}
                </option>
              </select>
            </label>
          </div>

          <div class="preview-film">
            <span>미리보기</span>
            <div class="preview-film__content">
              <video :src="FilmDetailData.filmVideoUrl" class="filmVideo" controls>
                <track kind="captions" />
              </video>
              <div class="preview-film__info">
                <!-- <div class="">{{ FilmDetailData.categoryName }}</div>
                  <div class="">{{ FilmDetailData.workTitle }}</div>
                  <div class="">{{ FilmDetailData.storyTitle }}</div>
                  <div class="">{{ FilmDetailData.teamMembers }}</div> -->
              </div>
            </div>
          </div>

          <div class="uploadcontent" @click="$emit('close')">
            <button class="uploadbutton" @click="createShareContent">업로드</button>
          </div>
        </div>
      </div>
    </div>
  </vue-final-modal>
</template>

<script>
import QuitButton from "@/assets/icons/Quit Button.svg";
import { reactive, ref } from "vue";
import { useStore } from "vuex";
import { getMyStudio, getMyFilm } from "@/api/users";
import { putFilmShare } from "@/api/share";
import { uploadFlimShareImageUpload } from "@/api/aws";
// import UploadCarousel from "./UploadCarousel.vue";

export default {
  name: "FilmShareUpload",
  components: {
    QuitButton,
  },
  props: {
    showModal: Boolean,
  },
  emits: ["updateFilmList"],
  setup(props, { emit }) {
    // const carouselref = ref();
    const store = useStore();
    const userData = reactive({
      userId: store.state.user.userId,
    });
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
      userId: userData.userId,
      filmId: Number,
      articleContent: "",
      articleTitle: "",
      articleThumbnailUrl: "",
    });
    const thumbNailFile = ref(null);

    const createShareContent = () => {
      uploadFlimShareImageUpload(
        thumbNailFile.value,
        ({ Location }) => {
          console.log(Location);
          uploadData.articleThumbnailUrl = Location;
          putFilmShare(
            uploadData,
            ({ data }) => {
              console.log(data, "생성 완료");
              emit("updateFilmList");
            },
            (error) => {
              console.log(error);
              console.log(uploadData);
            }
          );
        },
        (err) => {
          console.log(err);
        }
      );
    };

    function getImageFiles(event) {
      // eslint-disable-next-line prefer-destructuring
      thumbNailFile.value = event.target.files[0];
      const reader = new FileReader();
      reader.readAsDataURL(thumbNailFile.value);
      reader.onload = () => {
        const temp = document.getElementById("thumbnailsibal");
        temp.src = reader.result;
      };
    }

    getMyStudio(
      { user_id: userData.userId },
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

    const selectStudio = (studioTitle) => {
      console.log(studioTitle);
      MyFilmData.value = [];
      FilmDetailData.value = {};
      selectedFilm.value = "";
      getMyFilm(
        { user_id: userData.userId, studio_id: studioTitle },
        ({ data }) => {
          console.log("Get My film data:", data);
          data.forEach((array) => {
            MyFilmData.value.push(array);
            FilmDetailData.categoryName = array.myPageFilmsResponse.categoryName;
            FilmDetailData.workTitle = array.myPageFilmsResponse.workTitle;
            FilmDetailData.storyTitle = array.myPageFilmsResponse.storyTitle;
            FilmDetailData.teamMembers = array.teamMembers;
          });

          console.log("내 필름 정보랑 상세 정보가 들어갔나?");
          console.log(MyFilmData);
          console.log(FilmDetailData);
        },
        (error) => {
          console.log(error);
        }
      );
    };
    const onChangeStudio = (event) => {
      selectStudio(event.target.value);
    };

    const selectFilm = (choiceFilm) => {
      console.log("choice Flim", choiceFilm);
      MyFilmData.value.forEach((array) => {
        console.log(array.myPageFilmsResponse.filmId, choiceFilm);
        if (array.myPageFilmsResponse.filmId === parseInt(choiceFilm, 10)) {
          console.log("choice Flim url ", array.myPageFilmsResponse.filmVideoUrl);
          FilmDetailData.filmVideoUrl = array.myPageFilmsResponse.filmVideoUrl;
          uploadData.filmId = choiceFilm;
        }
      });
    };
    const onChangeFlim = (event) => {
      console.log("event", event.target);
      console.log("onChageFlim", event.target.value);
      selectFilm(event.target.value);
    };

    return {
      userData,
      getImageFiles,
      onChangeStudio,
      onChangeFlim,
      MyStudioData,
      selectedStudio,
      MyFilmData,
      FilmDetailData,
      selectStudio,
      selectFilm,
      selectedFilm,
      uploadData,
      createShareContent,
    };
  },
};
</script>
<style lang="scss" scoped>
::v-deep .modal-contain {
  display: flex;
  justify-content: center;
  align-items: center;
}

::v-deep .modal-cont {
  display: flex;
  flex-direction: column;
  width: 1050px;
  aspect-ratio: 2.5/1.3;
  background-color: white;
  padding: 20px;
  border-radius: 20px;
}

.film-upload-container {
  width: 100%;
  height: 100%;
}

.film-upload-container__header {
  display: flex;
  flex-direction: row;
  font-size: 1.2rem;
  justify-content: space-between;
  align-items: center;
  height: 10%;
  width: 100%;
  padding: 0px 20px;
  box-sizing: border-box;
}

.film-upload-container__main {
  display: flex;
  flex-direction: row;
  height: 90%;
  width: 100%;
}

.left_container {
  width: 45%;
  height: 100%;
  border-right: 1px solid rgb(211, 211, 211);
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}

.right_container {
  width: 55%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}

.left_container__input {
  padding: 13px;
}

.left_container__input h2 {
  font-size: 16px;
  font-weight: 400;
  margin: 7px;
}

.left_container__input__input_title {
  align-items: center;
  padding: 10px;
  gap: 5px;
  background: #ffffff;
  border: 1px solid #ff5775;
  width: 90%;
  border-radius: 10px;
}

.left_container__input__input_content {
  align-items: center;
  padding: 10px;
  gap: 5px;
  background: #ffffff;
  border: 1px solid #ff5775;
  border-radius: 10px;
  width: 90%;
  height: 60px;
}
</style>

<style scoped>
.dark-mode div::v-deep .modal-cont {
  border-color: #2d3748;
  background-color: #1a202c;
}

.thumbnail_upload {
  width: 200px;
  height: 100px;
  border: 1px solid #ff5775;
  margin: 0px 10px;
  border-radius: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.img_upload {
  display: none;
}

.select-studio {
  padding: 10px;
}

.select-studio span {
  margin-right: 30px;
}

.select-film {
  padding: 10px;
}

.select-film span {
  margin-right: 60px;
}

.preview-film {
  padding: 10px;
  display: flex;
  flex-direction: column;
}

.preview-film__info {
  display: flex;
  flex-direction: column;
  justify-content: start;
  width: 70%;
}

.filmVideo {
  width: 70%;
  aspect-ratio: 2.5/1.5;
}

.preview-film span {
  margin-bottom: 5px;
}

.preview-film__content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.uploadbutton {
  width: 174px;
  height: 38px;
  background-color: #ff5775;
  color: white;
  font-size: 16px;
  font-weight: 400;
  border: none;
  border-radius: 4px;
  float: right;
  cursor: pointer;
}

.thumbnail_upload {
  width: 150px;
  height: 80px;
  object-fit: cover;
}
</style>
