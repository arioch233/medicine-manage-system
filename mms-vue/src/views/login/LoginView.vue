<template>
  <div class="login-container">
    <div class="login-card">
      <!--   logo   -->
      <div style="text-align: center;">
        <img :src=logoURL style="width: 200px;margin-bottom: 20px;">
        <!--        <span style="font-size: 20px;vertical-align: middle;">某某医药</span>-->
      </div>
      <!--   主体部分   -->
      <div>
        <el-tabs v-model="activeName" stretch>
          <el-tab-pane name="login">
            <span slot="label" style="font-size: 18px">账号登录</span>
            <!-- 登录表单 -->
            <el-form
                status-icon
                :model="loginForm"
                :rules="ruleLogin"
                ref="ruleLoginForm"
                class="login-form"
            >
              <!-- 用户名输入框 -->
              <el-form-item prop="username">
                <el-input
                    v-model="loginForm.username"
                    prefix-icon="el-icon-user"
                    placeholder="用户名"
                    @keyup.enter.native="login"
                    clearable
                />
              </el-form-item>
              <!-- 密码输入框 -->
              <el-form-item prop="password">
                <el-input
                    v-model="loginForm.password"
                    prefix-icon="el-icon-lock"
                    show-password
                    placeholder="密码"
                    clearable
                    @keyup.enter.native="login"
                />
              </el-form-item>
            </el-form>
            <!-- 登录按钮 -->
            <el-button type="primary" @click="login">登录</el-button>
          </el-tab-pane>
          <el-tab-pane name="register">
            <span slot="label" style="font-size: 18px">账号注册</span>
            <!-- 注册表单 -->
            <el-form
                status-icon
                :model="registerForm"
                :rules="ruleRegister"
                ref="ruleRegisterForm"
                class="login-form"
            >
              <!-- 邮箱输入框 -->
              <el-form-item prop="username">
                <el-input
                    v-model="registerForm.email"
                    prefix-icon="el-icon-s-promotion"
                    placeholder="邮箱"
                    @keyup.enter.native="register"
                    clearable
                />
              </el-form-item>
              <!-- 昵称输入框 -->
              <el-form-item prop="username">
                <el-input
                    v-model="registerForm.nickname"
                    prefix-icon="el-icon-user"
                    placeholder="昵称"
                    @keyup.enter.native="register"
                    clearable
                />
              </el-form-item>
              <!-- 密码输入框 -->
              <el-form-item prop="password">
                <el-input
                    v-model="registerForm.password"
                    prefix-icon="el-icon-lock"
                    show-password
                    placeholder="密码"
                    clearable
                    @keyup.enter.native="register"
                />
              </el-form-item>
              <!-- 确认密码输入框 -->
              <el-form-item prop="password">
                <el-input
                    v-model="registerPassword"
                    prefix-icon="el-icon-lock"
                    show-password
                    placeholder="确认密码"
                    clearable
                    @keyup.enter.native="register"
                />
              </el-form-item>
            </el-form>
            <!-- 注册按钮 -->
            <el-button type="primary" @click="register">注册</el-button>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
  </div>
</template>

<script>
import logoURL from "../../assets/image/logo-small.png"

export default {
  name: "LoginView",
  data() {
    return {
      activeName: "login",
      logoURL: logoURL,
      loginForm: {
        username: "",
        password: ""
      },
      registerPassword: "",
      registerForm: {
        email: "",
        nickname: "",
        password: ""
      },
      ruleLogin: {
        username: [
          {required: true, message: "用户名不能为空", trigger: "blur"}
        ],
        password: [
          {required: true, message: "密码不能为空", trigger: "blur"}
        ]
      },
      ruleRegister: {}
    };
  },
  methods: {
    login() {
      this.$refs.ruleForm.validate(valid => {
        if (valid) {
          const that = this;
          that.$router.push("/home");
        } else {
          return false;
        }
      });
    },
    register() {

    }
  }
}
</script>

<style scoped>
.login-container {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  background: url("../../assets/image/0w3pdr.jpg") center center /
    cover no-repeat;
}

.login-card {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  background: #fff;
  padding: 20px 60px 180px;
  width: 350px;
}

.login-title {
  color: #303133;
  font-weight: bold;
  font-size: 1rem;
}

.login-form {
  margin-top: 1.2rem;
}

.login-card button {
  margin-top: 1rem;
  width: 100%;
}

/*/deep/.el-tabs__item {*/
/*  !* 修改为您想要的文字大小 *!*/
/*  font-size: 20px!important;*/
/*}*/
</style>