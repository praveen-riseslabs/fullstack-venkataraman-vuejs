import { createApp } from 'vue'
import { createStore } from 'vuex';
import App from './App.vue'
import router from './router.js';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

const app = createApp(App);

const store = createStore({

  state() {
    return {
      token: '',
      userid: '',
      searchKey: '',
      isSidebarOpen: false,
      name: ''
    };
  },

  mutations: {
    pass(state, payload) {
      state.token = payload.token;
      state.userid = payload.userid;
      state.name = payload.userName;
    },
    toggleSidebar(state) {
      state.isSidebarOpen = !state.isSidebarOpen;
    }
  },

  getters: {
    finalToken(state) {
      return state.token;
    },
    finalUserid(state) {
      return state.userid;
    },
    finalName(state) {
      return state.name;
    },
    isSidebarOpen(state) {
      return state.isSidebarOpen;
    }
  }

});



app.use(router);
app.use(store);
app.mount('#app');
