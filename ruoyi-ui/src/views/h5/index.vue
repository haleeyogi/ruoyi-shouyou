<template>
  <div class="home">
    <!-- 轮播 -->
    <div class="swiper">
      <img src="https://picsum.photos/800/300" alt="轮播图">
    </div>

    <!-- 导航 -->
    <div class="nav-row">
      <div class="nav-item" @click="$router.push('/h5/category')">全部分类</div>
      <div class="nav-item" @click="$router.push('/h5/rank')">排行榜</div>
      <div class="nav-item" @click="$router.push('/h5/search')">搜索游戏</div>
    </div>

    <!-- 热门游戏 -->
    <div class="title">🔥热门游戏</div>
    <div class="game-list">
      <div class="game-item" v-for="item in hotList" :key="item.id" @click="$router.push(`/h5/detail/${item.id}`)">
        <img :src="item.gameIcon" class="icon">
        <div class="info">
          <div class="name">{{item.gameName}}</div>
          <div class="desc">{{item.downloadCount}}次下载</div>
        </div>
      </div>
    </div>
    <div class="game-item" v-for="item in hotList" :key="item.id" @click="goDetail(item.id)">
      ...不变...
    </div>
  </div>
</template>

<script>
import { getHotGame } from "@/api/h5/game";
export default {
  data() {
    return { hotList: [] };
  },
  async created() {
    let res = await getHotGame();
    this.hotList = res.data;
  },
};
</script>

<style scoped>
.home { background: #f5f5f5; min-height: 100vh; }
.swiper img { width: 100%; border-radius: 10px; }
.nav-row { display: flex; justify-content: space-around; background: #fff; padding: 12px; margin: 10px; border-radius: 10px; }
.nav-item { font-size: 14px; }
.title { padding: 15px; font-size: 16px; font-weight: bold; }
.game-list { background: #fff; margin: 10px; border-radius: 10px; }
.game-item { display: flex; padding: 10px; border-bottom: 1px solid #eee; }
.icon { width: 60px; height: 60px; border-radius: 8px; }
.info { margin-left: 10px; }
.nav-row{display:flex;justify-content:space-around;background:#fff;padding:12px 0;margin:10px;border-radius:10px;}
.nav-item{font-size:14px;color:#333;}
</style>
