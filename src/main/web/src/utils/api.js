import http from '@/utils/http'
import CONSTANT from '@/utils/constant'

const LOGIN = {
  VERIFICATION: uuid => CONSTANT.HOST + `/captcha.jpg?uuid=${uuid}`,
  LOGIN: data => http.request('/sys/login', data)
}

export default {
  LOGIN
}
