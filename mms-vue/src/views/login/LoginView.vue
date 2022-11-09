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
                class="login-form"
            >
              <!-- 邮箱输入框 -->
              <el-form-item style="margin-bottom: 8px">
                <el-input
                    v-model="registerForm.username"
                    prefix-icon="el-icon-s-promotion"
                    placeholder="邮箱"
                    @keyup.enter.native="register"
                    clearable
                />
              </el-form-item>
              <!-- 验证码输入框 -->
              <el-form-item>
                <el-input placeholder="请输入验证码" v-model="registerForm.code"
                          style="width: 210px;display: inline-block">
                </el-input>
                <el-button plain style="display: inline-block;width: 110px; margin-left: 30px;" @click="getCode">
                  获取验证码
                </el-button>
              </el-form-item>
              <!-- 昵称输入框 -->
              <el-form-item>
                <el-input
                    v-model="registerForm.nickname"
                    prefix-icon="el-icon-user"
                    placeholder="昵称"
                    @keyup.enter.native="register"
                    clearable
                />
              </el-form-item>
              <!-- 密码输入框 -->
              <el-form-item>
                <el-input
                    v-model="registerForm.password"
                    prefix-icon="el-icon-lock"
                    show-password
                    placeholder="密码"
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
import {generateMenu} from "@/assets/js/router/menu";

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
      registerForm: {
        code: "",
        username: "",
        nickname: "",
        password: ""
      },
    };
  },
  methods: {
    login() {
      const that = this;
      const reg = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
      if (!reg.test(this.loginForm.username)) {
        this.$message.error("邮箱格式不正确！")
        return false;
      }
      if (this.loginForm.password.trim().length < 6) {
        this.$message.error("密码不少于6位！")
        return false;
      }
      // 发送登录请求
      let param = new URLSearchParams();
      param.append("username", that.loginForm.username);
      param.append("password", that.loginForm.password);
      that.request.post("/login", param).then(data => {
        if (data.code === 200) {
          // 登录后保存用户信息
          that.$store.commit("login", data.data);
          generateMenu();
          // 加载用户菜单
          this.$notify.success({
            title: "登录成功",
            message: data.message
          });
          that.$router.push({path: "/"})
        } else {
          this.$notify.error({
            title: "登录失败",
            message: data.message
          });
        }
      })

    },
    register() {
      const reg = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
      if (!reg.test(this.registerForm.username)) {
        this.$message.error("邮箱格式不正确！")
        return false;
      }
      if (this.registerForm.nickname.trim().length < 2) {
        this.$message.error("昵称不少于2位！")
        return false;
      }
      if (this.registerForm.password.trim().length < 6) {
        this.$message.error("密码不少于6位！")
        return false;
      }
      if (this.registerForm.code.trim().length !== 6) {
        this.$message.error("请输入正确的验证码！")
        return false;
      }
      this.request.post("/user/register", this.registerForm).then(data => {
        if (data.code === 200) {
          this.$notify.success({
            title: "注册成功",
          });
          this.$router.go(0)
        } else {
          this.$notify.error({
            title: "注册失败",
            message: data.message
          });
        }
      })
    },
    getCode() {
      this.request.get("/user/code", {
        params: {
          username: this.registerForm.username
        }
      }).then(data => {
        if (data.code === 200) {
          this.$notify.success({
            title: "成功",
            message: data.message
          });
        } else {
          this.$notify.error({
            title: "失败",
            message: data.message
          });
        }
      });
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