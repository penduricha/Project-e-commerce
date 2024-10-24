<script>
export default{
  name:'Header',
  components:{

  },
  data() {
    return {
      language: 'English',
    };
  },
  methods: {

  },
}
</script>

<template>
  <header class="header">
    <div style="justify-content: right; display: flex; flex: 2;">
      <p style="color: white; margin-top: 15px; font-size: 14px;">Summer Sale For All Swim Suits And Free Express Delivery - OFF 50%!</p>
      <a href="" class="style-link-shop-now">ShopNow</a>
    </div>
    <div class="style-view-language">
      <select name="languages" v-model="language" style="" class="custom-select">
        <option value="English">English</option>
        <option value="Italian">Italian</option>
        <option value="French">French</option>
      </select>
    </div>
  </header>
</template>
<style>
.header {
  flex: 0 0 2vh;
  background-color: black;
  width: 1680px;
  justify-content: center;
  display: flex;
  padding-top: 10px;
}

.style-view-language{
  justify-content: center;
  display: flex; flex: 1;
  align-items: center;
}

.style-link-shop-now{
  color: white;
  margin-top: 12px;
  font-size: 14px;
  font-weight: bold;
  text-decoration: underline;
  margin-left: 10px
}

.custom-select {
  border-radius: 0;
  position: relative;
  height: 70%;
  margin-top: 10px;
  border: none;
  color: white;
  background-color: black;
  margin-left: 230px;
  width: 80px;
}

.custom-select::after{
  background-color: white;
}


</style>