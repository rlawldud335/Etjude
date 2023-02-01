<template lang="">
  <div class="profile_container">
    <div class="profile">
      <img :src="require(`@/assets/images/체인소맨.jpg`)" alt="" />
      <div class="nickname">KingGod_doo</div>
    </div>
    <QuitButton></QuitButton>
  </div>
</template>
<script>
import QuitButton from "@/assets/icons/Quit Button.svg";

export default {
  name: "UserProfile",
  components: {
    QuitButton,
  },
};
</script>
<style lang="scss" scoped>
.profile_container {
  display: flex;
  text-align: center;
  align-items: center;
  justify-content: space-between;
  padding: 8px;
  padding-left: 16px;
  padding-right: 16px;
  border-bottom: 1px solid gray;
  background-color: rgb(219, 218, 218);
}
.profile {
  display: flex;
  align-items: center;
}
img {
  width: 48px;
  height: 48px;
  object-fit: cover;
  border-radius: 50%;
}
.nickname {
  margin-left: 16px;
  font-size: 14px;
  font-weight: bold;
}
</style>
