<template>
  <i class="fa fa-bars foldbutton" :class="{'isOpen': isOpen}" @click="toggleClick"></i>
</template>

<script>
  export default {
    name: 'foldButton',
    props: {
      toggleClick: {
        type: Function,
        default: null
      },
      isOpen: {
        type: Boolean,
        default: true
      }
    }
  }

</script>

<style lang="scss" scoped>
  .foldbutton {
    display: line-block;
    float: left;
    height: 50px;
    line-height: 50px;
    cursor: pointer;
    transform: rotate(0deg);
    transition: 0.38s;
    transform-origin: 50% 50%;
  }

  .foldbutton.isOpen {
    transform: rotate(90deg);
  }

</style>
