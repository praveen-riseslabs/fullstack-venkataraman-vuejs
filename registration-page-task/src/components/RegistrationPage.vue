<template>
  <body>
    <section class="container">
      <header><span>Re</span>gistration</header>
      <form @submit.prevent="submitDetails" class="form">

        <div class="column">

          <div class="input-box">
            <label for="name">Full Name</label>
            <input type="text" id="name" name="name" autocomplete="name" placeholder="Enter your name" v-model.trim="name"
              @blur="validateName">
            <p v-if="validateNameInput === 'invalid'">Invalid name</p>
          </div>

          <div class="input-box">
            <label for="username">User Name</label>
            <input type="text" id="username" name="username" autocomplete="username" placeholder="Enter your username"
              v-model.trim="username" @blur="validateUsername">
            <p v-if="validateUsernameInput === 'invalid'">Invalid username</p>
            <p v-if="checkingUsernameInput">username already existed</p>
          </div>

        </div>

        <div class="column">

          <div class="input-box">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" autocomplete="email" placeholder="Enter your email"
              v-model.trim="email" @blur="validateEmail">
            <p v-if="validateEmailInput === 'invalid'"> Invalid email address </p>
            <p v-if="checkingemailInput">email already existed</p>
          </div>

          <div class="input-box">
            <label for="phone">Phone Number</label>
            <input type="tel" id="phone" name="phone" autocomplete="phone" placeholder="Enter your number"
              v-model.trim="phone" maxlength="10" pattern="[0-9]*" @blur="validatePhone">
            <p v-if="validatePhoneInput === 'invalid'">Invalid phone number</p>
            <p v-if="checkingPhoneInput">phone number already existed</p>
          </div>

        </div>

        <div class="column">

          <div class="input-box">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" autocomplete="new-password"
              placeholder="Enter your password" v-model.trim="password" @blur="validatePassword">
            <p v-if="validatePasswordInput === 'invalid'">password should contain 8 characters</p>
          </div>

          <div class="input-box">
            <label for="confirmPassword">Confirm Password</label>
            <input type="password" id="confirmPassword" name="confirmPassword" autocomplete="new-password"
              placeholder="Confirm your password" v-model.trim="confirmPassword" @blur="validateConfirmPassword">
            <p v-if="validateConfirmPasswordInput === 'invalid'">passwords should match</p>
          </div>

        </div>

        <div class="gender-box">
          <h3>Gender</h3>
          <div class="gender-option">
            <div class="gender">
              <input type="radio" id="check-male" name="gender" value="male" v-model="gender" />
              <label for="check-male">Male</label>
            </div>
            <div class="gender">
              <input type="radio" id="check-female" name="gender" value="female" v-model="gender" />
              <label for="check-female">Female</label>
            </div>
            <div class="gender">
              <input type="radio" id="check-other" name="gender" value="others" v-model="gender" />
              <label for="check-other">Prefer not to say</label>
            </div>
          </div>
          <p v-if="validateGenderInput === 'invalid'">please choose option</p>
        </div>

        <div>
          <button>Register</button>
          <p v-if="validateSubmit === 'invalid'">Please check all fields properly</p>
          <p> Already have account <router-link :to="'/login'"> Login here</router-link></p>
        </div>

      </form>
    </section>
  </body>
</template>

<script>

export default {

  data() {
    return {

      name: '',
      username: '',
      email: '',
      phone: '',
      password: '',
      confirmPassword: '',
      gender: '',

      validateNameInput: 'pending',
      validateUsernameInput: 'pending',
      validateEmailInput: 'pending',
      validatePasswordInput: 'pending',
      validateConfirmPasswordInput: 'pending',
      validateGenderInput: 'pending',
      validatePhoneInput: 'pending',
      validateSubmit: 'pending',

      checkingUsernameInput: false,
      checkingemailInput: false,
      checkingPhoneInput: false,


    }
  },
  methods: {
    validategender() {

      this.validateGenderInput = this.gender === '' ? 'invalid' : 'pending';

    },
    validateName() {

      this.validateNameInput = this.name === '' ? 'invalid' : 'valid';

    },
    validateUsername() {

      this.checkingUsernameInput = false;

      this.validateSubmit = this.checkingPhoneInput || this.checkingemailInput ? 'invalid' : 'pending';

      this.validateUsernameInput = this.username === '' ? 'invalid' : 'pending';

    },
    validateEmail() {

      this.checkingemailInput = false;

      this.validateSubmit = this.checkingPhoneInput || this.checkingUsernameInput ? 'invalid' : 'pending';

      this.validateEmailInput = this.email === '' ? 'invalid' : 'pending';

    },
    validatePhone() {

      this.checkingPhoneInput = false;

      this.validateSubmit = this.checkingemailInput || this.checkingUsernameInput ? 'invalid' : 'pending';

      this.phone = this.phone.replace(/\D/g, '');

      this.validatePhoneInput = this.phone.length > 10 ? 'invalid' : this.phone.slice(0, 10);

    },
    validatePassword() {

      this.validatePasswordInput = this.password.length < 8 ? 'invalid' : 'pending';

    },
    validateConfirmPassword() {

      this.validateConfirmPasswordInput = this.password !== this.confirmPassword ? 'invalid' : 'pending';

    },
    async checkUserExists() {

      const checkUserExistsData = {
        email: this.email.toLowerCase(),
        phone: this.phone.toLowerCase(),
        username: this.username.toLowerCase()
      };

      try {
        const response = await fetch('http://localhost:8085/user/checkUser', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(checkUserExistsData)
        });
        const data = await response.json();

        if (data.exists) {

          this.checkingemailInput = data.existingFields.includes('email');

          this.checkingPhoneInput = data.existingFields.includes('phone');

          this.checkingUsernameInput = data.existingFields.includes('username');

          this.validateSubmit = 'invalid';

          return;

        }


        else {

          this.checkingUsernameInput = false;
          this.checkingPhoneInput = false;
          this.checkingemailInput = false;

          this.validateSubmit = 'pending';

          this.submitForm();
        }
      } catch (error) {
        console.error('Error while fetching:', error.message);
      }
    },
    async submitDetails() {
      this.validategender();
      this.validateName();
      this.validateUsername();
      this.validateEmail();
      this.validatePhone();
      this.validatePassword();
      this.validateConfirmPassword();

      if (this.validateNameInput === 'invalid' ||
        this.validateUsernameInput === 'invalid' ||
        this.validateEmailInput === 'invalid' ||
        this.validatePasswordInput === 'invalid' ||
        this.validateConfirmPasswordInput === 'invalid' ||
        this.validateGenderInput === 'invalid' ||
        this.validatePhoneInput === 'invalid') {
        this.validateSubmit = 'invalid';
        return;
      }
      this.checkUserExists();
    },
    async submitForm() {
      const formData = {
        name: this.name.toLowerCase(),
        username: this.username.toLowerCase(),
        email: this.email.toLowerCase(),
        phone: this.phone.toLowerCase(),
        password: this.password.toLowerCase(),
        gender: this.gender.toLowerCase()
      };
      try {
        const response = await fetch('http://localhost:8085/user/saveUser', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(formData)

        });
        const data = await response.json();
        if (data.status)
          this.$router.push('/successfullyRegistered');
        else
          this.$router.push('/failure');
      } catch (error) {
        console.error('Error while fetching:', error.message);
      }
    },

  }
}
</script>

<style src="../styles/style.css" scoped></style>