<template>
  <el-card class="main-card">
    <!-- 标题 -->
    <div class="title">{{ this.$route.name }}</div>
    <br>
    <br>
    <div>
      <el-form
          label-width="120px"
          :model="websiteConfigForm"
          label-position="left"
      >
        <el-row style="width:600px">
          <el-col :md="12">
            <el-form-item label="用户头像">
              <el-upload
                  class="avatar-uploader"
                  action="/api/admin/config/images"
                  :show-file-list="false"
                  :on-success="handleUserAvatarSuccess"
              >
                <img
                    v-if="websiteConfigForm.userAvatar"
                    :src="websiteConfigForm.userAvatar"
                    class="avatar"
                />
                <i v-else class="el-icon-plus avatar-uploader-icon"/>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="默认产品封面">
          <el-upload
              class="cover-uploader"
              action="/api/admin/config/images"
              :before-upload="beforeUpload"
              :show-file-list="false"
              :on-success="handleArticleCoverSuccess"
          >
            <img
                v-if="websiteConfigForm.cargoCover"
                :src="websiteConfigForm.cargoCover"
                class="cover"
            />
            <i v-else class="el-icon-plus cover-uploader-icon"/>
          </el-upload>
        </el-form-item>
        <el-form-item label="邮箱通知">
          <el-radio-group v-model="websiteConfigForm.isEmailNotice">
            <el-radio :label="0">关闭</el-radio>
            <el-radio :label="1">开启</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="客服状态">
          <el-radio-group v-model="websiteConfigForm.isChatRoom">
            <el-radio :label="0">关闭</el-radio>
            <el-radio :label="1">开启</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
            label="Websocket地址"
            v-show="websiteConfigForm.isChatRoom === 1"
        >
          <el-input
              v-model="websiteConfigForm.websocketUrl"
              size="small"
              style="width:400px"
          />
        </el-form-item>
        <el-button
            type="primary"
            size="medium"
            style="margin-left:6.3rem"
            @click="updateWebsiteConfig"
        >
          修改
        </el-button>
      </el-form>
    </div>
  </el-card>
</template>

<script>
import * as imageConversion from "image-conversion";

export default {
  name: "WebConfigView",
  created() {
    this.getWebsiteConfig();
  },
  data: function () {
    return {
      websiteConfigForm: {
        userAvatar: "",
        cargoCover: "",
        isChatRoom: 1,
        websocketUrl: "",
        isEmailNotice: 1,
      },
      activeName: "info"
    };
  },
  methods: {
    getWebsiteConfig() {
      this.request.get("/config/website").then(data => {
        this.websiteConfigForm = data.data;
      });
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
    handleClick(tab) {
      console.log(tab);
    },
    handleUserAvatarSuccess(response) {
      this.websiteConfigForm.userAvatar = response.data;
    },
    handleArticleCoverSuccess(response) {
      this.websiteConfigForm.articleCover = response.data;
    },
    updateWebsiteConfig() {
      this.request
          .put("/config/update", this.websiteConfigForm)
          .then(data => {
            if (data.code === 200) {
              this.$notify.success({
                title: "成功",
                message: data.message
              });
            } else {
              this.$notify.error({
                title: "失败",
                message: data.message
              });
            }
          });
    }
  }
};
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}

.avatar {
  width: 120px;
  height: 120px;
  display: block;
}

.cover-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.cover-uploader .el-upload:hover {
  border-color: #409eff;
}

.cover-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 300px;
  height: 160px;
  line-height: 160px;
  text-align: center;
}

.cover {
  width: 300px;
  height: 160px;
  display: block;
}
</style>