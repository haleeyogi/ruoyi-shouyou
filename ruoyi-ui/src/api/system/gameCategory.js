// src/api/system/gameCategory.js
import request from '@/utils/request'


// 查询游戏分类列表
export function listGameCategory(query) {
  return request({
    url: '/system/gameCategory/list',
    method: 'get',
    params: query
  })
}

// 其他增删改查接口，按需补充...
