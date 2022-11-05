<template>
  <el-card class="main-card">
    <!-- 标题 -->
    <div class="title">{{ this.$route.name }}</div>
    <!-- 表格操作 -->
    <div class="operation-container">
      <el-upload :action="'http://localhost:9090/file/image/upload'" :show-file-list="false"
                 :on-success="handleFileUploadSuccess"
                 style="margin-right: 15px">
        <el-button
            type="primary"
            size="small"
            icon="el-icon-plus"
        >
          上传
        </el-button>
      </el-upload>

      <el-button
          type="danger"
          size="small"
          icon="el-icon-delete"
          :disabled="this.fileIds.length === 0"
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
            placeholder="请输入角色名"
            style="width:200px"
            @keyup.enter.native="load"
        />
        <el-button
            type="primary"
            size="small"
            icon="el-icon-search"
            style="margin-left:1rem"
            @click="load"
        >
          搜索
        </el-button>
      </div>
    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"
              size="small"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column label="预览" align="center">
        <template slot-scope="scope">
          <el-image
              style="width: 180px; height: 100px"
              :src="scope.row.url"
              :preview-src-list="srcList">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="filename" label="文件名称" align="center" width="300"></el-table-column>
      <el-table-column prop="type" label="文件类型" align="center"></el-table-column>
      <el-table-column prop="size" label="文件大小(kb)" align="center"></el-table-column>
      <el-table-column label="下载" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="download(scope.row.url)">下载</el-button>
        </template>
      </el-table-column>
      <!--      <el-table-column label="启用" align="center">-->
      <!--        <template slot-scope="scope">-->
      <!--          <el-switch v-model="scope.row.enable" active-color="#13ce66" inactive-color="#ccc"-->
      <!--                     @change="changeEnable(scope.row)"></el-switch>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference" size="small">删除 <i class="el-icon-remove-outline"></i>
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="padding: 10px 0;">
      <el-pagination
          class="pagination-container"
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="current"
          :page-sizes="[10, 20]"
          :page-size="size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <!-- 批量删除对话框 -->
    <el-dialog :visible.sync="isDelete" width="30%">
      <div class="dialog-title-container" slot="title">
        <i class="el-icon-warning" style="color:#ff9900"/>提示
      </div>
      <div style="font-size:1rem">是否删除选中项？</div>
      <div slot="footer">
        <el-button @click="isDelete = false">取 消</el-button>
        <el-button type="primary" @click="deleteFiles(null)">
          确 定
        </el-button>
      </div>
    </el-dialog>
  </el-card>

</template>

<script>

export default {
  name: "FileView",
  data() {
    return {
      isDelete: false,
      fileUpload: false,
      tableData: [],
      keywords: '',
      multipleSelection: [],
      fileIds: [],
      current: 1,
      size: 10,
      total: 0,
      srcList: [
        'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
        'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
      ]
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.srcList = [];
      this.request.get("/file/image/all", {
        params: {
          current: this.current,
          size: this.size,
          keywords: this.keywords
        }
      }).then(res => {
        this.generateSrcList(res.data.records);
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    },
    generateSrcList(json) {
      console.log(json);
      json.forEach(value => {
        this.srcList.push(value.url);
      })
    },
    changeEnable(row) {
      this.request.post("/file/update", row).then(res => {
        if (res.code === '200') {
          this.$message.success("操作成功")
        }
      })
    },
    del(id) {
      let param = {};
      if (id == null) {
        param = {data: this.fileIds};
      } else {
        param = {data: [id]};
      }
      console.log(param)
      this.request.delete("/file/image/delete", param).then(res => {
        if (res.code === 200) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      this.tagIdList = [];
      val.forEach(item => {
        this.fileIds.push(item.id);
      });
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    handleFileUploadSuccess(res) {
      console.log(res)
      this.$message.success("上传成功")
      this.load()
    },
    download(url) {
      window.open(url)
    },
    preview(url) {
      window.open('https://file.keking.cn/onlinePreview?url=' + encodeURIComponent(window.btoa((url))))
    },
    deleteFiles(id) {
      let param = {};
      if (id == null) {
        param = {data: this.fileIds};
      } else {
        param = {data: [id]};
      }
      this.request.delete("/file/image/delete", param).then(data => {
        if (data.code === 200) {
          this.$notify.success({
            title: "成功",
            message: data.message
          });
          this.listRoles();
        } else {
          this.$notify.error({
            title: "失败",
            message: data.message
          });
        }
        this.isDelete = false;
      });
    },
  }
}
</script>

<style scoped>
</style>