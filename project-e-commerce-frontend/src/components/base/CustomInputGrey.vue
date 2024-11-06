<script>
export default {
  name: 'CustomInputGrey',
  props: ['typeInput', 'textInput','maxlength', 'isDisable', 'textPlaceholder'],

  data() {
    return {
      dataInput: this.textInput,
    }
  },

  watch: {

  },

  methods: {

  }
}
</script>

<template>
  <input :type="typeInput"
         class="style-input-grey"
         v-model="dataInput"
         :maxlength="maxlength"
         :disabled="isDisable"
         :placeholder="textPlaceholder"/>
</template>

<style lang="scss" scoped>
.style-input-grey{
  background-color: #f4f4f4;
  color: dimgrey;
  padding-left: 15px;
  border-radius: 4px;
}
</style>