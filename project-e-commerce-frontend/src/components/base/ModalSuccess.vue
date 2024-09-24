<script>
export default {
  name: 'ModalSuccess',
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
      <h5 style="text-align: center">{{textSuccess}}</h5>
      <div class="buttons">
        <button class="btn btn-success" @click="continueProgram()" style="border-radius: 10px">OK</button>
      </div>
    </div>
  </div>
<!--  <div class="modal" v-if="show" role="dialog" aria-hidden="true">-->
<!--    <div class="modal-dialog" role="document">-->
<!--      <div class="modal-content">-->
<!--        <div class="modal-header">-->
<!--          <h5 class="modal-title">{{textSuccess}}</h5>-->
<!--&lt;!&ndash;          <button type="button" class="close" data-dismiss="modal" aria-label="Close">&ndash;&gt;-->
<!--&lt;!&ndash;            <span aria-hidden="true">&times;</span>&ndash;&gt;-->
<!--&lt;!&ndash;          </button>&ndash;&gt;-->
<!--        </div>-->
<!--&lt;!&ndash;        <div class="modal-body">&ndash;&gt;-->
<!--&lt;!&ndash;          ...&ndash;&gt;-->
<!--&lt;!&ndash;        </div>&ndash;&gt;-->
<!--        <div class="modal-footer">-->
<!--&lt;!&ndash;          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>&ndash;&gt;-->
<!--          <button type="button" class="btn btn-primary">Save changes</button>-->
<!--          <button class="btn btn-success" @click="continueProgram()">OK</button>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
  <!--  @click.self="closeModal": click ngoài modal modal sẽ close-->
</template>

<style scoped>
.modal {
  position: fixed;
  z-index: 1;
  width: 100%;
  height: 100%;
  overflow: auto;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
  background-color: #fefefe;
  padding: 20px;
  border: 1px solid #888;
  width: 30%;
  display: flex;
  justify-content: center;
  justify-items: center;
}

.buttons {
  display: flex;
  margin-top: 20px;
  justify-content: center;
}
</style>