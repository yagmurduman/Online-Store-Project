<template>
<div class="login" :style="{backgroundImage:'url(' + require('@/assets/back.png') + ')'}">
    <div class="spark-screen container">
         <div class="row justify-content-center">
    <!-- Default form login -->
<form class="text-center border border-light p-5" action="#!">

    <h3><p class="h4 mb-4"><b>Login To Your Account</b></p></h3>

    <!-- Email -->
    <input type="email" v-model="LoginUsername" class="form-control mb-4" placeholder="Username" required>

    <!-- Password -->
    <input type="password" v-model="LoginPassword" class="form-control mb-4" placeholder="Password" required>

    

    <!-- Sign in button -->
    <div ><b-button variant="outline-danger" v-on:click="OnLoginClick()" class=" btn-block btn-5 my-1" >Sign in</b-button></div>

    <!-- Register -->
    <p>Not a member?
        <a href="#/Signup">Sign Up</a>
    </p>


</form>
<!-- Default form login -->


</div>
</div>

</div>
</template>

<script>
import axios from 'axios';
import { mapMutations } from 'vuex'
import { mapGetters } from 'vuex'
export default {
    name:'login',
    data () {
       return {
            LoginUsername:'',
            LoginPassword:''
       }
    },
    computed:{
         ...mapMutations(['login']),
         ...mapGetters(['cid','type',])
    },  
     notifications: {
      showLoginError: { // You can have any name you want instead of 'showLoginError'
        title: 'Login Failed',
        message: 'Failed to authenticate',
        type: 'error' // You also can use 'VueNotifications.types.error' instead of 'error'
        },

        showLoginSuccess: { // You can have any name you want instead of 'showLoginError'
        title: 'Login Successful',
        type: 'success' // You also can use 'VueNotifications.types.error' instead of 'error'
        },

        empty:{
        title: 'Fill in the empty fields',
        type: 'warn' // You also can use 'VueNotifications.types.error' instead of 'error'

        }
    
    },

    methods: {
      OnLoginClick() {
       //VUEX IS NEEDED FOR THAT ON CLICK METHOD
       if(this.LoginUsername == "" || this.LoginPassword == "") 
       {
           this.empty()
       }

       else{
           let pass=this.LoginPassword 
           let user=this.LoginUsername
          
           this.$store.dispatch("login", { user, pass })
           .then( token=> {
               
                   if(token =="1" || token == "2"  || token == "3"  || token == "4")
                    {
                        this.showLoginSuccess()
                    }
                    else{
                         this.showLoginError()
                    }
               
           })
           
        }
        
        
    }
}}

</script>

<style lang="stylus" scoped>
.login {
        width: auto;
        height :100%;
        border: 1px solid #CCCCCC;
        margin-bottom:150px;
      
}
.card {
            background-color: rgba(300, 228, 255, 0.2);
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
  background-image: linear-gradient(to right, #ffecd2 0%, #fcb69f 51%, #ffecd2 100%);
}
</style>


