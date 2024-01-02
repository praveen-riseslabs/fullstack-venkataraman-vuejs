<template>
  <body>
    <section class="container">
      <header><span>Ac</span>tivity</header>
      <form @submit.prevent="submitDetails" class="form">

        <div class="column">

          <div class="input-box">
            <label for="username">Activity Tiltle</label>
            <input type="text" id="username" name="username" autocomplete="username" placeholder="Task title"
              v-model.trim="ActivityTitle" @blur="validateTitle">
            <p v-if="validateTitleInput === 'invalid'">Invalid Title</p>
          </div>

        </div>

        <div class="column">

          <div class="input-box">
            <label for="password">Activity description</label>
            <textarea type="password" id="password" name="password" autocomplete="new-password" placeholder="Description"
              v-model.trim="ActivityDescription" @blur="validateDescription" />
            <p v-if="validateDescriptionInput === 'invalid'">Description shouldn't be empty</p>
          </div>

        </div>

        <div class="column">

          <div class="input-box">
            <label for="start-time">Start time: </label>
            <input type="time" id="start-time" name="start-time" v-model="startTime" @blur="validateStartTime">
            <p v-if="validateStartInput === 'invalid'">Start time can't be empty</p>
          </div>

          <div class="input-box">
            <label for="meeting-time">End time: </label>
            <input type="time" id="meeting-time" name="meeting-time" v-model="endTime" @blur="validateEndTime">
            <p v-if="validateEndInput === 'invalid'">End time can't be empty</p>
          </div>

        </div>

        <div>
          <button>Add activity</button>
          <p v-if="validateAddActivity === 'invalid'">Please check all fields properly</p>
        </div>

      </form>
    </section>
  </body>
</template>

<script>
export default {
  data() {
    return {
      ActivityTitle: '',
      ActivityDescription: '',
      startTime: '',
      endTime: '',
      id:'',

      validateTitleInput: 'pending',
      validateDescriptionInput: 'pending',
      validateAddActivity: 'pending',
      validateEndInput:'pending',
      validateStartInput:'pending'
    }
  },
  created() {
    // Retrieve route parameters (activity details)
    const { title, description, startTimeString, endTimeString ,id } = this.$route.query;

    this.ActivityTitle = title;
    this.ActivityDescription = description;
    this.startTime = startTimeString;
    this.endTime = endTimeString;
    this.id = id;
     },
  methods: {
    validateTitle() {
      if (this.ActivityTitle === '')
        this.validateTitleInput = 'invalid'
      else
        this.validateTitleInput = 'pending'
    },
    validateDescription() {
      if (this.ActivityDescription === '')
        this.validateDescriptionInput = 'invalid'
      else
        this.validateDescriptionInput = 'pending'
    },
    validateStartTime() {
      if (this.startTime === '') 
        this.validateStartInput = 'invalid';
      else 
        this.validateStartInput = 'valid';
    },
    validateEndTime() {
      if (this.endTime === '') 
        this.validateEndInput = 'invalid';
       else 
        this.validateEndInput = 'valid';
    },
    submitDetails(){

      this.validateTitle();
      this.validateDescription();
      this.validateStartTime();
      this.validateEndTime();

      if( this.validateEndInput === 'invalid' ||
       this.validateStartInput === 'invalid' ||
        this.validateDescriptionInput === 'invalid' ||
       this.validateTitleInput === 'invalid')
       {
        this.validateAddActivity === 'invalid';
        return;
       }
       this.submitForm();

    },

    async submitForm() {

      const updatedData = {
        title: this.ActivityTitle.toLowerCase(),
        desccription: this.ActivityDescription.toLowerCase(),
        startTimeString:this.startTime,
        endTimeString:this.endTime,
        id : this.id,
        userid:this.$store.getters.finalUserid
      };

      const loginCredentials = {
        title: this.ActivityTitle.toLowerCase(),
        desccription: this.ActivityDescription.toLowerCase(),
        startTimeString:this.startTime,
        endTimeString:this.endTime,
        userid:this.$store.getters.finalUserid
      };

      try {
        let response;
        if(this.id){
           response = await fetch('http://localhost:8085/user/mainController/updateActivity', {
          method: 'PUT',
          headers: {
            'Authorization': `Bearer ${this.$store.getters.finalToken}`,
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(updatedData)
        });
        }

        else{
         response = await fetch('http://localhost:8085/user/mainController/addActivity', {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${this.$store.getters.finalToken}`,
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(loginCredentials)
        });
      }
        const data = await response.json();

        if (data.status) {
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

<script></script>
<style src="../styles/style.css" scoped></style>