import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'index',
        component: () => import('../views/BaseView'),
        redirect: '/index',
    },
]


const createRouter = () =>
    new VueRouter({
        mode: "history",
        routes: routes
    });

const router = createRouter();

export const resetRouter = () => {
    const newRouter = createRouter();
    router.matcher = newRouter.matcher;
}

export default router
