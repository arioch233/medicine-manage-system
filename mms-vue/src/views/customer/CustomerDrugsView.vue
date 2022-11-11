<template>
  <el-card class="main-card">
    <!-- 标题 -->
    <div class="title">{{ this.$route.name }}</div>
    <!-- 表格操作 -->
    <div class="operation-container">
      <!-- 条件筛选 -->
      <div style="margin-left:auto">
        <el-input
            v-model="keywords"
            prefix-icon="el-icon-search"
            size="small"
            placeholder="请输入药品名"
            style="width:200px"
            @keyup.enter.native="searchDrugs"
        />
        <el-button
            type="primary"
            size="small"
            icon="el-icon-search"
            style="margin-left:1rem"
            @click="searchDrugs"
        >
          搜索
        </el-button>
      </div>
    </div>
    <!-- 表格展示 -->
    <el-table
        border
        :data="drugList"
        @selection-change="selectionChange"
        v-loading="loading"
    >
      <!-- 表格列 -->
      <el-table-column label="药品封图" align="center" width="200">
        <template slot-scope="scope">
          <el-image
              style="width: 180px; height: 100px"
              :src="scope.row.drugCover"
              fit="contain">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="drugTradeName" width="150" label="药品商品名" align="center"/>
      <el-table-column prop="drugCategory" width="150" label="药品分类" align="center"/>
      <el-table-column prop="drugForm" width="150" label="药品剂型" align="center"/>
      <el-table-column prop="drugSpecification" width="150" label="药品规格" align="center"/>
      <el-table-column prop="drugPackagingMaterial" width="150" label="药品包装材质" align="center"/>
      <el-table-column prop="drugEffect" width="150" label="药品功效" align="center"/>
      <el-table-column prop="drugManufacturer" width="150" label="药品厂商" align="center"/>
      <el-table-column prop="drugPrice" width="150" label="药品单价" align="center"/>
    </el-table>
    <!-- 分页 -->
    <el-pagination
        class="pagination-container"
        background
        @size-change="sizeChange"
        @current-change="currentChange"
        :current-page="current"
        :page-size="size"
        :total="count"
        :page-sizes="[10, 20]"
        layout="total, sizes, prev, pager, next, jumper"
    />
  </el-card>
</template>
<script>
import * as imageConversion from "image-conversion";

export default {
  name: "CustomerDrugsView",
  created() {
    this.listDrug();
  },
  data() {
    return {
      tabPosition: "left",
      loading: true,
      isDelete: false,
      keywords: null,
      current: 1,
      size: 10,
      count: 0,
      drugIdList: [],
      drugList: [],
      drugModel: false,
      drugForm: {
        drugNum: "",
        drugCommonName: "",
        drugTradeName: "",
        drugForm: "",
        drugSpecification: "",
        drugPackagingMaterial: "",
        drugManufacturer: "",
        drugEffect: "",
        drugQuantity: "0",
        drugPrice: "",
        drugCover: "",
        drugCategory: ""
      },
      drugCategoryOptions: []
    }
  },
  methods: {
    searchDrugs() {
      this.current = 1;
      this.listDrug();
    },
    sizeChange(size) {
      this.size = size;
      this.listDrug();
    },
    currentChange(current) {
      this.current = current;
      this.listDrug();
    },
    listDrug() {
      this.request.get("/drug/all", {
        params: {
          current: this.current,
          size: this.size,
          keywords: this.keywords,
        }
      }).then(data => {
        this.drugList = data.data.records;
        this.count = data.data.count;
        this.loading = false;
      })
    },

  }
}
</script>

<style scoped>
.upload-drug-img {
  text-align: center;
}
</style>