<template lang="">
  <vue-final-modal :value="showModal" classes="modal-contain" content-class="modal-cont">
    <div class="video_container">
      <video :src="filmdata.filmVideoUrl" controls>
        <track kind="captions" />
      </video>
    </div>
    <div class="community_container">
      <UserProfile class="user-profile-container" @close="$emit('close')" :filmdata="profiledata"></UserProfile>
      <FlimContents class="contents-container" :filmdata="postdata" />
      <div class="comment-container" :filmdata="postdata"></div>
    </div>
  </vue-final-modal>
</template>
<script>


import { getFilmDetail } from "@/api/share";
import { ref } from "vue";
import UserProfile from "./UserProfile.vue";
import FlimContents from "./FlimContents.vue";

export default {
  name: "FilmSharingDtail",
  components: {
    UserProfile,
    FlimContents
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
        filmdata.value = data;
        profiledata.value.writerNickName = data.writerNickName;
        profiledata.value.writerPhotoUrl = data.writerPhotoUrl;
        postdata.value.articleTitle = data.articleTitle;
        postdata.value.articleContent = data.articleContent;
        postdata.value.articleCreatedDate = data.articleCreatedDate;
        postdata.value.comments = data.comments;
      },
      (error) => {
        console.log("필름 상세 에러:", error);
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
  width: 1136px;
  aspect-ratio: 2.5/1.5;
}

.video_container {
  width: 50%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: black;
}

video {
  width: 100%;
  aspect-ratio: 640/480;
}

.community_container {
  width: 50%;
  height: 100%;
  background-color: white;
}


.user-profile-container {
  height: 12%;
}

.contents-container {
  height: 35%;
}

.comment-container {
  height: 53%;
  background-color: yellow;
}
</style>
