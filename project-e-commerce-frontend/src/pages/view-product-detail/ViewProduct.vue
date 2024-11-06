<script>
//npm install vue-star-rating@2.1.0
import CustomButton from "@/components/base/CustomButton.vue";

import StarRating from 'vue-star-rating';
import WareHouseDao from "@/daos/WareHouseDao.js";
import ProductDao from "@/daos/ProductDao.js";
import Cart from "@/models/Cart.js";
import RouterDao from "@/daos/RouterDao.js";


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
      groupedSizeByColor: null,
      image_main: null,
      price_view: 0,

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
      notifyValidation: null,

      //block choose

      //khi size và color có và khi click size
      listColorAfterChooseSize: [],
      listSizeAfterChooseColor: [],
      disabledColors: [],
      disabledSizes: [],
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

          this.groupedSizeByColor= this.wareHouses_By_ProductId.reduce((group, wh) =>{
            const { color } = wh;
            group[color] = group[color] ?? [];
            group[color].push(wh);
            return group;
          }, {});
        }


      }else{
        this.firstWarehouse = this.wareHouses_By_ProductId[0];
      }
    }else{
      alert('No product Detail.');
    }
    //this.listColor = this.listColor.concat(this.listColor);

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
    this.listSize = this.wareHouses_By_ProductId.filter(item => item.size !== null)

    this.listColor = this.wareHouses_By_ProductId.filter(item => item.color !== null);

    //set chi duoc dung trong mang ['A','B','C'] loc
    this.listSize = this.listSize.filter((item, index, self) =>
        index === self.findIndex((t) => (t.size === item.size))
    );

    this.listColor = this.listColor.filter((item, index, self) =>
        index === self.findIndex((t) => (t.color === item.color))
    );
    //neu list size === 0
    // if(this.listSize.length === 0){
    //   this.listColor = this.listColor.filter(l => l.quantity > 0);
    // }

    //set chọn nếu có 1
    if(this.listSize.length === 1){
      this.sizeChoose = this.listSize[0].size;
      this.handleChooseSize(this.sizeChoose);
    }

    if(this.listColor.length === 1){
      this.colorChoose = this.listColor[0].color;
    }

    if(this.listSize.filter(size => size !== null).length === 0){
      this.listColor = this.wareHouses_By_ProductId.filter(color => color !== null);
    }

    this.listColor = this.listColor.filter((item, index, self) =>
        index === self.findIndex((t) => (t.color === item.color))
    );

    //this.listColor = this.listColor.concat(this.listColor);

    console.log('List size: ',this.listSize);
    console.log('List color: ',this.listColor);
    this.price_view = getPrice_By_Discount(this.firstWarehouse.price, this.firstWarehouse.numberOfDiscount);
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
          this.listColorAfterChooseSize = this.groupedColorsBySize[size];
        } else {
          this.listColorAfterChooseSize = [];
        }
      } else {
        this.listColorAfterChooseSize = [];
      }
      this.listColorAfterChooseSize = this.listColorAfterChooseSize.filter((l => l.color !== null) && (l => l.quantity > 0));
    },

    setListSizeGroup(color) {
      if (this.groupedSizeByColor) {
        if (this.groupedSizeByColor[color]) {
          this.listSizeAfterChooseColor = this.groupedSizeByColor[color];
        } else {
          this.listSizeAfterChooseColor = [];
        }
      } else {
        this.listSizeAfterChooseColor = [];
      }
      this.listSizeAfterChooseColor = this.listSizeAfterChooseColor.filter((l => l.color !== null) && (l => l.quantity > 0));
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
      this.listColorAfterChooseSize = [];
      //this.listColorAfterChooseSize = null;
      if(this.listSize.length === 1){
        this.sizeChoose = this.listSize[0].size;
        const colorFind = this.listColor.find(l => l.wareHouseId === wareHouseId);
        this.colorChoose = colorFind.color;
        this.image_main = colorFind.image;
      }else{
        this.sizeChoose = null;
        this.listSizeAfterChooseColor = null;
        const colorFind = this.listColor.find(l => l.wareHouseId === wareHouseId);
        this.colorChoose = colorFind.color;

        if(this.listColor.length > 0 && this.listSize.length === 0){
          this.price_view = getPrice_By_Discount(colorFind.price, colorFind.numberOfDiscount);
        }

        this.image_main = colorFind.image;
        this.setListSizeGroup(this.colorChoose);
        console.log('List size after chose color: ',this.listSizeAfterChooseColor);
      }
    },

