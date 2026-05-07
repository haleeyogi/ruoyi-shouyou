<template>
  <div class="rank-page">
    <div class="title">🎮 游戏下载排行榜</div>
    <div class="rank-list">
      <div class="rank-item" v-for="(item,index) in rankList" :key="item.id"
           @click="$router.push('/h5/detail/'+item.id)">
        <div class="num" :class="top3(index)">{{index+1}}</div>
        <img :src="item.gameIcon" class="icon"/>
        <div class="info">
          <div class="name">{{item.gameName}}</div>
          <div class="desc">下载：{{item.downloadCount||0}} 次</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getGameRank } from '@/api/h5/game'
export default {
  data(){
    return{
      rankList:[]
    }
  },
  async created(){
    let res = await getGameRank()
    this.rankList = res.data
  },
  methods:{
    top3(idx){
      if(idx===0)return 'no1'
      if(idx===1)return 'no2'
      if(idx===2)return 'no3'
      return ''
    }
  }
}
</script>

<style scoped>
.rank-page{background:#f5f5f5;min-height:100vh;padding:10px;}
.title{font-size:18px;font-weight:bold;padding:10px 0;text-align:center;}
.rank-list{background:#fff;border-radius:10px;overflow:hidden;}
.rank-item{display:flex;align-items:center;padding:12px;border-bottom:1px solid #f0f0f0;}
.num{width:30px;text-align:center;font-weight:bold;font-size:16px;}
.num.no1{color:#f59e0b;}
.num.no2{color:#94a3b8;}
.num.no3{color:#b45309;}
.icon{width:50px;height:50px;border-radius:8px;margin:0 10px;}
.info .name{font-size:14px;}
.info .desc{font-size:12px;color:#666;margin-top:4px;}
</style>
