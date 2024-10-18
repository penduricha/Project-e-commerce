<script>
import './style-custom-item.scss';
export default {
  name: 'CustomItemService',
  //dinh nghia cac doi tuong cua props
  props: ['titleService','contentService','pathImage'],
  // props: {
  //   titleService: {
  //     type: String,
  //     required: true
  //   },
  //   contentService: {
  //     type: String,
  //     required: true
  //   },
  //   pathImage: {
  //     type: String,
  //     required: true
  //   }
  // }
  data(){
    return{

    }
  },
  methods: {

  }
}
</script>
<template>
  <div class="item-style">
    <div class="view-image-service">
      <div class="style-image-service">
        <div class="style-circle-grey">
          <div class="style-circle-black">
            <img :src="pathImage" class="style-icon-service" alt="image">
          </div>
        </div>
      </div>
    </div>
    <div class="view-content-service">
      <p class="style-title-service">{{ titleService }}</p>
      <p class="style-content-service">{{ contentService }}</p>
    </div>
  </div>
</template>
<style>

</style>