import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Product from "../components/Product.vue";
import Category from "../components/Category.vue";
import mainbooks from "../components/CategoryMainPageBooks.vue";
import mainmusic from "../components/CategoryMainPageMusic.vue";
import maindvd from "../components/CategoryMainPageDvd.vue";
import SearchResults from "../components/SearchResults.vue";
import checkout from "../components/Checkout.vue";
import signup from "../components/Signup.vue";
import Login from "../components/Login.vue";
import sm from "../components/sm.vue";
import pm from "../components/pm.vue";
import profile from "../components/Profile.vue";
import delivery from "../components/Delivery.vue";
import deliveryPm from "../components/Delivery-pm.vue";
import categoryPm from "../components/Category-pm.vue";
import productCategory from "../components/ProductCategory.vue";
import campaign from "../components/Campaign.vue";
import review from "../components/Review-pm.vue";
import adminPm from "../components/Admin-pm.vue";
import adminSm from "../components/Admin-sm.vue";

Vue.use(VueRouter);


const routes = [
  
  
  {path: "/",name: "Home", component: Home },
  {path: '/category/:id/:name',name: 'category',component: Category},
  {path: '/products:id',name: 'product',component: Product},
  {path: '/maincategorybooks',name: 'maincategorybooks',component: mainbooks},
  {path: '/maincategorymusic',name: 'maincategorymusic',component: mainmusic},
  {path: '/maincategorydvd',name: 'maincategorydvd',component: maindvd},
  {path: "/checkout",name: "checkout", component: checkout},
  {path: "/signup",name: "signup", component: signup},
  {path: "/login",name: "login", component: Login},
  {path: "/SearchResults",name: "SearchResults", component: SearchResults},
  {path: "/pm",name: "pm", component: pm,  meta: { requiresAuth: true}},
  {path: "/sm",name: "sm", component: sm,  meta: { requiresAuth: true}},
  {path: "/profile",name: "profile", component: profile},
  {path: "/Delivery-pm",name: "deliveryPm", component: deliveryPm, meta: { requiresAuth: true}},
  {path: "/Category-pm",name: "categoryPm", component: categoryPm, meta: { requiresAuth: true}},
  {path: "/Review-pm",name: "reviewPm", component: review, meta: { requiresAuth: true}},
  {path: "/ProductCategory",name: "productCategory", component: productCategory, meta: { requiresAuth: true}},
  {path: "/Delivery",name: "delivery", component: delivery, meta: { requiresAuth: true}},
  {path: "/Campaign",name: "campaign", component: campaign, meta: { requiresAuth: true}},
  {path: "/Admin-pm",name: "adminPm", component: adminPm, meta: { requiresAuth: true}},
  {path: "/Admin-sm",name: "adminSm", component: adminSm, meta: { requiresAuth: true}},

  
];

const router = new VueRouter({
  routes
});

export default router;
