<script>
export default {
  name: 'CustomGroupItemColor',

  props: ['color'],

  data(){

  },

  methods:{

  }
}
</script>

<template>
  <div class="container-custom-group-item">
    <div class="item-color">

    </div>
    <div class="item-color">

    </div>
  </div>
</template>

<style lang="scss">

.container-custom-group-item{
  width: 100%;
  height: 100%;
  margin-left: 2%;
  display: flex;
}

.item-color{
  height: 80%;
  width: 40px;
  margin-right: 2%;
  border-radius: 4px;
  border: #104888;
  background-color: #104888;
  cursor: pointer;
  justify-content: center;
  align-items: center;
  display: flex;
}


</style>