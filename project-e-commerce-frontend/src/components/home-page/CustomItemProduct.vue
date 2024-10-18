<script>
import CustomDiscount from "@/components/base/CustomDiscount.vue";

export default {
  name: 'CustomItemProduct',
  // props: {
  //   // Có thể thêm props nếu cần
  // },
  components: {
    CustomDiscount,
  },

  props:['product'],

  methods: {
    getPrice_By_Discount(price, numberOfDiscount){
      return price - price*(numberOfDiscount / 100);
    }
  },
  data(){
    return{

    }
  }
}


</script>

<template>
  <div class="item-product">
    <div style="background-color: #F5F5F5; border-radius: 4px;" class="item-product-image">
        <div class="item-image">
          <div class="item-discount-image">
            <CustomDiscount :text-number-discount=product.number_of_discounts*(-1) background-color="#DB4444" v-if="product.number_of_discounts > 0"/>
            <div class="image-content">
              <img :src="product.image" style="width: 100%; height: 100%" alt="product-image"/>
            </div>
          </div>
          <div class="item-heart-eye">
            <div class="item-heart">
              <svg fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" style="width: 24px; height: 24px; cursor: pointer;">
                <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
              </svg>
            </div>
            <div class="item-eye">
              <svg fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16" style="width: 24px; height: 24px; cursor: pointer;">
                <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8M1.173 8a13 13 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5s3.879 1.168 5.168 2.457A13 13 0 0 1 14.828 8q-.086.13-.195.288c-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5s-3.879-1.168-5.168-2.457A13 13 0 0 1 1.172 8z"/>
                <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5M4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0"/>
              </svg>
            </div>
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
<!--          <p style="font-size: 16px; color: #DB4444; font-weight: 600;" v-if="((product.price!==0) && (product.price!==null))">${{product.price}}</p>-->
          <p style="font-size: 16px; color: #DB4444; font-weight: 600;" v-if="product.price !== 0 ">${{ getPrice_By_Discount(product.price, product.number_of_discounts)}}</p>
          <p style="font-size: 16px; color: #7c7c7c; font-weight: 600; margin-left: 5%; text-decoration: line-through;" v-if="product.number_of_discounts > 0">${{ product.price }}</p>
<!--          text-decoration: line-through; #7c7c7c margin-left: 5%;-->
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
  width: 100%;

  height: 250px;

  padding-top: 10px;
  padding-right: 10px;
  display: flex;
}

.item-discount-image{
  width: 100%;
  height: 100%;
  flex: 4.5;
  padding-left: 10px;

}

.image-content{
  width: 90%;
  height: 80%;
  margin-left: 10%;
}

.item-heart-eye{
  flex: 1;
  width: 50px;
  height: 100px;
  display: flex; /* Sử dụng Flexbox cho phần tử này */
  flex-direction: column; /* Đặt hướng thành cột */
  justify-content: space-between; /* Dạt lên và xuống */
}

.item-heart, .item-eye{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 45px;
  height: 45px;
  border-radius: 50px;
  background-color: white;
  margin-left: 10%;
}

.add-to-cart {
  /* Ẩn nút khi không hover */
  /* Để đè lên các phần tử khác */
  display: none;
  border-bottom-left-radius: 4px;
  border-bottom-right-radius: 4px;
  color: white;
  background-color: black;
  border: none;
  /* Con trỏ chuột dạng tay */
  cursor: pointer;
  font-weight: 400;
  font-size: 16px;
  width: 100%;
  height: 100%;
  /* Đảm bảo nút nằm trên các phần tử khác */
}

.item-product-image:hover .add-to-cart {
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