<template>
 <div class="container-fluid" align="center">
     <ul class="dropdown-cart" role="menu"> 
         
              <section style=" display: inline-block;box-shadow: 0 0 2px #C88877;" v-for="item in cart" :key="item.pId" class="d-flex flex-row mb-2"> 
                     <div class="col-flex mr-4">
                        <router-link :to="{ name: 'product', params: { id: item.pId } }"  >
                        <img v-bind:src="item.image" class="thumbnail" alt="" />
                         </router-link>
                     </div>
                     <div class="flex-col-6 ">
                            <div class="row">
                               
                                    <router-link :to="{ name: 'product', params: { id: item.pId } }"  >
                                    <div >{{ item.pName.substring(0,20)+".." }}</div>
                                     </router-link>
                            </div>
                                    <div class="row">${{ item.price }}</div>
                                    <div class="row">Quantity: {{ item.quantity }}</div>
                            
                    </div>
                
                    <div class="col">
                        <b-button @click="removeFromCart(item.pId)" class="btn1 btn-5"></b-button>
                    </div>
               
              
        </section>
     </ul>
     <b-button v-if="this.len!=0"  to="/checkout/" size="lg" class="btn btn-3 my-2 my-sm-0">Checkout</b-button>
     <h5 style="margin-left:100px;" v-else>Your cart is empty!</h5>
 </div>

      
  

</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex'
export default {
    name: 'shoppingcart',

    data() {
        return {
        cart: []
        }
    }
    ,
    computed:{
        ...mapGetters([
      'cid',
    ]),
     len: function(){ return this.cart.length}  
    },

    methods: {

    removeFromCart(pid) {
      axios.delete("http://localhost:8090/shoppingCart/delete-By-Id?cId="+this.cid+"&pId="+pid)
       .catch(() => console.log());
    }
  },

 

    mounted () {
    axios
      .get('http://localhost:8090/shoppingCart/get-products-By-Id?id='+this.cid)
      .then(response => (this.cart = response.data))
      .catch(() => console.log(this.cid));
  },
    updated(){
    axios
      .get('http://localhost:8090/shoppingCart/get-products-By-Id?id='+this.cid)
      .then(response => (this.cart = response.data))
      .catch(() => console.log('cart.vue'));
  }

    
}
</script>

<style  scoped>

cart-list {
  width: 70%;
  @media only screen and (max-width: 832px) {
    width: 100%;
  }
}

.thumbnail {
  max-width: 50px;
  margin-top:3px;
  box-shadow: 0 0 2px #C88877
}


ul.dropdown-cart{
    min-width:400px;
    
     margin-bottom:20px;
}

ul.dropdown-cart section:hover{
    background-color:#f3f3f3;
}

ul.dropdown-cart section{
 margin-right:30px;

}
ul.dropdown-cart section:after{
    visibility: hidden;
    font-size: 0;
    content: " ";
    clear: both;
   
}

   

.btn {
  flex: 1 1 auto;
  text-align: center;
  text-transform: uppercase;
  transition: 0.5s;
  background-size: 200% auto;
  color: white;
 /* text-shadow: 0px 0px 10px rgba(0,0,0,0.2);*/
  box-shadow: 0 0 20px #C88877;
  border-radius: 10px;
 }

 .btn1 {
  flex: 1 1 auto;
  text-align: center;
margin-top: 30px;
  padding: 10px;
  text-transform: uppercase;
  transition: 0.5s;
  background-size: 200% auto;
  color: white;
 /* text-shadow: 0px 0px 10px rgba(0,0,0,0.2);*/
  box-shadow: 0 0 20px #C88877;
  border-radius: 10px;
 }

.btn:hover {
  background-position: right center; /* change the direction of the change here */
}
.btn-5 {
  background-image: linear-gradient(to right, #da482f 0%, #d60d06e0 51%, #da482f 100%);
  width:1px;
  height:1px;
}
.btn-3 {
  background-image: linear-gradient(to right, #CDD050 0%, #47CD4F 51%,#CDD050 100%);
}

</style>