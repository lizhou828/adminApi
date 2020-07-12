/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.model.dto;

import com.liz.adminApi.model.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@ApiModel(value = "系统用户批量DTO")
public class SysUserBatchDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "idList",value = "id集合",example = "",required = true)
    private List<java.lang.Long> idList;

    @ApiModelProperty(name = "sysUser",value = "商品sku",example = "",required = true)
    private SysUser sysUser;

    @ApiModelProperty(name = "sysUserList",value = "集合(批量新增时使用)",example = "",required = true)
    private List<SysUser> sysUserList;

    public List<java.lang.Long> getIdList() {
        return idList;
    }

    public void setIdList(List<java.lang.Long> idList) {
        this.idList = idList;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public List<SysUser> getSysUserList() {
        return sysUserList;
    }

    public void setSysUserList(List<SysUser> sysUserList) {
        this.sysUserList = sysUserList;
    }

    @Override
    public String toString() {
        return "SysUserBatchDto{" +
            "idList=" + idList +
            ", sysUser=" + sysUser +
            ", sysUserList=" + sysUserList +
        '}';
    }
}