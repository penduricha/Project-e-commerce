<script>
import dataCart from "@/pages/cart/dataCart.js";
import {defineComponent} from "vue";
import Header from "@/components/header-footer-menu/Header.vue";
import Menu from "@/components/menu/Menu.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import ValidateForm from "@/validate/ValidateForm.js";

export default defineComponent({
  components: {CustomButton, Footer, Menu, Header},
  name: 'BillingDetail,',

  created() {
    this.getDataOrder_From_Data_Js();
    this.get_Total_Price();
  },

  mounted() {
    this.validateFirstName();
    this.validateEmail();
  },

  data(){
    return{
      orderDetail: [],
      couponCode: null,
      methodPayment: null,
      totalPrice: 0,

      firstName: null,
      email: null,
      phoneNumber: null,

      //error notification
      errorFirstName: null,
      errorEmail: null,
      errorPhoneNumber: null,
    }
  },

  methods: {
    getDataOrder_From_Data_Js(){
      this.orderDetail = dataCart;
      console.log('Order Details: ', this.orderDetail);
    },

    get_Total_Price(){
      if(this.orderDetail.length > 0){
        this.totalPrice = Math.round(this.orderDetail.reduce((accumulator, item) => {
          return accumulator + (item.price * item.quantityBuy);
        }, 0));
      }
    },

    //validate
    validateFirstName(){
      const validate = new ValidateForm();
      if(!this.firstName){
        this.errorFirstName = '';
      }else{
        if(validate.isFullOfSpaces(this.firstName.trim())){
          this.errorFirstName = '';
        }else{
          if (!validate.isValidVietnameseName(this.firstName.trim())) {
            //!/^[a-zA-Z ]+$/.test(this.name) ||
            this.errorFirstName = 'First name is invalid.';
          } else {
            this.errorFirstName = '';
          }
        }
      }
    },

    validateEmail(){
      const validate = new ValidateForm();
      if(!this.email){
        this.errorEmail = '';
      }else{
        if(validate.isFullOfSpaces(this.email)){
          this.errorEmail = '';
        }else{
          if(!validate.isValidEmail(this.email)){
            this.errorEmail = 'Email is invalid.';
          }else{
            this.errorEmail = '';
          }
        }
      }
    },

    validatePhoneNumber(){
      const validate = new ValidateForm();
      if(!this.phoneNumber){
        this.errorPhoneNumber = '';
      }else{
        if(validate.isFullOfSpaces(this.phoneNumber)){
          this.errorPhoneNumber ='';
        }else{
          if(!validate.isNumeric(this.phoneNumber)){
            this.errorPhoneNumber = 'Phone number is invalid.';
          }else{
            //&& this.phoneNumber.length <= 11
            if(this.phoneNumber.length < 10){
              this.errorPhoneNumber = 'Phone number must be 10 or 11 digits.';
            }else{
              this.errorPhoneNumber ='';
            }
          }
        }
      }
    }
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
            <input type="text" maxlength=90 class="style-input-grey form-control style-input-bill"
                   @input="validateFirstName"
                   v-model="firstName"
                   :class="{ 'is-invalid': errorFirstName }"
            >
            <span class="span-error">{{errorFirstName}}</span>
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
            <input type="text" maxlength=11 class="style-input-grey form-control style-input-bill"
                   @input="validatePhoneNumber"
                   v-model="phoneNumber"
                   :class="{ 'is-invalid': errorPhoneNumber }"
            >
            <span class="span-error">{{errorPhoneNumber}}</span>
          </div>
          <div class="custom-label-and-input">
            <label class="style-label">Email<span class="style-star-required">*</span></label>
            <input type="text" maxlength=50 class="style-input-grey form-control style-input-bill"
                   @input="validateEmail"
                   v-model="email"
                   :class="{ 'is-invalid': errorEmail }"
            >
            <span class="span-error">{{errorEmail}}</span>
          </div>
          <div class="custom-label-and-input container-check-box-save" style="height: 50px;">
            <input type="checkbox" value="save" class="style-checkbox-save">
            <label class="label-checkbox-save">Save this information for faster check-out next time</label>
          </div>
        </div>
        <div class="container-order-detail">
          <div class="container-view-order-detail">
            <div class="view-order-detail" v-for="(o) in orderDetail">
              <div class="view-image-product-order-detail">
                <img :src="o.image" alt="Image Product" class="style-image-product-order-detail"/>
              </div>
              <div class="view-name-product">
                <label class="style-label-price">{{o.name}} {{o.size}}</label>
              </div>
              <div class="view-subtotal-product">
                <label class="style-label-price">${{Math.round(o.price*o.quantityBuy)}}</label>
              </div>
            </div>
          </div>
          <div class="container-subtotal">
            <div class="view-price" style="border-bottom: solid gray;">
              <label class="style-label-price">Subtotal:</label>
              <label class="style-label-price">${{totalPrice}}</label>
            </div>
            <div class="view-price" style="border-bottom: solid gray;">
              <label class="style-label-price">Shipping:</label>
              <label class="style-label-price">Free</label>
            </div>
            <div class="view-price">
              <label class="style-label-price">Total:</label>
              <label class="style-label-price">${{totalPrice}}</label>
            </div>
          </div>
          <div class="container-method-payment">
            <div class="style-custom-choose-payment">
              <div class="view-radio-label-payment">
                <input type="radio" value="Bank" v-model="methodPayment" class="style-radio-choose-payment" id="changeColor">
                <label class="style-label-payment" style="margin-left: 15px;" for="changeColor">Bank</label>
              </div>
              <div class="view-image-bank">
                <img src="./image-bank/b-kas.png" alt="bank bk" class="style-image-bank">
                <img src="./image-bank/visa.png" alt="bank visa" class="style-image-bank">
                <img src="./image-bank/master-card.png" alt="bank master card" class="style-image-bank">
                <img src="./image-bank/bank-india.png" alt="bank india" class="style-image-bank">
              </div>
            </div>
            <div class="style-custom-choose-payment">
              <div class="view-radio-label-payment-cash-on-delivery">
                <input type="radio" value="Cash on delivery" v-model="methodPayment" class="style-radio-choose-payment" id="changeColor">
                <label class="style-label-payment" style="margin-left: 15px;" for="changeColor">Cash on delivery</label>
              </div>
            </div>
          </div>
          <div class="custom-input-discount" style="height: 50px; width: 540px; margin-top: 25px;">
            <div class="input-code-coupon">
              <input type="text" maxlength=50 class="style-input-coupon style-input-coupon-billing" placeholder="Coupon Code" v-model="couponCode">
            </div>
            <div class="button-code-coupon">
              <CustomButton class="style-button-discount" text-button="Apply Coupon"/>
            </div>
          </div>
          <CustomButton class="style-button-play-order" text-button="Play Order"/>
        </div>

      </section>
    </main>
    <Footer style="height: 1500px; margin-top: 100px;"/>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/container';
@import '@/components/style-scss-input/input-grey';
@import '@/components/style-scss-input/coupon-style';

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
  flex: 0.9;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.container-order-detail{
  flex: 1.5;
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
  width: 65%;
  height: 150px;
  display: flex;
  flex-direction: column;
  margin-top: 25px;
}

.container-view-order-detail{
  width: 65%;
  display: flex;
  flex-direction: column;
  gap: 25px;
}

.view-order-detail{
  width: 100%;
  height: 55px;
  display: flex;
}

.view-image-product-order-detail{
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.view-name-product{
  flex: 5;
  display: flex;
  padding-left: 5%;
  align-items: center;
}

.view-subtotal-product{
  flex: 1.5;
  display: flex;
  align-items: center;
  justify-content: right;
  padding-right: 5px;
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
  width: 65%;
  height: 70px;
  margin-top: 25px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.style-custom-choose-payment{
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.style-radio-choose-payment{
  width: 20px;
  height: 20px;
}

#changeColor {
  accent-color: black;
}

.style-label-payment{
  font-size: 16px;
}

.view-image-bank{
  width: 40%;
  height: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.view-radio-label-payment{
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100%;
}

.view-radio-label-payment-cash-on-delivery{
  display: flex;
  align-items: center;
  width: 100%;
}

.style-image-bank{
  cursor: pointer;
  width: 40px;
  height: 25px;
  object-fit: contain;
}

.style-image-bank:hover{
  border: solid 1px black;
}

.style-input-coupon-billing{
  height: 100%;
  width: 95%;
  border: solid black;
  border-radius: 4px;
  padding-left: 5%;
}

.style-button-play-order{
  margin-top: 25px;
  height: 50px;
  width: 25%;
}

.style-image-product-order-detail{
  width: 80%;
  height: 80%;
  object-fit: contain;
}
</style>