package com.ruoyi.system.service;

import com.ruoyi.system.domain.GameCategory;
import java.util.List;

public interface GameCategoryService
{
    List<GameCategory> selectGameCategoryList(GameCategory gameCategory);
    GameCategory selectGameCategoryById(Long id);
    int insertGameCategory(GameCategory gameCategory);
    int updateGameCategory(GameCategory gameCategory);
    int deleteGameCategoryByIds(Long[] ids);
}