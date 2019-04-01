<template>
  <el-menu class="navbar" mode="horizontal">
    <fold-button :toggleClick="toggleSideBar" :isOpen="!sidebar.opened"></fold-button>
    <bread-crumb></bread-crumb>
    <el-dropdown class="userInfo" trigger="click">
      <span class="userInfo el-dropdown-link">
        <img :src="avatarURL()">
        欢迎您,{{ userName }}
        <i class="el-icon-caret-bottom"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="logout()">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </el-menu>
</template>

<script>
  import {
    mapGetters
  } from 'vuex'
  import FoldButton from '@/components/FoldButton'
  import BreadCrumb from '@/components/BreadCrumb'

  import CONSTANT from '@/utils/constant'

  export default {
    components: {
      FoldButton,
      BreadCrumb
    },
    computed: {
      ...mapGetters([
        'sidebar',
        'userInfo'
      ]),
      userName() {
        return this.userInfo ? this.userInfo.name : '';
      }
    },
    methods: {
      toggleSideBar() {
        this.$store.dispatch('ToggleSideBar');
      },
      logout() {
        this.$router.push('/login');
      },
      avatarURL() {
        return CONSTANT.STATIC_DIC + this.userInfo.avatar;
      }
    }
  }

</script>

<style lang="scss" scoped>
  .userInfo {
    display: flex;
    align-items: center;

    img {
      width: 30px !important;
      height: 30px !important;
      margin-top: 0 !important;
      margin-right: 5px;
    }
  }

</style>
