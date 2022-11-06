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
          :disabled="this.medicalDevicesIdList.length === 0"
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
            placeholder="请输入器材名"
            style="width:200px"
            @keyup.enter.native="searchMedicalDevices"
        />
        <el-button
            type="primary"
            size="small"
            icon="el-icon-search"
            style="margin-left:1rem"
            @click="searchMedicalDevices"
        >
          搜索
        </el-button>
      </div>
    </div>
    <!-- 表格展示 -->
    <el-table
        border
        :data="medicalDevicesList"
        @selection-change="selectionChange"
        v-loading="loading"
    >
      <!-- 表格列 -->
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="medicalDevicesNum" width="150" label="器材编码" fixed="left" align="center"/>
      <el-table-column label="器材封图" align="center" width="200">
        <template slot-scope="scope">
          <el-image
              style="width: 180px; height: 100px"
              :src="scope.row.medicalDevicesCover"
              fit="contain">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="medicalDevicesName" width="150" label="器材名称" align="center"/>
      <el-table-column prop="medicalDevicesCategory" width="150" label="器材分类" align="center"/>
      <el-table-column prop="medicalDevicesEffect" width="150" label="器材功能" align="center"/>
      <el-table-column prop="medicalDevicesSpecification" width="150" label="器材规格" align="center"/>
      <el-table-column prop="medicalDevicesManufacturer" width="150" label="器材厂商" align="center"/>
      <el-table-column prop="medicalDevicesQuantity" width="150" label="器材数量" align="center"/>
      <el-table-column prop="medicalDevicesPrice" width="150" label="器材单价" align="center"/>
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
    <el-dialog :visible.sync="medicalDevicesModel" width="40%" style="padding-top: 0px">
      <div class="dialog-title-container" slot="title" ref="supplierTitle"/>
      <el-form label-width="100px" size="medium" :model="medicalDevicesForm">
        <el-tabs active-name="medicalDevicesCover">
          <el-tab-pane name="medicalDevicesCover" label="器材封面">
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
                  <i class="el-icon-upload" v-if="medicalDevicesForm.medicalDevicesCover === ''"/>
                  <div class="el-upload__text" v-if="medicalDevicesForm.medicalDevicesCover === ''">
                    将文件拖到此处，或<em>点击上传</em>
                  </div>
                  <div class="el-upload__tip" slot="tip" v-if="medicalDevicesForm.medicalDevicesCover === ''">
                    只能上传jpg/png文件
                  </div>
                  <img
                      v-else
                      :src="medicalDevicesForm.medicalDevicesCover"
                      width="360px"
                      height="180px"
                  />
                </el-upload>
              </el-tab-pane>
              <el-tab-pane label="图片URL" style="text-align: center">
                <el-input
                    size="small"
                    v-model="medicalDevicesForm.medicalDevicesCover"
                    clearable
                    placeholder="请输入图片的URL地址">
                </el-input>
                <img
                    v-if="medicalDevicesForm.medicalDevicesCover !== ''"
                    :src="medicalDevicesForm.medicalDevicesCover"
                    width="360px"
                    height="180px"
                />
              </el-tab-pane>
            </el-tabs>
          </el-tab-pane>
          <el-tab-pane name="medicalDevicesBaseInfo" label="器材基本信息">
            <el-form-item label="器材编号">
              <el-input v-model="medicalDevicesForm.medicalDevicesNum" style="width:350px"/>
            </el-form-item>
            <el-form-item label="器材名称">
              <el-input v-model="medicalDevicesForm.medicalDevicesName" style="width:350px"/>
            </el-form-item>
            <el-form-item label="器材功能">
              <el-input v-model="medicalDevicesForm.medicalDevicesEffect" type="textarea" style="width:350px"/>
            </el-form-item>
            <el-form-item label="器材分类">
              <el-select
                  v-model="medicalDevicesForm.medicalDevicesCategory"
                  filterable
                  default-first-option
                  placeholder="请选择器材分类">
                <el-option
                    v-for="item in medicalDevicesCategoryOptions"
                    :key="item.id"
                    :label="item.categoryName"
                    :value="item.categoryName">
                </el-option>
              </el-select>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane name="medicalDevicesOther" label="其它">
            <el-form-item label="器材规格">
              <el-input v-model="medicalDevicesForm.medicalDevicesSpecification" style="width:350px"/>
            </el-form-item>
            <el-form-item label="器材厂商">
              <el-input v-model="medicalDevicesForm.medicalDevicesManufacturer" style="width:350px"/>
            </el-form-item>
            <el-form-item label="器材数量">
              <el-input v-model="medicalDevicesForm.medicalDevicesQuantity" style="width:350px"/>
            </el-form-item>
            <el-form-item label="器材单价">
              <el-input v-model="medicalDevicesForm.medicalDevicesPrice" style="width:350px"/>
            </el-form-item>
          </el-tab-pane>
        </el-tabs>
      </el-form>
      <div slot="footer">
        <el-button @click="medicalDevicesModel = false">取 消</el-button>
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
  name: "MedicalDevices",
  created() {
    this.listMedicalDevices();
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
      medicalDevicesIdList: [],
      medicalDevicesList: [],
      medicalDevicesModel: false,
      medicalDevicesForm: {
        medicalDevicesNum: "",
        medicalDevicesName: "",
        medicalDevicesSpecification: "",
        medicalDevicesManufacturer: "",
        medicalDevicesEffect: "",
        medicalDevicesQuantity: "0",
        medicalDevicesPrice: "",
        medicalDevicesCover: "",
        medicalDevicesCategory: ""
      },
      medicalDevicesCategoryOptions: []
    }
  },
  methods: {
    searchMedicalDevices() {
      this.current = 1;
      this.listMedicalDevices();
    },
    sizeChange(size) {
      this.size = size;
      this.listMedicalDevices();
    },
    currentChange(current) {
      this.current = current;
      this.listMedicalDevices();
    },
    selectionChange(medicalDevicesList) {
      this.medicalDevicesIdList = [];
      medicalDevicesList.forEach(item => {
        this.medicalDevicesIdList.push(item.id);
      });
    },
    listMedicalDevices() {
      this.request.get("/medical_devices/all", {
        params: {
          current: this.current,
          size: this.size,
          keywords: this.keywords,
        }
      }).then(data => {
        this.medicalDevicesList = data.data.records;
        this.count = data.data.count;
        this.loading = false;
      })
    },
    openModel(medicalDevices) {
      this.$refs.supplierTitle.innerHTML = medicalDevices ? "修改器材信息" : "新增器材信息";
      if (medicalDevices !== null) {
        this.medicalDevicesForm = JSON.parse(JSON.stringify(medicalDevices));
      } else {
        this.medicalDevicesForm = {
          medicalDevicesNum: "",
          medicalDevicesName: "",
          medicalDevicesSpecification: "",
          medicalDevicesManufacturer: "",
          medicalDevicesEffect: "",
          medicalDevicesQuantity: "0",
          medicalDevicesPrice: "",
          medicalDevicesCover: "",
          medicalDevicesCategory: ""
        };
      }
      this.request.get("/category/options", {
        params: {
          categoryType: 1
        }
      }).then(data => {
        this.medicalDevicesCategoryOptions = data.data;
      });
      this.medicalDevicesModel = true;
    },
    saveOrUpdate() {
      this.request.post("/medical_devices/update", this.medicalDevicesForm).then(data => {
        if (data.code === 200) {
          this.$notify.success({
            title: "成功",
            message: data.message
          });
          this.listMedicalDevices();
        } else {
          this.$notify.error({
            title: "失败",
            message: data.message
          });
        }
        this.medicalDevicesModel = false;
      });
    },
    deleteCargo(id) {
      let param = {};
      if (id == null) {
        param = {data: this.medicalDevicesIdList};
      } else {
        param = {data: [id]};
      }
      this.request.delete("/medical_devices/delete", param).then(data => {
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