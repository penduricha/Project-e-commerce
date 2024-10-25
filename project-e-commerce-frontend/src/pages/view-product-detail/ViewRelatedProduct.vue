<script>
import Title from "@/components/home-page/Title.vue";

export default {

  name: 'ViewRelatedProduct',

  components: {
    Title

  },

  props: ['productTypeId'],

  data(){
    return{
      products_related_by_productTypeId: [],
    }
  }

}
</script>

<template>
  <div style="width: 100%; height: 100%;">
    <div class="view-title">
      <Title text-time-title="Related Item"/>
    </div>
  </div>
</template>

<style lang="scss">

.view-title{
  width: 100%;
  height: 103px;
  display: flex;
}

</style>