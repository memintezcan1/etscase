import Vue from 'vue'
import App from './App.vue'
import router from "@/router";
import vuetify from "@/libs/vuetify";
import store from "@/libs/store";

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App),
}).$mount('#app')

require("./resources");
