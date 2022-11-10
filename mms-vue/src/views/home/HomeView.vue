<template>
  <div>
    <el-row :gutter="30" style="margin-top: 10px">
      <el-col :span="6">
        <el-card>
          <div class="card-icon-container">
            <i class="iconfont icon--yiliao-yaopin" style="color:#40C9C6;font-size: 3rem"/>
          </div>
          <div class="card-desc">
            <div class="card-title">医疗药品</div>
            <div class="card-count">{{ drugCount }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="card-icon-container">
            <i class="iconfont icon-yiliaoqicai" style="color:#34BFA3;font-size: 3rem"/>
          </div>
          <div class="card-desc">
            <div class="card-title">医疗器材</div>
            <div class="card-count">{{ medicalDeviceCount }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="card-icon-container">
            <i class="iconfont icon-s_yonghuliebiao" style="color:#F4516C;font-size: 3rem"/>
          </div>
          <div class="card-desc">
            <div class="card-title">用户量</div>
            <div class="card-count">{{ userCount }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="card-icon-container">
            <i class="iconfont icon-gongdanguanli" style="color:#36A3F7; font-size: 3rem"/>
          </div>
          <div class="card-desc">
            <div class="card-title">工单量</div>
            <div class="card-count">{{ orderCount }}</div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="30" style="margin-top: 30px">
      <el-col :span="10" :offset="1">
        <el-card>
          <div class="e-title">医疗药品分类统计</div>
          <div style="height:400px" >
            <v-chart :options="drugCategory" v-loading="loading" class="echarts"/>
          </div>
        </el-card>
      </el-col>
      <el-col :span="10" :offset="1">
        <el-card>
          <div class="e-title">医疗器材分类统计</div>
          <div style="height:400px" >
            <v-chart :options="medicalDeviceCategory" v-loading="loading"/>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "HomeView",
  created() {
    this.loadCount();
  },
  data() {
    return {
      loading: true,
      drugCount: "666",
      medicalDeviceCount: "666",
      userCount: "666",
      orderCount: "666",
      drugCategory: {
        color: [
          "#7EC0EE",
          "#FF9F7F",
          "#FFD700",
          "#C9C9C9",
          "#E066FF",
          "#C0FF3E"
        ],
        legend: {
          data: [
            {
              value: 11,
              name: "22"
            }
          ],
          bottom: "bottom"
        },
        tooltip: {
          trigger: "item"
        },
        series: [
          {
            name: "医疗药品分类",
            type: "pie",
            roseType: "radius",
            data: []
          }
        ]
      },
      medicalDeviceCategory: {
        color: [
          "#7EC0EE",
          "#FF9F7F",
          "#FFD700",
          "#C9C9C9",
          "#E066FF",
          "#C0FF3E"
        ],
        legend: {
          data: [],
          bottom: "bottom"
        },
        tooltip: {
          trigger: "item"
        },
        series: [
          {
            name: "医疗器材分类",
            type: "pie",
            roseType: "radius",
            data: []
          }
        ]
      },
    }
  },
  methods: {
    loadCount() {
      this.request.get("/website/info").then(data => {
        this.drugCount = data.data.drugCount;
        this.medicalDeviceCount = data.data.medicalDeviceCount;
        this.userCount = data.data.userCount;
        this.orderCount = data.data.orderCount;

      })
      this.request.get("/website/pie-chart").then(data => {
        if (data.data.drugCategory != null) {
          data.data.drugCategory.forEach(item => {
            this.drugCategory.series[0].data.push({
              value: item.cargoCount,
              name: item.categoryName
            });
            this.drugCategory.legend.data.push(item.categoryName);
          });
        }
        if (data.data.medicalDeviceCategory != null) {
          data.data.medicalDeviceCategory.forEach(item => {
            this.medicalDeviceCategory.series[0].data.push({
              value: item.cargoCount,
              name: item.categoryName
            });
            this.medicalDeviceCategory.legend.data.push(item.categoryName);
          });
        }
        this.loading = false;
      })

    }

  }
}
</script>

<style scoped>
.card-icon-container {
  display: inline-block;
  font-size: 3rem;
}

.area-wrapper {
  display: flex;
  justify-content: center;
}

.card-desc {
  font-weight: bold;
  float: right;
}

.card-title {
  margin-top: 0.3rem;
  line-height: 18px;
  color: rgba(0, 0, 0, 0.45);
  font-size: 1rem;
}

.card-count {
  margin-top: 0.75rem;
  color: #666;
  font-size: 1.25rem;
}

.echarts {
  width: 100%;
  height: 100%;
}

.e-title {
  font-size: 13px;
  color: #202a34;
  font-weight: 700;
}
</style>