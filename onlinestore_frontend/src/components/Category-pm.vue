<template>
    <!-- Editable table -->
    <div class="card">
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Category List</h3>
    <div class="card-body">
        <div id="table" class="table-editable">
        <span class="table-add float-left mb-3 mr-2">
          <ul class="nav nav-tabs">
            <li class="nav-item">
              <a class="nav-link" href="#/pm">Products</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" href="#/Category-pm">Categories</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/Delivery-pm">Deliveries</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/ProductCategory">Assign Categories</a>
            </li>
             <li class="nav-item">
              <a class="nav-link" href="#/Review-pm">Reviews</a>
            </li>
          </ul></span>
        <span class="table-add float-right mb-3 mr-2"><button 
                    class="btn btn-success btn-rounded btn-sm my-0" v-b-modal.modal-scrollable >Add SubCategory</button>
                    </span> 
                    
                    
                    
                    <b-modal id="modal-scrollable" scrollable title="Enter Product Information">
                       <div class="modal-body">
                        <form>
                          <div class="form-group">
                            <label for="catName" class="col-form-label">Category Name:</label>
                            <input type="string" v-model= "catName" class="form-control" id="catName">
                          </div> 

                          <div class="form-group">
                            <label for="type" class="col-form-label">Type:</label>
                            <input type="string" v-model= "type" class="form-control" id="dId">
                          </div> 
    
                        </form>
                      </div>
                      <template v-slot:modal-footer="{ ok, cancel}">
                        <button type="button" class="btn btn-secondary" @click="cancel()" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" @click="insertCategory()">Save changes</button>
                      </template>
                      
                    </b-modal> 

               
        <table class="table table-bordered table-responsive-md table-striped text-center">
            <tr>
               <th class="text-center">Category ID</th>
                <th class="text-center">Category</th>
                <th class="text-center">Type</th>
                <th class="text-center">Delete</th>

            </tr>
            <tr v-for="category in categories" :key="category.catId">
              <td>
                    <span class="category">
                      <span>{{ category.catId}}</span>
                    </span>
              </td>


              <td>
               
                  <span class="category">
                    <span>{{ category.catName}}</span>
                  </span>

              </td>

              <td>
               
                  <span class="delivery">
                    <span>{{ category.type}}</span>
                  </span>
              
             </td>

            <td>
                    <span class="table-remove"><button 
                    class="btn btn-danger btn-rounded btn-sm my-0" @click="deleteCategory(category.catId)">Delete</button></span>

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
      name: 'CategoryList',

      data() {
          return{
          categories: [],
          catId:"",
          catName:"",
          type:"",   
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
        remove:{
        title: 'Product removed!',
        type: 'success' // You also can use 'VueNotifications.types.error' instead of 'error'

        }
       
      },

      methods: {
       
        insertCategory() {
         if(this.catName==""||this.type==""){
           this.error()
         }
         else{
        axios.post("http://localhost:8090/category/insert-category",{
                        catName: this.catName,
                        type: this.type,
                      })
        .then(this.ChangesSaved())
        .catch(() => console.log(this.catName + this.type ));
         }
        
     }, 
        deleteCategory(catId) {
        axios.delete("http://localhost:8090/category/delete-category="+catId)
        .then(this.remove())
        .catch(() => console.log(catId));
      },


    },

      mounted () {
      axios
        .get('http://localhost:8090/category/get-all-subcategories')
        .then(response => (this.categories = response.data))
        .catch(() => console.log('pm.vue'));
      },
        updated(){
        axios
          .get('http://localhost:8090/category/get-all-subcategories')
          .then(response => (this.categories = response.data))
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
  
