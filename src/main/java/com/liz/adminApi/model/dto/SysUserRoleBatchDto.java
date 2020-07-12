/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.model.dto;

import com.liz.adminApi.model.SysUserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@ApiModel(value = "用户角色关联表批量DTO")
public class SysUserRoleBatchDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "idList",value = "id集合",example = "",required = true)
    private List<java.lang.Long> idList;

    @ApiModelProperty(name = "sysUserRole",value = "商品sku",example = "",required = true)
    private SysUserRole sysUserRole;

    @ApiModelProperty(name = "sysUserRoleList",value = "集合(批量新增时使用)",example = "",required = true)
    private List<SysUserRole> sysUserRoleList;

    public List<java.lang.Long> getIdList() {
        return idList;
    }

    public void setIdList(List<java.lang.Long> idList) {
        this.idList = idList;
    }

    public SysUserRole getSysUserRole() {
        return sysUserRole;
    }

    public void setSysUserRole(SysUserRole sysUserRole) {
        this.sysUserRole = sysUserRole;
    }

    public List<SysUserRole> getSysUserRoleList() {
        return sysUserRoleList;
    }

    public void setSysUserRoleList(List<SysUserRole> sysUserRoleList) {
        this.sysUserRoleList = sysUserRoleList;
    }

    @Override
    public String toString() {
        return "SysUserRoleBatchDto{" +
            "idList=" + idList +
            ", sysUserRole=" + sysUserRole +
            ", sysUserRoleList=" + sysUserRoleList +
        '}';
    }
}