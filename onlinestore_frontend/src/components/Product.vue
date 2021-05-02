<template>

  <div class="container my-4 py-5 z-depth-3">

  <!--Section: Content-->
  <section class="text-center">


    <div class="row">

      <div class="col-md-5 ml-5 ">

        <!--Carousel Wrapper-->
        <div id="carousel-thumb" class="carousel slide carousel-fade carousel-thumbnails" data-ride="carousel">
          <!--Slides-->
          <div class="carousel-inner text-center text-md-left" role="listbox">
            <div class="carousel-item active">
              <img  style=" width: 90%;box-shadow: 0 0 10px rgb(124, 85, 75);
  height:550px;" v-bind:src="product.image"
                alt="First slide" class="img-fluid">
            </div>
            
          </div>
          <!--/.Slides-->

        </div>
        <!--/.Carousel Wrapper-->

      </div>

      <div class="col-lg-5 text-center text-md-left">

        <h2 class="h2-responsive text-center text-md-left product-name font-weight-bold dark-grey-text mb-5 ml-xl-0 ml-4">
          <strong>{{ product.pName }}</strong>
        </h2>
        
        <h3 class="h3-responsive text-center text-md-left mb-5 ml-xl-0 ml-4">
          <span class="red-text font-weight-bold">
            <strong>Price: ${{ product.sellingPrice }}</strong>
          </span>
          <span class="grey-text">
            <small>
              <s> ${{ product.price }}</s>
            </small>
          </span>
        </h3>

        <!--Accordion wrapper-->
        <div class="accordion md-accordion" id="accordionEx" role="tablist" aria-multiselectable="true">

          <!-- Accordion card -->
          <div class="card">

            <!-- Card header -->
            <div class="card-header" role="tab" id="headingOne1">
              <a data-toggle="collapse"  aria-expanded="true"
                aria-controls="collapseOne1">
                <h5 class="mb-0">
                  Description
                  
                </h5>
              </a>
            </div>

            <!-- Card body -->
            <div id="collapseOne1" class="collapse show" role="tabpanel" aria-labelledby="headingOne1"
              data-parent="#accordionEx">
              <div class="card-body">
                {{product.description}}
              </div>
            </div>
          </div>
         
        </div>
        <!--/.Accordion wrapper-->

        <!-- Add to Cart -->
        <section style="padding-top:30px" class="color" v-if="this.isLoggedIn==0 || this.isLoggedIn==1">
          <div class="row">
            <div style="padding-top:3px" class="col-2">
            <p class="grey-text">Quantity: </p>
             </div >
             <div style="padding-top:3px" class="col">
            <input style="box-shadow: 0 0 0.05px rgb(124, 85, 75);" type="number"  v-model="value" value="1" min="0" ref="quantityinput" max="100" step="1" />
            </div >
            <div  style="margin-top:-10 px" class="row text-center text-md-left">
              <div  class="col text-center text-md-left">
                <button class="btn btn-sm btn-1 btn-rounded" @click="addToCart()">
                  <i class="fas fa-cart-plus mr-2" aria-hidden="true"></i> Add to cart</button>
              </div>
            </div> 
          </div>
        </section>
        <!-- /.Add to Cart -->

      </div>

    </div>
    <div class="row" style="margin-top:80px;">
		<div class="col-lg">
    	<div class="well well-sm"  v-if="this.isLoggedIn==0 || this.isLoggedIn==1">
            <div class="row" id="post-review-box">
                <div class="col-md-12"> 
                   
                      <textarea style="box-shadow: 0 0 5px rgb(124, 85, 75);" v-model="comment" class="form-control animated" cols="50" id="new-review" placeholder="Enter your review here..." rows="5"></textarea>
                      <div class="row">
                        <div style="padding-top:20px;margin-left:190px" class="col-8 ">
                          <span class>Your Rating:</span>
                             
                            <div>
                            <!-- Default inline 1-->
                            <div class="custom-control custom-radio custom-control-inline">
                              <input v-model="rate" value="1" type="radio" name="radioInline" class="custom-control-input" id="defaultInline1">
                              <label class="custom-control-label" for="defaultInline1">1</label>
                            </div>

                            <!-- Default inline 2-->
                            <div class="custom-control custom-radio custom-control-inline">
                              <input v-model="rate" value="2" type="radio" name="radioInline" class="custom-control-input" id="defaultInline2">
                              <label class="custom-control-label" for="defaultInline2">2</label>
                            </div>

                            <!-- Default inline 3-->
                            <div class="custom-control custom-radio custom-control-inline">
                              <input v-model="rate" value="3" type="radio" name="radioInline" class="custom-control-input" id="defaultInline3">
                              <label class="custom-control-label" for="defaultInline3">3</label>
                            </div>
                             <!-- Default inline 3-->
                            <div class="custom-control custom-radio custom-control-inline">
                              <input v-model="rate" value="4" type="radio" name="radioInline" class="custom-control-input" id="defaultInline4">
                              <label class="custom-control-label" for="defaultInline4">4</label>
                            </div>
                             <!-- Default inline 3-->
                            <div class="custom-control custom-radio custom-control-inline">
                              <input v-model="rate" value="5" type="radio" name="radioInline" class="custom-control-input" id="defaultInline5">
                              <label class="custom-control-label" for="defaultInline5">5</label>
                            </div>
                          </div>
                         
                        </div>
                       
                        <div style="margin-left:190px;padding-bottom:30px;" class="col-8 ">
                        <button style="margin-top:20px" class="btn btn-5 btn-lg" @click="addReview()" type="submit">Send</button>
                        </div>

                     
                    </div>
              </div>    
            </div>
        </div> 
         
		</div>
	</div>
    <div  class="row">
			<div class="col-lg">
				<div  class="rating-block">
					<h4>Average user rating</h4>
          
					<h2 class="bold padding-bottom-7">{{this.avg}} <small>/ 5</small></h2>
        
				</div>
			</div>
				
		</div>
    <div class="container my-5">


  <!--Section: Block Content-->
  <section class="dark-grey-text mb-5">

    <!-- Section heading -->
    <h3 class="font-weight-bold text-center mb-5">Product Reviews</h3>
  

    <div class="col ml-5">

         <div v-for="r in reviews" :key="r.cId"  >
           <div class="row mb-5">
          <img class=" card-img-100 rounded-circle z-depth-1-half d-flex mr-3" src="https://m2bob-forum.net/wcf/images/avatars/3e/2720-3e546be0b0701e0cb670fa2f4fcb053d4f7e1ba5.jpg" alt="Generic placeholder image">
            <div class="col-8">
              <a>
              <h5 class="user-name font-weight-bold">{{r.cName}}</h5>
            </a>
        <!-- Rating -->
            <h5 class=" font-weight">Rated: {{r.rate}}/5</h5>
            <div class="card-data">
              <ul class="list-unstyled mb-1">
                <li class="comment-date font-small grey-text">
                  <i class="far fa-clock"></i> {{r.rDate}}</li>
              </ul>
            </div>
            <p class="dark-grey-text article">{{r.comment}}</p>
            </div>
          </div>
          </div>
          
    </div>

	</section>
  <!--Section: Block Content-->

        </div>
  </section>
  <!--Section: Content-->


