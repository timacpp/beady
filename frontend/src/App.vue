<template>
  <v-app v-if="items">
    <div id="items" style="display: flex; flex-direction: row">
      <v-card v-for="item in items" :key="item.id" width="25%">
        <v-card-title>{{ item.name }}</v-card-title>
        <v-card-text>
          <v-row>
            <v-col>
              Available: {{ item.quantity }}
            </v-col>
            <v-col>
              Rating: {{ item.rating }} / 5
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <star-rating
                  v-bind:star-size="40"
                  v-bind:show-rating="false"
                  v-bind:clearable="false"
                  v-model:rating="rating"
                  @rating-selected="createFeedback(item.id)"
              />
            </v-col>
          </v-row>
        </v-card-text>
        <v-img :src="require(`./assets/${item.id}.jpg`)" class="white--text align-end"/>
        <v-card-actions>
          <v-col>
            Price: {{ item.price }} EUR
          </v-col>
          <v-col class="text-right">
            <v-btn color="deep-purple lighten-2" text @click="createOrder(item.id)">
              Order
            </v-btn>
          </v-col>
        </v-card-actions>
      </v-card>
    </div>
  </v-app>
</template>

<script>

import StarRating from 'vue-star-rating'

export default {
  name: 'HomeView',
  components: {StarRating},

  data() {
    return {
      items: undefined,
      rating: undefined,
    }
  },

  mounted() {
    this.getStock();
  },

  methods: {
    getStock() {
      return this.$http.get('http://localhost:8080/item/stock')
          .then(response => this.items = response.data)
    },

    createOrder(itemId) {
      console.log(this.itemId);
      this.$http.post('http://localhost:8080/order/create', {body: {
          email: "a@gmail.com",
          itemId: itemId,
          quantity: 1,
        }}).then(this.getStock);

      this.getStock();
    },

    createFeedback(itemId) {
      this.$http.post('http://localhost:8080/feedback/create', {body: {
        itemId: itemId,
        rating: this.rating
      }}).then(this.getStock)
    },
  }
}

</script>