import SignUp from "@/views/SignUp";
import HomepageView from "@/views/HomepageView";
import Vue from "vue";
import Router from "vue-router";

Vue.use(Router)

const routes = [
    {
        name: "LoginView",
        path: "/",
        component: () => import('./views/LoginView'),
    },
    {
        name: "SignUp",
        path: "/kayıt-ol",
        component: SignUp,
    },
    {
        name: "Homepage",
        path: "/anasayfa",
        component: HomepageView,
    },
];

const router = new Router({
    base: '/',
    routes: routes
})

export default router