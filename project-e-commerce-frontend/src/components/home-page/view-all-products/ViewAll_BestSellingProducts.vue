<script>
import Header from "@/components/header-footer-menu/Header.vue";
import Menu from "@/components/menu/Menu.vue";
import Title from "@/components/home-page/Title.vue";
import ViewTitle from "@/components/home-page/view-all-products/ViewTitle.vue";
import './view-all-product.scss';
import Footer from "@/components/header-footer-menu/Footer.vue";
import ProductDao from "@/daos/ProductDao.js";
import CustomButton from "@/components/base/CustomButton.vue";
import CustomItemProduct from "@/components/home-page/CustomItemProduct.vue";

export default{
  name: 'ViewAll_BestSellingProducts',
  components: {CustomItemProduct, CustomButton, Footer, ViewTitle, Title, Menu, Header},

  data(){
    return{
      products_Best_Selling_Products: [],
      displayedProducts: [],
      productsToShow: 8,
    }
  },

  created(){
    this.getProductBestSelling_Products_From_API();
  },

  methods:{
    async getProductBestSelling_Products_From_API(){
      const productDao = new ProductDao();
      let products = [];
      try{
        products = await productDao.getBestSellingProducts();
        //console.log('Product Flash Sales: ',products);
        this.products_Best_Selling_Products = products;
        let products_View = await products;
        console.log('Product Flash Sales: ',products_View);
        this.displayedProducts = this.products_Best_Selling_Products.slice(0, this.productsToShow);
      }catch(e){
        console.error(e);
        alert(e);
      }
    },

    groupFourProducts(){
      return chunkArray(this.displayedProducts, 4);
    },

    async toggleViewAll() {
      if (this.productsToShow < this.products_Best_Selling_Products.length) {
        this.productsToShow += 8;
        // Increase the number of products to show
        this.displayedProducts = this.products_Best_Selling_Products.slice(0, this.productsToShow);
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
  <div class="container">
    <Header style="height: 50px"/>
    <Menu style="height: 200px"/>
    <main class="main">
      <div class="view-product-slide" >
        <div class="view-title">
          <ViewTitle text-event="Best Selling Products"/>
        </div>
        <div class="view-list-product">
          <div v-for="(row, rowIndex) in groupFourProducts()" :key="rowIndex" class="view-list-product-row"
               :style="{ justifyContent: (row.length === 4) ? 'space-between' : 'none' }"
          >
            <div v-for="(product, productIndex) in row"
                 class="list-product"
                 :style="{ marginRight: (row.length < 4 && productIndex !== row.length - 1) ? '60px' : '0' }">
              <CustomItemProduct :product="product" />
            </div>
          </div>
          <div style="width: 100%; height: 56px; display: flex; justify-content: center; align-items: center;" v-if="productsToShow <= products_Best_Selling_Products.length">
            <CustomButton @click="toggleViewAll()" style="width: 234px; height: 100%; text-align: center;" text-button="View More"/>
          </div>
        </div>
      </div>
    </main>
    <Footer style="height: 1500px; margin-top: 3%"/>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/container';

</style>