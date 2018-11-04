<template>
  <div id="app">
    <router-view name="header"></router-view>
    <!-- <router-view :hellomsg="msg"></router-view>-->
    <router-view name="nav"></router-view>
    <router-view name="body"></router-view>
    <router-view name="footer" ></router-view>
  </div>
</template>

<script>

export default {
  name: 'app',
  data () {
    return {
      msg: 'Welcome to your Vue.js powered Spring Boot App'
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 20px;
}
</style>
