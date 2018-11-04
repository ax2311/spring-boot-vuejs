<template>
<b-navbar toggleable="md" type="dark" variant="info">

  <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>

  <b-navbar-brand href="#">Tender</b-navbar-brand>

  <b-collapse is-nav id="nav_collapse">

    <b-navbar-nav>        
        <b-nav-item href="/">Home</b-nav-item>
        <b-nav-item v-if="containRole('PROPERTY_MANAGEMENT')" href="/#/maintainVendorList">Maintain My List</b-nav-item>             
        <b-nav-item v-if="containRole('PROPERTY_MANAGEMENT')" href="/#/publishTender">Publish Tender</b-nav-item>      
        <b-nav-item v-if="containRole('PROPERTY_MANAGEMENT')" href="/#/currentTender">Current Tender</b-nav-item>    
        <b-nav-item v-if="containRole('ENGINEER_VENDOR') || containRole('SUPPLIER')" href="/#/bidTender">Bid Tender</b-nav-item>       
       <!--  <b-nav-item v-if="containRole('ENGINEER_VENDOR') || containRole('SUPPLIER')" href="/#/bidHistory">Bid History</b-nav-item>   -->
    </b-navbar-nav>

    <!-- Right aligned nav items -->
    <b-navbar-nav class="ml-auto">    
      <b-nav-item  v-if="!isLoggedIn()" href="/#/login">Login</b-nav-item>
      <b-nav-item  v-if="!isLoggedIn()" href="/#/register">Register</b-nav-item> 

      <b-nav-item-dropdown right v-if="isLoggedIn()">
        <!-- Using button-content slot -->
        <template slot="button-content">
          <em>
            {{logged_in_msg}} 
          </em>
        </template>
        <b-dropdown-item href="#/profile">Update Profile</b-dropdown-item>
        <b-dropdown-item  v-on:click="logOut">Logout</b-dropdown-item>
      </b-nav-item-dropdown>
    </b-navbar-nav>

  </b-collapse>
</b-navbar>

</template>
<script>
import Vue from 'vue'
import {AXIOS} from './http-common'

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

export {getCookie}

function set_cookie(name, value) {
    document.cookie = name +'='+ value +'; Path=/;';
}

export {set_cookie}

function delete_cookie(name) {
    document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

window.Event = new Vue({
    data: {isLoggedIn: false}
});

export default {
    name: 'navi',
    data () {
      return {
        logged_in_msg : "",
        userroles : []
      }
    },
    mounted(){
        if(getCookie("access_token")){       
 
            AXIOS.get("/getUserRole?access_token=" + getCookie("access_token"))
                .then(function(response){
                    this.userroles = response.data ;
                }.bind(this))
                .catch(function(error){
                   console.log(error);
                }); 

            AXIOS.get("/getUsername?access_token=" + getCookie("access_token"))
                .then(function(response){
                    this.logged_in_msg = "Welcome back , " + response.data;
                    window.Event.isLoggedIn = true;
                    Event.$emit('logged-in');
                }.bind(this))
                .catch(function(error){
                    delete_cookie("access_token");
                    return error;
                });
        }
    },
    methods : {
        logOut(){
            AXIOS.get("/logouts?access_token="+getCookie("access_token"))
                .then(function(response){
                    window.Event.isLoggedIn = false;
                    this.logged_in_msg  = "Successfully logged out";
                    delete_cookie("access_token")
                }.bind(this))
        },
        isLoggedIn(){
            return window.Event.isLoggedIn;
        },
        containRole(role){
            for (var i in this.userroles) {                                               
                if (this.userroles[i] == role)
                return true;
            }            
        }
    }
}
</script>
<style>

</style>