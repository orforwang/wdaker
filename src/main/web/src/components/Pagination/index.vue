<template>
  <el-pagination class="pagination" @current-change="pageChange" :page-size="pageSize"
    :current-page.sync="paginationPage" background v-if="totalCount > pageSize" layout="prev, pager, next"
    :total="totalCount">
  </el-pagination>
</template>

<script>
  import CONSTANT from '@/utils/constant'

  export default {
    name: 'Pagination',
    data() {
      return {
        paginationPage: 0
      }
    },
    watch: {
      pageNumber: function () {
        this.paginationPage = this.pageNumber;
      }
    },
    props: {
      pageNumber: {
        type: Number,
        default: 1
      },
      pageSize: {
        type: Number,
        default: CONSTANT.PAGINATION.PAGE_SIZE
      },
      totalCount: {
        type: Number,
        default: 0
      }
    },
    methods: {
      pageChange(pagination) {
        this.$emit('pageChange', pagination);
      }
    }
  }

</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .pagination {
    float: right;
  }

</style>
