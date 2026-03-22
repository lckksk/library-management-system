import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { path: '/', name: 'Home', component: () => import('../views/Home.vue') },
  { path: '/login', name: 'Login', component: () => import('../views/Login.vue') },
  { path: '/register', name: 'Register', component: () => import('../views/Register.vue') },
  { path: '/books/:id', name: 'BookDetail', component: () => import('../views/BookDetail.vue') },
  { path: '/my/borrows', name: 'MyBorrows', component: () => import('../views/MyBorrows.vue'), meta: { requiresAuth: true } },
  { path: '/profile', name: 'Profile', component: () => import('../views/Profile.vue'), meta: { requiresAuth: true } },
  { path: '/admin', name: 'Admin', component: () => import('../views/admin/Dashboard.vue'), meta: { requiresAuth: true, requiresAdmin: true } },
  { path: '/admin/books', name: 'BookManage', component: () => import('../views/admin/BookManage.vue'), meta: { requiresAuth: true, requiresAdmin: true } },
  { path: '/admin/users', name: 'UserManage', component: () => import('../views/admin/UserManage.vue'), meta: { requiresAuth: true, requiresAdmin: true } },
  { path: '/admin/borrows', name: 'BorrowManage', component: () => import('../views/admin/BorrowManage.vue'), meta: { requiresAuth: true, requiresAdmin: true } },
  { path: '/admin/statistics', name: 'Statistics', component: () => import('../views/admin/Statistics.vue'), meta: { requiresAuth: true, requiresAdmin: true } }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  const user = JSON.parse(localStorage.getItem('user') || '{}')
  
  if (to.meta.requiresAuth && !token) {
    next('/login')
  } else if (to.meta.requiresAdmin && user.role !== 'ADMIN') {
    next('/')
  } else {
    next()
  }
})

export default router
