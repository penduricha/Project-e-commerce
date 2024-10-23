<script>
import './style-view-slidebar.scss';
import Title from "@/components/home-page/Title.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import CustomItemProduct from "@/components/home-page/CustomItemProduct.vue";
import ProductDao from "@/daos/ProductDao.js";

export default {
  name:'ExploreProduct',
  components:{
    CustomButton,
    Title,
    CustomItemProduct,
  },

  data(){
    return {
      products_Explore_Products: [],

      allProducts: [],
      displayedProducts: [],
      productsToShow: 8
    }
  },

  created(){
    this.getExplore_Our_Product_From_API();
  },

  methods: {
    // async getExplore_Our_Product_From_API(){
    //   const productDao = new ProductDao();
    //   let products = [];
    //   try{
    //     products = await productDao.getExploreOurProducts();
    //     console.log('Product Explore Our Product: ',products);
    //     this.products_Explore_Products = products.slice(0,8);
    //   }catch(e){
    //     console.error(e);
    //     alert(e);
    //   }
    // },
    async getExplore_Our_Product_From_API() {
      const productDao = new ProductDao();
      try {
        const products = await productDao.getExploreOurProducts();
        console.log('Product Explore Our Product: ', products);
        this.allProducts = products;
        // Store all products
        this.displayedProducts = this.allProducts.slice(0, this.productsToShow);
      } catch (e) {
        console.error(e);
        alert(e);
      }
    },

    groupFourExploreProducts(){
      return chunkArray(this.displayedProducts, 4);
    },

    async toggleViewAll() {
      if (this.productsToShow < this.allProducts.length) {
        this.productsToShow += 8;
        // Increase the number of products to show
        this.displayedProducts = this.allProducts.slice(0, this.productsToShow);
        // Update displayed products
      }
    }
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
      <Title text-time-title="Our Products" :text-title="'Explore Our Products'"/>
    </div>
    <!--    v-for="(row, rowIndex) in getProductRows()" :key="rowIndex"-->
    <!--Co the dung scss-->
    <div class="view-list-product">
      <div v-for="(row, rowIndex) in groupFourExploreProducts()" :key="rowIndex" class="view-list-product-row"
           :style="{ justifyContent: (row.length === 4) ? 'space-between' : 'none' }"
      >
        <div v-for="(product, productIndex) in row"
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
