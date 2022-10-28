<template>
  <el-card class="main-card">
    <!-- 标题 -->
    <div class="title">{{ this.$route.name }}</div>
    <!-- 表格操作 -->
    <div class="operation-container">
      <el-button
          type="primary"
          size="small"
          icon="iconfont icon-tianjiagonghuoshang"
          @click="openModel(null)"
      >
        新增
      </el-button>
      <el-button
          type="danger"
          size="small"
          icon="el-icon-delete"
          :disabled="this.supplierIdList.length === 0"
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
            placeholder="请输入供货商名称"
            style="width:200px"
            @keyup.enter.native="searchSupplier"
        />
        <el-button
            type="primary"
            size="small"
            icon="el-icon-search"
            style="margin-left:1rem"
            @click="searchSupplier"
        >
          搜索
        </el-button>
      </div>
    </div>
    <!-- 表格展示 -->
    <el-table
        border
        :data="supplierList"
        @selection-change="selectionChange"
        v-loading="loading"
    >
      <!-- 表格列 -->
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="supplierName" label="供货商名称" fixed="left" width="200" align="center"/>
      <el-table-column prop="supplierPrincipal" label="供货商负责人" width="150" align="center"/>
      <el-table-column prop="supplierContract" label="供货商联系方式" width="150" align="center"/>
      <el-table-column prop="supplierEmail" label="供货商邮箱" width="150" align="center"/>
      <el-table-column prop="supplierAddress" label="供货商地址" width="200" align="center"/>
      <el-table-column prop="supplierBank" label="供货商开户行" width="150" align="center"/>
      <el-table-column prop="supplierBankCard" label="供货商开户行账号" width="300" align="center"/>
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
          {{ (scope.row.updateTime ? scope.row.updateTime : scope.row.createTime)| dateTime }}
        </template>
      </el-table-column>
      <!-- 列操作 -->
      <el-table-column label="操作" fixed="right" align="center" width="220">
        <template slot-scope="scope">
          <el-button type="text" size="mini" @click="openModel(scope.row)">
            <i class="el-icon-edit"/> 修改
          </el-button>
          <el-popconfirm
              title="确定删除吗？"
              style="margin-left:10px"
              @confirm="deleteSupplier(scope.row.id)"
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
    <!--  供货商对话框  -->
    <el-dialog :visible.sync="supplierModel" width="40%">
      <div class="dialog-title-container" slot="title" ref="supplierTitle"/>
      <el-form label-width="150px" size="medium" :model="supplierForm" :rules="rules">
        <el-form-item label="供货商名称">
          <el-input v-model="supplierForm.supplierName" style="width:350px"/>
        </el-form-item>
        <el-form-item label="供货商负责人">
          <el-input v-model="supplierForm.supplierPrincipal" style="width:350px"/>
        </el-form-item>
        <el-form-item label="供货商联系方式">
          <el-input v-model="supplierForm.supplierContract" style="width:350px"/>
        </el-form-item>
        <el-form-item label="供货商邮箱">
          <el-input v-model="supplierForm.supplierEmail" style="width:350px"/>
        </el-form-item>
        <el-form-item label="供货商地址">
          <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" v-model="supplierForm.supplierAddress"
                    style="width:350px"/>
        </el-form-item>
        <el-form-item label="供货商开户行">
          <el-autocomplete
              class="inline-input"
              v-model="supplierForm.supplierBank"
              :fetch-suggestions="querySearch"
              placeholder="请输入开户行"
              :trigger-on-focus="false"
              @select="handleSelect"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="供货商开户行账号">
          <el-input v-model="supplierForm.supplierBankCard" style="width:350px"/>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="supplierModel = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">
          确 定
        </el-button>
      </div>
    </el-dialog>
    <!-- 批量删除对话框 -->
    <el-dialog :visible.sync="isDelete" width="30%">
      <div class="dialog-title-container" slot="title">
        <i class="el-icon-warning" style="color:#ff9900"/>提示
      </div>
      <div style="font-size:1rem">是否删除选中项？</div>
      <div slot="footer">
        <el-button @click="isDelete = false">取 消</el-button>
        <el-button type="primary" @click="deleteSupplier(null)">
          确 定
        </el-button>
      </div>
    </el-dialog>
  </el-card>
