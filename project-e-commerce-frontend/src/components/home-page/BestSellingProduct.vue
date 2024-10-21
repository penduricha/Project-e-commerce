<script>
import './style-view-slidebar.scss';
import Title from "@/components/home-page/Title.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import CustomItemProduct from "@/components/home-page/CustomItemProduct.vue";
import CustomPaginationGrey from "@/components/paginations/CustomPaginationGrey.vue";
import ProductDao from "@/daos/ProductDao.js";

export default {
  name:'BestSellingProduct',

  components:{
    CustomPaginationGrey,
    CustomButton,
    Title,
    CustomItemProduct,
  },

  data(){

    return {

      product_Best_Selling: [],

    }
  },

  created(){
    this.getProductBestSelling_From_API();
  },
  mounted() {
    //this.getIsViewAll();
    //this.getIsViewAll();
  },
  methods: {
    async getProductBestSelling_From_API(){
      const productDao = new ProductDao();
      let products = [];
      try{
        products = await productDao.getBestSellingProducts();
        console.log('Product Flash Sales: ',products);
        this.product_Best_Selling = products.slice(0,4);
      }catch(e){
        console.error(e);
        alert(e);
      }
    },
  },
  computed: {

  },
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
<!--  style="display: flex; margin-bottom: 4%;"-->
  <div class="view-title">
    <div class="view-title-information">
      <Title text-time-title="Today's" ref="titleComponent"  :text-title="'Best Selling Product'"/>
    </div>
    <div class="view-button-view-all" v-if="'Best Selling Product'">
      <CustomButton  style="width: 100%; height: 50%; margin-top: 34%;" text-button="View All"/>
    </div>
    <!-- ref="titleComponent": là dùng titleComponent gọi các data method nó ra-->
  </div>
  <div class="view-list-product" style="display: flex; margin-bottom: 4%; align-items: center;"
       :style="{ justifyContent: (product_Best_Selling.length >= 4) ? 'space-between' : 'none' }"
  >
    <div v-for="(product) in product_Best_Selling" class="list-product"
         :style="{ marginRight: (product_Best_Selling.length < 4) ? '60px' : '0' }"
    >
      <CustomItemProduct :product="product" />
    </div>
  </div>

<!--  <div class="view-list-pagination">-->
<!--    <CustomPaginationGrey v-if="isViewPagination"/>-->
<!--  </div>-->
</template>
<style lang="scss">

</style>