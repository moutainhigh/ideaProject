import Vue from 'vue'
import Router from 'vue-router'
import smsaReg from '@/components/smsaReg'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'smsaReg',
      component: smsaReg
    },
  ]
})
