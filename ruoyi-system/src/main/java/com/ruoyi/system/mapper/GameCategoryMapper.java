package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.GameCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface GameCategoryMapper
{
    List<GameCategory> selectGameCategoryList(GameCategory gameCategory);
    GameCategory selectGameCategoryById(Long id);
    int insertGameCategory(GameCategory gameCategory);
    int updateGameCategory(GameCategory gameCategory);
    int deleteGameCategoryByIds(Long[] ids);
}