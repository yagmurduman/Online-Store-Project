<template>
    <div class="container my-5">

  <!-- Grid row -->
  <div v-if="this.len!=0" class="row">

    <!-- Grid column -->
    <div class="col-md-12">

      <div class="card shopping-cart">
        <div class="card-body p-4">

          <!-- Grid row -->
          <div class="row">

            <!-- Grid column -->
            <div class="col-lg-6" >

              <h3 class="mb-4 py-2 text-center font-weight-bold text-uppercase">Your products</h3>

              <div class="media mb-3 border" v-for="product in products" :key="product.pId">
                 <router-link :to="{ name: 'product', params: { id: product.pId } }" ><img class="d-flex mr-3"  v-bind:src="product.image" alt="Generic placeholder image">
                 </router-link>
                <div class="media-body">
                  <a class="float-right">
                       <button @click="removeFromCart(product.pId)">
                       <i class="fas fa-times"></i></button></a>
                   <router-link :to="{ name: 'product', params: { id: product.pId } }" ><h5 class="mt-0 font-weight-bold text-primary">{{product.pName}}</h5>
                   </router-link>
                  <div class="d-flex justify-content-between">
                    <p class="font-weight-bold">{{product.price}}$</p>
                    <div class="def-number-input number-input safari_only mt-n05">
                      <p class="font-weight-bold">Quantity: {{product.quantity}}</p>
                      <!--
                      <button onclick="this.parentNode.querySelector('input[type=number]').stepDown()" class="minus"></button>
                      <input class="quantity font-weight-bold black-text"  min="0" name="quantity" value="1" type="number">
                      <button onclick="this.parentNode.querySelector('input[type=number]').stepUp()" class="plus"></button>
                      -->
                    </div>
                  </div>
                </div>
              </div>
           
            
              <hr class="blue-border mb-4">
             
              <div class="d-flex justify-content-between light-blue lighten-5 p-2">
                <h5 class="font-weight-bold mb-0">Total:</h5>
                <h5 class="font-weight-bold mb-0">{{this.total}}$</h5>
              </div>

            </div>
            <!-- Grid column -->

            <!-- Grid column -->
            <div class="col-lg-6">

              <h3 class="mb-4 py-2 mt-lg-0 mt-4 text-center font-weight-bold text-uppercase">Payment</h3>

              <form action="">
                <!-- Medium input -->
                <div class="md-form mb-5">
                  <input type="tel" id="inputMDEx4" class="form-control">
                  <label for="inputMDEx4">Card number</label>
                </div>
                <!-- Medium input -->
                <div class="md-form">
                  <input type="text" id="inputMDEx3" class="form-control" >
                  <label for="inputMDEx3">Name on card</label>
                </div>

                <!-- Grid row -->
                <div class="row">

                  <!-- Grid column -->
                  <div class="col-md-6 mb-4">

                    <!-- Medium input -->
                    <div class="md-form">
                      <input type="text" id="inputMDEx2" class="form-control" >
                      <label for="inputMDEx2">Valid thru</label>
                    </div>

                  </div>
                  <!-- Grid column -->

                  <!-- Grid column -->
                  <div class="col-md-6 mb-4">

                    <!-- Medium input -->
                    <div class="md-form">
                      <input type="password" id="inputMDEx1" class="form-control" >
                      <label for="inputMDEx1">CCV number</label>
                    </div>

                  </div>
                  <!-- Grid column -->

                </div>
                <!-- Grid row -->
                <b-input-group>
                <b-form-input v-model="code" placeholder="Discount code"></b-form-input>
                <button class="btn btn-primary btn-sm " @click="apply()">Apply</button>
                </b-input-group>
                <button  type="button" class="btn btn-success btn-block mb-lg-0 mt-4" @click="buy()">Buy now</button>
               
                
                
                <div>
                
              </div>
              </form>

            </div>
            <!-- Grid column -->

          </div>
          <!-- Grid row -->

        </div>
      </div>
      

    </div>
    <!-- Grid column -->

  </div>
  <div v-else><h1>Your Shopping Cart is empty!</h1></div>
  
  <!-- Grid row -->

</div>
    
</template>

<script>
import { mapGetters } from 'vuex'
 import axios from 'axios';
