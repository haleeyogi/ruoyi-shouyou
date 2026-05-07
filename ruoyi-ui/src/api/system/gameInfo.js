import request from '@/utils/request'

// 游戏列表
export function listGameInfo(query) {
  return request({
    url: '/system/gameInfo/list',
    method: 'get',
    params: query
  })
}

// 游戏详情
export function getGameInfo(id) {
  return request({
    url: '/system/gameInfo/' + id,
    method: 'get'
  })
}

// 新增游戏
export function addGameInfo(data) {
  return request({
    url: '/system/gameInfo',
    method: 'post',
    data: data
  })
}

// 修改游戏
export function updateGameInfo(data) {
  return request({
    url: '/system/gameInfo',
    method: 'put',
    data: data
  })
}

// 删除游戏
export function delGameInfo(ids) {
  return request({
    url: '/system/gameInfo/' + ids,
    method: 'delete'
  })
}
