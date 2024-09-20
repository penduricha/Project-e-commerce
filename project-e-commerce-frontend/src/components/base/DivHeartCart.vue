<script>

export default {
  name: 'DivHeartCart',
  components:{

  },
  data(){
    return{

    }
  },
  methods: {

  }
}
</script>

<template>
  <div class="menu-icon">
    <div class="menu-heart">
      <svg fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" style="width: 24px; height: 24px; cursor: pointer;">
        <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
      </svg>
    </div>
    <div class="menu-cart">
      <svg fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16" style="width: 30px; height: 30px; cursor: pointer;">
        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
      </svg>
    </div>
  </div>
</template>

<style scoped lang="scss">
.menu-icon{
  width: 40%;
  height: 80%;
  display: flex;
  border: solid black;
}

.menu-heart{
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0;
  border: solid black;
}

.menu-cart{
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0;
  border: solid black;
}
</style>