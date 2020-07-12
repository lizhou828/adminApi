/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.model.dto;

import com.liz.adminApi.model.SysMenuFunction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@ApiModel(value = "菜单功能表批量DTO")
public class SysMenuFunctionBatchDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "idList",value = "id集合",example = "",required = true)
    private List<java.lang.Long> idList;

    @ApiModelProperty(name = "sysMenuFunction",value = "商品sku",example = "",required = true)
    private SysMenuFunction sysMenuFunction;

    @ApiModelProperty(name = "sysMenuFunctionList",value = "集合(批量新增时使用)",example = "",required = true)
    private List<SysMenuFunction> sysMenuFunctionList;

    public List<java.lang.Long> getIdList() {
        return idList;
    }

    public void setIdList(List<java.lang.Long> idList) {
        this.idList = idList;
    }

    public SysMenuFunction getSysMenuFunction() {
        return sysMenuFunction;
    }

    public void setSysMenuFunction(SysMenuFunction sysMenuFunction) {
        this.sysMenuFunction = sysMenuFunction;
    }

    public List<SysMenuFunction> getSysMenuFunctionList() {
        return sysMenuFunctionList;
    }

    public void setSysMenuFunctionList(List<SysMenuFunction> sysMenuFunctionList) {
        this.sysMenuFunctionList = sysMenuFunctionList;
    }

    @Override
    public String toString() {
        return "SysMenuFunctionBatchDto{" +
            "idList=" + idList +
            ", sysMenuFunction=" + sysMenuFunction +
            ", sysMenuFunctionList=" + sysMenuFunctionList +
        '}';
    }
}