export default {
    name: "checkout",
    data() {
    return {
      products: [],
      modal: true,
      discount:'',
      total:'',
      quantity:'',
      code:"",
      
    };
  },
   computed:{
        ...mapGetters([
      'cid',]),
      len: function(){ return this.products.length}
       },

    notifications: {
      showSuccess: { // You can have any name you want instead of 'showLoginError'
        title: 'Your order is recieved',
        message: 'You will shortly recieve an email',
        type: 'success' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
      successdiscount:{
        title: 'Discount is successfully applied!',
        type: 'success'
      },
      invaliddiscount:{
        title: 'Discount is invalid!',
        type: 'error'
      }

    
    },
   
    methods: {
    
    buy(){ //onclickte cid yi değiştir
    console.log(this.cid)
      axios.post("http://localhost:8090/delivery/cartCheckout="+this.cid)
      .then(response => {
      if( this.len != 0)
      { 
           this.showSuccess();
           this.$router.push({ path: '/' }).catch(err => {"checkout"})
      }
      else
      {
           this.alert("Your cart is empty!")
      }
      })
      },
    
    apply(){  
       axios
      .get("http://localhost:8090/shoppingCart/checkCode/code="+this.code+"&cId="+this.cid)
      .then(response =>  {
        if(response.status==200){
        this.successdiscount()}
        else{
          this.invaliddiscount()
        }
      })

    },

    removeFromCart(pid) {
      axios.delete("http://localhost:8090/shoppingCart/delete-By-Id?cId="+this.cid+"&pId="+pid)
       .catch(() => console.log(pid));
    }
    },

   mounted () {
    axios
      .get('http://localhost:8090/shoppingCart/get-products-By-Id?id='+this.cid)
      .then(response => (this.products = response.data))
      .catch(() => console.log('checkout1.vue'));


   
       axios
      .get('http://localhost:8090/shoppingCart/totalPrice/'+this.cid)
      .then(response => (this.total = response.data))
      .catch(() => console.log('checkout3.vue'));
  },

  updated(){
    axios
      .get('http://localhost:8090/shoppingCart/get-products-By-Id?id='+this.cid)
      .then(response => (this.products = response.data))
      .catch(() => console.log('cart4.vue'));

      

       axios
      .get('http://localhost:8090/shoppingCart/totalPrice/'+this.cid)
      .then(response => (this.total = response.data))
      .catch(() => console.log('checkout6.vue'));

     
  }

    
}
</script>
<style scoped>
 img {
      width: 150px;
    }
    .number-input input[type="number"] {
  -webkit-appearance: textfield;
  -moz-appearance: textfield;
  appearance: textfield;
}

.number-input input[type=number]::-webkit-inner-spin-button,
.number-input input[type=number]::-webkit-outer-spin-button {
  -webkit-appearance: none;
}

.number-input {
  margin-bottom: 2rem;
}

.number-input button {
  -webkit-appearance: none;
  background-color: transparent;
  border: none;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  margin: 0;
  position: relative;
}

.number-input button:before,
.number-input button:after {
  display: inline-block;
  position: absolute;
  content: '';
  height: 2px;
  transform: translate(-50%, -50%);
}

.number-input button.plus:after {
  transform: translate(-50%, -50%) rotate(90deg);
}

.number-input input[type=number] {
  text-align: center;
}

.number-input.number-input {
  border: 1px solid #ced4da;
  width: 10rem;
  border-radius: .25rem;
}

.number-input.number-input button {
  width: 2.6rem;
  height: .7rem;
}

.number-input.number-input button.minus {
  padding-left: 10px;
}

.number-input.number-input button:before,
.number-input.number-input button:after {
  width: .7rem;
  background-color: #495057;
}

.number-input.number-input input[type=number] {
  max-width: 4rem;
  padding: .5rem;
  border: 1px solid #ced4da;
  border-width: 0 1px;
  font-size: 1rem;
  height: 2rem;
  color: #495057;
}

@media not all and (min-resolution:.001dpcm) {
  @supports (-webkit-appearance: none) and (stroke-color:transparent) {

    .number-input.def-number-input.safari_only button:before,
    .number-input.def-number-input.safari_only button:after {
      margin-top: -.3rem;
    }
  }
}
.blue-border {
  border-top: 2px solid #4285f4;
}
.shopping-cart .def-number-input.number-input {
  border: none; }
  .shopping-cart .def-number-input.number-input input[type=number] {
    max-width: 2rem;
    border: none; }
    .shopping-cart .def-number-input.number-input input[type=number].black-text, .shopping-cart .def-number-input.number-input input.btn.btn-link[type=number], .shopping-cart .def-number-input.number-input input.md-toast-close-button[type=number]:hover,
    .shopping-cart .def-number-input.number-input input.md-toast-close-button[type=number]:focus {
      color: #212529 !important; }
  .shopping-cart .def-number-input.number-input button {
    width: 1rem; }
    .shopping-cart .def-number-input.number-input button:before, .shopping-cart .def-number-input.number-input button:after {
      width: .5rem; }
    .shopping-cart .def-number-input.number-input button.minus:before, .shopping-cart .def-number-input.number-input button.minus:after {
      background-color: #9e9e9e; }
    .shopping-cart .def-number-input.number-input button.plus:before, .shopping-cart .def-number-input.number-input button.plus:after {
      background-color: #4285f4; }
  .shopping-cart .def-number-input.number-input.mt-n05 {
    margin-top: -.33rem; }

.shopping-cart .back-link {
  position: absolute;
  bottom: 0; }

</style>