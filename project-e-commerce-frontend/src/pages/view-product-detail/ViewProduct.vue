<script>
//npm install vue-star-rating@2.1.0
import CustomButton from "@/components/base/CustomButton.vue";
import CustomGroupItemSize from "@/pages/view-product-detail/CustomGroupItemSize.vue";
import CustomGroupItemColor from "@/pages/view-product-detail/CustomGroupItemColor.vue";
import StarRating from 'vue-star-rating';
import WareHouseDao from "@/daos/WareHouseDao.js";
import ProductDao from "@/daos/ProductDao.js";


//npm install vue-star-rating@next
export default {
  name: 'ViewProduct',

  components: {
    CustomGroupItemColor,
    CustomGroupItemSize,
    CustomButton,
    StarRating,
  },

  props: ['productId'],

  data(){
    return{
      warehouses_By_ProductId: [],
      countQuantityBuy: 0,
      firstWarehouse: null,
      product: null,

      rating: "No Rating Selected",
      currentRating: "No Rating",
      currentSelectedRating: "No Current Rating",
      boundRating: 3,

      listSize: [],
      listColor: [],
      listImagesWithColor: [],
    }
  },

  async created() {
    await this.getWareHousesBy_ProductId();

    this.wareHouses_By_ProductId = await this.getWareHousesBy_ProductId();

    if (this.wareHouses_By_ProductId && this.wareHouses_By_ProductId.length > 0) {
      this.firstWarehouse = this.wareHouses_By_ProductId[0];
    }
    console.log('Warehouse Id first: ',  this.firstWarehouse.wareHouseId);

    this.product = await this.getProduct_By_ProductId();

    //trich size ra
    this.listSize = Array.from(
        this.wareHouses_By_ProductId.reduce((map, warehouse) => {
          if (!map.has(warehouse.size)) {
            map.set(warehouse.size, warehouse);
          }
          return map;
        }, new Map()).values()
    ).map(w => w.size);

    this.listColor = Array.from(
        this.wareHouses_By_ProductId.reduce((map, warehouse) => {
          if (!map.has(warehouse.color)) {
            map.set(warehouse.color, warehouse);
          }
          return map;
        }, new Map()).values()
    ).map(w => w.color);

    this.listImagesWithColor = this.wareHouses_By_ProductId.filter(warehouse =>
        warehouse.color !== null && warehouse.wareHouseId !== this.firstWarehouse.wareHouseId
    );

    console.log('List size: ',this.listSize);
    console.log('List color: ',this.listColor);
    console.log('List image with color: ',this.listImagesWithColor);
  },

  async mounted() {
    //mounted fetch api -> bien Vuejs
    //this.setWarehouses_By_ProductId();

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
      <div class="view-list-image-color" >
        <div v-for="(w) in listImagesWithColor">
          <img :src="w.image" alt="image color" class="style-image-color-list">
        </div>
      </div>
      <div class="view-image-init">
        <img v-if="firstWarehouse" :src="firstWarehouse.image" alt="delivery" class="style-image-init">
      </div>
    </div>
    <div class="view-information-product">
      <div class="view-information-product-child" style="flex: 3; border-bottom: solid grey">
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
      <div class="view-information-product-child">
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
        <div class="view-item-warehouse style-size-color" style="display: flex; align-items: center;">
          Size:
          <CustomGroupItemSize/>
        </div>
        <div class="view-item-warehouse style-size-color" style="display: flex; align-items: center; ">
          Color:
          <CustomGroupItemColor/>
        </div>
        <div class="view-item-warehouse" >
          <button @click="" class="button-add-to-cart">Add To Cart</button>
        </div>
        <div class="view-item-warehouse">
          <CustomButton @click="" style="width: 100%; height: 100%" text-button="Buy Now"/>
        </div>
      </div>
      <div class="view-information-product-child style-service">
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
}

.view-list-image-color{
  width: 100%;
  height: 100%;
  flex: 1;
  overflow-y: scroll;
  overflow-x: hidden;
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
  flex: 5;
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
  flex: 1;
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
  height: 100%;
}

.style-size-color{
  font-size: 20px;
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
  }

  &.quantity-equal-zero{
    cursor: pointer;
    font-size: 35px;
    display: flex;
    justify-content: center;
    align-items: center;

  }
}

.style-button-quantity-reduce{
  &.quantity-greater-zero{
    font-size: 35px;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
  }

  &.quantity-equal-zero{

    font-size: 35px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: gray;
  }
}

.style-item-choose-quantity{
  border: solid grey;
  height: 45px;
  width: 35%;
  border-radius: 4px;
  display: flex;
  margin-top: 4%
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
  width: 100%;
  height: auto;
  object-fit: cover;
  /* Đảm bảo hình ảnh phủ đầy div mà không bị biến dạng */
  margin-bottom: 5px;
  cursor: pointer;
}
</style>