//     if(this.listColor.length > 0 && this.listSize.length > 0){
//   this.price_view = this.listColor.filter(l =>
//       l.color === this.colorChoose &&
//       l.size === this.sizeChoose
//   )[0].price;
// }

    handleChooseSize(size){
      if(this.listSize && !this.listColor){
        this.sizeChoose = size;
        let whFound = this.listSize.filter(l =>  l.size === this.sizeChoose)[0]
        this.price_view = getPrice_By_Discount(whFound.price, whFound.numberOfDiscount);
      }else{
        if(this.listSize.length === 1){
          this.listColorAfterChooseSize = null;
          this.listColorAfterChooseSize = this.listColor;
        }else{
          if(this.listColor.length === 1){
            this.colorChoose = this.listColor[0].color;
          }else{
            if (this.colorChoose === null || this.sizeChoose !== size) {
              this.sizeChoose = size;
              this.listColorAfterChooseSize = null;
              this.setListColorGroup(this.sizeChoose);
              console.log('List color after chose size: ', this.listColorAfterChooseSize);
              this.colorChoose = null;
            }
          }
        }
      }
    },

    blockButtonColor(color) {
      this.disabledColors = this.listColor.filter(colorItem =>
            !this.listColorAfterChooseSize.some(item => item.color === colorItem.color)
      );
      let colorsThatQuantityZero = this.listColor.filter(l => l.quantity <=0);
      this.disabledColors = this.disabledColors.concat(colorsThatQuantityZero);
      console.log('List color disable: ', this.disabledColors);
      return this.disabledColors.some(disabledColor => disabledColor.color === color);
    },

    blockButtonSize(size) {
      this.disabledSizes = this.listColor.filter(colorItem =>
          !this.listSizeAfterChooseColor.some(item => item.size === colorItem.size)
      );
      let sizesThatQuantityZero = this.listColor.filter(l => l.quantity <= 0);
      this.disabledSizes = this.disabledSizes.concat(sizesThatQuantityZero);
      console.log('List size disable: ', this.disabledSizes);
      return this.disabledSizes.some(disabledSize => disabledSize.size === size);
    },

    handleChooseColor(color) {
      this.colorChoose = color;
      //console.log('Found: ', colorFind);
      //this.price_view = colorFind.price;
      if(this.listColor.length > 0 && this.listSize.length === 0){
        const colorFind = this.listColor.filter(l => l.color === this.colorChoose);
        this.price_view = getPrice_By_Discount(colorFind[0].price, colorFind[0].numberOfDiscount);
        this.image_main = colorFind[0] ? colorFind[0].image : null;
      }

      if(this.listColor.length > 0 && this.listSize.length > 0){
        let whFound = this.listColor.filter(l =>
            l.color === this.colorChoose &&
            l.size === this.sizeChoose
        );

        this.price_view = getPrice_By_Discount(whFound[0].price, whFound[0].numberOfDiscount);

        this.image_main = this.listColor.filter(l =>
            l.color === this.colorChoose &&
            l.size === this.sizeChoose
        )[0].image;
      }
      // Check if colorFind is not undefined before accessing image
      //this.price_view = this.listSize.filter(l =>  l.size === this.sizeChoose)[0];
    },

    isButtonChooseColorDisabled(color) {
      if(this.listSize.length === 1 && this.listColor){
        return false;
      }else{
        if(this.colorChoose === color){
          return false;
        }else{
          const shouldDisable = this.listSize.length > 0 && this.listColor.length > 0;
          if(this.listSize.length > 0 && this.listColor.length > 0){
            if (this.listColorAfterChooseSize.length === 0) {
              return shouldDisable;
            }else{
              return this.blockButtonColor(color);
            }
          }else{
            if(this.listSize.length === 0 && this.listColor.length > 0){
              const findQuantity = this.listColor.filter(l => l.color === color);
              return findQuantity.quantity <= 0 || !findQuantity;
            }
          }
        }
      }
    },

    isButtonChooseSizeDisabled(size){
      if(this.listSize && this.listColor.length === 1){
        return false;
      }else{
        if(this.listSize.length > 0 && this.listColor.length > 0){
          if(this.sizeChoose !== null){
            return false;
          }else{
            if(this.listSizeAfterChooseColor.length === 0){
              return false;
            }else{
              return this.blockButtonSize(size);
            }
          }
        }else{
          if(this.listSize.length > 0 && this.listColor.length === 0){
            const findQuantity = this.listSize.filter(l => l.size === size);
            return findQuantity.quantity <= 0 || !findQuantity;
          }
        }
      }
    },

    handleAddToCart(){
      //let cartProduct = new Cart(this.product.productId, this.product.image, this.product.name, this.);
      // listSize: [],
      //listColor: [],
      console.log(this.listSize);
      console.log(this.listColor);
      if(this.countQuantityBuy > 0){
        if(this.listSize.length > 0 && this.listColor.length === 0){
          if(this.sizeChoose){
            //chi có size
            //constructor(productId, size, color ,image, name, price, quantity)
            let whFound = this.listSize.filter(warehouse => warehouse.size === this.sizeChoose);
            if(this.countQuantityBuy > whFound[0].quantity){
              this.notifyValidation = 'Selected products exceed products in stock.';
            }else{
              this.notifyValidation = '';
              const cart = new Cart(this.product.productId,
                  this.sizeChoose, this.colorChoose, this.image_main,
                  this.product.name, this.price_view, this.countQuantityBuy);
              console.log('Product to add cart: ',cart);
            }
          }else{
            this.notifyValidation = 'Please choose size.';
          }
        }

        if(this.listSize.length === 0 && this.listColor.length > 0){
          if(this.colorChoose){
            let whFound = this.listSize.filter(warehouse => warehouse.color === this.colorChoose);
            if(this.countQuantityBuy > whFound[0].quantity){
              this.notifyValidation = 'Selected products exceed products in stock.';
            }else{
              this.notifyValidation = '';
              const cart = new Cart(this.product.productId,
                  this.sizeChoose, this.colorChoose, this.image_main,
                  this.product.name, this.price_view, this.countQuantityBuy);
              console.log('Product to add cart: ',cart);
            }
          }else{
            this.notifyValidation = 'Please choose size.';
          }
        }

        if(this.listSize.length > 0 && this.listColor.length > 0){
          if(!this.sizeChoose){
            this.notifyValidation = 'Please choose size.';
          }

          if(!this.colorChoose){
            this.notifyValidation = 'Please choose color.';
          }

          if(this.sizeChoose && this.colorChoose){
            let whFound = this.listSize.filter(warehouse =>
                warehouse.color === this.colorChoose &&
                warehouse.size === this.sizeChoose
            );

            if(this.countQuantityBuy > whFound[0].quantity){
              this.notifyValidation = 'Selected products exceed products in stock.';
            }else{
              //add to cart
              this.notifyValidation = '';
              const cart = new Cart(this.product.productId,
                  this.sizeChoose, this.colorChoose, this.image_main,
                  this.product.name, this.price_view, this.countQuantityBuy);
              console.log('Product to add cart: ',cart);
            }
          }
        }

        const routerDao = new RouterDao();
        if(routerDao.getEmailPhoneNumberFromLocalStorage() === null){
          //save from Local Storage

        }else{
          //save from database POST

        }
      }else{
        this.notifyValidation = 'Please choose quantity.';
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

    viewListImageScroll(){
      return (this.listColor.filter(l => l.color !== null).length > 4)
        ? 'scroll' : 'no-scroll';
    }
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
function getPrice_By_Discount(price, numberOfDiscount){
  let priceView = price - price*(numberOfDiscount / 100);
  if(isZero(getDecimalPart(priceView))){
    return priceView.toFixed(2);
  }else
  {
    return priceView.toFixed(2);
  }
  ///return  price - price*(numberOfDiscount / 100);
}

function getDecimalPart(num) {
  num = num.toFixed(2);
  const integerPart = Math.floor(num);
  const decimalPart = num - integerPart;
  const decimalAsInt = Math.round(decimalPart * 10000);
  return decimalAsInt.toString().padStart(2, '0');
}

function isZero(number){
  return Number(number) === 0;
}
</script>

<template>
  <div class="container-view-product" >
    <div class="view-image-product">
      <div class="view-list-image-color" v-if="this.listColor.filter(l => l.color !==null).length > 0"
           :class="['scroll-view-list-image', viewListImageScroll]"
      >
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
        <p class="style-price" v-if="firstWarehouse">${{price_view}}</p>
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
        <!--Size Choose-->
        <div class="view-item-warehouse style-size-color"  v-if="listSize.length > 0">
          <div v-if="listSize.length > 0">Size:</div>
          <div class="container-custom-group-item">
            <button class="item-size"
                    v-for="(s) in listSize"
                    :class="['item-size-width', s.size.length > 3 ?
                    'length-item-greater-3' : 'length-item-lower-equal-3',
                    'item-size-color', s.size === sizeChoose ? 'chose' : 'no-choose',
                    'item-block-size-choose', isButtonChooseSizeDisabled(s.size) ? 'disabled' : 'enabled'
              ]"
                      @click="handleChooseSize(s.size)"
                      :disabled="isButtonChooseSizeDisabled(s.size)"
              >
                {{ s.size }}
              </button>
          </div>
        </div>
        <!--Color Choose-->
        <div class="view-item-warehouse style-size-color" v-if="listColor.filter(l => l.color !== null).length > 0">
          <div v-if="(listColor.filter(color => color !== null).length > 0 && listSize.length <= 0) ||
              (listColor.filter(color => color !== null).length > 0)
            "
          >Color:</div>
          <div class="container-custom-group-item"
               v-if="(listColor.filter(color => color !== null).length > 0 && listSize.length <= 0) ||
              (listColor.filter(color => color !== null).length > 0)"
          >
            <button class="item-color"
                    v-for="(c) in listColor.filter(l => l.color !== null)"
                    :class="['item-color-border-color', c.color === colorChoose ? 'chose' : 'no-choose',
                            'item-block-color-choose', isButtonChooseColorDisabled(c.color) ? 'disabled' : 'enabled'
                      ]"
                    :style="{ backgroundColor: c.color }"
                    @click="handleChooseColor(c.color)"
                    :disabled="isButtonChooseColorDisabled(c.color)"
            />
          </div>
        </div>
        <div class="view-item-warehouse">
          <span class="span-error" style="font-size: 18px;">{{notifyValidation}}</span>
        </div>
        <div class="view-item-warehouse" >
          <button @click="handleAddToCart()" class="button-add-to-cart">Add To Cart</button>
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
@import '@/assets/input-white';

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
    //hien thi icon cam
    cursor: not-allowed;
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
  width: 90%;
  height: 24%;
  cursor: pointer;
  margin-bottom: 4%;
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
    color: red;
  }

  &.no-choose{
    border: none;
    color: red;
  }
}

.item-block-color-choose{
  &.enable {
    opacity: 1;
    // Fully visible
    cursor: pointer;
    // Indicate that it's clickable
  }

  &.disabled {
    opacity: 0.25;
    cursor: not-allowed;
  }
}

.item-block-size-choose{
  &.enable {
    opacity: 1;
    // Fully visible
    cursor: pointer;
    // Indicate that it's clickable
  }

  &.disabled {
    opacity: 0.25;
    cursor: not-allowed;
  }
}

.scroll-view-list-image{
  &.no-scroll {
    overflow-y: hidden;
  }

  &.scroll{
    overflow-y: scroll;
    object-fit: contain;
  }
}

</style>