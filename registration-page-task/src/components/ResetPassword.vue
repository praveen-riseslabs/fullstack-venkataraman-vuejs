<template>
  <body>
    <section class="container">
      <header><span>Re</span>set password</header>
      <form @submit.prevent="submitDetails" class="form">

        <div class="column">

          <div class="input-box">
            <label for="password">Enter new Password</label>
            <input type="password" id="password" name="password" autocomplete="new-password"
              placeholder="Enter your password" v-model.trim="password" @blur="validatePassword">
            <p v-if="validatePasswordInput === 'invalid'">password should contain minimum 8 characters</p>
          </div>

        </div>

        <div class="column">

          <div class="input-box">
            <label for="confirmpassword">Confirm Password</label>
            <input type="password" id="confirmpassword" name="confirmpassword" autocomplete="new-password"
              placeholder="confirm password" v-model.trim="confirmpassword" @blur="validateConfirmPassword">
            <p v-if="validateConfirmPasswordInput === 'invalid'">passwords should match</p>
          </div>

        </div>

        <div>
          <button>Reset Password</button>
          <p v-if="validateSubmit === 'invalid'">Please check all fields properly</p>

        </div>

      </form>
    </section>
  </body>
</template>

<script>
export default {
  data() {
    return {
      password: '',
      confirmpassword: '',
      validatePasswordInput: 'pending',
      validateConfirmPasswordInput: 'pending',
      validateSubmit: 'pending'
    }
  },
  props: ['Token', 'email'],
  methods: {
    validatePassword() {

      this.validatePasswordInput = this.password.length < 8 ? 'invalid' : 'pending';

    },

    validateConfirmPassword() {

      this.validateConfirmPasswordInput = this.password !== this.confirmpassword ? 'invalid' : 'pending';

    },

    submitDetails() {
      this.validatePassword();
      this.validateConfirmPassword();

      if (this.validateConfirmPasswordInput === 'invalid' || this.validatePasswordInput === 'invalid') {
        this.validateSubmit = 'invalid';
        return;
      }

      this.submitForm();

    },

    async submitForm() {
      console.log('entered email: ' + this.email.toLowerCase())
      console.log('entered password: ' + this.confirmpassword.toLowerCase())
      console.log('entered token: ' + this.Token)

      const loginCredentials = {
        email: this.email.toLowerCase(),
        token: this.confirmpassword.toLowerCase()
      };
      console.log('json input : ' + JSON.stringify(loginCredentials))
      try {
        const response = await fetch('http://localhost:8085/user/mainController/resetPassword', {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${this.Token}`,
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(loginCredentials)
        });

        const data = await response.json();

        console.log(data.status)
        if (data.status) {
          alert("Password successfully changed page redirecting to login page...")

          this.$router.push('/login');
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