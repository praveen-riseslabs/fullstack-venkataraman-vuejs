<template>
  <div>
    <header class="top-navbar">
      <nav class="navbar navbar-expand-lg bg-custom-color fixed-top">
        <!-- Container wrapper -->
        <div class="container-fluid">

          <!-- Brand -->
          <a class="navbar-brand" href="#">
            <h3>
              CORONA
            </h3>

          </a>
          <button class="openbtn ms-2" @click="toggleSidebar"><i class="bi bi-list"></i></button>

          <ul class="navbar-nav ms-auto d-flex flex-row">

            <li class="nav-item dropdown">
              <a class="nav-link me-3 me-lg-6" href="#">
                <i class="bi bi-square-fill" style="color: white;"></i>
              </a>
            </li>

            <li class="nav-item dropdown">
              <a class="nav-link me-3 me-lg-6" href="#">
                <i class="bi bi-envelope-fill" style="color: white;"></i>
              </a>
            </li>

            <!-- Notification dropdown -->
            <li class="nav-item dropdown">
              <a class="nav-link me-3 me-lg-6" href="#">
                <i class="bi bi-bell-fill" style="color: white;"></i>
              </a>
            </li>

            <!-- Avatar -->
            <li class="nav-item d-flex align-items-center">
              <a href="#" class="me-2 ">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (31).webp" class="rounded-circle" height="40"
                  alt="Avatar" loading="lazy" />
              </a>
              <h6 class=" text-white m-0 dropdown-toggle hidden-arrow">{{ this.$store.getters.finalName }}</h6>
            </li>
          </ul>
        </div>
        <!-- Container wrapper -->
      </nav>
    </header>
  </div>
</template>

<script>
export default {
  methods: {
    toggleSidebar() {
      this.$store.commit('toggleSidebar');
    },
  },
}
</script>

<style scoped>
.top-navbar {
  top: 0;
  height: 10%;
}

.custom-search {
  background-color: #191C24;
  color: white;
  border: 2px solid #2C2E33;
  width: 40vw;
  border-radius: 5px;
}


.navbar-brand h3 {
  color: white;
}

.bg-custom-color {
  background-color: #191C24;
}

.openbtn {
  font-size: 16px;
  cursor: pointer;
  background-color: #111;
  color: white;
  padding: 10px 15px;
  border: none;
}

@media(width<769px) {
  input {
    display: none;
  }

  h3 {
    display: none;
  }
}

@media(width<821px) {

  h3 {
    display: none;
  }
}

@media(width < 430px) {
  .nav-item h6 {
    display: none;
  }
}


@media(width < 1025px) {
  a h3 {
    display: none;
  }

}
</style>