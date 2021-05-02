<template>
  <div class="Home">
    <Header/>
    <HomePage/>
     
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header-notloggedin.vue";
import HomePage from "@/components/HomePage.vue"

export default {
  name: "Home",
  components: {
    Header,
    HomePage
  }
};
</script>
