<script>
import './style-modal-logout.scss';
import CustomButton from "@/components/base/CustomButton.vue";
export default {
  name: 'ModalNotifyToLogout',
  components: {CustomButton},
  data() {
    return {
      show: false
    }
  },
  methods: {
    openModal() {
      this.show = true;
    },
    closeModal() {
      this.show = false;
    },
    confirmLogout() {
      this.closeModal();
      this.$emit('handleLogout');
    }
  }
}
</script>

<template>
  <div class="modal" v-if="show" @click.self="closeModal">
    <div class="modal-content" style="width: 25%; border-style: solid;">
      <div class="style-title">
        <h5 style="text-align: center">You are required to log out of your account before creating a new account.</h5>
      </div>
      <div class="buttons">
        <CustomButton style="border-radius: 1px" @click="confirmLogout" text-button="Yes, Log out"/>
        <button class="btn-cancel btn" @click="closeModal">Cancel</button>
      </div>
    </div>
  </div>
  <!--  @click.self="closeModal": click ngoài modal modal sẽ close-->
</template>