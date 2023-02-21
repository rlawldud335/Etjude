<template>
  <div class="profile">
    <div class="profile__user-info">
      <div class="profile__thumbnail-frame">
        <img :src="userInfo?.myPageSimpleResponse.userPhotoUrl" alt="" />
      </div>
      <div class="profile__nickname">
        <span> {{ userInfo?.myPageSimpleResponse.userNickName }} </span>
      </div>
    </div>
    <div class="profile__category-section main__1136width">
      <div class="profile__category-buttons">
        <div @click="changeCategory('studios')"><span>나의 스튜디오</span></div>
        <div @click="changeCategory('films')"><span>나의 필름</span></div>
        <div @click="changeCategory('boards')"><span>나의 게시글</span></div>
        <!-- <div><span>나의 좋아요</span></div> -->
        <div @click="changeCategory('comments')"><span>내가 작성한 댓글</span></div>
        <div
          :class="[
            {
              'profile__bar-studios': state.tab === 'studios',
              'profile__bar-films': state.tab === 'films',
              'profile__bar-boards': state.tab === 'boards',
              'profile__bar-comments': state.tab === 'comments',
            },
            'active-bar',
          ]"
        ></div>
      </div>
      <div class="profile__category-section">
        <ProfileStudioList v-if="state.tab === 'studios'"> </ProfileStudioList>
        <ProfileFilmList v-if="state.tab === 'films'"></ProfileFilmList>
        <ProfileBoardList v-if="state.tab === 'boards'"></ProfileBoardList>
        <ProfileCommentList v-if="state.tab === 'comments'"></ProfileCommentList>
      </div>
    </div>
  </div>
</template>
<script>
import { getMyPage } from "@/api/users";
import { reactive, ref, onBeforeMount } from "vue";
import { useRoute, useRouter } from "vue-router";
import ProfileStudioList from "@/components/profile/ProfileStudioList.vue";
import ProfileFilmList from "@/components/profile/ProfileFilmList.vue";
import ProfileCommentList from "@/components/profile/ProfileCommentList.vue";
import ProfileBoardList from "@/components/profile/ProfileBoardList.vue";

export default {
  name: "ProfileView",
  components: {
    ProfileStudioList,
    ProfileFilmList,
    ProfileCommentList,
    ProfileBoardList,
  },
  setup() {
    const route = useRoute();
    const router = useRouter();
    const userId = ref(route.params.userId);
    const userInfo = ref(null);
    const state = reactive({
      tab: "studios",
    });
    onBeforeMount(() => {
      if (route.params.userId) {
        userId.value = route.params.userId;
      }
      if (route.name.startsWith("profile-")) {
        console.log(route.name.slice(8));
        state.tab = route.name.slice(8);
      }
    });
    getMyPage(
      {
        user_id: userId.value,
      },
      ({ data }) => {
        userInfo.value = data;
      },
      (error) => {
        console.log(error);
      }
    );
    const changeCategory = (categoryName) => {
      state.tab = categoryName;
      router.push({ name: `profile-${categoryName}` });
    };
    return {
      route,
      state,
      userId,
      userInfo,
      changeCategory,
    };
  },
};
</script>
<style lang="scss" scoped>
.profile {
  margin-top: 100px;
  display: flex;
  flex-direction: column;
  text-align: center;
  align-items: center;
}
.profile__thumbnail-frame {
  height: 100px;
  width: 100px;
  min-height: 100px;
  min-width: 100px;
  border-radius: 50%;
  overflow: hidden;
  justify-content: center;
  align-items: center;
  img {
    height: 100%;
    width: 100%;
    object-fit: cover;
  }
}

.profile__nickname {
  margin-top: 15px;
  font-size: 20px;
  font-weight: 500;
}
.profile__category-buttons {
  margin-top: 30px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  padding: 10px;
  border-bottom: 1px #757575 solid;
  position: relative;
  div {
    width: 130px;
    cursor: pointer;
    span {
      font-weight: 500;
    }
  }
}
.active-bar {
  position: absolute;
  height: 6px;
  top: 30px;
  transition: 1s;
  width: 130px;
  background: #ff5775;
  transition: 0.3s ease;
}
.profile__bar-studios {
  right: 921px;
  bottom: 0;
}
.profile__bar-films {
  right: 643px;
}
.profile__bar-boards {
  right: 363px;
}
.profile__bar-comments {
  right: 84px;
}
.profile__category-section {
  margin-top: 50px;
  min-height: 300px;
}
</style>
