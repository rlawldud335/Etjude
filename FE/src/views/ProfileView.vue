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
    <div>
      <div class="profile__category-button">
        <div><span>나의 스튜디오</span></div>
        <div><span>나의 필름</span></div>
        <div><span>나의 게시글</span></div>
        <div><span>나의 좋아요</span></div>
        <div><span>내가 작성한 댓글</span></div>
      </div>
    </div>
  </div>
</template>
<script>
import { getMyPage } from "@/api/users";
import { ref, onBeforeMount } from "vue";
import { useRoute } from "vue-router";

export default {
  name: "ProfileView",
  components: {},
  setup() {
    const route = useRoute();
    const userId = ref(route.params.userId);
    const userInfo = ref(null);
    onBeforeMount(() => {
      if (route.params.userId) {
        userId.value = route.params.userId;
      }
    });
    getMyPage(
      {
        user_id: userId.value,
      },
      ({ data }) => {
        console.log(data);
        userInfo.value = data;
      },
      (error) => {
        console.log(error);
      }
    );
    console.log(userId.value);
    return {
      userId,
      userInfo,
    };
  },
};
</script>
<style lang="scss" scoped>
.profile {
  margin-top: 50px;
  display: flex;
  flex-direction: column;
  text-align: center;
  align-items: center;
}
.profile__thumbnail-frame {
  height: 80px;
  width: 80px;
  min-height: 80px;
  min-width: 80px;
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
  margin-top: 10px;
  font-size: 18px;
  font-weight: 500;
}
.profile__category-button {
  display: flex;
  flex-direction: row;
}
</style>
