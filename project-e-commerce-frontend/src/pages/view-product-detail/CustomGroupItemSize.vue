<script>
export default {
  name: 'CustomGroupItemSize',

  props: ['size'],

  data(){

  },

  methods:{

  }
}
</script>

<template>
  <div class="container-custom-group-item">
    <div class="item-size">
      M
    </div>
    <div class="item-size">
      X
    </div>
  </div>
</template>

<style lang="scss">

.container-custom-group-item{
  width: 100%;
  height: 100%;
  margin-left: 2%;
  display: flex;
  align-items: center;
}

.item-size{
  height: 80%;
  width: 40px;
  border: solid grey;
  margin-right: 2%;
  border-radius: 4px;
  background-color: transparent;
  cursor: pointer;
  justify-content: center;
  align-items: center;
  display: flex;
  white-space: nowrap;
}

//.item-color{
//  height: 100%;
//  width: 15%;
//  padding-left: 3%;
//  padding-right: 3%;
//  border: solid grey;
//  margin-right: 4%;
//  border-radius: 4px;
//  background-color: transparent;
//  cursor: pointer;
//  justify-content: center;
//  align-items: center;
//  display: flex;
//}


</style>