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
          @click="openModel(null)"
      >
        新增
      </el-button>
      <el-button
          type="danger"
          size="small"
          icon="el-icon-delete"
          :disabled="this.instrumentCategoryIdList.length === 0"
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
            placeholder="请输入器材分类名称"
            style="width:200px"
            @keyup.enter.native="searchInstrumentCategories"
        />
        <el-button
            type="primary"
            size="small"
            icon="el-icon-search"
            style="margin-left:1rem"
            @click="searchInstrumentCategories"
        >
          搜索
        </el-button>
      </div>
    </div>
    <!-- 表格展示 -->
    <el-table
        border
        :data="instrumentCategoryList"
        @selection-change="selectionChange"
        v-loading="loading"
    >
      <!-- 表格列 -->
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="instrumentCategoryName" label="器材分类名" width="200" align="center"/>
      <el-table-column prop="instrumentCategoryLabel" label="器材分类描述" align="center"/>
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
      <el-table-column label="操作" align="center" width="220">
        <template slot-scope="scope">
          <el-button type="text" size="mini" @click="openModel(scope.row)">
            <i class="el-icon-edit"/> 修改
          </el-button>
          <el-popconfirm
              title="确定删除吗？"
              style="margin-left:10px"
              @confirm="deleteCategory(scope.row.id)"
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
    <!--  分类对话框  -->
    <el-dialog :visible.sync="categoryModel" width="30%">
      <div class="dialog-title-container" slot="title" ref="categoryTitle"/>
      <el-form label-width="80px" size="medium" :model="instrumentForm">
        <el-form-item label="分类名">
          <el-input v-model="instrumentForm.instrumentName" style="width:300px"/>
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" v-model="instrumentForm.instrumentLabel" style="width:300px"/>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="categoryModel = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">
          确 定
        </el-button>
      </div>
    </el-dialog>

  </el-card>
</template>

<script>
export default {
  name: "InstrumentCategoryView",
  created() {
    this.listInstrumentCategories();
  },
  data() {
    return {
      loading: true,
      isDelete: false,
      keywords: null,
      current: 1,
      size: 10,
      count: 0,
      instrumentCategoryIdList: [],
      instrumentCategoryList: [],
      categoryModel: false,
      instrumentForm: {
        instrumentName: "",
        instrumentLabel: "",
      }
    }
  },
  methods: {
    searchInstrumentCategories() {
      this.current = 1;
      this.listInstrumentCategories();
    },
    sizeChange(size) {
      this.size = size;
      this.listInstrumentCategories();
    },
    currentChange(current) {
      this.current = current;
      this.listInstrumentCategories();
    },
    selectionChange(list) {
      this.instrumentCategoryIdList = [];
      list.forEach(item => {
        this.instrumentCategoryIdList.push(item.id);
      });
    },
    listInstrumentCategories() {
      this.instrumentCategoryList = [
        {
          instrumentCategoryName: "消毒",
          instrumentCategoryLabel: "xxxxxxx"
        }
      ];
      this.loading = false;
    },
    openModel(category) {
      this.$refs.categoryTitle.innerHTML = category ? "修改分类" : "新增分类";
      this.categoryModel = true;
    },
    saveOrUpdate() {

    },
    deleteCategory(id) {

    }
  }
}
</script>

<style scoped>

</style>