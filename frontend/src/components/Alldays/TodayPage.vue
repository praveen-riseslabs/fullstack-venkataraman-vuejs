<template>
  <div class="container my-2">
    <input v-if="itemList.length > 0" type="search" 
          v-model.trim="searchKeyword" 
          placeholder='Search activity' 
          class="custom-search  me-5 "/>
    <table v-if="itemList.length > 0" class="table table-bordered table-striped table-hover">
      <thead class="table-dark">
        <tr>
          <th>Title</th>
          <th>Description</th>
          <th>Start Time</th>
          <th>End Time</th>
          <th>Date</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody class="table table-hover">
        <tr v-for="(filteredItems, index) in filteredItems" :key="index">
          <td>{{ filteredItems.title }}</td>
          <td>{{ filteredItems.description }}</td>
          <td>{{ filteredItems.startTime }}</td>
          <td>{{ filteredItems.endTime }}</td>
          <td>{{ filteredItems.date }}</td>
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
      itemList: [],
      searchKeyword: ''
    };
  },
  mounted() {
    this.fetchDataFromBackend();
  },
  computed: {
    filteredItems() {
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
        const response = await fetch('http://localhost:8085/api/private/today', {
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
      this.$router.push({
    name: 'SuccessPage',
    query: {
      title: this.itemList[index].title,
      description: this.itemList[index].description,
      startTimeString: this.itemList[index].startTime,
      endTimeString: this.itemList[index].endTime,
      id:this.itemList[index].id
    }
  });
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

.action-btn {
  padding: 6px 10px;
  margin-right: 5px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}



</style> 
