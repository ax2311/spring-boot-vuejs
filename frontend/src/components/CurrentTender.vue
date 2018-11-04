<template>
	<div class = 'currentTender'>
		<div class="container">
			<b-row>
				<h4>My Tender List</h4>
				(click to view detail)
			</b-row>
			<b-row>
				<div class="tender-table">
						<b-table show-empty :items="currTenderListItems" :fields="currTenderListFields" :current-page="currTenderListCurrentPage"
								:per-page="5" striped hover @row-clicked="clickHandler">

							<template slot="empty" >
                				Sorry, there is nothing to display here.
            				</template>
						</b-table>
				</div>
			</b-row>
			<b-row>
                <b-pagination size="md" :total-rows="currTenderListTotRow" :per-page="5" v-model="currTenderListCurrentPage" class="my-0">
                </b-pagination>
			</b-row>
			<b-row>
				<h4>Description</h4>
			</b-row>
			<b-row>
				<b-form-textarea id="description" class="textarea"
					v-model="description"
					placeholder="Description"
					disabled
					:rows="3"
					:max-rows="6">
				</b-form-textarea>
			</b-row>
			<b-row>
				<h4>Bids</h4>
				(click to view remarks)
			</b-row>
			<b-row>
				<div class="tender-table">
					<b-table show-empty :items="bidListItems" :fields="bidListFields" :current-page="bidListCurrentPage"
							:per-page="5" striped hover @row-clicked="clickHandlerBid" class="bid-table">
							<template slot="accepted" slot-scope="row">
								<div v-if="tenderClose && row.item.accepted === 'Y'">
									This is accepted
								</div>
								<div v-else-if = "!tenderClose && row.item.bidded === 'Y'">
									<b-button @click="acceptBid(row.item)">Accept</b-button>
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
			<b-row>
                <b-pagination size="md" :total-rows="bidListTotRow" :per-page="10" v-model="bidListCurrentPage" class="my-0">
                </b-pagination>
			</b-row>
			<b-row>
				<h4>Remark</h4>
			</b-row>
			<b-row>
				<b-form-textarea id="remarks" class="textarea"
					v-model="remarks"
					disabled
					placeholder="remarks"
					:rows="3"
					:max-rows="6">
				</b-form-textarea>
			</b-row>
		</div>
	</div>
</template>
<script>
import {AXIOS} from './http-common'

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

const tempCurrTenderListItems = [

]

const tempBidListItems = [

]

export default {
	name: 'currentTender',
	data () {
		return {
			currTenderListItems: tempCurrTenderListItems,
            currTenderListFields: {
								title: { label: 'Title' }, 
								fromDate: { label: 'Start Date' },
								toDate: { label: 'End Date' },
								minimumPrice: { label: 'Minimum' },
								maximumPrice: { label: 'Maximum' }, 
								 dateAdded: { label: 'Added Date' },
								 dateExpired: { label: 'Expired Date' }								 
								  },
            currTenderListCurrentPage: 1,
			currTenderListTotRow: 1,	
			bidListItems: tempBidListItems,
            bidListFields: {
								username: { label: 'Username' }, 
								companyName: { label: 'Company Name' },
								price: { label: 'Price' },
								accepted: { label: 'Accept' },
								detail: { label: 'Detail' },
								bidded: { label: 'Bidded'}
							},
            bidListCurrentPage: 1,
			bidListTotRow: 1,	
			description: '',
			remarks: '',
			selectedTenderId: '',
			tenderClose:'false'
		}
	},
	methods: {
		clickHandler(item){
			AXIOS({
        			method:'get',
        			url:'getTenderDescriptionByTenderId',
					params:{
						tenderId:item.tenderId,companyType:item.companyType
					}
        		}).then(function(response){
						this.description = response.data; 
						this.selectedTenderId = item.tenderId
						this.remarks = '';
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
				});
				
			AXIOS({
        			method:'get',
        			url:'getTenderDetails',
					params:{
						tenderId:item.tenderId,companyType:item.companyType,access_token:getCookie("access_token")
					}
        		}).then(function(response){
					this.bidListItems = response.data;
					this.bidListTotRow = this.bidListItems.length;
					this.tenderClose = _.includes(_.map(this.bidListItems, 'accepted'), 'Y');
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
                });
		},
		clickHandlerBid(item){
			AXIOS({
        			method:'get',
        			url:'getRemarks',
					params:{
						tenderDetailId:item.tenderDetailId
					}
        		}).then(function(response){
						this.remarks = response.data;					
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
				});
		},
		acceptBid(item){
				AXIOS({
        			method:'put',
        			url:'acceptBid',
					params:{
						tenderDetailId:item.tenderDetailId,access_token:getCookie("access_token")
					}
        		}).then(function(response){
					location.reload();
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
				});
		}
	},
	beforeMount(){
			    AXIOS({
        			method:'get',
        			url:'currentTenders',
					params:{
						access_token:getCookie("access_token")
					}
        		}).then(function(response){
						this.currTenderListItems = response.data;     
						this.currTenderListTotRow = this.currTenderListItems.length;
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

.bid-table {
	width: 1000px;
}

.textarea {
	width: 1000px;
}
</style>