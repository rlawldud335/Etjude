<template lang="">
  <div class="profile_container">
    <div class="profile">
      <img :src="require(`@/assets/images/체인소맨.jpg`)" alt="" />
      <div class="nickname">KingGod_doo</div>
    </div>
    <div>icon</div>
  </div>
</template>
<script>
export default {
  name: "UserProfile",
};
</script>
<style lang="scss" scoped>
.profile_container {
  display: flex;
  text-align: center;
  align-items: center;
  justify-content: space-between;
  padding: 8px;
  border-bottom: 1px solid gray;
  background-color: rgb(219, 218, 218);
}
.profile {
  display: flex;
  align-items: center;
}
img {
  width: 56px;
  height: 56px;
  object-fit: cover;
  border-radius: 50%;
}
.nickname {
  margin-left: 16px;
  font-size: 14px;
  font-weight: bold;
}
</style>
