import { createApp } from 'vue'
import { createStore } from 'vuex';
import App from './App.vue'
import router from './router.js';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

const app = createApp(App);

const store = createStore({

state(){
  return{
    token:'',
    userid:'',
    searchKey:''
  };
},

mutations:{
  pass(state,payload){
    state.token = payload.token;
    state.userid = payload.userid;
  },
  searchPass(state,payload){
    state.searchKey = payload;
  }
},

getters:{
  finalToken(state){
    return state.token;
  },
  finalUserid(state){
    return state.userid;
  },
  finalSearchkey(state){
    return state.searchKey;
  }
}

});



app.use(router);
app.use(store);
app.mount('#app');
