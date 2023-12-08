<template>
<body>
  <section class="container">
    <header><span>Re</span>gistration</header>
    <form @submit.prevent="submitDetails" class="form">

      <div class="column">

        <div class="input-box" >
        <label for="name">Full Name</label>
        <input type="text" id="name" name="name" autocomplete="name" placeholder="Enter your name" v-model.trim="name" @blur="validateName">
        <p v-if="validateNameInput === 'invalid' ">Invalid name</p>
      </div>

      <div class="input-box" >
        <label for="username">User Name</label>
        <input type="text" id="username" name="username" autocomplete="username" placeholder="Enter your username" v-model.trim="username" @blur="validateUsername">
        <p v-if="validateUsernameInput === 'invalid'">Invalid username</p>
        <p v-if="checkingUsernameInput">username already existed</p>
      </div>

      </div>

      <div class="column">

        <div class="input-box" >
          <label for="email">Email</label>
          <input type="email" id="email" name="email" autocomplete="email" placeholder="Enter your email" v-model.trim="email" @blur="validateEmail">
          <p v-if="validateEmailInput === 'invalid'"> Invalid email address </p>
        <p v-if="checkingemailInput">email already existed</p>
        </div>

        <div class="input-box" >
          <label for="phone">Phone Number</label>
          <input type="tel" id="phone" name="phone" autocomplete="phone" placeholder="Enter your number" v-model.trim="phone" maxlength="10" pattern="[0-9]*" @blur="validatePhone">
          <p v-if="validatePhoneInput === 'invalid'">Invalid phone number</p>
          <p v-if="checkingPhoneInput">phone number already existed</p>
        </div>

      </div>

      <div class="column">

          <div class="input-box" >
            <label for="password">Password</label>
           <input type="password" id="password" name="password" autocomplete="new-password" placeholder="Enter your password" v-model.trim="password" @blur="validatePassword">
           <p v-if="validatePasswordInput === 'invalid'">password should contain 8 characters</p>
          </div>

          <div class="input-box">
            <label for="confirmPassword">Confirm Password</label>
           <input type="password" id="confirmPassword" name="confirmPassword" autocomplete="new-password" placeholder="Confirm your password" v-model.trim="confirmPassword" @blur="validateConfirmPassword">
           <p v-if="validateConfirmPasswordInput === 'invalid'">passwords should match</p>
          </div>

      </div>

      <div class="gender-box">
        <h3>Gender</h3>
          <div class="gender-option">
              <div class="gender">
              <input type="radio" id="check-male" name="gender" value="male" v-model="gender"/>
              <label for="check-male">Male</label>
            </div>
            <div class="gender">
              <input type="radio" id="check-female" name="gender" value="female" v-model="gender"/>
              <label for="check-female">Female</label>
            </div>
            <div class="gender">
              <input type="radio" id="check-other" name="gender" value="others" v-model="gender"/>
              <label for="check-other">Prefer not to say</label>
            </div>
        </div>
        <p v-if="validateGenderInput === 'invalid'">please choose option</p>
    </div>

    <div>
      <button>Register</button>
      <p  v-if="validateSubmit === 'invalid'">Please check all fields properly</p>
    </div>
      
    </form>
  </section>
</body>
</template>

