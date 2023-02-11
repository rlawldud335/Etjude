import firebaseConfig from "@/firebaseConfig";
import { getAuth, signInWithPopup, signOut, GoogleAuthProvider } from "firebase/auth";
import { apiInstance } from "@/api/index";

const api = apiInstance();

// eslint-disable-next-line no-unused-expressions
firebaseConfig;

const auth = getAuth();
const provider = new GoogleAuthProvider();

async function login(user, success, fail) {
    console.log("# POST : 회원가입 및 로그인");
    await api.post(`/user/login`, user).then(success).catch(fail);
}

export function handleSignInGoogle() {
    signInWithPopup(auth, provider)
        .then((result) => {
            localStorage.setItem("access-token", JSON.stringify(result.user.accessToken));
            this.user = {
                "userId" : result.user.uid,
                "email" : result.user.email,
                "nickName" : result.user.displayName,
                "picture" : result.user.photoURL,
                "roleType" : "USER",
            }
            login(this.user);
            this.accessToken = result.user.accessToken;
            this.$router.go();
        })
    .catch((error) => {
        console.log(error);
    });
}

export function handleSignOut() {
    signOut(auth)
        .then(() => {
            localStorage.setItem("access-token", "");
            this.user = "";
            this.accessToken = "";
            this.$router.push({ name: 'main' })
        })
    .catch((error) => {
        console.log(error);
    });
}

export { login };
