<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="70px">
      <el-form-item label="游戏名称" prop="gameName">
        <el-input v-model="queryParams.gameName" placeholder="请输入游戏名称" clearable @key.enter.native="getList" />
      </el-form-item>
      <el-form-item label="游戏分类" prop="catId">
        <el-select v-model="queryParams.catId" placeholder="请选择分类" clearable>
          <el-option label="全部分类" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="上架状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="上架" value="1" />
          <el-option label="下架" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getList">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="small" @click="handleAdd">新增游戏</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="small" @click="handleBatchDelete">批量删除</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" width="60" />
      <el-table-column label="游戏名称" align="center" prop="gameName" />
      <el-table-column label="安装包大小" align="center" prop="gameSize" />
      <el-table-column label="版本号" align="center" prop="gameVersion" />
      <el-table-column label="下载次数" align="center" prop="downloadCount" />
      <el-table-column label="是否推荐" align="center" prop="isRecommend">
        <template slot-scope="scope">
          {{ scope.row.isRecommend === 1 ? '是' : '否' }}
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span :class="scope.row.status === 1 ? 'text-green' : 'text-red'">
            {{ scope.row.status === 1 ? '上架' : '下架' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180" />
      <el-table-column label="操作" align="center" width="200">
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
    <el-dialog title="游戏信息编辑" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" label-width="90px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="分类ID" prop="catId" rules="[{ required: true, message: '请选择分类ID', trigger: 'blur' }]">
              <el-input v-model.number="form.catId" placeholder="输入分类ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="游戏名称" prop="gameName" rules="[{ required: true, message: '游戏名称不能为空', trigger: 'blur' }]">
              <el-input v-model="form.gameName" placeholder="请输入游戏名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="游戏图标地址" prop="gameIcon">
              <el-input v-model="form.gameIcon" placeholder="图标链接" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="封面图地址" prop="gameCover">
              <el-input v-model="form.gameCover" placeholder="封面链接" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="安装包大小" prop="gameSize">
              <el-input v-model="form.gameSize" placeholder="如：256M" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="版本号" prop="gameVersion">
              <el-input v-model="form.gameVersion" placeholder="如：1.0.0" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="游戏简介" prop="gameDesc">
          <el-input v-model="form.gameDesc" type="textarea" rows="3" placeholder="游戏简介" />
        </el-form-item>
        <el-form-item label="安卓下载链接" prop="gameDownloadUrl">
          <el-input v-model="form.gameDownloadUrl" placeholder="APK下载地址" />
        </el-form-item>
        <el-form-item label="H5链接" prop="gameH5Url">
          <el-input v-model="form.gameH5Url" placeholder="H5游戏地址" />
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="是否推荐" prop="isRecommend">
              <el-radio-group v-model.number="form.isRecommend">
                <el-radio label="1">推荐</el-radio>
                <el-radio label="0">不推荐</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="上架状态" prop="status">
              <el-radio-group v-model.number="form.status">
                <el-radio label="1">上架</el-radio>
                <el-radio label="0">下架</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="排序序号" prop="sortNum">
          <el-input v-model.number="form.sortNum" placeholder="数值越小越靠前" />
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
import { listGameInfo, getGameInfo, addGameInfo, updateGameInfo, delGameInfo } from "@/api/system/gameInfo";

export default {
  name: "GameInfo",
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        gameName: null,
        catId: null,
        status: null
      },
      loading: false,
      list: [],
      total: 0,
      open: false,
      form: {},
      rules: {},
      ids: [],
      showSearch: true
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      listGameInfo(this.queryParams).then(res => {
        this.list = res.rows;
        this.total = res.total;
        this.loading = false;
      });
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.getList();
    },
    handleAdd() {
      this.open = true;
      this.form = { status: 1, isRecommend: 0, sortNum: 0 };
      this.resetForm("form");
    },
    handleEdit(row) {
      this.open = true;
      getGameInfo(row.id).then(res => {
        this.form = res.data;
      });
    },
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.form.id) {
            updateGameInfo(this.form).then(() => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGameInfo(this.form).then(() => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    handleDelete(row) {
      this.$confirm('确定删除该游戏？', '提示', { type: 'warning' }).then(() => {
        delGameInfo(row.id).then(() => {
          this.msgSuccess("删除成功");
          this.getList();
        });
      }).catch(() => {});
    },
    handleBatchDelete() {
      if (this.ids.length === 0) {
        this.msgWarning("请选择要删除的数据");
        return;
      }
      this.$confirm('确定批量删除选中数据？', '提示', { type: 'warning' }).then(() => {
        delGameInfo(this.ids).then(() => {
          this.msgSuccess("删除成功");
          this.getList();
        });
      }).catch(() => {});
    },
    handleSelectionChange(val) {
      this.ids = val.map(item => item.id);
    }
  }
};
</script>
