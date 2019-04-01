<template>
  <div class="scroll-container" ref="scrollContainer" @wheel.prevent="handleScroll">
    <div class="scroll-wrapper" ref="scrollWrapper" :style="{top: top + 'px'}">
      <slot></slot>
    </div>
  </div>
</template>

<script>
  import {
    mapGetters
  } from 'vuex'

  const delta = 0;

  export default {
    name: 'scrollBar',
    data() {
      return {
        top: 0
      }
    },
    props: {
      confirmFixed: {
        type: Boolean,
        default: true
      }
    },
    computed: {
      ...mapGetters([
        'scroll'
      ])
    },
    methods: {
      handleScroll(e) {
        const eventDelta = e.wheelDelta || -e.deltaY * 3
        const $container = this.$refs.scrollContainer
        const $containerHeight = $container.offsetHeight
        const $wrapper = this.$refs.scrollWrapper
        const $wrapperHeight = $wrapper.offsetHeight
        if (eventDelta > 0) {
          this.top = Math.min(0, this.top + eventDelta)
        } else {
          if ($containerHeight - delta < $wrapperHeight) {
            if (this.top < -($wrapperHeight - $containerHeight + delta)) {
              this.top = this.top
            } else {
              this.top = Math.max(this.top + eventDelta, $containerHeight - $wrapperHeight - delta)
            }
          } else {
            this.top = 0
          }
        }
      },
      backToTop() {
        this.top = 0;
      }
    }
  }

</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .scroll-container {
    position: relative;
    width: 100%;
    height: 100%;
    background-color: #304156;

    .scroll-wrapper {
      position: absolute;
      z-index: 1;
      width: 100% !important;
    }
  }

</style>
