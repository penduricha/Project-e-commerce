<script>
import CustomGroupItemSize from "@/pages/view-product-detail/CustomGroupItemSize.vue";

export default {
  name: 'CustomGroupItemColor',

  props: ['listColor','sizeWareHouseFirst','colorWareHouseFirst','listSize'],

  components: {
    CustomGroupItemSize,
  },

  data(){
    return {
      //lay tu bien session
      sizeChoose: sessionStorage.getItem('sizeChoose'),
      colorChoose: this.colorWareHouseFirst,
      groupedColorsBySize: null,
    }
  },

  created() {
    this.getListColorToChoose();
  },


  updated() {
    this.getSizeChoose();
  },

  methods:{
    getListColorToChoose(){
      console.log('List color to choose: ',this.listColor);

      console.log('Color chosen when init: ',this.colorWareHouseFirst);

      console.log('Size to group color: ',this.sizeWareHouseFirst);
    },

    handleChooseColor(color){
      this.colorChoose = color;
    },

    getSizeChoose(){
      // this.sizeChoose = sessionStorage.getItem('sizeChoose');
      // console.log('Size chosen to group: ',this.sizeChoose);
      this.sizeChoose = sessionStorage.getItem('sizeChoose');
      console.log('Updated size: ', this.sizeChoose);
    }
  },

  watch: {
    //ham khi update
    sizeChoose() {
      this.getSizeChoose();
    }
  },

  computed: {
    // buttonColorBorderClass(){
    //   return ()
    //       ? 'chose'
    //       : 'none-border-bottom';
    // }
  }
}
</script>

<template>
  <div class="container-custom-group-item">
    <button class="item-color"
            v-for="(color) in listColor"
            :class="['item-color-border-color', color === colorChoose ? 'chose' : 'no-choose']"
            :style="{ backgroundColor: color }"
            @click="handleChooseColor(color)"
    />


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
  height: 40px;
  width: 40px;
  margin-right: 2%;
  border-radius: 4px;
  //border: #104888;
  //background-color: #104888;
  cursor: pointer;
  justify-content: center;
  align-items: center;
  display: flex;
}

.item-color-border-color{
  &.chose{
    border: solid 4px #DB4444;
   }

  &.no-choose{
    border: none;
  }
}

</style>