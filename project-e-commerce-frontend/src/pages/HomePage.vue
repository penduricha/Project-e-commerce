<script>
import Header from "@/components/header-footer-menu/Header.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";

import ImagePhone from "@/pages/ImagePhone.vue";
import FlashSales from "@/components/home-page/FlashSales.vue";
import productFlashSale from "@/assets/data-product/ProductFlashSale.js";
import CustomButton from "@/components/base/CustomButton.vue";
import BestSellingProduct from "@/components/home-page/BestSellingProduct.vue";
import productBestSelling from "@/assets/data-product/ProductBestSelling.js";
import productExplore from "@/assets/data-product/ProductExplore.js";
import MenuHomePageNoAccount from "@/components/menu/Menu.vue";
import ExploreProduct from "@/components/home-page/ExploreProduct.vue";
import CustomService from "@/components/base/CustomService.vue";
export default {
  name: 'HomePage',
  components:{
    CustomService,
    ExploreProduct,
    MenuHomePageNoAccount,
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
  }
}
</script>

<template>
  <div class="container">
    <Header style="height: 50px"/>
    <MenuHomePageNoAccount style="height: 200px"/>
    <main class="main">
      <div class="container-child-1">
        <div class="container-menu">
          <div class="btn-group dropend">
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
        <div class="container-image-advertise">
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
        <div class="view-circle-arrow-up">
          <img src="@/assets/images/icons_arrow-up.png" alt="icon" style="width: 24px; height: 24px;">
        </div>
      </div>
    </main>
    <Footer style="height: 1500px; margin-top: 3%"/>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/input-white';
@import '@/assets/container';


.container-child-1{
  display: flex;
  flex-direction: column;
  height: 400px;
  width: 1680px;
  padding: 0;
}

.container-menu{
  height: 100%;
  width: 350px;
  border-right: 1px solid #bcbcbc;
  margin-left: 7%;
  //border: solid;
  padding-top: 20px;
}

.container-image-advertise{
  height: 49%;
  width: 1176px;
  position: absolute;
  margin-left: 500px;
  padding-top: 25px;
  padding-left: 10px;
}

.menu-item{
  width: 325px;
  height: 40px;
  background-color: transparent;
  border-radius: 0;
  color: black;
  text-align: left;
  font-size: 16px;
  border: none;
}

.menu-item-child{
  width: 305px;
  height: 40px;
  background-color: transparent;
  border-radius: 0;
  text-align: left;
  font-size: 16px;
  border: none;
  margin-left: 20px;
}


.dropdown-toggle::after {
  content: ''; /* Đảm bảo có mũi tên */
  display: inline-block; /* Hiển thị như một phần tử inline */
  border: solid; /* Tạo mũi tên */
  border-width: 0 2px 2px 0; /* Định dạng mũi tên */
  padding: 3px; /* Kích thước mũi tên */
  transform: rotate(315deg); /* Quay mũi tên 315 do*/
}

/*css cho arrow menu*/

.woman-fashion::after{
  /* Khoảng cách giữa văn bản và mũi tên */
  margin-left: 170px;
  /* Quay mũi tên 315 do*/
  transform: rotate(315deg);
}
/*
.woman-fashion.active::after {
  /* Quay mũi tên khi active */
 /* transform: rotate(45deg);*/
/*}*/
/* Khoảng cách giữa văn bản và mũi tên */
.men-fashion::after{
  margin-left: 192px;
}

.view-product-slide{
  width: 82%;
  margin-left: 9%;
  margin-top: 5%;
  height: 120%;
}

.view-service{
  width: 80%;
  margin-top: 5%;
  height: 140px;
  margin-left: 9%;
  display: flex;
  justify-content: center;
}

.view-arrow-up{
  height: 50px;
  width: 90%;
  margin-left: 5%;
  margin-top: 5%;
  display: flex;
  justify-content: right;
}

.view-circle-arrow-up{
  height: 100%;
  width: 50px;
  border-radius: 50px;
  background-color: #F5F5F5;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>