import { createApp } from 'vue'
import { createStore } from 'vuex';
import App from './App.vue'
import router from './router.js';
const app = createApp(App);

const store = createStore({

state(){
  return{
    token:'',
    userid:''
  };
},

mutations:{
  pass(state,payload){
    state.token = payload.token;
    state.userid = payload.userid;
  }
},

getters:{
  finalToken(state){
    return state.token;
  },
  finalUserid(state){
    return state.userid;
  }
}

});



app.use(router);
app.use(store);
app.mount('#app');
