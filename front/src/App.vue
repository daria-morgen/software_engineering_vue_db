<template>
  <div class="head_div">
    <hr>
    <input class="search_input" v-model="searchValue"/>
    <button @click="searchProduct()">Поиск</button>
    <br>
    <br>
    <ul class="listOfProducts">
      <li v-for="(product, index) in products" :key="index" class="product">
        <h3 class="product-name">
          {{ product.name }}
        </h3>
        <div class="product-price">
          <span>{{ product.prise }}</span>
          <!--        <span>10 x {{ Math.round(product.price / 10) }}, 00 </span>-->
        </div>
      </li>
    </ul>
    <hr>

  </div>
</template>

<script>

export default {
  name: 'App',
  components: {
    // ProductComponent
  },
  data() {
    return {
      searchValue: "",
      products: []
    }
  },
  methods: {
    getAllProducts() {
      this.axios.get('http://localhost:8081/products')
          .then(response => (this.products = response.data));

      try {
        this.products = JSON.parse(this.products)
      } catch (error) {
        return null;
      }
    },
    searchProduct() {
      const urlString = 'http://localhost:8081/productByName/' + this.searchValue

      this.axios.get(urlString)
          .then(response => (this.products = response.data));
    }
  },
  beforeMount() {
    this.getAllProducts()
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

.search_input {
  margin-right: 10px;
}

.listOfProducts {
  width: 100%;
  max-width: 1000px;
  margin: 0 auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  padding: 0;
}

.product {
  width: 300px;
  background-color: #fff;
  list-style: none;
  box-sizing: border-box;
  padding: 1em;
  margin: 1em 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 7px;
  /*border-color: black;*/
  border: solid;
}

.product-name {
  font-size: 1.2em;
  font-weight: normal;
}

/*.product-name:hover {*/
/*  cursor: pointer;*/
/*  text-decoration: underline;*/
/*}*/

.product-price {
  width: 100%;
  align-self: flex-start;
  display: flex;
  justify-content: space-between;
  margin-bottom: .5em;
}
</style>
