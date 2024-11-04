<script>
export default {
  name: 'CustomInputCouponCode',
  props: ['textCouponCode', 'textPlaceholder'],
  data(){
    return{
      couponCode: this.textCouponCode,
    }
  }
}
</script>

<template>
  <!-- limit input coupon code 50 char-->
  <input type="text" maxlength=50 class="style-input-coupon" :placeholder="textPlaceholder" v-model="couponCode">
</template>

<style lang="scss" scoped>
.style-input-coupon{
  width: 100%;
  height: 100%;
  border: solid 1.5px;
  border-radius: 4px;
  font-size: 16px;
  padding-left: 20px;
}
</style>