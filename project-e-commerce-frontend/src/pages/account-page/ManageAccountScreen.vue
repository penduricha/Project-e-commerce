

<script>
import Header from "@/components/header-footer-menu/Header.vue";
import Menu from "@/components/menu/Menu.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import CustomInputGrey from "@/components/base/CustomInputGrey.vue";
import UserDao from "@/daos/UserDao.js";
import User from "@/models/User.js";

export default{
  name: 'ManageAccountScreen',

  components: {CustomInputGrey, CustomButton, Footer, Menu, Header},

  props: {},

  data() {
    return {
      userManageAccount: null,
      emailPhoneNumberModel: '02190739693',
      nameWelcome: null,

      //disable input,
      disableInputEmail: false,
      disableInputPhoneNumber: false,

      firstName: null,
      lastNameAndMiddleName: null,
      address: null,
      email: null,
      phoneNumber: null,
      spanSuccessPassword: null,

      //password
      currentPassword: null,
      newPassword: null,
      confirmNewPassword: null,
      disableInputCurrentPassword: false,

      //error notify
      errorFirstname: null,
      errorLastname: null,
      errorEmail: null,
      errorPhoneNumber: null,
      errorAddress: null,
      errorPassword: null,

      //error input password,
      errorInputCurrentPassword: false,
      errorInputNewPassword: false,
      errorInputConfirmNewPassword: false,
    }
  },

  mounted() {
    this.getUserFromEmailOrPhoneNumber();
    this.validateFirstName();
    this.validateLastName();
    this.validateEmail();
    this.validatePhoneNumber();
    this.validateAddress();
    this.validateFormatPassword();
    window.addEventListener('beforeunload', this.handleBeforeUnload);
  },

  beforeDestroy() {
    window.removeEventListener('beforeunload', this.handleBeforeUnload);
  },

  methods: {
    async getUserFromEmailOrPhoneNumber(){
      try{
        //gia dinh email or phone number
        const emailPhoneNumber = '02190739693';
        const userDao = new UserDao();
        this.userManageAccount = await userDao.getUserByEmailOrPhoneNumber(emailPhoneNumber);
        console.log('User is: ',this.userManageAccount);
        //console.log(this.userManageAccount._firstName);
        //Gán giá trị từ userManageAccount vào các thuộc tính
        this.firstName = this.userManageAccount.firstName || ''; // Sử dụng || để tránh null
        this.lastNameAndMiddleName = (this.userManageAccount.lastName || '') + ' ' + (this.userManageAccount.middleName || '');
        this.address = this.userManageAccount.address || '';
        this.email = this.userManageAccount.email;
        this.phoneNumber = this.userManageAccount.phoneNumber;
        this.nameWelcome = (this.userManageAccount.lastName || '') + ' '
            + (this.userManageAccount.middleName || '')  + ' '
            + (this.userManageAccount.firstName || '');

        if(this.userManageAccount.email){
          this.disableInputEmail = true;
        }

        if(this.phoneNumber){
          this.disableInputPhoneNumber = true;
        }
      }catch(error){
        alert(error);
        console.error(error);
      }
    },

    preventPaste(event) {
      event.preventDefault();
    },

    // async setToDataScreen(){
    //   const userFromAPI = await this.getUserFromEmailOrPhoneNumber();
    //   this.firstName = userFromAPI.firstName;
    // }

    // async getToFormInput(){
    //   this.firstName = this.userManageAccount._firstName;
    // }

    //validate
    validateFirstName(){
      if(!this.firstName){
        this.errorFirstname='';
      }else{
        if(isFullOfSpaces(this.firstName.trim())){
          this.errorFirstname = '';
        }else{
          if (!isValidVietnameseFirstName(this.firstName.trim())) {
            //!/^[a-zA-Z ]+$/.test(this.name) ||
            this.errorFirstname = 'First name is invalid,.';
          } else {
            this.errorFirstname = '';
          }
        }
      }
    },

    validateLastName(){
      if(!this.lastNameAndMiddleName){
        this.errorLastname='';
      }else{
        if (!isValidVietnameseName(this.lastNameAndMiddleName.trim())) {
          //!/^[a-zA-Z ]+$/.test(this.name) ||
          this.errorLastname = 'Last name is invalid.';
        } else {
          this.errorLastname = '';
        }
      }
    },

    validateEmail(){
      if(!this.email){
        this.errorEmail='';
      }else{
        if(isFullOfSpaces(this.email)){
          this.errorEmail='';
        }else{
          if(!isValidEmail(this.email)){
            this.errorEmail = 'Email is invalid.';
          }else{
            this.errorEmail='';
          }
        }
      }
    },

    validatePhoneNumber(){
      if(!this.phoneNumber){
        this.errorPhoneNumber = '';
      }else{
        if(isFullOfSpaces(this.phoneNumber)){
          this.errorPhoneNumber ='';
        }else{
          if(!isNumeric(this.phoneNumber)){
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
    },

    validateAddress(){

    },

    async validateFormatPassword() {
      if (!this.currentPassword && !this.newPassword && !this.confirmNewPassword) {
        this.errorPassword = '';
        this.errorInputCurrentPassword = false;
        this.errorInputNewPassword = false;
        this.errorInputCurrentPassword = false;
      } else {
        if ((!this.currentPassword && (this.newPassword || this.confirmNewPassword))) {
          this.errorPassword = 'Please enter current password.';
          this.errorInputCurrentPassword = true;
        } else {
          //ép user nhập mk hiện tại\
          this.errorInputCurrentPassword = false;
          if (!isValidPassword(this.currentPassword)) {
            this.errorInputCurrentPassword = true;
            this.errorPassword = 'Current password includes letter(s), digit(s), special character(s), no space, from 6-20 characters. Ex: ben123@.';
          } else {
            this.errorInputCurrentPassword = false;
            //hashing sha512
            const passwordHashed = await sha512(this.currentPassword);
            const passwordAccount = this.userManageAccount.password;
            if (!comparePassword(passwordHashed, passwordAccount)) {
              this.errorPassword = 'Password does not match with password account.';
              this.errorInputCurrentPassword = true;
            } else {
              //khi current password dung
              this.errorInputCurrentPassword = false;
              this.disableInputCurrentPassword = true;
              this.spanSuccessPassword = 'Correct Password.';
              if(!this.newPassword && this.confirmNewPassword){
                this.errorInputNewPassword = true;
                this.errorPassword = 'Please enter new password.';
              }else{
                this.errorInputNewPassword = false;
                if (!isValidPassword(this.newPassword) && this.newPassword){
                  this.errorInputNewPassword = true;
                  this.errorPassword = 'New password includes letter(s), digit(s), special character(s), no space, from 6-20 characters. Ex: ben123@.';
                }else{
                  this.errorInputNewPassword = false;
                  const newPasswordHashed = await sha512(this.newPassword);

                  if (comparePassword(newPasswordHashed, passwordAccount) && this.newPassword) {
                    this.errorInputNewPassword = true;
                    this.errorPassword = 'New password does not match with current password.';
                  }else{
                    this.errorInputNewPassword = false;
                    this.errorPassword = '';
                    if (!isValidPassword(this.confirmNewPassword) && this.confirmNewPassword){
                      this.errorInputConfirmNewPassword = true;
                      this.errorPassword = 'Confirm new password includes letter(s), digit(s), special character(s), no space, from 6-20 characters. Ex: ben123@.';
                    }else{
                      this.errorInputConfirmNewPassword = false;
                      this.errorPassword = '';
                      if((!comparePassword(this.newPassword, this.confirmNewPassword)) && this.newPassword && this.confirmNewPassword){
                        this.errorInputConfirmNewPassword = true;
                        this.errorPassword = 'Confirm new password does not match with new password.';
                      }else{
                        this.errorPassword = '';
                        this.errorInputConfirmNewPassword = false;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    },

    validateNullInput(){
      if(!this.firstName){
        this.errorFirstname = 'Please enter first name.';
      }

      if(!this.lastNameAndMiddleName){
        this.errorLastname = 'Please enter last name.';
      }

      //email và address ko can

      // if(!this.currentPassword){
      //   this.errorPassword = 'Please enter current password.';
      //   this.errorInputCurrentPassword = true;
      // }
      //
      // if(this.disableInputCurrentPassword && !this.newPassword){
      //   this.errorPassword = 'Please enter new password.';
      //   this.errorInputNewPassword = true;
      // }
      //
      // if(this.newPassword && !this.confirmNewPassword && !this.errorPassword){
      //   this.errorPassword = 'Please enter confirm new password.';
      //   this.errorInputConfirmNewPassword = true;
      // }
    },

    async updateAccount() {
      this.validateNullInput();
      if (!this.errorFirstname && !this.errorLastname && !this.errorEmail && !this.errorPhoneNumber) {
        //B1 xu li tk email tồn tại
        if (this.email && this.email !== this.userManageAccount.email) {
          try {
            let userExist = await getUserByEmailOrPhoneNumber(this.email.trim());
            //console.log('User by email to check exist: ', userExist);
            //userExist la Object ko phai la Map
            if(userExist._email){
              this.errorEmail = 'Account already exist';
            }
          } catch (error) {
            alert(error);
            console.error(error);
            this.$router.replace({path: '/screen-404'}).catch((error) => {
              console.error('Error navigating :', error);
            });
          }
        }

        if (this.phoneNumber && this.phoneNumber !== this.userManageAccount.phoneNumber) {
          try {
            let userExist = await getUserByEmailOrPhoneNumber(this.phoneNumber.trim());
            //console.log('User by email to check exist: ', userExist);
            //userExist la Object ko phai la Map
            if(userExist._phoneNumber){
              this.errorPhoneNumber = 'Account already exist';
            }
          } catch (error) {
            alert(error);
            console.error(error);
            this.$router.replace({path: '/screen-404'}).catch((error) => {
              console.error('Error navigating :', error);
            });
          }
        }

        //constructor(email, phoneNumber, password, firstName, lastName, middleName, address)
        const email = this.email ? this.email.trim() : null;
        const phoneNumber = this.phoneNumber ? this.phoneNumber.trim() : null;
        // const passwordHashed = await sha512(this.)
        const firstName = this.firstName.trim().replace(/\s+/g, ' ')
            .split(' ')
            .map(word => word.charAt(0).toUpperCase() + word.slice(1).toLowerCase())
            .join(' ');

        const lastNameAndMiddleName = this.lastNameAndMiddleName.trim().replace(/\s+/g, ' ')
            .split(' ')
            .map(word => word.charAt(0).toUpperCase() + word.slice(1).toLowerCase())
            .join(' ');
        const nameParts = lastNameAndMiddleName.split(' ');

        const lastName = nameParts[0];
        const middleName = nameParts.slice(1).join(' ');

        console.log('New email: ',email);
        console.log('New phoneNumber: ',phoneNumber);
        console.log('New first name: ',firstName);
        console.log('New lastName: ', lastName);
        console.log('New middleName: ', middleName);

        if(!this.newPassword && !this.confirmNewPassword){
          //ko doi mk
          if(!this.currentPassword){
            this.errorInputCurrentPassword = true;
            this.errorPassword = 'Please enter your current password to confirm.';
          }else{
            if(!this.errorPassword && !this.newPassword && !this.confirmNewPassword){
              //alert("Ok update no change password");
            }
          }
        }else{
          if(!this.errorPassword){
            alert("Ok update and change password");
          }
        }
      }
    }

  },

  computed: {
    inputEmailClass(){
      return (this.disableInputEmail)
          ? 'disabled'
          : 'enable';
    },

    inputButtonClass(){
      return (this.disableInputPhoneNumber)
          ? 'disabled'
          : 'enable';
    }
  },
}

//cac function khac

function isNumeric (str){
  return /^\d+$/.test(str);
}

async function getUserByEmailOrPhoneNumber(emailPhoneNumber){
  let user = await UserDao.getUserByEmailOrPhoneNumber(emailPhoneNumber);
  if(user!==null){
    return new User(user.email, user.phoneNumber, user.password, user.firstName, user.lastName, user.middleName, user.address);
  }else{
    return null;
  }
}

function comparePassword(password1, password2){
  return password1 === password2;
}

async function sha512(password) {
  let buf = await crypto.subtle.digest("SHA-512", new TextEncoder("utf-8").encode(password));
  return Array.prototype.map.call(new Uint8Array(buf), x => (('00' + x.toString(16)).slice(-2))).join('');
}

function isValidVietnameseFirstName(name) {
  // Remove accents from Vietnamese characters
  const removeAscent = (str) => {
    if (str === null || str === undefined) return str;
    return str.normalize('NFD').replace(/[\u0300-\u036f]/g, '');
  };

  // Regex pattern for Vietnamese names without spaces
  const regex = /^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$/;

  // Check if name is valid and doesn't contain spaces
  const processedName = removeAscent(name);
  return regex.test(processedName) && !/\s/.test(processedName);
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

function isValidEmail(email){
  return /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email.trim());
}

function isValidPassword(password){
  return /^(?=.*[0-9])(?=.*[^a-zA-Z0-9])(?!.*\s).{6,20}$/.test(password);
}
</script>

<template>
  <div class="container">
    <Header style="height: 50px"/>
    <Menu style="height: 200px"/>
    <main class="style-manage-account">
      <section class="container-manage-account">
        <div class="view-home-account-welcome">
          <label style="font-size: 17px;">
            <span style="color: grey; cursor: pointer">Home /</span> My Account
          </label>
          <label style="font-size: 17px;">
            Welcome! <span style="color: #DB4444">{{nameWelcome}}</span>
          </label>
        </div>
        <div class="view-body-manage-account">
          <aside class="view-menu-manage-account">
            <label class="style-title-menu">Manage My Account</label>
            <label class="style-menu" style="color: #DB4444">My Profile</label>
            <label class="style-title-menu">My Orders</label>
            <label class="style-menu">My Returns</label>
            <label class="style-menu">My Cancellations</label>
          </aside>
          <div class="view-form-manage-account">
            <div class="view-input-button-manage-account">
              <h4 style="color: #DB4444; font-size: 25px;">Edit Your Profile</h4>
              <div class="style-row-form">
                <div class="style-input">
                  <label class="style-label-input">
                    First Name
                  </label>
                  <!-- form-control is-invalid: do-->
                  <input type="text"
                         class="style-input-grey style-input-form form-control"
                         v-model="firstName"
                         maxlength=30
                         :class="{ 'is-invalid': errorFirstname }"
                         @input="validateFirstName"
                         :disabled="false"
                  />
                  <span class="span-error">{{errorFirstname}}</span>
                </div>
                <div class="style-input">
                  <label class="style-label-input">Last Name</label>
                  <input type="text"
                         class="style-input-grey style-input-form form-control"
                         v-model="lastNameAndMiddleName"
                         maxlength=100
                         :class="{ 'is-invalid': errorLastname }"
                         @input="validateLastName"
                         :disabled="false"
                  />
                  <span class="span-error">{{errorLastname}}</span>
                </div>
              </div>
              <div class="style-row-form">
                <div class="style-input">
                  <label class="style-label-input">Email</label>
                  <!-- rang buoc disable va invalid-->
                  <input type="text"
                         class="style-input-form form-control"
                         v-model="email"
                         @input="validateEmail"
                         maxlength=50
                         :class="[
                             'style-input-grey-enable-disable',inputEmailClass,
                             { 'is-invalid': errorEmail }
                         ]"
                         :disabled="disableInputEmail"
                  />
                  <span class="span-error">{{errorEmail}}</span>
                </div>
                <div class="style-input">
                  <label class="style-label-input">Address</label>
                  <input type="text"
                         class="style-input-grey style-input-form form-control"
                         v-model="address"
                         @input="validateAddress"
                         :class="{ 'is-invalid': errorAddress }"
                         maxlength=255
                         :disabled="false"
                  />
                  <span class="span-error">{{errorAddress}}</span>
                </div>
              </div>
              <div class="style-row-form">
                <div class="style-input">
                  <label class="style-label-input">Phone number</label>
                  <input type="text"
                         class="style-input-form form-control"
                         :class="['style-input-grey-enable-disable', inputButtonClass,  { 'is-invalid': errorPhoneNumber }]"
                         v-model="phoneNumber"
                         @input="validatePhoneNumber"
                         maxlength=11
                         :disabled="phoneNumber !== null"
                  />
                  <!--:disabled="phoneNumber !== null"-->
                  <span class="span-error">{{errorPhoneNumber}}</span>
                </div>
                <div class="style-input">
                  <!--no tag-->
                </div>
              </div>
              <div class="view-password-input">
                <label class="style-label-input">Password Changes</label>
                <!--is-valid-->
                <input type="password" @input="validateFormatPassword" @paste="preventPaste($event)" v-model="currentPassword" maxlength=20 placeholder="Current Password" class="style-input-password style-input-grey form-control"
                      :disabled="disableInputCurrentPassword"
                      :class="[{ 'is-valid': disableInputCurrentPassword }, { 'is-invalid': errorInputCurrentPassword }]"
                />
                <span class="text-success" style="margin-bottom: 5px;">{{spanSuccessPassword}}</span>
                <input type="password" @input="validateFormatPassword" @paste="preventPaste($event)" v-model="newPassword" maxlength=20 placeholder="New Password"
                       class="style-input-password style-input-grey form-control"
                       :class="{ 'is-invalid': errorInputNewPassword }"
                />
                <input type="password" @input="validateFormatPassword" @paste="preventPaste($event)" v-model="confirmNewPassword" maxlength=20 placeholder="Confirm New Password"
                       class="style-input-password style-input-grey form-control"
                       :class="{ 'is-invalid': errorInputConfirmNewPassword }"
                />
                <span class="span-error">{{errorPassword}}</span>

              </div>
              <div class="style-view-button-save-change">
                <CustomButton @click="updateAccount()" text-button="Save Changes" class="style-button-save-changes" />
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
@import '@/components/style-scss-input/input-grey';
@import '@/assets/input-white';

.style-manage-account{
  //trên, phải, dưới, trái
  padding: 30px 100px 30px 100px;
  width: 1680px;
  height: 950px;
}

.container-manage-account{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.view-home-account-welcome{
  width: 100%;
  display: flex;
  justify-content: space-between ;
}

.view-body-manage-account{
  width: 100%;
  height: 90%;
  display: flex;
}

.view-menu-manage-account{
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.view-form-manage-account{
  flex: 3;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  //trên, phải, dưới, trái
  padding: 40px 125px 40px 125px;
}

.style-title-menu{
  font-size: 17px;
  font-weight: 500;
}

.style-menu{
  color: gray;
  font-size: 17px;
  margin-left: 10%;
}

.view-input-button-manage-account{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.style-row-form{
  height: 100px;
  display: flex;
  gap: 7.5%;
}

.style-input{
  flex: 1;
  display: flex;
  flex-direction: column;
}

.style-button-save-changes{
  height: 50px;
  width: 22.25%;
}

.style-label-input{
  font-size: 17px;
  font-weight: 400;
}

.style-input-form{
  width: 100%;
  height: 48px;
}

.view-password-input{
  height: 250px;
  display: flex;
  flex-direction: column;
}

.style-input-password{
  width: 100%;
  height: 48px;
  margin-bottom: 10px;
}

.style-view-button-save-change{
  height: 50px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}



</style>