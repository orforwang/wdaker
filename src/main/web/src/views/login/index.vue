<template>
	<div id="login">
		<p>贵州农信后台管理平台</p>
		<el-form ref="form">
			<div class="inlineP">
                <!-- <span>用户名</span> -->
                <el-input type="text" v-model="loginForm.username" @keyup.enter.native="login" auto-complete="off">
					<i slot="prefix" class="el-input__icon fa fa-user-o"></i>
				</el-input>
            </div>
			<div class="inlineP">
                <!-- <span>密码</span> -->
                <el-input type="password" v-model="loginForm.password" @keyup.enter.native="login" auto-complete="off">
					<i slot="prefix" style="font-size: 1.3em;margin-top: 2px;" class="el-input__icon fa fa-lock"></i>
				</el-input>
            </div>
			<div class="inlineP">
                <!-- <span>验证码</span> -->
				<div class="verificationDiv">
					<el-input type="text" v-model="loginForm.code" maxlength="5" @keyup.enter.native="login" auto-complete="off">
						<i slot="prefix" style="margin-top: 2px;" class="el-input__icon fa fa-key"></i>
					</el-input>
					<img id="captchaImage" @click="verification" :src="imgSrc" />
				</div>
            </div>
			<div class="inlineP">
				<!-- <span></span> -->
				<el-button style="width: 100%;" type="primary" :disabled="enableLogin" @click="login">登&nbsp;录</el-button>
			</div>
		</el-form>
	</div>
</template>

<script>
import API from '@/utils/api'

import md5 from 'js-md5'

import { asyncRouters } from '@/router'

export default {
	name: 'Login',
	data(){
		return {
			loginForm: {
				username: '',
				password: '',
				uuid: '',
				code:''
			},
			imgSrc: ''
		}
	},
	computed: {
		enableLogin: function(){
			return ('' === this.loginForm.username.trim() || '' === this.loginForm.password.trim() || '' === this.loginForm.code.trim());
		}
	},
	mounted(){
		this.verification();
	},
	methods: {
		login(){
			let that = this;
			API.LOGIN.LOGIN({
				username: this.loginForm.username,
				password: md5.hex(this.loginForm.password),
				captcha: this.loginForm.code,
				uuid: this.loginForm.uuid
			}).then(res => {
				if(0 === res.code){
					// 存储token,权限列表,基础信息
					localStorage.setItem('token', res.token);
					that.$store.dispatch('UserInfo', res.data.userInfo);
					that.$store.dispatch('Competence', res.data.competence);
					that.$store.dispatch('GenerateRoutes', []);
					that.$router.push('/');
				}else{
					this.verification();
				}
			});
		},
		verification(){
			this.imgSrc = API.LOGIN.VERIFICATION(this.loginForm.uuid);
			this.loginForm.code = '';
		}
	}
}
</script>

<style lang="scss">
#login {
	width: 100%;
	height: 100%;
	background: url('/static/images/loginBg.jpg') no-repeat;
	background-size: cover;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	align-content: center;
	& > p {
		font-size: 28px;
		color: #fff;
		font-weight: bold;
		margin-top: 150px;
		margin-bottom: 20px;
	}
	form {
		background: rgba(255, 255, 255, 0.6);
		border-radius: 15px;
		padding: 30px 20px;
		.el-input {
			width: 100%;
		}
	}
}

.verificationDiv {
	width: 300px;
	display: flex;
	justify-content: space-between;
	.el-input {
		width: 180px !important;
	}
	img {
		width: 100px;
		height: 40px;
	}
}
</style>
