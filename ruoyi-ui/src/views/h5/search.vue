<template>
  <div class="search-page">
    <div class="search-bar">
      <el-input v-model="keyword" placeholder="输入游戏名称搜索" @key.enter.native="doSearch">
        <el-button slot="append" type="primary" @click="doSearch">搜索</el-button>
      </el-input>
    </div>

    <div class="result-list" v-if="resultList.length>0">
      <div class="game-item" v-for="item in resultList" :key="item.id"
           @click="$router.push('/h5/detail/'+item.id)">
        <img :src="item.gameIcon" class="icon"/>
        <div class="info">
          <div class="name">{{item.gameName}}</div>
          <div class="desc">{{item.gameSize}} · {{item.downloadCount||0}}次下载</div>
        </div>
      </div>
    </div>
    <div class="empty" v-else>暂无搜索结果</div>
  </div>
</template>

<script>
import { searchGame } from '@/api/h5/game'
export default {
  data(){
    return{
      keyword:'',
      resultList:[]
    }
  },
  methods:{
    async doSearch(){
      if(!this.keyword) return;
      let res = await searchGame(this.keyword)
      this.resultList = res.data
    }
  }
}
</script>

<style scoped>
.search-page{background:#f5f5f5;min-height:100vh;padding:10px;}
.search-bar{margin-bottom:15px;}
.game-item{display:flex;align-items:center;padding:10px;background:#fff;border-radius:8px;margin-bottom:8px;}
.icon{width:50px;height:50px;border-radius:8px;}
.info{margin-left:10px;}
.name{font-size:14px;}
.desc{font-size:12px;color:#666;margin-top:4px;}
.empty{text-align:center;color:#999;padding:30px 0;}
</style>
