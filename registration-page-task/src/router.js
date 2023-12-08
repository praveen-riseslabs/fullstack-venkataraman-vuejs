import { createRouter, createWebHistory } from 'vue-router';
import SuccessSubmission from './components/SuccessSubmission.vue';


const routes = [
    { path: '/users', component: SuccessSubmission }
  ];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
