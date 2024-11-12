<script>
// import dataCart from "@/pages/cart/dataCart.js";
import Header from "@/components/header-footer-menu/Header.vue";
import Menu from "@/components/menu/Menu.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import CustomInputCouponCode from "@/components/base/CustomInputCouponCode.vue";
import Screen404 from "@/pages/Screen404.vue";
import CustomButtonWhite from "@/components/base/CustomButtonWhite.vue";
import RouterDao from "@/daos/RouterDao.js";
import CartDao from "@/daos/CartDao.js";


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
    this.getDataCart_From_LocalStorage_Or_API();
    this.get_Subtotal();
  },

  methods: {
    getDataCart_From_Data_Js(){
      //this.carts = dataCart;
    },

    get_Subtotal(){
      this.subtotal = Math.round(this.carts.reduce((accumulator, item) => {
        return accumulator + (item.price * item.quantityBuy);
      }, 0));
    },

    getDataCart_From_LocalStorage_Or_API(){
      const routerDao = new RouterDao();
      if(routerDao.getEmailPhoneNumberFromLocalStorage() === null){
        //get from Local Storage
        const cartDao = new CartDao();
        if(cartDao.getListCartLocalStorage()){
          this.carts = cartDao.getListCartLocalStorage();
        }

      }else{
        //get from database

      }
    },

    //quantity
    handleIncrease(cartIndex){
      const cartDao = new CartDao();
      if (cartIndex !== -1) {
        this.carts[cartIndex].quantityBuy += 1;
        //tang xong get lai\
        let listCarts = cartDao.getListCartLocalStorage();
        cartDao.updateQuantityLocalStorage_In_Cart(listCarts,this.carts[cartIndex]);
        this.get_Subtotal();
      } else {
        console.log("Not found!");
      }
    },

    handleReduce(cartIndex){
      const cartDao = new CartDao();
      if (cartIndex !== null) {
        if(this.carts[cartIndex].quantityBuy > 1){
          this.carts[cartIndex].quantityBuy -= 1;
          //giam xong get lai
          let listCarts = cartDao.getListCartLocalStorage();
          cartDao.updateQuantityLocalStorage_In_Cart(listCarts,this.carts[cartIndex]);
          this.get_Subtotal();
        }
      } else {
        console.log("Not found!");
      }
    },

    handleDeleteItemCart(index){
      const cartDao = new CartDao();
      if(this.carts.length > 0){
        cartDao.deleteCartItemLocalStorage(this.carts,index);
        window.location.reload();
      }
    },

    handleReturnToShop(){
      const routerDao = new RouterDao();

      if(!routerDao.getEmailPhoneNumberFromLocalStorage()){

        routerDao.saveRouterPathToSessionStorage("/home-page");

        this.$router.push({
          path: '/home-page',
        }).catch((error) => {
          console.error('Error navigating :', error);
          alert(error);
        });
      }else{
        routerDao.saveRouterPathToSessionStorage("/home-page-with-account");

        this.$router.push({
          path: '/home-page-with-account',
          query: {
            emailPhoneHomePage: routerDao.getEmailPhoneNumberFromLocalStorage().trim(),
          }
        }).catch((error) => {
          console.error('Error navigating :', error);
          alert(error);
        });
      }
    },

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
                <th style="text-align: center">Subtotal</th>
                <th style="text-align: right; padding-right: 3%">Action</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(c, index) in carts">
                <td style="padding-left: 3%;">
                  <div style=" display: flex; gap: 20px; height: 50%; width: 100%;">
                    <div style="flex: 1;">
                      <img :src="c.image"
                           alt="image product" class="style-image-product-cart">
                    </div>
                    <p style="flex: 4; height: 100%; align-content: center; font-size: 16px;">
                      {{c.name}} {{c.size}}
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
                           @click="handleIncrease(index)"
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
                           @click="handleReduce(index)"
                           viewBox="0 0 512.02 319.26">
                        <path d="M5.9 48.96 48.97 5.89c7.86-7.86 20.73-7.84 28.56 0l178.48 178.48L434.5 5.89c7.86-7.86 20.74-7.82 28.56 0l43.07 43.07c7.83 7.84 7.83 20.72 0 28.56l-192.41 192.4-.36.37-43.07 43.07c-7.83 7.82-20.7 7.86-28.56 0l-43.07-43.07-.36-.37L5.9 77.52c-7.87-7.86-7.87-20.7 0-28.56z"/>
                      </svg>
                    </div>
                  </div>
                </td>
                <td style="text-align: center">${{Math.round(c.price*c.quantityBuy)}}</td>
                <td style="text-align: right; padding-right: 3.75%" class="style-trash-delete">
                  <svg @click="handleDeleteItemCart(index)" xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
                    <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
                  </svg>
                </td>
              </tr>
            </tbody>
          </table>
          <div class="style-view-empty-item-cart" v-if="carts.length === 0">
            <svg width="159" height="175" viewBox="0 0 159 175" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M2.5 46.3692L2.50023 172.5H156.5V46.3692M2.5 46.3692H156.5M2.5 46.3692L13.5 28.8511H20.032M156.5 46.3692L144.278 28.8511H137.755" stroke="black" stroke-width="4"></path>
              <path d="M55.4375 72.6129V97.1685H103.562V72.6129" stroke="black" stroke-width="4"></path>
              <path d="M93.918 23.1935V9.22327L139.234 12.5849L136.688 46.6809" stroke="black" stroke-width="4"></path>
              <path d="M53.5456 46.6808L51.5576 28.9124L101.653 21.709L106.725 46.6808" stroke="black" stroke-width="4"></path>
              <path d="M21.5691 45.7204L18.0596 7.04951L71.8723 2.5L74.2119 25.2476" stroke="black" stroke-width="4"></path>
            </svg>
            <h5>Cart is empty</h5>
            <CustomButton @click="handleReturnToShop()" class="style-button-continue-buy" text-button="Continue buy"/>
          </div>
          <div class="custom-return-update-button">
            <CustomButtonWhite @click="handleReturnToShop()" text-button="Return To Shop" style="width: 220px" />
            <CustomButtonWhite text-button="Update Cart" style="width: 200px" />
          </div>
        </div>
        <div class="custom-amount-paid">
          <div class="custom-input-discount">
            <div class="input-code-coupon">
              <input type="text" maxlength=50 class="style-input-coupon" placeholder="Coupon Code" v-model="couponCode" >
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
  height: 35px;
  border-radius: 4px;
  border: solid gray;
  margin-left: 105px;
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
  height: 50px;
  object-fit: contain;
}

.style-input-coupon{
  width: 100%;
  height: 100%;
  border: solid 1.5px;
  border-radius: 4px;
  font-size: 16px;
  padding-left: 20px;
}

.style-view-empty-item-cart{
  height: 350px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.style-button-continue-buy{
  height: 50px;
  width: 300px;
}

.style-trash-delete{
  color: red;
  cursor: pointer;
}
</style>