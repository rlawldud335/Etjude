<template>
  <div class="studio-nav">
    <div class="studio-info">
      <div class="studio-info__logo">Logo</div>
      <div class="studio-info__title">
        <span>진양철의 초밥이 몇개고?</span>
        <span> > </span>
        <span>밥알패밀리 스튜디오</span>
      </div>
      <CircleSetting />
    </div>
    <div class="studio-users">
      <div v-for="user in users" :key="user" @click="this.detailUser = user.name">
        <div :class="{ 'studio-users__detail': this.detailUser == user.name }">
          <img class="profile-img" :src="user.imageUrl" alt="" />
          <span v-if="this.detailUser == user.name">{{ user.name }}</span>
          <div class="icon" v-if="this.detailUser == user.name">
            <LinkIcon />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import CircleSetting from "@/assets/icons/CircleSetting.svg";
import LinkIcon from "@/assets/icons/link.svg";

export default {
  components: {
    CircleSetting,
    LinkIcon,
  },
  data() {
    return {
      users: [
        {
          name: "user1",
          imageUrl: require("@/assets/images/profile.jpg"),
        },
        {
          name: "user2",
          imageUrl: require("@/assets/images/profile1.jpg"),
        },
        {
          name: "user3",
          imageUrl: require("@/assets/images/profile2.jpg"),
        },
        {
          name: "user4",
          imageUrl: require("@/assets/images/profile3.jpg"),
        },
      ],
      detailUser: "user4",
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
}

.studio-info__title {
  color: white;
}

.studio-info__title > span {
  margin: 0px 5px;
}

.studio-users {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
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

.profile-img {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  margin: 0px 5px;
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
