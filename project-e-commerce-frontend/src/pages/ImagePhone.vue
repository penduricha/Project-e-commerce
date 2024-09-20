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
    <div class="background-image-1">
      <div class="background-image-1-1">
        <div class="background-image-1-1-1">
          <div class="background-image-1-1-1-1">
            <img src="@/assets/images/1200px-Apple_gray_logo%201.png" alt="apple" style="width: 40px; height: 49px"/>
          </div>
          <div class="background-image-1-1-1-2">
            <p style="font-size: 16px; color: #FAFAFA; ">iPhone 14 Series</p>
          </div>
        </div>
        <div class="background-image-1-1-2">
          <p style="font-size: 48px; color: #FAFAFA; font-weight: bolder;">Up to 10%<br>off Voucher</p>
        </div>
        <div class="background-image-1-1-3">
          <a href="" style="font-size: 16px; color: #FAFAFA; border-bottom: solid #FAFAFA; height: 100%; text-decoration: none;">Shop Now</a>
          <svg @click="" width="24" height="24" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 15 15" style="color: #FAFAFA; margin-left: 5%; margin-top: 3%; cursor: pointer;">
            <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8"/>
          </svg>
        </div>
      </div>
    </div>
    <div class="background-image-2">
      <img src="@/assets/images/hero_endframe__cvklg0xk3w6e_large%202.png" alt="iphone" style="width: 100%; height: 100%"/>
    </div>
  </div>
</template>

<style>
.background-image{
  width: 892px;
  height: 344px;
  display: flex;
  background-color: black;
}

.background-image-1{
  flex: 1;
  padding: 40px;
  display: flex;
}

.background-image-1-1{
  flex: 1;
  margin-left: 8%;
}

.background-image-1-1-1{
  height: 25%;
  display: flex;

}

.background-image-1-1-1-1{
  flex: 2;

}

.background-image-1-1-1-2{
  flex: 8;
  align-content: center;
}

.background-image-1-1-2{
  height: 50%;
  
}

.background-image-1-1-3{
  height: 12%;
  width: 50%;
  display: flex;
  margin-top: 10%;
}


.background-image-2{
  flex: 1.5;
  padding-top: 10px;
}

</style>