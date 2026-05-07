package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.GameInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface GameInfoMapper
{
    List<GameInfo> selectGameInfoList(GameInfo gameInfo);
    GameInfo selectGameInfoById(Long id);
    int insertGameInfo(GameInfo gameInfo);
    int updateGameInfo(GameInfo gameInfo);
    int deleteGameInfoByIds(Long[] ids);
}