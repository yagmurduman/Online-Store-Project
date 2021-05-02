<template>
    <!-- Editable table -->
    <div style="margin-bottom:200px" class="card">
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Admin page</h3>
    <div class="card-body">
        <div id="table" class="table-editable">
        <span class="table-add float-left mb-3 mr-2">
          <ul class="nav nav-tabs">
            <li class="nav-item">
              <a class="nav-link active " href="#/admin-pm">Product Managers</a>
            </li>
            <li class="nav-item">
              <a class="nav-link " href="#/admin-sm">Sales Managers</a>
            </li>

          </ul></span>
        <span class="table-add float-right mb-3 mr-2">
          <button class="btn btn-success btn-rounded btn-sm my-0" v-b-modal.modal-scrollable >Add Pm</button>
                    </span> 
                <b-modal id="modal-scrollable" scrollable title="Enter Product Information">
                       <div class="modal-body">
                        <form>



                          <div class="form-group">
                            <label for="userName" class="col-form-label">Username:</label>
                            <input type="text" v-model= "userName" class="form-control" id="userName">
                          </div>

                          <div class="form-group">
                            <label for="password" class="col-form-label">Password:</label>
                            <input type="text" v-model= "password" class="form-control" id="password">
                          </div>
                          
                          
                        
                        </form>
                      </div>
                      <template v-slot:modal-footer="{ ok, cancel}">

                        <button type="button" class="btn btn-secondary" @click="cancel()" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" @click="addPm()">Save changes</button>
                      </template>
                    </b-modal>
                  
    
               
        <table class="table table-bordered table-responsive-md table-striped text-center">
            <tr>
                <th class="text-center">User ID</th>
                <th class="text-center">User Type</th>
                <th class="text-center">Username</th>
                <th class="text-center">Remove Product Manager</th>
        
            </tr>
            <tr v-for="pm in pms" :key="pm.userName">
              <td>
                    <span class="pm">
                      <span>{{ pm.uId}}</span>
                    </span>
              </td>

              <td>
               
                  <span class="pm">
                    <span>{{ pm.type}}</span>
                  </span>

              </td>


              <td>
               
                  <span class="pm">
                    <span>{{ pm.userName}}</span>
                  </span>

              </td>


              <td>
                   <span class="table-remove"><button 
                    class="btn btn-danger btn-rounded btn-sm my-0" @click="removePm(pm.uId)">Remove</button></span>
                  
              </td>

            </tr>
          
        
        </table>
        </div>
    </div>
    </div>
    <!-- Editable table -->
</template>

<script>
  import axios from 'axios';
  export default {
      name: 'Admin',

      data() {
          return {
          pms: [],
          uId: "",
          type: "",
          userName: "",
          password: "",
          }
      },


      methods: {
  
      removePm(uId) {
        axios.delete("http://localhost:8090/admin/deleteUser?uId="+uId)
        .then(response => (this.pms = response.data)) 
        .catch(() => console.log(uId));
        
          
      },

      addPm() {
        axios.post("http://localhost:8090/admin/registerPM",{
                        uId: this.uId,
                        type: this.type,
                        userName: this.userName,
                        password: this.password,
                      })
              
        .catch(() => console.log(this.uId + this.type + this.userName + this.password));
        alert('Your changes are saved!')
     }, 


    },

    mounted () {
    axios
    .get('http://localhost:8090/admin/get-all-pm',)
    .then(response => (this.pms = response.data))
    .catch(() => console.log('Admin-pm.vue'));
    },
    
    updated(){
    axios
        .get('http://localhost:8090/admin/get-all-pm',)
        .then(response => (this.pms = response.data))
        .catch(() => console.log('Admin-pm.vue'));
    }
      

  }
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
</style>