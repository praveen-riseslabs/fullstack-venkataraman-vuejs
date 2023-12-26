<template>
  <div class="container my-2">
    <table v-if="itemList.length > 0" class="table table-bordered table-striped table-hover">
      <thead class="table-dark">
        <tr>
          <th>Title</th>
          <th>Description</th>
          <th>Start Time</th>
          <th>End Time</th>
          <th>Date</th>
        </tr>
      </thead>
      <tbody class="table table-hover">
        <tr v-for="(filteredItems, index) in filteredItems" :key="index">
          <td>{{ filteredItems.title }}</td>
          <td>{{ filteredItems.description }}</td>
          <td>{{ filteredItems.startTime }}</td>
          <td>{{ filteredItems.endTime }}</td>
          <td>{{ filteredItems.date }}</td>
          </tr>
      </tbody>
    </table>
    <p v-else>No items available</p>
  </div>
</template>

<script>

export default {
  data() {
    return {
      itemList: [],
      searchKeyword: ''
    };
  },
  mounted() {
    this.fetchDataFromBackend();
    this.searchKeyword = this.$store.getters.finalSearchkey;
  },
  watch: {
    '$store.getters.finalSearchkey'(newVal) {
      this.searchKeyword = newVal;
    }
  },
  computed: {
    filteredItems() {
      // Filtering logic based on searchKeyword
      if (!this.searchKeyword) {
        return this.itemList;
      } else {
        const keyword = this.searchKeyword.toLowerCase();
        return this.itemList.filter(item =>
          item.title.includes(keyword)
                  );
              }
    },
  },
  methods: {
    async fetchDataFromBackend() {

      const loginCredentials = {
        userid:this.$store.getters.finalUserid
      };
      try {
        const response = await fetch('http://localhost:8085/user/mainController/oneweek', {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${this.$store.getters.finalToken}`,
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(loginCredentials)
        });
        const data = await response.json();
        this.itemList = data; // Assuming the response is an array of items
      } catch (error) {
        console.error('Error fetching data:', error);
      }

    }
  }
};
</script>

<style scoped>
.item-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

.item-table th,
.item-table td {
  padding: 8px 12px;
  border: 1px solid #ccc;
  text-align: left;
}

.item-table th {
  background-color: #f0f0f0;
}

.item-table .action-btn {
  padding: 6px 10px;
  margin-right: 5px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>
