<script>
import CustomButton from "@/components/base/CustomButton.vue";

export default {
  name:'Title',

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
          <p class="style-time-content">{{textTimeTitle}}</p>
        </div>
      </div>
      <div class="title-content">
        <p class="style-content-title">{{textTitle}}</p>
      </div>
    </div>
<!--    <div class="content-button-view">-->
<!--      <CustomButton  @click=""  style="width: 100%; height: 50%; margin-top: 30%;" text-button="View All"/>-->
<!--      &lt;!&ndash; v-if="getTextTitle() !== 'Flash Sales'" &ndash;&gt;-->
<!--    </div>-->
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

}

.style-time-content {
  color: #DB4444;
  font-size: 16px;
  width: 100%;
  height: 100%;
  display: flex;               /* Enable Flexbox */
  align-items: center;        /* Center content vertically */
  /*justify-content: center;    /* Center content horizontally (optional) */
  /* Remove default margin */
  font-weight: 500;
}
</style>