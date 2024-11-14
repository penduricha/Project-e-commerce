<script>
import {defineComponent} from "vue";
import Header from "@/components/header-footer-menu/Header.vue";
import Menu from "@/components/menu/Menu.vue";
import Footer from "@/components/header-footer-menu/Footer.vue";
import CustomButton from "@/components/base/CustomButton.vue";

export default defineComponent({
  components: {CustomButton, Footer, Menu, Header},
  name: 'ContactScreen',

  props: {},

  created(){

  },

  data(){
    return {

    }
  },

  methods: {

  }
})

</script>

<template>
  <div class="container">
    <Header style="height: 50px"/>
    <Menu style="height: 200px"/>
    <main class="style-main-contact-screen">
      <section class="contact-section">
        <div class="view-contact-information-container">
          <div class="view-contact-information">
            <div class="view-call-to-us">
              <div class="view-title-call-to-us">
                <div class="custom-icon">
                  <svg xmlns="http://www.w3.org/2000/svg" width="27" height="27" fill="currentColor" class="bi bi-telephone style-envelope-phone" viewBox="0 0 16 16">
                    <path d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.6 17.6 0 0 0 4.168 6.608 17.6 17.6 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.68.68 0 0 0-.58-.122l-2.19.547a1.75 1.75 0 0 1-1.657-.459L5.482 8.062a1.75 1.75 0 0 1-.46-1.657l.548-2.19a.68.68 0 0 0-.122-.58zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.68.68 0 0 0 .178.643l2.457 2.457a.68.68 0 0 0 .644.178l2.189-.547a1.75 1.75 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.6 18.6 0 0 1-7.01-4.42 18.6 18.6 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877z"/>
                  </svg>
                </div>
                <label class="style-label-title-contact">Call To Us</label>
              </div>
              <div class="view-content-call-to-us">
                <label class="style-content-contact">We are available 24/7, 7 days a week.</label>
                <label class="style-content-contact" style="margin-top: 15px">Phone: +8801611112222</label>
              </div>
            </div>
            <div class="view-write-to-us">
              <div class="view-title-write-to-us">
                <div class="custom-icon">
                  <svg xmlns="http://www.w3.org/2000/svg" width="27" height="27" fill="currentColor" class="bi bi-envelope style-envelope-phone" viewBox="0 0 16 16" >
                    <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1zm13 2.383-4.708 2.825L15 11.105zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741M1 11.105l4.708-2.897L1 5.383z"/>
                  </svg>
                </div>
                <label class="style-label-title-contact">Write To US</label>
              </div>
              <div class="view-content-write-to-us">
                <label class="style-content-contact">Fill out our form and we will contact<br>you within 24 hours.</label>
                <label class="style-content-contact">Emails: customer@exclusive.com</label>
                <label class="style-content-contact">Emails: support@exclusive.com</label>
              </div>
            </div>
          </div>
        </div>
        <div class="view-container-form-contact">
          <div class="view-form-contact">
            <div class="view-form-input-name">
              <div class="view-input">
                <input type="text" maxlength=90 class="style-input-grey form-control style-input-form-contact" placeholder="Your Name">
<!--                <span class="span-error" style="margin-left: 5%">Invalid</span>-->
              </div>
              <div class="view-input">
                <input type="text" maxlength=50 class="style-input-grey form-control style-input-form-contact" placeholder="Your Email">
              </div>
              <div class="view-input">
                <input type="text" maxlength=11 class="style-input-grey form-control style-input-form-contact" placeholder="Your Phone">
              </div>
            </div>
            <div class="view-text-message">
              <textarea id="w3review" name="w3review" class="style-textarea-input" placeholder="Your Massage" maxlength=1000 />
            </div>
            <div class="view-button-send">
              <CustomButton class="style-button-send" text-button="Send Massage" />
            </div>
          </div>
        </div>
      </section>
    </main>
    <Footer style="height: 1500px;"/>
  </div>
</template>

<style lang = "scss" scoped>
@import '@/assets/container';
@import '@/components/style-scss-input/input-grey';

.style-main-contact-screen{
  padding: 70px 180px 70px 180px;
  width: 1680px;
  height: 650px;
}

.contact-section{
  width: 100%;
  height: 100%;
  display: flex;
  gap: 20px;
}

.view-contact-information-container{
  flex: 1;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  padding: 50px 45px 50px 45px;
}

.view-contact-information{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.view-container-form-contact{
  flex: 2.5;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  padding: 40px 30px 40px 30px;
}

.view-form-contact{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  gap: 25px;
}

.view-form-input-name{
  flex: 0.75;
  display: flex;
  gap: 15px;
}

.view-input{
  flex: 1;
}

.view-text-message{
  flex: 3;
}

.view-button-send{
  flex: 0.65;
  display: flex;
  justify-content: right;
}

.view-write-to-us{
  flex: 1.5;
  border-top: solid darkgrey;
  padding-top: 35px;
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.view-title-write-to-us{
  width: 100%;
  flex: 1;
  display: flex;
  align-items: center;
}

.view-content-write-to-us{
  width: 100%;
  flex: 2.5;
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.custom-icon{
  height: 50px;
  width: 50px;
  border-radius: 100px;
  background-color: #DB4444;
  display: flex;
  justify-content: center;
  align-items: center;
}

.style-envelope-phone{
  color: white;
}

.style-label-title-contact{
  font-size: 20px;
  margin-left: 15px;
  font-weight: 500;
}

.style-content-contact{
  font-size: 17px;
}

.view-call-to-us{
  flex: 1;
  padding-bottom: 35px;
  display: flex;
  flex-direction: column;
  gap: 25px;
}

.view-title-call-to-us{
  flex: 1;
  display: flex;
  align-items: center;
}

.view-content-call-to-us{
  flex: 1.55;
}

//button send
.style-button-send{
  height: 100%;
  width: 220px;
}

.style-textarea-input{
  width: 100%;
  height: 100%;
  background-color: #f4f4f4;
  color: dimgrey;
  border-radius: 4px;
  font-size: 18px;
  resize: none;
  padding-left: 15px;
  padding-top: 10px;
  /* Không cho phép thay đổi kích thước */
}

.style-input-form-contact{
  width: 100%;
  height: 45px;
  font-size: 18px;
}
</style>