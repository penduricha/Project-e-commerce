import SignupScreen from "@/signup-screen/SignupScreen.vue";
import LoginScreen from "@/login-screen/LoginScreen.vue";
import HomePage from "@/pages/HomePage.vue";

const routers = [
    { path: '/', component: HomePage },
    { path: '/login-screen', component: LoginScreen },
    { path: '/signup-screen', component: SignupScreen },
    { path: '/home-page', component: HomePage },
];
export default routers;

// { path: '/main', component: Main, props: (route) => ({ emailMain: route.query.emailMain }) },
// { path: '/loginScreen', component: LoginScreen },
// { path: '/signUpScreen', component: SignUpScreen },