<template>
  <body>
    <section class="container">
    <header><span>En</span>ter Email</header>
    <form @submit.prevent="submitDetails" class="form">

    <div class="column">

      <div class="input-box" >
        <label for="email">Enter registered email</label>
        <input type="text" id="email" name="email" autocomplete="email" placeholder="Enter your email" v-model.trim="email" @blur="validateUsername">
        <p v-if="validateEmailInput === 'invalid'">Invalid username</p>
        <p v-if="checkingUser">User doesn't Exist</p>
      </div>

    </div>

    <div>
      <button>Submit</button>
      <p  v-if="validateEmailInput === 'invalid'  || validateSubmit === 'invalid' ">Please check all fields properly</p>

    </div>
      
    </form>
  </section>
  </body>
</template>

<script>

export default {

 data () {

  return{

    email:'',

    validateEmailInput:'pending',

    validateSubmit:'pending',

    checkingUser:false,

    sendToken:'',

    sendEmail:''

  }

 },

 methods:{

  validateUsername(){

    this.validateEmailInput = this.email === '' ? 'invalid' : 'pending';


},

submitDetails(){

this.validateUsername();

if(this.validateEmailInput === 'invalid' ) {

  this.validateSubmit = 'invalid';

return;

}
else{

  this.validateSubmit = 'pending';
  this.checkUserExist();

}

},

async checkUserExist(){

  const checkUserExistsData = {
    email: this.email.toLowerCase()
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

      this.validateEmailInput = data.existingFields.includes('email');

      this.checkingUser = false;

      this.validateSubmit = 'pending';

      this.submitForm();

     } 
     else {

      this.checkingUser = true;
        
      this.validateSubmit = 'invalid';
        
       return;
     }
   } catch (error) {
       console.error('Error while fetching:', error.message);
   }

},

async submitForm () {

  const checkUserExistsData = {
    email: this.email.toLowerCase()
        };

        try {
    const response = await fetch('http://localhost:8085/user/authenticateToken', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(checkUserExistsData)
    });
    const data = await response.json();

    if (data) {
      const token = data.token;
      
      this.sendToken = token;

      this.sendEmail = checkUserExistsData.email;
      
      alert("The password reset link sent to your mail check your mail and the page is redirecting to the login page");

      this.$router.push('/login'); 
    } 
  } catch (error) {
    console.error('Error while fetching:', error.message);
  }

  this.sendCredentials();

},
async sendCredentials(){

  const checkUserExistsData = {
    email: this.sendEmail.toLowerCase(),
    token: this.sendToken
  }

        try {
    const response = await fetch('http://localhost:8085/user/saveEmailToken', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(checkUserExistsData)
    });
    const data = await response.json();
    if (data.status) {
      
      this.sendToken = '';

      this.sendEmail = '';
      
    } 
  } catch (error) {
    console.error('Error while fetching:', error.message);
  }

}

 }

}

</script>

<style src="../styles/style.css" scoped>

</style>