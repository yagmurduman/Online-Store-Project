<template>

<div class="container">
  <mdb-navbar position="top"  dark>
    <mdb-navbar-brand v-if="this.isLoggedIn!=4" to="/">
     <img style= "width : 50px;height : 50px;float: left;margin-left: 10px;" src="@/assets/logo_transparent.png"/>
    </mdb-navbar-brand>
    <mdb-navbar-toggler>
      <mdb-navbar-nav  >
       
        <mdb-dropdown >
        <mdb-dropdown-toggle v-if="this.isLoggedIn!=4" class=" mr-3" size="md" slot="toggle">Books</mdb-dropdown-toggle>
            <mdb-dropdown-menu>
              <mdb-dropdown-item to="/maincategorybooks">All Books</mdb-dropdown-item>
              <div v-for = "i in catbook" :key="i.catId">
              <mdb-dropdown-item :to="{ name: 'category', params: { id: i.catId ,name:i.catName, type:i.type} }"> {{i.catName}} 
              </mdb-dropdown-item>
              </div>
           </mdb-dropdown-menu>
       </mdb-dropdown>
        <mdb-dropdown>
        <mdb-dropdown-toggle v-if="this.isLoggedIn!=4" class=" mr-3" size="md" slot="toggle">Music</mdb-dropdown-toggle>
            <mdb-dropdown-menu>
              <mdb-dropdown-item to="/maincategorymusic">All Music</mdb-dropdown-item>
              <div v-for = "i in catmusic" :key="i.catId">
              <mdb-dropdown-item :to="{ name: 'category', params: { id: i.catId ,name:i.catName , type:i.type} }"> {{i.catName}} 
              </mdb-dropdown-item>
              </div>
           </mdb-dropdown-menu>
       </mdb-dropdown>
        <mdb-dropdown>
        <mdb-dropdown-toggle v-if="this.isLoggedIn!=4" class=" mr-3" size="md" slot="toggle">Dvd</mdb-dropdown-toggle>
            <mdb-dropdown-menu>
              <mdb-dropdown-item to="/maincategorydvd">All DVD</mdb-dropdown-item>
              <div v-for = "i in catdvd" :key="i.catId">
              <mdb-dropdown-item :to="{ name: 'category', params: { id: i.catId ,name:i.catName, type:i.type} }"> {{i.catName}} 
              </mdb-dropdown-item>
              </div>
           </mdb-dropdown-menu>
       </mdb-dropdown>
        </mdb-navbar-nav>

  
        <mdb-navbar-nav right>
          
          <mdb-form-inline >
            <div v-if="this.isLoggedIn!=4" class="md-form ml-5 input-group">
              <input v-model="text" class="form-control" placeholder="Search" aria-label="Search"
                aria-describedby="MaterialButton-addon2">
              <div class="input-group-append">
                <b-button class="btn btn-md btn-1 m-0 px-3" type="submit" to="/SearchResults/" @click="Search()"  id="MaterialButton-addon2">Search</b-button>
              </div>
            </div>
               
          <mdb-dropdown >
            <mdb-dropdown-toggle  class="ml-2" size="md" slot="toggle"> <i class="fas fa-user-circle"></i></mdb-dropdown-toggle>
            <mdb-dropdown-menu>
            <mdb-dropdown-item v-if="this.isLoggedIn==1" to="/profile">Profile</mdb-dropdown-item>
            <mdb-dropdown-item v-if="this.isLoggedIn==2" to="/sm">Sales Manager</mdb-dropdown-item>
             <mdb-dropdown-item v-if="this.isLoggedIn==3" to="/pm">Product Manager</mdb-dropdown-item>
            <mdb-dropdown-item  @click="logout()">Logout</mdb-dropdown-item>
            
            </mdb-dropdown-menu>
          </mdb-dropdown> 

          <mdb-dropdown v-if="isLoggedIn==0 || isLoggedIn==1">
              <mdb-dropdown-toggle class="ml-2" size="md" slot="toggle">
              <i class="fas fa-shopping-cart"></i>
            </mdb-dropdown-toggle>
                <mdb-dropdown-menu>
                  <li> <shoppingcart/> </li>
                </mdb-dropdown-menu>
          </mdb-dropdown>
      </mdb-form-inline>
       </mdb-navbar-nav>
    
    
    </mdb-navbar-toggler>
  </mdb-navbar>


  
  
