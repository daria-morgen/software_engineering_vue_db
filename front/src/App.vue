<template>
  <div class="head_div">
    <hr>

    <h3>Отделы</h3>
    <ul class="listOfCards">
      <li v-for="(department, index) in departments" :key="index" class="department">
        <h3 class="department-name">
          <button @click="productsByDepartment(department.name)">{{ department.name }}</button>
        </h3>
      </li>
    </ul>


    <hr>
    <h3>Товары</h3>
    <input class="search_input" v-model="searchValue"/>
    <button @click="searchProduct()">Поиск</button>
    <ul class="listOfCards">
      <li v-for="(product, index) in products" :key="index" class="product">
        <h3 class="card-name">
          {{ product.name }}
        </h3>
        <div class="product-price">
          <span>цена: {{ product.price }},000 руб</span>
          <!--        <span>10 x {{ Math.round(product.price / 10) }}, 00 </span>-->
        </div>
        <div class="product-price" v-if="product.color!=null">
          <span>цвет: {{ product.color }}</span>
          <!--        <span>10 x {{ Math.round(product.price / 10) }}, 00 </span>-->
        </div>
        <div class="product-price" v-if="product.size>0">
          <span>размер: {{ product.size }}</span>
          <!--        <span>10 x {{ Math.round(product.price / 10) }}, 00 </span>-->
        </div>
        <div class="product-price" v-if="product.department!=null">
          <span>отдел: {{ product.department.name }}</span>
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
      products: [],
      departments: []
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

    getAllDepartments() {
      this.axios.get('http://localhost:8081/departments')
          .then(response => (this.departments = response.data));

      try {
        this.departments = JSON.parse(this.departments)
      } catch (error) {
        return null;
      }
    },

    productsByDepartment(name) {
      this.axios.get('http://localhost:8081/productsByDepartment/' + name)
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
    this.getAllDepartments()
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

.listOfCards {
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

.card-name {
  font-size: 1.2em;
  font-weight: normal;
}

.product-price {
  width: 100%;
  align-self: flex-start;
  display: flex;
  justify-content: space-between;
  margin-bottom: .5em;
}
</style>
