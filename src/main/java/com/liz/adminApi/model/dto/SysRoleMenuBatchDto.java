/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.model.dto;

import com.liz.adminApi.model.SysRoleMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@ApiModel(value = "角色菜单关联表批量DTO")
public class SysRoleMenuBatchDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "idList",value = "id集合",example = "",required = true)
    private List<java.lang.Long> idList;

    @ApiModelProperty(name = "sysRoleMenu",value = "商品sku",example = "",required = true)
    private SysRoleMenu sysRoleMenu;

    @ApiModelProperty(name = "sysRoleMenuList",value = "集合(批量新增时使用)",example = "",required = true)
    private List<SysRoleMenu> sysRoleMenuList;

    public List<java.lang.Long> getIdList() {
        return idList;
    }

    public void setIdList(List<java.lang.Long> idList) {
        this.idList = idList;
    }

    public SysRoleMenu getSysRoleMenu() {
        return sysRoleMenu;
    }

    public void setSysRoleMenu(SysRoleMenu sysRoleMenu) {
        this.sysRoleMenu = sysRoleMenu;
    }

    public List<SysRoleMenu> getSysRoleMenuList() {
        return sysRoleMenuList;
    }

    public void setSysRoleMenuList(List<SysRoleMenu> sysRoleMenuList) {
        this.sysRoleMenuList = sysRoleMenuList;
    }

    @Override
    public String toString() {
        return "SysRoleMenuBatchDto{" +
            "idList=" + idList +
            ", sysRoleMenu=" + sysRoleMenu +
            ", sysRoleMenuList=" + sysRoleMenuList +
        '}';
    }
}