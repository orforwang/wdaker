import Fly from 'flyio/dist/npm/fly';

import router from '@/router'

import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

import {
  Loading
} from 'element-ui'

import CONSTANT from '@/utils/constant'
import { Message } from 'element-ui';

// 创建fly实例
let fly = new Fly();

NProgress.configure({
  showSpinner: false
})

// 正在请求的请求url数组
let pending = [];

// 是否正在请求
let isPending = url => pending.includes(url);

// 移除完成的请求
let removePending = url => {
  let index = pending.findIndex(item => item === url);
  pending.splice(index, 1);
};

let loading = null;

fly.interceptors.request.use(request => {
  // 定义needIntercept判断是否需要拦截相同request,Promise.all中的请求不需要拦截
  if (request.headers.needIntercept && isPending(request.url)) {
    //终止请求
    return Promise.reject(new Error('重复请求已被拦截!'));
  }
  pending.push(request.url);
  request.headers.needNprogress && NProgress.start();
  return request;
});

fly.interceptors.response.use(response => {
  removePending(response.request.url);
  !pending.length && NProgress.done();
  if (response.request.headers.downloadFile) {
    return response;
  }
  loading && loading.close();
  // 将response.data转换成json对象
  'string' === typeof response.data && (response.data = JSON.parse(response.data));
  if (0 === response.data.code) {
    return response.data;
  }
  Message.error(response.data.msg || '系统错误,请联系管理员!');
  // 400代表token失效,跳转到登陆页面
  if (400 === response.data.code) {
    router.push('/login');
  }
  return response.data;
}, error => {
  removePending(error.request.url);
  !pending.length && NProgress.done();
  loading && loading.close();
  // 弹窗提示系统错误
  Message.error('系统错误,请联系管理员!');
  return Promise.reject(new Error('系统错误,请联系管理员!'));
});

let request = (url, params, method = 'POST', needIntercept = true, needNprogress = true) => {
  if ('POST' !== method) {
    if (url.includes('?')) {
      url += '&currentTime=' + new Date().getTime();
    } else {
      url += '?currentTime=' + new Date().getTime();
    }
  }
  let result = fly.request(url, params, {
    method: method,
    baseURL: CONSTANT.HOST,
    // 无超时限制
    timeout: 0,
    headers: {
      'needIntercept': needIntercept,
      'needNprogress': needNprogress,
      'token': localStorage.getItem('token')
    }
  });
  return result;
}

let downloadFile = (url, params, fileName) => {
  fly.post(url, params, {
    baseURL: CONSTANT.HOST,
    responseType: 'arraybuffer',
    // 无超时限制
    timeout: 0,
    headers: {
      'needIntercept': true,
      'downloadFile': true,
      'token': localStorage.getItem('token')
    }
  }).then(res => {
    if ('msSaveOrOpenBlob' in navigator) {
      window.navigator.msSaveOrOpenBlob(new Blob([res.data]), fileName);
    } else {
      const blob = new Blob([res.data])
      let reader = new FileReader();
      // 转换为base64,可以直接放入a标签href
      reader.readAsDataURL(blob);
      reader.onload = function (e) {
        const elink = document.createElement('a');
        elink.download = fileName;
        elink.style.display = 'none';
        elink.href = e.target.result;
        document.body.appendChild(elink);
        elink.click();
        // 释放URL 对象
        URL.revokeObjectURL(elink.href);
        document.body.removeChild(elink);
      }
    }
  });
}

let uploadFile = (url, formData, needIntercept = true) => {
  // 上传文件时,显示loading动画
  loading = Loading.service({
    lock: true,
    text: '拼命上传中',
    spinner: 'el-icon-loading',
    background: 'rgba(0, 0, 0, 0.7)'
  });
  let res = fly.post(url, formData, {
    method: 'POST',
    // 无超时限制
    timeout: 0,
    headers: {
      'Content-Type': 'multipart/form-data',
      'needIntercept': needIntercept,
      'token': localStorage.getItem('token')
    }
  });
  return res;
}

export default {
  request,
  downloadFile,
  uploadFile
}
