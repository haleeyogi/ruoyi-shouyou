package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class GameInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "分类ID")
    private Long catId;

    @Excel(name = "游戏名称")
    private String gameName;

    @Excel(name = "游戏图标")
    private String gameIcon;

    @Excel(name = "封面图")
    private String gameCover;

    @Excel(name = "安装包大小")
    private String gameSize;

    @Excel(name = "版本号")
    private String gameVersion;

    private String gameDesc;

    private String gameDownloadUrl;

    private String gameH5Url;

    private Integer downloadCount;

    @Excel(name = "是否推荐", readConverterExp = "0=否,1=是")
    private Integer isRecommend;

    @Excel(name = "排序")
    private Integer sortNum;

    @Excel(name = "状态", readConverterExp = "1=上架,0=下架")
    private Integer status;
}