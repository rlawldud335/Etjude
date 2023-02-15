<template>
  <div class="studio-nav">
    <div class="studio-info">
      <div class="studio-info__logo">
        <router-link :to="{ name: 'main' }"> <studioLogo /></router-link>
      </div>
      <div class="studio-info__title">
        <span>{{ studioInfo.storyTitle }}</span>
        <span> > </span>
        <span>{{ studioInfo.studioTitle }}</span>
      </div>
      <div
        class="studio__setting"
        @mouseover="state.isHovered = true"
        @focus="state.isHovered = true"
        @mouseout="state.isHovered = false"
        @focusout="state.isHovered = false"
      >
        <CircleSetting class="studio__setting-icon" />

        <div class="studio__setting-section" v-show="state.isHovered">
          <div class="studio__setting-dropdown">
            <p>스튜디오 정보</p>
            <p>스튜디오 이름 : {{ studioInfo.studioTitle }}</p>
            <p>
              스튜디오 멤버 :
              <span v-for="member in studioInfo.member_list" :key="member.user_id">
                {{ member.nickname }}&nbsp;
              </span>
            </p>
            <p>생성일 : {{ studioInfo.studio_created_date }}</p>
            <p>만료일 : {{ studioInfo.studio_end_date }}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="studio-users">
      <div
        v-for="member in studioInfo.member_list"
        :key="member.user_id"
        @click="state.detailUser = member.user_id"
      >
        <div :class="{ 'studio-users__detail': state.detailUser == member.user_id }">
          <div class="profile-img-frame">
            <img class="profile-img" :src="member.profile_url" alt="" />
          </div>
          <span v-if="state.detailUser == member.user_id">{{ member.nickname }}</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import CircleSetting from "@/assets/icons/CircleSetting.svg";
import { reactive } from "vue";
import studioLogo from "@/assets/icons/whiteLogo2.svg";

export default {
  components: {
    CircleSetting,
    studioLogo,
  },
  props: {
    studioInfo: Object,
  },
  setup() {
    const state = reactive({
      isHovered: false,
      detailUser: null,
    });

    return {
      state,
    };
  },
  methods: {},
};
</script>
<style lang="scss">
.studio-nav {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  background-color: $bana-pink;
  justify-content: space-between;
}

.studio__setting {
  display: flex;
  align-items: center;
  justify-content: center;
}

.studio-info {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.studio-info__logo {
  margin: 10px 30px;
  font-weight: bold;
  color: white;

  a {
    color: white;
  }
}

.studio-info__title {
  color: white;
}

.studio-info__title > span {
  margin: 0px 5px;
}

.studio__setting {
  position: relative;
  display: inline-block;
}

.studio__setting-section {
  position: absolute;
  z-index: 1;
}
.studio__setting-icon {
  margin-left: 5px;
}

.studio__setting-dropdown {
  margin-top: 5px;
  background-color: white;
  border-radius: 10px;
  padding: 20px 30px;
  white-space: nowrap;

  p {
    line-height: 140%;
    margin: 3px;
    font-size: 14px;
  }

  p:nth-child(1) {
    font-weight: 500;
    margin-bottom: 10px;
    font-size: 16px;
  }
}

.studio-users {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-right: 20px;
  gap: 2px;
}

.studio-users__detail {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  background-color: #ffd1d9;
  padding: 5px 10px;
  border-radius: 20px;
  margin: 0px 10px;
  transition: all 0.5s;
}

.studio-users__detail > span {
  margin: 0px 10px;
  cursor: pointer;
}

.profile-img-frame {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  margin: 0px 5px;
  cursor: pointer;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}

.profile-img {
  width: 30px;
}

.profile-detail {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}

.studio-users__detail {
  background-color: #ffd1d9;
  padding: 5px 10px;
  border-radius: 20px;
  margin-right: 20px;
}

.profile-detail > span {
  margin: 0px 10px;
}

.icon {
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 15px;
}

.icon:hover {
  background-color: #ffa8b7;
}

.display-none {
  display: none;
}
</style>
