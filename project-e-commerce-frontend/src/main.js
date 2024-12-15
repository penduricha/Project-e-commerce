import './assets/main.css';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';

import routers from "@/listRouters.js";
import App from './App.vue';
import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import RouterDao from "@/daos/RouterDao.js";
import HomePage from "@/pages/HomePage.vue";
import LoginScreen from "@/login-screen/LoginScreen.vue";
import SignupScreen from "@/signup-screen/SignupScreen.vue";
import Screen404 from "@/pages/Screen404.vue";
import AboutPage from "@/pages/AboutPage.vue";
import HomePageWithAccount from "@/pages/HomePageAccount.vue";
import ViewAll_FlashSales from "@/components/home-page/view-all-products/ViewAll_FlashSales.vue";

//vuetify
//npm install vuetify@next @mdi/font
import { createVuetify } from 'vuetify';
import 'vuetify/styles';
import 'vuetify/dist/vuetify-labs.min.css';
import ViewAll_BestSellingProducts from "@/components/home-page/view-all-products/ViewAll_BestSellingProducts.vue";
import ViewAll_ExploreOurProducts from "@/components/home-page/view-all-products/ViewAll_ExploreOurProducts.vue";
import ProductDetail from "@/pages/view-product-detail/ProductDetail.vue";
import CartScreen from "@/pages/cart/CartScreen.vue";
import ManageAccountScreen from "@/pages/account-page/ManageAccountScreen.vue";
import BillingDetail from "@/pages/billing/BillingDetail.vue";
import ContactScreen from "@/pages/contact/ContactScreen.vue";
import AuthService from "@/services/AuthService.js";

const vuetify = createVuetify();

//tạo path và emailPhoneNumber
const routerDao = new RouterDao();

//routerDao.saveRouterPathToSessionStorage("/about-page");

const app = createApp(App);
app.use(vuetify);

function initRouter(routers, routerPath)
{
    const router = createRouter({
        // mode: 'history',
        history: createWebHistory(),
        routes: routers,
    });


    app.use(router);

    router.replace(routerPath).catch((error) => {
        console.error('Error navigating: ', error);
        router.replace('/screen-404').catch(err => console.error(err));
    });
    //app.unmount();
    //app.use(Vue3GeoLocation);
    app.mount('#app')
}

function initHomePageAccount(emailPhoneNumber, routerPath){
    const router = createRouter({
        // mode: 'history',
        history: createWebHistory(),
        //ten bien du lieu truyen qua ko duoc trung
        routes: [
            { path: '/', component:  HomePageWithAccount, props: () => ({ emailPhoneHomePage: emailPhoneNumber })},
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
            {
                path: '/home-page-with-account', component: HomePageWithAccount,
                props: () => ({ emailPhoneHomePage: emailPhoneNumber })
            },
            { path: '/cart', component: CartScreen},
            { path: '/manage-account', component: ManageAccountScreen},
            { path: '/billing-details', component: BillingDetail},
            { path: '/contact-screen', component: ContactScreen },
        ],
    });
    const app = createApp(App)
    // .use(vuetify);
    app.use(router);
    router.replace(routerPath).catch((error) => {
        console.error('Error navigating: ', error);
        router.replace('/screen-404').catch(err => console.error(err));
    });
    //app.unmount();
    //app.use(Vue3GeoLocation);
    app.mount('#app');
}

//chạy màn hình
//initRouter(routers);

//run
function execute(){
    //try {
        // Đăng nhập và thiết lập thông tin xác thực
        //await AuthService.login('user', 'user1234');
        if(routerDao.getEmailPhoneNumberFromLocalStorage() === null){
            let routerPath = routerDao.getRouterPathFromSessionStorage();
            initRouter(routers,routerPath);
        }else{
            let routerPath = routerDao.getRouterPathFromSessionStorage();
            let emailPhoneNumber = routerDao.getEmailPhoneNumberFromLocalStorage();
            initHomePageAccount(emailPhoneNumber, routerPath);
        }
    // } catch (err) {
    //     console.error(err);
    // }
}



execute();