<template>
  <button class="custom-button">{{textButton}}</button>
</template>

<script>
export default {
  name: 'CustomButton',
  // props: {
  //   // Có thể thêm props nếu cần
  // },
  props: ['textButton'],
  methods: {

  },
  data(){
    return{

    }
  }
}
</script>

<style>
.custom-button {
  background-color: #DB4444;
  color: white;
  border: none;
  border-radius: 4px;
}
</style>