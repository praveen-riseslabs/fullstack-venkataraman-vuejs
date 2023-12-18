<template>
  <div>
    <h2>List of Items</h2>
    <table v-if="itemList.length > 0" class="item-table">
      <thead>
        <tr>
          <th>Title</th>
          <th>Description</th>
          <th>Start Time</th>
          <th>End Time</th>
          <th>Date</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in itemList" :key="index">
          <td>{{ item.title }}</td>
          <td>{{ item.description }}</td>
          <td>{{ item.startTime }}</td>
          <td>{{ item.endTime }}</td>
          <td>{{ item.date }}</td>
          <td>
            <button @click="editItem(index)" class="action-btn">Edit</button>
            <button @click="deleteItem(index)" class="action-btn">Delete</button>
          </td>
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
      itemList: [] // Initially an empty array to store items from backend
    };
  },
  mounted() {

    this.fetchDataFromBackend();
  },
  methods: {
    async fetchDataFromBackend() {

      const loginCredentials = {
        userid:this.$store.getters.finalUserid
      };
      try {
        const response = await fetch('http://localhost:8085/user/mainController/today', {
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
    editItem(index) {
      // Implement the logic to edit an item at the given index
      // You can redirect to a different route or open a modal for editing
      console.log(`Editing item at index ${index}:`, this.itemList[index]);
    },
    deleteItem(index){
      this.fetchDataToBackend(index);
    },
    async fetchDataToBackend(index) {

      const loginCredentials = {
        userid: this.itemList[index].id
      };
try {
  const response = await fetch('http://localhost:8085/user/mainController/delete', {
    method: 'DELETE',
    headers: {
      'Authorization': `Bearer ${this.$store.getters.finalToken}`,
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(loginCredentials)
  });
  const data = await response.json();
  if (data.status) {
    this.fetchDataFromBackend();
  }
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
