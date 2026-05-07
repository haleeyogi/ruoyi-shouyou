package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class GameCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "分类名称")
    private String catName;

    @Excel(name = "分类图标")
    private String catIcon;

    @Excel(name = "排序")
    private Integer sortNum;

    @Excel(name = "状态", readConverterExp = "1=正常,0=禁用")
    private Integer status;
}