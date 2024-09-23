<script>
import Header from "@/components/header-footer-menu/Header.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import Menu from "@/components/menu/Menu.vue";

export default {
  name: 'LoginScreen',
  components:{
    CustomButton,
    Header,
    Footer,
    Menu,
  },
  data(){
    return{
      emailPhoneNumber: null,
      password: null,
      //error notify
      errorEmailPhoneNumber: '',
      errorPassword: '',
    }
  },
  mounted(){
    this.validateEmailAndPhoneNumber();
    this.validatePassword();
  },
  methods: {
    validateEmailAndPhoneNumber(){
      if(!this.emailPhoneNumber){
        this.errorEmailPhoneNumber='';
      }else {
        if (this.emailPhoneNumber.length > 11) {
          if (!/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.emailPhoneNumber)) {
            this.errorEmailPhoneNumber = 'Please enter valid email or phone number.';
          } else {
            this.errorEmailPhoneNumber = '';
          }
        } else {
          if (!isNumeric(this.emailPhoneNumber.trim())) {
            if (!/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.emailPhoneNumber)) {
              this.errorEmailPhoneNumber = 'Please enter valid email or phone number.';
            } else {
              this.errorEmailPhoneNumber = '';
            }
          } else {
            if(this.emailPhoneNumber.trim().length < 10){
              this.errorEmailPhoneNumber = 'Please enter phone number 10 digits.';
            }else{
              this.errorEmailPhoneNumber = '';
            }
          }
        }
      }
    },
    validatePassword(){
      if(!this.password){
        this.errorPassword='';
      }else{
        if (!/^(?=.*[0-9])(?=.*[^a-zA-Z0-9])(?!.*\s).{6,15}$/.test(this.password)) {
          this.errorPassword = 'Password includes letter(s), digit(s), special character(s), no space, from 6-20 characters. Ex: khan123@.';
        } else {
          this.errorPassword = '';
        }
      }
    },
    validateNullAndLogin(){
      if(!this.name){
        this.errorName='Please enter name.';
      }

      if(!this.emailPhoneNumber){
        this.errorEmailPhoneNumber='Please enter email or phone number.';
      }

      if(!this.password){
        this.errorPassword='Please enter password';
      }
    },
    preventPaste(event) {
      event.preventDefault();
    },
  }
}
function isNumeric (str){
  return /^\d+$/.test(str);
}
</script>
<template>
  <div class="container">
    <Header style="height: 50px"/>
    <Menu style="height: 200px"/>
    <main class="main">
      <div class="container-child">
        <div style="
                    display: flex;
                    justify-content: center;
                    height: 85%;
                    width: 127%;
                    margin-top: 70px;
                    padding: 0;
                    margin-left: 25px;">
          <div class="column-1">
            <img src="@/assets/images/image-cart-phone.png" alt="Image Background" style="width: 100%; height: 100%;"/>
          </div>
          <div class="column-2">
            <div class="column-2-1">
              <p style="font-size: 36px; text-align: left">Log in to Exclusive</p>
              <p style="font-size: 16px; text-align: left; margin-top: 24px;">Enter your details below</p>
              <div style="width: 100%; height: 275px; margin-top: 30px;">
                <input @input="validateEmailAndPhoneNumber()" type="text" maxlength=50 v-model="emailPhoneNumber" class="form-control input" placeholder="Email or Phone Number">
                <span class="span-error">{{errorEmailPhoneNumber}}</span>

                <input @input="validatePassword()" type="password" maxlength=20 v-model="password" class="form-control input" placeholder="Password" @paste="preventPaste($event)">
                <span class="span-error">{{errorPassword}}</span>

                <div style="display: flex; height: 18%; margin-top: 30px; align-items: center">
                  <CustomButton @click="validateNullAndLogin()" style="width: 35%; height: 100%;" text-button="Log in"/>
                  <router-link to="" style="font-size: 16px; text-align: right; margin-left: 100px; color: #DB4444; text-decoration: none;" class="forget-password">Forget Password?</router-link>
                </div>
              </div>

            </div>
          </div>
        </div>
      </div>
    </main>
    <Footer style="height: 1500px;"/>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/input-white';
@import '@/assets/container';

.container-child{
  display: flex;
  flex-direction: column;
  height: 820px;
  width: 1338px;
  margin-top: -20px;
  padding: 0;
  margin-left: -25px;
}

.column-1 {
  flex: 3; /* Tỷ lệ 3 */
  padding-left: 20px;

}
.column-2 {
  flex: 2; /* Tỷ lệ 2 */
  padding: 20px;

}

@media (max-width: 600px) {
  .container {
    flex-direction: column; /* Chuyển thành cột trên màn hình hẹp */
  }
}

.column-2-1{
  width: 60%;
  height: 70%;
  margin-left: 100px;
  margin-top: 100px;
}

.forget-password:hover{
  background-color: transparent;
}

</style>