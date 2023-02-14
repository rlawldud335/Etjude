<template>
  <div class="piece">
    <div class="piece-banner" :style="backgroundStyle">
      <div class="blur-filter"></div>
      <div class="piece-banner__center">
        <div class="piece-banner__image-frame">
          <img :src="work.thumbnailUrl" alt="thumbnail-img" class="piece-banner__pieceImg" />
        </div>
        <div class="piece-banner__text">
          <span class="piece-banner__pieceTitle">{{ work.title }}</span>
          <span class="piece-banner__pieceInfo">{{ work.description }}</span>
        </div>
      </div>
    </div>
    <div class="piece__story-list main__1136width">
      <div class="piece__piece-story-head">
        <span class="piece__story-list-title"> "{{ work.title }}"의 스토리</span>
      </div>
      <div class="piece__story-cards">
        <StoryCardItem
          v-for="story in work.storyList"
          :key="story.storyId"
          :carditem="story"
        ></StoryCardItem>
      </div>
    </div>
  </div>
</template>
<script>
import { reactive, computed } from "vue";
import { useRoute } from "vue-router";
import pieceDetail from "@/dummy/pieceDetailData.json";
import { getWorkInfo, getStoriesOnWork } from "@/api/pieceDetail";
import StoryCardItem from "@/components/common/StoryCardItem.vue";

export default {
  name: "PieceCardItem",
  components: {
    StoryCardItem,
  },
  setup() {
    const route = useRoute();
    const work = reactive({
      id: route.params.pieceId,
      title: null,
      description: null,
      thumbnailUrl: null,
      storyList: [],
    });
    getWorkInfo(
      route.params.pieceId,
      ({ data }) => {
        console.log(data);
        work.title = data.workTitle;
        work.description = data.workDesc;
        work.thumbnailUrl = data.workThumbnailUrl;
      },
      (error) => {
        console.log(error);
      }
    );
    const backgroundStyle = computed(() => ({
      background: `url(${work.thumbnailUrl})`,
      "background-size": "cover",
      "background-repeat": "no-repeat",
      "background-position": "center center",
    }));
    getStoriesOnWork(
      route.params.pieceId,
      ({ data }) => {
        console.log(data);
        work.storyList = data;
      },
      (error) => {
        console.log(error);
      }
    );
    return {
      work,
      pieceDetail,
      backgroundStyle,
    };
  },
};
</script>
<style lang="scss" scoped>
.piece {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.piece-banner {
  width: 100%;
  height: 350px;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 100px;
}

.blur-filter {
  width: 100%;
  height: 350px;
  background: rgba(217, 217, 217, 0.2);
  backdrop-filter: blur(5px);
  position: absolute;
  top: 0;
  left: 0;
}

.piece-banner__center {
  width: 1136px;
  height: 350px;
  position: relative;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}

.piece-banner__image-frame {
  width: 22%;
  aspect-ratio: 3/4;
  border: 3px solid #ffffff;
  position: absolute;
  left: 0;
  margin-left: 60px;
  top: 80px;
  // overflow: hidden;
}
.piece-banner__pieceImg {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.piece-banner__text {
  width: 62%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  margin-right: 60px;
}

.piece-banner__pieceTitle {
  font-size: 24px;
  color: white;
  margin-top: 80px;
  margin-bottom: 25px;
  text-shadow: 1px 1px 1px #000;
}

.piece-banner__pieceInfo {
  color: white;
  font-weight: 200;
  font-size: 16px;
  line-height: 140%;
  text-shadow: 1px 1px 1px #000;
}

.piece__story-list {
  display: flex;
  flex-direction: column;
}
.piece__story-list-title {
  margin: 10px;
  line-height: 140%;
}
.piece__story-cards {
  display: flex;
  flex-wrap: wrap;
  margin: 14px;
  min-height: 200px;
}
</style>
