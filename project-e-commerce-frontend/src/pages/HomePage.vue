<script>
import Header from "@/components/header-footer-menu/Header.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import './style-item-menu.scss';
import './style-home-page.scss';
import ImagePhone from "@/pages/ImagePhone.vue";
import FlashSales from "@/components/home-page/FlashSales.vue";

import CustomButton from "@/components/base/CustomButton.vue";
import BestSellingProduct from "@/components/home-page/BestSellingProduct.vue";
import Menu from "@/components/menu/Menu.vue";
import ExploreProduct from "@/components/home-page/ExploreProduct.vue";
import CustomService from "@/components/base/CustomService.vue";
import ProductTypeDao from "@/daos/ProductTypeDao.js";


export default {
  name: 'HomePage',
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
      productType_ParentId_Null: [],

      productType_By_ParentId_2: [],

      productType_By_ParentId_3: [],

      isMenu_2_Open: false,

      isMenu_3_Open: false,

      //temp
      temp_typeMenu_1: null,

      temp_typeMenu_2: null,

    }
  },

  created() {
    this.getListProductType_That_ParentId_Null();
  },

  methods: {
    //method lấy ds
    async getListProductType_That_ParentId_Null(){
      //let productType_ParentId_Null = null;
      const productTypeDao = new ProductTypeDao();

      try{
        this.productType_ParentId_Null = await productTypeDao.getListProductType_That_ParentId_Null();
        console.log('List Product Type that parentId null:', this.productType_ParentId_Null);
      }catch (error){
        //chuyen trang 404
        //alert(error);
        this.$router.replace({ path: '/screen-404' }).catch((error) => { console.error('Error navigating :', error); });
      }
    },

    // getTypeProductById_Menu_1(productTypeId) {
    //   return this.productType_ParentId_Null.filter(p => p.productTypeId === productTypeId).typeProduct;
    // },

    // async toggleChild_2_Menu(productTypeId, typeProductMenu) {
    //   // this.isMenu_3_Open = false;
    //   // this.isMenu_2_Open = false;
    //
    //   const productTypeDao = new ProductTypeDao();
    //   try {
    //     const productType = await productTypeDao.getListProductType_By_ParentId(productTypeId);
    //     console.log('List Product Type that parentId after clicked:', productType);
    //     console.log(typeProductMenu);
    //
    //     if(this.isMenu_2_Open === true && (this.isMenu_3_Open === true || this.isMenu_3_Open === false)){
    //
    //       let typeProduct = await productTypeDao.getTypeProductById(productTypeId);
    //       console.log('Name menu:',typeProduct);
    //
    //       if(this.temp_typeMenu_1 === typeProduct){
    //         //dong menu hien tai
    //         this.isMenu_3_Open = false;
    //         this.isMenu_2_Open = false;
    //       }else{
    //         //dong menu cu mo menu moi
    //         this.temp_typeMenu_1 = typeProductMenu;
    //         this.isMenu_3_Open = false;
    //         this.isMenu_2_Open = false;
    //
    //         this.productType_By_ParentId_2 = productType;
    //         //dong tang 2 va tang 3
    //         this.isMenu_2_Open = !this.isMenu_2_Open;
    //       }
    //     }else{
    //       this.temp_typeMenu_1 = typeProductMenu;
    //       this.productType_By_ParentId_2 = productType;
    //       //dong tang 2 va tang 3
    //       this.isMenu_2_Open = !this.isMenu_2_Open;
    //     }
    //   } catch (error) {
    //     alert(error);
    //   }
    // },

    async toggleChild_2_Menu(productTypeId, typeProductMenu) {
      // this.isMenu_3_Open = false;
      // this.isMenu_2_Open = false;

      const productTypeDao = new ProductTypeDao();
      try {
        const productType = await productTypeDao.getListProductType_By_ParentId(productTypeId);
        console.log('List Product Type that parentId after clicked:', productType);
        console.log(typeProductMenu);

        if(this.isMenu_2_Open === true && (this.isMenu_3_Open === true || this.isMenu_3_Open === false)){

          let typeProduct = await productTypeDao.getTypeProductById(productTypeId);
          console.log('Name menu:',typeProduct);

          if(this.temp_typeMenu_1 === typeProduct){
            //dong menu hien tai
            this.isMenu_3_Open = false;
            this.isMenu_2_Open = false;
          }else{
            //dong menu cu mo menu moi
            this.temp_typeMenu_1 = typeProductMenu;
            this.isMenu_3_Open = false;
            this.isMenu_2_Open = false;

            this.productType_By_ParentId_2 = productType;
            //dong tang 2 va tang 3
            this.isMenu_2_Open = !this.isMenu_2_Open;
          }
        }else{
          this.temp_typeMenu_1 = typeProductMenu;
          this.productType_By_ParentId_2 = productType;
          //dong tang 2 va tang 3
          this.isMenu_2_Open = !this.isMenu_2_Open;
        }
      } catch (error) {
        alert(error);
      }
    },

    async toggleChild_3_Menu(productTypeId, typeProductMenu){
      const productTypeDao = new ProductTypeDao();
      try {
        const productType = await productTypeDao.getListProductType_By_ParentId(productTypeId);
        console.log('List Product Type that parentId after clicked:', productType);

        if(this.isMenu_3_Open === true){
          let typeProduct = await productTypeDao.getTypeProductById(productTypeId);
          console.log('Name menu:',typeProduct);

          if(this.temp_typeMenu_2 === typeProduct){
            this.isMenu_3_Open = false;
          }else{
            this.isMenu_3_Open = false;

            this.temp_typeMenu_2 = typeProductMenu;
            this.productType_By_ParentId_3 = productType;
            this.isMenu_3_Open = !this.isMenu_3_Open;
          }
        }else{
          //mở menu
          this.temp_typeMenu_2 = typeProductMenu;
          this.productType_By_ParentId_3 = productType;
          this.isMenu_3_Open = !this.isMenu_3_Open;
        }
      } catch (error) {
        alert(error);
      }
    },


  }
}


