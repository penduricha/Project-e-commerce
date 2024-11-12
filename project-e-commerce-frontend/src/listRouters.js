import SignupScreen from "@/signup-screen/SignupScreen.vue";
import LoginScreen from "@/login-screen/LoginScreen.vue";
import HomePage from "@/pages/HomePage.vue";
import HomePageWithAccount from "@/pages/HomePageAccount.vue";
import Screen404 from "@/pages/Screen404.vue";
import AboutPage from "@/pages/AboutPage.vue";
import ViewAll_FlashSales from "@/components/home-page/view-all-products/ViewAll_FlashSales.vue";
import ViewAll_BestSellingProducts from "@/components/home-page/view-all-products/ViewAll_BestSellingProducts.vue";
import ViewAll_ExploreOurProducts from "@/components/home-page/view-all-products/ViewAll_ExploreOurProducts.vue";
import ProductDetail from "@/pages/view-product-detail/ProductDetail.vue";
import CartScreen from "@/pages/cart/CartScreen.vue";
import ManageAccountScreen from "@/pages/account-page/ManageAccountScreen.vue";
import BillingDetail from "@/pages/billing/BillingDetail.vue";

const routers = [
    // ProductDetail
    { path: '/', component: BillingDetail},
    { path: '/login-screen', component: LoginScreen },
    { path: '/signup-screen', component: SignupScreen },
    { path: '/home-page', component: HomePage },
    { path: '/screen-404', component: Screen404 },
    { path: '/about-page', component: AboutPage },
    { path: '/view-all-flash-sales', component: ViewAll_FlashSales },
    { path: '/view-all-best-selling-product', component: ViewAll_BestSellingProducts },
    { path: '/view-all-explore-our-product', component: ViewAll_ExploreOurProducts },
    { path: '/product-detail', component: ProductDetail,
        props: (route) => ({ productIdProductDetail: route.query.productIdProductDetail })
    },
    { path: '/home-page-with-account', component: HomePageWithAccount,
        props: (route) => ({ emailPhoneHomePage: route.query.emailPhoneHomePage })
    },
    { path: '/cart', component: CartScreen},
    { path: '/manage-account', component: ManageAccountScreen},
    { path: '/billing-details', component: BillingDetail},
];
export default routers;

// { path: '/main', component: Main, props: (route) => ({ emailMain: route.query.emailMain }) },
// { path: '/loginScreen', component: LoginScreen },
// { path: '/signUpScreen', component: SignUpScreen },
//HomePageWithAccount, props: (route) => ({ emailPhoneHomePage: 'abc@edu.com' })