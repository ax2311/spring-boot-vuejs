<template>
    <div class="register">
    <div class="container" >
        <div class="row justify-content-center">
            <div class="container">
                <div class="row justify-content-center">
                    <h4 id="loginHeader">Register</h4>
                </div>
                <div class="row justify-content-center">
                    <div class="col-3">
                        <b-form-select v-model="selectedAccType" v-on:change="refreshForm">
                                <option disabled value="">select account type</option>
                                <option>Property Management</option>
                                <option>Engineer Vendor</option>
                                <option>Supplier</option>
                        </b-form-select>                        
                    </div>
                </div>
            </div>          
				<!-- property management input fields -->				
                <div class="container"  v-if="selectedAccType === 'Property Management'">
                    <div class="row justify-center with-margin remark">	
                        <div class="col-2 justify-left" >
                            * Refers to mandatory
                        </div>                    
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2" >
                            <label for="username">* Username <br> (5-20 english letters or numbers)</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="username"  type="text"  id="usernamePm"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="password">* Password <br> (5-20 english letters or numbers)</label>
                        </div>
                        <div class="col-3">
                             <b-form-input v-model="password" class="u-full-width" type="password" id="passwordPm"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="passwordConfirm">* Confirm password <br> (5-20 english letters or numbers)</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="passwordConfirm" class="u-full-width" type="password" id="passwordConfirmPm"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="companyName">* Company Name</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="companyName" class="u-full-width" type="text" id="companyNamePm"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="address">Address</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="address" class="u-full-width" type="text" id="addressPm"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="mailAddress">Mail Address</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="mailAddress" class="u-full-width" type="text" id="mailAddressPm"/>
                         </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="phoneNum">Phone number</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="phoneNum" class="u-full-width" type="text" id="phoneNumPm"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="email">* Email</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="email" class="u-full-width" type="text" id="emailPm"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="district">District</label>
                        </div>
                        <div class="col-3">
                            <b-form-select v-model="district" id=districtPm>
                                <option disabled value="">select one district</option>
                                <option v-for="district in districts" v-bind:key="district.id" v-bind:value="district.id">
                                    {{ district.value }}
                                </option>
                            </b-form-select>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
					        <label for="description">Description</label>
                         </div>
                        <div class="col-3">
					        <b-form-textarea v-model="description" placeholder="self decription" rows="9" cols="30" id="descriptionPm"></b-form-textarea>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
					        <label for="profilePhoto">Profile Photo (accept .jpg only)</label>
                        </div>
                        <div class="col-3">                        
                            <b-form-file ref="file" accept=".jpg" @change="handleFileUpload($event.target.files)"
                             id="profilePhotoPm" class="mt-3" plain></b-form-file>
                         </div>
                    </div>
                    
                    <div>
                        <b-btn @click="register">Submit</b-btn>
                    </div>
                </div>

                <!-- engineer vendor input fields -->
                <div class="container" v-if="selectedAccType === 'Engineer Vendor'">
                    <div class="row justify-center with-margin remark">	
                        <div class="col-2 justify-left" >
                            * Refers to mandatory
                        </div>                    
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="username">* Username <br> (5-20 english letters or numbers)</label>
                        </div>
                        <div class="col-3">   
                            <b-form-input v-model="username" class="u-full-width" type="text" placeholder="yourUsername" id="usernameEv"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="password">* Password <br> (5-20 english letters or numbers)</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="password" class="u-full-width" type="password" id="passwordEv"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="passwordConfirm">* Confirm password <br> (5-20 english letters or numbers)</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="passwordConfirm" class="u-full-width" type="password" id="passwordConfirmEv"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="companyName">* Company Name</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="companyName" class="u-full-width" type="text" id="companyNameEv"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="category">* Category</label>
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
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="address">Address</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="address" class="u-full-width" type="text" id="addressEv"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="mailAddress">Mail Address</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="mailAddress" class="u-full-width" type="text" id="mailAddressEv"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                             <label for="phoneNum">Phone number</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="phoneNum" class="u-full-width" type="text" id="phoneNumEv"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="email">* Email</label>
                        </div>
                        <div class="col-3">
                        <b-form-input v-model="email" class="u-full-width" type="text" id="emailEv"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="district">District</label>
                        </div>
                        <div class="col-3">
                            <b-form-select v-model="district" id="districtEv">
                                <option disabled value="">select one district</option>
                                <option v-for="district in districts" v-bind:key="district.id" v-bind:value="district.id">
                                    {{ district.value }}
                                </option>
                            </b-form-select>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
					        <label for="numOfEmployer">Number of employers</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="numOfEmployer" class="u-full-width" type="text" id="numOfEmployerEv"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
					        <label for="description">Description</label>
                        </div>
                        <div class="col-3">
					        <b-form-textarea v-model="description" placeholder="self decription"  rows="9" cols="30" id = "descriptionEv"></b-form-textarea>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
					        <label for="profilePhoto">Profile Photo (accept .jpg only)</label>
                        </div>
                        <div class="col-3">
                            <b-form-file ref="file" accept=".jpg" @change="handleFileUpload($event.target.files)"
                             id="profilePhotoEv" class="mt-3" plain></b-form-file>
                        </div>
                    </div>
                    
                    <div>
                        <b-btn @click="register">Submit</b-btn>       
                    </div>
                </div>
                
                <!-- supplier b-form-input fields -->
                <div class="container" v-if="selectedAccType === 'Supplier'">
                    <div class="row justify-center with-margin remark">	
                        <div class="col-2 justify-left" >
                            * Refers to mandatory
                        </div>                    
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="username">* Username <br> (5-20 english letters or numbers)</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="username" class="u-full-width" type="text" placeholder="yourUsername" id="usernameSp"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="password">* Password <br> (5-20 english letters or numbers)</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="password" class="u-full-width" type="password" id="passwordSp"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="passwordConfirm">* Confirm password <br> (5-20 english letters or numbers)</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="passwordConfirm" class="u-full-width" type="password" id="passwordConfirmSp"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="companyName">* Company Name</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="companyName" class="u-full-width" type="text" id="companyNameSp"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="category">* Category</label>
                        </div>
                        <div class="col-3">
                            <b-form-select v-model="categories[0]" id="categorySp">
                                <option disabled value="">select one category</option>
                                <option v-for="supplierCategory in supplierCategories" v-bind:key="supplierCategory.categoryId" v-bind:value="supplierCategory.categoryId" >
                                    {{ supplierCategory.category }}
                                </option>
                            </b-form-select>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="address">Address</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="address" class="u-full-width" type="text" id="addressSp"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="mailAddress">Mail Address</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="mailAddress" class="u-full-width" type="text" id="mailAddressSp"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="phoneNum">Phone number</label>
                        </div>
                        <div class="col-3">
                             <b-form-input v-model="phoneNum" class="u-full-width" type="text" id="phoneNumSp"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="email">* Email</label>
                        </div>
                        <div class="col-3">
                             <b-form-input v-model="email" class="u-full-width" type="text" id="emailSp"/>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
                            <label for="district">District</label>
                        </div>
                        <div class="col-3">
                            <b-form-select v-model="district" id="districtSp">
                                <option disabled value="">select one district</option>
                                <option v-for="district in districts" v-bind:key="district.id" v-bind:value="district.id">
                                    {{ district.value }}
                                </option>
                            </b-form-select>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
					        <label for="numOfEmployer">Number of employers</label>
                        </div>
                        <div class="col-3">
                            <b-form-input v-model="numOfEmployer" class="u-full-width" type="text" id="numOfEmployerSp"/>
                         </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
					        <label for="description">Description</label>
                        </div>
                        <div class="col-3"> 
					        <b-form-textarea v-model="description" placeholder="self decription"  rows="9" cols="30" id="descriptionId"></b-form-textarea>
                        </div>
                    </div>
                    <div class="row justify-center with-margin">
                        <div class="col-2">
					        <label for="profilePhoto">Profile Photo (accept .jpg only)</label>
                        </div>
                        <div class="col-3"> 
                            <b-form-file ref="file" accept=".jpg" @change="handleFileUpload($event.target.files)"
                             id="profilePhotoSp" class="mt-3" plain></b-form-file>
                        </div>
                    </div>
                    <div>
                    <!-- Using modifiers -->
                         <b-btn @click="register">Submit</b-btn>                    
                    </div>                
                </div>    
                <font color="red" >
                    {{invalidMsgs}}
                </font>
            </div>
         </div>
                <!-- <p v-if="invalidMsgs" v-for="invalidMsg in invalidMsgs" v-bind:key="invalidMsg.id">	  
                    <font color="red" >                       
	                   {{invalidMsg.msg}}
                    </font>
                </p> -->
               
                <!-- the modal -->
                 <b-modal id="popup" ref="popupRef" ok-only hide-header-closee @ok="confirmForward">
                        User register successful
                 </b-modal>
        </div>
