<template>
    <!-- Editable table -->
    <div class="card">
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Product Categories</h3>
    <div class="card-body">
        <div id="table" class="table-editable">
        <span class="table-add float-right mb-3 mr-2">
          <button class="btn btn-success btn-rounded btn-sm my-0" v-b-modal.modal-scrollable >Assign SubCategory</button>
        </span> 

        <b-modal id="modal-scrollable" scrollable title="Enter Product Information">
                       <div class="modal-body">
                        <form>
                          <div class="form-group">
                            <label for="pId" class="col-form-label">Product ID:</label>
                            <input type="text" v-model= "pId" class="form-control" id="pId" >
                          </div>
                          <div class="form-group">
                            <label for="catId" class="col-form-label">Category ID:</label>
                            <input type="number" v-model= "catId" class="form-control" id="catId">
                          </div>   
                        </form>
                      </div>
                       <template v-slot:modal-footer="{ ok, cancel}">
                        <button type="button" class="btn btn-secondary" @click="cancel()" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" @click="assignCategory()">Save changes</button>
                      </template>
                    </b-modal>

        <span class="table-add float-left mb-3 mr-2">
          <ul class="nav nav-tabs">
            <li class="nav-item">
              <a class="nav-link" href="#/pm">Products</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/Category-pm">Categories</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/Delivery-pm">Deliveries</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" href="#/ProductCategory">Assign Categories</a>
            </li>
             <li class="nav-item">
              <a class="nav-link" href="#/Review-pm">Reviews</a>
            </li>
          </ul></span>

        <span class="table-add float-right mb-3 mr-2">
                    </span> 
                    
        

        <table class="table table-bordered table-responsive-md table-striped text-center">
          <thead>
            <tr>
               <th class="text-center">Product ID</th>
               <th class="text-center">Category ID</th>

            </tr>
          </thead>
          <tbody>
            <tr v-for="productCategory in productCategories" :key="productCategory.pId">
              <td>
                    <span class="productCategory">
                      <span>{{ productCategory.pId}}</span>
                    </span>
              </td>


              <td>
               
                  <span class="productCategory">
                    <span>{{ productCategory.catId}}</span>
                  </span>
                </td>
              
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
      name: 'ProductCategoryList',

      data() {
          return{
          productCategories: [],
          pId:"",
          catId:"",   
          }
      },
      notifications: {
     
      ChangesSaved: { // You can have any name you want instead of 'showLoginError'
        title: 'Your changes are saved!',
        type: 'success' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
        error: { // You can have any name you want instead of 'showLoginError'
        title: 'Check empty fields!',
        type: 'error' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
        notfound: { // You can have any name you want instead of 'showLoginError'
        title: 'Not found!',
        type: 'error' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
        remove:{
        title: 'Product removed!',
        type: 'success' // You also can use 'VueNotifications.types.error' instead of 'error'

        }
       
      },
      

      methods: {
        assignCategory() {
        if( this.pId==""|| this.catId==""){
          this.error()
        }
         else{
        axios.post("http://localhost:8090/category/insert-product-with-category",{
          pId: this.pId,
          catId: this.catId
        })

        .then(response => 
          {
            if(response.status==200)
            { this.ChangesSaved()
              
            }
            else
            {
              this.notfound()
            }
          })
        .catch(() => console.log());
      }},

    },

      mounted () {
      axios
        .get('http://localhost:8090/category/get-all-product-with-category',)
        .then(response => (this.productCategories = response.data))
        .catch(() => console.log('pm.vue'));
      },
        updated(){
        axios
          .get('http://localhost:8090/category/get-all-product-with-category',)
          .then(response => (this.productCategories = response.data))
          .catch(() => console.log('pm.vue'));
      },


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