<template>
  <div class="login">
    <span class="login__notice">소셜로그인으로 시작하기</span>
    <div class="login__button-list">
      <button class="login__button google" @click="login">
        <div class="login__logo-frame">
          <googleLogo />
        </div>
        <span> Google로 로그인하기 </span>
      </button>
      <button class="login__button facebook">
        <div class="login__logo-frame">
          <facebookLogo />
        </div>
        <span> 페이스북으로 로그인하기 </span>
      </button>
      <button class="login__button github">
        <div class="login__logo-frame">
          <githubLogo />
        </div>

        <span> github로 로그인하기 </span>
      </button>
      <button class="login__button twitter">
        <div class="login__logo-frame">
          <twitterLogo />
        </div>

        <span> Twitter로 로그인하기 </span>
      </button>
    </div>
  </div>
</template>

<script>
// import axios from "axios";
import googleLogo from "@/assets/icons/google-logo.svg";
import facebookLogo from "@/assets/icons/facebook-logo.svg";
import githubLogo from "@/assets/icons/github-logo.svg";
import twitterLogo from "@/assets/icons/twitter-logo.svg";

// const ARI_URL = "";

export default {
  name: "LoginView",
  components: {
    googleLogo,
    facebookLogo,
    githubLogo,
    twitterLogo,
  },
  setup() {
    const login = () => {
      // axios({
      //   method: 'get',
      //   url: '{API_URL}',
      // 	params: {},
      // 	data: {},
      // });
      // 	.then((response) => {
      //     console.log(response)
      //   })
      // 	.catch((error) => {
      // 		console.log(error)
      // 	})
    };

    return {
      login,
    };
  },
};
</script>
<style lang="scss" scoped>
.login {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 400px;
}

.login__notice {
  margin-top: 120px;
  font-size: 18px;
  font-weight: 500;
}

.login__button-list {
  display: flex;
  flex-direction: column;
  margin-top: 50px;
  button {
    display: flex;
    align-items: center;
    width: 400px;
    height: 40px;
    margin: 9px 0px;
    padding: 15px 25px 15px 40px;
    border-radius: 15px;
    border: none;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
    span {
      width: calc(100% - 30px);
      text-align: center;
      font-size: 14px;
    }
  }
  .google {
    background-color: white;
  }
  .facebook {
    background-color: #1777f0;
    color: white;
  }
  .github {
    background-color: #000000;
    color: white;
  }
  .twitter {
    background-color: #2c96d4;
    color: white;
  }
}
.login__logo-frame {
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>