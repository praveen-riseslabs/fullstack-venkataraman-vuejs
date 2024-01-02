<template>
  <body>
    <section class="container">
      <header><span>Lo</span>gin</header>
      <form @submit.prevent="submitDetails" class="form">

        <div class="column">

          <div class="input-box">
            <label for="username">User Name</label>
            <input type="text" id="username" name="username" autocomplete="username" placeholder="Enter your username"
              v-model.trim="username" @blur="validateUsername">
            <p v-if="validateUsernameInput === 'invalid'">Invalid username</p>
          </div>

        </div>

        <div class="column">

          <div class="input-box">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" autocomplete="new-password"
              placeholder="Enter your password" v-model.trim="password" @blur="validatePassword">
            <p v-if="validatePasswordInput === 'invalid'">password should contain 8 characters</p>
          </div>

        </div>

        <div>
          <button>Login</button>
          <p v-if="validateSubmit === 'invalid'">Please check all fields properly</p>
          <p> New User <router-link :to="'/registrationPage'"> Register here</router-link></p>
          <p> Existing User <router-link :to="'/forgotpassword'"> Forget Password</router-link></p>

        </div>

      </form>
    </section>
  </body>
</template>


<script>


export default {



  data() {
    return {
      username: '',
      password: '',

      validateUsernameInput: 'pending',
      validatePasswordInput: 'pending',
      validateSubmit: 'pending',

    }
  },

  methods: {

   

    validateUsername() {

      this.validateSubmit = this.validatePasswordInput === 'invalid' ? 'invalid' : 'pending';


      this.validateUsernameInput = this.username === '' ? 'invalid' : 'pending';


    },
    validatePassword() {

      this.validateSubmit = this.validateUsernameInput === 'invalid' ? 'invalid' : 'pending';


      this.validatePasswordInput = this.password.length < 8 ? 'invalid' : 'pending';


    },

    submitDetails() {

      this.validateUsername();
      this.validatePassword();

      if (this.validateUsernameInput === 'invalid' ||
        this.validatePasswordInput === 'invalid') {

        this.validateSubmit = 'invalid';
        return;

      }

      this.submitForm();

    },

    async submitForm() {

      const loginCredentials = {
        username: this.username.toLowerCase(),
        password: this.password.toLowerCase()
      };

      try {
        const response = await fetch('http://localhost:8085/user/authenticate', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(loginCredentials)
        });
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }

        const data = await response.json();

        if (data && data.token && data.userid && data.userName){
         this.$store.commit('pass',data)

          this.$router.push('/mainbar');

        } else {
          this.$router.push('/failure');
        }
      } catch (error) {
        console.error('Error while fetching:', error.message);
      }

    }

  }

}
</script>
  
<style src="../styles/style.css" scoped></style>