</template>
<script>
import {AXIOS} from './http-common'

let templateData = {
            username: '',
            password: '',
            passwordConfirm: '',
            companyName: '',
            categories: [ ],
            address: '',
            mailAddress: '',
            phoneNum: '',
            email: '',
            district: '',
            numOfEmployer: '',
            description: '',
            showModal: false,
            profilePhoto: ''
        };

let updFile;

export default {
    name: 'register',
    data () {
      return { 
          selectedAccType: '',
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
            vendorCategories: [
            	
            ],
            supplierCategories: [],
            invalidMsgs: null,
      		 ...templateData
       }
    },
     methods: {
         	getAllcategories(){
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
            refreshForm(){
                Object.assign(this, templateData);
                updFile = null;
            },
            confirmForward(){
            	document.location.replace("/");	
            },
            register(){
                //let formData1 = document.getElementById("form_pm");
                let formData1 = new FormData();
                formData1.append('username', this.username);
                formData1.append('password', this.password);
                formData1.append('passwordConfirmation', this.passwordConfirm);
                formData1.append('accType', this.selectedAccType);
                formData1.append('companyName', this.companyName);
                formData1.append('categories',this.categories );
                formData1.append('address', this.address);
                formData1.append('mailAddress', this.mailAddress);
                formData1.append('phoneNum', this.phoneNum);
                formData1.append('email',this.email );
                formData1.append('district', this.district);
                formData1.append('numOfEmployer', this.numOfEmployer);
                formData1.append('description', this.description);
                if (updFile)
                	formData1.append('profilePhoto',updFile);

                console.log(formData1);

                AXIOS({
                    method:'post',
                    url:'register',
                    data: formData1,
                    config: { headers: {'Content-Type': 'multipart/form-data' }}
                    
                }).then(function(response){
                    /* var msg = [];
                    for (var i in response.data) {                        
                        msg.push({id:i, msg:response.data[i]});
                    }
                     this.invalidMsgs = msg; */
                     this.invalidMsgs = response.data[0];
                	if (response.data[0] == "user created") {   
                        this.$refs.popupRef.show();                        
                	}                    
                }.bind(this));
            },
            handleFileUpload(files){
                //formData1.append('profilePhoto',this.$refs.file);
                updFile = files[0];
            }
     },
     beforeMount(){
        this.getAllcategories();
    }
}
</script>
<style>
.with-margin {
  margin-bottom: 5px;
}

.remark {
  font-size: 13px;
}

</style>
