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
      <el-table-column prop="drugCommonName" width="150" label="药品通用名" align="center"/>
      <el-table-column prop="drugTradeName" width="150" label="药品商品名" align="center"/>
      <el-table-column prop="drugForm" width="150" label="药品剂型" align="center"/>
      <el-table-column prop="drugSpecification" width="150" label="药品规格" align="center"/>
      <el-table-column prop="drugPackagingMaterial" width="150" label="药品包装材质" align="center"/>
      <el-table-column prop="drugEffect" width="150" label="药品功效" align="center"/>
      <el-table-column prop="drugManufacturer" width="150" label="药品厂商" align="center"/>
      <el-table-column prop="drugQuantity" width="150" label="药品数量" align="center"/>
      <el-table-column prop="drugPrice" width="150" label="药品单价" align="center"/>
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
    <!--  药品对话框  -->
    <el-dialog :visible.sync="drugModel" width="40%" style="padding-top: 0px">
      <div class="dialog-title-container" slot="title" ref="supplierTitle"/>
      <el-form label-width="100px" size="medium" :model="drugForm">
        <el-tabs active-name="drugCover">
          <el-tab-pane name="drugCover" label="药品封面">
            <el-upload
                class="upload-drug-img"
                drag
                action="http://localhost:9090/file/image/upload"
                multiple>
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将药品封图拖到此处，或<em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
          </el-tab-pane>
          <el-tab-pane name="drugBaseInfo" label="药品基本信息">
            <el-form-item label="药品通用名">
              <el-input v-model="drugForm.drugCommonName" style="width:350px"/>
            </el-form-item>
            <el-form-item label="药品商品名">
              <el-input v-model="drugForm.drugTradeName" style="width:350px"/>
            </el-form-item>
            <el-form-item label="药品功效">
              <el-input v-model="drugForm.drugEffect" style="width:350px"/>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane name="drugInfo" label="药品信息">
            <el-form-item label="药品剂型">
              <el-input v-model="drugForm.drugForm" style="width:350px"/>
            </el-form-item>
            <el-form-item label="药品规格">
              <el-input v-model="drugForm.drugSpecification" style="width:350px"/>
            </el-form-item>
            <el-form-item label="药品包装材质">
              <el-input v-model="drugForm.drugPackagingMaterial" style="width:350px"/>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane name="drugOther" label="其它">
            <el-form-item label="药品厂商">
              <el-input v-model="drugForm.drugManufacturer" style="width:350px"/>
            </el-form-item>
            <el-form-item label="药品数量">
              <el-input v-model="drugForm.drugQuantity" style="width:350px"/>
            </el-form-item>
            <el-form-item label="药品单价">
              <el-input v-model="drugForm.drugPrice" style="width:350px"/>
            </el-form-item>
          </el-tab-pane>
        </el-tabs>
      </el-form>
      <div slot="footer">
        <el-button @click="drugModel = false">取 消</el-button>
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
        <el-button type="primary" @click="deleteCargo(null)">
          确 定
        </el-button>
      </div>
    </el-dialog>
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
      drugList: [],
      drugModel: false,
      drugForm: {
        drugCommonName: "",
        drugTradeName: "",
        drugForm: "",
        drugSpecification: "",
        drugPackagingMaterial: "",
        drugManufacturer: "",
        drugEffect: "",
        drugQuantity: "",
        drugPrice: ""
      }
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
      this.$refs.supplierTitle.innerHTML = drug ? "修改药品信息" : "新增药品信息";
      this.drugModel = true;
    },
    saveOrUpdate() {

    },
    deleteCargo(id) {

    }
  }
}
</script>

<style scoped>
.upload-drug-img {
  text-align: center;
}
</style>