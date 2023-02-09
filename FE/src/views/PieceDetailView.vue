<template>
  <div class="piece">
    <div class="piece-banner">
      <div class="blur-filter"></div>
      <div class="piece-banner__center">
        <img src="@/assets/images/작품썸네일.jpg" alt="" class="piece-banner__pieceImg" />
        <div class="piece-banner__text">
          <span class="piece-banner__pieceTitle">{{ pieceDetail.pieceName }}</span>
          <span class="piece-banner__pieceInfo">{{ pieceDetail.pieceInfo }}</span>
        </div>
      </div>
    </div>
    <div class="piece__story-list">
      <span class="story-list__title"> {{ pieceDetail.pieceName }} 의 전체 스토리</span>
      <div class=""></div>
    </div>
  </div>
</template>
<script>
import { reactive } from "vue";
import pieceDetail from "@/dummy/pieceDetailData.json";
import StoryCardItemData from "@/dummy/StoryCardItemData.json";

export default {
  setup() {
    const state = reactive({});

    return {
      state,
      pieceDetail,
      StoryCardItemData,
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
  background: url("@/assets/images/작품썸네일.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center center;
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

.piece-banner__pieceImg {
  width: 22%;
  height: auto;
  border: 3px solid #ffffff;
  position: absolute;
  top: 80px;
  left: 0;
  margin-left: 30px;
}

.piece-banner__text {
  width: 60%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  margin-right: 30px;
}

.piece-banner__pieceTitle {
  font-size: 24px;
  color: white;
  margin-bottom: 25px;
}

.piece-banner__pieceInfo {
  color: white;
  font-weight: 200;
  font-size: 16px;
}
</style>
