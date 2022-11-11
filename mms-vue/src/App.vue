<template>
  <div id="app">
    <router-view/>

  </div>

</template>

<script>
import {generateMenu} from "@/assets/js/router/menu";

export default {

  created() {
    // 刷新页面查询用户菜单
    if (this.$store.state.userId != null) {
      generateMenu();
    }
    this.request.get("/config/website").then(data => {
      this.$store.state.webSite = data.data;
    })
  },
}

</script>
<style>

</style>
