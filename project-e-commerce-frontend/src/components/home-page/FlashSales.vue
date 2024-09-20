<script>
import './style-view-slidebar.css';
import Title from "@/components/home-page/Title.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import CustomItemProduct from "@/components/base/CustomItemProduct.vue";

export default {
  name:'FlashSales',
  components:{
    CustomButton,
    Title,
    CustomItemProduct,
  },
  props:['product'],
  data(){

  },
  methods: {
    getProductFlashSales(){
      return this.product;
    },
    groupFourProductFlashSales(){
      return chunkArray(this.getProductFlashSales(), 4);
    },
    getTextTile(textTitle){
      return textTitle;
    },
  }
}
//khai các ham xu li khac
function chunkArray(array, chunkSize) {
  const result = [];
  for (let i = 0; i < array.length; i += chunkSize) {
    result.push(array.slice(i, i + chunkSize));
  }
  return result;
}

</script>

<template>
  <div style="width: 100%; height: 70%">
    <div class="view-title">
      <Title text-time-title="Today's" :text-title="getTextTile('Flash Sales')"/>
    </div>
    <!--    v-for="(row, rowIndex) in getProductRows()" :key="rowIndex"-->
    <div class="view-list-product">
      <div v-for="(row, rowIndex) in groupFourProductFlashSales()" :key="rowIndex" class="view-list-product-row">
        <div v-for="(product) in row" :key="product.id" class="list-product">
          <CustomItemProduct :product="product" />
        </div>
      </div>
      <div style="width: 100%; height: 56px; display: flex; justify-content: center; align-items: center;">
        <CustomButton @click="" style="width: 234px; height: 100%; text-align: center;" text-button="View All Products" v-if="(getTextTile('Flash Sales')!=='Best Selling Product')"/>
      </div>
    </div>
  </div>
</template>
