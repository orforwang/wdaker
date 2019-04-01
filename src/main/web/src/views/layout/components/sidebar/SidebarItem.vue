<template>
  <div class="menu-wrapper">
    <template v-for="(item, index) in menus" v-if="!item.hidden">
      <!-- 针对无children的节点,即该节点可以点击 -->
      <router-link v-if="item.redirect || !item.children" :to="getUrl(superiorTo, item.path)">
        <el-menu-item :index="getUrl(superiorTo, item.path)">
          <i :class="'fa ' + item.meta.iconClass"></i>
          <span slot="title">{{item.meta.title}}</span>
        </el-menu-item>
      </router-link>
      <!-- 有子节点children的菜单 -->
      <el-submenu v-else :index="item.path + '/' + item.children[0].path" class="isRoot">
        <template slot="title">
          <i :class="'fa ' + item.meta.iconClass"></i>
          <span>{{item.meta.title}}</span>
        </template>
        <sidebar-item :menus="item.children" :superiorTo="superiorTo  + '/' + item.path"></sidebar-item>
      </el-submenu>
    </template>
  </div>
</template>

<script>
  import {
    mapGetters
  } from 'vuex'

  export default {
    name: 'SidebarItem',
    props: {
      menus: {
        type: Array
      },
      superiorTo: {
        type: String,
        default: ''
      }
    },
    computed: {
      ...mapGetters([
        'sidebar'
      ])
    },
    data() {
      return {
        routes: []
      }
    },
    methods: {
      getUrl: (superiorTo, path) => {
        return (superiorTo + '/' + path).substr(1);
      }
    }
  }

</script>
