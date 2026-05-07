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
