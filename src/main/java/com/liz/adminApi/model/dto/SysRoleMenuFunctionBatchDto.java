/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.model.dto;

import com.liz.adminApi.model.SysRoleMenuFunction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@ApiModel(value = "角色与菜单功能表批量DTO")
public class SysRoleMenuFunctionBatchDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "idList",value = "id集合",example = "",required = true)
    private List<java.lang.Long> idList;

    @ApiModelProperty(name = "sysRoleMenuFunction",value = "商品sku",example = "",required = true)
    private SysRoleMenuFunction sysRoleMenuFunction;

    @ApiModelProperty(name = "sysRoleMenuFunctionList",value = "集合(批量新增时使用)",example = "",required = true)
    private List<SysRoleMenuFunction> sysRoleMenuFunctionList;

    public List<java.lang.Long> getIdList() {
        return idList;
    }

    public void setIdList(List<java.lang.Long> idList) {
        this.idList = idList;
    }

    public SysRoleMenuFunction getSysRoleMenuFunction() {
        return sysRoleMenuFunction;
    }

    public void setSysRoleMenuFunction(SysRoleMenuFunction sysRoleMenuFunction) {
        this.sysRoleMenuFunction = sysRoleMenuFunction;
    }

    public List<SysRoleMenuFunction> getSysRoleMenuFunctionList() {
        return sysRoleMenuFunctionList;
    }

    public void setSysRoleMenuFunctionList(List<SysRoleMenuFunction> sysRoleMenuFunctionList) {
        this.sysRoleMenuFunctionList = sysRoleMenuFunctionList;
    }

    @Override
    public String toString() {
        return "SysRoleMenuFunctionBatchDto{" +
            "idList=" + idList +
            ", sysRoleMenuFunction=" + sysRoleMenuFunction +
            ", sysRoleMenuFunctionList=" + sysRoleMenuFunctionList +
        '}';
    }
}