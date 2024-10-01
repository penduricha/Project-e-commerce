<script>
import Header from "@/components/header-footer-menu/Header.vue";
import Menu from "@/components/menu/Menu.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import CustomService from "@/components/base/CustomService.vue";
import CustomItemService from "@/components/base/CustomItemService.vue";
import CustomItemStatistic from "@/components/base/CustomItemStatistic.vue";

export default {
  name: "AboutPage",
  components: {
    CustomItemStatistic,
    CustomItemService,
    CustomService,
    Footer,
    Menu,
    Header,
  },
  data(){
    return {
      image_statistic: {
        seller:  new URL('@/assets/image-about-svg/icon_shop.svg',import.meta.url).href,
        monthly: new URL('@/assets/image-about-svg/icon_dollar.svg',import.meta.url).href,
        customer: new URL('@/assets/image-about-svg/customer_active.svg',import.meta.url).href,
        annual_gross: new URL('@/assets/image-about-svg/icon_money_bag.svg',import.meta.url).href,
      },
    }
  }
}
</script>

<template>
  <div class="container">
    <Header style="height: 50px"/>
    <Menu style="height: 200px"/>
    <main class="main" style="padding-left: 160px; padding-right: 160px;">
      <div class="description-shop">
        <div class="description-story">
          <div style="height: 55%;width: 100%; display: flex; justify-content: space-between; flex-direction: column;">
            <p style="font-size: 54px; font-weight: 500;">Our Story</p>
            <p style="font-size: 16px;">Launched in 2015, Exclusive is South Asia’s premier online shopping <br>
              marketplace with an active present in Bangladesh. Supported <br>
              by wide range of tailored marketing, data and service solutions, <br>
              Exclusive has 10,500 sales and 300 brands and serves 3 <br>
              millions customers across the region.
              <br>
              <br>
              Exclusive has more than 1 Million products to offer, growing at a <br>
              very fast. Exclusive offers a diverse assortment in categories <br>
              ranging  from consumer.
            </p>
          </div>
        </div>
        <div class="image-description-story">
          <img src="@/assets/image-about/image-about-description.png" alt="image-description"/>
        </div>
      </div>
      <div class="view-statistic">
        <CustomItemStatistic class="style-custom-item-statistic" :path-image="image_statistic.seller" number-statistic=10 content-statistic="Sellers active our site"/>
        <CustomItemStatistic class="style-custom-item-statistic" :path-image="image_statistic.monthly" number-statistic=33 content-statistic="Monthly Product Sale"/>
        <CustomItemStatistic class="style-custom-item-statistic" :path-image="image_statistic.customer" number-statistic=45.5 content-statistic="Customer active in our site"/>
        <CustomItemStatistic class="style-custom-item-statistic" :path-image="image_statistic.annual_gross" number-statistic=25 content-statistic="Annual gross sale in our site"/>
      </div>
      <div class="view-service">
        <CustomService/>
      </div>
    </main>
    <Footer style="height: 1500px; margin-top: 6%"/>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/container';

.description-shop{
  width: 100%;
  height: 617px;
  margin-top: 50px;
  display: flex;
}

.description-story{
  flex: 1;
  display: flex;
  align-items: center;
}

.image-description-story{
  flex: 1;
}

.view-service{
  width: 100%;
  margin-top: 5%;
  height: auto;
  display: flex;
  justify-content: center;
}

.view-statistic{
  width: 100%;
  height: 200px;
  margin-top: 5%;
  display: flex;
  justify-content: space-between;
}

.style-custom-item-statistic{
  width: 20%;
  border: solid 1px grey;
  border-radius: 4px;
}

.style-custom-item-statistic:hover{
  background-color: #DB4444;
}
</style>