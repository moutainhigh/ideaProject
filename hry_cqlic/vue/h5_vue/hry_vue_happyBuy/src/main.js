// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import $ from 'jquery'
import App from './App'
import axios from 'axios'
import router from './router'
import store from './store';
import '@/utils/axios';
import hrymd5 from '@/utils/hrymd5';
import expression_input from '@/utils/expression_input'
// import axiosPublic from './assets/libs/axiosPub.js';
import 'mint-ui/lib/style.css';
import './assets/css/my-mint.css';
import './assets/css/reset.css'
import './assets/css/border.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import contryUtil from '@/utils/contryList';
import MintUI from 'mint-ui'
import { Header } from 'mint-ui';
import { Field } from 'mint-ui';
import { Tabbar, TabItem } from 'mint-ui';
import { Swipe, SwipeItem } from 'mint-ui';
import { Actionsheet } from 'mint-ui';
import { Search } from 'mint-ui';
import { Cell } from 'mint-ui';
import { Button } from 'mint-ui';
import { Toast } from 'mint-ui';
import { Badge } from 'mint-ui';
import { Loadmore } from 'mint-ui';//上拉下拉刷新
import { Popup } from 'mint-ui';
import { CellSwipe } from 'mint-ui';
Vue.prototype.$ajax=axios;
Vue.prototype.INPUT = expression_input;
Vue.prototype.hrymd5 = hrymd5;
Vue.prototype.contryUtil = contryUtil;
Vue.use(router)
Vue.use(MintUI);
// Vue.use(axiosPublic);
Vue.use(ElementUI);

Vue.component(Header.name, Header);
Vue.component(Field.name, Field);
Vue.component(Tabbar.name, Tabbar);
Vue.component(TabItem.name, TabItem);
Vue.component(Swipe.name, Swipe);
Vue.component(SwipeItem.name, SwipeItem);
Vue.component(Actionsheet.name, Actionsheet);
Vue.component(Search.name, Search);
Vue.component(Cell.name, Cell);
Vue.component(Button.name, Button);
Vue.component(Badge.name, Badge);
Vue.component(Loadmore.name, Loadmore);
Vue.component(Popup.name, Popup);
Vue.component(CellSwipe.name, CellSwipe);



Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
