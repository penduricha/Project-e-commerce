<script>
import CustomButton from "@/components/base/CustomButton.vue";
import './style-modal-logout.scss';
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
    <div class="modal-content" >
      <h5 style="text-align: center">Register Successfully</h5>
      <CustomButton @click="continueProgram()" class="style-button-continue" text-button="Return to Login."/>
    </div>

  </div>
</template>

<style lang="scss" scoped>
.style-button-continue{
  border-radius: 2px;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}
</style>
