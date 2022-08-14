/*
 * 二次封装axios
 * time:2019年02月15日15:26:43
 * author: wuxiaoyuan
 * httpServer  为post 请求
 * fdServer    为上传文件、图片类的请求
 */
import axios from 'axios';
import Cookies from 'js-cookie';
import qs from 'qs'
// import VueRouter from 'vue-router';
import Vue from 'vue';
import router from '@/router'
import { MessageBox } from 'mint-ui';
// import {routers} from '@/router/router';
// import {router} from '@/router/index'

/*
*  post 封装  拦截器
*  time： 2018年08月09日14:13:45
*/
// var CancelToken = axios.CancelToken;
const instancePost = axios.create({
  baseURL: "/", //
  method:"post",
  responseType: "json",
  withCredentials: true, // 是否允许带cookie这些
  headers: {'content-type': 'application/x-www-form-urlencoded'},
  // cancelToken: new CancelToken(function (cancel) {
  //   console.log(cancel)
  //   // alert('请求中段');
  // })

});
instancePost.interceptors.response.use(response => {
  // Spin.hide();
  // if(response.data.status==80){
  //   router.replace({
  //     name: 'login',
  //     query: {redirect: router.currentRoute.fullPath}
  //   })
  // }
  // router.beforeEach((to, from, next) => {
  //   if(response.data.status==80){
  //     alert('1')
  //     next({
  //       name: 'login'
  //     });
  //   }
  // })
  return response
}, error => {
  console.log(error)
  if (error && error.response) {
    // console.log(error+'-----')
    // console.log(Message)
    switch (error.response.status) {
      case 400:
        MessageBox('温馨提示', '错误请求');
        break;
      case 401:
        MessageBox('温馨提示', '请重新登录');
        // err.message = '未授权，请重新登录'
        break;
      case 403:
        router.replace({
          name: 'error-403',
          query: {redirect: router.currentRoute.fullPath}
        })
        MessageBox('温馨提示', '拒绝访问');
        break;
      case 404:
        console.log(router.push)
        router.replace({
          name: 'error-404',
          query: {redirect: router.currentRoute.fullPath}
        })
        // Message.error('请求错误,未找到该资源');
        // err.message = '请求错误,未找到该资源'
        break;
      case 405:
        MessageBox('温馨提示', '请求方法未允许');
        // err.message = '请求方法未允许'
        break;
      case 408:
        MessageBox('温馨提示', '请求超时');
        // err.message = '请求超时'
        break;
      case 500:
        router.replace({
          name: 'error-500',
          query: {redirect: router.currentRoute.fullPath}
        })
        break;
      case 501:
        MessageBox('温馨提示', '网络未实现');
        // err.message = '网络未实现'
        break;
      case 502:
        MessageBox('温馨提示', '网络错误');
        // err.message = '网络错误'
        break;
      case 503:
        MessageBox('温馨提示', '服务不可用');
        // err.message = '服务不可用'
        break;
      case 504:
        MessageBox('温馨提示', '网络超时');
        // err.message = '网络超时'
        break;
      case 505:
        MessageBox('温馨提示', 'http版本不支持该请求');
        // err.message = 'http版本不支持该请求'
        break;
      default:
      MessageBox('温馨提示', '连接错误${err.response.status}');
        // err.message = `连接错误${err.response.status}`
    }
  } else {
    MessageBox('温馨提示', '连接到服务器失败');
    // error.message = "连接到服务器失败"
  }
  // Message.error(error.response.statusText)
  // return Promise.resolve(error.response)
})

instancePost.interceptors.request.use(
  config => {
    // console.log(config.method);
    // console.log(Spin);
    // Spin.show({
    //   render: (h) => {
    //     return h('div', [
    //       h('Icon', {
    //         'class': 'demo-spin-icon-load',
    //         props: {
    //           type: 'load-c',
    //           size: 18
    //         }
    //       }),
    //       h('div', '加载中....')
    //     ])
    //   }
    // })
    // 在发送请求之前做某件事
    // if (
    //   config.method === "post" ||
    //   config.method === "put" ||
    //   config.method === "delete"
    // ) {
    //   // 序列化
    //   config.data = qs.stringify(config.data);
    // }
    //
    // // 若是有做鉴权token , 就给头部带上token
    // if (localStorage.token) {
    //   config.headers.Authorization = localStorage.token;
    // }
    // console.log(config)
    return config;
  },
  error => {
    alert('请求错误');
    return Promise.reject(error.data.error.message);
  }
);

var upload = axios.create({
  headers: {'content-Type': 'multipart/form-data'}
});

var instanceGet=axios.create({
  baseURL: "/", //
  method:"get",
  responseType: "json",
  withCredentials: true, // 是否允许带cookie这些
  headers: {'Access-Control-Allow-Origin': '*'},
})
// var url=""+url;
const httpServer = (url,params) => { return instancePost.post(url, qs.stringify(params)).then(res =>{
    return res
});};

const httpGet = (url,params) => { return instanceGet.get(url, qs.stringify(params)).then(res =>{
  return res.data
});};

const fdServer = (url,params) => {return upload.post(url, params).then(res => res);};

export default {
  install: function(Vue, Option) {
    Object.defineProperty(Vue.prototype, "$httpServer", { value: httpServer });
    Object.defineProperty(Vue.prototype, "$fdServer", { value: fdServer });
    Object.defineProperty(Vue.prototype, "$httpGet", { value: httpGet });
  }
};
