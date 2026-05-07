<template>
  <div class="h5-container">
    <!-- 轮播 -->
    <el-carousel indicator="outside" arrow="never" height="180px">
      <el-carousel-item v-for="item in bannerList" :key="item.id">
        <img :src="item.gameCover" class="banner-img" />
      </el-carousel-item>
    </el-carousel>

    <!-- 热门游戏 -->
    <div class="title">热门游戏</div>
    <div class="game-list">
      <div class="game-item" v-for="item in hotList" :key="item.id">
        <img :src="item.gameIcon" class="icon" />
        <div class="info">
          <div class="name">{{ item.gameName }}</div>
          <div class="desc">{{ item.gameSize }} · {{ item.downloadCount }}次下载</div>
        </div>
        <el-button size="mini" type="primary">下载</el-button>
      </div>
    </div>

    <!-- 最新游戏 -->
    <div class="title">最新上架</div>
    <div class="game-list">
      <div class="game-item" v-for="item in newList" :key="item.id">
        <img :src="item.gameIcon" class="icon" />
        <div class="info">
          <div class="name">{{ item.gameName }}</div>
          <div class="desc">{{ item.gameSize }} · {{ item.downloadCount }}次下载</div>
        </div>
        <el-button size="mini" type="success">下载</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { getBanner, getHotGame, getNewGame } from '@/api/h5/game'

export default {
  name: 'H5Index',
  data() {
    return {
      bannerList: [],
      hotList: [],
      newList: []
    }
  },
  created() {
    this.loadData()
  },
  methods: {
    async loadData() {
      const [banner, hot, newGame] = await Promise.all([
        getBanner(),
        getHotGame(),
        getNewGame()
      ])
      this.bannerList = banner.data
      this.hotList = hot.data
      this.newList = newGame.data
    }
  }
}
</script>

<style scoped>
.h5-container {
  background: #f5f5f5;
  min-height: 100vh;
}
.banner-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.title {
  padding: 10px;
  font-size: 16px;
  font-weight: bold;
}
.game-list {
  background: #fff;
}
.game-item {
  display: flex;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #eee;
}
.icon {
  width: 50px;
  height: 50px;
  border-radius: 8px;
}
.info {
  flex: 1;
  margin: 0 10px;
}
.name {
  font-size: 14px;
}
.desc {
  font-size: 12px;
  color: #666;
}
</style>
