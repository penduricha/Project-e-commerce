<script>
import CustomButton from "@/components/base/CustomButton.vue";

export default {
  name: 'ModalSuccess',
  components: {CustomButton},
  props:['textSuccess'],
  data() {
    return {
      show: false,
    }
  },
  methods: {

    openModal() {
      this.show = true;
    },

    closeModal() {
      this.show = false;
    },

    async continueProgram() {
      this.closeModal();
      this.$emit('handleContinue');
    }
  }
}
</script>

<template>
  <div class="modal" v-if="show" role="dialog">
    <div class="modal-content" style="width: 20%; border-style: solid">
      <h5 style="text-align: center">Register Successfully</h5>
      <CustomButton class="buttons" @click="continueProgram()" style="border-radius: 4px" text-button="Return to Login."/>
    </div>
  </div>
</template>

<style scoped>
.modal {
  position: fixed;
  z-index: 1;
  width: 100%;
  height: 100%;
  overflow: auto;
  border-radius: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: #fefefe;
  padding: 20px;
  border: 1px solid #888;
  width: 200px;
  height: 200px;
  margin-top: 1px;
  display: flex;
  justify-content: center;
  justify-items: center;
}

.buttons {
  display: flex;
  margin-top: 20px;
  justify-content: center;
  height: 50px;
  align-items: center;
}
</style>