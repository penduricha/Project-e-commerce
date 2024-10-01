<script>
import CustomItemService from "@/components/base/CustomItemService.vue";

export default {
  name: 'CustomService',
  components:{
    CustomItemService

  },
  data(){
    return{
      image_service:{
        delivery: new URL('@/assets/image-service/icon-delivery.png',import.meta.url).href,
        support: new URL('@/assets/image-service/icon-customer-service.png',import.meta.url).href,
        money: new URL('@/assets/image-service/shield-tick.png',import.meta.url).href,
      }
    }
  },
  methods: {

  }
}
</script>

<template>
  <div class="container-view-service">
    <div class="view-service-child">
      <CustomItemService title-service="FREE AND FAST DELIVERY" content-service="Free delivery for all orders over $140" :path-image="image_service.delivery"/>
    </div>
    <div class="view-service-child">
      <CustomItemService title-service="24/7 CUSTOMER SERVICE" content-service="Friendly 24/7 customer support" :path-image="image_service.support"/>
    </div>
    <div class="view-service-child">
      <CustomItemService title-service="MONEY BACK GUARANTEE" content-service="We refund money within 30 days" :path-image="image_service.money"/>
    </div>
  </div>
</template>

<style>
.container-view-service{
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.view-service-child{
  height: 100%;
  width: 30%;
}
</style>