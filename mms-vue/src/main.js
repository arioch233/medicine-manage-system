import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// vue2-animate
import 'vue2-animate/dist/vue2-animate.min.css'

// ElementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// NProgress
import NProgress from "nprogress";

// dayjs
import dayjs from "dayjs";

// vue-echarts
import ECharts from "vue-echarts";
import "echarts/lib/chart/line";
import "echarts/lib/chart/pie";
import "echarts/lib/chart/bar";
import "echarts/lib/chart/map";
import "echarts/lib/component/tooltip";
import "echarts/lib/component/legend";
import "echarts/lib/component/title";

// 自定义CSS
import './assets/css/index.css'
import './assets/css/iconfont/iconfont.css'
// 自定义js
import request from "@/util/request";
import config from "@/assets/js/config";
// config
Vue.prototype.config = config;
Vue.prototype.request = request
Vue.prototype.$moment = dayjs;
Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.component("v-chart", ECharts);

Vue.filter("date", function (value, formatStr = "YYYY-MM-DD") {
    return dayjs(value).format(formatStr);
});

Vue.filter("dateTime", function (value, formatStr = "YYYY-MM-DD HH:mm:ss") {
    return dayjs(value).format(formatStr);
});

NProgress.configure({
    easing: "ease", // 动画方式
    speed: 500, // 递增进度条的速度
    showSpinner: false, // 是否显示加载ico
    trickleSpeed: 200, // 自动递增间隔
    minimum: 0.3 // 初始化时的最小百分比
});

router.beforeEach((to, from, next) => {
    NProgress.start();
    next();

});

router.afterEach(() => {
    NProgress.done();
});

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
