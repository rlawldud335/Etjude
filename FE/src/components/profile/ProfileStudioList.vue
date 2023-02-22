<template>
  <div v-if="MyStudioData == null">셍성된 스튜디오가 없습니다</div>
  <div v-else class="MyStudio-result">
    <StudioCardItem
      v-for="(item, index) in MyStudioData"
      :key="index"
      :carditem="item"
    ></StudioCardItem>
  </div>
</template>

<script>
import { getMyStudio } from "@/api/users";
import { ref } from "vue";
import StudioCardItem from "../common/StudioCardItem.vue";

export default {
  name: "ProfileStudioList",
  components: { StudioCardItem },
  props: { userId: String },
  setup(props) {
    const MyStudioData = ref([]);
    getMyStudio(
      { user_id: props.userId },
      ({ data }) => {
        data.forEach((array) => {
          MyStudioData.value.push({
            studioId: array.studioId,
            storyThumbnailUrl: array.storyThumbnailUrl,
            studioTitle: array.studioTitle,
            storyTitle: array.storyTitle,
            studioCreatedDate: array.studioCreatedDate,
            studioEndDate: array.studioEndDate,
          });
        });
        console.log("내 스튜디오 데이터:", MyStudioData);
      },
      (error) => {
        console.log("내 스튜디오 찾기 에러:", error);
      }
    );
    return { MyStudioData };
  },
};
</script>
<style lang="scss" scoped>
.MyStudio-result {
  display: flex;
  flex-wrap: wrap;
  gap: 10px 0px;

  > * {
    margin: 5px 23px;
  }
}
</style>
