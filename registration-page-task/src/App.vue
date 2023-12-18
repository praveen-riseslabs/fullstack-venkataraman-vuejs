<template>
  <div id="app">
    <!-- Conditionally render NavBar based on the route -->
    <ActivitiesPage v-if="showNavBar" />
    <router-view />
  </div>
</template>

<script>
import ActivitiesPage from './components/ActivitiesPage.vue';

export default {
  components: {
    ActivitiesPage,
  },
  data() {
    return {
      showNavBar: false,
    };
  },
  created() {
    this.$router.beforeEach((to) => {
      // Check if the route path requires the NavBar to be shown
      this.showNavBar = to.path === '/today' || to.path === '/oneweek' || to.path === '/onemonth' || to.path === '/oneyear';
    });
  },
};
</script>

<style>
/* Your global styles */
</style>
