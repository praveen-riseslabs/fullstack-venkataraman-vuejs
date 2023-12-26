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
      searchKeyword:''
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


