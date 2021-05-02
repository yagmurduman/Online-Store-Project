<template>
    <!-- Editable table -->
    <div class="card">
      
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Delivery List</h3>
    
    <div class="card-body">
        <div id="table" class="table-editable">

        <span class="table-add float-left mb-3 mr-2">
          <ul class="nav nav-tabs">
            <li class="nav-item">
              <a class="nav-link" href="#/pm">Products</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/Category-pm">Categories</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" href="#/Delivery-pm">Deliveries</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/ProductCategory">Assign Categories</a>
            </li>
             <li class="nav-item">
              <a class="nav-link" href="#/Review-pm">Reviews</a>
            </li>
          </ul></span>

        <span class="table-add float-right mb-3 mr-2">
                    </span> 

        
                     
        <table class="table table-bordered table-responsive-md  text-center">
            <thead>
               <th class="text-center">Delivery ID</th>
                <th class="text-center">Customer ID</th>
                <th class="text-center">Product ID</th>
                <th class="text-center">Delivery Date</th>
                <th class="text-center">Quantity</th>
                <th class="text-center">Price</th>
                <th class="text-center">Delivery Status</th>

            </thead>
                        
         
            <tbody v-for="delivery in deliveries" :key="delivery.dId">
              <tr bgcolor=lightgrey>
              <td>
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

            <td></td>

             <td></td>

             <td>
            
                  <span class="delivery">
                    <span>
                      {{ delivery[0].delivered}}
                    </span>
                  </span>
        
              </td>  
              
            </tr>
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
    </div>
    <!-- Editable table -->
</template>

<script>
  import axios from 'axios';
  export default {
      name: 'DeliveryList',

      data() {
          return{
          deliveries: [],
          cId:"",
          dId:"",
          date:"",
          delivered:"" ,
          pId:"",
          price:"", 
          quantity:"", 
          }
      },


      mounted () {
      axios
        .get('http://localhost:8090/delivery/get-all-deliveries',)
        .then(response => (this.deliveries = response.data))
        .catch(() => console.log('pm.vue'));
      },
        updated(){
        axios
          .get('http://localhost:8090/delivery/get-all-deliveries',)
          .then(response => (this.deliveries = response.data))
          .catch(() => console.log('pm.vue'));
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