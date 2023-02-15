<template>
  <div class="story-character-role">
    <div class="story__role-image-frame">
      <img class="story__role-image" :src="role.rolePhotoUrl" alt="" />
    </div>
    <div class="story__role-desc-text">
      <p class="story__role-desc-title">
        {{ role.roleName }}
      </p>
      <p class="story__role-desc-body">
        {{ role.roleDesc }}
      </p>
    </div>
  </div>
</template>
<script>
import {} from "vue";

export default {
  name: "StoryCharacterRole",
  props: {
    role: Object,
  },
  setup() {},
};
</script>
<style lang="scss">
.story-character-role {
  display: flex;
  margin: 50px;
}
.story__role-image-frame {
  width: 200px;
  height: 250px;
  overflow: hidden;
  display: flex;
  border-radius: 15px;
}
.story__role-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.story__role-desc-text {
  margin: 30px 0px 30px 50px;
  max-width: 26rem;
}

.story__role-desc-title {
  width: 100%;
  font-weight: 700;
  font-size: 18px;
  line-height: 200%;
}

.story__role-desc-body {
  height: 140px;
  margin-top: 10px;
  font-weight: 400;
  font-size: 14px;
  line-height: 18px;
  overflow: auto;
  // overflow: hidden;
  // text-overflow: ellipsis;
  // display: -webkit-box;
  // -webkit-line-clamp: 8;
  // -webkit-box-orient: vertical;
}

.story__role-desc-body::-webkit-scrollbar {
  display: none;
}
</style>
