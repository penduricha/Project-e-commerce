<script>
export default {
  name: 'CustomService',
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
  <div class="container-view-service">
    <div class="view-service-child">
      <div class="view-image-service">
        <div class="style-image-service">
          <div class="style-circle-grey">
            <div class="style-circle-black">
              <img src="@/assets/image-service/icon-delivery.png" class="style-icon-service" alt="delivery">
            </div>
          </div>
        </div>
      </div>
      <div class="view-content-service">
        <p class="style-title-service">FREE AND FAST DELIVERY</p>
        <p class="style-content-service">Free delivery for all orders over $140</p>
      </div>
    </div>
    <div class="view-service-child">
      <div class="view-image-service">
        <div class="style-image-service">
          <div class="style-circle-grey">
            <div class="style-circle-black">
              <img src="@/assets/image-service/icon-customer-service.png" class="style-icon-service" alt="24/7">
            </div>
          </div>
        </div>
      </div>
      <div class="view-content-service">
        <p class="style-title-service">24/7 CUSTOMER SERVICE</p>
        <p class="style-content-service">Friendly 24/7 customer support</p>
      </div>
    </div>
    <div class="view-service-child">
      <div class="view-image-service">
        <div class="style-image-service">
          <div class="style-circle-grey">
            <div class="style-circle-black">
              <img src="@/assets/image-service/shield-tick.png" style="width: 35px; height: 40px; margin-top: 5px" alt="money back">
            </div>
          </div>
        </div>
      </div>
      <div class="view-content-service">
        <p class="style-title-service">MONEY BACK GUARANTEE</p>
        <p class="style-content-service">We refund money within 30 days</p>
      </div>
    </div>
  </div>
</template>

<style>
.container-view-service{
  width: 80%;
  display: flex;
  justify-content: space-between;
}

.view-service-child{
  height: 100%;
  width: 30%;
}

.view-image-service{
  width: 100%;
  height: 74%;
  display: flex;
  justify-content: center;
}

.view-content-service{
  width: 100%;
  height: 26%;
}

.style-title-service{
  font-weight: 750;
  font-size: 20px;
  text-align: center;
}

.style-content-service{
  font-size: 14px;
  text-align: center;
  margin-top: -15px;
}

.style-image-service{
  width: 80px;
  height: 80px;
}

.style-circle-grey{
  border-radius: 50px;
  width: 95%;
  height: 95%;
  background-color: #b4b4b4;
  display: flex;
  justify-content: center;
  align-items: center;
}

.style-circle-black{
  border-radius: 50px;
  width: 70%;
  height: 70%;
  background-color: #2F2E30;
  display: flex;
  justify-content: center;
  align-items: center;
}

.style-icon-service{
  width: 40px;
  height: 40px;
}
</style>