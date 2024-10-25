<script>

import Menu from "@/components/menu/Menu.vue";
import Header from "@/components/header-footer-menu/Header.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import ViewTitle from "@/components/home-page/view-all-products/ViewTitle.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import ViewProduct from "@/pages/view-product-detail/ViewProduct.vue";
import ViewRelatedProduct from "@/pages/view-product-detail/ViewRelatedProduct.vue";

export default {
  name: 'ProductDetail',
  components: {ViewRelatedProduct, ViewProduct, Footer, ViewTitle, CustomButton, Header, Menu},

  props: ['productId'],

  data(){
    return{

    }
  }

}
</script>

<template>
  <div class="container">
    <Header style="height: 50px"/>
    <Menu style="height: 200px"/>
    <main class="main style-main" >
      <div class="view-product-detail">
        <ViewProduct/>
      </div>
      <div class="view-related-product">
        <ViewRelatedProduct/>
      </div>
    </main>
    <Footer style="height: 1500px; margin-top: 3%"/>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/container';

.style-main{
  height: 1250px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 5%;
}

.view-product-detail{
  width: 80%;
  height: 60%;
}

.view-related-product{
  width: 90%;
  height: 40%;
}

</style>