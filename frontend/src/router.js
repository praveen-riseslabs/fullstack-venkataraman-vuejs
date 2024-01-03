import { createRouter, createWebHistory } from 'vue-router';
import SuccessSubmission from './components/SuccessSubmission.vue';
import RegistrationPage from './components/RegistrationPage.vue';
import LoginPage from './components/LoginPage.vue';
import FailureSubmission from './components/FailureSubmission.vue';
import RegisteredUserSucess from './components/RegisteredUserSucess.vue';
import ForgotPassword from './components/ForgotPassword.vue';
import ResetPassword from './components/ResetPassword.vue';
import DashboardPage from './components/DashboardPage.vue';
import MainbarPage from './components/UIcomponents/MainbarPage.vue';
import TodayPage from './components/Alldays/TodayPage.vue';
import OneWeek from './components/Alldays/OneWeek.vue';
import OneYear from './components/Alldays/OneYear.vue';
import OneMonth from './components/Alldays/OneMonth.vue';

const routes = [
  { path: '/failure', component: FailureSubmission },
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
  { path: '/dashboard', component: DashboardPage },
  {
    path: '/mainbar', component: MainbarPage, children: [
      {
        path: '/success',
        name: 'SuccessPage',
        component: SuccessSubmission,
        props: true
      },
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
