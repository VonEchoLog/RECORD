<template>
  <div id ="background">
    <div id ="loginDiv" >
      <label class="label-wrap">系统登录</label>
      <br>
      <el-input class="el-input" v-model="loginForm.username" placeholder="请输入账号"></el-input>
      <br>
      <el-input placeholder="请输入密码"  v-model="loginForm.password" show-password></el-input>
      <el-row>
        <el-button class="el-button el-button--primary" type="info" plain v-on:click="login"> 登录 </el-button>
      </el-row>

    </div>
  </div>
</template>

<script>
export default {
  name: 'login',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {
    login () {
      this.$axios
        .post('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$router.replace({path: '/index'})
          }
        })
        .catch(failResponse => {
        })
    }
  }

}
</script>

<style scoped>
#background {
  height: 100%;
  width: 100%;
  position: fixed;
  top:0px;
  left:0px;
  right:0px;
  bottom:0px;
  /*background: black;*/
  background: url("../../static/image/preview.jpg") no-repeat;
  background-size: cover;
  background-position: center;
}
#loginDiv {
  background: whitesmoke;
  position: absolute;
  margin:auto;
  left:0;
  right:0;
  top:0;
  bottom:0;
  height:650px;
  width: 650px;
  text-align: center;
  border-radius:30px;
  box-shadow: rgba(0, 0, 0, 0.2) 0px 0px 20px, rgba(0, 0, 0, 0.2) 0px 0px 20px;
  /*filter:alpha(Opacity=60);-moz-opacity:0.6;opacity: 0.6; //半透明效果*/

}
.el-input {
  /*float: left; 居左显示*/
  text-align: center;
  width: 400px;
  margin: 20px;
}
.label-wrap {
  Line-height:200px;
  height: 250px;
  width: 600px;
  font-size: 30px;
  text-align: center;
}

</style>
