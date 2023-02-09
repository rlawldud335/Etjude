<template lang="">
  <vue-final-modal :value="showModal" classes="modal-contain" content-class="modal-cont">
    <div class="video_container">
      <video src="https://youtu.be/tp6NKAK9p7E" controls>
        <track kind="captions" />
      </video>
    </div>
    <div class="community_container">
      <!-- {{ filmDetailData }} -->
      {{ filmdata }}
      <UserProfile></UserProfile>
      <UserPost></UserPost>
      <UserPostInput></UserPostInput>
    </div>
  </vue-final-modal>
</template>
<script>
import { getRecommendFilmDetail } from "@/api/share";
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
  setup() {
    const filmdata = ref(null);
    getRecommendFilmDetail(
      ({ data }) => {
        console.log(data);
        filmdata.value = data;
      },
      (error) => {
        console.log(error);
      }
    );
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
  height: 786px;
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
