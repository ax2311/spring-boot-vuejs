<template>
  <div class="Home">
  	<h1>{{ hellomsg }}</h1>
  	<b-container>  		
  		<b-row  class="text-left with-margin">
  			<div  >
				<b-button :disabled="!containRole('PROPERTY_MANAGEMENT')" href="/#/maintainVendorList">Maintain List</b-button><br>
				Add or remove your frequently contacted list of Supplier or Engineer Vendor<br>
				The list will be used in publish tender
			</div>
		</b-row>
		<b-row  class="text-left with-margin">
			<div >
				<b-button :disabled="!containRole('PROPERTY_MANAGEMENT')" href="/#/publishTender">Publish Tender</b-button><br>
				Publish a tender with details of date, address, price,etc and set the receivers from your list
			</div>
		</b-row>
		<b-row  class="text-left with-margin">
			<div >
				<b-button :disabled="!containRole('PROPERTY_MANAGEMENT')" href="/#/currentTender">Current Tender</b-button><br>
				View your published tenders. Check the list of receivers of each tender and accept one of the bids. 
			</div>
		</b-row>
		<b-row  class="text-left with-margin">
			<div  >
				<b-button :disabled="!containRole('ENGINEER_VENDOR') && !containRole('SUPPLIER')" href="/#/bidTender">Bid Tender</b-button><br>
				View the tenders you received. Bid with a price and remarks to let the sender knows.
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

export default {
  name: 'Home',
  props: { hellomsg: { type: String, required: true } },
  data () {
    return {
    	userroles : []
    }
  },
  mounted(){
      if(getCookie("access_token")){       

          AXIOS.get("/getUserRole?access_token=" + getCookie("access_token"))
              .then(function(response){
                  this.userroles = response.data ;
                  console.log( this.userroles);
              }.bind(this))
              .catch(function(error){
                 console.log(error);
              }); 
      }
  },
  methods : { 
      containRole(role){
          for (var i in this.userroles) {                                               
              if (this.userroles[i] == role)
              return true;
          }            
      }
  }
  /* props: { hellomsg: { type: String, required: true } } */
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.with-margin {
  margin-bottom: 20px;
}
</style>
