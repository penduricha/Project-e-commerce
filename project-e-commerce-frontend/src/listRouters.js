import SignupScreen from "@/signup-screen/SignupScreen.vue";
import LoginScreen from "@/login-screen/LoginScreen.vue";
import HomePage from "@/pages/HomePage.vue";
import HomePageWithAccount from "@/pages/HomePageAccount.vue";
import Screen404 from "@/pages/Screen404.vue";
import AboutPage from "@/pages/AboutPage.vue";
import ViewAll_FlashSales from "@/components/home-page/view-all-products/ViewAll_FlashSales.vue";
import ViewAll_BestSellingProducts from "@/components/home-page/view-all-products/ViewAll_BestSellingProducts.vue";
import ViewAll_ExploreOurProducts from "@/components/home-page/view-all-products/ViewAll_ExploreOurProducts.vue";

const routers = [
    { path: '/', component:  HomePage},
    { path: '/login-screen', component: LoginScreen },
    { path: '/signup-screen', component: SignupScreen },
    { path: '/home-page', component: HomePage },
    { path: '/screen-404', component: Screen404 },
    { path: '/about-page', component: AboutPage },
    { path: '/view-all-flash-sales', component: ViewAll_FlashSales },
    { path: '/view-all-best-selling-product', component: ViewAll_BestSellingProducts },
    { path: '/view-all-explore-our-product', component: ViewAll_ExploreOurProducts },
    { path: '/home-page-with-account', component: HomePageWithAccount,
        props: (route) => ({ emailPhoneHomePage: route.query.emailPhoneHomePage })},
];
export default routers;

// { path: '/main', component: Main, props: (route) => ({ emailMain: route.query.emailMain }) },
// { path: '/loginScreen', component: LoginScreen },
// { path: '/signUpScreen', component: SignUpScreen },
//HomePageWithAccount, props: (route) => ({ emailPhoneHomePage: 'abc@edu.com' })