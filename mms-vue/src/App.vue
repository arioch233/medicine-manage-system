<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import {generateMenu} from "@/assets/js/router/menu";

export default {
  created() {
    console.log("Created");
    const json = {
      "flag": true,
      "code": 200,
      "message": "操作成功",
      "data": [
        {
          "name": null,
          "path": "/",
          "component": "Layout",
          "icon": null,
          "hidden": false,
          "children": [
            {
              "name": "首页",
              "path": "/index",
              "component": "/home/HomeView.vue",
              "icon": "icon-shouye-shouye",
              "hidden": null,
              "children": null
            }
          ]
        },
        {
          "name": "权限管理",
          "path": "/permission-submenu",
          "component": "Layout",
          "icon": "icon-quanxian",
          "hidden": false,
          "children": [
            {
              "name": "菜单管理",
              "path": "/menu",
              "component": "/permission/MenuView.vue",
              "icon": "icon-caidan1",
              "hidden": false,
              "children": null
            },
            {
              "name": "接口管理",
              "path": "/resources",
              "component": "/permission/ResourceView.vue",
              "icon": "icon-API",
              "hidden": false,
              "children": null
            },
            {
              "name": "角色管理",
              "path": "/roles",
              "component": "/permission/RoleView.vue",
              "icon": "icon-role",
              "hidden": false,
              "children": null
            }
          ]
        },
        {
          "name": "文件管理",
          "path": "/image-submenu",
          "component": "Layout",
          "icon": "icon-wenjian",
          "hidden": false,
          "children": [
            {
              "name": "图片管理",
              "path": "/file",
              "component": "/file/FileImageView.vue",
              "icon": "icon-tupian",
              "hidden": false,
              "children": null
            },
          ]
        },
        {
          "name": "系统管理",
          "path": "/system-submenu",
          "component": "Layout",
          "icon": "icon-xitong",
          "hidden": false,
          "children": [
            {
              "name": "网站管理",
              "path": "/admin/website",
              "component": "/website/WebsiteView.vue",
              "icon": "icon-website",
              "hidden": false,
              "children": null
            },
          ]
        },
      ]
    };

    // generateMenu(json);
    this.request.get("/menu/user").then(data => {
      if (data.code === 200) {
        generateMenu(data);
      }
    })
  }
}

</script>
<style>

</style>
