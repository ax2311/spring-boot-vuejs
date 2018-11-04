<template>
    <div class = 'maintainVendorList'>
        <div class="container">
            <div class="row">
                <h4 id="searchHeader">Maintain My List</h4>
            </div>
            <div class="row">
                <b-container fluid >
                    <b-row class="with-margin">
                        <b-col cols="2">
                            <b-form-select v-model="selectedAccType" id="accType"  v-on:input="changeVendorOrSupplier">
                            <option disabled value="">select vendor or supplier</option>
                                    <option value="E">Engineer Vendor</option>
                                    <option value="S">Supplier</option>
                            </b-form-select>
                        </b-col>
                    </b-row>
                    <b-row class="with-margin">
                        <b-col cols="2">
                            <b-form-select v-model="searchCategory" id="searchCategory">
                                <option value="">select one category</option>
                                <option v-for="category in searchCategories" v-bind:value="category.categoryId" v-bind:key="category.categoryId">
                                    {{ category.category }}
                                </option>
                            </b-form-select>
                        </b-col>                          
                    </b-row>  
                    <b-row class="with-margin">
                        <b-col cols="1">
                            Username:
                        </b-col>
                        <b-col cols="2">
                            <b-form-input v-model="usernameInput" class="u-full-width" type="text" placeholder="username" id="usernameInput"/>
                        </b-col>
                    </b-row>
                    <b-row class="with-margin">
                        <b-col cols="1">
                            Company:
                        </b-col>
                        <b-col cols="2">
                            <b-form-input v-model="companyInput" class="u-full-width" type="text" placeholder="company" id="companyInput"/>
                        </b-col>
                    </b-row>
                    <b-row class="with-margin">
                        <b-col cols="1">            
                            <b-button class="u-full-width button-primary" @click="searchVendorOrCategory" >Search</b-button>
                        </b-col>  
                    </b-row>
                </b-container>
               
                <div class="search-table">
                    <b-table show-empty :items="searchListItems" :fields="searchListFields" :current-page="searchListCurrentPage"
                            :per-page="5" >
                            <template slot="add" slot-scope="row">
                                <div v-if="selectedAccType==='E'">
                                    <input type="checkbox" :value="row.item.engineerVendorId" v-model="searchListCheckedIds"> 
                                </div>
                                <div v-if="selectedAccType==='S'">
                                    <input type="checkbox" :value="row.item.supplierId" v-model="searchListCheckedIds">
                                </div>
                            </template>
                            <template slot="detail" slot-scope="row">
                                <a target="_blank" :href="'/#/viewProfile?username=' + row.item.username">click</a>
                            </template>
							<template slot="empty" >
                				Sorry, there is nothing to display here.
            				</template>
                    </b-table>
                 </div> 
                <b-container fluid>
                    <b-row class="with-margin">
                        <b-pagination size="md" :total-rows="searchListTotRow" :per-page="5" v-model="searchListCurrentPage" class="my-0">
                        </b-pagination>
                    </b-row>
                    <b-row class="with-margin">
                        <b-button @click="addVendor" >Add</b-button>
                    </b-row>
                </b-container>

                <b-modal id="addPopUp" ref="addPopUpRef" ok-only hide-header-closee>
                        <b-container>
                            <b-row>
                                <b-col>
                                    Companies added successful
                                </b-col>
                                <b-col>
                                    Companies not added
                                </b-col>
                            </b-row>
                            <b-row>
                                <b-col>
                                    <li v-for="item in searchListSuccess" :key="item">
                                        {{ item }}
                                    </li>
                                </b-col>
                                <b-col>
                                    <li v-for="item in searchListFail" :key="item">
                                        {{ item }}
                                    </li>
                                </b-col>
                            </b-row>
                        </b-container>
                 </b-modal>
            </div>
        <div class="row">
            <b-container fluid>
                <b-row class="with-margin">
                    <h4 >My List</h4>
                </b-row>
                <b-row class="with-margin">
                    <b-col cols="2">
                        <b-form-select v-model="myListCategory" id="myListCategory" v-on:input="searchMyListByCat">
                            <option disabled :value="null">select one category</option>
                            <option v-for="category in searchCategories" v-bind:value="category.categoryId" v-bind:key="category.categoryId">
                                {{ category.category }}
                            </option>
                        </b-form-select>
                    </b-col>
                </b-row>
                <b-row class="with-margin">
                    <div class="search-table">
                        <b-table show-empty :items="myListItems" :fields="myListFields" :current-page="myListCurrentPage"
                                    :per-page="5" >
                            <template slot="delete" slot-scope="row">
                                <div v-if="selectedAccType==='E'">                                
                                    <input type="checkbox" :value="row.item.engineerVendorId" v-model="myListCheckedIds">
                                </div>
                                <div v-if="selectedAccType==='S'">
                                    <input type="checkbox" :value="row.item.supplierId" v-model="myListCheckedIds">
                                </div> 
                            </template>
                            <template slot="detail" slot-scope="row">
                                <a target="_blank" :href="'/#/viewProfile?username=' + row.item.username">click</a>
                            </template>
						    <template slot="empty" >
                				Sorry, there is nothing to display here.
            			    </template>
                        </b-table> 
                    </div>
                </b-row>
                <b-row class="with-margin">
                    <b-pagination size="md" :total-rows="myListTotRow" :per-page="5" v-model="myListCurrentPage" class="my-0">
                    </b-pagination>
                </b-row>
                <b-row class="with-margin">
                    <b-button @click="deleteVendor">Delete</b-button>
                </b-row>
            </b-container >
                <b-modal id="deletePopUp" ref="deletePopUpRef" ok-only hide-header-closee>
                        <b-container>
                            <b-row>
                                <b-col>
                                    Companies deleted successful
                                </b-col>
                                <b-col>
                                    Companies not deleted
                                </b-col>
                            </b-row>
                            <b-row>
                                <b-col>
                                    <li v-for="{item, id} in myListSuccess" :key="id">
                                        {{ item }}
                                    </li>
                                </b-col>
                                <b-col>
                                    <li v-for="{item, id} in myListFail" :key="id">
                                        {{ item }}
                                    </li>
                                </b-col>
                            </b-row>
                        </b-container>
                 </b-modal>
        </div>
    </div>
    </div>
