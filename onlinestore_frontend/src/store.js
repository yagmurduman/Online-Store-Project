import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from './router/index.js'

Vue.use(Vuex);

export const store = new Vuex.Store({

    state:{
        wishlist:[],
        searchkey:"",
        cid: localStorage.getItem('cid') || "0",
        type:"",
        categories:[{id:1,name:"All Books",images:['library.jpg']},{id:2,name:"All Music",images:['Music.png']},{id:3,name:"All DVDs",images:['Films.jpg']}],
        searchProducts:[],

        status: '',
        token:  localStorage.getItem('token'),
  
        
      
    },
    
    getters: {
        searchkey: state => {
          return state.searchkey
        },
        searchProduct: state => {
          
          return state.searchProducts
        },
        cid: state => {
            return state.cid
          },
        type: state => {
            return state.type
          },
          /*
        loggedin: state => {
            return state.loggedin
          },*/
          isLoggedIn: state =>{ 
          if(state.token == "undefined" || state.token == "-2" || state.token == "-1" || state.token == null )
          { 
            
            return "0"
          }
          else
          { 
            console.log(state.token)
            return state.token;
          }},
          
         //type
          authStatus: state => state.status,

    },
    
    mutations: {

      setproducts (state, value) {
        state.searchProducts = value;
      },
      auth_request(state){
        state.status = 'loading'
      },
      auth_success(state, payload){
        state.status = 'success'
        state.token = payload.token//type
        state.cid = payload.cid//cid
        
      },
      auth_error(state){
        state.status = 'error'
      },
      logout(state){
        state.status = ''
        state.token = '0'
        
      },
     
      
    },

    actions: {

      login({ commit }, user) {
        return new Promise((resolve, reject) => {
          commit('auth_request')
           axios.post("http://localhost:8090/user/login?password="+user.pass +"&userName="+user.user)
            .then(response => {
              console.log(response.data[0])
              if(response.data[0] =="1" || response.data[0] == "2"  ||  response.data[0] == "3"  ||  response.data[0] == "4"){
              const token = response.data[0]//type
              const cid = response.data[1]//cid
              if(response.data[0]=="4")
              {
              router.push("/Admin-pm")
               }
              else{ router.push("/")}
              localStorage.setItem('token', token)
              localStorage.setItem('cid', cid)
              // Add the following line:
              axios.defaults.headers.common['Authorization'] = token
              commit({type:'auth_success',token,cid})
              resolve(response.data[0])
            }
            else{
             resolve(response.data[0])
            }
          })
            .catch(error => {
              commit('auth_error')
              localStorage.removeItem('token')
              reject(error)
            })
        })
      },

      logout({commit})
      {
        
        return new Promise((resolve) => {
          commit('logout')
          localStorage.setItem('token', "0")
          localStorage.removeItem('cid')
          delete axios.defaults.headers.common['Authorization']
          resolve()
        })
      }
    }

});
