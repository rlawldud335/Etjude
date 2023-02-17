/* eslint-disable import/no-extraneous-dependencies */
import firebaseConfig from "@/firebaseConfig";
import { getAuth, signInWithPopup, signOut, GoogleAuthProvider } from "firebase/auth";
import { apiInstance } from "@/api/index";

const api = apiInstance();

// eslint-disable-next-line no-unused-expressions
firebaseConfig;

const auth = getAuth();
const provider = new GoogleAuthProvider();

async function login(user, success, fail) {
  await api.post(`/user/login`, user).then(success).catch(fail);
}

async function getUserInfo(userId, success, fail) {
  await api({
    method: "post",
    url: "/mypage",
    data: {
      user_id: userId,
    },
  })
    .then(success)
    .catch(fail);
}

export function handleSignInGoogle() {
  signInWithPopup(auth, provider)
    .then((result) => {
      this.user = {
        userId: result.user.uid,
        email: result.user.email,
        nickName: result.user.displayName,
        picture: result.user.photoURL,
        roleType: "USER",
      };
      login(this.user);
      let userInfo = {};
      getUserInfo(
        this.user.userId,
        ({ data }) => {
          userInfo = data;
          userInfo.accessToken = result.user.accessToken;
          userInfo.email = result.user.email;
          userInfo.userId = result.user.uid;
          this.$store.dispatch("login", userInfo);
          const path = this.$route.query?.next;
          if (path) {
            this.$router.push({ path });
          } else {
            this.$router.push({ name: "main" });
          }
        },
        (error) => {
          console.log("유저 정보 에러", error);
        }
      );
    })
    .catch((error) => {
      console.log("소셜 로그인 에러", error);
    });
}

export function handleSignOut() {
  signOut(auth)
    .then(() => {
      this.user = "";
      this.$store.dispatch("logout");
    })
    .catch((error) => {
      console.log("로그아웃 에러:", error);
    });
}

export { login, getUserInfo };
