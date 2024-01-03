<template>
  <nav class="d-block sidebar collapse bg-custom-color" :class="{ 'active': isSidebarOpen }">
    <div class="position-sticky">
      <div class="list-group mx-3 mt-4 bg-custom-color">

        <li class="nav-item d-flex align-items-center">
          <a href="#" class="me-2">
            <img src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (31).webp" class="rounded-circle" height="40"
              alt="Avatar" loading="lazy" />
          </a>
          <h5 class=" text-white m-0">{{ this.$store.getters.finalName }}</h5>
        </li>
        <router-link to="/mainbar" class="custom-link">
          <span class="list-group-item mt-4 py-2  text-white ">
            <i class="bi bi-speedometer2 me-1" style="color: #8F5FE8;"></i>
            <span class="label">dashboard</span>
          </span>
        </router-link>

        <div id="app">
          <button class="btn btn-success" @click="toggleCollapse" 
          style="background-color: #191C24; 
          border-color: #191C24;
          padding: 0px;">
            <span class="list-group-item py-2  text-white ">
            <i class="bi bi-list-nested me-1" style="color: #b2c237;"></i>
            <span>My activities</span>
          </span>
          </button>

          <div v-if="collapsed">
            <router-link to="/success" class="custom-link">
          <span class="list-group-item py-2  text-white ">
            <i class="bi bi-activity me-1" style="color: #FC424A;"></i>
            <span class="label">Add activity</span>
          </span>
        </router-link>

        <router-link to="/today" class="custom-link">
          <span class="list-group-item py-2  text-white ">
            <i class="bi bi-calendar-check-fill me-1" style="color: #00D25B;"></i>
            <span class="label">today activities</span>
          </span>
        </router-link>

        <router-link to="/oneweek" class="custom-link">
          <span class="list-group-item py-2  text-white ">
            <i class="bi bi-calendar-check-fill me-1" style="color: #FFAB00;"></i>
            <span class="label">one week activities</span>
          </span>
        </router-link>

        <router-link to="/onemonth" class="custom-link">
          <span class="list-group-item py-2  text-white ">
            <i class="bi bi-calendar-check-fill me-1" style="color: #0090E7;"></i>
            <span class="label">one month activities</span>
          </span>
        </router-link>

        <router-link to="/oneyear" class="custom-link">
          <span class="list-group-item py-2 ms-0 text-white ">
            <i class="bi bi-calendar-check-fill me-1" style="color: #921A6C;"></i>
            <span class="label">one year activities</span>
          </span>
        </router-link>

          </div>
        </div>

      </div>
    </div>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      collapsed: false,
    };
  },
  methods: {
    toggleCollapse() {
      this.collapsed = !this.collapsed;
    },
  },
  computed: {
    isSidebarOpen() {
      return this.$store.getters.isSidebarOpen;
    },
  },
};
</script>

<style scoped>
.sidebar {
  position: fixed;
  top: 9%;
  bottom: 0;
  height: 91%;
  transition: transform 0.3s ease;
  /* Animation for smooth transition */
  transform: translateX(-100%);
  /* Hide sidebar by default */
  left: 0;
  background-color: #191C24;
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
  width: 20%;
  z-index: 600;
}

.sidebar.active {
  transform: translateX(0);
  /* Show sidebar when active class is added */
}

.bg-custom-color .list-group-item {
  background-color: #191C24;
}

.bg-custom-color {
  background-color: #191C24;
}

.custom-link,
.custom-link a {
  text-decoration: none;
  color: inherit;
  background-color: #191C24;

}

.list-group-item {
  border: none;
  background-color: #191C24;
  padding-left: 0;
}

@media(width < 431px), (max-width: 541px) {

  .label {
    display: none;
  }

  .list-group li h5 {
    display: none;
  }

  .sidebar {
    position: absolute;
    overflow: auto;
    top: 9%;
    bottom: 0;
    height: 91%;
    transition: transform 0.3s ease;
    /* Animation for smooth transition */
    transform: translateX(-100%);
    /* Hide sidebar by default */
    left: 0;
    background-color: #191C24;
    box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
    width: 16%;
    z-index: 600;
  }
}
</style>