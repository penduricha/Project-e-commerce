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
            Welcome! <span style="color: #DB4444">Md Rim</span>
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
                  <label class="style-label-input">First Name</label>
                  <CustomInputGrey type-input="text" maxlength=30 :text-input="firstName" class="style-input-form" :is-disable=false />
                </div>
                <div class="style-input">
                  <label class="style-label-input">Last Name</label>
                  <CustomInputGrey type-input="text" maxlength=60 :text-input="lastNameAndMiddleName" class="style-input-form" :is-disable=false />
                </div>
              </div>
              <div class="style-row-form">
                <div class="style-input">
                  <label class="style-label-input">Email</label>
                  <!-- scss no-->
                  <CustomInputGrey type-input="text" maxlength=50 class="style-input-form" :is-disable=true />
                </div>
                <div class="style-input">
                  <label class="style-label-input">Address</label>
                  <CustomInputGrey type-input="text" maxlength=255 class="style-input-form" :is-disable=false />
                </div>
              </div>
              <div class="style-row-form">
                <div class="style-input">
                  <!-- scss no-->
                  <label class="style-label-input">Phone number</label>
                  <CustomInputGrey type-input="text" maxlength=11 class="style-input-form" :is-disable=true />
                </div>
                <div class="style-input">
                  <!--no tag-->
                </div>
              </div>
              <div class="view-password-input">
                <label class="style-label-input">Password Changes</label>
                <!--                <input type="password" maxlength=20 placeholder="Current Passwod"-->
                <CustomInputGrey type-input="password" maxlength=20 text-placeholder="Current Password" class="style-input-password" />
                <CustomInputGrey type-input="password" maxlength=20 text-placeholder="New Password" class="style-input-password" />
                <CustomInputGrey type-input="password" maxlength=20 text-placeholder="Confirm New Password" class="style-input-password" />
              </div>
              <div style="flex: 0.6; display: flex; justify-content: flex-end; align-items: center;">
                <CustomButton text-button="Save Changes" class="style-button-save-changes" />
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
    <Footer style="height: 1500px;"/>
  </div>
</template>

<script>
import Header from "@/components/header-footer-menu/Header.vue";
import Menu from "@/components/menu/Menu.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import CustomButton from "@/components/base/CustomButton.vue";
import CustomInputGrey from "@/components/base/CustomInputGrey.vue";
import User from "@/models/User.js";
import UserDao from "@/daos/UserDao.js";

export default{
  name: 'ManageAccountScreen',

  components: {CustomInputGrey, CustomButton, Footer, Menu, Header},

  props: {},

  async data() {
    return {
      userManageAccount: null,
      emailPhoneNumberModel: '02190739693',

      firstName: null,
      lastNameAndMiddleName: null,
      address: null,
    }
  },

  created() {
    this.getUserFromEmailOrPhoneNumber();
    //this.getToFormInput();
  },

  methods: {
    async getUserFromEmailOrPhoneNumber(){
      //gia dinh
      const emailPhoneNumber = '02190739693';
      const userDao = new UserDao();
      let userManageAccount = await userDao.getUserByEmailOrPhoneNumber(emailPhoneNumber);
      this.userManageAccount = userManageAccount;
      console.log('User is: ',userManageAccount);
      //console.log(this.userManageAccount._firstName);
      // Gán giá trị từ userManageAccount vào các thuộc tính
      this.firstName = this.userManageAccount.firstName || ''; // Sử dụng || để tránh null
      this.lastNameAndMiddleName = (this.userManageAccount.lastName || '') + ' ' + (this.userManageAccount.middleName || '');
      this.address = this.userManageAccount.address || '';
      console.log(this.firstName);
      console.log(this.lastNameAndMiddleName);

    },

    // async getToFormInput(){
    //   this.firstName = this.userManageAccount._firstName;
    // }
  },

  computed: {

  },
}

//cac function khac

function isNumeric (str){
  return /^\d+$/.test(str);
}

// async function getUserByEmailOrPhoneNumber(emailPhoneNumber){
//   let user = await UserDao.getUserByEmailOrPhoneNumber(emailPhoneNumber);
//   if(user!==null){
//     return new User(user.email, user.phoneNumber, user.password, user.firstName, user.lastName, user.middleName, user.address);
//   }else{
//     return null;
//   }
// }

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



<style lang="scss" scoped>
@import '@/assets/container';
.style-manage-account{
  //trên, phải, dưới, trái
  padding: 30px 100px 30px 100px;
  width: 1680px;
  height: 900px;
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
  gap: 17px;
}

.style-row-form{
  flex: 1;
  display: flex;
  gap: 7.5%;
}

.style-input{
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.style-button-save-changes{
  height: 100%;
  width: 22.25%;
}

.style-label-input{
  font-size: 17px;
  font-weight: 400;
}

.style-input-form{
  width: 95%;
  height: 60%;
}

.view-password-input{
  flex: 2.75;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.style-input-password{
  width: 100%;
  height: 22%;
}
</style>