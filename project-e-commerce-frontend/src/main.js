import './assets/main.css';
import 'bootstrap/dist/css/bootstrap.css';

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

//vuetify
//npm install vuetify@next @mdi/font
import { createVuetify } from 'vuetify';
import 'vuetify/styles';
import 'vuetify/dist/vuetify-labs.min.css';
// import '@mdi/font/css/materialdesignicons.css';
// const vuetify = createVuetify();
// function getRouterFromLocalStorage(){
//     const router = localStorage.getItem('router');
//     if(router){
//         const { _emailPhoneNumber, _routerPath } = JSON.parse(router);
//         return new Router(_emailPhoneNumber, _routerPath);
//     }
//     else {
//         return null;
//     }
// }

// function getRouterFromSessionStorage(){
//     const routerDao = new RouterDao();
//     return routerDao.getRouterFromSessionStorage();
// }
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
            { path: '/home-page-with-account', component: HomePageWithAccount, props: () => ({ emailPhoneHomePage: emailPhoneNumber })}
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
    if(routerDao.getEmailPhoneNumberFromLocalStorage() === null){

        let routerPath = routerDao.getRouterPathFromSessionStorage();

        initRouter(routers,routerPath);
    }else{

        let routerPath = routerDao.getRouterPathFromSessionStorage();

        let emailPhoneNumber = routerDao.getEmailPhoneNumberFromLocalStorage();

        initHomePageAccount(emailPhoneNumber, routerPath);
    }
}



execute();