import SignupScreen from "@/signup-screen/SignupScreen.vue";
import LoginScreen from "@/login-screen/LoginScreen.vue";
import HomePage from "@/pages/HomePage.vue";
import HomePageWithAccount from "@/pages/HomePageWithAccount.vue";

const routers = [
    { path: '/', component: HomePageWithAccount, props: (route) => ({ emailPhoneHomePage: 'abc@edu.com' }) },
    { path: '/login-screen', component: LoginScreen },
    { path: '/signup-screen', component: SignupScreen },
    { path: '/home-page', component: HomePage },
    { path: '/home-page-with-account', component: HomePageWithAccount, props: (route) => ({ emailPhoneHomePage: route.query.emailPhoneHomePage })},
];
export default routers;

// { path: '/main', component: Main, props: (route) => ({ emailMain: route.query.emailMain }) },
// { path: '/loginScreen', component: LoginScreen },
// { path: '/signUpScreen', component: SignUpScreen },