

<script>
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
.item-style {
  width: 100%;
  height: auto; /* Change to auto for better responsiveness */
  display: flex;
  flex-direction: column;
  align-items: center;
}

.view-image-service {
  width: 100%;
  height: auto; /* Change to auto for better responsiveness */
  display: flex;
  justify-content: center;
  padding: 10px 0; /* Add padding for spacing */
}

.view-content-service {
  width: 100%;
  text-align: center;
  padding: 10px 0; /* Add padding for spacing */
}

.style-title-service {
  font-weight: 750;
  font-size: 20px;
}

.style-content-service {
  font-size: 14px;
  margin-top: -15px;
}

.style-image-service {
  width: 80px;
  height: 80px;
}

.style-circle-grey {
  border-radius: 50%;
  width: 95%;
  height: 95%;
  background-color: #b4b4b4;
  display: flex;
  justify-content: center;
  align-items: center;
}

.style-circle-black {
  border-radius: 50%;
  width: 70%;
  height: 70%;
  background-color: #2F2E30;
  display: flex;
  justify-content: center;
  align-items: center;
}

.style-icon-service {
  width: 40px;
  height: 40px;
}
</style>