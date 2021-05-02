
<template>

  <div class="container">
 
  <img  class="container rounded" style=" box-shadow: 0 0 10px rgb(124, 85, 75);"  src="../assets/Music.png" alt="" />
   

 <!--Section: Content-->
     <section  style=" box-shadow: 0 0 10px rgb(124, 85, 75);" class="row-fluid">
      <!--Grid row-->
      <div class="row d-flex mx-auto mt-4 justify-content-start">
        
        <!--Grid column-->
        <div class="col-lg-2 border mx-auto">

          <!-- Filter panel -->
          
          <div class="mb-5 mt-5">
            <h5 class="font-weight-bold mb-3">Order by</h5>

            <div class="divider-small mb-3"></div>

            <ul class="list-unstyled link-black">
             <li class="mb-2">
                <input type="radio" v-model="order" value="0" class="form-check-input" id="materialGroupExample1" name="groupOfMaterialRadios">
                 <label class="form-check-label" for="materialGroupExample1">Default</label>
                
              </li>

              <li class="mb-2">
                <input type="radio" v-model="order" value="1" class="form-check-input" id="materialGroupExample1" name="groupOfMaterialRadios">
                 <label class="form-check-label" for="materialGroupExample1">Popularity</label>
                
              </li>
              <li class="mb-2">
                 <input type="radio" v-model="order" value="2" class="form-check-input" id="materialGroupExample1" name="groupOfMaterialRadios">
                 <label class="form-check-label" for="materialGroupExample1">Rating</label>
                
              </li>
              <li class="mb-2">
                 <input type="radio" v-model="order" value="3" class="form-check-input" id="materialGroupExample1" name="groupOfMaterialRadios">
                 <label class="form-check-label" for="materialGroupExample1">Price: low to high</label>
               
              </li>
              <li class="mb-2">
                 <input type="radio" v-model="order" value="4" class="form-check-input" id="materialGroupExample1" name="groupOfMaterialRadios">
                 <label class="form-check-label" for="materialGroupExample1">Price: high to low</label>
               
              </li>
            </ul>
          </div>
          <!-- Filter panel -->

          <!-- Filter panel -->
          <!--
          <div class="mb-5">

            <h5 class="font-weight-bold mb-3">Category</h5>

            <div class="divider-small mb-3"></div>

            
            <div v-for="c in cat" :key="c.catId" class="form-check pl-0 mb-2">
              <input type="radio"  v-model="category" :value="c.catId" class="form-check-input" id="materialGroupExample1" name="groupOfMaterialRadios">
              <label class="form-check-label" for="materialGroupExample1">{{c.catName}}</label>
            </div>

          </div>
          -->
          <!-- Filter panel -->

          <!-- Filter panel -->
          <div class="mb-5">

            <h5 class="font-weight-bold mb-3">Max Price</h5>

            <div class="divider-small mb-3"></div>

            <form class="range-field">
              <input type="range" min="0" max="300"  v-model="price" />
            </form>

            <div class="row justify-content-start">

              <!-- Grid column -->
              <div class="col-sm-4 text-left">
                <p class="dark-grey-text"><strong id="resellerEarnings">0$</strong></p>
              </div>
              <!-- Grid column -->
               <div class="col-sm-4 text-center">
                <p class="dark-grey-text"><strong id="resellerEarnings">{{this.price}}$</strong></p>
              </div>

              <!-- Grid column -->
              <div class="col-sm-4 text-right">
                <p class="dark-grey-text"><strong id="clientPrice">300$</strong></p>
              </div>
              <!-- Grid column -->
            </div>

          </div>
          <!-- Filter panel -->

          <!-- Filter panel -->
          <div class="link-black">

       <h5 class="font-weight-bold mb-3">Min Rating</h5>

            <div class="divider-small "></div>
              <input type="radio" v-model="rate" value="5" class="form-check-input" id="materialGroupExample3" name="groupOfMaterialRadios3">
              <label class="form-check-label1" for="materialGroupExample3">
            <div class="amber-text fa-sm mb-2">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <a class="ml-2 active">5</a>
            </div>
            </label>
            <div class="divider-small"></div>
            <input type="radio"  v-model="rate" value="4" class="form-check-input" id="materialGroupExample3" name="groupOfMaterialRadios3">
            <label class="form-check-label1" for="materialGroupExample3">
            <div class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <a  class="ml-2">4</a>
            </div>
            </label>
          
            <div class="divider-small"></div>
          <input type="radio"  v-model="rate" value="3" class="form-check-input" id="materialGroupExample3" name="groupOfMaterialRadios3">
              <label class="form-check-label1" for="materialGroupExample3">
              <div class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <a  class="ml-2">3</a>
            </div></label>
              <div class="divider-small"></div>
            <input type="radio"  v-model="rate" value="2" class="form-check-input" id="materialGroupExample3" name="groupOfMaterialRadios3">
              <label class="form-check-label1" for="materialGroupExample3">
             <div class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <a  class="ml-2">2</a>
            </div></label>
              <div class="divider-small "></div>
            <input type="radio"  v-model="rate" value="1" class="form-check-input" id="materialGroupExample3" name="groupOfMaterialRadios3">
              <label class="form-check-label1" for="materialGroupExample3">
             <div class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <a  class="ml-2">1 </a>
            </div></label>


          </div>
          <!-- Filter panel -->
         <a @click="filter()" id="Filter">Filter</a>
        </div>

    <div class=" col mt-4 ">
      
   
    <!-- Grid row -->
    <div v-for="(chunk,i) in groupedProducts" :key=i class="row">
      <!-- Grid column -->
      <div class="col-3-center  " v-for="product in chunk" :key="product.pId">
      
        <!-- Card -->
        
            <div class="col ">
              <div class="card ">
                <div class="view overlay">
                  <img  class="img-thumbnail" v-bind:src="product.image"/>
                <router-link :to="{ name: 'product', params: { id: product.pId } }" >
                    <div class="mask rgba-white-slight"> </div>
                </router-link>
                  
                </div>
                <div class="card-body d-flex flex-column justify-content-between p-3">
                  <h5 class="card-title font-weight-bold fuchsia-rose-text mb-0">   <router-link :to="{ name: 'product', params: { id: product.pId } }" ><a>{{product.pName}}</a></router-link>
              </h5>
                  
          <!--rating-->
           
            <div v-if="product.avgRate==5" class="amber-text fa-sm mb-2">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              
            </div>
          
            <div  v-if="product.avgRate<5 && product.avgRate>=4" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
           
            </div>
          
              <div  v-if="product.avgRate<4 && product.avgRate>=3" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              
            </div>
              
          
             <div  v-if="product.avgRate<3 && product.avgRate>=2" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
             
            </div>
            
             <div v-if="product.avgRate<2 && product.avgRate>=1" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
            </div>
             <div v-if="product.avgRate<1 && product.avgRate>=0" class="amber-text fa-sm mb-1">
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
             
            </div>
                 
                  <p class="chili-pepper-text mb-0">${{product.sellingPrice}}</p>
                  <div v-if="isLoggedIn==0 || isLoggedIn==1">
                
                  <a class="" data-toggle="tooltip" data-placement="top" title="Add to Cart">
                    <button  class="btn btn-1 btn-sm " @click="addToCart(product)">
                    <i  class="fas fa-shopping-cart white-text " aria-hidden="true"></i>
                    </button>
                   </a>
                   </div>
                </div>
              </div>
          
          </div>
        </div>
        
      </div>
       <a @click="loadmore" id="loadMore">Load More</a>
    </div>
        <!--Grid column-->

      </div>
      
      <!--Grid row-->
    </section>
    <!--Section: Content-->

