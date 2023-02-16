<template lang="">
  <div class="profile_container">
    <div class="profile">
      <img
        src="https://upload.wikimedia.org/wikipedia/commons/6/62/Alt%C4%B1n_Portakal.jpg"
        alt=""
      />
      <div class="nickname">{{ profiledata.writerNickName }}</div>
    </div>
    <div class="close-btn" @click="$emit('close')">
      <QuitButton></QuitButton>
    </div>
  </div>
</template>
<script>
import QuitButton from "@/assets/icons/Quit Button.svg";
import { ref } from "vue";

export default {
  name: "UserProfile",
  components: {
    QuitButton,
  },
  props: {
    filmdata: Object,
  },
  setup(props) {
    const profiledata = ref(props.filmdata);
    return { profiledata };
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
  border: none;
  border-bottom: 1px solid #dedede;
  height: 7%;
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
  font-size: 0.9rem;
  font-weight: 500;
}

.close-btn {
  padding: 10px;
}
</style>
