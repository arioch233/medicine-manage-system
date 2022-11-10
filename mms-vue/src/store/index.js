import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        collapse: false,    // 侧边栏隐藏按钮
        tabList: [
            {
                name: '首页',
                path: '/',
            },
        ],                  // 面包屑
        userId: null,       // 用户id
        roleList: null,     // 角色列表
        avatar: null,    // 头像地址
        nickname: null,     // 昵称
        intro: null,     // 用户简介
        webSite: null,      //  站点信息
        userMenuList: [],   // 用户菜单列表
    },
    getters: {},
    mutations: {
        /**
         *  侧边栏隐藏文字按钮
         */
        trigger(state) { // 隐藏按钮点击触发
            state.collapse = !state.collapse; // 翻转
        },
        /**
         * 面包屑处理
         */
        saveTab(state, tab) { // 保存面包屑标签
            if (state.tabList.findIndex(item => item.path === tab.path) === -1) {
                // console.log(tab)
                state.tabList.push({
                    name: tab.name,
                    path: tab.path,
                })
            }
        },
        removeTab(state, tab) { // 移除面包屑标签
            let index = state.tabList.findIndex(item => item.name === tab.name);
            state.tabList.splice(index, 1);
        },
        resetTab(state) { // 重置面包屑
            state.tabList = [
                {
                    name: '首页',
                    path: '/',
                },
            ];
        },
        /**
         *  登录
         */
        login(state, user) { // 将相关数据储存
            state.userId = user.userInfoId;
            state.roleList = user.roleList;
            state.avatar = user.avatar;
            state.nickname = user.nickname;
            state.intro = user.intro;
            state.webSite = user.webSite;
        },
        /**
         * 登出
         */
        logout(state) { // 将相关状态移除
            state.userId = null;
            state.roleList = null;
            state.avatar = null;
            state.nickname = null;
            state.intro = null;
            state.webSite = null;
            state.userMenuList = [];
        },
        /**
         *  保存用户菜单列表
         */
        saveUserMenuList(state, userMenuList) { // 保存用户菜单
            state.userMenuList = userMenuList;
        },
        /**
         *  更新头像
         */
        updateUserAvatar(state, avatar) {
            console.log(avatar)
            state.avatar = avatar;
        },
        /**
         * 更新用户信息
         */
        updateUserInfo(state, user) {
            state.nickname = user.nickname;
            state.intro = user.intro;
            state.webSite = user.webSite;
        },

    },
    actions: {},
    modules: {},
    plugins: [
        createPersistedState({
            storage: window.sessionStorage
        })
    ]
})
