<template>
  <div class="app-wrapper" :class="{hideSidebar: !sidebar.opened}">
    <sidebar class="sidebar-container"></sidebar>
    <div class="main-container">
      <navbar></navbar>
      <app-main></app-main>
    </div>
  </div>
</template>

<script>
  import {
    mapGetters
	} from 'vuex'
	
  import {
    Sidebar,
    Navbar,
    AppMain
	} from '@/views/layout/components'
	
  import {
    asyncRouters
  } from '@/router'

  export default {
    name: 'Layout',
    // 初始化通过权限验证菜单
    created() {
      // 没有过滤后的路由数组信息,表明没有登陆权限
      if (!this.routes.length) {
        this.$router.push('/login');
        return false;
      }
      if (!this.sidebar.router) {
        // 获取第一个匹配的路由进入
        var path = this.findFirstRoute(this.routes[0], []);
        var currentPath = path.join('/');
        this.$router.push(currentPath);
      } else {
        this.$router.push(this.sidebar.router);
      }
    },
    beforeDestroy() {
      // 退出主界面,重新登陆,清空当前的路由和侧边栏状态
      this.$store.dispatch('CurrentSidebar', '');
      this.$store.dispatch('CurrentRouter', '');
    },
    components: {
      Sidebar,
      Navbar,
      AppMain
    },
    computed: {
      ...mapGetters([
        'sidebar',
        'routes'
      ])
    },
    watch: {
      $route() {
        let matchedRouter = this.$route.matched;
        let currentSidebar = matchedRouter[0].redirect ? matchedRouter[0].path : matchedRouter[matchedRouter.length - 1].path;
        this.$store.dispatch('CurrentSidebar', currentSidebar);
        this.$store.dispatch('CurrentRouter', this.$route.path);
      }
    },
    methods: {
      // 递归获取嵌套路由
      findFirstRoute(route, routeArray) {
        if (!route.redirect && route.children && route.children.length) {
          return routeArray.concat(route.path, this.findFirstRoute(route.children[0], routeArray));
        } else {
          return routeArray.concat(route.path);
        }
      }
    }
  }

</script>

<style lang="scss">
  .app-wrapper {
    position: relative;
    width: 100%;
    height: 100%;
  }
</style>
