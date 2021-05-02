<template>
<div :style="{backgroundImage:'url(' + require('@/assets/back.png') + ')'}">
  
  <div class="spark-screen container">
    <div class="row justify-content-center" >
      
      <div class="col-lg-8">
          </div></div> 
    <div class="row justify-content-center"> 
        <div class="col-lg-8">
          <div class="card card-default" bg-variant="dark">
          <div class="card-header"><span><strong>Sign Up Form</strong></span></div> 
     
    <div class="card-body"  bg-variant="light" >
    <form ref="form">
        <div class="form-group row"><label class="col-md-4 col-form-label text-md-right"><i>Name</i></label> 
        <div class="col-md-6"><input type="text"  v-model="name" autofocus="autofocus" class="form-control"> <span class="invalid-feedback" style="display: none;"></span></div></div> 

        <div class="form-group row"><label class="col-md-4 col-form-label text-md-right"><i>Surname</i></label> 
        <div class="col-md-6"><input type="text"  v-model="surname" autofocus="autofocus" class="form-control"> <span class="invalid-feedback" style="display: none;"></span></div></div>

        <div class="form-group row"><label class="col-md-4 col-form-label text-md-right"><i>Username</i></label> 
        <div class="col-md-6"><input type="text"  v-model="username" autofocus="autofocus" class="form-control"> <span class="invalid-feedback" style="display: none;"></span></div></div> 

        <div class="form-group row"><label class="col-md-4 col-form-label text-md-right"><i>E-Mail Address</i></label> 
        <div class="col-md-6"><input type="email" v-model="email" class="form-control"> <span class="invalid-feedback" style="display: none;"></span></div></div> 
      
      
        <div class="form-group row"><label class="col-md-4 col-form-label text-md-right"><i>Password</i></label> 
        <div class="col-md-6"><input type="password"  v-model="password" class="form-control"> <span class="invalid-feedback" style="display: none;"></span></div></div> 
        
        <div class="form-group row"><label class="col-md-4 col-form-label text-md-right"><i>Confirm Password</i></label> 
        <div class="col-md-6"><input type="password" v-model="password_confirmation" class="form-control"> <span class="invalid-feedback" style="display: none;"></span></div></div> 

        <div class="form-group row"><label class="col-md-4 col-form-label text-md-right"><i>Address</i></label> 
        <div class="col-md-6"><input type="address" v-model="address" class="form-control"> <span class="invalid-feedback" style="display: none;"></span></div></div> <div>

      
        <div class="form-group row mb-0"><div class="col-md-12 offset-md-0"><b-button variant="outline-danger" v-on:click="OnSignupClick()" class=" btn-block my-1" ><span><i class="fa fa-btn fa-check-circle"></i> Sign Up</span></b-button></div>     </div></div></form></div></div></div></div> </div> 

      <div id="modal-plan-details" tabindex="-1" role="dialog" class="modal fade"><div class="modal-dialog modal-sm"><!----></div></div>
</div>
</template>

<script>
import axios from 'axios';
import firebase from "firebase";

export default {
    name: 'signup',
    
    data() {
        return {
            address:'',
            name:'',
            username:'',
            surname:'',
            email:'',
            password:'',
            password_confirmation:''
        }
    }
    ,
    methods: {
      signUp()
      {
        console.log(this.email)
        axios.post("http://localhost:8090/customer/register",{ address:this.address, cId:0 , cName:this.username, email:this.email, name: this.name, password: this.password, surname: this.surname})
      },

    OnSignupClick() {
       alert('Processing')
       if(this.password == this.password_confirmation)
       {
         alert('Passwords matched!Your account is just Created!')
         this.signUp()
         this.$router.push({ path: '/' })

       }
       else
       {
         alert('Passwords do not match,Try Again!')
       }
       
    },
    
  }
}
</script>

<style lang="stylus" scoped>
.card {
            background-color: rgba(300, 228, 255, 0.2);
        }


</style>
