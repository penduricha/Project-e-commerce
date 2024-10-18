<script>
import './style-view-slidebar.scss';
import Title from "@/components/home-page/Title.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import CustomItemProduct from "@/components/home-page/CustomItemProduct.vue";
import ProductDao from "@/daos/ProductDao.js";

//npm install vue3-carousel
//npm install vuetify@3.7.3
export default {
  name:'FlashSales',
  components:{
    CustomButton,
    Title,
    CustomItemProduct,
  },

  // props:['product'],
  data(){
    return {
      products_Flash_Sales: [],
    }
  },

  created(){
    this.getProductFlashSales_From_API();
  },

  methods: {

    async getProductFlashSales_From_API(){
      const productDao = new ProductDao();
      let products = [];
      try{
        products = await productDao.getFlashSalesProducts();
        console.log('Product Flash Sales: ',products);
        this.products_Flash_Sales = products.slice(0,4);
      }catch(e){
        console.error(e);
        alert(e);
      }
    },
  },

  computed: {
    //ham thay doi css
    // flashSalesClass() {
    //   // Check the length of products_Flash_Sales
    //   return this.products_Flash_Sales.length >= 4 ? 'justify-space-between' : 'justify-start';
    // },

    // customItemProductClass(){
    //   return this.products_Flash_Sales.length >= 4 ? 'margin-right-none' : 'margin-right-have';
    // },
  }
}
//khai các ham xu li khac
// function chunkArray(array, chunkSize) {
//   const result = [];
//   for (let i = 0; i < array.length; i += chunkSize) {
//     result.push(array.slice(i, i + chunkSize));
//   }
//   return result;
// }

</script>

<template>
  <div style="width: 100%; height: 70%">
    <div class="view-title">
      <Title text-time-title="Today's" :text-title="'Flash Sales'"/>
    </div>
    <!--        :class="['view-product-flash-sales', flashSalesClass]"-->
    <!--        <div v-for="(product) in products_Flash_Sales.slice(0, 4)" :key="product.productId" class="list-product" :class="['view-margin-item', customItemProductClass]">-->
    <!--          <CustomItemProduct :product="product" />-->
    <!--        </div>-->
    <div class="view-list-product">
      <!--Ap dung computed cho scss-->
      <div style="display: flex; width: auto;">
        <div v-for="(product) in products_Flash_Sales" class="list-product margin-right-product">
          <CustomItemProduct :product="product" />
        </div>
      </div>

      <div style="width: 100%; height: 56px; display: flex; justify-content: center; align-items: center; margin-top: 5%">
        <CustomButton @click="" style="width: 234px; height: 100%; text-align: center;" text-button="View All Products"/>
      </div>
    </div>
  </div>
</template>

<style lang="scss">
//@function justify-content($condition) {
//  @if $condition < 4 {
//    @return flex-start;
//  } @else {
//    @return space-between;
//  }
//}
//
//.view-product-flash-sales {
//  display: flex;
//  justify-content: justify-content(4);
//}
//@function justify-content($condition) {
//  @if $condition < 4 {
//    @return flex-start;
//  } @else {
//    @return space-between;
//  }
//}
//
//
//.view-margin-item{
//  &.margin-right-none{
//    margin-right: 0;
//  }
//
//  &.margin-right-have{
//    margin-right: 60px;
//  }
//}

//.view-product-flash-sales {
//  display: flex;
//
//  &.justify-start {
//    justify-content: justify-content(3);
//  }
//
//  &.justify-space-between {
//    justify-content: justify-content(4);
//  }
//}

.margin-right-product {
  margin-right: 50px;
}


</style>