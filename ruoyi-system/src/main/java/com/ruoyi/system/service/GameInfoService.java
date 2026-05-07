package com.ruoyi.system.service;

import com.ruoyi.system.domain.GameInfo;
import java.util.List;

public interface GameInfoService
{
    List<GameInfo> selectGameInfoList(GameInfo gameInfo);
    GameInfo selectGameInfoById(Long id);
    int insertGameInfo(GameInfo gameInfo);
    int updateGameInfo(GameInfo gameInfo);
    int deleteGameInfoByIds(Long[] ids);
}