<template>
<div  class=" mt-4" style="width:103%;margin-left:-30px" :style="{backgroundImage:'url(https://www.solidbackgrounds.com/images/2480x3508/2480x3508-pale-copper-solid-color-background.jpg)'}">

<!-- Footer -->
<footer class="footer font-small  white-text">

  <!-- Footer Elements -->
  <div class="container-fluid">

    <div class="row">
      <div class="col-md-4">
        <h3 class="font-weight-bold mb-0 mr-5">Onlinestore ✎♬ </h3>
      </div>
      <div class="col-md-4">
        <ul class="list-unstyled d-flex justify-content-center mb-0 mt-2 text-uppercase">
          <li>
          	<a class="mx-1" role="button">© All</a>
          </li>
          <li>
          	<a class="mx-1" role="button">Rights</a>
          </li>
          <li>
          	<a class="mx-1" role="button">Reserved</a>
          </li>
        </ul>
      </div>
      <div class="col-md-4">
        <ul class="list-unstyled d-flex justify-content-end mb-0 mt-2">
          <li>
          	<a class="mx-3" role="button"><i class="fab fa-facebook-f"></i></a>
          </li>
          <li>
          	<a class="mx-3" role="button"><i class="fab fa-twitter"></i></a>
          </li>
          <li>
          	<a class="mx-3" role="button"><i class="fab fa-instagram"></i></a>
          </li>
        </ul>
      </div>
    </div>

  </div>
  <!-- Footer Elements -->

</footer>
</div>
<!-- Footer -->
</template>
<script>
import router from '../router/index.js';
import axios from 'axios';


export default {
    name: 'Footer'   
};
</script>



<style>


  .footer {
   flex-shrink: 0;
   height:100px;
   padding-top:40px;

  }

  #footer .icon {
    height: 23px;
    display: inline-block;
    margin-bottom: -6px;
  }

  .hr {
    border-bottom: 1px solid #dbdbdb;
    margin: 3em 0;
  }

  #footer p {
    font-size: 15px;
    color: #767676 !important;
    
}
</style>