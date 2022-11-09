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
          1
        </el-card>
      </el-col>
      <el-col :span="10" :offset="2">
        <el-card>
          1
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "HomeView",
  data() {
    return {
      drugCount: "666",
      medicalDeviceCount: "666",
      userCount: "666",
      orderCount: "666",
    }
  },
  methods: {}
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