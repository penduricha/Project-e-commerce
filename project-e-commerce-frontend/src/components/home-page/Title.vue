<script>
import CustomButton from "@/components/base/CustomButton.vue";

export default {
  name:'ViewTitle',

  props: ['textTimeTitle','textTitle'],

  components:{
    CustomButton,
  },
  data() {
    return {

    };
  },
  methods: {
    // Đảo ngược trạng thái


  },
}
</script>

<template>
  <div style="display: flex; width: 100%; height: 100%">
    <div class="content-title">
      <div class="title-time">
        <div class="icon-red"/>
        <div class="time-content">
          <p class="style-time-content" style="font-weight: 600; color: #DB4444;">{{textTimeTitle}}</p>
        </div>
      </div>
      <div class="title-content">
        <p class="style-content-title">{{textTitle}}</p>
      </div>
    </div>
  </div>
</template>

<style>
.content-title{
  height: 100%;
  flex: 7;
}

.title-time{
  height: 40px;
  display: flex;
}

.title-content{
  height: 50%;
  width: 100%;
  align-items: flex-end;
  margin-top: 10px;
}

.style-content-title{
  font-size: 36px;
  font-weight: 630;
}

.icon-red{
  width: 20px;
  height: 100%;
  background-color: #DB4444;
  border-radius: 3px;
}

.time-content{
  width: 100%;
  height: 100%;
  /*cho cac thanh phan vertical giua*/
  padding-left: 16px;
  color: #DB4444;
}

.style-time-content {
  font-size: 16px;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
}
</style>