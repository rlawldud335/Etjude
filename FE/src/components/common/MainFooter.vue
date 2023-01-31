<template>
    <div class="footer">
    <div class="footer__text">
      <div class="footer__head">
        <div class="footer__logo">로고</div>
        &nbsp; &nbsp;
        <span class="footer__notice"
          >|&nbsp;&nbsp;개인정보처리방침&nbsp;&nbsp;|&nbsp;&nbsp;이용약관</span
        >
      </div>
      <div class="footer__body">
        사업자번호 : 어쩌구<br />
        주소 : 어디어디
      </div>
    </div>
    <div class="link"></div>
  </div>
</template>
<script>
export default {
    
}
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
}

.footer__body {
  font-size: 12px;
}
</style>