<template>
  <div class="container-fluid text-center" >
 

  <mdb-carousel  class="carousel"
    slide
    :items="basicCarousel"
    indicators
  ></mdb-carousel>
  
  

  <div class="container-fluid text-center">
  <!--Section: Content-->
  <section class="dark-grey-text text-center">
    
    <!-- Section heading -->
    <img  class="images rounded" src="@/assets/newbooks2.png"/>
  
    <!-- Section description -->
   
  	<!-- Carousel Wrapper -->
    <hooper  class="container-fluid text-center" :itemsToShow="5" pagination="fraction" :infiniteScroll="true">
    <slide v-for="(i, indx) in books" :key="indx" :index="indx">

   <div class="carousel-inner " role="listbox">

          <div class="carousel-item active">
            <div class="col ">
              <div class="card mb-2">
                <div class="view overlay">
                  <img  class="img-thumbnail" v-bind:src="i.image"/>
                <router-link :to="{ name: 'product', params: { id: i.pId } }" >
                    <div class="mask rgba-white-slight"> </div>
                </router-link>

             
                  
                </div>
                <div class="card-body d-flex flex-column justify-content-between p-3 ">
                  <h5 class="card-title font-weight-bold fuchsia-rose-text mb-0">   <router-link :to="{ name: 'product', params: { id: i.pId } }" ><a>{{i.pName}}</a></router-link>
                   </h5>
                    <div v-if="i.avgRate==5" class="amber-text fa-sm mb-2">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              
            </div>
          
            <div  v-if="i.avgRate<5 && i.avgRate>=4" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
            </div>
              <div  v-if="i.avgRate<4 && i.avgRate>=3" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
            </div>
             <div  v-if="i.avgRate<3 && i.avgRate>=2" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
             
            </div>
            
             <div v-if="i.avgRate<2 && i.avgRate>=1" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
            </div>
             <div v-if="i.avgRate<1 && i.avgRate>=0" class="amber-text fa-sm mb-1">
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
             
            </div>
                  <p class="chili-pepper-text mb-0">${{i.sellingPrice}}</p>
                    <div  v-if="isLoggedIn==0 || isLoggedIn==1">
                  
                  <a class="" data-toggle="tooltip" data-placement="top" title="Add to Cart">
                    <button  class="align-self-end btn btn-1 btn-sm " @click="addToCart(i)">
                    <i class="fas fa-shopping-cart white-text " aria-hidden="true"></i>
                    </button>
                   </a>
                   </div>
                </div>
              </div>
            </div>
          </div>
        </div>
   
                   
  </slide>
      <hooper-navigation slot="hooper-addons"></hooper-navigation>

 

</hooper>
    <!-- Carousel Wrapper -->
  </section>
  <!--Section: Content-->
</div>

 <div class="container-fluid ">
  <!--Section: Content-->
  <section class="dark-grey-text text-center">
    
    <!-- Section heading -->
     <img class="images rounded " src="@/assets/newmusic2.png"/>
    <!-- Section description -->
   
  	<!-- Carousel Wrapper -->
    <hooper class="container-fluid  text-center" :itemsToShow="5" pagination="fraction" :infiniteScroll="true">
    <slide v-for="(i, indx) in music" :key="indx" :index="indx">
<div class="carousel-inner" role="listbox">

          <div class="carousel-item active">
            <div class="col ">
              <div class="card mb-2">
                <div class="view overlay">
                  <img  class="img-thumbnail" v-bind:src="i.image"/>
                <router-link :to="{ name: 'product', params: { id: i.pId } }" >
                    <div class="mask rgba-white-slight"> </div>
                </router-link>
                  
                </div>
                <div class="card-body d-flex flex-column justify-content-between  p-3">
                  <h5 class="card-title font-weight-bold fuchsia-rose-text mb-0">   <router-link :to="{ name: 'product', params: { id: i.pId } }" ><a>{{i.pName}}</a></router-link>
              </h5>
                    <div v-if="i.avgRate==5" class="amber-text fa-sm mb-2">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              
            </div>
          
            <div  v-if="i.avgRate<5 && i.avgRate>=4" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
            </div>
              <div  v-if="i.avgRate<4 && i.avgRate>=3" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
            </div>
             <div  v-if="i.avgRate<3 && i.avgRate>=2" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
             
            </div>
            
             <div v-if="i.avgRate<2 && i.avgRate>=1" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
            </div>
             <div v-if="i.avgRate<1 && i.avgRate>=0" class="amber-text fa-sm mb-1">
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
             
            </div>
                  
                  <ul class="list-unstyled list-inline my-2">
                    
                  </ul>
                  <p class="chili-pepper-text mb-0">${{i.sellingPrice}}</p>
                   <div  v-if="isLoggedIn==0 || isLoggedIn==1">
                  
                  <a class="" data-toggle="tooltip" data-placement="top" title="Add to Cart">
                    <button  class="btn btn-1 btn-sm " @click="addToCart(i)">
                    <i  class=" fas fa-shopping-cart white-text  " aria-hidden="true"></i>
                    </button>
                   </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
   
                   
  </slide>
      <hooper-navigation slot="hooper-addons"></hooper-navigation>

 

</hooper>
    <!-- Carousel Wrapper -->

  </section>
  <!--Section: Content-->
