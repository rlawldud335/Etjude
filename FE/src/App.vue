<template>
  <nav class="header">
    <div class="header__logo">
      <span>Logo</span>
      <div class="header__nav">
        <router-link to="/" class="header__nav-item">검색</router-link>
        <router-link to="/about" class="header__nav-item">카테고리</router-link>
      </div>
    </div>
    <div>
      <button class="header__login-button">Login</button>
    </div>
  </nav>
  <router-view />
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

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}

.header {
  display: flex;
  justify-content: space-between;
}

.header__logo {
  display: flex;
  color: $bana-pink;
  font-weight: 1001;
}

.header__nav {
  display: flex;
}

.header__nav-item {
  font-family: Spoqa Han Sans Neo;
  color: black;
  margin: 3px;
}

.header__login-button {
  border: none;
  width: 87px;
  height: 32px;
  border-radius: 4px;
}
</style>
