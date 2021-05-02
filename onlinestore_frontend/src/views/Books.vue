<template>
  <div class="books">
    <Header/>
    <Books/>
  </div>
</template>

<script>
import Header from "@/components/Header-notloggedin"
import Books from "@/components/CategoryMainPageBooks"
export default {

  name: "books",
  components: {
    Header,
    Books
  }
  
}
</script>

<style  scoped>

</style>