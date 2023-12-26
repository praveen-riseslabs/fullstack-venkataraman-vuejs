<template>

      <nav id="mysidebar" class="d-md-block sidebar collapse bg-custom-color">

        <div class="position-sticky">
          <div class="list-group mx-3 mt-4 bg-custom-color">

            <li class="nav-item d-flex align-items-center">
              <a href="#" class="me-2">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (31).webp" class="rounded-circle" height="40"
                  alt="Avatar" loading="lazy" />
              </a>
              <h5 class=" text-white m-0">username</h5>
            </li>
            <router-link to="/dashboard" class="custom-link">
              <span class="list-group-item mt-4 py-2  text-white ">
                <i class="bi bi-speedometer2 me-1" style="color: #8F5FE8;"></i>
                dashboard
              </span>
            </router-link>

            <router-link to="/success" class="custom-link">
              <span class="list-group-item py-2  text-white ">
                <i class="bi bi-activity me-1" style="color: #FC424A;"></i>
                Add activity
              </span>
            </router-link>

            <router-link to="/today" class="custom-link">
              <span class="list-group-item py-2  text-white ">
                <i class="bi bi-calendar-check-fill me-1" style="color: #00D25B;"></i>
                today activities
              </span>
            </router-link>

            <router-link to="/oneweek" class="custom-link">
              <span class="list-group-item py-2  text-white ">
                <i class="bi bi-calendar-check-fill me-1" style="color: #FFAB00;"></i>
                one week activities
              </span>
            </router-link>

            <router-link to="/onemonth" class="custom-link">
              <span class="list-group-item py-2  text-white ">
                <i class="bi bi-calendar-check-fill me-1" style="color: #0090E7;"></i>
                one month activities
              </span>
            </router-link>

            <router-link to="/oneyear" class="custom-link">
              <span class="list-group-item py-2 ms-0 text-white ">
                <i class="bi bi-calendar-check-fill me-1" style="color: #921A6C;"></i>
                one year activities
              </span>
            </router-link>

          </div>
        </div>
      </nav>

</template>

<style scoped>
.sidebar {
  position: fixed;
  top: 9%;
  bottom: 0;
  height: 91%;
  left: 0;
  background-color: #191C24;
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
  width: 20%;
  z-index: 600;
}

.custom-search {
  background-color: #191C24;
  color: white;
  border: 2px solid #2C2E33;
  width: 40vw;
  margin-left: 10vw;
  border-radius: 5px;
}


.navbar-brand h3 {
  color: white;
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


@media(width < 1025px){
  .sidebar{
    top: 5%;
    height:95%;
  }
}



</style>