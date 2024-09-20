<script>
export default {
  name: 'CustomDiscount',
  // props: {
  //   // Có thể thêm props nếu cần
  // },
  props: ['textNumberDiscount','backgroundColor'],
  methods: {

  },
  data(){
    return{

    }
  }
}
</script>

<template>
  <div class="container-discount" :style="{ backgroundColor: backgroundColor }">
    <p style="font-size: 12px; color: #FAFAFA; text-align: center;">{{textNumberDiscount}}%</p>
  </div>
</template>

<style>
.container-discount{
  width: 55px;
  height: 25px;
  border-radius: 4px;
  /*background-color: #DB4444;*/
  padding-top: 2px;
}
</style>