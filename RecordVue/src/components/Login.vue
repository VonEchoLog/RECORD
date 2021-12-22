<template>
  <div id ="background">
    <div id ="loginDiv" >

      <el-input class="el-input" v-model="loginForm.username" placeholder="请输入账号"></el-input>
      <br>
      <el-input placeholder="请输入密码"  v-model="loginForm.password" show-password></el-input>
      <el-row>
        <el-button type="info" plain v-on:click="Login"> 登录 </el-button>
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
  /*background: url("../../static/image/preview.jpg") no-repeat;*/
  background-size: cover;
  background-position: center;
}
#loginDiv {
  position: absolute;
  margin:auto;
  left:0;
  right:0;
  top:0;
  bottom:0;
  height:600px;
  width: 600px;
  /*text-align: center;*/
  border-radius:20px;
  box-shadow: rgba(0, 0, 0, 0.12) 0px 2px 4px, rgba(0, 0, 0, 0.1) 0px 0px 6px;

}
.el-input {
  /*float: left; 居左显示*/
  text-align: center;
  width: 400px;
  margin: 20px;
}

</style>
