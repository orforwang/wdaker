<template>
  <scroll-bar>
    <el-menu mode="vertical" :default-active="sidebar.active" unique-opened :collapse="isCollapse"
      background-color="#304156" text-color="#fff" active-text-color="#409EFF">
      <sidebar-item :menus="menus" superiorTo=""></sidebar-item>
    </el-menu>
  </scroll-bar>
</template>

<script>
  import {
    mapGetters
  } from 'vuex'

  import SidebarItem from './SidebarItem'
  import ScrollBar from '@/components/ScrollBar'

  export default {
    components: {
      SidebarItem,
      ScrollBar
    },
    computed: {
      menus() {
        return this.routes
      },
      ...mapGetters([
        'sidebar',
        'routes'
      ]),
      isCollapse() {
        return !this.sidebar.opened
      }
    }
  }

</script>
