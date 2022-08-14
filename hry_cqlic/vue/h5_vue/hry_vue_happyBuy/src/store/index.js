import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios'
Vue.use(Vuex);
let defaultLogined = false;
try {
  if (sessionStorage.IsLogin) {
    defaultLogined = sessionStorage.IsLogin;
  }
} catch (e) { }

let store = new Vuex.Store({
    
})
export default store
