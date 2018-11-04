<template>
   <div class="profile">
    <div class="container">
        <div class="row justify-content-center">
            <div class="container" >
            
            		<div class="row justify-center with-margin">
            			<div v-if="profilePhoto" class="ui small image">
            				<img :src="profilePhoto">
            			</div>
            		</div>
                    <div class="row justify-center with-margin remark">	
                        <div class="col-2 justify-left" >
                            * Refers to mandatory
                        </div>                    
                    </div>
    
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
                            <label for="companyName">* Company Name:</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="companyName" class="u-full-width" type="text" id="companyNamePm"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin" v-if="containRole('ENGINEER_VENDOR') ">
                        <div class="col-2 text-left">
                            <label for="category">* Category:</label>
                        </div>
                        <div class="col-3">
                            <b-form-select v-model="categories[0]" id="categoryEv">
                                <option disabled value="">select one category</option>
                                <option v-for="vendorCategory in vendorCategories" v-bind:key="vendorCategory.categoryId" v-bind:value="vendorCategory.categoryId">
                                    {{ vendorCategory.category }}
                                </option>
                            </b-form-select>
                        </div>
                    </div>
                    <div class="row justify-center with-margin" v-if="containRole('SUPPLIER') ">
                        <div class="col-2 text-left">
                            <label for="category">* Category:</label>
                        </div>
                        <div class="col-3">
                            <b-form-select v-model="categories[0]" id="categorySp">
                                <option disabled value="">select one category</option>
                                <option v-for="supplierCategory in supplierCategories" v-bind:key="supplierCategory.categoryId" v-bind:value="supplierCategory.categoryId">
                                    {{ supplierCategory.category }}
                                </option>
                            </b-form-select>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
                            <label for="address">Address:</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="address" class="u-full-width" type="text" id="addressPm"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
                            <label for="mailAddress">Mail Address:</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="mailAddress" class="u-full-width" type="text" id="mailAddressPm"/>
                         </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
                            <label for="phoneNum">Phone number:</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="phoneNum" class="u-full-width" type="text" id="phoneNumPm"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
                            <label for="email">* Email:</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="email" class="u-full-width" type="text" id="emailPm"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
                            <label for="district">District:</label>
                        </div>
                        <div class="col-3">
                            <b-form-select v-model="district" id="district">
                                <option disabled value="">select one district</option>
                                <option v-for="district in districts" v-bind:key="district.id" v-bind:value="district.id">
                                    {{ district.value }}
                                </option>
                            </b-form-select>
                        </div>
                    </div>
                    <div class="row justify-center with-margin"  v-if="containRole('SUPPLIER') || containRole('ENGINEER_VENDOR')">
                        <div class="col-2 text-left">
					        <label for="numOfEmployee">Number of employee:</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="numOfEmployee" class="u-full-width" type="text" id="numOfEmployee"/>
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
                        <div class="col-7">
					        <b-form-textarea v-model="description" placeholder="self decription" rows="9" cols="30" id="descriptionPm"></b-form-textarea>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2 text-left">
					        <label for="profilePhoto">Profile Photo:</label>
                        </div>
                        <div class="col-3">                        
                            <b-form-file ref="file" accept=".jpg" @change="handleFileUpload($event.target.files)"
                             id="profilePhoto" class="mt-3" plain></b-form-file>
                         </div>
                    </div>
                    <div>
                        <b-btn @click="register">Submit</b-btn>       
                    </div>
                    <font color="red" >
                        {{invalidMsgs}}
                    </font>
            </div>
        </div>
    </div> 
        <b-modal id="popup" ref="popupRef" ok-only hide-header-closee @ok="confirmForward">
             Profile update successful
        </b-modal>   
  </div> 

</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'
  import {set_cookie} from './script.js'

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

  let templateData = {
          companyName: '',
          categories: [ ],
          address: '',
          mailAddress: '',
          phoneNum: '',
          email: '',
          district: '',
          numOfEmployee: '',
          description: ''
      };
  
