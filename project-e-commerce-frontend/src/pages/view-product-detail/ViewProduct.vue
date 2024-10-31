<script>
//npm install vue-star-rating@2.1.0
import CustomButton from "@/components/base/CustomButton.vue";

import StarRating from 'vue-star-rating';
import WareHouseDao from "@/daos/WareHouseDao.js";
import ProductDao from "@/daos/ProductDao.js";


//npm install vue-star-rating@next
export default {
  name: 'ViewProduct',

  components: {

    CustomButton,
    StarRating,
  },

  props: ['productId'],

  data(){
    return {
      warehouses_By_ProductId: [],
      countQuantityBuy: 0,
      firstWarehouse: null,
      product: null,
      groupedColorsBySize: null,
      image_main: null,

      rating: "No Rating Selected",
      currentRating: "No Rating",
      currentSelectedRating: "No Current Rating",
      boundRating: 3,

      listSize: [],
      listColor: [],

      //choose size;
      sizeChoose: null,
      colorChoose: null,

      //error
      notifyChoose: null,
    }
  },

  async created() {
    await this.getWareHousesBy_ProductId();

    this.wareHouses_By_ProductId = await this.getWareHousesBy_ProductId();

    if (this.wareHouses_By_ProductId && this.wareHouses_By_ProductId.length > 0) {
      if(this.wareHouses_By_ProductId.filter(size => size !== null).length > 0){

        this.firstWarehouse = this.wareHouses_By_ProductId.filter(size => size !== null)[0];
        //nguon https://dmitripavlutin.com/javascript-array-group/
        if(this.wareHouses_By_ProductId.filter(color => color !== null).length > 0){
          this.groupedColorsBySize = this.wareHouses_By_ProductId.reduce((group, wh) =>{
            const { size } = wh;
            group[size] = group[size] ?? [];
            group[size].push(wh);
            return group;
          }, {});

          this.listColor = [];
        }
      }else{
        this.firstWarehouse = this.wareHouses_By_ProductId[0];
        this.listColor = [...new Set(
              this.wareHouses_By_ProductId
                  .filter(color => color !== null)
          )];
        }
    }else{
      alert('No product Detail.');
    }

    this.image_main = this.firstWarehouse.image;

    console.log('Warehouse first: ',  this.firstWarehouse);
    console.log('Warehouse Id first: ',  this.firstWarehouse.wareHouseId);
    console.log('Warehouse Size first: ',  this.firstWarehouse.size);
    console.log('Warehouse Color first: ',this.firstWarehouse.color);
    console.log('Group color by size: ', this.groupedColorsBySize);

    //set màu khi init
    //this.setListColorGroup(this.firstWarehouse.size);

    this.product = await this.getProduct_By_ProductId();

    //trich size ra
    this.listSize = [...new Set(
        this.wareHouses_By_ProductId
            .map(w => w.size)
            .filter(size => size !== null)
    )];

    if(this.listSize.filter(size => size !== null).length === 0){
      this.listColor = [...new Set(
          this.wareHouses_By_ProductId
              .filter(color => color !== null)
      )];
    }

    // this.listImagesWithColor = [... new Set(
    //     this.wareHouses_By_ProductId.filter(warehouse =>
    //         warehouse.color !== null
    //     )
    // )];

    console.log('List size: ',this.listSize);
    console.log('List color: ',this.listColor);
    //console.log('List image with color: ',this.listImagesWithColor);
  },

  async mounted() {

  },

  methods: {
    // setRating: function(rating) {
    //   this.rating = "You have Selected: " + rating + " stars";
    // },
    // showCurrentRating: function(rating) {
    //   this.currentRating = (rating === 0) ? this.currentSelectedRating : "Click to select " + rating + " stars"
    // },
    // setCurrentSelectedRating: function(rating) {
    //   this.currentSelectedRating = "You have Selected: " + rating + " stars";
    // }
    setListColorGroup(size) {
      if (this.groupedColorsBySize) {
        if (this.groupedColorsBySize[size]) {
          this.listColor = this.groupedColorsBySize[size];
        } else {
          this.listColor = [];
        }
      } else {
        this.listColor = [];
      }
      this.listColor = this.listColor.filter(color => color !== null);
    },

    handleIncrease(){
      this.countQuantityBuy += 1;
    },

    handleReduced(){
      this.countQuantityBuy -= 1;
    },

    async getWareHousesBy_ProductId() {
      const wareHouseDao = new WareHouseDao();
      try {
        let wareHouses_By_ProductId = await wareHouseDao.getListWareHouseBy_ProductId(this.productId);
        console.log('List Warehouse: ', wareHouses_By_ProductId);
        return wareHouses_By_ProductId;
      } catch (e) {
        console.error(e);
        alert(e);
        return null;
      }
    },

    async getProduct_By_ProductId(){
      const productDao = new ProductDao();
      try {
        let product = await productDao.getProduct_By_ProductId(this.productId);
        console.log('Product: ', product);
        return product;
      } catch (e) {
        console.error(e);
        alert(e);
        return null;
      }
    },

    handleClickImage_Color(wareHouseId){
      const colorFind = this.listColor.find(l => l.wareHouseId === wareHouseId);
      this.colorChoose = colorFind.color;
      this.image_main = colorFind.image;
    },

    handleChooseSize(item){
      //chọn sẽ set màu
      this.sizeChoose = item;
      this.setListColorGroup(this.sizeChoose);
    },

    // handleChooseColor(color){
    //   this.colorChoose = color.color;
    //   const colorFind = this.listColor.filter(l => l.color === this.colorChoose);
    //   console.log('Found: ',colorFind);
    //   console.log('Image: ',colorFind.image);
    //   this.image_main = colorFind ? colorFind.image : null;
    // },

    handleChooseColor(color) {
      this.colorChoose = color.color;
      const colorFind = this.listColor.find(l => l.color === this.colorChoose);
      console.log('Found: ', colorFind);
      // Check if colorFind is not undefined before accessing image
      this.image_main = colorFind ? colorFind.image : null;
    }
  },

  computed: {
    buttonIncreaseClass(){
      return (this.countQuantityBuy > 0)
          ? 'quantity-greater-zero'
          : 'quantity-equal-zero';
    },

    buttonReduceClass(){
      return (this.countQuantityBuy > 0)
          ? 'quantity-greater-zero'
          : 'quantity-equal-zero';
    },
  }

}

