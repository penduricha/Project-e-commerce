<script>
import './style-modal-logout.css';
import CustomButton from "@/components/base/CustomButton.vue";
export default {
  name: 'ModalConfirmLogout',
  components: {
    CustomButton
  },
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
    <div class="modal-content" style="width: 21%; border-style: solid">
      <h5 style="text-align: center">Are you sure you want to log out ?</h5>
      <div class="buttons">
        <CustomButton style="border-radius: 1px" @click="confirmLogout" text-button="Yes, Log out"/>
        <button class="btn-cancel btn" @click="closeModal">Cancel</button>
      </div>
    </div>
  </div>
  <!--  @click.self="closeModal": click ngoài modal modal sẽ close-->
</template>

