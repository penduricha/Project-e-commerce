<template>
  <div>
    <component :is="currentComponent" />
    <div style="width: 80.15%; background-color: black; display: flex; justify-content: center; align-items: center;">
      <div class="group-button">
        <div class="button-in-group" v-for="(button, index) in buttons" :key="index">
          <button class="button-pagination" @click="changeComponent(index)"/>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Samsung from "@/pages/list-image-advertise/Samsung.vue";
import Iphone from "@/pages/list-image-advertise/Iphone.vue";
import Xiaomi from "@/pages/list-image-advertise/Xiaomi.vue";
import Oppo from "@/pages/list-image-advertise/Oppo.vue";
import Ipad from "@/pages/list-image-advertise/Ipad.vue";

export default {
  name: 'MyComponent',
  components: {
    Samsung,
    Iphone,
    Xiaomi,
    Oppo,
    Ipad,
  },
  data() {
    return {
      currentComponent: 'Iphone',
      //ds components
      //theo trinh tu paginations
      buttons: ['Iphone','Xiaomi','Samsung','Oppo','Ipad'],
    }
  },
  methods: {
    changeComponent(index) {
      this.currentComponent = this.buttons[index];
    },
  },
};
</script>
<style>

.group-button{
  width: 100px;
  height: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button-in-group{
  width: 15px;
  height: 15px;
  background-color: transparent;
  border-radius: 100px;
  border: none;
  display: flex;
  justify-content: center;
  align-items: center;
}

.button-pagination{
  border-radius: 200px;
  border: none;
  width: 80%;
  height: 80%;
  background-color: grey;
}
</style>