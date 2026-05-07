package com.ruoyi.web.controller.api;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.GameCategory;
import com.ruoyi.system.domain.GameInfo;
import com.ruoyi.system.service.GameCategoryService;
import com.ruoyi.system.service.GameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 手游盒子H5首页公开接口
 */
@RestController
@RequestMapping("/api/game")
public class GameApiController extends BaseController {

    @Autowired
    private GameInfoService gameInfoService;
    private GameCategoryService gameCategoryService;
    /**
     * 首页轮播图（推荐+上架+排序靠前）
     */
    @GetMapping("/banner")
    public AjaxResult getBanner() {
        GameInfo query = new GameInfo();
        query.setStatus(1); // 上架
        query.setIsRecommend(1); // 推荐
        List<GameInfo> list = gameInfoService.selectGameInfoList(query);
        return AjaxResult.success(list);
    }

    /**
     * 首页热门游戏（下载量排序）
     */
    @GetMapping("/hot")
    public AjaxResult getHot() {
        GameInfo query = new GameInfo();
        query.setStatus(1);
        List<GameInfo> list = gameInfoService.selectGameInfoList(query);
        // 按下载量倒序
        list.sort((a, b) -> Integer.compare(b.getDownloadCount(), a.getDownloadCount()));
        return AjaxResult.success(list);
    }

    /**
     * 首页最新游戏（创建时间倒序）
     */
    @GetMapping("/new")
    public AjaxResult getNew() {
        GameInfo query = new GameInfo();
        query.setStatus(1);
        List<GameInfo> list = gameInfoService.selectGameInfoList(query);
        return AjaxResult.success(list);
    }

    /**
     * 游戏详情
     */
    @GetMapping("/detail/{id}")
    public AjaxResult detail(@PathVariable Long id) {
        GameInfo game = gameInfoService.selectGameInfoById(id);
        return AjaxResult.success(game);
    }
    /**
     * 游戏下载（直接返回下载链接）
     */
    @GetMapping("/download/{id}")
    public AjaxResult download(@PathVariable Long id) {
        GameInfo game = gameInfoService.selectGameInfoById(id);
        if (game == null) {
            return AjaxResult.error("游戏不存在");
        }
        // 下载量+1
        game.setDownloadCount(game.getDownloadCount() == null ? 1 : game.getDownloadCount() + 1);
        gameInfoService.updateGameInfo(game);
        return AjaxResult.success(game.getGameDownloadUrl());
    }
    /**
     * 获取全部分类
     */
    @GetMapping("/category/list")
    public AjaxResult getCategoryList(){
        List<GameCategory> list = gameCategoryService.selectGameCategoryList(new GameCategory());
        return AjaxResult.success(list);
    }

    /**
     * 根据分类ID获取游戏
     */
    @GetMapping("/game/byCat/{catId}")
    public AjaxResult getGameByCat(@PathVariable Long catId){
        GameInfo query = new GameInfo();
        query.setCatId(catId);
        query.setStatus(1);
        List<GameInfo> list = gameInfoService.selectGameInfoList(query);
        return AjaxResult.success(list);
    }

    /**
     * 游戏排行榜（按下载量倒序）
     */
    @GetMapping("/rank")
    public AjaxResult getRank(){
        GameInfo query = new GameInfo();
        query.setStatus(1);
        List<GameInfo> list = gameInfoService.selectGameInfoList(query);
        list.sort((a,b)->Integer.compare(b.getDownloadCount()==null?0:b.getDownloadCount(),
                a.getDownloadCount()==null?0:a.getDownloadCount()));
        return AjaxResult.success(list);
    }

    /**
     * 游戏搜索
     */
    @GetMapping("/search")
    public AjaxResult searchGame(@RequestParam String keyword){
        GameInfo query = new GameInfo();
        query.setGameName(keyword);
        query.setStatus(1);
        List<GameInfo> list = gameInfoService.selectGameInfoList(query);
        return AjaxResult.success(list);
    }
}