</div>
</template>

<script>
import { mapGetters } from 'vuex'
import router from '../router/index.js'
import shoppingcart from './ShoppingCart.vue'
import axios from 'axios';
import { mdbNavbar,mdbDropdown,
mdbNavbarToggler,
mdbDropdownToggle,
mdbDropdownMenu,
mdbNavbarNav,
mdbNavbarNavItem,
mdbDropdownItem,
mdbButton,
mdbFormInline,
mdbNavbarBrand} from 'mdbvue';
export default {
    name: 'Header',

    computed:{
    ...mapGetters([
      'cid','isLoggedIn']),
      
  },
    components: shoppingcart,
    data (){
        return {
                modal:false,
                isPanelOpen: true,
                text:'',
                catbook:[],
                catmusic:[],
                catdvd:[]
              }
    },
    mounted() {

        axios.get("http://localhost:8090/category/get-subcategories-by-type=book")
                .then(response=> this.catbook = response.data)
        axios.get("http://localhost:8090/category/get-subcategories-by-type=music")
                .then(response=> this.catmusic = response.data)
        axios.get("http://localhost:8090/category/get-subcategories-by-type=dvd")
                .then(response=> this.catdvd = response.data)

    },
     notifications: {
     
      direct: { // You can have any name you want instead of 'showLoginError'
        title: 'Logging out!',
        message: 'You are being directed to homepage!',
        type: 'success' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
      
       
    },
    methods :{

        logout(){
         
         this.direct()
         this.$store.dispatch("logout")
         .then(() => 
                  this.$router.push("/")
               )
        .catch(err => console.log(err));
        },
         
         Search() {
              
                if(this.text!=""){
                axios.post("http://localhost:8090/search/?search="+ this.text)
                .then(response=> 
                {
                 
                 this.$store.state.searchProducts = response.data.value
                 this.$store.state.searchkey=response.data.key
                 
                 //setter eklenmesi gerekebilir
                  
                }
                )
                .catch(console.log("error"))
                }
    
            }
    },
    components:{mdbNavbar,mdbDropdown,
    mdbNavbarNav,
    mdbFormInline,
mdbDropdownToggle,
mdbDropdownMenu,
mdbNavbarNavItem,
mdbDropdownItem,
mdbNavbarBrand,
mdbButton,
mdbNavbarToggler}


};
</script>

<style scoped>

.navbar{
    /*background-image:url('../assets/header7.png');*/
  background-image: linear-gradient(to right, #F3BF95 0%, #EDA18D 51%, #F3BF95 100%);

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
  box-shadow: 0 0 20px #C88877;
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
.navbar .navbar-nav{
    margin : 10 auto;
    font-size: 1.4em;
    font-family: droid-sans, sans-serif !important;
    font-style: normal;
    font-weight: 400;
    
       

    
}



    
.container{

        text-align: center;
        margin-right:10%;
        margin-bottom: 10%;
        

    }
  
.navbar-nav li:hover > ul.dropdown-menu {
    display: block;
}
.dropdown-submenu {
    position:absolute;
}
.dropdown-submenu>.dropdown-menu {
    top: 0;
    left: 100%;
    margin-top:-6px;
}

/* rotate caret on hover */
.dropdown-menu > li > a:hover:after {
    text-decoration: underline;
    transform: rotate(-90deg);
} 


    
.container{

        text-align: center;
        margin-right:10%;
        margin-bottom: 10%;
       

    }
    
  
    
</style>