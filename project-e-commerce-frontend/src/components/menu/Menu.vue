<script>
import ModalConfirmLogout from "@/components/modal/ModalConfirmLogout.vue";
import ModalNotifyToLogout from "@/components/modal/ModalNotifyToLogout.vue";

import RouterDao from "@/daos/RouterDao.js";
export default{
  name:'Menu',

  data(){
    return{
      searchText: null,
      isDropdownOpenAccount: false,
    }
  },

  components:{
    ModalNotifyToLogout,
    ModalConfirmLogout,
  },

  created() {

  },

  methods:{
    handleSearch(){
      //alert("Searched")
    },

    // handleToSignup(){
    //   this.$router.push('/signup-screen');
    // }
    isActiveRoute(route) {
      return this.$route.path === route;
    },

    showDropdownAccount() {
      this.isDropdownOpenAccount = !this.isDropdownOpenAccount;
    },

    openModalLogout(){
      this.$refs.logoutModal.openModal();
    },

    openModalNotifyLogout(){
      this.$refs.logoutNotifyModal.openModal();
    },

    handleSignup(){
      const routerDao = new RouterDao();

      if(routerDao.getEmailPhoneNumberFromLocalStorage() !== null){
        this.openModalNotifyLogout();
      }else{
        routerDao.saveRouterPathToSessionStorage("/signup-screen");

        this.$router.replace({
          path: '/signup-screen',
        }).catch((error) => {
          console.error('Error navigating :', error);
          alert(error);
        });
      }
    },

    getEmailPhoneNumberFromLocalStorage(){
      const routerDao = new RouterDao();
      return routerDao.getEmailPhoneNumberFromLocalStorage();
    },

    handleLogout(){
      removeEmailPhoneNumber();

      alert("Log out successfully.");

      this.$router.replace({
        path: '/home-page',
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },

    // getLocalStorageRouter(){
    //   return getLocalStorageRouter();
    // },

    handleAbout(){
      //window.location.reload();

      const routerDao = new RouterDao();

      // if(routerDao.getEmailPhoneNumberFromLocalStorage() === null){
      //   routerDao.saveRouterPathToSessionStorage("/about-page");
      //
      //   this.$router.replace({
      //     path: '/about-page',
      //   }).catch((error) => {
      //     console.error('Error navigating :', error);
      //     alert(error);
      //   });
      // }else{
      //   routerDao.saveRouterPathToSessionStorage("/about-page-with-account");
      //
      //   this.$router.replace({
      //     path: '/about-page-with-account',
      //   }).catch((error) => {
      //     console.error('Error navigating :', error);
      //     alert(error);
      //   });
      // }
      routerDao.saveRouterPathToSessionStorage("/about-page");
      this.$router.replace({
                path: '/about-page',
              }).catch((error) => {
                console.error('Error navigating :', error);
                alert(error);
              });
    },

    handleHomePage(){

      const routerDao = new RouterDao();

      if(!routerDao.getEmailPhoneNumberFromLocalStorage()){

        routerDao.saveRouterPathToSessionStorage("/home-page");

        this.$router.replace({
          path: '/home-page',
        }).catch((error) => {
          console.error('Error navigating :', error);
          alert(error);
        });
      }else{
        routerDao.saveRouterPathToSessionStorage("/home-page-with-account");

        this.$router.replace({
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


function removeEmailPhoneNumber(){
  localStorage.removeItem('emailPhoneNumber');
}

</script>

<template>
  <div class="menu">
    <div class="menu-child">
      <div class="menu-child-1">
        <div class="menu-exclusive">
          <p style="font-size: 24px; font-weight: bold; margin-top: 10px;">Exclusive</p>
        </div>
        <div class="menu-navbar">
          <ul class="navbar-nav" style="justify-content: center; align-items: center; margin-top: 10px;">
            <li class="nav-item" :style="{ borderBottom: ((isActiveRoute('/')) || (isActiveRoute('/home-page')) || (isActiveRoute('/home-page-with-account'))) ? 'solid 2px' : 'none' }">
              <button class="btn btn-light nav-link" @click.prevent="handleHomePage()">Home</button>
            </li>
            <li class="nav-item">
              <button class="btn btn-light nav-link" @click.prevent="">Contact</button>
            </li>
            <li class="nav-item"
                :style="{ borderBottom: (isActiveRoute('/about-page') || isActiveRoute('/about-page-with-account')) ? 'solid 2px' : 'none' }">
              <button class="btn btn-light nav-link" @click.prevent="handleAbout()">About</button>
            </li>
            <li class="nav-item"
                :style="{ borderBottom: (isActiveRoute('/signup-screen')) ? 'solid 2px' : 'none' }">
              <button
                  class="btn btn-light nav-link nav-item-signup"
                  @click.prevent="handleSignup()"
              >Sign up</button>
            </li>
          </ul>
        </div>
      </div>
      <div class="menu-search">
        <div class="style-input-search"  :style="{ marginLeft: (!isActiveRoute('/signup-screen') && !isActiveRoute('/login-screen')) ? '-175px' : '0' }" style="padding-right: 10px;">
          <input type="text" placeholder="What are you looking for?" v-model="searchText" maxlength=30 class="style-input-search" style="width: 90%;">
          <svg fill="currentColor" class="bi bi-search" viewBox="0 0 20 20" style="width: 25px; height: 25px; margin-top: 3px;" @click="handleSearch()">
            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
          </svg>
        </div>

        <div style="display: flex; width: 50%; margin-left: 5%; justify-content: center" v-if="((!isActiveRoute('/signup-screen')) && (!isActiveRoute('/login-screen')))">

          <button class="button-icon">
            <svg fill="currentColor" class="bi bi-heart style-icon-menu" viewBox="0 0 16 16">
              <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
            </svg>
          </button>

          <button class="button-icon">
            <svg fill="currentColor" class="bi bi-cart3 style-icon-menu" viewBox="0 0 16 16">
              <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l.84 4.479 9.144-.459L13.89 4zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
            </svg>
          </button>

          <div class="button-icon button-icon-account"
              @click="showDropdownAccount()"
               v-if="(!isActiveRoute('/home-page')) && (!isActiveRoute('/screen-404')) && ( getEmailPhoneNumberFromLocalStorage()!==null )">
            <div style="width: 100%;
                        height: 100%;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        border-radius: 80px;
                        background-color: transparent;
                        border: transparent; ">
              <svg fill="currentColor" class="bi bi-person style-icon-menu style-account-icon" viewBox="0 0 16 16">
                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
              </svg>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- modal logout -->
  <ModalConfirmLogout ref="logoutModal" @handleLogout="handleLogout()" />

  <ModalNotifyToLogout ref="logoutNotifyModal" @handleLogout="handleLogout()" />

  <div class="menu-account-drop" v-if="isDropdownOpenAccount">
    <div class="menu-account-drop-child">
      <div class="item-menu">
        <div class="item-menu-image">
          <svg fill="currentColor" class="bi bi-person style-icon-menu style-account-icon" viewBox="0 0 16 16" style="color: white">
            <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
          </svg>
        </div>
        <div class="item-menu-content">
          <button class="style-item-menu-account">Manage My Account</button>
        </div>
      </div>
      <div class="item-menu">
        <div class="item-menu-image">
          <img src="@/assets/image-menu-account/menu-order-icon.png" class="style-item-image" alt="menu order">
        </div>
        <div class="item-menu-content">
          <button class="style-item-menu-account">My Order</button>
        </div>
      </div>
      <div class="item-menu">
        <div class="item-menu-image">
          <svg fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16" style="color: white; width: 25px; height: 25px;">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
            <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"/>
          </svg>
        </div>
        <div class="item-menu-content">
          <button class="style-item-menu-account">My Cancellations</button>
        </div>
      </div>
      <div class="item-menu">
        <div class="item-menu-image">
          <svg fill="currentColor" class="bi bi-star" viewBox="0 0 16 16" style="color: white; width: 25px; height: 25px;">
            <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.56.56 0 0 0-.163-.505L1.71 6.745l4.052-.576a.53.53 0 0 0 .393-.288L8 2.223l1.847 3.658a.53.53 0 0 0 .393.288l4.052.575-2.906 2.77a.56.56 0 0 0-.163.506l.694 3.957-3.686-1.894a.5.5 0 0 0-.461 0z"/>
          </svg>
        </div>
        <div class="item-menu-content">
          <button class="style-item-menu-account">My Reviews</button>
        </div>
      </div>
      <div class="item-menu">
        <div class="item-menu-image">
          <svg fill="currentColor" class="bi bi-box-arrow-left" viewBox="0 0 16 16" style="color: white; width: 25px; height: 25px; margin-left: -8px">
            <path fill-rule="evenodd" d="M6 12.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-8a.5.5 0 0 0-.5.5v2a.5.5 0 0 1-1 0v-2A1.5 1.5 0 0 1 6.5 2h8A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 5 12.5v-2a.5.5 0 0 1 1 0z"/>
            <path fill-rule="evenodd" d="M.146 8.354a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L1.707 7.5H10.5a.5.5 0 0 1 0 1H1.707l2.147 2.146a.5.5 0 0 1-.708.708z"/>
          </svg>
        </div>
        <div class="item-menu-content">
          <button @click="openModalLogout()" style="margin-left: 1px;" class="style-item-menu-account">Logout</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.menu
{
  width: 1680px;
  height: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-bottom: 1px solid #bcbcbc;
  background-color: transparent;
}

.menu-child{
  text-align: center;
  width: 85%;
  height: 70%;
  display: flex;
  margin-top: 30px;
  margin-bottom: 10px;
}

.menu-child-1{
  flex: 10;
  display: flex;
}
.menu-exclusive{
  flex: 0.4;
  display: flex;
  align-items: center;
  justify-content: left;
}

.menu-navbar{
  flex: 1;
}

.menu-search{
  flex: 3.5;
  display: flex;
  height: 100%;
  padding-top: 15px;
  padding-bottom: 15px;
  padding-left: 15%;
}

.navbar-nav {
  display: flex;
  /* Hiển thị các phần tử theo hàng */
  flex-direction: row;
  list-style: none;
  padding: 0;
  margin: 0;
  height: 55%;
}

.nav-item {
  margin: 0 25px;
  text-decoration: none;
  cursor: pointer;
  height: 100%;
}


.nav-link {
  text-decoration: none;
  color: black;
  transition: background-color 0.3s, color 0.3s;
}

.nav-link:hover {
  background-color: transparent;
  border-radius: 5px;
}

.style-input-search {
  width: 100%;
  height: 100%;
  background-color: #F5F5F5;
  padding-left: 5px;
  border-radius: 4px;
  font-size: 16px;
  border-color: transparent;
  display: flex;
  align-items: center;
}


.style-icon-menu{
  width: 28px;
  height: 28px;
  cursor: pointer;
}

.button-icon{
  width: 25%;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 80px;
  background-color: transparent;
  border: transparent;
}

/*css khi hover icon account*/

.button-icon-account:hover{
  background-color: #DB4444;
}

.button-icon-account:hover svg {
  color: white;
}

.menu-account-drop {
  position: absolute;
  left: 77.5%;
  /* Đặt vị trí từ bên trái */
  top: 14%;
  /* Đặt vị trí từ trên cùng */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
  width: 230px;
  height: 220px;
  border-radius: 4px;
  background: #9c989c;
  backdrop-filter: blur(10px);
  padding: 10px;
}

.menu-account-drop-child{
  width: 90%;
  height: 100%;
}

.item-menu{
  width: 100%;
  height: 40px;
  display: flex;
}

.item-menu-image{
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.item-menu-content{
  flex: 3;
  display: flex;
  justify-content: left;
  align-items: center;
}

.style-item-image{
  width: 30px;
  height: 30px;
  margin-top: 7px;
}

.style-item-menu-account{
  font-size: 14px;
  color: white;
  text-decoration: none;
  background-color: transparent;
  border: none;
}

.style-item-menu-account:hover{
  background-color: transparent;
}
</style>