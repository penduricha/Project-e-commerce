<script>

export default {
  name: 'ImagePhone',
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
  <div class="background-image">
    <div class="background-advertise">
      <div class="background-advertise-child">
        <div class="background-apple-iphone-14">
          <div class="background-apple">
            <img src="@/assets/images/1200px-Apple_gray_logo%201.png" alt="apple" style="width: 40px; height: 49px"/>
          </div>
          <div class="background-iphone-14-series">
            <p style="font-size: 16px; color: #FAFAFA; ">iPhone 14 Series</p>
          </div>
        </div>
        <div class="background-voucher">
          <p style="font-size: 48px; color: #FAFAFA; font-weight: bolder;">Up to 10%<br>off Voucher</p>
        </div>
        <div class="background-shop-now">
          <a href="" style="font-size: 16px; color: #FAFAFA; border-bottom: solid #FAFAFA; height: 100%; text-decoration: none;">Shop Now</a>
          <svg @click="" width="24" height="24" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 15 15" style="color: #FAFAFA; margin-left: 5%; margin-top: 3%; cursor: pointer;">
            <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8"/>
          </svg>
        </div>
      </div>
    </div>
    <div class="background-iphone">
      <img src="@/assets/images/hero_endframe__cvklg0xk3w6e_large%202.png" alt="iphone" style="width: 100%; height: 100%"/>
    </div>
  </div>
</template>

<style>
.background-image{
  width: 950px;
  height: 344px;
  display: flex;
  background-color: black;
}

.background-advertise{
  flex: 1;
  padding: 40px;
  display: flex;
}

.background-advertise-child{
  flex: 1;
  margin-left: 8%;
}

.background-apple-iphone-14{
  height: 25%;
  display: flex;
}

.background-apple{
  flex: 2;
}

.background-iphone-14-series{
  flex: 8;
  align-content: center;
}

.background-voucher{
  height: 50%;
}

.background-shop-now{
  height: 12%;
  width: 50%;
  display: flex;
  margin-top: 10%;
}


.background-iphone{
  flex: 1.5;
  padding-top: 10px;
}

</style>