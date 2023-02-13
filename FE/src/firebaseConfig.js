// eslint-disable-next-line import/extensions
import { initializeApp } from "firebase/app";

// TODO: Replace the following with your app's Firebase project configuration
const firebaseConfig = {
  apiKey: "AIzaSyBfvd5iH1OWMbBNZ2gDDPPH0pouLvDLHe0",
  authDomain: "etjude-auth.firebaseapp.com",
  projectId: "etjude-auth",
  storageBucket: "etjude-auth.appspot.com",
  messagingSenderId: "130717970783",
  appId: "1:130717970783:web:4d33cde12a6e24e4fcc6ea",
  measurementId: "G-L0MZK8MJXC",
};

const firebaseApp = initializeApp(firebaseConfig);

export default firebaseApp;
