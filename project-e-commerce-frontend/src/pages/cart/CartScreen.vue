<script>
import dataCart from "@/pages/cart/dataCart.js";
import Header from "@/components/header-footer-menu/Header.vue";
import Menu from "@/components/menu/Menu.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import CustomInputCouponCode from "@/components/base/CustomInputCouponCode.vue";
import Screen404 from "@/pages/Screen404.vue";
import CustomButtonWhite from "@/components/base/CustomButtonWhite.vue";


export default {
  name: "CartScreen",
  components: {CustomButtonWhite, Screen404, CustomInputCouponCode, CustomButton, Footer, Menu, Header},
  //props
  data(){
    return {
      carts: [],
      couponCode: null,

      //cartToCheckOut: [],

      //data gia
      //quantityBuy: 1,
      subtotal: 0,
    }
  },

  created() {
    this.getDataCart_From_Data_Js();
    this.getDataCart_From_LocalStorage();
    this.get_Subtotal();
  },

  methods: {
    getDataCart_From_Data_Js(){
      this.carts = dataCart;
    },

    get_Subtotal(){
      this.subtotal = Math.round(this.carts.reduce((accumulator, item) => {
        return accumulator + (item.price * item.quantityBuy);
      }, 0));
    },

    getDataCart_From_LocalStorage(){

    },

    //quantity
    handleIncrease(productId){
      const cartIndex = this.carts.findIndex(item => item.productId === productId);
      //findIndex giúp đồng bộ data
      // Item exists, update its quantity
      if (cartIndex !== -1) {
        this.carts[cartIndex].quantityBuy += 1;
        //tang xong get lai
        this.get_Subtotal();
      } else {
        console.log("Not found!");
      }
    },

    handleReduce(productId){
      const cartIndex = this.carts.findIndex(item => item.productId === productId);
      if (cartIndex !== null) {
        if(this.carts[cartIndex].quantityBuy > 1)
          this.carts[cartIndex].quantityBuy -= 1;
          //giam xong get lai
          this.get_Subtotal();
      } else {
        console.log("Not found!");
      }
    },
    // handleIncrease(){
    //   this.quantityBuy++;
    // },
    //
    // handleReduce(){
    //   if(this.quantityBuy > 1){
    //     this.quantityBuy--;
    //   }
    // },

  }
}
</script>

