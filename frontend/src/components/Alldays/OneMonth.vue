<template>
  <div class="container my-2">
    <input v-if="itemList.length > 0" type="search" 
          v-model.trim="searchKeyword" 
          placeholder='Search activity' 
          class="custom-search  me-5 "/>
    <div v-if="itemList.length > 0" class="date-range my-2">
      <label for="fromDate">From Date:</label>
      <input type="date" id="fromDate" v-model="fromDate">
      <label for="toDate">To Date:</label>
      <input type="date" id="toDate" v-model="toDate">
    </div>
    <table v-if="itemList.length > 0" class="table table-bordered table-striped table-hover">
      <thead class="table-dark">
        <tr>
          <!-- <th>Title</th>
          <th>Description</th>
          <th>Start Time</th>
          <th>End Time</th>
          <th>Date</th> -->

      <th @click="sortTable('title')">Title</th>
      <th @click="sortTable('description')">Description</th>
      <th @click="sortTable('startTime')">Start Time</th>
      <th @click="sortTable('endTime')">End Time</th>
      <th @click="sortTable('date')">Date</th>

        </tr>
      </thead>
      <!-- <tbody class="table table-hover">
        <tr v-for="(filteredItems, index) in filteredItems" :key="index">
          <td>{{ filteredItems.title }}</td>
          <td>{{ filteredItems.description }}</td>
          <td>{{ filteredItems.startTime }}</td>
          <td>{{ filteredItems.endTime }}</td>
          <td>{{ filteredItems.date }}</td>
        </tr>
      </tbody> -->
      <tbody class="table table-hover">
  <tr v-for="item in sortedItems" :key="item.id">
    <td>{{ item.title }}</td>
    <td>{{ item.description }}</td>
    <td>{{ item.startTime }}</td>
    <td>{{ item.endTime }}</td>
    <td>{{ item.date }}</td>
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
      searchKeyword: '',
      fromDate: '',
      toDate: '',
      displayedItems: [],
      sortColumn: '',
      sortOrder: 1, // Track the sorting order
    };
  },
  mounted() {
    this.fetchDataFromBackend();
  },
 
  computed: {
    filteredItems() {
      let filteredList = [...this.itemList];
      if (this.searchKeyword) {
        const keyword = this.searchKeyword.toLowerCase();
        filteredList = filteredList.filter(item =>
          item.title.toLowerCase().includes(keyword)
        );
      }
      if (this.fromDate && this.toDate) {
        const fromDate = new Date(this.fromDate).getTime();
        const toDate = new Date(this.toDate).getTime();
        filteredList = filteredList.filter(item => {
          const itemDate = new Date(item.date).getTime();
          return itemDate >= fromDate && itemDate <= toDate;
        });
      }
      return filteredList;
    },
    sortedItems() {
      const sortedList = [...this.filteredItems];
      if (this.sortColumn) {
        sortedList.sort((a, b) => {
          const valueA = a[this.sortColumn];
          const valueB = b[this.sortColumn];
          const modifier = this.sortOrder;

          if (valueA < valueB) return -1 * modifier;
          if (valueA > valueB) return 1 * modifier;
          return 0;
        });
      }
      return sortedList;
    },
  },
  methods: {
    async fetchDataFromBackend() {

      const loginCredentials = {
        userid: this.$store.getters.finalUserid
      };
      try {
        const response = await fetch('http://localhost:8085/api/private/onemonth', {
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

    },

    sortTable(column) {
      if (this.sortColumn === column) {
        this.sortOrder *= -1;
      } else {
        this.sortColumn = column;
        this.sortOrder = 1;
      }
    },
  

  }
};
</script>

<!-- <style scoped>
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
</style> -->
