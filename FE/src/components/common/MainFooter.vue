<template>
  <div class="footer">
    <div class="footer__text">
      <div class="footer__head">
        <footerLogo />
        &nbsp; &nbsp;
        <span class="footer__notice"
          >|&nbsp;&nbsp;개인정보처리방침&nbsp;&nbsp;|&nbsp;&nbsp;이용약관</span
        >
      </div>
      <div class="footer__body">
        <p>이 사이트의 콘텐츠는 저작권법의 보호를 받는 바 무단 전재, 복사, 배포 등을 금합니다.</p>
        <p>퇴근쓰 &nbsp;|&nbsp; 주소 : 서울특별시 강남구 테헤란로 212</p>
      </div>
    </div>
    <div class="link"></div>
  </div>
</template>
<script>
import footerLogo from "@/assets/icons/logo_textrow_footer.svg";

export default {
  name: "MainFooter",
  components: {
    footerLogo,
  },
};
</script>
<style lang="scss">
.footer {
  color: $efefe-gray;
  height: 263px;
  background-color: #303740;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  line-height: 100%;
}

.footer__text {
  width: 60%;
}

.footer__head {
  display: flex;
  flex-direction: row;
  font-size: 14px;
  align-items: center;
}

.footer__body {
  font-size: 12px;
  margin: 5px 0px;
  p {
    line-height: 140%;
  }
}
</style>