<template>
  <div class="container">
    <Header style="height: 50px"/>
    <Menu style="height: 200px"/>
    <main class="style-main-cart">
      <section class="custom-section">
        <div class="custom-list-cart">
          <table>
            <thead>
              <tr>
                <th style="padding-left: 3%">Product</th>
                <th style="text-align: center">Price</th>
                <th style="text-align: center">Quantity</th>
                <th style="text-align: right; padding-right: 3%">Subtotal</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(c) in carts">
                <td style="padding-left: 3%;">
                  <div style=" display: flex; gap: 20px; height: 50%; width: 100%;">
                    <div style="flex: 1;">
                      <img :src="c.image"
                           alt="image product" class="style-image-product-cart">
                    </div>
                    <p style="flex: 4; height: 100%; align-content: center; font-size: 16px;">
                      {{c.name}}
                    </p>
                  </div>
                </td>
                <td style="text-align: center">${{c.price}}</td>
                <td style="text-align: center;">
                  <!-- <input type="number" class="style-input-number-quantity" value=1 min=1 />-->
                  <div class="style-input-number-quantity">
                    <div class="style-quantity-buy">
                      {{c.quantityBuy}}
                    </div>
                    <div class="style-arrow-up-down">
                      <svg xmlns="http://www.w3.org/2000/svg"
                           shape-rendering="geometricPrecision"
                           text-rendering="geometricPrecision"
                           image-rendering="optimizeQuality"
                           fill-rule="evenodd"
                           clip-rule="evenodd"
                           style="transform: rotate(180deg); margin-top: 3px;"
                           class="style-arrow-quantity"
                           @click="handleIncrease(c.productId)"
                           viewBox="0 0 512.02 319.26">
                        <path d="M5.9 48.96 48.97 5.89c7.86-7.86 20.73-7.84 28.56 0l178.48 178.48L434.5 5.89c7.86-7.86 20.74-7.82 28.56 0l43.07 43.07c7.83 7.84 7.83 20.72 0 28.56l-192.41 192.4-.36.37-43.07 43.07c-7.83 7.82-20.7 7.86-28.56 0l-43.07-43.07-.36-.37L5.9 77.52c-7.87-7.86-7.87-20.7 0-28.56z"/>
                      </svg>
                      <svg xmlns="http://www.w3.org/2000/svg"
                           shape-rendering="geometricPrecision"
                           text-rendering="geometricPrecision"
                           image-rendering="optimizeQuality"
                           fill-rule="evenodd"
                           clip-rule="evenodd"
                           class="style-arrow-quantity"
                           style="margin-bottom: 3px;"
                           @click="handleReduce(c.productId)"
                           viewBox="0 0 512.02 319.26">
                        <path d="M5.9 48.96 48.97 5.89c7.86-7.86 20.73-7.84 28.56 0l178.48 178.48L434.5 5.89c7.86-7.86 20.74-7.82 28.56 0l43.07 43.07c7.83 7.84 7.83 20.72 0 28.56l-192.41 192.4-.36.37-43.07 43.07c-7.83 7.82-20.7 7.86-28.56 0l-43.07-43.07-.36-.37L5.9 77.52c-7.87-7.86-7.87-20.7 0-28.56z"/>
                      </svg>
                    </div>
                  </div>
                </td>
                <td style="text-align: right; padding-right: 4.75%">${{c.price*c.quantityBuy}}</td>
              </tr>
            </tbody>
          </table>
          <div class="custom-return-update-button">
            <CustomButtonWhite text-button="Return To Shop" style="width: 220px" />
            <CustomButtonWhite text-button="Update Cart" style="width: 200px" />
          </div>
        </div>
        <div class="custom-amount-paid">
          <div class="custom-input-discount">
            <div class="input-code-coupon">
              <CustomInputCouponCode :text-coupon-code="couponCode" text-placeholder="Coupon Code"/>
            </div>
            <div class="button-code-coupon">
              <CustomButton class="style-button-discount" text-button="Apply Coupon"/>
            </div>
          </div>
          <div class="custom-calculate-total">
            <div class="container-custom-calculate-total">
              <div class="view-total-price">
                <div class="view-cart-total-letter">
                  <h4 style="font-weight: 500;">Cart Total</h4>
                </div>
                <div class="view-price" style="border-bottom: solid gray; ">
                  <p class="style-text-total-price" style="margin-left: 1px;">Subtotal:</p>
                  <!--cho nay se lam tron so-->
                  <p class="style-text-total-price" style="margin-right: 1px">${{subtotal}}</p>
                </div>
                <div class="view-price" style="border-bottom: solid gray;">
                  <p class="style-text-total-price" style="margin-left: 1px;">Shipping:</p>
                  <p class="style-text-total-price" style="margin-right: 1px">Free</p>
                </div>
                <div class="view-price">
                  <p class="style-text-total-price" style="margin-left: 1px">Total:</p>
                  <p class="style-text-total-price" style="margin-right: 1px">${{subtotal}}</p>
                </div>
              </div>
              <div class="view-button-checkout">
                <CustomButton style="width: 100%; height: 75%;" text-button="Process to checkout"/>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
    <Footer style="height: 1500px;"/>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/container';
.style-main-cart{
  padding: 80px 170px 80px 170px;
  width: 1680px;
}

.custom-section{
  width: 100%;
  height: 100%;
}

.custom-amount-paid{
  height: 350px;
  margin-top: 80px;
  display: flex;
  justify-content: space-between;
}

.custom-calculate-total{
  width: 520px;
  border-radius: 4px;
  border: solid;
  padding: 25px 30px 25px 30px;
}

.container-custom-calculate-total{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.view-total-price{
  flex: 3;
  display: flex;
  flex-direction: column;
}

.view-cart-total-letter{
  flex: 1;
  display: flex;
  align-items: center;
}

.view-price{
  flex: 1;
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
}

.view-button-checkout{
  flex: 1;
  padding: 0 100px 0 100px;
}

.custom-input-discount{
  width: 560px;
  height: 15%;
  display: flex;
  justify-content: space-between;
}

.input-code-coupon{
  width: 310px;
}

.button-code-coupon{
  width: 220px;
}

.style-button-discount{
  width: 100%;
  height: 100%;
}

.style-text-total-price{
  font-weight: 495;
  font-size: 18px;
  margin-top: 10px;
}


//table
table {
  width: 100%;
  border-collapse: separate;
  /* Sử dụng border-collapse: separate để áp dụng border-spacing */
  border-spacing: 0 35px;
}

tr{
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  border-radius: 4px;
}

th, td{
  width: 20%;
}

th, td {
  align-content: center;

  font-size: 16px;
  justify-content: space-between;
}

th{
  height: 70px;
}

td{
  height: 100px;
}
//th {
//  //background-color: #f2f2f2;
//}

.custom-return-update-button{
  width: 100%;
  height: 55px;
  display: flex;
  justify-content: space-between;
}

.style-input-number-quantity{
  width: 20%;
  height: 40%;
  border-radius: 4px;
  border: solid gray;
  margin-left: 135px;
  display: flex;
  padding-right: 1px;
}

.style-arrow-quantity{
  width: 40%;
  height: 40%;
  cursor: pointer;
}

.style-arrow-up-down{
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.style-quantity-buy{
  flex: 2;
  font-size: 16px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.style-image-product-cart{
  width: 100%;
  height: 100%;
  object-fit: contain;
}

</style>