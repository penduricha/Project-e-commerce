<script>
import CustomButton from "@/components/base/CustomButton.vue";
import RouterDao from "@/daos/RouterDao.js";

export default {
  name:'Title',

  props: ['textEvent'],

  components:{
    CustomButton,
  },

  data() {
    return {

    };
  },

  methods: {
    handleHomePage(){

      const routerDao = new RouterDao();

      if(!routerDao.getEmailPhoneNumberFromLocalStorage()){

        routerDao.saveRouterPathToSessionStorage("/home-page");

        this.$router.replace({
          path: '/home-page',
        }).catch((error) => {
          console.error('Error navigating :', error);
          alert(error);
        });
      }else{
        routerDao.saveRouterPathToSessionStorage("/home-page-with-account");

        this.$router.replace({
          path: '/home-page-with-account',
          query: {
            emailPhoneHomePage: routerDao.getEmailPhoneNumberFromLocalStorage().trim(),
          }
        }).catch((error) => {
          console.error('Error navigating :', error);
          alert(error);
        });
      }
    },
  },
}
</script>

<template>
  <div style="display: flex; width: 100%; height: 100%;">
    <div class="content-title" >
      <div class="title-time">
        <div style="display: flex;">
          <div class="icon-red"/>
          <div class="time-content">
            <p class="style-time-content">{{textEvent}}</p>
          </div>
        </div>
        <CustomButton @click="handleHomePage()" class="style-button-back" text-button="Back to home page"/>
      </div>
    </div>
  </div>
</template>

<style>
.content-title{
  height: 100%;
  flex: 7;
}

.style-button-back{
  width: 200px;
  height: 100%;
}

.title-time{
  height: 40px;
  display: flex;
  justify-content: space-between;
}

.icon-red{
  width: 25px;
  height: 100%;
  background-color: #DB4444;
  border-radius: 3px;
}

.time-content{
  width: 100%;
  height: 100%;
  padding-left: 16px;

}

.style-time-content {
  font-size: 20px;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  color: black;
  font-weight: 480;
}
</style>