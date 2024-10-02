<script>
export default {
  name: 'CustomPaginationGrey',
  components:{

  },
  data(){
    return{

    }
  },
  methods: {

  }
}
</script>

<template>
  <div class="group-button">
    <div class="button-in-group">
      <button class="button-pagination"></button>
    </div>
    <div class="button-in-group">
      <button class="button-pagination"></button>
    </div>
    <div class="button-in-group">
      <button class="button-pagination"></button>
    </div>
    <div class="button-in-group">
      <button class="button-pagination"></button>
    </div>
    <div class="button-in-group">
      <button class="button-pagination"></button>
    </div>
  </div>
</template>

<style>
.group-button{
  width: 100px;
  height: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button-in-group{
  width: 15px;
  height: 15px;
  background-color: transparent;
  border-radius: 100px;
  border: none;
  display: flex;
  justify-content: center;
  align-items: center;
}

.button-pagination{
  border-radius: 200px;
  border: none;
  width: 80%;
  height: 80%;
  background-color: grey;
}
</style>