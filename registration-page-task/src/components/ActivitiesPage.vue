<template>
<nav class="navbar">
    <div class="navbar-links">
      <div class="routerLink"><router-link to="/today">Today</router-link></div>
      <div class="routerLink"><router-link to="/oneweek">One Week</router-link></div>
      <div class="routerLink"><router-link to="/onemonth">One Month</router-link></div>
      <div class="routerLink"><router-link to="/oneyear">One Year</router-link></div>
    </div>
    <div class="search-bar">
      <input type="text" v-model="searchKeyword" placeholder="Search..." class="search-input">
      <button @click="searchItems" class="search-button">Search</button>
    </div>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      showNavBar: true,
    };
  },
  created() {
    this.$router.beforeEach((to) => {
      this.showNavBar = to.path === '/today' ||
       to.path === '/oneweek' ||
        to.path === '/onemonth' ||
         to.path === '/oneyear';
    });
  },
};
</script>

<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 20px 0;
}

.navbar-links .routerLink {
  background-color: aquamarine;
  margin: 0 10px;
  padding: 8px 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
  cursor: pointer;
}

.search-bar {
  display: flex;
  align-items: center;
}

.search-input {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-right: 10px;
}

.search-button {
  padding: 8px 16px;
  background-color: aquamarine;
  border: 1px solid #ccc;
  border-radius: 4px;
  cursor: pointer;
}
</style>
