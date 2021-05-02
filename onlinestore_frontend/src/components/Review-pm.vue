<template>
    <!-- Editable table -->
    <div class="card">
      
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">New Reviews</h3>
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
              <a class="nav-link " href="#/Delivery-pm">Deliveries</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/ProductCategory">Assign Categories</a>
            </li>
              <li class="nav-item">
              <a class="nav-link active" href="#/Review-pm">Reviews</a>
            </li>
          </ul></span>

        <span class="table-add float-right mb-3 mr-2">
                    </span> 

       
                     
        <table class="table table-bordered table-responsive-md table-striped text-center">
            <tr>
                <th class="text-center">PId</th>
                <th class="text-center">Customer Name</th>
                <th class="text-center">Comment</th>
                <th class="text-center">Delivery Date</th>
                <th class="text-center">Review Status</th>
                <th class="text-center">Approve</th>
                <th class="text-center">Delete</th>


            </tr>
                        
         
            <tr v-for="review in reviews" :key="review.rId">
               <td>
                    <span class="delivery">
                      <span>{{ review.pId}}</span>
                    </span>
              </td>

              <td>
                    <span class="delivery">
                      <span>{{ review.cName}}</span>
                    </span>
              </td>


              <td>
               
                  <span class="delivery">
                    <span>{{ review.comment}}</span>
                  </span>

              </td>

              <td>
               
                  <span class="delivery">
                    <span>{{ review.rDate}}</span>
                  </span>
              
             </td>

             <td>
            
                  <span class="delivery">
                    <span>{{ review.approved}}</span>
                  </span>
            
              </td>
               <td>
            
                  <span class="delivery">
                    <span>  <button @click="approve(review.cId,review.pId)" class="btn btn-success btn-rounded btn-sm my-0" >Approve</button></span>
                  </span>
            
              </td>
               <td>
            
                  <span class="delivery">
                    <span>  <button  @click="deleterev(review.cId,review.pId)" class="btn btn-danger btn-rounded btn-sm my-0" >Delete</button></span>
                  </span>
            
              </td>

            

              
            </tr>
          
        
        </table>


        </div>
    </div>
    </div>
    <!-- Editable table -->
</template>

<script>
  import axios from 'axios';
  export default {
      name: 'ReviewList',

      data() {
          return{
          reviews: [],

          cId:"",
          dId:"",
          date:"",
          delivered:"" ,
          pId:"",
          price:"", 
          quantity:"", 
          }
      },

      methods: {

          approve(cid,pid){
            axios
                .put("http://localhost:8090/productManager/update-isApproved/cId="+cid+"&pId="+pid+"&approval=true")
            

          },

          deleterev(cid,pid){
             
               axios
                .delete(" http://localhost:8090/review/delete-review/cId="+cid+"&pId="+pid)
               
          }


      },


      mounted () {
      axios
        .get('http://localhost:8090/review/get-all-reviews-to-approve',)
        .then(response => (this.reviews = response.data))
        .catch(() => console.log('pm.vue'));
      },
        updated(){
        axios
          .get('http://localhost:8090/review/get-all-reviews-to-approve',)
          .then(response => (this.reviews = response.data))
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