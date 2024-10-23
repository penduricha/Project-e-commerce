<script>
import CustomDiscount from "@/components/base/CustomDiscount.vue";
import Product from "@/models/Product.js";
import CustomNewLabel from "@/components/base/CustomNewLabel.vue";

export default {
  name: 'CustomItemProduct',
  // props: {
  //   // Có thể thêm props nếu cần
  // },
  components: {
    CustomNewLabel,
    CustomDiscount,
  },

  props:['product'],

  methods: {
    getPrice_By_Discount(price, numberOfDiscount){
      let priceView = price - price*(numberOfDiscount / 100);
      if(isZero(getDecimalPart(priceView))){
        return priceView;
      }else
      {
        return priceView.toFixed(2);
      }
      ///return  price - price*(numberOfDiscount / 100);
    },

    getProduct(){
      return new Product(this.product.productId,
          this.product.name,
          this.product.image,
          this.product.price,
          this.product.number_of_discounts,
          this.product.name_event_purchasing
      );
    }
  },

  data(){
    return{

    }
  }
}

function getDecimalPart(num) {
  num = num.toFixed(2);
  const integerPart = Math.floor(num);
  const decimalPart = num - integerPart;
  const decimalAsInt = Math.round(decimalPart * 10000);
  return decimalAsInt.toString().padStart(2, '0');
}

function isZero(number){
  return Number(number) === 0;
}

</script>

<template>

  <div class="item-product">
    <div style="background-color: #F5F5F5; border-radius: 4px; width: auto" >
        <CustomDiscount :text-number-discount="getProduct()._numberOfDiscount*(-1)"
                        background-color="#DB4444"
                        v-if="getProduct()._numberOfDiscount > 0" style=" margin-top: 10px; margin-left: 15px; position: absolute"/>
        <CustomNewLabel text="NEW"
                      background-color="#00FF66"
                      v-if="getProduct()._nameEventPurchasing === 'New'" style=" margin-top: 10px; margin-left: 15px; position: absolute"/>
        <div class="item-heart-eye">
          <div class="item-heart">
            <svg fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" style="width: 20px; height: 20px; cursor: pointer;">
              <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
            </svg>
          </div>
          <div class="item-eye">
            <svg fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16" style="width: 20px; height: 20px; cursor: pointer;">
              <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8M1.173 8a13 13 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5s3.879 1.168 5.168 2.457A13 13 0 0 1 14.828 8q-.086.13-.195.288c-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5s-3.879-1.168-5.168-2.457A13 13 0 0 1 1.172 8z"/>
              <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5M4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0"/>
            </svg>
          </div>
        </div>
        <div class="item-image">
            <div class="image-content">
              <img :src="getProduct()._image" style="width: 100%; height: 100%;" alt="product-image"/>
            </div>
      </div>
      <div style="height: 50px;" class="item-cart">
        <button @click="" class="add-to-cart">Add to Cart</button>
      </div>
    </div>
    <div class="item-content">
      <div class="content-product">
        <p style="font-size: 16px; font-weight: 500">{{product.name}}</p>
        <div class="content-price">
          <p style="font-size: 16px; color: #DB4444; font-weight: 600;" v-if="getProduct()._price !== 0 ">${{ getPrice_By_Discount(getProduct()._price, getProduct()._numberOfDiscount)}}</p>
          <p style="font-size: 16px; color: #7c7c7c; font-weight: 600; margin-left: 5%; text-decoration: line-through;" v-if="getProduct()._numberOfDiscount > 0">${{ getProduct()._price }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.item-product{
  height: 100%;
  width: 100%;
}

.item-image{
  width: auto;
  height: 250px;
  padding-top: 10px;
  display: flex;
  justify-content: center;
}

.image-content{
  width: auto;
  height: 100%;
}

.item-heart-eye{
  position: absolute;
  width: 50px;
  height: 80px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-left: 250px;
  margin-top: 10px;

}

.item-heart, .item-eye{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 34px;
  height: 34px;
  border-radius: 50px;
  background-color: white;
  margin-left: 10%;
}

.add-to-cart {
  display: none;
  border-bottom-left-radius: 4px;
  border-bottom-right-radius: 4px;
  color: white;
  background-color: black;
  border: none;
  cursor: pointer;
  font-weight: 400;
  font-size: 16px;
  width: 100%;
  height: 100%;
}

.item-product:hover .add-to-cart {
  display: block;
}


.item-content{
  width: 100%;
  height: 102px;
  padding-top: 5%;
  padding-right: 25%;
}

.content-product{
  width: 100%;
  height: 100%;
}

.content-price{
  width: 100%;
  display: flex;
}

</style>