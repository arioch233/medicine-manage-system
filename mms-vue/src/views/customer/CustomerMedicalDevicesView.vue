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
      <el-table-column prop="medicalDevicesEffect" label="器材功能" align="center"/>
      <el-table-column prop="medicalDevicesSpecification" width="150" label="器材规格" align="center"/>
      <el-table-column prop="medicalDevicesManufacturer" width="150" label="器材厂商" align="center"/>
      <el-table-column prop="medicalDevicesPrice" width="150" label="器材单价" align="center"/>
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
  name: "CustomerMedicalDevicesView",
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
  }
}
</script>

<style scoped>
.upload-drug-img {
  text-align: center;
}
</style>