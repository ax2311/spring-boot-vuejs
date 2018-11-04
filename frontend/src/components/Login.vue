<template>
   <div class="login">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-3">
            <div class="row justify-content-center">
                    <h4 id="loginHeader">Login</h4>
            </div>
            <div class="row justify-content-center with-margin">
                <div class="col-4">
                    <label for="username">Username</label>
                </div>
                <div class="col-8">
                    <b-form-input v-model="username" class="u-full-width" type="text" placeholder="yourUsername" id="username"/>
                </div>
            </div>
            <div class="row justify-content-center with-margin">
                <div class="col-4">
                    <label for="password">Password</label>
                </div>
                <div class="col-8">
                    <b-form-input v-model="password" class="u-full-width" placeholder="password" type="password" id="password"/>
                </div>
            </div>
            <div class="row justify-content-center">
                    <b-btn @click="login" >Login</b-btn>
            </div>
            <div class="row justify-content-center">
                    <ul v-if="invalidMsg">
	                    <li>
	                        {{invalidMsg}}
	                    </li>
                	</ul>
            </div>
        </div>
        </div>
    </div>    
  </div> 

</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'
  import {set_cookie} from './script.js'

  export default {
    name: 'login',
    data () {
      return {        
        invalidMsg: '',
        username: '',
        password: ''
      }
    },
    methods: {
      login(){
                var params = new URLSearchParams();
                params.append('grant_type', 'password');
                params.append('username', this.username);
                params.append('password',this.password);
                AXIOS({
                    method:'post',
                    url:'/oauth/token',
                    auth:{username:'my-trusted-client',password:'secret'},
                    headers: {"Content-type": "application/x-www-form-urlencoded; charset=utf-8"},
                    data:params                    
                }).then(response => {
                    console.log( response);                    
                    set_cookie("access_token",response.data.access_token);                    
                    document.location.replace("/");
                }).catch(function (error) {
                    console.log(error);
                    if (error.response.data.error == "invalid_grant") {
                		this.invalidMsg = 'User name or password is incorrect';
                    }
                }.bind(this));  
            } 
    }
  }
</script>
<style>
.login{   
    padding-top: 2em;
}

.with-margin {
  margin-bottom: 5px;
}

#InputGroup1, #InputGroup2{
    width: 300px;
    margin: auto;
    text-align: left;
    padding-top: 10px;
    padding-right: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
}

</style>