import Vue from 'vue'
import App from './App.vue'
import VueCarousel from 'vue-carousel';
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import router from './router/index.js'
import shoppingcart from "./components/ShoppingCart.vue"
import 'bootstrap/dist/css/bootstrap.min.css'
import {store} from './store.js'
import axios from 'axios'
import VueAxios from 'vue-axios'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'
import VueSession from 'vue-session'
import VueNotifications from 'vue-notifications'
import miniToastr from 'mini-toastr'
import 'bootstrap-css-only/css/bootstrap.min.css'
import 'mdbvue/lib/css/mdb.min.css'
import '@fortawesome/fontawesome-free/css/all.min.css'

import JwPagination from 'jw-vue-pagination';
import VCalendar from 'v-calendar';
Vue.use(VCalendar)
Vue.component('jw-pagination', JwPagination);
Vue.use(VueSession)
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.use(VueAxios, axios)
Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)
Vue.use(VueCarousel);

Vue.component('shoppingcart',shoppingcart);

Vue.config.productionTip = false
const toastTypes = {
  success: 'success',
  error: 'error',
  info: 'info',
  warn: 'warn'
}
miniToastr.init({types: toastTypes})
function toast ({title, message, type, timeout, cb}) {
  return miniToastr[type](message, title, timeout, cb)
}
const options = {
  success: toast,
  error: toast,
  info: toast,
  warn: toast
}

Vue.use(VueNotifications, options)

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
