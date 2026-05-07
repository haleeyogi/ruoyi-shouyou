package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.GameCategory;
import com.ruoyi.system.mapper.GameCategoryMapper;
import com.ruoyi.system.service.GameCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameCategoryServiceImpl implements GameCategoryService
{
    @Autowired
    private GameCategoryMapper gameCategoryMapper;

    @Override
    public List<GameCategory> selectGameCategoryList(GameCategory gameCategory)
    {
        return gameCategoryMapper.selectGameCategoryList(gameCategory);
    }

    @Override
    public GameCategory selectGameCategoryById(Long id)
    {
        return gameCategoryMapper.selectGameCategoryById(id);
    }

    @Override
    public int insertGameCategory(GameCategory gameCategory)
    {
        return gameCategoryMapper.insertGameCategory(gameCategory);
    }

    @Override
    public int updateGameCategory(GameCategory gameCategory)
    {
        return gameCategoryMapper.updateGameCategory(gameCategory);
    }

    @Override
    public int deleteGameCategoryByIds(Long[] ids)
    {
        return gameCategoryMapper.deleteGameCategoryByIds(ids);
    }
}