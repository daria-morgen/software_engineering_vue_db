<template>
  <div class="new_product_head">
    <div class="new_product">
      <table>
        <thead>
        <tr>
          <th colspan="2">Создать новый товар</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>
            <span>Название: </span>
          </td>
          <td>
            <input v-model="newProduct.name">
          </td>
        </tr>
        <tr>
          <td>
            <span>цена: </span>
          </td>
          <td>
            <input v-model="newProduct.price">
          </td>
        </tr>
        <tr>
          <td>
            <span>Отдел:</span>
          </td>
          <td>
            <select v-model="newProduct.department">
              <option v-for="(department, index) in departments" :key="index" :value="department">
                {{ department.name }}
              </option>
            </select>
          </td>
        </tr>
        <tr>
          <td>
            <span>Тип товара:</span>
          </td>
          <td>
            <select v-model="newProduct.productType">
              <option v-for="(productType, index) in productTypes" :key="index" :value="productType">
                {{ productType.name }}
              </option>
            </select>
          </td>
        </tr>
        </tbody>
      </table>
      <br>
      <button @click="createProduct()">Создать</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    departments: Array,
    productTypes: Array
  },
  data() {
    return {
      newProduct: {
        name: "",
        price: "",
        color: "",
        size: "",
        department: {},
        productType: {}
      }
    }
  },
  methods: {
    createProduct() {
      const urlString = 'http://localhost:8081/newProduct'
      console.log(this.productToJson(this.newProduct))

      this.axios.post(urlString, this.productToJson(this.newProduct),
          {
            headers: {
              "content-type": "application/json",
            },
          }
      );
    },
    productToJson(productToJson) {
      return "{\"name\":\"" + productToJson.name + "\",\n" +
          "    \"price\":\"" + productToJson.price + "\",\n" +
          "    \"color\":\"" + productToJson.color + "\",\n" +
          "    \"size\":\"" + productToJson.size + "\",\n" +
          "    \"productType\": {\"id\":" + productToJson.productType.id + ", \"name\": \"" + productToJson.productType.name + "\"},\n" +
          "    \"department\": {\"id\": " + productToJson.department.id + ", \"name\": \"" + productToJson.department.name + "\"}\n" +
          "}";
    }
  }
}

</script>

<style>
.new_product {
  margin: auto;
  width: 1000px;
  border: solid;
  background-color: #f0f0e6;
  border-radius: 7px;

}
.new_product thead{
  text-align: center;
}
.new_product table {
  text-align: left;
  margin: auto;
}
.new_product td{
  padding: 10px;
  box-sizing: inherit;
  border: 1px solid #ccc!important;
  border-radius: 7px;
}

.new_product select, input {
  width: 200px;
}
.new_product_head{
  padding: 10px;
}

</style>