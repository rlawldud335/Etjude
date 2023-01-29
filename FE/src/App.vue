<template>
  <nav class="header">
    <div class="header__logo-nav">
      <div class="header__logo">
        <p>Logo</p>
      </div>
      <div class="header__nav">
        <router-link to="/" class="header__nav-item">검색</router-link>
        <router-link to="/about" class="header__nav-item">카테고리</router-link>
      </div>
    </div>
    <div>
      <button class="header__login-button">Login</button>
    </div>
    <div class="header__categoty--dropdown">
      <li>1</li>
      <li>2</li>
      <li>3</li>
      <li>4</li>
    </div>
  </nav>
  <router-view />
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

<style lang="scss">
@import url(//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css);
* {
  font-family: "Spoqa Han Sans Neo", "sans-serif";
}

nav {
  padding: 16px;

  a {
    font-weight: bold;
    color: #2c3e50;
    text-decoration: none;

    // &.router-link-exact-active {
    //   color: #42b983;
    // }
  }
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 24px;
  padding: 16px;
}

.header__logo-nav {
  display: flex;
  align-items: center;
  color: $bana-pink;
  margin-left: 30px;
}

.header__logo {
  display: flex;
  align-items: center;
  font-weight: 700;
  font-size: 24px;
}

.header__nav {
  display: flex;
  margin-left: 8rem;
  align-items: center;
}

.header__nav-item {
  font-size: 18px;
  margin-right: 5rem;
}

.header__login-button {
  border: none;
  color: white;
  background-color: $bana-pink;
  width: 87px;
  height: 32px;
  border-radius: 4px;
  margin-right: 20px;
}

.header__category--dropdown {
  position: absolute;
}

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
