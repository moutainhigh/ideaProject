import axios from 'axios'
import qs from 'qs'
import Vue from 'vue'
import router from '@/router/index'
// import { Loading } from 'element-ui';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
//默认请求头  并 指向原型
axios.defaults.withCredentials = true; //意思是携带cookie信息,保持session的一致性

axios.defaults.baseURL = 'http://www.bbgo.vip/app';
Vue.prototype.path = 'http://www.bbgo.vip/app';
Vue.prototype.pathImg = process.env.PATH_IMG;
// let loading = null;
let loadingOpenCount = 0;
axios.interceptors.request.use(function (config) {
  // if (config.showLoading != false && config.method != 'OPTIONS') {
  //   []
  //   loadingOpenCount++;
  // }
  // if (config.showLoading != false && loadingOpenCount == 1 && config.method != 'OPTIONS') {
  //   open();
  // }
  if (sessionStorage.getItem('token')) {
    config.headers.common['token'] = sessionStorage.getItem('token');
  }
  if (config.method === 'post') {
    config.data = qs.stringify(config.data);
  }
  if (config.method === 'get') {
    config.url = config.url + "?" + qs.stringify(config.data);
  }
  if (config.method === 'upload') {
    config.method = 'post';
  }
  return config
}, function (error) {
  return Promise.reject(error)
});
axios.interceptors.response.use(
  response => {
    // if (response.config.showLoading != false && response.config.showLoading != false && response.config.method != 'OPTIONS') {
    //   loadingOpenCount--;
    //   if (loadingOpenCount == 0) {
    //     setTimeout(function () {
    //       close();
    //     }, 0.3 * 1000)
    //   }
    // }
    if(response.headers.authorization){
      sessionStorage.setItem('token',response.headers.authorization) ;
    }
    //判断返回的数据里面code资源
    else if (!response || response.data.code == 401) {
      sessionStorage.clear();
      router.push({
        path: '/login', name: 'login'
        , params: { 'error': response.data.tokenMsg }
      });
      close();
    }
    return response;
  },
  error => {
    close();
    // //  	请求异常处理
    //             switch (error.response.status) {
    //                 case 401:
    //                     sessionStorage.clear();
    //                     router.push({path:'/Login',name:'Login'
    //                     ,params: {'error': 'token失效,请重新登陆'}
    //                     })
    //                    }
    if(error.response == undefined){
      return {data: {success: false, msg: '请求异常'}};
    }else{
      return checkStatus(error.response);
    }

    if (error.response && error.response.data) {
      return Promise.reject(error.response.data)
    }
    // 返回接口返回的错误信息
  });
// function getConfig(config){
// 	return config;
// }
// function open() {
//   loading = Loading.service({
//     lock: false,
//     text: '拼命加载中...',
//     spinner: 'el-icon-loading',
//     background: 'rgba(0, 0, 0, 0.7)'
//   });
// }
// function close() {
//   if (loading) {
//     loading.close();
//   }
// }

// 检查状态
function checkStatus(response) {
  let data = {data: {}};
  data.data['success'] = false;
  switch (response.status) {
    case 400:
      data.data['msg'] = '请求错误'
      break

    case 401:
      data.data['msg'] = '未授权，请登录'
      break

    case 403:
      data.data['msg'] = '拒绝访问'
      break

    case 404:
      data.data['msg'] = '请求地址出错'
      break

    case 408:
      data.data['msg'] = '请求超时'
      break

    case 500:
      data.data['msg'] = '服务器内部错误'
      break

    case 501:
      data.data['msg'] = '服务未实现'
      break

    case 502:
      data.data['msg'] = '网关错误'
      break

    case 503:
      data.data['msg'] = '服务不可用'
      break

    case 504:
      data.data['msg'] = '网关超时'
      break

    case 505:
      data.data['msg'] = 'HTTP版本不受支持'
      break

    default:
      data.data['msg'] = '异常请求';
  }
  return data;
}
