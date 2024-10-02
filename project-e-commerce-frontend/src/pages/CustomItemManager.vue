<script>
export default {
  name: 'CustomItemManager',
  props: ['name', 'position', 'imageManager'],
  data(){
   return{

   }
  }

}
</script>

<template>
  <div class="item-manager">
    <div class="item-manager-image">
      <img :src="imageManager" alt="Image Manager" class="style-image-manager">
    </div>
    <div class="item-manager-view-profile">
      <div class="item-manager-profile">
        <p class="style-name">{{name}}</p>
        <p class="style-position">{{position}}</p>
      </div>
      <div class="item-link-account">
        <img src="@/assets/image-icon-social-network/twitter.png" alt="Twitter" class="style-icon-social-network">
        <img src="@/assets/image-icon-social-network/instragram.png" alt="IG" class="style-icon-social-network">
        <img src="@/assets/image-icon-social-network/linkedin.png" alt="Linkedin" class="style-icon-social-network">
      </div>
    </div>
  </div>
</template>

<style>
.item-manager{
  display: flex;
  flex-direction: column;
}

.item-manager-image{
  flex: 3;
  border-radius: 4px;
  background-color: #F5F5F5;
  display: flex;
  justify-content: center;
  align-items: flex-end;
}

.style-image-manager{
  width: auto;
  height: auto;
}

.item-manager-view-profile{
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: left;
}

.item-manager-profile{
  width: 100%;
  height: 85%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.item-link-account{
  width: 30%;
  height: 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-align: left;
}

.style-name{
  font-size: 32px;
  font-weight: 500;
}

.style-position{
  font-size: 16px;
  margin-top: -10%;
}

.style-icon-social-network{
  cursor: pointer;
}
</style>