</script>

<template>
  <div class="container">
    <Header style="height: 50px"/>
    <Menu style="height: 200px"/>
    <main class="main">
      <div class="container-menu-image">
        <div class="container-menu">
          <div class="container-menu-child-1">
              <div v-for="p in productType_ParentId_Null" :key="p.productTypeId">
                <div class="btn-group">
                  <button
                      type="button"
                      @click="toggleChild_2_Menu(p.productTypeId, p.typeProduct)"
                      class="btn btn-secondary dropdown-toggle menu-item"
                  >
                    {{ p.typeProduct }}
                  </button>
                </div>
            </div>
          </div>
          <div class="container-menu-child-2" v-if="isMenu_2_Open">
              <div v-for="p in productType_By_ParentId_2" :key="p.productTypeId" style="background-color: white">
                <div class="btn-group">
                  <button
                      type="button"
                      @click="toggleChild_3_Menu(p.productTypeId, p.typeProduct)"
                      :class="['btn', 'btn-secondary', 'menu-item-2', { 'dropdown-toggle': p.typeProduct !== 'Others' }]"
                  >
                    {{ p.typeProduct }}
                  </button>
                </div>
              </div>
          </div>
          <div class="container-menu-child-3" v-if="isMenu_3_Open">
            <div v-for="p in productType_By_ParentId_3" :key="p.productTypeId" style="background-color: white">
              <div class="btn-group">
                <button
                    type="button"
                    @click=""
                    class="btn btn-secondary menu-item-3"
                >
                  {{ p.typeProduct }}
                </button>
              </div>
            </div>
          </div>
        </div>
        <div class="container-image-advertise">
          <ImagePhone/>
        </div>
      </div>

<!--      <div class="btn-group dropend">-->
<!--        <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">-->
<!--          Dropright-->
<!--        </button>-->
<!--        <ul class="dropdown-menu">-->
<!--          <li>Hello</li>-->
<!--          <li>Hi</li>-->
<!--        </ul>-->
<!--      </div>-->


      <div class="view-product-slide">
        <FlashSales/>
      </div>

      <hr style="margin-left: 9%; margin-top: 8%; width: 82%;">

      <div class="view-product-slide">
        <BestSellingProduct/>
      </div>
      <div class="view-product-slide">
        <ExploreProduct/>
      </div>
      <div class="view-service">
        <CustomService/>
      </div>
      <div class="view-arrow-up">
        <a class="view-circle-arrow-up" href="">
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

</style>