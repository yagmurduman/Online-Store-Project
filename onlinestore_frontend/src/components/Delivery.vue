<template>
    <!-- Editable table -->
    
    <div class="card">
  
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Delivery List</h3>
   
    <div class="card-body">
        <div id="table" class="table-editable">
        <span class="table-add float-left mb-3 mr-2">
          <ul class="nav nav-tabs">
            <li class="nav-item">
              <a class="nav-link" href="#/sm">Products</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" href="#/Delivery">Delivery List</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/Campaign">Campaigns</a>
            </li>
             
          </ul></span>
     
     <v-date-picker mode='range'
    v-model='range' 
    :input-props='{
  
    placeholder: "Please enter the date range",
    readonly: true
    }' />
    <b-button class="btn btn-md mr-2 mt-4" @click="Filter()"  id="MaterialButton-addon2">Filter</b-button>
    <b-button class="btn btn-md mr-2 mt-4" @click="Clear()"  id="MaterialButton-addon2">Clear</b-button>

    </div>          
        <table class="table table-bordered table-responsive-md  text-center mt-4">
            <thead>
               <th class="text-center">Delivery ID</th>
                <th class="text-center">Customer ID</th>
                <th class="text-center">Product Name</th>
                <th class="text-center">Delivery Date</th>
                <th class="text-center">Quantity</th>
                <th class="text-center">Price</th>
                <th class="text-center">Update Delivery Status</th>

            </thead>
            <tbody v-for="delivery in deliveries" :key="delivery.dId">
              <tr bgcolor=lightgrey>
              <td  >
                    <span class="delivery">
                      <span>{{ delivery[0].dId}}</span>
                    </span>
              </td>
              <td>
                  <span class="delivery">
                    <span>{{ delivery[0].cId}}</span>
                  </span>

              </td>
            <td></td>
           
            <td>
            
                  <span class="delivery">
                    <span>{{ delivery[0].date}}</span>
                  </span>
            
              </td>

             <td> </td>
             <td> </td>
              <td>
               
                  <span v-if=" delivery[0].delivered==true" class="delivery">
                    <span>  <button  class="btn btn-success btn-rounded btn-sm my-0" >Delivered</button></span>
                  </span>
                  <span v-if=" delivery[0].delivered==false" class="delivery">
                    <span>  <button @click="updateDelivery(delivery[0].dId)" class="btn btn-danger btn-rounded btn-sm my-0" >Not Delivered</button></span>
                  </span>
                  
        
              </td> 
             </tr >
              
             <tr id="items" v-for="x in delivery" :key="x.pName">
              <td></td>
              <td></td>
              <td>{{x.pName}}</td>
              <td></td>
              <td>{{x.quantity}}</td>
              <td>${{x.price}}</td>
              <td></td>
              
            </tr> 
              
          
            </tbody>
        
        </table>


        </div>
    </div>
    
    <!-- Editable table -->
</template>

<script>
  import axios from 'axios';
  import moment from 'moment'

  export default {
      name: 'DeliveryList',

      computed:{
        st(){ return moment(this.range.start,'YYYY-MM-DD').format().substr(0,10)},
        end(){return moment(this.range.end,'YYYY-MM-DD').format().substr(0,10)}

      },
      data() {
          return{
          deliveries: [],
          cId:"",
          dId:"",
          date:"",
          delivered:"" ,
          pId:"",
          price:"", 
          status:"",
          quantity:"", 
          range: {
              start: new Date(), 
              end: new Date()  , 
             
          }
          }
      }
      ,
      notifications: {
     
      ChangesSaved: { // You can have any name you want instead of 'showLoginError'
        title: 'Your changes are saved!',
        type: 'success' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
        error: { // You can have any name you want instead of 'showLoginError'
        title: 'Check empty fields!',
        type: 'error' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
       
      },

      methods: {

        updateDelivery(did) {
          axios.put("http://localhost:8090/salesManager/update-delivered/dId="+did)
          .then(response =>   
          {
            if(response.status==200)
            { this.ChangesSaved()
              
            }
            else
            {
              this.error()
            }
          })
          this.deliveries=[]
          axios.get("http://localhost:8090/delivery/get-invoice-by-date?fromDate="+this.st+"&toDate="+this.end)
          .then(response=> (this.deliveries= response.data))
          .catch(() => console.log())
          
          
          },

        Filter(){
        console.log(this.st)
        axios.get("http://localhost:8090/delivery/get-invoice-by-date?fromDate="+this.st+"&toDate="+this.end)
        .then(response=> (this.deliveries= response.data))
        .catch(() => console.log())

        },

        Clear(){
           axios
          .get('http://localhost:8090/delivery/get-all-deliveries')
          .then(response => (this.deliveries = response.data))
          .catch(() => console.log('pm.vue'));


        }
         
    },

      mounted () {
      axios
        .get('http://localhost:8090/delivery/get-all-deliveries')
        .then(response => (this.deliveries = response.data))
        .catch(() => console.log('pm.vue'));

      },

      updated(){
      }
     

     

  }
</script>

<style scoped>

img {
  width: 50%;
  height: 80%;
}

.column {
  float: left;
  width: 50%;
}

/* Clear floats after the columns */
.row1{
  margin-top: 5%;
}
.row1:after{
  content: '';
  display: table;
  clear: both;
  margin-left: 40%;
  margin-top:10%;
}
.row2:after {
  content: '';
  display: table;
  clear: both;
  margin-left: 40%;
  margin-top:10%;
}



container {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 50px;
  display: grid;
  grid-template-rows: auto;
  grid-template-columns: repeat(1, 100%);
}
</style>
  