</div>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex'
import { mdbRating } from 'mdbvue';


export default {  
  name: 'product',
 
  data() {
    return {
     
      product: [],
      reviews:[],
      value:"",
      avg:"",
      comment:"",
      rate:"",
     
      
    };
  },
  
  components:{ mdbRating},
  computed:{
    ...mapGetters([
      'cid','isLoggedIn']),
      
  },
  notifications: {
      showLoginError: { // You can have any name you want instead of 'showLoginError'
        title: 'Login needed!',
        message:'You Have to Login to make a review',
        type: 'error' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
      showCartError: { // You can have any name you want instead of 'showLoginError'
        title: 'Login needed!',
        message: 'You Have to Login to add item to your cart!',
        type: 'error' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
        showSuccessCart:{
           message: 'Item Succesfully added to your cart!',
            type: 'success'

        },
        showSuccessReview:{
           message: 'Your review is succesfully submitted!',
            type: 'success'
        },
        showErrorReview:{
           message: 'You have already left a review for this product!',
            type: 'error'
        },
        empty:{
            message: 'Empty fields!',
            type: 'warn'

        }
  },

  methods: {

    
    
    addToCart() {
      if(this.isLoggedIn==0){this.showCartError()}
      else{
      axios.post("http://localhost:8090/shoppingCart/add-To-Cart",{cId:this.cid, pId:this.product.pId,pName:this.product.pName, price:this.product.price,image:this.product.image,quantity:this.value})
      this.showSuccessCart()
      }
    },

    addReview(){

      if(this.isLoggedIn==0)
      {this.showLoginError()}
      else{
        if(this.rate=="" || this.comment =="")
        {
          this.empty()
     
      }
      else{
        axios.post("http://localhost:8090/review/add-review",
          { cId: this.cid, pId: this.product.pId, cName:null, rate: this.rate,comment: this.comment,rDate:null})
          .then(response => 
            {if (response.status == 200)
              { 
                this.showSuccessReview()}
          })
          .catch(() => console.log(this.showErrorReview()));

        
      }
      
      }
    },
    
  },

  mounted() {
    axios
      .get('http://localhost:8090/product/get-by-id/?id=' + this.$route.params.id)
      .then(response => (this.product = response.data))
      .catch(() => console.log('product.vue'));

     axios
      .get('http://localhost:8090/review/get-reviews-by-pId=' + this.$route.params.id)
      .then(response => (this.reviews = response.data))
      .catch(() => console.log('product.vue'));

     axios
      .get(' http://localhost:8090/review/get-avg-rate-by-pId=' + this.$route.params.id)
      .then( response => { 
        this.avg = response.data  
        console.log(this.avg)
      })
      .catch(() => { { this.avg=0;}})
     
  }
};
</script>

<style scoped>


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
  box-shadow: 0 0 20px #eee;
  border-radius: 10px;
 }

.btn:hover {
  background-position: right center; /* change the direction of the change here */
}
.btn-5 {
  background-image: linear-gradient(to right, #CDD050 0%, #47CD4F 51%,#CDD050 100%);
}
.btn-1 {
  background-image: linear-gradient(to right, #B8CCF8 0%, #1DD5D0 51%, #B8CCF8 100%);
}


container {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 50px;
  display: grid;
  grid-template-rows: auto;
  grid-template-columns: repeat(1, 100%);
}
body {
  padding-top: 70px;
}
.btn-grey{
    background-color:#D8D8D8;
	color:#FFF;
}
.rating-block{
	background-color:#FAFAFA;
	border:1px solid #EFEFEF;
	padding:15px 15px 20px 15px;
	border-radius:3px;
}
.bold{
	font-weight:700;
}

.review-block-name{
	font-size:12px;
	margin:10px 0;
}
.review-block-date{
	font-size:12px;
}
.review-block-rate{
	font-size:13px;
	margin-bottom:15px;
}
.review-block-title{
	font-size:15px; 
	font-weight:700;
	margin-bottom:10px;
}
.review-block-description{
	font-size:13px;
}
 .animated {
    -webkit-transition: height 0.2s;
    -moz-transition: height 0.2s;
    transition: height 0.2s;
}

 
</style>
