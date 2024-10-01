<script>
import './style-custom-item.css';
export default {
  name: 'CustomItemStatistic',
  // props: {
  //   content_Statistic: {
  //     type: String,
  //     required: true,
  //   },
  //
  //   number_Statistic: {
  //     type: Number,
  //     required: true,
  //   },
  //
  //   path_image: {
  //     type: String,
  //     required: true,
  //   },
  // },
  props: ['contentStatistic', 'numberStatistic', 'pathImage'],


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
      <p class="style-title-service style-title-hover" style="font-size: 32px;">{{ numberStatistic }}</p>
      <p class="style-content-service style-content-hover" style="font-size: 16px;">{{ contentStatistic }}</p>
    </div>
  </div>
</template>

<style>

.item-style:hover{
  color: white;
}

</style>