</template>

<script>
export default {
  name: "SupplierView",
  created() {
    this.listSupplier();
  },
  data() {
    return {
      loading: true,
      isDelete: false,
      keywords: null,
      current: 1,
      size: 10,
      count: 0,
      supplierIdList: [],
      supplierList: [],
      supplierModel: false,
      supplierForm: {
        supplierName: "",
        supplierPrincipal: "",
        supplierContract: "",
        supplierEmail: "",
        supplierAddress: "",
        supplierBank: "",
        supplierBankCard: ""
      },
      restaurants: [],
      rules: [],
    }
  },
  methods: {
    searchSupplier() {
      this.current = 1;
      this.listSupplier();
    },
    sizeChange(size) {
      this.size = size;
      this.listSupplier();
    },
    currentChange(current) {
      this.current = current;
      this.listSupplier();
    },
    selectionChange(list) {
      this.supplierIdList = [];
      list.forEach(item => {
        this.supplierIdList.push(item.id);
      });
    },
    listSupplier() {
      this.request.get("/supplier/all", {
        params: {
          current: this.current,
          size: this.size,
          keywords: this.keywords,
        }
      }).then(data => {
        this.supplierList = data.data.records;
        this.count = data.data.count;
        this.loading = false;
      })
    },
    openModel(suppler) {
      this.$refs.supplierTitle.innerHTML = suppler ? "修改供货商信息" : "新增供货商信息";
      this.supplierModel = true;
    },
    saveOrUpdate() {
      if (this.supplierForm.supplierName.trim() === "") {
        this.$message.error("供货商名称不能为空");
        return false;
      }
      if (this.supplierForm.supplierPrincipal.trim() === "") {
        this.$message.error("供货商负责人不能为空");
        return false;
      }
      if (this.supplierForm.supplierContract.trim() === "") {
        this.$message.error("供货商联系方式不能为空");
        return false;
      }
      if (this.supplierForm.supplierEmail.trim() === "") {
        this.$message.error("供货商邮箱不能为空");
        return false;
      }
      if (this.supplierForm.supplierAddress.trim() === "") {
        this.$message.error("供货商地址不能为空");
        return false;
      }
      if (this.supplierForm.supplierBank.trim() === "") {
        this.$message.error("供货商开户行不能为空");
        return false;
      }
      if (this.supplierForm.supplierBankCard.trim() === "") {
        this.$message.error("供货商开户行账号不能为空");
        return false;
      }
      this.request.post("/supplier/update",this.supplierForm).then(data => {
        if (data.code === 200) {
          this.$notify.success({
            title: "成功",
            message: data.message
          });
          this.listSupplier();
        } else {
          this.$notify.error({
            title: "失败",
            message: data.message
          });
        }
        this.supplierModel = false;
      })
    },
    deleteSupplier(id) {
      let param = {};
      if (id == null) {
        param = {data: this.supplierIdList};
      } else {
        param = {data: [id]};
      }
      this.request.delete("/supplier/delete", param).then(data => {
        if (data.code === 200) {
          this.$notify.success({
            title: "成功",
            message: data.message
          });
          this.listSupplier();
        } else {
          this.$notify.error({
            title: "失败",
            message: data.message
          });
        }
        this.isDelete = false;
      });
    },
    querySearch(queryString, cb) {
      let restaurants = this.restaurants;
      let results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item) {
      console.log(item);
    },
    loadAll() {
      return [
        {"value": "中国工商银行"},
        {"value": "中国农业银行"},
        {"value": "中国银行"},
        {"value": "中国建设银行"},
        {"value": "中国交通银行"},
        {"value": "招商银行"},
        {"value": "浦发银行"},
        {"value": "兴业银行"}
      ];
    },
  },
  mounted() {
    this.restaurants = this.loadAll();
  }
}
</script>

<style scoped>

</style>