<script>
export default {
data (){
  return{
   
    name:'',
    username:'',
    email:'',
    phone:'',
    password:'',
    confirmPassword:'',
    gender:'',

    validateNameInput:'pending',
    validateUsernameInput:'pending',
    validateEmailInput:'pending',
    validatePasswordInput:'pending',
    validateConfirmPasswordInput:'pending',
    validateGenderInput:'pending',
    validatePhoneInput:'pending',
    validateSubmit:'pending',

    checkingUsernameInput:false,
    checkingemailInput:false,
    checkingPhoneInput:false,


  }
},
methods:{
  validategender(){
    if(this.gender === '')
    this.validateGenderInput='invalid';
  else
  this.validateGenderInput = 'pending';
  },
  validateName(){
        if(this.name === '')
        this.validateNameInput = 'invalid';
        else
        this.validateNameInput = 'valid';
  },
  validateUsername(){
    if(this.username === '')
    this.validateUsernameInput = 'invalid';
    else
    this.checkingUsername();
  },
  async checkingUsername(){
    try {
    const response = await fetch(`http://localhost:8085/user/username/${this.username}`);
      const data = await response.json();
    if(data)
    this.checkingUsernameInput = true;
    else
    this.checkingUsernameInput = false;

    } catch (error) {
    console.error('Error while fetching:', error.message);
  }

   
  },
  validateEmail(){
    if(this.email === '')
    this.validateEmailInput = 'invalid';
    else
    this.checkingemail();  
  },
  async checkingemail(){
    try {
    const response = await fetch(`http://localhost:8085/user/mail/${this.email}`);
    const data = await response.json();
    if(data)
      this.checkingemailInput = true;
    else
    this.checkingemailInput = false;
    } catch (error) {
    console.error('Error while fetching:', error.message);
  }
  },
  validatePhone(){
    this.phone = this.phone.replace(/\D/g, '');
      if (this.phone.length > 10) {
        this.phone = this.phone.slice(0, 10);
      }
      else if(this.phone.length < 10){
        this.validatePhoneInput = 'invalid';
      }
      else{
        this.checkingPhone();
      }
  },
 async checkingPhone(){
  try {
    const response = await fetch(`http://localhost:8085/user/phone/${this.phone}`);

    const data = await response.json();
    if(data)
      this.checkingPhoneInput = true;
    else
    this.checkingPhoneInput = false;
    } catch (error) {
    console.error('Error while fetching:', error.message);
  }
  },
  validatePassword(){
    if(this.password.length < 8)
    this.validatePasswordInput = 'invalid';
  else
  this.validatePasswordInput = 'pending';

  },
  validateConfirmPassword(){
    if(this.password != this.confirmPassword)
    this.validateConfirmPasswordInput = 'invalid';
    else
    this.validateConfirmPasswordInput = 'pending';
    },
    async submitDetails(){
      this.validategender();
      this.validateName();
      this.validateUsername();
      this.validateEmail();
      this.validatePhone();
      this.validatePassword();
      this.validateConfirmPassword();

      await this.checkingUsername();
      await this.checkingemail();
      await this.checkingPhone();

    if(this.validateNameInput === 'invalid' ||
    this.validateUsernameInput === 'invalid' ||
    this.validateEmailInput === 'invalid' ||
    this.validatePasswordInput === 'invalid' ||
    this.validateConfirmPasswordInput === 'invalid' ||
    this.validateGenderInput === 'invalid' ||
    this.validatePhoneInput === 'invalid' ||
    this.checkingUsernameInput ||
    this.checkingemailInput ||
    this.checkingPhoneInput)
    {
      this.validateSubmit = 'invalid';
      return;
    }
        this.submitForm();
    },
 async  submitForm() {
  const formData = {
    name: this.name,
    username: this.username,
    email: this.email,
    phone: this.phone,
    password: this.password,
    gender: this.gender
  };

  try {
    const response = await fetch('http://localhost:8085/user', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(formData)
    });
     const data = await response.json();
    if(data)
      this.callRouter();
  } catch (error) {
    console.error('Error while fetching:', error.message);
  }
},
callRouter(){
  this.$router.push('/users');  
}


}
}
</script>

<style scoped>
*{
  font-family: "Poppins", sans-serif;
  box-sizing: border-box;
  padding: 0;
  margin: 0 ;
}

body{
  min-height: 100vh;
  display:flex;
  align-items:center;
  justify-content: center;
  background: linear-gradient( to right, #74B2E1 , #9A5BB4);
  padding: 20px;
}

.container{
background: #fff;
max-width: 700px;
width:100%;
position: relative;
padding: 25px;
border-radius: 8px;
box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.container header{
 font-size:1.5rem;
 color: #333;
 font-weight: 500;
}

.container header span{
  text-decoration: underline;
  text-decoration-color: rgb(130,106,251);
}

.container .form{
  margin-top:30px;
}

.container .input-box{
  width:100%;
  margin-top:20px;
}

.input-box label{
  font-weight: bold;
  color: #333;
}

.form .input-box input{
position: relative;
height:50px;
width: 100%;
font-size: 1rem;
color: #707070;
margin-top: 8px;
outline: none;
border: 1px solid #707070;
border-radius: 6px;
padding:0 15px;
box-shadow: 2px 2px 4px 2px rgba(0, 0, 0, 0.05);
}

.form .input-box{
  width:100%;
  margin-top: 20px;
}

.form .column{
  display:flex;
  column-gap: 1rem;

}
.form .column .input-box p{
  color: red;
}

.form .gender-box p{
  color: red;
}

.form :where(.gender-option, .gender){
  display: flex;
  align-items:center;
  column-gap: 50px;
  flex-wrap: wrap;
}

.form .gender{
  column-gap: 5px;
}

.gender-box h3{
  color: #333;
  font-size: 1rem;
  font-weight: 400px;
  margin-bottom: 8px;
}

.form :where(.gender input, .gender label) {
 cursor:pointer;
}

.form .gender-box{
  margin-top: 20px;
}

@media screen and (max-width:500px){
  .form .column{
    flex-wrap: wrap;
  }
}

.form button{
  height: 55px;
  width: 100%;
  color: #fff;
  font-size: 1rem;
  border: none;
  margin-top: 30px;
  cursor: pointer;
  border-radius: 6px;
  font-weight: 400px;
  transition: all 0.2s ease;
  background:linear-gradient( to right, #74B2E1 , #9A5BB4);;
  }

  .form button:hover{
    background-color: rgb(130, 106, 251);
  }
</style>