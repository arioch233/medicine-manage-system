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
          :disabled="this.purchaseIdList.length === 0"
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
            placeholder="请输入订单单号"
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
        :data="purchaseList"
        @selection-change="selectionChange"
        v-loading="loading"
    >
      <!-- 表格列 -->
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="订单 ID: " style="margin-left: 300px">
              <span>{{ props.row.id }}</span>
            </el-form-item>
            <el-form-item label="供货商: " style="margin-left: 50px">
              <span>{{ props.row.supplierName }}</span>
            </el-form-item>
            <el-form-item label="订单负责人: " style="margin-left: 50px">
              <span>{{ props.row.employeeName }}</span>
            </el-form-item>
            <el-form-item label="总价: " style="margin-left: 50px">
              <span>{{ countPrice(props.row.cargoList) }}￥</span>
            </el-form-item>
            <el-table
                :header-cell-style="{backgroundColor:'#fcfcfc', color: '#333'}"
                type="expand"
                :data="props.row.cargoList"
                style="width: 60%; margin-left: 300px">
              <el-table-column
                  prop="cargoId"
                  label="商品ID"
                  align="center"
                  width="180">
              </el-table-column>
              <el-table-column
                  prop="cargoName"
                  label="商品名"
                  align="center"
                  width="250">
              </el-table-column>
              <el-table-column
                  prop="cargoPrice"
                  align="center"
                  label="商品单价">
              </el-table-column>
              <el-table-column
                  prop="cargoQuantity"
                  align="center"
                  label="商品总量">
              </el-table-column>
            </el-table>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="id" label="订单编号" width="200" align="center"/>
      <el-table-column prop="employeeName" label="订单负责人" width="200" align="center"/>
      <el-table-column prop="supplierName" label="供货商" align="center"/>
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
      <!-- 列操作 -->
      <el-table-column label="操作" align="center" width="100">
        <template slot-scope="scope">
          <el-popconfirm
              title="确定删除吗？"
              style="margin-left:10px"
              @confirm="deletePurchaseList(scope.row.id)"
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
    <!--  订单对话框  -->
    <el-dialog :visible.sync="purchaseListModel" width="40%" style="padding-top: 0px">
      <div class="dialog-title-container" slot="title" ref="supplierTitle"/>
      <el-form label-width="100px" size="medium" :model="purchaseListForm">
        <el-form-item label="负责员工" style="display: inline-block">
          <el-input
              style="width: 100px"
              :value=" this.$store.state.nickname"
              :disabled="true">
          </el-input>
        </el-form-item>
        <el-form-item label="供货商" style="display: inline-block">
          <el-select v-model="purchaseListForm.supplierId" placeholder="请选择供货商">
            <el-option
                v-for="item in supplierOptions"
                :key="item.id"
                :label="item.supplierName"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item
            label-width="80px"
            v-for="(cargo, index) in purchaseListForm.cargoList"
            :label="'商品' + (index + 1)"
            :key="cargo.key"
            :prop="'cargo.' + index + '.value'"
        >
          <el-select class="cargo-id" v-model="cargo.cargoId" clearable filterable placeholder="请选择商品">
            <el-option-group
                v-for="group in cargoOptions"
                :key="group.label"
                :label="group.label">
              <el-option
                  v-for="item in group.options"
                  :key="item.cargoId"
                  :label="item.cargoName"
                  :value="item.cargoId">
              </el-option>
            </el-option-group>
          </el-select>
          <span>&nbsp;数量&nbsp;</span>
          <el-input class="cargo-quantity" v-model="cargo.cargoQuantity"></el-input>
          <span>&nbsp;单价&nbsp;</span>
          <el-input class="cargo-price" v-model="cargo.cargoPrice"></el-input>
          <el-button class="cargo-add" type="text" @click.prevent="addCargo()">
            <i class="el-icon-circle-plus"></i>
          </el-button>
          <el-button v-if="purchaseListForm.cargoList.length > 1" class="cargo-remove" type="text"
                     @click.prevent="removeCargo(cargo)">
            <i class="el-icon-remove"></i>
          </el-button>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="purchaseListModel = false">取 消</el-button>
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
        <el-button type="primary" @click="deletePurchaseList(null)">
          确 定
        </el-button>
      </div>
    </el-dialog>
  </el-card>
</template>