</div>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex'
export default {
  name: 'mainmusic',

  data() {
    return {
      products: [],
      cat:[],
      price:0,
      category:0,
      order:0,
      rate:0,
      filtered:0
    };
  },
  computed:{
     len() {
    
    return this.products.length;
    },
    groupedProducts() {
      return _.chunk(this.products, 4)
    },
    ...mapGetters([
      'cid','isLoggedIn'])
  },
  notifications: {
     
      showCartError: { // You can have any name you want instead of 'showLoginError'
        title: 'Login needed!',
        message: 'You Have to Login to add item to your cart!',
        type: 'error' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
        showSuccessCart:{
           message: 'Item Succesfully added to your cart!',
            type: 'success'

        },
  },
  methods:{
      
      addToCart(product) {
        if(this.isLoggedIn==0){this.showCartError()}
        else{
       axios.post("http://localhost:8090/shoppingCart/add-To-Cart",{cId:this.cid, pId:product.pId,pName:product.pName, price:product.price,image:product.image,quantity:1})
        .then(this.showSuccessCart())
        }
   },
    filteredchange(){
      this.filtered=1;
    },
    filteredchange2(){
      this.filtered=2;
    },
    loadmore() {

       if(this.filtered==0){
         console.log("no")
          axios.post("http://localhost:8090/product/get-by-type/?key="+this.len +"&type=music" )
          .then(response => {
            {if(response.status==200)
             
            for(let i=0 ;i<response.data.length;i++){
              this.products.push(response.data[i]);
            }
  
            }
           
          });}

      else if(this.filtered==2){
        this.filterrest();

      }
      else{console.log("sıkıntıı")}
    },
    filter(){
       
     axios.post("http://localhost:8090/filter/extended-filter?category="+this.category+"&key=0&orderType="+this.order+"&price="+this.price+"&rate="+this.rate+"&type=music")    
     .then(response => {(this.products = response.data)
     console.log(this.products)})
     .catch( console.log(this.products))
     this.filteredchange2()
    },

     filterrest(){
       axios.post(
       //ilk key 0
      "http://localhost:8090/filter/extended-filter?category="+this.category+"&key="+this.len+"+&orderType="+this.order+"&price="+this.price+"&rate="+this.rate+"&type=music")    
     .then(response => {
            {if(response.status==200)
             
            for(let i=0 ;i<response.data.length;i++){
              this.products.push(response.data[i]);
            }
  
            }
           
      })
     .catch(() => console.log(this.products));


    },

  },




 
  mounted(){
    axios.post("http://localhost:8090/product/get-by-type/?key=0&type=music")
    .then(response=> this.products=response.data)
    .catch(() => console.log("Can’t access response. Blocked by browser?"));

    axios.get("http://localhost:8090/category/get-subcategories-by-type=music")
                .then(response=> this.cat = response.data)
    
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


h1 {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  margin-top: 10%;
  padding-left: 10%;
  color: #993c3c;
  position: absolute;
}

.img-thumbnail{

  border:2px;
  width:50%;
  height:auto;
 margin-left:auto;
 margin-right:auto;
  
}
.card{
box-shadow: 0 0 5px rgb(124, 85, 75);

 width:200px;
 height: 40vh;
 margin-top:20px;
 margin-bottom:20px;
 padding:10px;
 text-align: center;
}
.btn {
  flex: 1 1 auto;
  margin: 10px;
  text-align: center;
  text-transform: uppercase;
  transition: 0.5s;
  background-size: 200% auto;
  color: white;
 /* text-shadow: 0px 0px 10px rgba(0,0,0,0.2);*/
  box-shadow: 0 0 20px #eee;
  border-radius: 10px;
  width:30px;
  height:30px;
 }

.btn:hover {
  background-position: right center; /* change the direction of the change here */
}
.btn-5 {
  background-image: linear-gradient(to right, #ffecd2 0%, #fcb69f 51%, #ffecd2 100%);
}
.btn-1 {
  background-image: linear-gradient(to right, #f6d365 0%, #fda085 51%, #f6d365 100%);
}
.font-size-lg { font-size: 2.5rem; }

#Filter {
  width: 100px;
  color: #fff;
  display: block;
  text-align: center;
  margin: 40px auto;
  padding: 10px;
  border-radius: 10px;
  border: 2px solid transparent;
  background-image: linear-gradient(to right, #fdddb0 0%, #fc9b7a 51%, #fdddb0 100%);
  transition: .3s;
}
#Filter:hover {
  color: #ffecd2;
  background-color: #fff;
  border: 1px solid #ffecd2;
  text-decoration: none;
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
