import './assets/main.css';
import 'bootstrap/dist/css/bootstrap.css';
import routers from "@/listRouters.js";
import App from './App.vue';
import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import Router from "../demo-js/localStorage/Router.js";
import HomePage from "@/pages/HomePage.vue";
import LoginScreen from "@/login-screen/LoginScreen.vue";
import SignupScreen from "@/signup-screen/SignupScreen.vue";
import HomePageWithAccount from "@/pages/HomePageAccount.vue";
//vuetify
//npm install vuetify@next @mdi/font
// import { createVuetify } from 'vuetify';
// import 'vuetify/styles';
// import '@mdi/font/css/materialdesignicons.css';
// const vuetify = createVuetify();
function getRouter(){
    const router = localStorage.getItem('router');
    if(router){
        const { _emailPhoneNumber, _routerPath } = JSON.parse(router);
        return new Router(_emailPhoneNumber, _routerPath);
    }
    else {
        return null;
    }
}

function initRouter(routers)
{
    const router = createRouter({
        // mode: 'history',
        history: createWebHistory(),
        //ten bien du lieu truyen qua ko duoc trung
        routes: routers,
    });
    const app = createApp(App)
        // .use(vuetify);
    app.use(router);
    //Dat con tro khi load man hinh, man hinh tro den khi chay main.js
    //push quay ve duoc con replace ko quay ve
    //init trang tại /home-page
    router.replace('/home-page').catch((error) => { console.error('Error navigating: ', error); });
    //app.unmount();
    //app.use(Vue3GeoLocation);
    app.mount('#app')
}

function initHomePageAccount(routerInit){
    const router = createRouter({
        // mode: 'history',
        history: createWebHistory(),
        //ten bien du lieu truyen qua ko duoc trung
        routes: [
            { path: '/login-screen', component: LoginScreen },
            { path: '/signup-screen', component: SignupScreen },
            { path: '/home-page', component: HomePage },
            { path: '/home-page-with-account', component: HomePageWithAccount, props: (route) => ({ emailPhoneHomePage: routerInit._emailPhoneNumber.trim() })},
        ],
    });
    const app = createApp(App)
    // .use(vuetify);
    app.use(router);
    //Dat con tro khi load man hinh, man hinh tro den khi chay main.js
    //push quay ve duoc con replace ko quay ve
    //init trang tại /home-page
    router.replace(routerInit._routerPath).catch((error) => { console.error('Error navigating: ', error); });
    //app.unmount();
    //app.use(Vue3GeoLocation);
    app.mount('#app');
}

//chạy màn hình
//initRouter(routers);

//run
if(getRouter() === null){
    initRouter(routers);
}else{
    initHomePageAccount(getRouter());
}

// const router = createRouter({
//     // mode: 'history',
//     history: createWebHistory(),
//     //ten bien du lieu truyen qua ko duoc trung
//     routes: routers,
// });
// const app = createApp(App)
// // .use(vuetify);
// app.use(router);
// //Dat con tro khi load man hinh, man hinh tro den khi chay main.js
// //push quay ve duoc con replace ko quay ve
// //init trang tại /home-page
// router.replace('/').catch((error) => { console.error('Error navigating: ', error); });
// //app.unmount();
// //app.use(Vue3GeoLocation);
// app.mount('#app')