// async function getProduct_By_ProductId(productId){
//   const productDao = new ProductDao();
//   let p = await productDao.getProduct_By_ProductId(productId);
//   if(p!==null){
//     return new ProductViewDto(p.productId, p.name, p.description, p.createAt, p.updateAt);
//   }else{
//     return null;
//   }
// }
</script>

<template>
  <div class="container-view-product" >
    <div class="view-image-product">
      <div class="view-list-image-color" v-if="this.listColor.filter(l => l.color !==null).length > 0">
          <img v-for="(w) in listColor"
              :src="w.image" alt="image color" class="style-image-color-list"
               @click="handleClickImage_Color(w.wareHouseId)"
          >
      </div>
      <div class="view-image-init">
        <img :src="image_main" alt="delivery" class="style-image-init">
      </div>
    </div>
    <div class="view-information-product">
      <div class="view-information-product-child" style="border-bottom: solid grey; height: auto; flex: 1">
        <div class="style-name-product" v-if="product">{{product.name}}</div>
        <div style="width: 80%; height: 25px; display: flex; margin-top: 5px;">
          <div style="flex: 1; display:inline-block;">
            <star-rating
                inactive-color="grey"
                active-color="#FFAD33"
                style="margin-top: -5%"
                :star-size="20"
                :show-rating="false"
            />
          </div>
          <div style="flex: 1; padding-left: 5px; color: grey; font-size: 14px;">
            (150 Reviews)
          </div>
          <div style="flex: 1; border-left: solid 1px grey; color: #00FF66; padding-left: 20px;">
            In Stock
          </div>
        </div>
        <p class="style-price" v-if="firstWarehouse">${{firstWarehouse.price.toFixed(2)}}</p>
        <p class="style-description" v-if="product">{{product.description}}</p>
      </div>
      <div class="view-information-product-child" style="flex: 4">
        <div class="style-item-choose-quantity">
          <button @click="handleReduced()"
                  :disabled="countQuantityBuy <= 0"
                  class="style-button-minus"
                  :class="['style-button-quantity-reduce',buttonReduceClass]"
          >
            -
          </button>
          <div class="style-number-quantity">
            {{countQuantityBuy}}
          </div>
          <button @click="handleIncrease()" class="style-button-plus"
                  :class="['style-button-quantity-increase',buttonIncreaseClass]">
            +
          </button>
        </div>
        <div class="view-item-warehouse style-size-color"  v-if="listSize.length > 0">
          <div v-if="listSize.length > 0">Size:</div>
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
        </div>
        <div class="view-item-warehouse style-size-color" v-if="listColor.filter(l => l.color !== null).length > 0">
          <div v-if="(listColor.filter(color => color !== null).length > 0 && listSize.length <= 0) ||
              (sizeChoose !== null && listColor.filter(color => color !== null).length > 0)
            "
          >Color:</div>
          <div class="container-custom-group-item"
               v-if="(listColor.filter(color => color !== null).length > 0 && listSize.length <= 0) ||
              (sizeChoose !== null && listColor.filter(color => color !== null).length > 0)"
          >
            <button class="item-color"
                    v-for="(color) in listColor.filter(l => l.color !== null)"
                    v-if="color !== null"
                    :class="['item-color-border-color', color.color === colorChoose ? 'chose' : 'no-choose']"
                    :style="{ backgroundColor: color.color }"
                    @click="handleChooseColor(color)"
            />
          </div>
        </div>
        <div class="view-item-warehouse" >
          <button @click="" class="button-add-to-cart">Add To Cart</button>
        </div>
        <div class="view-item-warehouse">
          <CustomButton @click="" style="width: 100%; height: 50px;" text-button="Buy Now"/>
        </div>
      </div>
      <div class="view-information-product-child style-service" style="margin-top: 30px;">
        <div class="view-delivery">
          <div class="view-image-delivery">
            <img src="@/assets/image-view-product-detail/icon-delivery.png" alt="delivery" class="style-image">
          </div>
          <div class="view-information-delivery">
              <p class="style-label-service">Free Delivery</p>
              <p class="style-description-service">Enter your postal code for Delivery Availability</p>
          </div>
        </div>
        <div class="view-return">
          <div class="view-image-return">
            <img src="@/assets/image-view-product-detail/icon-return.png" alt="delivery" class="style-image">
          </div>
          <div class="view-information-return">
            <p class="style-label-service">Return Delivery</p>
            <p class="style-description-service" style="text-decoration: none">
              Free 30 Days Delivery Returns. <u style="font-size: 14px; font-weight: 550;">Details</u>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss">

