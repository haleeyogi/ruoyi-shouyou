<template>
  <div class="category-page">
    <div class="cat-tab">
      <div class="tab-item" :class="{active:activeId===0}" @click="changeCat(0)">全部</div>
      <div class="tab-item" v-for="item in catList" :key="item.id"
           :class="{active:activeId===item.id}" @click="changeCat(item.id)">
        {{item.catName}}
      </div>
    </div>

    <div class="game-list">
      <div class="game-item" v-for="item in gameList" :key="item.id" @click="$router.push('/h5/detail/'+item.id)">
        <img :src="item.gameIcon" class="icon"/>
        <div class="info">
          <div class="name">{{item.gameName}}</div>
          <div class="desc">{{item.gameSize}} · {{item.downloadCount||0}}次下载</div>
        </div>
        <el-button size="mini" type="primary">下载</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { getCategoryList, getGameByCat } from '@/api/h5/game'
export default {
  data(){
    return{
      catList:[],
      gameList:[],
      activeId:0
    }
  },
  created(){
    this.loadCat()
    this.loadAllGame()
  },
  methods:{
    async loadCat(){
      let res = await getCategoryList()
      this.catList = res.data
    },
    async loadAllGame(){
      let res = await getGameByCat(0)
      this.gameList = res.data
    },
    async changeCat(id){
      this.activeId = id
      let res = await getGameByCat(id)
      this.gameList = res.data
    }
  }
}
</script>

<style scoped>
.category-page{background:#f5f5f5;min-height:100vh;}
.cat-tab{display:flex;flex-wrap:wrap;background:#fff;padding:10px;}
.tab-item{padding:6px 12px;margin:5px;border-radius:20px;background:#eee;font-size:13px;}
.tab-item.active{background:#409eff;color:#fff;}
.game-list{background:#fff;margin-top:10px;}
.game-item{display:flex;align-items:center;padding:10px;border-bottom:1px solid #eee;}
.icon{width:50px;height:50px;border-radius:8px;}
.info{flex:1;margin:0 10px;}
.name{font-size:14px;}
.desc{font-size:12px;color:#666;}
</style>
