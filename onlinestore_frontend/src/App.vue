<template>
  <div id="app">
  
    <div v-if="this.isLoggedIn=='0'"><HeaderNotLoggedin /></div>
    <div v-else> <HeaderLoggedin /></div>
   
    <router-view> <Homepage /> </router-view>
    <Footer/>
  </div>
</template>

<script>
import Homepage from './components/HomePage.vue';
import HeaderNotLoggedin from './components/Header-notloggedin.vue';
import HeaderLoggedin from './components/Header-Loggedin.vue';

import Footer from './components/Footer.vue';
import { mapGetters } from 'vuex'
export default {
  name: 'App',
  components: {
    HeaderNotLoggedin,
    HeaderLoggedin,
   
    Homepage,
    Footer
  } ,
   computed:{
        ...mapGetters([
      'isLoggedIn',])
    }
};
</script>

<style>
#app {
font-family: vortice-concept, sans-serif;
  font-style: normal;
  font-weight: 400;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #063764;
  
  
  margin-left: 30px;
  margin-right: 30px;

  display: grid;
  grid-template-rows: auto;
  grid-template-columns: repeat(1, 100%);
  
}

</style>
