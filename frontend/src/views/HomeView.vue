<template>
  <div class="home">
    <ShopHeader/>
    <ItemPreview>

    </ItemPreview>
  </div>>
</template>

<script>

import ShopHeader from '@/components/ShopHeader'
import ItemPreview from "@/components/ItemPreview";

import {Api} from "@/api/api";

export default {
  name: 'HomeView',
  components: {ShopHeader, ItemPreview},

  data() {
    return {
      items: undefined,
    }
  },

  methods: {
    getItems() {
      Api.get(this, 'available-items', (items) => {
        this.items = items;
      }, (error) => {
        console.error(error);
      });
    }
  }
}

</script>
