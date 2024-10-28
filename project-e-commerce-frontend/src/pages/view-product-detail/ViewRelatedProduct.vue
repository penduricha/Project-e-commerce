<script>
import Title from "@/components/home-page/Title.vue";

import ProductDao from "@/daos/ProductDao.js";
import CustomButton from "@/components/base/CustomButton.vue";
import CustomItemProduct from "@/components/home-page/CustomItemProduct.vue";
import {Swiper, SwiperSlide} from "swiper/vue";
import {A11y, Navigation, Pagination, Scrollbar} from "swiper/modules";

export default {

  name: 'ViewRelatedProduct',

  components: {
    Swiper, SwiperSlide, CustomItemProduct, CustomButton,
    Title

  },

  props: ['productId'],



  data(){
    return{
      products_related_by_productTypeId: [],
      product: null,
    }
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

  async created() {
    this.product = await this.getProduct_By_ProductId();
    this.products_related_by_productTypeId = await this.getList_Related_Product();
  },

  async mounted(){

  },

  methods: {
    async getProduct_By_ProductId(){
      const productDao = new ProductDao();
      try {
        let product = await productDao.getProduct_By_ProductId(this.productId);
        console.log('Product information: ', product);
        console.log(product.productTypeId);
        console.log(product.productId);
        return product;
      } catch (e) {
        console.error(e);
        alert(e);
        return null;
      }
    },

    async getList_Related_Product(){
      const productDao = new ProductDao();
      try {
        let product = await productDao.getList_Related_Product(this.product.productTypeId, this.productId);
        console.log('List products related:',product);
        return product;
      } catch (e) {
        console.error(e);
        alert(e);
        return null;
      }
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
      return this.products_related_by_productTypeId.length % 2 === 0 ? 2 : 1;
    }
  }

}
</script>

<template>
  <div style="width: 100%; height: 100%; display: flex; flex-direction: column;">
    <div class="view-title">
      <Title text-time-title="Related Item"/>
    </div>
    <div class="view-list-product">
      <div class="style-view-related">,
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

          <swiper-slide v-for="(product) in products_related_by_productTypeId" class="list-product margin-right-product">
            <CustomItemProduct :product="product" />
          </swiper-slide>
        </swiper>
      </div>
    </div>
  </div>
</template>

<style lang="scss">

.view-title{
  width: 100%;
  flex: 1;
  display: flex;
}

.view-list-product{
  width: 100%;
  flex: 11;
}

.style-view-related{
  display: flex;
  width: 100%;
  align-content: space-between;
}

.style-swiper{
  display: flex;
  align-items: center;
  width: 100%;
}

.list-product{
  width: 300px;
  height: 100%;
}

.margin-right-product {
  margin-right: 60px;
}

</style>