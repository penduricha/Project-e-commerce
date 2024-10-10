<script>
import Header from "@/components/header-footer-menu/Header.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import UserDAO from "@/daos/UserDAO.js";
import CustomButton from "@/components/base/CustomButton.vue";
import Menu from "@/components/menu/Menu.vue";
import User from "@/models/User.js";
import ModalSuccess from "@/components/modal/ModalSuccess.vue";


export default {

  name: 'SignupScreen',
  components:{
    ModalSuccess,
    Header,
    Footer,
    Menu,
    CustomButton,
  },

  data(){
    return{
      name: null,
      emailPhoneNumber: null,
      password: null,
      //error notify
      errorName: '',
      errorEmailPhoneNumber: '',
      errorPassword: '',
    }
  },

  mounted(){
    this.validateName();
    this.validateEmailAndPhoneNumber();
    this.validatePassword();
  },

  methods: {
    validateName(){
      if(!this.name){
        this.errorName='';
      }else{
        if (!isValidVietnameseName(this.name)) {
          //!/^[a-zA-Z ]+$/.test(this.name) ||
          this.errorName = 'Name is invalid.';
        } else {
          this.errorName = '';
        }
      }
    },
    //chung ta phai trim() chuoi sau do moi xet tinh valid
    validateEmailAndPhoneNumber(){
      if(!this.emailPhoneNumber){
        this.errorEmailPhoneNumber='';
      }else {
        if(!isFullOfSpaces(this.emailPhoneNumber))
        {
          if (this.emailPhoneNumber.trim().length > 11) {
            if (!/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.emailPhoneNumber.trim())) {
              this.errorEmailPhoneNumber = 'Please enter valid email or phone number.';
            } else {
              this.errorEmailPhoneNumber = '';
            }
          } else {
            if (!isNumeric(this.emailPhoneNumber.trim())) {
              if (!/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.emailPhoneNumber.trim())) {
                this.errorEmailPhoneNumber = 'Please enter valid email or phone number.';
              } else {
                this.errorEmailPhoneNumber = '';
              }
            } else {
              if(this.emailPhoneNumber.trim().length < 10){
                this.errorEmailPhoneNumber = 'Please enter phone number 10 digits or 11 digits.';
              }else{
                this.errorEmailPhoneNumber = '';
              }
            }
          }
        }
      }
    },

    validatePassword(){
      if(!this.password){
        this.errorPassword='';
      }else{
        if (!/^(?=.*[0-9])(?=.*[^a-zA-Z0-9])(?!.*\s).{6,20}$/.test(this.password)) {
          this.errorPassword = 'Password includes letter(s), digit(s), special character(s), no space, from 6-20 characters. Ex: khan123@.';
        } else {
          this.errorPassword = '';
        }
      }
    },

    async validateNullAndCreateAccount() {
      if (!this.name) {
        this.errorName = 'Please enter name.';
      }

      if (!this.emailPhoneNumber || this.emailPhoneNumber.trim() ==="") {
        this.errorEmailPhoneNumber = 'Please enter email or phone number.';
      }else{
        try{
          let userExist = await getUserByEmailOrPhoneNumber(this.emailPhoneNumber.trim());
          console.log('User by email or phone number to check exist: ',userExist);

          if(userExist._email !== null && userExist._phoneNumber !== null){
            this.errorEmailPhoneNumber='';
          }else{
            this.errorEmailPhoneNumber = 'Account already exists.';
          }
        }catch(error){
          console.error(error);
          this.$router.replace({ path: '/screen-404' }).catch((error) => { console.error('Error navigating :', error); });
        }
      }

      if (!this.password) {
        this.errorPassword = 'Please enter password';
      }

      if(!this.errorEmailPhoneNumber && !this.errorPassword && !this.errorName){
        let name = this.name.trim().replace(/\s+/g, ' ')
            .split(' ')
            .map(word => word.charAt(0).toUpperCase() + word.slice(1).toLowerCase())
            .join(' ');
        // Tách thành mảng từ
        // Định dạng từng từ
        // Xoá khoảng trắng thừa
        let passwordHashed = await sha512(this.password.trim());
        let address = null;
        let emailOrPhoneNumber = this.emailPhoneNumber.trim();

        let nameParts = name.split(' ');

        // Gán các phần tên vào các biến "An"
        let firstName = nameParts[nameParts.length - 1]; //
        let middleName = nameParts.length > 2 ? nameParts.slice(1, -1).join(' ') : '';
        let lastName = nameParts[0];

        console.log(`Name: ${name}, Password: ${passwordHashed}, Address: ${address}, Email or Phone number ${emailOrPhoneNumber}`);
        console.log(`Firstname: ${firstName}, Middle name: ${middleName}, Last name: ${lastName}, Password: ${passwordHashed}, Address: ${address}, Email or Phone number ${emailOrPhoneNumber}`);

        // constructor(email, phoneNumber, password, firstName, lastName, middleName, address)
        let user = null;
        if (emailOrPhoneNumber.includes('@')) {
          user=new User(emailOrPhoneNumber,null, passwordHashed, firstName, lastName, middleName,address);
        } else {
          user=new User(null,emailOrPhoneNumber, passwordHashed, firstName, lastName, middleName,address);
        }

        let resultCreateAccount = await UserDAO.createAccount(user);

        if(resultCreateAccount === 1){
          this.$refs.modalSuccess.openModal();
        }else{
          //alert('Register Fail, please try again.');
          this.$router.replace({ path: '/screen-404' }).catch((error) => { console.error('Error navigating :', error); });
        }
      }
      //Mo modal
      //this.$refs.modalSuccess.openModal();
    },

    handleContinue(){
      this.$router.replace({ path: '/login-screen' }).catch((error) => { console.error('Error navigating :', error); });
    },

    //lock paste
    preventPaste(event) {
      event.preventDefault();
    },
  }
}

