<template>

<div class="container bootstrap snippet">
    <div class="row">
        <div class="col-sm-10" >
            <h1>{{this.userinfo.name + " " +this.userinfo.surname }}</h1></div>
       
    </div>
    <div class="row">
        <div class="col-sm-3">
            <!--left col-->

            <ul class="list-group">
                <li class="list-group-item text-muted">Profile</li>
                <li class="list-group-item text-right-bold"><span class="pull-left"><strong>Address: </strong></span> {{this.userinfo.address}}</li>
                <li class="list-group-item text-right-bold"><span class="pull-left"><strong>Name: </strong></span> {{this.userinfo.name }}</li>
                <li class="list-group-item text-right-bold"><span class="pull-left"><strong>Surname: </strong></span> {{this.userinfo.surname }}</li>
                <li class="list-group-item text-right-bold"><span class="pull-left"><strong>Username: </strong></span> {{this.userinfo.cName }}</li>
                <li class="list-group-item text-right-bold"><span class="pull-left"><strong>Email: </strong></span> {{this.userinfo.email }}</li>
    
            </ul>
            

        </div>
        <!--/col-3-->
        <div class="col-sm-9">
            <vue-tabs>
                <v-tab title="Orders">
                
            <div class="tab-content">
                <div class="tab-pane active" id="home">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    
                                    <th>Delivery ID</th>
                                    <th>Products</th>
                                    <th>Price </th>
                                    <th>Quantity </th>
                                    <th>Date of order </th>
                                    <th>Delivery status </th>
                                </tr>
                            </thead>
                            <tbody  id="items" v-for="i in deliveries" :key="i.dId">
                                 <tr bgcolor=lightgrey>
                                    <td>{{i[0].dId}}</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>{{i[0].date}}</td>
                                    <td>{{i[0].delivered}}</td> 
                                 </tr>
                                   <tr id="items" v-for="x in i" :key="x">
                                     <td></td>
                                    <td>{{x.pName}}</td>
                                    <td>{{x.price}}</td>
                                    <td>{{x.quantity}}</td>
                                    <td></td>
                                    <td></td>
                                </tr>

                            </tbody>
                            
                        </table>
                        <hr>
                        <div class="row">
                            <div class="col-md-4 col-md-offset-4 text-center">
                                <ul class="pagination" id="myPager"></ul>
                            </div>
                        </div>
                    </div>
                    <!--/table-resp-->

                    <hr>
                </div>
                </div>
                </v-tab>

              

                <v-tab title="Settings">
                <div class="tab-panel" id="settings">
                    
                    <hr>
                    <form class="form"  id="registrationForm">
                    
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="address">
                                    <h4>Change Address: </h4></label>
                                <input v-model="address" type="adress" class="form-control" id="location" placeholder="somewhere" title="enter a location">
                                <button @click="changeAdress()" class="btn btn-5 btn-md" ><i class="glyphicon glyphicon-ok-sign"></i>Change Address</button>
                 
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="password">
                                    <h4>Change Password: </h4></label>
                                <input  v-model="password"  type="password" class="form-control" name="password" id="password" placeholder="password" title="enter your password.">
                                     <button @click="changePassWord()" class="btn btn-5 btn-md " type="submit"><i class="glyphicon glyphicon-ok-sign"></i>Change Password</button>
                 
                            </div>
                        </div>
                       
                        <div class="form-group">
                            <div class="col-xs-12">
                                <br>
                                      </div>
                        </div>
                    </form>
                    <hr>
                </div>
                
             </v-tab>
            </vue-tabs>

                <!--/tab-pane-->
                
                <!--/tab-pane-->
               
            <!--/tab-pane-->
       
        <!--/tab-content-->

    </div>
    <!--/col-9-->
</div>
</div>
<!--/row-->                             
    
</template>


<script>
import {VueTabs, VTab} from 'vue-nav-tabs'
import 'vue-nav-tabs/themes/vue-tabs.css'

import axios from 'axios';
import { mapGetters } from 'vuex'
export default {
    name: 'profile',   
    components: {
    VueTabs,
    VTab
    }, 
    data() {
    return {
      deliveries: [],
      userinfo:[],
      address:"",
      password:"",
    };
    },
    computed:{
    ...mapGetters([
      'cid',])
  },
  methods:{
        changeAdress()
        {  
             if(this.address!=""){
            axios
            .put("http://localhost:8090/customer/updateAdress?adress="+this.address+"&cId="+this.cid)
            .then(response=> {if(response.status==200)
            {   this.showaddresschange();
            
            }})
            
            .catch(() => console.log('adress'));
             }else{
                this.fillblank()


            }

        }
        
        ,
        changePassWord(){
            if(this.password!=""){
                axios
                .put("http://localhost:8090/customer/updatePassword?cId="+this.cid+"&password="+this.password)
                .then(response=> {if(response.status==200)
                {   this.showpasswordchange();

                }})
                .catch(() => console.log('adress'));
            }else{
                this.fillblank()


            }
        }

  },
  notifications: {
     
      showaddresschange: { // You can have any name you want instead of 'showLoginError'
        title: 'Success!',
        message: 'Your address is succesfully changed!',
        type: 'success' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
        showpasswordchange:{
           message: 'Your password is succesfully changed!',
            type: 'success'

        },
         fillblank:{
           message: 'Please fill in the blanks!',
            type: 'error'

        },
  },
 
    mounted() {
    axios
      .get("http://localhost:8090/delivery/get-deliveries-by-cId="+this.cid)
      .then(response => this.deliveries=response.data
      )
      .catch(() => console.log(this.cid));
     axios
      .post("http://localhost:8090/customer/get-info?cId="+this.cid)
      .then(response => (this.userinfo = response.data))
      .catch(() => console.log('whyy?'));
  }
    
}
</script>
<style scoped>
body{margin-top:20px;} 

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
</style>