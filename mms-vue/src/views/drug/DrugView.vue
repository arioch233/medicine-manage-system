<template>
  <el-card class="main-card">
    <!-- 标题 -->
    <div class="title">{{ this.$route.name }}</div>
    <!-- 表格操作 -->
    <div class="operation-container">
      <el-button
          type="primary"
          size="small"
          icon="el-icon-plus"
          @click="openMenuModel(null)"
      >
        新增
      </el-button>
      <el-button
          type="danger"
          size="small"
          icon="el-icon-delete"
          :disabled="this.drugIdList.length === 0"
          @click="isDelete = true"
      >
        批量删除
      </el-button>
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
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="drugNum" width="150" label="药品编号" fixed="left" align="center"/>
      <el-table-column prop="drugCategoryName" width="150" label="药品名称" align="center"/>
      <el-table-column prop="drugCategoryName" width="150" label="药品名称" align="center"/>
      <el-table-column prop="drugCategoryName" width="150" label="药品名称" align="center"/>
      <el-table-column prop="drugCategoryName" width="150" label="药品名称" align="center"/>
      <el-table-column
          prop="createTime"
          label="创建时间"
          width="200"
          align="center"
      >
        <template slot-scope="scope">
          <i class="el-icon-time" style="margin-right:5px"/>
          {{ scope.row.createTime | dateTime }}
        </template>
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="修改时间"
          width="200"
          align="center"
      >
        <template slot-scope="scope">
          <i class="el-icon-time" style="margin-right:5px"/>
          {{ scope.row.updateTime | dateTime }}
        </template>
      </el-table-column>
      <!-- 列操作 -->
      <el-table-column label="操作" align="center" width="220" fixed="right">
        <template slot-scope="scope">
          <el-button type="text" size="mini" @click="openModel(scope.row)">
            <i class="el-icon-edit"/> 修改
          </el-button>
          <el-popconfirm
              title="确定删除吗？"
              style="margin-left:10px"
              @confirm="deleteCargo(scope.row.id)"
          >
            <el-button size="mini" type="text" slot="reference" style="color: #F56C6C">
              <i class="el-icon-delete"/> 删除
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
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
export default {
  name: "DrugView",
  created() {
    this.listDrug();
  },
  data() {
    return {
      loading: true,
      isDelete: false,
      keywords: null,
      current: 1,
      size: 10,
      count: 0,
      drugIdList: [],
      drugList: []
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
    selectionChange(roleList) {
      this.drugIdList = [];
      roleList.forEach(item => {
        this.drugIdList.push(item.id);
      });
    },
    listDrug() {
      this.loading = false;
    },
    openModel(drug) {

    },
    deleteCargo(id) {

    }
  }
}
</script>

<style scoped>

</style>