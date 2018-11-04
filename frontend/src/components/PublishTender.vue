<template>
	<div class = 'publishTender'>
		
	  <b-container>		  		
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
                <b-form-select v-model="myCategory" id="myCategory" v-on:input="getMyList">
                    <option value="">select one category</option>
                    <option v-for="category in myCategories" v-bind:value="category.categoryId" v-bind:key="category.categoryId">
                                    {{ category.category }}
                     </option>
                </b-form-select>
			 </b-col>
	  	</b-row>
		<b-row class="with-margin">
			<h4>My List</h4>
		</b-row>
		<b-row class="with-margin">
			<div class="tender-table">
				<b-table show-empty :items="myListItems" :fields="myListFields" :current-page="myListCurrentPage"
											:per-page="5">

							<template slot="add" slot-scope="row">
								<input type="checkbox" :value="row.item" v-model="myListCheckedItems"> 
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
			<b-pagination size="md" :total-rows="myListTotRow" :per-page="5" v-model="myListCurrentPage" class="my-0"></b-pagination>
		</b-row>
		<b-row class="with-margin">
			<b-col cols="1">
				<b-button @click="addToList">Add</b-button>
			</b-col>
			<b-col cols="1">
				<b-button @click="addAllToList">Add All</b-button>
			</b-col>
		</b-row>
		<br>
		<b-row class="with-margin">
			<h4>Publish List</h4>
		</b-row>
		<b-row class="with-margin">
			<div class="tender-table">
				<b-table show-empty :items="publishListItems" :fields="publishListFields" :current-page="publishListCurrentPage"
															:per-page="5">
					
							<template slot="delete" slot-scope="row">
								<div v-if="selectedAccType==='E'">
									<input type="checkbox" :value="row.item.engineerVendorId" v-model="publishListCheckedIds">
								</div>
								<div v-if="selectedAccType==='S'">
									<input type="checkbox" :value="row.item.supplierId" v-model="publishListCheckedIds">
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
			<b-pagination size="md" :total-rows="publishListTotRow" :per-page="5" v-model="publishListCurrentPage" class="my-0"></b-pagination>
		</b-row>
		<b-row class="with-margin">
			<b-col cols="1">
				<b-button @click="deleteFromList" >Delete</b-button>
			</b-col>
			<b-col cols="1">
				<b-button @click="deleteAllFromList" >Delete All</b-button>
			</b-col>
		</b-row>
		<br>
		<b-row class="with-margin">
			<b-col sm="1.5"  class="text-left">
				(Must input) Title:
			</b-col>
			<b-col sm="3" >
			<b-form-input v-model="title"
				                  type="text"
			  placeholder="Title" maxlength="100" :state="titleState" aria-describedby=" titleFeedback"></b-form-input>

				<b-form-invalid-feedback id="titleFeedback">      
      					Please enter a title
    			</b-form-invalid-feedback>
			</b-col>
		</b-row>		
		<b-row class="with-margin">
			<b-col class="text-left">
			Description
			</b-col>
		</b-row>
		<b-row class="with-margin">
			<b-col>
				<b-form-textarea id="description"
					v-model="description"
					placeholder="Description"
					:rows="3"
					:max-rows="6"
					maxlength="1000">
				</b-form-textarea>
			</b-col>
		</b-row>	
		<b-row class="with-margin">
			<b-col class="text-left">
				Delivery Date
			</b-col>
		</b-row>
		<b-row class="with-margin">			
			<b-col sm="1" class="text-left">
				From Date:
			</b-col>
			<b-col>
				<b-form-input type="date" v-model="fromDate" :state="fromDateState" aria-describedby=" fromDateLiveFeedback">					
				</b-form-input>
				<b-form-invalid-feedback id="fromDateLiveFeedback">      
      					Please enter both date and time
    			</b-form-invalid-feedback>
			</b-col>
			<b-col sm="1" class="text-left">
				From Time:
			</b-col>
			<b-col>
				<b-form-input type="time" v-model="fromTime" :state="fromDateState" aria-describedby=" fromDateLiveFeedback"></b-form-input>
			</b-col>
		</b-row>
		<b-row class="with-margin">
			<b-col sm="1" class="text-left"> 
				To Date:
			</b-col>
			<b-col>
				<b-form-input type="date" v-model="toDate" :state="toDateState" aria-describedby=" toDateLiveFeedback"></b-form-input>
				<b-form-invalid-feedback id="toDateLiveFeedback">      
      					Please enter both date and time
    			</b-form-invalid-feedback>
			</b-col>
			<b-col sm="1" class="text-left">
				To Time:
			</b-col>
			<b-col>
				<b-form-input type="time" v-model="toTime" :state="toDateState" aria-describedby=" toDateLiveFeedback"></b-form-input>
			</b-col>
		</b-row>
		<b-row class="with-margin">
			<b-col sm="1" class="text-left">
				Address:
			</b-col>
			<b-col sm="6">
				<b-form-input v-model="address"
					type="text"
					placeholder="address" maxlength="200"></b-form-input>
			</b-col>
		</b-row>
		<b-row class="with-margin">
			<b-col class="text-left">
			Price range
			</b-col>
		</b-row>
		<b-row class="with-margin">
			<b-col sm="1" class="text-left">Minimum:</b-col>
			<b-col>
				<b-form-input v-model="minimum"	type="number" :state="miniState"
                  aria-describedby=" miniLiveFeedback" placeholder="Minimum" onkeydown='return (event.which >= 48 && event.which <= 57) || event.which == 8 || event.which == 46' >
				  </b-form-input>

				    <b-form-invalid-feedback id="miniLiveFeedback">      
      					Enter integer of maximum 7 digits only
    				</b-form-invalid-feedback>
			</b-col>
			<b-col sm="1" class="text-left">Maximum:</b-col>
			<b-col>
				<b-form-input v-model="maximum" type="number" :state="maxState"
                  aria-describedby=" maxLiveFeedback" placeholder="Maximum" onkeydown='return (event.which >= 48 && event.which <= 57) || event.which == 8 || event.which == 46'>
				  </b-form-input>

				  	<b-form-invalid-feedback id="maxLiveFeedback">      
      					Enter integer of maximum 7 digits only
    				</b-form-invalid-feedback>
			</b-col>
		</b-row>
		<!-- <b-row class="with-margin">
			<b-col sm="2" class="text-left">
				Document<br> (JPEG, Word, Excel or Pdf only)
			</b-col>
			<b-col>
				<b-form-file v-model="uploadDoc" accept=".jpg, .pdf, .doc .docx .xls .xlsx" class="mt-3" plain></b-form-file>
			</b-col>
		</b-row> -->
		<b-row class="with-margin">
			<b-button @click="publish">Publish</b-button>
		</b-row>	
	  </b-container>
	</div>
