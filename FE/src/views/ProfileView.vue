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
        <div @click="changeCategory('studios')">
          <div v-if="isUser">나의 스튜디오</div>
          <div v-else class="profile__category-name">
            <span class="profile__category-nickname">
              {{ userInfo?.myPageSimpleResponse.userNickName.slice(0, 3) }}
            </span>
            <span>의 스튜디오 </span>
          </div>
        </div>
        <div @click="changeCategory('films')">
          <div v-if="isUser">나의 필름</div>
          <div v-else class="profile__category-name">
            <span class="profile__category-nickname">
              {{ userInfo?.myPageSimpleResponse.userNickName.slice(0, 3) }}
            </span>
            <span>의 필름 </span>
          </div>
        </div>
        <div @click="changeCategory('boards')">
          <div v-if="isUser">나의 게시글</div>
          <div v-else class="profile__category-name">
            <span class="profile__category-nickname">
              {{ userInfo?.myPageSimpleResponse.userNickName.slice(0, 3) }}
            </span>
            <span>의 게시글 </span>
          </div>
        </div>
        <!-- <div><span>나의 좋아요</span></div> -->
        <div @click="changeCategory('comments')">
          <div v-if="isUser">나의 댓글</div>
          <div v-else class="profile__category-name">
            <span class="profile__category-nickname">
              {{ userInfo?.myPageSimpleResponse.userNickName.slice(0, 3) }}
            </span>
            <span>의 댓글</span>
          </div>
        </div>
        <div
          :class="[
            {
              'profile__bar-studios': tab === 'studios',
              'profile__bar-films': tab === 'films',
              'profile__bar-boards': tab === 'boards',
              'profile__bar-comments': tab === 'comments',
            },
            'active-bar',
          ]"
        ></div>
      </div>
      <div class="profile__category-section">
        <ProfileStudioList v-if="tab === 'studios'" :userId="userId"> </ProfileStudioList>
        <ProfileFilmList v-if="tab === 'films'"></ProfileFilmList>
        <ProfileBoardList v-if="tab === 'boards'"></ProfileBoardList>
        <ProfileCommentList v-if="tab === 'comments'" :userId="userId"></ProfileCommentList>
      </div>
    </div>
  </div>
</template>
<script>
import { getMyPage } from "@/api/users";
import { ref, onBeforeMount, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useStore } from "vuex";
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
    const store = useStore();
    const userId = ref(route.params.userId);
    const userInfo = ref(null);
    const isUser = computed(() => store.state.user?.userId === userId.value);
    const tab = ref("studios");
    onBeforeMount(() => {
      if (route.params.userId) {
        userId.value = route.params.userId;
      }
      if (route.name.startsWith("profile-")) {
        console.log(route.name.slice(8));
        tab.value = route.name.slice(8);
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
      tab.value = categoryName;
      router.push({ name: `profile-${categoryName}` });
    };
    return {
      route,
      tab,
      userId,
      userInfo,
      store,
      isUser,
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
.profile__category-name {
  display: flex;
  justify-content: center;
  flex-wrap: nowrap;
}
.profile__nickname {
  margin-top: 15px;
  font-size: 20px;
  font-weight: 500;
  span {
    white-space: nowrap;
  }
}
.profile__user-info {
  display: flex;
  flex-direction: column;
  align-items: center;
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
    * {
      font-weight: 500;
    }
  }
}
.profile__category-nickname {
  max-width: 60px;
  overflow: hidden;
  text-overflow: "";
  white-space: nowrap;
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
