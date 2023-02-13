<template lang="">
  <vue-final-modal :value="showModal" classes="modal-contain" content-class="modal-cont">
    <div class="video_container">
      <video :src="filmdata.filmVideoUrl" controls>
        <track kind="captions" />
      </video>
    </div>
    <div class="community_container">
      <UserProfile :filmdata="profiledata"></UserProfile>
      <UserPost :filmdata="postdata"></UserPost>
      <UserPostInput></UserPostInput>
    </div>
  </vue-final-modal>
</template>
<script>
import { getFilmDetail } from "@/api/share";
import { ref } from "vue";
import UserProfile from "./UserProfile.vue";
import UserPost from "./UserPost.vue";
import UserPostInput from "./UserPostInput.vue";

export default {
  name: "FilmSharingDtail",
  components: {
    UserProfile,
    UserPost,
    UserPostInput,
  },
  props: {
    showModal: Boolean,
    filmDetailData: Number,
  },
  setup(props) {
    const articleId = ref(props.filmDetailData);
    const filmdata = ref({});
    const profiledata = ref({
      writerNickName: String,
      writerPhotoUrl: String,
    });
    const postdata = ref({
      articleTitle: String,
      articleContent: String,
      articleCreatedDate: Date,
      comments: Object,
    });
    // const getFilmDetailmodal = () => {
    getFilmDetail(
      articleId.value,
      ({ data }) => {
        console.log(data);
        filmdata.value = data;
        profiledata.value.writerNickName = data.writerNickName;
        profiledata.value.writerPhotoUrl = data.writerPhotoUrl;
        postdata.value.articleTitle = data.articleTitle;
        postdata.value.articleContent = data.articleContent;
        postdata.value.articleCreatedDate = data.articleCreatedDate;
        postdata.value.comments = data.comments;
      },
      (error) => {
        console.log(error);
      }
    );
    // };
    // getFilmDetailmodal();
    return {
      articleId,
      filmdata,
      profiledata,
      postdata,
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
  height: 82%;
  // flex-direction: column;
  // margin: 0 1rem;
  // padding: 1rem;
  // border: 1px solid #e2e8f0;
  // border-radius: 0.25rem;
  // background: rgb(66, 44, 44);
}
.video_container {
  width: 738px;
}
video {
  width: 100%;
  height: 100%;
}
.community_container {
  width: 582px;
  height: 100%;
  background-color: white;
}
.video_container img {
  width: 100%;
  height: 100%;
}
.profile_container img {
  object-fit: cover;
  width: auto;
}
</style>

<style scoped>
.dark-mode div::v-deep .modal-cont {
  border-color: #2d3748;
  background-color: #1a202c;
}
</style>
