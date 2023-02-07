<template>
  <div class="story-character-role">
    <div class="story__role-image-frame">
      <img class="story__role-image" :src="role.photo_url" alt="" />
    </div>
    <div class="story__role-desc-text">
      <div class="story__role-desc-title">
        {{ role.name }}
      </div>
      <div class="story__role-desc-body">
        {{ role.desc }}
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "StoryCharacterRole",
  props: {
    role: Object,
  },
  setup() {
    return {};
  },
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
</style>
