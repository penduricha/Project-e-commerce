<script>
import DivHeartCart from "@/components/base/DivHeartCart.vue";

export default{
  name:'MenuHomePageNoAccount',
  data(){
    return{
      searchText: null,
    }
  },
  components:{
    DivHeartCart,
  },
  methods:{
    handleSearch(){

    },
    // handleToSignup(){
    //   this.$router.push('/signup-screen');
    // }
    isActiveRoute(route) {
      return this.$route.path === route;
    },
  }
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
            <li class="nav-item" :style="{ borderBottom: (isActiveRoute('/home-page') || isActiveRoute('/')) ? 'solid 2px' : 'none' }">
            <!-- || isActiveRoute('/')-->
              <router-link
                  class="btn btn-light nav-link nav-item-signup"
                  to="/home-page"
              >Home</router-link>
            </li>
            <li class="nav-item">
              <button class="btn btn-light nav-link" @click.prevent="">Contact</button>
            </li>
            <li class="nav-item">
              <button class="btn btn-light nav-link" @click.prevent="">About</button>
            </li>
            <li class="nav-item"
                :style="{ borderBottom: (isActiveRoute('/signup-screen')) ? 'solid 2px' : 'none' }">
              <router-link
                  class="btn btn-light nav-link nav-item-signup"
                  to="/signup-screen"
              >Sign up</router-link>
              <!--|| isActiveRoute('/')-->
            </li>
          </ul>
        </div>
      </div>
      <!--      border: solid 2px;-->
      <!--:style="{ textDecoration: (isActiveRoute('/signup-screen') || isActiveRoute('/')) ? 'underline' : 'none' }"-->
      <div class="menu-search">
        <input type="text" placeholder="What are you looking for?" v-model="searchText" maxlength=30 class="style-input-search" :style="{ marginLeft: (!isActiveRoute('/signup-screen') && !isActiveRoute('/login-screen')) ? '-105px' : '0' }">
        <svg fill="currentColor" class="bi bi-heart style-icon-menu" viewBox="0 0 16 16" style="width: 24px; height: 24px; cursor: pointer;" v-if="((!isActiveRoute('/signup-screen')) && (!isActiveRoute('/login-screen')))">
          <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
        </svg>
        <svg fill="currentColor" class="bi bi-cart style-icon-menu" viewBox="0 0 16 16" style="width: 24px; height: 24px; cursor: pointer;" v-if="((!isActiveRoute('/signup-screen')) && (!isActiveRoute('/login-screen')))">
          <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
        </svg>
      </div>
    </div>
  </div>
</template>

<style>
.menu
{
  flex: 0 0 8vh;
  width: 1700px;
  display: flex;
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
  border-bottom: 1px solid #bcbcbc;
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
  flex: 1;
  display: flex;
  align-items: center; /* Center vertically */
  justify-content: left;
}

.menu-navbar{
  flex: 0.75;
}

.menu-search{
  flex: 3;
  display: flex;
  height: 100%;
  padding-top: 15px;
  padding-bottom: 15px;
  padding-left: 15%;
}


/*
.menu-child-3{
  flex: 1.2;
  display: flex;
  height: 70%;
  padding-left: 20px;
  border: solid;
}*/


.navbar-nav {
  display: flex; /* Hiển thị các phần tử theo hàng */
  flex-direction: row;
  list-style: none; /* Bỏ dấu chấm của danh sách */
  padding: 0; /* Bỏ khoảng cách bên trong */
  margin: 0; /* Bỏ khoảng cách bên ngoài */
  height: 55%;
}

.nav-item {
  margin: 0 25px; /* Khoảng cách giữa các mục */
  text-decoration: none; /* Mặc định không gạch chân */
  cursor: pointer; /* Đổi con trỏ khi hover */
  height: 100%;
}


.nav-link {
  text-decoration: none; /* Bỏ gạch chân */
  color: black; /* Màu chữ mặc định */
  transition: background-color 0.3s, color 0.3s; /* Hiệu ứng chuyển tiếp */
}

/* Hiệu ứng hover */
.nav-link:hover {
  background-color: transparent; /* Màu nền khi hover */
  border-radius: 5px; /* Bo góc */
}


/*

.input-container input {
  width: 100%;
  height: 90%;
  padding: 10px 30px 10px;
  background-color: #F5F5F5;
  border: none;
  border-radius: 5px;
  --color-border-hover: white;
}*/
/*
.placeholder-image {
  position: absolute;
  right: 10px;
  margin-top: 21px;
  transform: translateY(-50%);
  width: 24px;
  height: 24px;
  /*pointer-events: none;
}*/
.style-input-search[type=text] {
  width: 100%;
  height: 100%;
  background-color: #F5F5F5;
  padding-left: 5px;
  border-radius: 4px;
  font-size: 16px;
  border-color: transparent;
  background-repeat: no-repeat;
  background-image: url('@/assets/images/glass-search.png');
  background-size: 24px 24px;
  background-position: right 10px center;
}

.style-icon-menu{
  margin-top: 10px;
  margin-left: 20px;
}
</style>