<script>
export default {
  name: "PurchaseListView",
  created() {
    console.log("userId " + this.$store.state.userId)
    this.listPurchase();
  },
  data() {
    return {
      loading: true,
      isDelete: false,
      keywords: null,
      current: 1,
      size: 10,
      count: 0,
      purchaseListModel: false,
      purchaseIdList: [],
      purchaseList: [],
      purchaseListForm: {
        cargoList: [
          {
            cargoId: "",
            cargoQuantity: "",
            cargoPrice: ""
          },
        ],
        employeeId: this.$store.state.userId,
        supplierId: this.supplierId
      },
      supplierOptions: [],
      cargoOptions: [{
        label: '医药药品',
        options: [{
          cargoId: "",
          cargoName: ""
        },]
      }, {
        label: '医疗器械',
        options: [{
          cargoId: "",
          cargoName: ""
        },]
      }],
    }
  },
  methods: {
    countPrice(list) {
      let price = 0;
      list.forEach(function (item) {
        price += item.cargoPrice * item.cargoQuantity;
      });
      return price;
    },
    removeCargo(item) {
      let index = this.purchaseListForm.cargoList.indexOf(item)
      if (index !== -1) {
        this.purchaseListForm.cargoList.splice(index, 1)
      }
    },
    addCargo() {
      this.purchaseListForm.cargoList.push({
        cargoId: "",
        cargoQuantity: "",
        cargoPrice: "",
        key: Date.now()
      });
    },
    searchSupplier() {
      this.current = 1;
      this.listPurchase();
    },
    sizeChange(size) {
      this.size = size;
      this.listPurchase();
    },
    currentChange(current) {
      this.current = current;
      this.listPurchase();
    },
    selectionChange(list) {
      this.purchaseIdList = [];
      list.forEach(item => {
        this.purchaseIdList.push(item.id);
      });
    },
    listPurchase() {
      this.request.get("/purchase_list/all", {
        params: {
          current: this.current,
          size: this.size,
          keywords: this.keywords,
        }
      }).then(data => {
        this.purchaseList = data.data.recordList;
        this.count = data.data.count;
        this.loading = false;
      })
    },
    openModel(listPurchase) {
      this.$refs.supplierTitle.innerHTML = listPurchase ? "查看订单信息" : "新增订单信息";
      if (listPurchase !== null) {
        this.drugForm = JSON.parse(JSON.stringify(listPurchase));
        console.log(this.drugForm)
      } else {
        this.purchaseListForm = {
          employeeId: this.$store.state.userId,
          supplierId: this.supplierId,
          cargoList: [
            {
              cargoId: "",
              cargoQuantity: "",
              cargoPrice: ""
            },
          ],
        };
      }
      this.request.get("/supplier/options").then(data => {
        this.supplierOptions = data.data;
      });
      this.request.get("/drug/options").then(data => {
        this.cargoOptions[0].options = data.data;
      });
      this.request.get("/medical_devices/options").then(data => {
        this.cargoOptions[1].options = data.data;
      });
      this.purchaseListModel = true;
    },
    saveOrUpdate() {
      console.log(this.purchaseListForm)
      this.request.post("/purchase_list/update", this.purchaseListForm).then(data => {
        if (data.code === 200) {
          if (data.code === 200) {
            this.$notify.success({
              title: "成功",
              message: data.message
            });
            this.listPurchase();
          } else {
            this.$notify.error({
              title: "失败",
              message: data.message
            });
          }
        }
        this.purchaseListModel = false;
      })

    },
    deletePurchaseList(id) {
      let param = {};
      if (id == null) {
        param = {data: this.purchaseIdList};
      } else {
        param = {data: [id]};
      }
      this.request.delete("/purchase_list/delete", param).then(data => {
        if (data.code === 200) {
          this.$notify.success({
            title: "成功",
            message: data.message
          });
          this.listPurchase();
        } else {
          this.$notify.error({
            title: "失败",
            message: data.message
          });
        }
        this.isDelete = false;
      });
    }
  }
}
</script>

<style scoped>
.cargo-id {
  width: 200px;
}

.cargo-quantity {
  width: 70px;
}

.cargo-price {
  width: 70px;
}

.cargo-add {
  margin-left: 10px;
  font-size: 1.25rem;
}

.cargo-remove {
  color: #F56C6C;
  font-size: 1.25rem;
}


</style>