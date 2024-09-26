<script>
import './style-view-slidebar.css';
import Title from "@/components/home-page/Title.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import CustomItemProduct from "@/components/base/CustomItemProduct.vue";

export default {
  name:'ExploreProduct',
  components:{
    CustomButton,
    Title,
    CustomItemProduct,
  },
  props:['product'],
  data(){

  },
  methods: {
    getExploreProducts(){
      return this.product;
    },
    groupFourExploreProducts(){
      return chunkArray(this.getExploreProducts(), 4);
    },
    getTextTile(textTitle){
      return textTitle;
    },
  }
}

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
      <Title text-time-title="Our Products" :text-title="getTextTile('Explore Our Products')"/>
    </div>
    <!--    v-for="(row, rowIndex) in getProductRows()" :key="rowIndex"-->
    <div class="view-list-product">
      <div v-for="(row, rowIndex) in groupFourExploreProducts()" :key="rowIndex" class="view-list-product-row"
           :style="{ justifyContent: (row.length === 4) ? 'space-between' : 'none' }">
        <div v-for="(product, productIndex) in row" :key="product.id"
             class="list-product"
             :style="{ marginRight: (row.length < 4 && productIndex !== row.length - 1) ? '60px' : '0' }">
          <CustomItemProduct :product="product" />
        </div>
      </div>
      <div style="width: 100%; height: 56px; display: flex; justify-content: center; align-items: center;">
        <CustomButton @click="" style="width: 234px; height: 100%; text-align: center;" text-button="View All Products"/>
      </div>
    </div>
  </div>
</template>
