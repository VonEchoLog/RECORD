import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'
import Login from '../components/Login'
import AppIndex from '../components/AppIndex'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/HelloWorld',
      name: 'HelloWorld.vue',
      component: HelloWorld
    },
    {
      path: '/login',
      name: 'Login.vue',
      component: Login
    },
    {
      path: '/index',
      name: 'AppIndex.vue',
      component: AppIndex
    }
  ]
})
