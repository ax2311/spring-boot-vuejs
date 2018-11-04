<template>
	<div class = 'bidTender'>
	  <b-container>
		  <b-row class="text-left">
		  	<h4>Tender Received</h4>
			  (click to view description)
		  </b-row>
		  <b-row>
                <b-table show-empty :items="receivedTenderItems" :fields="receivedTenderFields" :current-page="receivedTenderCurrentPage"
                        :per-page="5"  striped hover @row-clicked="clickHandler">
                    <template slot="detail" slot-scope="row">
                                <a target="_blank" :href="'/#/viewProfile?username=' + row.item.username">click</a>
                    </template>
					<template slot="empty" >
                			Sorry, there is nothing to display here.
            		</template>
                 </b-table>
		  </b-row>
		  <b-row>
                <b-pagination size="md" :total-rows="receivedTenderTotRow" :per-page="5" v-model="receivedTenderCurrentPage" class="my-0">
                </b-pagination>
		  </b-row>
		 <b-row class="with-margin">
				Description
				<b-form-textarea id="description"
					v-model="description"
					disabled
					:rows="3"
					:max-rows="6">
				</b-form-textarea>
		  </b-row>
		<!--   <b-row class="with-margin">
			  Document
		  </b-row> -->
		  <b-row class="text-left with-margin" >
			  	Price
			  <b-col sm="1" >
			  	<b-form-input v-model="bidPrice" class="u-full-width" 	type="number" :state="priceState"
                  aria-describedby=" priceLiveFeedback" placeholder="price" onkeydown='return (event.which >= 48 && event.which <= 57) || event.which == 8 || event.which == 46' id="bidPrice"/>

				<b-form-invalid-feedback id="priceLiveFeedback">      
      					Please enter integer of maximum 7 digits
    				</b-form-invalid-feedback>
			  </b-col> 
		  </b-row>
		  <b-row class="with-margin">
			  Remark
			<b-form-textarea id="remarks"
					v-model="remarks"
					placeholder="Remarks"
					:rows="3"
					:max-rows="6">
			</b-form-textarea>
		  </b-row>
		  <b-row class="text-left with-margin">
			  <div v-if="bidded==='Y'">
				  This is already bidded
			  </div>
			  <div v-else>
		  		<b-button @click="bid">Bid</b-button>
			  </div>
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

const tempReceivedTenderItems = [
]

export default {
	name: 'bidTender',
	computed: {
		priceState(){
			if(this.bidPrice && this.bidPrice.toString().length < 8)
				return true;

			return false;
		}
	},
	data () {
		return {
			receivedTenderItems: tempReceivedTenderItems,
			receivedTenderFields: { username: { label: 'Username'},
								company: { label: 'Company' }, 
								title: { label: 'Title' }, 
								fromDate: { label: 'Start Date' },
								toDate: { label: 'End Date' },
								minimumPrice: { label: 'Minimum' },
								maximumPrice: { label: 'Maximum' }, 
								 dateAdded: { label: 'Added Date' },
								 dateExpired: { label: 'Expired Date' },
								 detail: { label: 'Detail' } },
            receivedTenderCurrentPage: 1,
			receivedTenderTotRow: 1,	
			description: null,
			bidPrice: null,
			remarks: '',
			selectedTenderDetailId: null,
			bidded: 'N'
		}
	},
	methods: {
		clickHandler(item){
			AXIOS({
        			method:'get',
        			url:'getTenderDescriptionAndBidded',
					params:{
						tenderDetailId:item.tenderDetailId
					}
        		}).then(function(response){
						this.description = response.data.description;
						this.bidded = response.data.bidded;
						console.log(this.description); 
						this.selectedTenderDetailId = item.tenderDetailId
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
                });
		},
		bid(){
			if(!this.priceState)
				return ;

			AXIOS({
        			method:'post',
        			url:'bidTender',
					params:{
						tenderDetailId:this.selectedTenderDetailId,remarks:this.remarks,bidPrice:this.bidPrice,
						access_token:getCookie("access_token")
					}
        		}).then(function(response){
					response.data; 
					location.reload();
        		}.bind(this)).catch(function (error) {
                    console.log(error);                    
                });
		}
	},
	beforeMount(){
			    AXIOS({
        			method:'get',
        			url:'receivedTenders',
					params:{
						access_token:getCookie("access_token")
					}
        		}).then(function(response){
						this.receivedTenderItems = response.data;     
						this.receivedTenderTotRow = this.receivedTenderItems.length;
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
</style>