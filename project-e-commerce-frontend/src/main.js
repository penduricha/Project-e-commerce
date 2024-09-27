import './assets/main.css';
import 'bootstrap/dist/css/bootstrap.css';
import routers from "@/listRouters.js";
import App from './App.vue';
import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
//vuetify
//npm install vuetify@next @mdi/font
// import { createVuetify } from 'vuetify';
// import 'vuetify/styles';
// import '@mdi/font/css/materialdesignicons.css';
// const vuetify = createVuetify();

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
    router.replace('/').catch((error) => { console.error('Error navigating: ', error); });
    //app.use(Vue3GeoLocation);
    app.mount('#app')
}

function initHomePageWithAccount(){

}

//chạy màn hình
initRouter(routers);
