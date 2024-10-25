<script>
//npm install vue-star-rating@2.1.0
import CustomButton from "@/components/base/CustomButton.vue";
import CustomGroupItemSize from "@/pages/view-product-detail/CustomGroupItemSize.vue";
import CustomGroupItemColor from "@/pages/view-product-detail/CustomGroupItemColor.vue";
import StarRating from 'vue-star-rating';
//npm install vue-star-rating@next
export default {
  name: 'ViewProduct',

  components: {
    CustomGroupItemColor,
    CustomGroupItemSize,
    CustomButton,
    StarRating,
  },

  props: ['productId'],

  data(){
    return{
      warehouses_By_ProductId: [],
      countQuantityBuy: 0,

      rating: "No Rating Selected",
      currentRating: "No Rating",
      currentSelectedRating: "No Current Rating",
      boundRating: 3,
    }
  },

  methods: {
    // setRating: function(rating) {
    //   this.rating = "You have Selected: " + rating + " stars";
    // },
    // showCurrentRating: function(rating) {
    //   this.currentRating = (rating === 0) ? this.currentSelectedRating : "Click to select " + rating + " stars"
    // },
    // setCurrentSelectedRating: function(rating) {
    //   this.currentSelectedRating = "You have Selected: " + rating + " stars";
    // }
    handleIncrease(){
      this.countQuantityBuy += 1;
    },

    handleReduced(){
      this.countQuantityBuy -= 1;
    },
  },

}
</script>

<template>
  <div class="container-view-product">
    <div class="view-image-product">
      <div class="view-list-image-color">

      </div>
      <div class="view-image-init">
        <img src="https://raw.githubusercontent.com/penduricha/Image_E_Commerce/refs/heads/master/men's-fashion/hat/normal.png"
             alt="delivery"
             class="style-image-init">
      </div>
    </div>
    <div class="view-information-product">
      <div class="view-information-product-child" style="flex: 3; border-bottom: solid grey">
        <div class="style-name-product" >HV G-92 Gamepad</div>
        <div style="width: 80%; height: 25px; display: flex; margin-top: 5px;">
          <div style="flex: 1; display:inline-block;">
            <star-rating style="margin-top: -5%" :star-size="20" :show-rating="false"></star-rating>
          </div>
          <div style="flex: 1; padding-left: 5px; color: grey; font-size: 14px;">
            (150 Reviews)
          </div>
          <div style="flex: 1; border-left: solid 1px grey; color: #00FF66; padding-left: 20px;">
            In Stock
          </div>
        </div>
        <p class="style-price">$192.00</p>
        <p class="style-description">PlayStation 5 Controller Skin High quality vinyl with air channel adhesive for easy bubble free install & mess free removal Pressure sensitive.</p>
      </div>
      <div class="view-information-product-child">
        <div class="view-item-warehouse" style="border: solid grey; width: 40%; border-radius: 4px; display: flex; margin-top: 4%">
          <button @click="handleReduced()"
                  :disabled="countQuantityBuy <= 0"
                  style="flex: 1; border-right: solid grey;" class="style-button-quantity">
            -
          </button>
          <div style="flex: 1.5; font-weight: 500; display: flex; font-size: 24px; justify-content: center; align-items: center;">
            {{countQuantityBuy}}
          </div>
          <button @click="handleIncrease()" style="flex: 1; border-left: solid grey;" class="style-button-quantity">
            +
          </button>
        </div>
        <div class="view-item-warehouse style-size-color" style="display: flex; align-items: center;">
          Size:
          <CustomGroupItemSize/>
        </div>
        <div class="view-item-warehouse style-size-color" style="display: flex; align-items: center; ">
          Color:
          <CustomGroupItemColor/>
        </div >
        <div class="view-item-warehouse" >
          <button @click="" class="button-add-to-cart">Add To Cart</button>
        </div>
        <div class="view-item-warehouse">
          <CustomButton @click="" style="width: 100%; height: 100%" text-button="Buy Now"/>
        </div>
      </div>
      <div class="view-information-product-child style-service">
        <div class="view-delivery">
          <div class="view-image-delivery">
            <img src="@/assets/image-view-product-detail/icon-delivery.png" alt="delivery" class="style-image">
          </div>
          <div class="view-information-delivery">
              <p class="style-label-service">Free Delivery</p>
              <p class="style-description-service">Enter your postal code for Delivery Availability</p>
          </div>
        </div>
        <div class="view-return">
          <div class="view-image-return">
            <img src="@/assets/image-view-product-detail/icon-return.png" alt="delivery" class="style-image">
          </div>
          <div class="view-information-return">
            <p class="style-label-service">Return Delivery</p>
            <p class="style-description-service" style="text-decoration: none">
              Free 30 Days Delivery Returns. <u style="font-size: 14px; font-weight: 550;">Details</u>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss">

.container-view-product{
  width: 100%;
  height: 100%;
  display: flex;
}

.view-image-product{
  flex: 2;
  display: flex;
  padding-right: 5%;
  border-radius: 4px;
}

.view-list-image-color{
  width: 100%;
  height: 100%;
  flex: 1;
}

.view-image-init{
  width: 100%;
  height: 100%;
  flex: 3;
  background-color: #F5F5F5;
  display: flex;
  justify-content: center;
  align-items: center;
}

.view-information-product{
  flex: 1;
  display: flex;
  flex-direction: column;
}

.view-information-product-child{
  flex: 5;
  display: flex;
  flex-direction: column;
  padding-bottom: 3%;
}

.style-name-product{
  font-weight: 600;
  font-size: 24px;
}

.style-price{
  font-size: 24px;
}

.style-description{
  font-size: 14px;
  width: 75%;
}

.style-service{
  flex: 3;
  display: flex;
  flex-direction: column;
}

.view-delivery{
  flex: 1;
  border-top: solid grey;
  border-left: solid grey;
  border-right: solid grey;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  display: flex;
}

.view-return{
  flex: 1;
  border: solid grey;
  border-bottom-left-radius: 4px;
  border-bottom-right-radius: 4px;
  display: flex;
}

.view-image-delivery, .view-image-return{
  flex: 1;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.view-information-delivery, .view-information-return {
  flex: 4;
  width: 100%;
  height: 100%;
}

.style-image{
  width: auto;
  height: auto;
}

.style-label-service{
  font-size: 18px;
  font-weight: 550;
  margin-top: 5%;
}

.style-description-service{
  font-size: 14px;
  font-weight: 550;
  text-decoration: underline;
  margin-top: -5%;
}

.style-image-init{
  width: 80%;
  height: 80%;
}

.view-item-warehouse{
  flex: 1;
  margin-top: 2%;
}

.button-add-to-cart{
  color: white;
  background-color: black;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 400;
  font-size: 16px;
  width: 100%;
  height: 100%;
}

.style-size-color{
  font-size: 20px;
}

.style-button-quantity{
  cursor: pointer;
  font-size: 30px;
  display: flex;
  justify-content: center;
  align-items: center
}
</style>