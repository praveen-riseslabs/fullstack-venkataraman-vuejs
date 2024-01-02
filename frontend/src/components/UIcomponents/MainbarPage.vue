<template>


    <topbar-page />

    <sidebar-page/>


  <main :class="{ 'active': isSidebarOpen }">
    
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
  },
  computed: {
    isSidebarOpen() {
      return this.$store.getters.isSidebarOpen;
    },
  },
  mounted() {
    document.body.style.backgroundColor = '#191C24'; 
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
  left: 20;
  width: 100%;
  background-color: #000000;
  color: white;
  overflow: auto;
  transition: width 0.3s ease; 
  transform: translateX(0%); 
}

main.active {
  transition: width 0.3s ease;
  width: 80%; 
}

@media(width < 1025px){
  main{
    top: 5%;
    height:95%;
  }
}

@media(width < 413px){
  main {
  position: absolute;
  top: 9%;
  bottom: 0;
  Right: 0;
  height: 91%;
  left: 20;
  width: 100%;
  background-color: #000000;
  color: white;
  overflow: auto;
  transition: width 0.3s ease; 
  transform: translateX(0%); 
}
}

@media(width < 541px){
  main{
    top: 8%;
    height:92%;
  }
}


</style>

