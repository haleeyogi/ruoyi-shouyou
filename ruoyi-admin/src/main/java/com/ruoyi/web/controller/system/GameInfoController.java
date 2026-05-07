package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.GameInfo;
import com.ruoyi.system.service.GameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/system/gameInfo")
public class GameInfoController extends BaseController
{
    @Autowired
    private GameInfoService gameInfoService;

    @PreAuthorize("@ss.hasPermi('system:gameInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(GameInfo gameInfo)
    {
        startPage();
        List<GameInfo> list = gameInfoService.selectGameInfoList(gameInfo);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:gameInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return AjaxResult.success(gameInfoService.selectGameInfoById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:gameInfo:add')")
    @Log(title = "游戏管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GameInfo gameInfo)
    {
        return toAjax(gameInfoService.insertGameInfo(gameInfo));
    }

    @PreAuthorize("@ss.hasPermi('system:gameInfo:edit')")
    @Log(title = "游戏管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GameInfo gameInfo)
    {
        return toAjax(gameInfoService.updateGameInfo(gameInfo));
    }

    @PreAuthorize("@ss.hasPermi('system:gameInfo:remove')")
    @Log(title = "游戏管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gameInfoService.deleteGameInfoByIds(ids));
    }
}