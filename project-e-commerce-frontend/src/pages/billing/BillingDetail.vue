<script>
import dataCart from "@/pages/cart/dataCart.js";
import {defineComponent} from "vue";
import Header from "@/components/header-footer-menu/Header.vue";
import Menu from "@/components/menu/Menu.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";

export default defineComponent({
  components: {Footer, Menu, Header},
  name: 'BillingDetail,',

  created() {
    this.getDataOrder_From_Data_Js();
  },

  data(){
    return{
      orderDetail: [],


    }
  },

  methods: {
    getDataOrder_From_Data_Js(){
      this.orderDetail = dataCart;
      console.log('Order Details: ', this.orderDetail);
    },
  }

})
</script>

<template>
  <div class="container">
    <Header style="height: 50px"/>
    <Menu style="height: 200px"/>
    <main class="style-main-billing-detail">
      <h3>Billing Details</h3>
      <section class="custom-section">
        <div class="container-input-bill">
          <div class="custom-label-and-input">
            <label class="style-label">First name<span class="style-star-required">*</span></label>
            <input type="text" maxlength=90 class="style-input-grey form-control style-input-bill">

          </div>
          <div class="custom-label-and-input">
            <label class="style-label">Company Name</label>
            <input type="text" maxlength=255 class="style-input-grey form-control style-input-bill">
            <span class="span-error"></span>
          </div>
          <div class="custom-label-and-input">
            <label class="style-label">Street Address<span class="style-star-required">*</span></label>
            <input type="text" maxlength=255 class="style-input-grey form-control style-input-bill">
            <span class="span-error"></span>
          </div>
          <div class="custom-label-and-input">
            <label class="style-label">Apartment, floor, etc. (optional)</label>
            <input type="text" maxlength=50 class="style-input-grey form-control style-input-bill">
            <span class="span-error"></span>
          </div>
          <div class="custom-label-and-input">
            <label class="style-label">Town/City<span class="style-star-required">*</span></label>
            <input type="text" maxlength=100 class="style-input-grey form-control style-input-bill">
            <span class="span-error"></span>
          </div>
          <div class="custom-label-and-input">
            <label class="style-label">Phone Number<span class="style-star-required">*</span></label>
            <input type="text" maxlength=11 class="style-input-grey form-control style-input-bill">
            <span class="span-error"></span>
          </div>
          <div class="custom-label-and-input">
            <label class="style-label">Email<span class="style-star-required">*</span></label>
            <input type="text" maxlength=50 class="style-input-grey form-control style-input-bill">
            <span class="span-error"></span>
          </div>
          <div class="custom-label-and-input container-check-box-save" style="height: 50px;">
            <input type="checkbox" value="save" class="style-checkbox-save">
            <label class="label-checkbox-save">Save this information for faster check-out next time</label>
          </div>
        </div>
        <div class="container-order-detail">
          <div class="container-subtotal">
            <div class="view-price" style="border-bottom: solid gray;">
              <label class="style-label-price">Subtotal:</label>
              <label class="style-label-price">$30</label>
            </div>
            <div class="view-price" style="border-bottom: solid gray;">
              <label class="style-label-price">Shipping:</label>
              <label class="style-label-price">Free</label>
            </div>
            <div class="view-price">
              <label class="style-label-price">Total:</label>
              <label class="style-label-price">$30</label>
            </div>
          </div>
          <div class="container-method-payment">

          </div>
        </div>
      </section>
    </main>
    <Footer style="height: 1500px; margin-top: 100px;"/>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/container';
@import '@/components/style-scss-input/input-grey';
.style-main-billing-detail{
  padding: 70px 180px 70px 180px;
  width: 1680px;
  height: 850px;
}

.custom-section{
  width: 100%;
  height: 100%;
  display: flex;
  gap: 200px;
  margin-top: 30px;
}

.container-input-bill{
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.container-order-detail{
  flex: 1.5;
  border: solid;
}

.custom-label-and-input{
  width: 100%;
  height: 150px;
}

.style-label{
  font-size: 17px;
  color: grey;
}

.style-star-required{
  font-size: 17px;
  color: #DB4444;
  opacity: 0.5;
}

.style-input-bill{
  margin-top: 5px;
}

.style-checkbox-save {
  width: 18px;
  height: 18px;
  cursor: pointer;
}

input[type='checkbox']{
  position: relative;
  background-color: #ccc;
  appearance: none;
  width: 20px;
  height: 20px;
  border-radius: 4px;
}

input[type='checkbox']:checked{
  background-color: #DB4444;
}

input[type='checkbox']::before{
  content: '';
  position: absolute;
  top: 2px;
  left: 6px;
  width: 7px;
  height: 13px;
  border: 2px solid transparent;
  border-left: none;
  border-top: none;
  transform: rotate(45deg) scale(1);
}

input[type='checkbox']:checked:before{
  border-color: #ffffff;
}

.label-checkbox-save{
  font-size: 17px;
  margin-left: 10px;
}

.container-check-box-save{
  display: flex;
  align-items: center;
  justify-content: start;
}

.container-subtotal{
  width: 75%;
  height: 150px;
  display: flex;
  flex-direction: column;
}

.view-price{
  flex: 1;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-left: 5px;
  padding-right: 5px;
}

.style-label-price{
  font-size: 17px;
}

.container-method-payment{
  width: 80%;
  border: solid;
  height: 80px;
  margin-top: 35px;
  display: flex;
  flex-direction: column;
}
</style>