</template>
<script>
import {AXIOS} from './http-common'

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

export default {
	name: 'publishTender',
	computed: {
		miniState () {
			return this.minimum.length < 8 ? true : false
		},
		maxState () {
			return this.maximum.length < 8 ? true : false
		},
		fromDateState (){
			if ((!(this.fromDate)&&(this.fromTime)) || (!(this.fromTime)&&(this.fromDate))){
				return false;
			}
			return true;
		},
		toDateState () {
			if ((!(this.toDate)&&(this.toTime)) || (!(this.toTime)&&(this.toDate))){
				return false;
			}
			return true;
		},
		titleState () {
			if (!(this.title)) {
				return false;
			}
		}
    },
	data () {
		return {
			myListItems: null,
			myListFields: { category: { label: 'Category'}, 
                            username: { label: 'Username'},
                            company: { label: 'Company'}, 
                            description: { label: 'Description'},
                            district: { label: 'District'},
                            email: { label: 'Email'},
                            phone: {label: 'Phone'},
                            detail: {label: 'Detail'},
                            add: {label: 'Add' }}, 
			myListTotRow: 1,
			myListCurrentPage: 1,
			myListCheckedItems: [],
			publishListCurrentPage: 1,
			publishListItems: null,
			publishListFields: { category: { label: 'Category'}, 
                                username: { label: 'Username'},
                                company: { label: 'Company'}, 
                                description: { label: 'Description'},
                                district: { label: 'District'},
                                email: { label: 'Email'},
                                phone: {label: 'Phone'},
                                detail: {label: 'Detail'},
                                delete: {label: 'Delete' }},
			publishListTotRow: 1,
			publishListCurrentPage: 1,
			publishListCheckedIds: [],
			selectedAccType: '',
			myCategory: null,
			myCategories: [ ],
			tempMyCategories: [ ],
			title: '',
			description: '',
			address: '',
			minimum: '',
			maximum: '',
			uploadDoc: '',
			getMyListByCategoryUrl: null,
			postTenderUrl: null,
			fromDate: '',
			toDate: '',
			fromTime: '',
			toTime: ''
		}
	},  
	methods: {
		changeVendorOrSupplier () {
			this.getCategory()
			this.publishListItems=[];
			this.publishListCheckedIds=[];
			this.myListCheckedItems=[];
			this.myListItems=[];
			if (this.selectedAccType === 'S'){
			  this.getMyListByCategoryUrl = 'getMyListSuppliersByCategory/';
			  this.postTenderUrl = 'postSupplierTender/';
			  this.listId = 'supplierId';
			} else if (this.selectedAccType === 'E'){
			  this.getMyListByCategoryUrl = 'getMyListVendorsByCategory/';
			  this.postTenderUrl = 'postVendorTender/';
			  this.listId = 'engineerVendorId';
			}
		},
		getCategory () {
				if (this.selectedAccType == 'E') {
					this.myCategories = this.tempMyCategories.filter(function(u) {
						return u.companyType == 'E';
					});        	
				} else if (this.selectedAccType == 'S') {
					this.myCategories = this.tempMyCategories.filter(function(u) {
						return u.companyType == 'S';
					});      
				}
		},
		getMyList () {
			this.myListCheckedItems=[];			
            AXIOS({
        			method:'get',
        			url:this.getMyListByCategoryUrl,
        			params:{catId:this.myCategory, access_token:getCookie("access_token")}
        		}).then(function(response){
                    this.myListItems = response.data;
                    this.myListTotRow = this.myListItems.length;   
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
                });
		},
		addToList () {
				this.publishListItems = _.uniqBy(this.publishListItems.concat(this.myListCheckedItems), this.listId);
		},
		addAllToList () {
				this.publishListItems = _.uniqBy(this.publishListItems.concat(this.myListItems), this.listId);
		},
		deleteFromList () {			
			if (this.selectedAccType=='E'){
			  this.publishListItems = _.filter(this.publishListItems, (obj)=>{ return !_.includes(this.publishListCheckedIds, obj.engineerVendorId)});
			} else if (this.selectedAccType=='S'){
			  this.publishListItems = _.filter(this.publishListItems, (obj)=>{ return !_.includes(this.publishListCheckedIds, obj.supplierId)});
			}
			this.publishListCheckedIds = [];
		},
		deleteAllFromList () {
			this.publishListItems = [];
			this.publishListCheckedIds = [];
		},
		publish () {
			if (!(this.miniState && this.maxState && this.fromDateState && this.toDateState && this.publishListItems))
				return ;

			var postIds = _.map(this.publishListItems, this.listId)

            AXIOS({
        			method:'post',
                    url:this.postTenderUrl  + postIds + '/',
                    params:{
							access_token:getCookie("access_token"),
							companyType:this.selectedAccType,
							title:this.title,
							description:this.description,
							fromDate:this.fromDate,
							toDate:this.toDate,
							address:this.address,
							minimumPrice:this.minimum,
							maximumPrice:this.maximum,
							document:this.uploadDoc,
							fromTime:this.fromTime,
							toTime:this.toTime 
							}
        		}).then(function(response){
                   location.reload();
                    console.log(response.data);
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
                });			
		}
	},
	beforeMount(){
			    AXIOS({
        			method:'get',
        			url:'categories',
        			data:{}
        		}).then(function(response){
						this.tempMyCategories = response.data;     
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

.tender-table {
    height: 300px;
    width: 1000px;
}

</style>