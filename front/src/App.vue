<template>
  <div class="head_div">
    <hr>
    <ProductComponent></ProductComponent>
    <span>{{ products }}</span>
    <button @click="getList()">GetList</button>

  </div>
</template>

<script>
import ProductComponent from './components/ProductComponent.vue'

export default {
  name: 'App',
  components: {
    ProductComponent
  },
  data() {
    return {
      products: []
    }
  },
  methods: {
    getList() {
      this.axios.get('http://localhost:8081/products')
          .then(response => (this.products = response.data));
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
