<script>
export default {
  name: 'CustomNewLabel',
  // props: {
  //   // Có thể thêm props nếu cần
  // },
  props: ['text','backgroundColor'],
  methods: {

  },
  data(){
    return{

    }
  }
}
</script>

<template>
  <div class="container-new" :style="{ backgroundColor: backgroundColor }">
<!--    <p style="font-size: 12px; color: #FAFAFA; text-align: center;">{{text}}</p>-->
    {{text}}
  </div>
</template>

<style>
.container-new{
  width: 55px;
  height: 25px;
  border-radius: 4px;
  /*background-color: #DB4444;*/
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 12px;
  color: #FAFAFA;
}
</style>