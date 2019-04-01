<template>
  <section id="app-main">
    <scroll-bar ref="scrollBar" style="background-color:#F0F3F4">
      <transition name="fade" mode="out-in">
        <router-view />
      </transition>
    </scroll-bar>
  </section>
</template>

<script>
  import ScrollBar from '@/components/ScrollBar'

  export default {
    name: 'AppMain',
    components: {
      ScrollBar
    },
    watch: {
      $route() {
        // 路由切换的时候回到顶部
        this.$refs.scrollBar.backToTop();
      }
    }
  }

</script>

<style rel="stylesheet/scss" lang="scss" scoped>
</style>
