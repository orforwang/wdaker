import Vue from 'vue'

// 引入elementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

import App from './App'
import router from './router'
import store from './store'

// 将moment.js引入并放置在vue原型链上
import moment from 'moment'
Vue.prototype.moment = moment

// 引入公用css
import '@/styles/base.scss'
import 'font-awesome/css/font-awesome.css'

// 引入并遍历设置全局filter
import * as filters from '@/utils/filter'

Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key]);
});

Vue.config.productionTip = false

// 设置动态路由
import {
  asyncRouters
} from '@/router'

// 递归解决权限问题
function filterRoutes(routes, competence) {
  let hasPermission;
  for (let i = routes.length - 1; i >= 0; i--) {
    let route = JSON.parse(JSON.stringify(routes))[i];
    if (route.hidden) {
      continue;
    }
    hasPermission = (route.meta && route.meta.alwaysShow) || (route.meta && route.meta.competence && competence.includes(route.meta.competence));
    if (route.children) {
      filterRoutes(route.children, competence);
      !hasPermission && route.children.length == 0 && routes.splice(i, 1);
    } else {
      !hasPermission && routes.splice(i, 1);
    }
  }
}

router.beforeEach((to, from, next) => {
  if (store.getters.routes.length) {
    next();
  } else {
    if (store.getters.competence) {
      let [...actualMenus] = asyncRouters;
      filterRoutes(actualMenus, store.getters.competence);
      store.dispatch('GenerateRoutes', actualMenus).then(() => {
        router.addRoutes(actualMenus);
        next({
          ...to,
          replace: true
        });
      })
    } else {
      // 如果没有competence,则跳转到登陆界面进行登陆
      if (to.path === '/login') {
        next();
      } else {
        next('/login');
      }
    }
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {
    App
  },
  template: '<App/>'
})
