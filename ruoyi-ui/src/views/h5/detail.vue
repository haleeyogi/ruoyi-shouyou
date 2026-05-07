<template>
  <div class="detail-page">
    <!-- 头部封面 -->
    <div class="cover">
      <img :src="game.gameCover" class="cover-img" />
    </div>

    <!-- 游戏信息 -->
    <div class="info-card">
      <div class="top">
        <img :src="game.gameIcon" class="icon" />
        <div class="right">
          <div class="name">{{ game.gameName }}</div>
          <div class="info">
            {{ game.gameSize }} | {{ game.downloadCount || 0 }}次下载
          </div>
        </div>
      </div>

      <div class="btn-group">
        <el-button type="primary" round @click="goDownload">
          立即下载
        </el-button>
        <el-button type="success" round @click="goH5">
          在线玩
        </el-button>
      </div>
    </div>

    <!-- 游戏介绍 -->
    <div class="section">
      <div class="title">游戏介绍</div>
      <div class="desc">{{ game.gameDesc || '暂无介绍' }}</div>
    </div>

    <!-- 版本信息 -->
    <div class="section">
      <div class="title">版本信息</div>
      <div class="info-item">
        <span>版本号：{{ game.gameVersion }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import { getGameDetail } from '@/api/h5/game'

export default {
  data() {
    return {
      game: {}
    }
  },
  created() {
    this.loadDetail()
  },
  methods: {
    async loadDetail() {
      const id = this.$route.params.id
      const res = await getGameDetail(id)
      this.game = res.data
    },
    // 下载
    goDownload() {
      if (!this.game.gameDownloadUrl) {
        this.$message.warning('暂无下载链接')
        return
      }
      window.open(this.game.gameDownloadUrl, '_blank')
    },
    // 在线玩
    goH5() {
      if (!this.game.gameH5Url) {
        this.$message.warning('暂无在线玩链接')
        return
      }
      window.open(this.game.gameH5Url, '_blank')
    }
  }
}
</script>

<style scoped>
.detail-page {
  background: #f5f5f5;
  min-height: 100vh;
  padding-bottom: 40px;
}
.cover-img {
  width: 100%;
  height: 220px;
  object-fit: cover;
}
.info-card {
  background: #fff;
  margin: -30px 10px 10px;
  border-radius: 12px;
  padding: 15px;
  box-shadow: 0 2px 10px #00000010;
}
.top {
  display: flex;
  align-items: center;
}
.icon {
  width: 70px;
  height: 70px;
  border-radius: 12px;
}
.right {
  margin-left: 12px;
}
.name {
  font-size: 18px;
  font-weight: bold;
}
.info {
  font-size: 12px;
  color: #666;
  margin-top: 5px;
}
.btn-group {
  display: flex;
  gap: 10px;
  margin-top: 15px;
}
.section {
  background: #fff;
  margin: 10px;
  border-radius: 12px;
  padding: 15px;
}
.title {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 10px;
}
.desc {
  font-size: 14px;
  color: #333;
  line-height: 1.6;
}
</style>
