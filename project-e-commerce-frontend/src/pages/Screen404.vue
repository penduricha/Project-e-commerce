<script>
import Header from "@/components/header-footer-menu/Header.vue";
import Menu from "@/components/menu/Menu.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import Router from "../../demo-js/localStorage/Router.js";

export default {
  name: 'Screen404',
  components:{
    CustomButton,
    Header,
    Menu,
    Footer,
  },
  data(){
    return{

    }
  },
  methods: {
    handleHomePage(){
      if(getLocalStorageRouter() === null){
        this.$router.replace({
          path: '/home-page',
        }).catch((error) => {
          console.error('Error navigating :', error);
          alert(error);
        });
      }else{
        this.$router.replace({
          path: '/home-page-with-account',
          query: {
            emailPhoneHomePage: this.emailPhoneHomePage.trim(),
          }
        }).catch((error) => {
          console.error('Error navigating :', error);
          alert(error);
        });
      }
    },
  }
}

function getLocalStorageRouter(){
  const router = localStorage.getItem('router');
  if(router){
    const { _emailPhoneNumber, _routerPath } = JSON.parse(router);
    // let router= new Router(_emailPhoneNumber, _routerPath);
    // return router._emailPhoneNumber;
    return new Router(_emailPhoneNumber, _routerPath);
  }
  else {
    return null;
  }
}
</script>

<template>
  <div class="container">
    <Header style="height: 50px"/>
    <Menu style="height: 200px"/>
    <main class="main" style="height: 360px; display: flex; justify-content: center; align-items: center; flex-direction: column;">
      <div class="title-404">
        <div>
          <p style="font-size: 90px; font-weight: 450;">404 Not Found</p>
          <p style="text-align: center; font-size: 16px;">Your visited page not found. You may go home page.</p>
        </div>
      </div>
      <CustomButton @click="handleHomePage()" style="width: 254px; height: 56px; margin-top: 15px;" text-button="Back to home page"/>
    </main>
    <Footer style="height: 1500px;"/>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/input-white';
@import '@/assets/container';
.title-404{
  width: 60%;
  height: 60%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>