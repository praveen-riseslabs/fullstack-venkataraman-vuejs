<template>
  <div>
    <h2>Registration Successful!</h2>
    <p>You have successfully registered your details.</p>
    <h3>Previously Registered Users:</h3>
    <ul>
      <li v-for="(user, index) in allUsers" :key="index">
        {{ user.name }} - {{ user.username }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      allUsers: [] 
    };
  },
  mounted() {
    this.fetchAllUsers(); 
  },
  methods: {
    async fetchAllUsers() {
      try {
        const response = await fetch('http://localhost:8085/allusers');
        const data = await response.json();
        this.allUsers = data; 
      } catch (error) {
        console.error('Error fetching data:', error.message);
      }
    }
  }
};

</script>