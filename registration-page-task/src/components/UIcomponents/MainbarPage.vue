<template>
  <div>
    <topbar-page/>
      <sidebar-page/>
  </div>
  <main>
    <router-view></router-view>
  </main>
</template>

<script>
import TopbarPage from './TopbarPage.vue';
import SidebarPage from './SidebarPage.vue';

export default {
  components: {
    TopbarPage,
    SidebarPage
  }
}
</script>

<style scoped>

main {
  position: absolute;
  top: 9%;
  bottom: 0;
  Right: 0;
  height: 91%;
  left: 20%;
  background-color: #000000;
  color: white;
  overflow: auto;
}

@media(width < 1025px){
  main{
    top: 5%;
    height:95%;
  }
}

@media(width<767px){
  main{
    left:0;
    width:100%;
  }
}

</style>