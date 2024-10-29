<script>


export default {
  name: 'CustomGroupItemSize',

  props: ['listSize','sizeWareHouseFirst'],

  data(){
    return{
      //gán biến sizeChoose mang giá trị csủa size ở warehouse đầu tiên
      sizeChoose: this.sizeWareHouseFirst,
    }
  },

  created() {
    this.getSizeWareHouseFirst();
  },

  methods:{
    getSizeWareHouseFirst(){
      console.log(this.sizeWareHouseFirst);
      //set vào session
      sessionStorage.setItem('sizeChoose', this.sizeChoose);
    },

    handleChooseSize(item){
      this.sizeChoose = item;
      sessionStorage.setItem('sizeChoose', this.sizeChoose);
      console.log('Size chosen: ',sessionStorage.getItem('sizeChoose'));
      //emit này sẽ call ở
      this.$emit('size-chosen', sessionStorage.getItem('sizeChoose'));
    },
  },



  computed: {
    itemSizeClass(){
      // return (this.countQuantityBuy > 0)
      //     ? 'quantity-greater-zero'
      //     : 'quantity-equal-zero';
    }
  }
}
</script>

<template>

  <div class="container-custom-group-item">
    <button class="item-size"
         v-for="(item) in listSize"
         :key="item"
         :class="['item-size-width', item.length > 3 ?
         'length-item-greater-3' : 'length-item-lower-equal-3',
         'item-size-color', item === sizeChoose ? 'chose' : 'no-choose'
         ]"
          @click="handleChooseSize(item)"
    >
      {{ item }}
    </button>
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
  height: 40px;
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

.item-size-color{
  &.chose{
    background-color: #DB4444;
    border-color: #DB4444;
    color: white;
  }

  &.no-choose{
    background-color: transparent;
  }
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

.item-size-width{
  &.length-item-greater-3{
    width: auto;
  }

  &.length-item-lower-equal-3{
    width: 40px;
  }
}


</style>