<script>

import Menu from "@/components/menu/Menu.vue";
import Header from "@/components/header-footer-menu/Header.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import ViewTitle from "@/components/home-page/view-all-products/ViewTitle.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import ViewProduct from "@/pages/view-product-detail/ViewProduct.vue";
import ViewRelatedProduct from "@/pages/view-product-detail/ViewRelatedProduct.vue";
import WareHouseDao from "@/daos/WareHouseDao.js";
import RouterDao from "@/daos/RouterDao.js";

export default {
  name: 'ProductDetail',
  components: {ViewRelatedProduct, ViewProduct, Footer, ViewTitle, CustomButton, Header, Menu},

  // props: ['productIdProductDetail'],

  props: {
    productIdProductDetail: {
      type: Number,
      required: true
    }
  },

  data(){
    return{
      wareHouses_By_ProductId: [],
      productIdSession: this.getProductId_From_Session(),
    }
  },

  created() {
    this.getProductId_From_Session();
  },

  mounted() {

  },

  methods: {
    getProductId_From_Session(){
      const routerDao = new RouterDao();
      return routerDao.getProductIdFromSessionStorage();
    },


  }

}

//map field
// this.wareHouses_By_ProductId = warehouses.map(w => ({
//   wareHouseId: w.wareHouseId,
//   size: w.size,
//   color: w.color,
//   quantity: w.quantity,
//   image: w.image,
//   price: w.price,
//   numberOfDiscount: w.numberOfDiscount,
// }))
</script>

<template>
  <div class="container">
    <Header style="height: 50px"/>
    <Menu style="height: 200px"/>
    <main class="main style-main" >
      <div class="view-product-detail">
        <ViewProduct :product-id="productIdSession"/>
      </div>
      <div class="view-related-product">
        <ViewRelatedProduct :product-id="productIdSession"/>
      </div>
    </main>
    <Footer style="height: 1500px; margin-top: 3%"/>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/container';

.style-main{
  height: 1500px;
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
  width: 80%;
  height: 40%;
}

</style>