<script>
import Header from "@/components/header-footer-menu/Header.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import './style-item-menu.css';
import './style-home-page.css';
import ImagePhone from "@/pages/ImagePhone.vue";
import FlashSales from "@/components/home-page/FlashSales.vue";
import productFlashSale from "@/assets/data-product/ProductFlashSale.js";
import CustomButton from "@/components/base/CustomButton.vue";
import BestSellingProduct from "@/components/home-page/BestSellingProduct.vue";
import productBestSelling from "@/assets/data-product/ProductBestSelling.js";
import productExplore from "@/assets/data-product/ProductExplore.js";
import Menu from "@/components/menu/Menu.vue";
import ExploreProduct from "@/components/home-page/ExploreProduct.vue";
import CustomService from "@/components/base/CustomService.vue";
export default {
  name: 'HomePageWithAccount',

  props: {
    emailPhoneHomePage: {
      type: String,
      required: true
    }
  },

  components:{
    CustomService,
    ExploreProduct,
    Menu,
    BestSellingProduct,
    CustomButton,
    Header,
    Footer,
    ImagePhone,
    FlashSales,
  },

  data(){
    return{
      isActiveWomenFashion: false,
    }
  },

  created() {
    this.getEmailPhoneNumber();
  },

  methods: {
    //method lấy ds
    productFlashSale() {
      return productFlashSale;
    },
    productBestSelling(){
      return productBestSelling;
    },
    productExplore(){
      return productExplore;
    },
    getEmailPhoneNumber(){
      console.log(this.emailPhoneHomePage);
      return this.emailPhoneHomePage
    }
  }
}
</script>

<template>
  <div class="container">
    <Header style="height: 50px"/>
    <Menu style="height: 200px"/>
    <main class="main">
      <div class="container-child-1">
        <div class="container-menu">
          <div class="btn-group">
            <button
                type="button"
                @click=""
                :class="['btn', 'btn-secondary', 'dropdown-toggle', 'menu-item', 'woman-fashion',]"
                data-bs-toggle="collapse"
            >
              Woman’s Fashion
            </button>
          </div>

          <div class="btn-group">
            <button type="button" class="btn btn-secondary dropdown-toggle menu-item men-fashion">
              Men’s Fashion
            </button>
            <ul class="dropdown-menu">

            </ul>
          </div>
          <div class="btn-group">
            <button type="button" class="btn btn-secondary menu-item">
              Electronics
            </button>
            <ul class="dropdown-menu">

            </ul>
          </div>
          <div class="btn-group">
            <button type="button" class="btn btn-secondary menu-item">
              Home & Lifestyle
            </button>
            <ul class="dropdown-menu">

            </ul>
          </div>
          <div class="btn-group">
            <button type="button" class="btn btn-secondary menu-item">
              Medicine
            </button>
            <ul class="dropdown-menu">

            </ul>
          </div>
          <div class="btn-group">
            <button type="button" class="btn btn-secondary menu-item">
              Sports & Outdoor
            </button>
            <ul class="dropdown-menu">

            </ul>
          </div>
          <div class="btn-group">
            <button type="button" class="btn btn-secondary menu-item">
              Baby’s & Toys
            </button>
            <ul class="dropdown-menu">

            </ul>
          </div>
          <div class="btn-group">
            <button type="button" class="btn btn-secondary menu-item">
              Groceries & Pets
            </button>
            <ul class="dropdown-menu">

            </ul>
          </div>
          <div class="btn-group">
            <button type="button" class="btn btn-secondary menu-item">
              Health & Beauty
            </button>
            <ul class="dropdown-menu">

            </ul>
          </div>
        </div>
        <div class="container-image-advertise container-image-advertise-account">
          <ImagePhone/>
        </div>
      </div>
      <div class="view-product-slide">
        <FlashSales :product="productFlashSale()" />
      </div>

      <hr style="margin-left: 9%; margin-top: 8%; width: 82%;">

      <div class="view-product-slide">
        <BestSellingProduct :product="productBestSelling()" />
      </div>
      <div class="view-product-slide">
        <ExploreProduct :product="productExplore()" />
      </div>
      <div class="view-service">
        <CustomService/>
      </div>
      <div class="view-arrow-up">
        <a class="view-circle-arrow-up">
          <img src="@/assets/images/icons_arrow-up.png" alt="icon" style="width: 24px; height: 24px;">
        </a>
      </div>
    </main>
    <Footer style="height: 1500px; margin-top: 3%"/>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/input-white';
@import '@/assets/container';
.dropdown-toggle::after {
  content: '';
  display: inline-block;
  border: solid;
  border-width: 0 2px 2px 0;
  padding: 4px;
  transform: rotate(315deg);
  text-align: right;
}

.container-image-advertise-account{
  margin-top: -22.5%;
}
</style>