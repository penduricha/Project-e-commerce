<script>
export default {
  name: "CustomButtonWhite",
  props: ['textButton']
}
</script>

<template>
  <button class="style-button-white">{{textButton}}</button>
</template>

<style lang="scss" scoped>
.style-button-white{
  width: 100%;
  height: 100%;
  border: solid gray;
  font-size: 16px;
  font-weight: 500;
  border-radius: 4px;
}
</style>