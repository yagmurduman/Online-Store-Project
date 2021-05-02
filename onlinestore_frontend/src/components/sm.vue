<template>
    <!-- Editable table -->
    <div class="card">
   
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Sales Manager</h3>
    
   
    <div class="card-body">
        <div id="table" class="table-editable">
          <span class="table-add float-left mb-3 mr-2">
          <ul class="nav nav-tabs">
            <li class="nav-item">
              <a class="nav-link active" href="#/sm">Products</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/Delivery">Delivery List</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/Campaign">Campaigns</a>
            </li>
          </ul></span>
        <span class="table-add float-right mb-3 mr-2"><button 
                    class="btn btn-success btn-rounded btn-sm my-0" v-b-modal.modal-scrollable >Update Discount</button>
                    </span> 
              
                    
                    
                    <b-modal id="modal-scrollable" scrollable title="Enter Product Information">
                       <div class="modal-body">
                        <form>
                          
                          <div class="form-group">
                            <label for="pId" class="col-form-label">Product ID:</label>
                            <input type="number" v-model= "pId" class="form-control" >
                          </div> 
                          <div class="form-group">
                            <label for="discount" class="col-form-label">Discount:</label>
                            <input type="number" v-model= "discount" class="form-control" >
                          </div>
            
                        </form>
                      </div>
                       
                       <template v-slot:modal-footer="{ ok, cancel}">
                        
                        <button type="button" class="btn btn-secondary" @click=cancel() data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" @click="updateDiscount()">Save changes</button>
                      </template>
                    </b-modal>
                  
                  <span class="table-add float-right mb-3 mr-2"><button 
                    class="btn btn-success btn-rounded btn-sm my-0" v-b-modal.modal-scrollable2 >Update Price</button>
                    </span> 
                    <b-modal id="modal-scrollable2" scrollable title="Enter Product Information">
                       <div class="modal-body">
                        <form>
                         
                          <div class="form-group">
                            <label for="pId" class="col-form-label">Product ID:</label>
                            <input type="number" v-model= "pId" class="form-control" >
                          </div> 
                         
                          <div class="form-group">
                            <label for="price" class="col-form-label">Price:</label>
                            <input type="number" v-model= "price" class="form-control" >
                          </div>
                  
                        </form>
                      </div>
                      
                       <template v-slot:modal-footer="{ ok, cancel}">
                        <button type="button" class="btn btn-secondary" @click=cancel() data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" @click="updatePrice()">Save changes</button>
                       </template>
                    </b-modal>
                  
    

      <div class="mt-5 ml-4 md-form  input-group">
              <input v-model="search" class="form-control" placeholder="Search" aria-label="Search"
                aria-describedby="MaterialButton-addon2">
              <div class="input-group-append">
                <b-button class="btn btn-md btn-1 mr-5 px-3" @click="Search()"  id="MaterialButton-addon2">Search</b-button>
              </div>
        </div>
               

        <table class="table table-bordered table-responsive-md table-striped text-center">
            <tr>
                <th class="text-center">Product ID</th>
                <th class="text-center">Product Image</th>
                <th class="text-center">Product Type</th>
                <th class="text-center">Product Name</th>
                <th class="text-center">Price</th>
                <th class="text-center">Stock</th>
                <th class="text-center">Purchased Price</th>
                <th class="text-center">Selling Price</th>
                <th class="text-center">Discount</th>
                <th class="text-center">Description</th>
                <th class="text-center">Publisher</th>
                <th class="text-center">Published Date</th>

            </tr>

            <tr v-for="product in products" :key="product.pId">
              <td>
                    <span class="product">
                      <span>{{ product.pId}}</span>
                    </span>
              </td>

              <td style="height:100px;width:100px">
                
                  <span class="product">
                     <img v-bind:src="product.image"/>
                  </span>
              
              </td>

              <td>
               
                  <span class="product">
                    <span>{{ product.type}}</span>
                  </span>

              </td>

              <td>
               
                  <span class="product">
                    <span>{{ product.pName}}</span>
                  </span>
              
             </td>

             <td>
            
                  <span class="product">
                    <span>{{ product.price}}</span>
                  </span>
            
              </td>

             <td>
            
                  <span class="product">
                    <span>
                      {{ product.stock}}
                          </span>
                  </span>
        
              </td>

              <td>
              
                  <span class="product">
                    <span>{{ product.purchasedPrice}}</span>
                  </span>
            
               </td>

              <td>
              
                  <span class="product">
                    <span>{{ product.sellingPrice}}</span>
                  </span>
           
                </td>

              <td>
                
                    <span class="product">
                      <span>{{ product.discount}}</span>
                    </span>
            
                </td>

              <td>
                
                    <span class="product">
                      <span>{{ product.description}}</span>
                    </span>

                </td>

              <td>
                
                    <span class="product">
                      <span>{{ product.publisher}}</span>
                    </span>
              
                </td>

              <td>
                <tr class="pt-3-half" contenteditable="true">
                    <span class="product">
                      <span>{{ product.publishedDate}}</span>
                    </span>
                  </tr>
                </td>
              
            </tr>
          

        </table>

        <a @click="loadmore" id="loadMore">Load More</a>

        </div>
    </div>
    </div>
    <!-- Editable table -->
