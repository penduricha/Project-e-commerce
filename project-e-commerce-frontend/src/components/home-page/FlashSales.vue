<script>
import './style-view-slidebar.scss';
import Title from "@/components/home-page/Title.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import CustomItemProduct from "@/components/home-page/CustomItemProduct.vue";
import ProductDao from "@/daos/ProductDao.js";


import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
//npm install vue3-carousel
//npm install vuetify@3.7.3
// import Swiper core and required modules
import { Navigation, Pagination, Scrollbar, A11y } from 'swiper/modules';

// Import Swiper styles
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import 'swiper/css/scrollbar';
import RouterDao from "@/daos/RouterDao.js";
export default {
  name:'FlashSales',
  components:{
    CustomButton,
    Title,
    CustomItemProduct,
    Swiper,
    SwiperSlide,
  },


  setup() {


    // const onSwiper = (swiper) => {
    //   console.log(swiper);
    //   mySwiper.value = swiper; // Lưu instance vào ref
    // };
    //
    // const onSlideChange = () => {
    //   console.log('slide change');
    // };

    const onSwiper = (swiper) => {
      console.log(swiper);
    };

    const onSlideChange = () => {
      console.log('slide change');
    };

    return {
      onSwiper,
      onSlideChange,
      modules: [Navigation, Pagination, Scrollbar, A11y],
    };

    //const slideCount = 1;
    // Số lượng slide sẽ lướt mỗi lần

    // const prevSlide = () => {
    //   if (mySwiper.value) {
    //     mySwiper.value.slideTo(mySwiper.value.activeIndex - slideCount);
    //   }
    // };
    //
    // const nextSlide = () => {
    //   if (mySwiper.value) {
    //     mySwiper.value.slideTo(mySwiper.value.activeIndex + slideCount);
    //   }
    // };
    //
    // return {
    //   mySwiper,
    //   onSwiper,
    //   onSlideChange,
    //   prevSlide,
    //   nextSlide,
    // };
  },
  // props:['product'],
  data(){
    return {
      products_Flash_Sales: [],
    }
  },

  created(){
    this.getProductFlashSales_From_API();
  },

  methods: {

    async getProductFlashSales_From_API(){
      const productDao = new ProductDao();
      let products = [];
      try{
        products = await productDao.getFlashSalesProducts();
        console.log('Product Flash Sales: ',products);
        this.products_Flash_Sales = products;
      }catch(e){
        console.error(e);
        alert(e);
      }
    },

    handleViewAll_FlashSale(){
      const routerDao = new RouterDao();
      routerDao.saveRouterPathToSessionStorage("/view-all-flash-sales");
      this.$router.replace({
        path: '/view-all-flash-sales',
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },
  },

  computed: {
    //ham thay doi css
    // flashSalesClass() {
    //   // Check the length of products_Flash_Sales
    //   return this.products_Flash_Sales.length >= 4 ? 'justify-space-between' : 'justify-start';
    // },

    // customItemProductClass(){
    //   return this.products_Flash_Sales.length >= 4 ? 'margin-right-none' : 'margin-right-have';
    // },
    slidesPerGroup () {
      return this.products_Flash_Sales.length % 2 === 0 ? 2 : 1;
    }
  }
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
  <div style="width: 100%; height: 70%">
    <div class="view-title">
      <Title text-time-title="Today's" :text-title="'Flash Sales'"/>
    </div>
    <div class="view-list-product">
      <!--Ap dung computed cho scss-->
      <div class="style-view-flash-sales">,
<!--        <div class="view-arrow" >-->
<!--          <button class="view-circle-arrow" @click="prevSlide" style="background: transparent; border: none;">-->
<!--            <svg class="icon arrow-left" viewBox="0 0 9 16" fill="none" xmlns="http://www.w3.org/2000/svg" style="width: 24px; height: 24px;">-->
<!--              <path d="M7.99976 15L0.999756 8L7.99976 1" stroke="#333" stroke-width="1.2" stroke-linejoin="round">-->
<!--              </path>-->
<!--            </svg>-->
<!--          </button>-->
<!--        </div>-->
        <swiper
            :modules="modules"
            :slides-per-view="4"
            :space-between="60"
            :slides-per-group="slidesPerGroup"
            navigation
            @swiper="onSwiper"
            @slideChange="onSlideChange"
            class="style-swiper"
            :navigation="{ nextEl: '.custom-next', prevEl: '.custom-prev' }"
        >

          <swiper-slide v-for="(product) in products_Flash_Sales" class="list-product margin-right-product">
            <CustomItemProduct :product="product" />
          </swiper-slide>
        </swiper>
<!--        <div class="view-arrow" style="position: absolute; z-index: 10; margin-left: 1450px;">-->
<!--          <button class="view-circle-arrow" @click="nextSlide">-->
<!--            <svg class="icon arrow-right" viewBox="0 0 9 16"-->
<!--                 fill="none" xmlns="http://www.w3.org/2000/svg"-->
<!--                 style="width: 24px; height: 24px;"-->
<!--            >-->
<!--              <path d="M1 15L8 8L0.999999 1" stroke="#333"-->
<!--                    stroke-width="1.2" stroke-linejoin="round">-->
<!--              </path>-->
<!--            </svg>-->
<!--          </button>-->
<!--        </div>-->
      </div>

      <div class="style-button-view-all">
        <CustomButton @click="handleViewAll_FlashSale()" class="button-view-all" text-button="View All Products"/>
      </div>
    </div>
  </div>
</template>

<style lang="scss">
//@function justify-content($condition) {
//  @if $condition < 4 {
//    @return flex-start;
//  } @else {
//    @return space-between;
//  }
//}
//
//.view-product-flash-sales {
//  display: flex;
//  justify-content: justify-content(4);
//}
//@function justify-content($condition) {
//  @if $condition < 4 {
//    @return flex-start;
//  } @else {
//    @return space-between;
//  }
//}
//
//
//.view-margin-item{
//  &.margin-right-none{
//    margin-right: 0;
//  }
//
//  &.margin-right-have{
//    margin-right: 60px;
//  }
//}

//.view-product-flash-sales {
//  display: flex;
//
//  &.justify-start {
//    justify-content: justify-content(3);
//  }
//
//  &.justify-space-between {
//    justify-content: justify-content(4);
//  }
//}
.swiper-button-next {
  margin-top: -70px;
}

.swiper-button-prev{
  margin-top: -70px;
}


.style-swiper{
  display: flex;
  align-items: center;
  width: 100%;
}

.style-view-flash-sales{
  display: flex;
  width: 100%;
  align-content: space-between;
}

.style-button-view-all{
  width: 100%;
  height: 56px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 4%;
}

.button-view-all{
  width: 234px;
  height: 100%;
  text-align: center;
}

.margin-right-product {
  margin-right: 60px;
}

.view-arrow{
  height: 50px;
  align-content: center;
  position: absolute;
  z-index: 10;
  padding-top: 160px;
}

.view-circle-arrow{
  height: 100%;
  width: 50px;
  border-radius: 50px;
  background-color: transparent;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

.custom-prev, .custom-next {
  position: absolute;
  color: red;
  border: none;
  cursor: pointer;
}

.custom-prev {
  left: 10px;
}

.custom-next {
  right: 10px;
}

</style>