</template>
<script>
import {AXIOS} from './http-common'
//import {getCookie} from './script.js'

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

const tempSearchListItems = [
//    { category:'cat', username:'user', company:'comp', description:'desc', district:'', email:'', phone:'', detail:'', add:''}
]

const tempMyListItems = [
 //   { category:'cat', username:'user', company:'comp', description:'desc', district:'', email:'', phone:'', detail:'', add:''}
]

export default{
    name : 'maintainVendorList',
    data () {
        return {
            selectedAccType: '',
            searchListItems: tempSearchListItems,
            searchCategories: [ ],
            searchCategory: '',
            allCategories: '',
            usernameInput: '',
            companyInput: '',
            search: null,
            searchListFields: { category: { label: 'Category'}, 
                                username: { label: 'Username'},
                                company: { label: 'Company'}, 
                                description: { label: 'Description'},
                                district: { label: 'District'},
                                email: { label: 'Email'},
                                phone: {label: 'Phone'},
                                detail: {label: 'Detail'},
                                add: {label: 'Add' }}, 
            searchListCurrentPage: 1,
            searchListTotRow: 0,
            searchListCheckedIds: [ ],
            searchListSuccess: [ ],
            searchListFail: [ ],
            myListItems: tempMyListItems,
            myListFields: { category: { label: 'Category'}, 
                                username: { label: 'Username'},
                                company: { label: 'Company'}, 
                                description: { label: 'Description'},
                                district: { label: 'District'},
                                email: { label: 'Email'},
                                phone: {label: 'Phone'},
                                detail: {label: 'Detail'},
                                delete: {label: 'Delete' }},
            myListCheckedIds: [ ],
            myListTotRow: 0,
            myListCurrentPage: 1,
            myListCategory: null,    
            myListSuccess: [ ],
            myListFail: [ ],
            searchVendorOrCategoryUrl: null,
            addVendorUrl: null,
            getMyListUrl: null,
            deleteVendorUrl: null
        }
    },
    methods: {
        searchVendorOrCategory () {
            if (!this.selectedAccType) {
                return;
            }
            AXIOS({
        			method:'get',
        			url:this.searchVendorOrCategoryUrl,
        			params:{catId:this.searchCategory,username:this.usernameInput,companyName:this.companyInput}
        		}).then(function(response){
                    this.searchListItems = response.data;
                    this.searchListTotRow = this.searchListItems.length;   
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
                });
        },
        addVendor () {
            AXIOS({
        			method:'post',
                    url:this.addVendorUrl + this.searchListCheckedIds +'/',
                    params:{access_token:getCookie("access_token")}
        		}).then(function(response){
                    this.searchListSuccess = null;
                    this.searchListFail = null;
                    this.$refs.addPopUpRef.show();
                    this.searchListSuccess = response.data[0];
                    this.searchListFail = response.data[1];
                    this.searchListCheckedIds = [];
                    this.searchMyListByCat()
                    console.log(response.data);
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
                });
        },
        searchMyListByCat () {
            AXIOS({
        			method:'get',
        			url:this.getMyListUrl,
        			params:{catId:this.myListCategory, access_token:getCookie("access_token")}
        		}).then(function(response){
                    this.myListItems = response.data;
                    this.myListTotRow = this.myListItems.length;   
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
                });
        },
        deleteVendor () {
            AXIOS({
        			method:'delete',
                    url:this.deleteVendorUrl + this.myListCheckedIds +'/',
                    params:{access_token:getCookie("access_token")}
        		}).then(function(response){
                    this.myListSuccess =null;
                    this.myListFail = null;
                    this.$refs.deletePopUpRef.show();
                    this.myListSuccess = response.data[0];
                    this.myListFail = response.data[1];
                    this.myListCheckedIds = [];
                    this.searchMyListByCat()
                    console.log(response.data);
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
                });
        },
        changeVendorOrSupplier () {
            if (this.selectedAccType == 'E'){
                    this.searchCategories = this.allCategories.filter(function(u) {
        		         return u.companyType == "E";
                     }); 

                     this.searchVendorOrCategoryUrl = 'getVendorsByCategory/';
                     this.addVendorUrl = 'postVendorList/';
                     this.getMyListUrl = 'getMyListVendorsByCategory/';
                     this.deleteVendorUrl = 'deleteVendorList/';
            } else if (this.selectedAccType == 'S'){
                    this.searchCategories = this.allCategories.filter(function(u) {
        		         return u.companyType == "S";
                     });
                     
                     this.searchVendorOrCategoryUrl = 'getSuppliersByCategory/';
                     this.addVendorUrl = 'postSupplierList/';
                     this.getMyListUrl = 'getMyListSuppliersByCategory/';
                     this.deleteVendorUrl = 'deleteSupplierList/';
            }
            this.searchListItems = null;
            this.myListItems = null;
            this.myListCheckedIds = [];
            this.searchListCheckedIds = [];
            this.searchCategory = null;
            this.myListCategory = null;
        }
    },
    beforeMount(){
        		AXIOS({
        			method:'get',
        			url:'categories',
        			data:{}
        		}).then(function(response){
                    this.allCategories = response.data;
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
                });
    }
}
</script>
<style>
.with-margin {
  margin-bottom: 5px;
}

.search-table {
    height: 300px;
    width: 1000px;
}
</style>