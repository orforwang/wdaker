import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import LoginComponent from '@/views/login'
import Layout from '@/views/layout'

export const constantRouters = [{
    path: '/login',
    component: LoginComponent
  },
  {
    path: '/',
    component: Layout
  },
  {
    path: '/404',
    component: () => import('@/views/errorPage/404'),
  }
];

export default new Router({
  routes: constantRouters
});

export const asyncRouters = [{
  path: '/workStatistics',
  component: Layout,
  meta: {
    title: '工作统计',
    iconClass: 'fa-dashboard'
  },
  children: [{
    path: 'statisticsChart',
    component: resolve => require(['@/views/workStatistics/statisticsChart'], resolve),
    meta: {
      title: '统计图表',
      alwaysShow: true
    }
  }]
  //  {
  //   path: 'workOrderRecord',
  //   component: resolve => require(['@/views/workStatistics/workOrderRecord'], resolve),
  //   meta: {
  //     title: '工单记录',
  //     alwaysShow: true
  //   }
  // }, {
  //   path: 'taskRecord',
  //   component: resolve => require(['@/views/workStatistics/taskRecord'], resolve),
  //   meta: {
  //     title: '任务记录',
  //     alwaysShow: true
  //   }
  // }, {
  //   path: 'approvalRecord',
  //   component: resolve => require(['@/views/workStatistics/approvalRecord'], resolve),
  //   meta: {
  //     title: '审批记录',
  //     alwaysShow: true
  //   }
  // }]
}, {
  path: '*',
  redirect: '/404',
  hidden: true
}]
