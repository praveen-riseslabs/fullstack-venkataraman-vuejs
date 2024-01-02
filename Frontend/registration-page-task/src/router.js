import { createRouter, createWebHistory } from 'vue-router';
import SuccessSubmission from './components/SuccessSubmission.vue';
import RegistrationPage from './components/RegistrationPage.vue';
import LoginPage from './components/LoginPage.vue';
import failureSubmission from './components/failureSubmission.vue';
import RegisteredUserSucess from './components/RegisteredUserSucess.vue';
import ForgotPassword from './components/ForgotPassword.vue';
import ResetPassword from './components/ResetPassword.vue';
// import DashboardPage from './components/DashboardPage.vue';
import MainbarPage from './components/UIcomponents/MainbarPage.vue';
import TodayPage from './components/Alldays/TodayPage.vue';
import OneWeek from './components/Alldays/OneWeek.vue';
import OneYear from './components/Alldays/OneYear.vue';
import OneMonth from './components/Alldays/OneMonth.vue';

// import SidebarPage from './components/UIcomponents/SidebarPage.vue';

const routes = [

  {
    path: '/success',//:token/:userid',  Define a parameter in the route
    name: 'SuccessPage',
    component: SuccessSubmission,
    props: true // To pass the token as a prop to the SuccessPage component
  },
  { path: '/failure', component: failureSubmission },
  { path: '/registrationPage', component: RegistrationPage },
  { path: '/login', component: LoginPage },
  { path: '/successfullyRegistered', component: RegisteredUserSucess },
  { path: '/forgotpassword', component: ForgotPassword },
  {
    path: '/resetPassword',
    name: 'resetPassword',
    component: ResetPassword,
    props: route => ({
      Token: route.query.token,
      email: route.query.email
    })
  },

  {
    path: '/mainbar', component: MainbarPage, children: [
      // { path: '/dashboard', component: DashboardPage },
      { path: '/today', component: TodayPage },
      { path: '/oneweek', component: OneWeek },
      { path: '/oneyear', component: OneYear },
      { path: '/onemonth', component: OneMonth }
    ]
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
