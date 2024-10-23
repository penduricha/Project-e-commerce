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
  name: 'ViewAll_FlashSales',
  components: {CustomItemProduct, CustomButton, Footer, ViewTitle, Title, Menu, Header},

  data(){
    return{
      products_Flash_Sales: [],
      displayedProducts: [],
      productsToShow: 8,
    }
  },

  created(){
    this.getProductFlashSales_From_API();
  },

  methods:{
    async getProductFlashSales_From_API(){
      const productDao = new ProductDao();
      let products = [];
      try{
        products = await productDao.getFlashSalesProducts();
        //console.log('Product Flash Sales: ',products);
        this.products_Flash_Sales = products;
        let products_View = await products;
        console.log('Product Flash Sales: ',products_View);
        this.displayedProducts = this.products_Flash_Sales.slice(0, this.productsToShow);
      }catch(e){
        console.error(e);
        alert(e);
      }
    },

    groupFourProducts(){
      return chunkArray(this.displayedProducts, 4);
    },

    async toggleViewAll() {
      if (this.productsToShow < this.products_Flash_Sales.length) {
        this.productsToShow += 8;
        // Increase the number of products to show
        this.displayedProducts = this.products_Flash_Sales.slice(0, this.productsToShow);
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
          <ViewTitle text-event="Flash Sales"/>
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
          <div style="width: 100%; height: 56px; display: flex; justify-content: center; align-items: center;">
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