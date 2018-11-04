<template>
	<div class = 'bidHistory'>
	  <b-container>
		  <b-row class="text-left">
		  	Tender Bidded
		  </b-row>
		  <b-row>
                <b-table :items="bidItems" :fields="bidFields" :current-page="bidCurrentPage"
                        :per-page="20">
                 </b-table>
                <b-pagination size="md" :total-rows="bidTotRow" :per-page="20" v-model="bidCurrentPage" class="my-0">
                </b-pagination>
		  </b-row>
		 <b-row>
				Description
				<b-form-textarea id="description"
					v-model="text"
					placeholder="Description"
					:rows="3"
					:max-rows="6">
				</b-form-textarea>
		  </b-row>
		  <b-row class="text-left">
			  <b-col sm="1" >
			  	Price
			  </b-col>
			  <b-col sm="1" >
			  	<input v-model="priceInput" class="u-full-width" type="text" placeholder="price" id="priceInput"/>
			  </b-col> 
		  </b-row>
		  <b-row>
			  Remark
			<b-form-textarea id="remark"
					v-model="text"
					placeholder="Remark"
					:rows="3"
					:max-rows="6">
			</b-form-textarea>
		  </b-row>
		  <b-row class="text-left">
		  	<b-button>Bid</b-button>
		  </b-row>
	  </b-container>
	</div>
</template>
<script>
import {AXIOS} from './http-common'

const tempBidItems = [
	{ User_name: 'Dickerson', Company_name: 'Macdonald' },
	 { User_name: 'Dickerson', Company_name: 'Macdonald' }
]

export default {
	name: 'tenderHistory',
	data () {
		return {
			bidItems: tempBidItems,
            bidFields: [ 'User_name', 'Company_name', 'Add_to' ],
            bidCurrentPage: tempBidItems.length,
			bidTotRow: 1,	
		}
	},
	methods: {
	}
}
  
</script>