</div>
<div class="container-fluid ">
  <!--Section: Content-->
  <section class="dark-grey-text text-center">
    
    <!-- Section heading -->
    <img  class="images rounded" src="@/assets/newdvd2.png"/>
    
  	<!-- Carousel Wrapper -->
    <hooper class="container-fluid  text-center" :itemsToShow="5" pagination="fraction" :infiniteScroll="true">
    <slide v-for="(i, indx) in dvd" :key="indx" :index="indx">
<div class="carousel-inner" role="listbox">

          <div class="carousel-item active mx-auto">
            <div class="col">
              <div class="card" >
                <div class="view overlay">
                  <img  style="border:2px" class="img-thumbnail" v-bind:src="i.image"/>
                <router-link :to="{ name: 'product', params: { id: i.pId } }" >
                    <div class="mask rgba-white-slight"> </div>
                </router-link>
                  
                </div>
                <div class="card-body d-flex flex-column justify-content-between p-3">
                  <h5 class="card-title font-weight-bold fuchsia-rose-text mb-0">   <router-link :to="{ name: 'product', params: { id: i.pId } }" ><a>{{i.pName}}</a></router-link>
              </h5>
              
          <!--rating-->
           
            <div v-if="i.avgRate==5" class="amber-text fa-sm mb-2">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              
            </div>
          
            <div  v-if="i.avgRate<5 && i.avgRate>=4" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
            </div>
              <div  v-if="i.avgRate<4 && i.avgRate>=3" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
            </div>
             <div  v-if="i.avgRate<3 && i.avgRate>=2" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
             
            </div>
            
             <div v-if="i.avgRate<2 && i.avgRate>=1" class="amber-text fa-sm mb-1">
              <i class="fas fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
            </div>
             <div v-if="i.avgRate<1 && i.avgRate>=0" class="amber-text fa-sm mb-1">
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
              <i class="far fa-star"></i>
             
            
            </div>
                  
                  <ul class="list-unstyled list-inline my-2">
                   
                  </ul>
                  <p class="chili-pepper-text mb-0">${{i.sellingPrice}}</p>
                  <div  v-if="isLoggedIn==0 || isLoggedIn==1">
                 
                  <a class="" data-toggle="tooltip" data-placement="top" title="Add to Cart">
                    <button  class=" btn btn-1 btn-sm " @click="addToCart(i)">
                    <i class="fas fa-shopping-cart white-text" aria-hidden="true"></i>
                    </button>
                   </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
   
                   
  </slide>
      <hooper-navigation slot="hooper-addons"></hooper-navigation>

 

</hooper>
    <!-- Carousel Wrapper -->

  </section>
  <!--Section: Content-->
</div>
</div>
</template>

<script>
//import { Carousel, Slide } from 'vue-carousel';
import { mdbCarousel } from "mdbvue";
import { mdbRating } from 'mdbvue';

import { mapGetters } from 'vuex'
import { Hooper, Slide,  Navigation as HooperNavigation
 } from 'hooper';
import 'hooper/dist/hooper.css';

import axios from 'axios';
export default {
  name: 'Homepage',
  data() {
    return { books: [],music:[],dvd:[] ,
        carousel: 2,  
        basicCarousel: [
          {
            img: true,
            src:
           require("../assets/bestsellers10.jpeg"),
            
            alt: "First Slide",
            
          },
          {
            img: true,
            src:
              require("../assets/slide2.png"),
           
            
          },
          {
            img: true  ,
           
            src:
               require("../assets/slide3.png"),
           
           
          }
        ]
 };
  },
  computed: {
    ...mapGetters([
      'cid','isLoggedIn']),
    
  },
  methods:{

      addToCart(product) {
        if(this.isLoggedIn==0){this.showCartError()}
        else{
     
        axios.post("http://localhost:8090/shoppingCart/add-To-Cart",{cId:this.cid, pId:product.pId,pName:product.pName, price:product.price,image:product.image,quantity:1}
      )
      .catch(err=> console.log(this.cid))
      this.showSuccessCart()
      }

   }
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
 
  components: {
    //Carousel,
   // Slide,
    Hooper,
    Slide,
     mdbCarousel,
      HooperNavigation,
      mdbRating
  },
 

  mounted(){
    axios.get("http://localhost:8090/filter/bestseller-book")
    .then(response=> this.books=response.data)
    axios.get("http://localhost:8090/filter/bestseller-music")
    .then(response=> this.music=response.data)
    axios.get("http://localhost:8090/filter/bestseller-dvd")
    .then(response=> this.dvd=response.data)
    
  },
  
  
 

};
</script>


<style scoped>
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
container {
  display: grid;
  grid-template-rows: auto;
  grid-template-columns: repeat(2, 70%);
}
h1 {
  padding-top: 20px;
}

.view:hover {
    cursor: pointer;
}
.hooper{

box-shadow: 0 0 20px rgb(124, 85, 75);
margin:10px;
max-width:1600;
height: 45vh;
border-radius: 15px;
text-align:center;

}
.carousel{

  box-shadow: 0 0 20px rgb(124, 85, 75);
  border-radius: 15px;
}


.images{

width:100%;
margin-top:2%;
margin-bottom: 2%;
box-shadow: 0 0 10px rgb(124, 85, 75);


}

.img-thumbnail{

  border:2px;
  width:50%;
  height:auto;
 margin-left:auto;
 margin-right:auto;
  
}
.card{
box-shadow: 0 0 10px rgb(124, 85, 75);

 width:200px;
 height: 40vh;
 margin-top:20px;
 margin-bottom:20px;
 margin-right: 10px;
 padding:10px;
 text-align: center;
}
</style>
