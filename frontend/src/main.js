import Vue from 'vue';
import App from './App.vue';
import VueResource from 'vue-resource';
import Vuetify from "vuetify";
import VueStarRating from "vue-star-rating";

Vue.use(VueResource);
Vue.use(Vuetify)
Vue.component('star-rating', VueStarRating.default);

new Vue({
    vuetify: new Vuetify(),
    render: h => h(App)
}).$mount('#app');

