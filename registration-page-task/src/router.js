import { createRouter, createWebHistory } from 'vue-router';
import SuccessSubmission from './components/SuccessSubmission.vue';
import RegistrationPage from './components/RegistrationPage.vue';
import LoginPage from './components/LoginPage.vue';
import failureSubmission from './components/failureSubmission.vue';
import RegisteredUserSucess from './components/RegisteredUserSucess.vue';
import ForgotPassword from './components/ForgotPassword.vue';
import ResetPassword from './components/ResetPassword.vue';
const routes = [
  {
    path: '/success/:token', // Define a parameter in the route
    name: 'success',
    component: SuccessSubmission,
    props: true // To pass the token as a prop to the SuccessPage component
  },
    { path: '/failure', component: failureSubmission },
    { path: '/registrationPage', component: RegistrationPage },
    { path: '/login', component: LoginPage },
    { path:'/successfullyRegistered', component: RegisteredUserSucess},
    { path:'/forgotpassword', component: ForgotPassword },
    {
      path: '/resetPassword',
      name: 'resetPassword',
      component: ResetPassword,
      props: route => ({ Token: route.query.token,
                          email: route.query.email }) // Access token from the query parameters
    }
  ];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