</template>

<script>
  import axios from 'axios';
  export default {
      name: 'SalesManager',

      data() {
          return {
          products: [],
          description: "",
          discount: "",
          image: "",
          pId: "",
          pName: "",
          price: "",
          publishedDate: "",
          publisher: "",
          purchasedPrice: "",
          sellingPrice: "",
          stock: "",
          type: "",
          update:0,
          search:""
          }
      }
      ,
      computed:{

          len() {return this.products.length;},
          getupdate()
         {
           return this.update;
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
       
      },

      methods: {

       
      loadmore(){ 

       if(this.update==0){
          axios.post('http://localhost:8090/product/get-all-products?key='+this.len)
          .then(response => {
            {if(response.status==200)
             
            for(let i=0 ;i<response.data.length;i++){
              this.products.push(response.data[i]);
            }
            }
          });}

      else if(this.update==2){
        this.Searchrest();

      }
      else{console.log("sıkıntıı")}
     
      },

      updatefunc1(){
        this.update=1;
        
      },
      updatefunc2(){
        this.update=2;
      },
     

      Search(){
    
         axios.post('http://localhost:8090/search/?search='+this.search)
          .then(response => {
            if( response.status==200)
            {this.products=response.data.value
            console.log(response.data.value)
            }

          })
          .catch(() => console.log('sm.vue'));
          
          this.updatefunc2()
      },
      
    Searchrest(){
      
       //ilk key 0
       axios.post("http://localhost:8090/search/search-limit?key="+this.len+ "&search="+this.search )
      .then(response => {
            {if(response.status==200)
             
            for(let i=0 ;i<response.data.length;i++){
              this.products.push(response.data[i]);
            }
  
            }
           
      })  
     .catch(() => console.log(this.products));


    },

      
      updateDiscount() {
        if(this.pId=="" || this.discount==""){
          this.error()
        }
        else{
        axios.put("http://localhost:8090/salesManager/update-discount/pId="+this.pId+"&discount="+this.discount)
        .then(response => 
          {
            if(response.status==200)
            { this.ChangesSaved()
             this.products=[]
            axios.post('http://localhost:8090/product/get-all-products?key=0',)
            .then(response => (this.products = response.data))
            .catch(() => console.log('pm.vue'));
              
            }
            else
            {
              this.error()
            }
          })
        .catch(() => console.log(this.pId))
        }
       
      },

      updatePrice() {
        if(this.pId=="" || this.price==""){
          this.error()
        }
        else{
        axios.put("http://localhost:8090/salesManager/update-price/pId="+this.pId+"&price="+this.price)
        .then(response => 
          {
            if(response.status==200)
            { this.ChangesSaved()
              this.products=[]
              axios.post('http://localhost:8090/product/get-all-products?key=0',)
              .then(response => (this.products = response.data))
              .catch(() => console.log('pm.vue'));

            }
             else
            {
              this.error()
            }
            
          })
        .catch(() => console.log(this.price))
        }

       
      },


    },

      mounted () {
      axios
        .post('http://localhost:8090/product/get-all-products?key=0',)
        .then(response => (this.products = response.data))
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
.btn {
  flex: 1 1 auto;
  margin: 10px;
  padding: 30px;
  text-align: center;
  text-transform: uppercase;
  transition: 0.5s;
  background-size: 200% auto;
  color: white;
 /* text-shadow: 0px 0px 10px rgba(0,0,0,0.2);*/
  
  border-radius: 10px;
 }

.btn:hover {
  background-position: right center; /* change the direction of the change here */
}
.btn-5 {
  background-image: linear-gradient(to right, #ffecd2 0%, #fcb69f 51%, #ffecd2 100%);
}
.btn-1 {
  background-image: linear-gradient(to right, #EFEEBB 0%, #EDA18D 51%, #EFEEBB 100%);
}

#loadMore {
  width: 200px;
  color: #fff;
  display: block;
  text-align: center;
  margin: 40px auto;
  padding: 10px;
  border-radius: 10px;
  border: 1px solid transparent;
  background-image: linear-gradient(to right, #fdddb0 0%, #fc9b7a 51%, #fdddb0 100%);
  transition: .3s;
}
#loadMore:hover {
  color: #ffecd2;
  background-color: #fff;
  border: 1px solid #ffecd2;
  text-decoration: none;
}



</style>
