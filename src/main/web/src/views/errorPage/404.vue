<template>
  <div id="wrapper">
    <img src="/static/images/404.png" />
    <div>
      <h3>温馨提示：您访问的地址不存在或没有权限访问！</h3>
      <h3>{{ countdown }}s后返回主页</h3>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Error404',
    data() {
      return {
        currentTime: 0,
        timeOut: 3,
      }
    },
    computed: {
      countdown() {
        return this.timeOut - this.currentTime;
      }
    },
    mounted() {
      let that = this;
      let autoPlay = setInterval(function () {
        if (that.currentTime >= that.timeOut) {
          clearInterval(autoPlay);
          that.$router.push('/');
          return false;
        }
        that.currentTime += 1;
      }, 1000);
    }
  }

</script>

<style lang="scss" scoped>
  #wrapper {
    width: 100%;
    height: 100%;
    background: #c8bdc4;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    &>img {
      margin-bottom: 30px;
    }

    h3 {
      text-align: center;
    }
  }

</style>
