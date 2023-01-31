<template lang="">
  <div class="post_container">
    <div class="post_header">
      <div class="post_title">
        게시글의 제목이 들어가는 공간
        <div class="post_date">2023-01-31 17:59</div>
      </div>
      <div class="post_menu">icon</div>
    </div>
    <div class="post_main">
      <div class="post_text">
        설명입니다. 로렘 입숨(lorem ipsum; 줄여서 립숨, lipsum)은 출판이나 그래픽 디자인 분야에서
        폰트, 타이포그래피, 레이아웃 같은 그래픽 요소나 시각적 연출을 보여줄 때 사용하는 표준 채우기
        텍스트
      </div>
      <div class="post_icon">icon</div>
      <div class="post_favor">좋아요 9321개</div>
    </div>
    <div class="post_comment">
      <div class="comment_read">댓글 1</div>
      <div class="comment_write">댓글쓰기</div>
    </div>
  </div>
</template>
<script>
export default {
  name: "UserPost",
};
</script>
<style lang="scss" scoped>
.post_container {
  display: flex;
  flex-direction: column;
  padding: 24px;
}
.post_header {
  display: flex;
  //   height: 40px;
  justify-content: space-between;
  align-items: center;
  padding: 5px 3px 5px 7px;
}
.post_title {
  font-size: 24px;
  font-weight: bold;
  .post_date {
    font-size: 16px;
    font-weight: 100;
    margin-top: 6px;
    color: gray;
  }
}
.post_main {
  display: flex;
  flex-direction: column;
  //   height: 232px;
  justify-content: space-between;
  padding: 16px 3px 0 7px;
  font-weight: 300;
}
.post_text {
  min-height: 200px;
  //   box-shadow: 0 0 0 1px #000 inset;
}
.post_icon {
  margin-top: 16px;
}
.post_favor {
  margin-top: 8px;
}
.post_comment {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-top: 16px;
  padding: 16px 3px 0 7px;
  box-shadow: 0 0 0 1px #000 inset;
  .comment_read {
    min-height: 272px;
  }
  .comment_write {
  }
}
</style>
