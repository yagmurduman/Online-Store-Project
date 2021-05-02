<template>
    <!-- Editable table -->
    <div class="card">
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Campaigns</h3>
    <div class="card-body">
        <div id="table" class="table-editable">
          <span class="table-add float-left mb-3 mr-2">
          <ul class="nav nav-tabs">
            <li class="nav-item">
              <a class="nav-link " href="#/sm">Products</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/Delivery">Delivery List</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" href="#/Campaign">Campaigns</a>
            </li>
          </ul></span>
        <span class="table-add float-right mb-3 mr-2"><button 
                    class="btn btn-success btn-rounded btn-sm my-0" v-b-modal.modal-scrollable >Insert Campaign</button>
                    </span> 
                    
                    <b-modal id="modal-scrollable" scrollable title="Enter Campaign Information">
                       <div class="modal-body">
                        <form>
                          <div class="form-group">
                            <label for="discount" class="col-form-label">Discount:</label>
                            <input type="number" v-model= "discount" class="form-control" id="discount">
                          </div>


                          <div class="form-group">
                            <label for="pId" class="col-form-label">Product ID:</label>
                            <input type="number" v-model= "pId" class="form-control" id="pId">
                          </div> 

                          <div class="form-group">
                            <label for="location" class="col-form-label">Location:</label>
                            <input type="text" v-model= "location" class="form-control" id="location">
                          </div> 

                         
                         
                        </form>
                      </div>
                      <template v-slot:modal-footer="{ ok, cancel}">
                        <button type="button" class="btn btn-secondary" @click="cancel()" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" @click=" insertCampaign(camId,location)">Save changes</button>
                      </template>
                    </b-modal>
                  
                  <span class="table-add float-right mb-3 mr-2"><button 
                    class="btn btn-success btn-rounded btn-sm my-0" v-b-modal.modal-scrollable2 >Delete Campaign</button>
                    </span> 
                    <b-modal id="modal-scrollable2" scrollable title="Enter Campaign Information">
                       <div class="modal-body">
                        <form>
                         
                          <div class="form-group">
                            <label for="camId" class="col-form-label">Campaign ID:</label>
                            <input type="number" v-model= "camId" class="form-control" id="camId">
                          </div> 
                         
                          
                  
                        </form>
                      </div>
                      <template v-slot:modal-footer="{ ok, cancel}">
                        <button type="button" class="btn btn-secondary"  @click="cancel()" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" @click=" deleteCampaign()">Save changes</button>
                      </template>
                    </b-modal>


                      <span class="table-add float-right mb-3 mr-2"><button 
                        class="btn btn-success btn-rounded btn-sm my-0" v-b-modal.modal-scrollable7 >Update Campaign</button>
                        </span> 
                        <b-modal id="modal-scrollable7" scrollable title="Enter Campaign Information">
                          <div class="modal-body">
                            <form>
                         
                              <div class="form-group">
                                <label for="camId" class="col-form-label">Campaign ID:</label>
                                <input type="number" v-model= "camId" class="form-control" id="camId">
                              </div> 
                         
                              <div class="form-group">
                                <label for="value" class="col-form-label">Status:</label>
                                <input type="boolean" v-model= "value" class="form-control" id="value">
                              </div>
                  
                            </form>
                          </div>
                          <template v-slot:modal-footer="{ ok, cancel}">
                            <button type="button" class="btn btn-secondary" @click="cancel()" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" @click=" UpdateCampaign()">Save changes</button>
                          </template>
                        </b-modal>
                  
    
            
          
            
        <table class="table table-bordered table-responsive-md table-striped text-center">
            <tr>
                <th class="text-center">Campaign Id</th>
                <th class="text-center">Code</th>
                <th class="text-center">Discount</th>
                <th class="text-center">Product Id</th>
                <th class="text-center">Validation Status</th>
                <th class="text-center">Location</th>

            </tr>
            <tr v-for="campaign in campaigns" :key="campaign.camId">
              <td>
                    <span class="campaign">
                      <span>{{ campaign.camId}}</span>
                    </span>
              </td>


              <td>
                    <span class="campaign">
                      <span>{{ campaign.code}}</span>
                    </span>
              </td>

               <td>
                    <span class="campaign">
                      <span>{{ campaign.discount}}</span>
                    </span>
              </td>


               <td>
                    <span class="campaign">
                      <span>{{ campaign.pId}}</span>
                    </span>
              </td>               

               <td>
                    <span class="campaign">
                      <span>{{ campaign.valid}}</span>
                    </span>
              </td>

              <td>
                    <span class="campaign">
                      <span>{{ campaign.location}}</span>
                    </span>
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
      name: 'Campaign',

      data() {
          return {
          campaigns: [],
          camId: "",
          code: "",
          discount: "",
          pId: "",
          valid: "" ,
          location: "",
          value:"",
         
          }
      }
      ,
      notifications: {
     
      ChangesSaved: { // You can have any name you want instead of 'showLoginError'
        title: 'Your changes are saved!',
        type: 'success' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
        error: { // You can have any name you want instead of 'showLoginError'
        title: 'Check empty fields!',
        type: 'error' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
        dberror: { // You can have any name you want instead of 'showLoginError'
        title: 'Not found!',
        type: 'error' // You also can use 'VueNotifications.types.error' instead of 'error'
        },
       
      },
      methods: {

      
      insertCampaign() {
        if(this.discount=="" || this.pId==""){
          this.error()
        }
        axios.post("http://localhost:8090/salesManager/insert-campaign",{
            camId: this.camId,
            code: this.code,
            discount: this.discount,
            pId: this.pId,
            valid: this.valid,
            location: this.location
        }
        )
         .then(response => 
          {
            if(response.status==200)
            { this.ChangesSaved()
              
            }
          
          })
        .catch(() =>  this.dberror())
        
      },

      deleteCampaign() {
         if(this.camId==""){
          this.error()
        }
        axios.delete("http://localhost:8090/salesManager/delete-campaign="+this.camId)
       .then(response => 
          {
            if(response.status==200)
            { this.ChangesSaved()
              
            }
            else
            {
              this.dberror()
            }
          })
        .catch(() => this.dberror())
      },

      UpdateCampaign() {
        if(this.camId=="" || this.value==""){
          this.error()
        }
        axios.put("http://localhost:8090/salesManager/update-valid/camId="+this.camId+"&value="+this.value)
         .then(response => 
          {
            if(response.status==200)
            { this.ChangesSaved()
              
            }
         
          })
        .catch(() => this.dberror())
        
      },


    },

      mounted () {
      axios
        .get('http://localhost:8090/salesManager/get-all-campaigns',)
        .then(response => (this.campaigns = response.data))
        .catch(() => console.log('pm.vue'));
      },
        updated(){
        axios
          .get('http://localhost:8090/salesManager/get-all-campaigns',)
          .then(response => (this.campaigns = response.data))
          .catch(() => console.log('pm.vue'));
      }

  }
</script>

<style scoped>

img {
  width: 50%;
  height: 80%;
}

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
</style>

