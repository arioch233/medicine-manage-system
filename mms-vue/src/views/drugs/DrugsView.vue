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
      <el-table-column prop="drugNum" width="150" label="药品本位码" fixed="left" align="center"/>
      <el-table-column label="药品封图" align="center" width="200">
        <template slot-scope="scope">
          <el-image
              style="width: 180px; height: 100px"
              :src="scope.row.drugCover"
              fit="contain">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="drugCommonName" width="150" label="药品通用名" align="center"/>
      <el-table-column prop="drugTradeName" width="150" label="药品商品名" align="center"/>
      <el-table-column prop="drugCategory" width="150" label="药品分类" align="center"/>
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
          {{ (scope.row.updateTime ? scope.row.updateTime : scope.row.createTime)| dateTime }}
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
            <el-tabs :tab-position="tabPosition" style="height: 220px;">
              <el-tab-pane label="上传封图">
                <el-upload
                    class="upload-drug-img"
                    drag
                    action="http://localhost:9090/file/image/upload"
                    multiple
                    :before-upload="beforeUpload"
                    :on-success="uploadCover"
                >
                  <i class="el-icon-upload" v-if="drugForm.drugCover === ''"/>
                  <div class="el-upload__text" v-if="drugForm.drugCover === ''">
                    将文件拖到此处，或<em>点击上传</em>
                  </div>
                  <div class="el-upload__tip" slot="tip" v-if="drugForm.drugCover === ''">
                    只能上传jpg/png文件
                  </div>
                  <img
                      v-else
                      :src="drugForm.drugCover"
                      width="360px"
                      height="180px"
                  />
                </el-upload>
              </el-tab-pane>
              <el-tab-pane label="图片URL" style="text-align: center">
                <el-input
                    size="small"
                    v-model="drugForm.drugCover"
                    clearable
                    placeholder="请输入图片的URL地址">
                </el-input>
                <img
                    v-if="drugForm.drugCover !== ''"
                    :src="drugForm.drugCover"
                    width="360px"
                    height="180px"
                />
              </el-tab-pane>
            </el-tabs>
          </el-tab-pane>
          <el-tab-pane name="drugBaseInfo" label="药品基本信息">
            <el-form-item label="药品本位码">
              <el-input v-model="drugForm.drugNum" style="width:350px"/>
            </el-form-item>
            <el-form-item label="药品通用名">
              <el-input v-model="drugForm.drugCommonName" style="width:350px"/>
            </el-form-item>
            <el-form-item label="药品商品名">
              <el-input v-model="drugForm.drugTradeName" style="width:350px"/>
            </el-form-item>
            <el-form-item label="药品功效">
              <el-input v-model="drugForm.drugEffect" type="textarea" style="width:350px"/>
            </el-form-item>
            <el-form-item label="药品分类">
              <el-select
                  v-model="drugForm.drugCategory"
                  filterable
                  default-first-option
                  placeholder="请选择药品分类">
                <el-option
                    v-for="item in drugCategoryOptions"
                    :key="item.id"
                    :label="item.categoryName"
                    :value="item.categoryName">
                </el-option>
              </el-select>
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
import * as imageConversion from "image-conversion";

export default {
  name: "DrugView",
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
    selectionChange(roleList) {
      this.drugIdList = [];
      roleList.forEach(item => {
        this.drugIdList.push(item.id);
      });
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
    openModel(drug) {
      this.$refs.supplierTitle.innerHTML = drug ? "修改药品信息" : "新增药品信息";
      if (drug !== null) {
        this.drugForm = JSON.parse(JSON.stringify(drug));
        console.log(this.drugForm)
      } else {
        this.drugForm = {
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
        };
      }
      this.request.get("/category/options", {
        params: {
          categoryType: 0
        }
      }).then(data => {
        this.drugCategoryOptions = data.data;
      });
      this.drugModel = true;
    },
    saveOrUpdate() {
      this.request.post("/drug/update", this.drugForm).then(data => {
        if (data.code === 200) {
          this.$notify.success({
            title: "成功",
            message: data.message
          });
          this.listDrug();
        } else {
          this.$notify.error({
            title: "失败",
            message: data.message
          });
        }
        this.drugModel = false;
      });
    },
    deleteCargo(id) {
      let param = {};
      if (id == null) {
        param = {data: this.drugCategoryIdList};
      } else {
        param = {data: [id]};
      }
      this.request.delete("/drug/delete", param).then(data => {
        if (data.code === 200) {
          this.$notify.success({
            title: "成功",
            message: data.message
          });
          this.listDrug();
        } else {
          this.$notify.error({
            title: "失败",
            message: data.message
          });
        }
        this.isDelete = false;
      });
    },
    uploadCover(response) {
      this.drugForm.drugCover = response.data;
    },
    beforeUpload(file) {
      return new Promise(resolve => {
        if (file.size / 1024 < this.config.UPLOAD_SIZE) {
          resolve(file);
        }
        // 压缩到200KB,这里的200就是要压缩的大小,可自定义
        imageConversion
            .compressAccurately(file, this.config.UPLOAD_SIZE)
            .then(res => {
              resolve(res);
            });
      });
    },
  }
}
</script>

<style scoped>
.upload-drug-img {
  text-align: center;
}
</style>