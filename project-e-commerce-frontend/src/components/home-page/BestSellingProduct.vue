<script>
import './style-view-slidebar.css';
import Title from "@/components/home-page/Title.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import CustomItemProduct from "@/components/base/CustomItemProduct.vue";
import CustomPagination from "@/components/base/CustomPagination.vue";

export default {
  name:'BestSellingProduct',

  components:{
    CustomPagination,
    CustomButton,
    Title,
    CustomItemProduct,
  },
  props:['product'],
  data(){
    return {
      isViewPagination: false,
    }
  },
  created(){

  },
  mounted() {
    //this.getIsViewAll();
    //this.getIsViewAll();
  },
  methods: {
    getProductBestSelling(){
      return this.product;
    },

    getTextTile(textTitle){
      return textTitle;
    },

    viewPagination(){
      this.isViewPagination = !this.isViewPagination;
    },

  },
  computed: {
    buttonText() {
      return this.isViewPagination ? 'Hide' : 'View All';
    },
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
      <Title text-time-title="Today's" ref="titleComponent"  :text-title="getTextTile('Best Selling Product') "/>
    </div>
    <div class="view-button-view-all" v-if="(getTextTile('Best Selling Product')==='Best Selling Product')">
      <CustomButton  @click="viewPagination()"  style="width: 100%; height: 50%; margin-top: 34%;" :text-button="buttonText"/>
    </div>
    <!-- ref="titleComponent": là dùng titleComponent gọi các data method nó ra-->
  </div>
  <div class="view-list-product" style="display: flex; margin-bottom: 4%;">
    <div v-for="(product) in getProductBestSelling()" :key="product.id" class="list-product">
      <CustomItemProduct :product="product" />
    </div>
  </div>
  <div style="width: 100%; height: 56px; display: flex; justify-content: center; align-items: center;">
    <CustomButton @click="" style="width: 234px; height: 100%; text-align: center;" text-button="View All Products" v-if="(getTextTile('Best Selling Product')!=='Best Selling Product')"/>
  </div>
  <CustomPagination v-if="isViewPagination"/>
  <!--  <CustomPagination v-if="!getIsViewAll()" />-->
  <!--v-if="(getIsViewAll()===false)" -->
</template>
<style>

</style>