let updFile;
  
export default {
    name: 'profile',
    data () {
      return {        
        userroles : [],
        districts: [
            { id: 1, value: 'Central and Western' },
            { id: 2, value: 'Eastern' },
            { id: 3, value: 'Southern' },
            { id: 4, value: 'Wan Chai' },
            { id: 5, value: 'Kowloon City' },
            { id: 6, value: 'Kwun Tong' },
            { id: 7, value: 'Sham Shui Po' },
            { id: 8, value: 'Wong Tai Sin' },
            { id: 9, value: 'Yau Tsim Mong' },
            { id: 10, value: 'Islands' },
            { id: 11, value: 'Kwai Tsing' },
            { id: 12, value: 'North' },
            { id: 13, value: 'Sai Kung' },
            { id: 14, value: 'Sha Tin' },
            { id: 15, value: 'Tai Po' },
            { id: 16, value: 'Tsuen Wan' },
            { id: 17, value: 'Tuen Mun' },
            { id: 18, value: 'Yuen Long' }
            ],
            supplierCategories:[],
            vendorCategories:[],
            ...templateData,
            invalidMsgs:null,
            profilePhoto:''
      }
    },
    mounted() {
            AXIOS.get("/getUserRole?access_token=" + getCookie("access_token"))
                .then(function(response){
                    this.userroles = response.data ;
                }.bind(this))
                .catch(function(error){
                   console.log(error);
                }); 

        	AXIOS({
        			method:'get',
        			url:'categories',
        			data:{}
        		}).then(function(response){
                    this.supplierCategories = response.data.filter(function(u) {
        		         return u.companyType == "S";
                     });        			
        			
        			 this.vendorCategories = response.data.filter(function(u) {
       		            return u.companyType == "E"; 
       		        })        		     
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
                });

    },
    created: function(){
    	 AXIOS({
				method:'get',
				url:'getMyProfile',
				params:{access_token:getCookie("access_token")}
			}).then(function(response){
	            this.companyName = response.data.companyName
	            this.address = response.data.address
	            this.district = response.data.district
	            this.mailAddress = response.data.mailAddress
	            this.phoneNum = response.data.phoneNum
	            this.email = response.data.email
	            this.numOfEmployee = response.data.numOfEmployer
	            this.description = response.data.description
	            this.categories[0] = response.data.category
	            //this.profilePhoto = response.data.profilePhoto
	            this.profilePhoto = 'data:image/jpeg;base64,' + response.data.profilePhoto;
			}.bind(this)).catch(function (error) {
	            console.log(error);                    
	      });  
    },
    methods:{ 
        containRole(role){
            for (var i in this.userroles) {                                               
                if (this.userroles[i] == role)
                return true;
            }            
        },
        register(){
            let formData1 = new FormData();
            formData1.append('companyName', this.companyName);
            formData1.append('categories',this.categories );
            formData1.append('mailAddress', this.mailAddress);
            formData1.append('address', this.address);            
            formData1.append('phoneNum', this.phoneNum);
            formData1.append('email',this.email );
            formData1.append('district', this.district);
            formData1.append('numOfEmployer', this.numOfEmployee);
            formData1.append('description', this.description);
            if(updFile)
            	formData1.append('profilePhoto',updFile);
        	
                AXIOS({
                    method:'post',
                    url:'updateProfile',
                    data:formData1,
                    params:{access_token:getCookie("access_token")},
                	config: { headers: {'Content-Type': 'multipart/form-data' }}
                }).then(function(response){
                    this.invalidMsgs = response.data;
                	if (response.data == "success") {
                        console.log(response.data);
                		this.$refs.popupRef.show();
                	}                 
                }.bind(this));
        },
        confirmForward(){
            	document.location.replace("/");	
        },
        handleFileUpload(files){
            updFile = files[0];
        }
    }
  }
</script>
<style>

.with-margin {
  margin-bottom: 5px;
}


</style>