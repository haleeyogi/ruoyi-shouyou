<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="分类名称" prop="catName">
        <el-input v-model="queryParams.catName" placeholder="请输入分类名称" clearable @key.enter.native="getList" />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="正常" value="1" />
          <el-option label="禁用" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getList">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="small" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="small" @click="handleBatchDelete">删除</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="分类名称" align="center" prop="catName" />
      <el-table-column label="排序" align="center" prop="sortNum" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span :class="scope.row.status === 1 ? 'text-green' : 'text-red'">
            {{ scope.row.status === 1 ? '正常' : '禁用' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180" />
      <el-table-column label="操作" align="center" width="180">
        <template slot-scope="scope">
          <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 新增编辑弹窗 -->
    <el-dialog title="游戏分类编辑" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="分类名称" prop="catName" rules="[{ required: true, message: '分类名称不能为空', trigger: 'blur' }]">
          <el-input v-model="form.catName" placeholder="请输入分类名称" />
        </el-form-item>
        <el-form-item label="分类图标" prop="catIcon">
          <el-input v-model="form.catIcon" placeholder="请输入图标地址" />
        </el-form-item>
        <el-form-item label="排序" prop="sortNum">
          <el-input v-model.number="form.sortNum" placeholder="数值越小越靠前" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio label="1">正常</el-radio>
            <el-radio label="0">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listGameCategory, getGameCategory, addGameCategory, updateGameCategory, delGameCategory } from "@/api/system/gameCategory";

export default {
  name: "GameCategory",
  data() {
    return {
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        catName: null,
        status: null
      },
      // 遮罩层
      loading: false,
      // 列表数据
      list: [],
      // 总条数
      total: 0,
      // 弹出层
      open: false,
      // 表单
      form: {},
      // 表单校验
      rules: {},
      // 选中数组
      ids: [],
      // 显示搜索
      showSearch: true
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 查询列表
    getList() {
      this.loading = true;
      listGameCategory(this.queryParams).then(res => {
        this.list = res.rows;
        this.total = res.total;
        this.loading = false;
      });
    },
    // 重置查询
    resetQuery() {
      this.resetForm("queryForm");
      this.getList();
    },
    // 新增
    handleAdd() {
      this.open = true;
      this.form = { status: 1, sortNum: 0 };
      this.resetForm("form");
    },
    // 编辑
    handleEdit(row) {
      this.open = true;
      getGameCategory(row.id).then(res => {
        this.form = res.data;
      });
    },
    // 提交
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.form.id) {
            updateGameCategory(this.form).then(res => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGameCategory(this.form).then(res => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    // 单条删除
    handleDelete(row) {
      this.$confirm('此操作将永久删除该分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delGameCategory(row.id).then(res => {
          this.msgSuccess("删除成功");
          this.getList();
        });
      }).catch(() => {});
    },
    // 多选删除
    handleBatchDelete() {
      if (this.ids.length == 0) {
        this.msgWarning("请选择要删除的数据");
        return;
      }
      this.$confirm('此操作将永久删除选中数据, 是否继续?', '提示', {
        type: 'warning'
      }).then(() => {
        delGameCategory(this.ids).then(res => {
          this.msgSuccess("删除成功");
          this.getList();
        });
      }).catch(() => {});
    },
    // 选中
    handleSelectionChange(val) {
      this.ids = val.map(item => item.id);
    }
  }
};
</script>