//cac function khac

function isNumeric (str){
  return /^\d+$/.test(str);
}

async function getUserByEmailOrPhoneNumber(emailPhoneNumber){
  let user = await UserDAO.getUserByEmailOrPhoneNumber(emailPhoneNumber);
  if(user!==null){
    return new User(user.email, user.phoneNumber, user.password, user.firstName, user.lastName, user.middleName, user.address);
  }else{
    return null;
  }
}

async function sha512(password) {
  let buf = await crypto.subtle.digest("SHA-512", new TextEncoder("utf-8").encode(password));
  return Array.prototype.map.call(new Uint8Array(buf), x => (('00' + x.toString(16)).slice(-2))).join('');
}

function isValidVietnameseName(name) {
  // Remove accents from Vietnamese characters
  const removeAscent = (str) => {
    if (str === null || str === undefined) return str;
    return str.normalize('NFD').replace(/[\u0300-\u036f]/g, '');
  };
  // Regex pattern for Vietnamese names
  const regex = /^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s]+$/;
  return regex.test(removeAscent(name));
}

function isFullOfSpaces(s) {
  for (let char of s) {
    if (char !== ' ') {
      return false;
    }
  }
  return true;
}
</script>

<template>
  <div class="container">
    <ModalSuccess ref="modalSuccess" @handleContinue="handleContinue()" text-success="Register successfully"/>
    <Header style="height: 50px"/>
    <Menu style="height: 200px"/>
    <main class="main">
      <div class="container-child">
        <div class="main-content">
          <div class="column-left">
            <img src="@/assets/images/image-cart-phone.png" alt="Image Background" style="width: 100%; height: 100%"/>
          </div>
          <div class="column-right">
            <div class="view-create-account">
              <p style="font-size: 36px; text-align: left">Create an account</p>
              <p style="font-size: 16px; text-align: left; margin-top: 24px;">Enter your details below</p>
              <div style="width: 100%; height: 275px; margin-top: 30px;">
                <input type="text" maxlength=120 v-model="name" class="form-control input" placeholder="Name" @input="validateName()">
                <span class="span-error">{{errorName}}</span>

                <input type="text" maxlength=50 v-model="emailPhoneNumber" class="form-control input" placeholder="Email or Phone Number" @input="validateEmailAndPhoneNumber()">
                <span class="span-error">{{errorEmailPhoneNumber}}</span>

                <input type="password" maxlength=20 v-model="password" class="form-control input" placeholder="Password" @input="validatePassword()" @paste="preventPaste($event)">
                <span class="span-error">{{errorPassword}}</span>
                <CustomButton @click="validateNullAndCreateAccount()" style="width: 100%; height: 20%; margin-top: 30px" text-button="Create Account"/>
                <div style="display: flex; margin-top: 10px;">
                  <p style="font-size: 16px; text-align: left; margin-top: 3px;">Already have account?</p>
                  <div style="height: 50%; border-bottom: solid 1px; width: 12.75%; padding: 0; margin-left: 15px; margin-top: 3px;">
                    <router-link to="/login-screen" style="font-size: 16px; text-align: left;  color: black; text-decoration: none;" class="login">Log in</router-link>
                  </div>
                </div>
              </div>
              <!--Truyen tham so props cho CustomButton-->
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
@import '@/assets/style-page-little.css';

.column-left {
  flex: 3;
  padding-left: 20px;
}

.column-right {
  flex: 2;
  padding: 20px;
}

@media (max-width: 600px) {
  .container {
    flex-direction: column;
  }
}

.view-create-account{
  width: 60%;
  height: 70%;
  margin-left: 100px;
  margin-top: 100px;
}

.login:hover{
  background-color: transparent;
}
</style>