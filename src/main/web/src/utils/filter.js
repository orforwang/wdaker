import Vue from 'vue'

// 创建Vue实例,使用原型链上的moment方法
const that = new Vue({})

let formatTime = function (value, pattern) {
  if (value) {
    return that.moment(value).format(pattern);
  } else {
    return '';
  }
}

export {
  formatTime
}
