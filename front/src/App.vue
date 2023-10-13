<template>
  <div class="head_div">
    <hr>
    <h3>Оформление заказа</h3>
    <div>
      <span>Поиск клиента: </span><input class="search_input" v-model="clientName">
      <button @click="searchClient()">Поиск</button>
      <br>
      <br>
      <div class="client_card" v-if="client!=null">
        <span>Клиент: {{ client.name }}</span><br>
        <span>Телефон: {{ client.tel }}</span><br>
        <span>Действующая скидка: {{ client.discount }}%</span><br>
        <hr>
        <span>Количество товара в корзине: {{ shopping_cart.length }}</span><br>
        <span>Сумма к оплате: {{ sum }}</span><br>
        <span>Сумма к оплате со скидкой: {{ sumWithDiscount }}</span>
        <br>
        <ul class="listOfCardsForCheck">
          <li v-for="(shoppingCart, index) in shopping_cart" :key="index" class="shoppingCart">
            <h3 class="department-name">
              <span>{{ shoppingCart.name }} {{ shoppingCart.color }}</span>
            </h3>
          </li>
        </ul>
        <br>
        <button @click="checkOut()">Оформить заказ</button>
      </div>
    </div>
    <br>
    <div>
      <span>Поиск товара: </span><input class="search_input" v-model="searchValue"/>
      <button @click="searchProduct()">Поиск</button>
    </div>
    <ul class="listOfCards">
      <li v-for="(department, index) in departments" :key="index" class="department">
        <h3 class="department-name">
          <button @click="productsByDepartment(department.name)">{{ department.name }}</button>
        </h3>
      </li>
    </ul>


    <ul class="listOfCards">
      <li v-for="(product, index) in products" :key="index" class="product">
        <h3 class="card-name">
          {{ product.name }}
        </h3>
        <div class="product-price">
          <span>цена: {{ product.price }},000 руб</span>
        </div>
        <div class="product-price" v-if="product.color!=null">
          <span>цвет: {{ product.color }}</span>
        </div>
        <div class="product-price" v-if="product.size>0">
          <span>размер: {{ product.size }}</span>
        </div>
        <div class="product-price" v-if="product.department!=null">
          <span>отдел: {{ product.department.name }}</span>
        </div>
        <button @click="addToShoppingCart(product)">+</button>
      </li>
      <li class="product">
        <h3 class="card-name">Создать новый товар</h3>
        <table class="create_product">
          <tr>
            <td>
              <span>Название: </span>
            </td>
            <td>
              <input>
            </td>
          </tr>
          <tr>
            <td>
              <span>цена: </span>
            </td>
            <td>
              <input>
            </td>
          </tr>
          <tr>
            <td>
              <span>Тип товара: </span>
            </td>
            <td>
              <input>
            </td>
          </tr>
          <tr>
            <td>
              <span>Отдел: </span>
            </td>
            <td>
              <input>
            </td>
          </tr>
        </table>

        <!--        <div class="product-price" v-if="product.color!=null">-->
        <!--          <span>цвет: {{ product.color }}</span>-->
        <!--          &lt;!&ndash;        <span>10 x {{ Math.round(product.price / 10) }}, 00 </span>&ndash;&gt;-->
        <!--        </div>-->
        <!--        <div class="product-price" v-if="product.size>0">-->
        <!--          <span>размер: {{ product.size }}</span>-->
        <!--          &lt;!&ndash;        <span>10 x {{ Math.round(product.price / 10) }}, 00 </span>&ndash;&gt;-->
        <!--        </div>-->
        <!--        <div class="product-price" v-if="product.department!=null">-->
        <!--          <span>отдел: {{ product.department.name }}</span>-->
        <!--          &lt;!&ndash;        <span>10 x {{ Math.round(product.price / 10) }}, 00 </span>&ndash;&gt;-->
        <!--        </div>-->
        <button>Создать</button>
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
      client: null,
      clientName: "",
      products: [],
      departments: [],
      shopping_cart: [],
      sum: 0,
      sumWithDiscount: 0
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

    },

    productsByDepartment(name) {
      this.axios.get('http://localhost:8081/productsByDepartment/' + name)
          .then(response => (this.products = response.data));
    },

    searchProduct() {
      if (this.searchValue !== "") {
        const urlString = 'http://localhost:8081/productByName/' + this.searchValue
        this.axios.get(urlString)
            .then(response => (this.products = response.data));
      }

    },
    searchClient() {

      if (this.clientName !== "") {
        const urlString = 'http://localhost:8081/clientByName/' + this.clientName

        this.axios.get(urlString)
            .then(response => (this.client = response.data)).finally(() => {
          if (this.client === null)
            alert("Клиент не найден")
        });

        this.shopping_cart = []
      }
    },
    addToShoppingCart(product) {
      this.shopping_cart.push(product);
      this.sum = this.sum + product.price;

      this.sumWithDiscount = (this.sum - (this.sum / 100 * this.client.discount))
    },

    checkOut() {


    },
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

.client_card {
  width: 300px;
  background-color: #fff;
  list-style: none;
  box-sizing: border-box;
  padding: 1em;
  /*margin: 1em 0;*/
  border: solid;
  align-items: center;
  margin: auto;
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
.create_product td{
padding: 5px;
}

</style>
