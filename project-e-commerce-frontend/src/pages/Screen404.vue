<script>
import Header from "@/components/header-footer-menu/Header.vue";
import Menu from "@/components/menu/Menu.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import CustomButton from "@/components/base/CustomButton.vue";

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
      <CustomButton @click="" style="width: 254px; height: 56px; margin-top: 15px;" text-button="Back to home page"/>
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