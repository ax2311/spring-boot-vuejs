<template>
   <div class="viewProfile">
        <div class="container">
                  <div class="container">
                    <div class="row justify-center with-margin">
            			<div v-if="profilePhoto" class="ui small image">
            				<img :src="profilePhoto">
            			</div>
            		</div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
                            <label for="companyName">Company Name:</label>
                        </div>
                        <div class="col-3">
                            {{companyName}}
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
                            <label for="category">Category:</label>
                        </div>
                        <div class="col-3">
                            {{category}}
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
                            <label for="address">Address:</label>
                        </div>
                        <div class="col-3">
                            {{address}}
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
                            <label for="mailAddress">Mail Address:</label>
                        </div>
                        <div class="col-3">
                           {{mailAddress}}
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
                             <label for="phoneNum">Phone number:</label>
                        </div>
                        <div class="col-3">
                            {{phoneNum}}
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
                            <label for="email">Email:</label>
                        </div>
                        <div class="col-3">
                          {{email}}
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
                            <label for="district">District:</label>
                        </div>
                        <div class="col-3">
                            {{district}}
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
					        <label for="numOfEmployer">Number of employers:</label>
                        </div>
                        <div class="col-3">
                            {{numOfEmployer}}
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                    	<div class="col-2 text-left">
					    	<label for="description">Description:</label>
					    </div>
					    <div class="col-3">
					    </div>
                    </div>
                    <div class="row justify-center with-margin">
                         <div class="col-9">
                            <b-form-textarea disabled v-model="description"  rows="9" ></b-form-textarea>
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
    name: 'viewProfile',
    data () {
      return {        
        username: null,
        companyName: null,
        address: null,
        district: null,
        mailAddress: null,
        phoneNum: null,
        email: null,
        numOfEmployer: null,
        description: null,
        category: null,
        profilePhoto: ''
      }
    },
    mounted() {
        this.username = this.$route.query.username;
        console.log(this.username);
        AXIOS({
        			method:'get',
        			url:'getProfile',
        			params:{username:this.username}
        		}).then(function(response){
                    this.companyName = response.data.companyName
                    this.address = response.data.address
                    this.district = response.data.district
                    this.mailAddress = response.data.mailAddress
                    this.phoneNum = response.data.phoneNum
                    this.email = response.data.email
                    this.numOfEmployer = response.data.numOfEmployer
                    this.description = response.data.description
                    this.category = response.data.category
                    this.profilePhoto = 'data:image/jpeg;base64,' + response.data.profilePhoto
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
              });
    },
    methods: {

    }
  }
</script>
<style>

.with-margin {
  margin-bottom: 5px;
}

.row {

}

.col {

}
</style>