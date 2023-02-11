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
            this.user = {
                "userId" : result.user.uid,
                "email" : result.user.email,
                "nickName" : result.user.displayName,
                "picture" : result.user.photoURL,
                "roleType" : "USER",
            }
            login(this.user);
            this.$store.dispatch("login", result.user.accessToken);
            this.$router.push({ name: 'main' });
        })
    .catch((error) => {
        console.log(error);
    });
}

export function handleSignOut() {
    signOut(auth)
        .then(() => {
            this.user = "";
            this.$store.dispatch("logout");
            this.$router.push({ name: 'main' })
        })
    .catch((error) => {
        console.log(error);
    });
}

export { login };