.container-view-product{
  width: 100%;
  height: 100%;
  display: flex;
}

.view-image-product{
  flex: 2;
  display: flex;
  padding-right: 5%;
  border-radius: 4px;
  height: 610px;
}

.view-list-image-color{
  width: 100%;
  height: 100%;
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.view-image-init{
  width: 100%;
  height: 100%;
  flex: 3;
  background-color: #F5F5F5;
  display: flex;
  justify-content: center;
  align-items: center;
}

.view-information-product{
  flex: 1;
  height: 700px;
  display: flex;
  flex-direction: column;
}

.view-information-product-child{
  display: flex;
  flex-direction: column;
  padding-bottom: 3%;
}

.style-name-product{
  font-weight: 600;
  font-size: 24px;
}

.style-price{
  font-size: 24px;
}

.style-description{
  font-size: 14px;
  width: 75%;
  height: auto;
}

.style-service{
  flex: 3;
  display: flex;
  flex-direction: column;
}

.view-delivery{
  flex: 1;
  border-top: solid grey;
  border-left: solid grey;
  border-right: solid grey;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  display: flex;
}

.view-return{
  flex: 1;
  border: solid grey;
  border-bottom-left-radius: 4px;
  border-bottom-right-radius: 4px;
  display: flex;
}

.view-image-delivery, .view-image-return{
  flex: 1;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.view-information-delivery, .view-information-return {
  flex: 4;
  width: 100%;
  height: 100%;
}

.style-image{
  width: auto;
  height: auto;
}

.style-label-service{
  font-size: 18px;
  font-weight: 550;
  margin-top: 5%;
}

.style-description-service{
  font-size: 14px;
  font-weight: 550;
  text-decoration: underline;
  margin-top: -5%;
}

.style-image-init{
  max-width: 100%;
  max-height: 100%;
  width: auto;
  height: auto;
  object-fit: contain;
}

.view-item-warehouse{
  margin-top: 2%;
}

.button-add-to-cart{
  color: white;
  background-color: black;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 400;
  font-size: 16px;
  width: 100%;
  height: 50px;
}

.style-size-color{
  font-size: 20px;
  display: flex;
  align-items: center;
  height: 20px;
  margin-bottom: 15px;
}

.style-button-quantity{
  cursor: pointer;
  font-size: 30px;
  display: flex;
  justify-content: center;
  align-items: center
}

.style-button-quantity-increase{
  &.quantity-greater-zero{
    cursor: pointer;
    font-size: 35px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #DB4444;
    color: white;
    padding-bottom: 8px;
  }

  &.quantity-equal-zero{
    cursor: pointer;
    font-size: 35px;
    display: flex;
    justify-content: center;
    align-items: center;
    padding-bottom: 8px;
  }
}

.style-button-quantity-reduce{
  &.quantity-greater-zero{
    font-size: 35px;
    cursor: pointer;
    display: flex;
    justify-content: center;
    align-items: center;
    padding-bottom: 8px;
  }

  &.quantity-equal-zero{
    font-size: 35px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #ccc;
    padding-bottom: 8px;
  }
}

.style-item-choose-quantity{
  border: solid grey;
  height: 45px;
  width: 35%;
  border-radius: 4px;
  display: flex;
  margin-top: 8%;
  margin-bottom: 15px;

}

.style-button-minus{
  flex: 1;
  border-right: solid grey;
}

.style-button-plus{
  flex: 1;
  border-left: solid grey;
}

.style-number-quantity{
  flex: 2;
  font-weight: 500;
  display: flex;
  font-size: 20px;
  justify-content: center;
  align-items: center;
}

.style-image-color-list{
  width: 80%;
  height: 25%;
  cursor: pointer;
  margin-bottom: 10px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 4px;
  background-color: #F5F5F5;
  object-fit: contain;
}

//group item
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