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
    const token = this.$route.params.token;
    this.fetchAllUsers(token);
  },
  methods: {
    async fetchAllUsers(token) {
      console.log(`Authorization : Bearer ${token}`)

      try {
        const response = await fetch('http://localhost:8085/user/mainController/allUsers', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
          }
        });



        const data = await response.json();
        console.log(data)
        this.allUsers = data;
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }
  }
};
</script>
