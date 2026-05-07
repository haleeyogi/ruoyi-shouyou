package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.GameInfo;
import com.ruoyi.system.mapper.GameInfoMapper;
import com.ruoyi.system.service.GameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class GameInfoServiceImpl implements GameInfoService
{
    @Autowired
    private GameInfoMapper gameInfoMapper;

    @Override
    public List<GameInfo> selectGameInfoList(GameInfo gameInfo)
    {
        return gameInfoMapper.selectGameInfoList(gameInfo);
    }

    @Override
    public GameInfo selectGameInfoById(Long id)
    {
        return gameInfoMapper.selectGameInfoById(id);
    }

    @Override
    public int insertGameInfo(GameInfo gameInfo)
    {
        return gameInfoMapper.insertGameInfo(gameInfo);
    }

    @Override
    public int updateGameInfo(GameInfo gameInfo)
    {
        return gameInfoMapper.updateGameInfo(gameInfo);
    }

    @Override
    public int deleteGameInfoByIds(Long[] ids)
    {
        return gameInfoMapper.deleteGameInfoByIds(ids);
    }
}