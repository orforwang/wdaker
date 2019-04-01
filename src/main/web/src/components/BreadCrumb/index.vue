<template>
  <el-breadcrumb class="app-breadcrumb" separator="/">
    <el-breadcrumb-item v-for="(item,index) in breadCrumbPaths" :key="item.path">
      <span :class="{'currentMenu': item.isCurrent}">{{item.title}}</span>
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script>
  import {
    mapGetters
  } from 'vuex'

  export default {
    name: 'BreadCrumb',
    created() {
      this.getBreadcrumb();
    },
    data() {
      return {
        breadCrumbPaths: null
      }
    },
    computed: {
      ...mapGetters([
        'common'
      ])
    },
    watch: {
      $route() {
        this.getBreadcrumb();
      }
    },
    methods: {
      getBreadcrumb() {
        // 过滤包含name属性的路由
        let filterPaths = this.$route.matched.filter(item => item.meta.title);
        this.breadCrumbPaths = filterPaths.map((item, index) => {
          return {
            path: item.path,
            title: item.meta.title,
            isCurrent: index === filterPaths.length - 1
          }
        });
      }
    }
  }

</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .app-breadcrumb {
    display: inline-block;
    font-size: 14px;
    height: 50px;
    line-height: 50px;
    margin-left: 10px;

    span {
      color: #97a8be;
      cursor: text;
    }

    .currentMenu {
      color: #333;
      font-weight: bold;
    }
  }

</style>
