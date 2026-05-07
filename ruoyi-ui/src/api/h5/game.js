import request from '@/utils/request'

// 首页轮播
export function getBanner() {
  return request({
    url: '/api/game/banner',
    method: 'get'
  })
}

// 热门游戏
export function getHotGame() {
  return request({
    url: '/api/game/hot',
    method: 'get'
  })
}

// 最新游戏
export function getNewGame() {
  return request({
    url: '/api/game/new',
    method: 'get'
  })
}

// 游戏详情
export function getGameDetail(id) {
  return request({
    url: '/api/game/detail/' + id,
    method: 'get'
  })
}
// 获取分类列表
export function getCategoryList() {
  return request({ url: '/api/game/category/list', method: 'get' })
}

// 根据分类查游戏
export function getGameByCat(catId) {
  return request({ url: '/api/game/byCat/'+catId, method: 'get' })
}

// 排行榜
export function getGameRank() {
  return request({ url: '/api/game/rank', method: 'get' })
}

// 搜索游戏
export function searchGame(keyword) {
  return request({ url: '/api/game/search?keyword='+keyword, method: 'get' })
}
