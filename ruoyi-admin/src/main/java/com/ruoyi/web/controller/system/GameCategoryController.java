package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.GameCategory;
import com.ruoyi.system.service.GameCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/system/gameCategory")
public class GameCategoryController extends BaseController
{
    @Autowired
    private GameCategoryService gameCategoryService;

    @PreAuthorize("@ss.hasPermi('system:gameCategory:list')")
    @GetMapping("/list")
    public TableDataInfo list(GameCategory gameCategory)
    {
        startPage();
        List<GameCategory> list = gameCategoryService.selectGameCategoryList(gameCategory);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:gameCategory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return AjaxResult.success(gameCategoryService.selectGameCategoryById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:gameCategory:add')")
    @Log(title = "游戏分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GameCategory gameCategory)
    {
        return toAjax(gameCategoryService.insertGameCategory(gameCategory));
    }

    @PreAuthorize("@ss.hasPermi('system:gameCategory:edit')")
    @Log(title = "游戏分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GameCategory gameCategory)
    {
        return toAjax(gameCategoryService.updateGameCategory(gameCategory));
    }

    @PreAuthorize("@ss.hasPermi('system:gameCategory:remove')")
    @Log(title = "游戏分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gameCategoryService.deleteGameCategoryByIds(ids));
    }
}