/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@ApiModel(value = "菜单功能表")
public class SysMenuFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    /*  */
    @ApiModelProperty(name = "id",value = "主键id",example = "",required = false)
    private java.lang.Long id;
    /* 菜单id */
    @ApiModelProperty(name = "menuId",value = "菜单id",example = "",required = true)
    private java.lang.Long menuId;
    /* 功能名称 */
    @ApiModelProperty(name = "name",value = "功能名称",example = "",required = true)
    private java.lang.String name;
    /* 功能编码 */
    @ApiModelProperty(name = "code",value = "功能编码",example = "",required = true)
    private java.lang.String code;
    /* 是否可用(0否，1是) */
    @ApiModelProperty(name = "enabled",value = "是否可用(0否，1是)",example = "",required = true)
    private java.lang.Integer enabled;
    /* 创建人 */
    @ApiModelProperty(name = "creatorId",value = "创建人",example = "",required = true)
    private java.lang.Long creatorId;
    /* 创建人姓名 */
    @ApiModelProperty(name = "createName",value = "创建人姓名",example = "",required = true)
    private java.lang.String createName;
    /* 创建时间 */
    @ApiModelProperty(name = "createTime",value = "创建时间",example = "",required = true)
    private java.sql.Timestamp createTime;
    /* 更新人 */
    @ApiModelProperty(name = "updaterId",value = "更新人",example = "",required = true)
    private java.lang.Long updaterId;
    /* 更新人姓名 */
    @ApiModelProperty(name = "updateName",value = "更新人姓名",example = "",required = true)
    private java.lang.String updateName;
    /* 更新时间 */
    @ApiModelProperty(name = "updateTime",value = "更新时间",example = "",required = true)
    private java.sql.Timestamp updateTime;
    /* 0未删除，1已删除 */
    @ApiModelProperty(name = "dropState",value = "0未删除，1已删除",example = "",required = true)
    private java.lang.Integer dropState;

    /* get  */
    public java.lang.Long getId() {
        return id;
    }

    /* set  */
    public void setId(java.lang.Long id) {
        this.id = id;
    }
    /* get 菜单id */
    public java.lang.Long getMenuId() {
        return menuId;
    }

    /* set 菜单id */
    public void setMenuId(java.lang.Long menuId) {
        this.menuId = menuId;
    }
    /* get 功能名称 */
    public java.lang.String getName() {
        return name;
    }

    /* set 功能名称 */
    public void setName(java.lang.String name) {
        this.name = name;
    }
    /* get 功能编码 */
    public java.lang.String getCode() {
        return code;
    }

    /* set 功能编码 */
    public void setCode(java.lang.String code) {
        this.code = code;
    }
    /* get 是否可用(0否，1是) */
    public java.lang.Integer getEnabled() {
        return enabled;
    }

    /* set 是否可用(0否，1是) */
    public void setEnabled(java.lang.Integer enabled) {
        this.enabled = enabled;
    }
    /* get 创建人 */
    public java.lang.Long getCreatorId() {
        return creatorId;
    }

    /* set 创建人 */
    public void setCreatorId(java.lang.Long creatorId) {
        this.creatorId = creatorId;
    }
    /* get 创建人姓名 */
    public java.lang.String getCreateName() {
        return createName;
    }

    /* set 创建人姓名 */
    public void setCreateName(java.lang.String createName) {
        this.createName = createName;
    }
    /* get 创建时间 */
    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    /* set 创建时间 */
    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }
    /* get 更新人 */
    public java.lang.Long getUpdaterId() {
        return updaterId;
    }

    /* set 更新人 */
    public void setUpdaterId(java.lang.Long updaterId) {
        this.updaterId = updaterId;
    }
    /* get 更新人姓名 */
    public java.lang.String getUpdateName() {
        return updateName;
    }

    /* set 更新人姓名 */
    public void setUpdateName(java.lang.String updateName) {
        this.updateName = updateName;
    }
    /* get 更新时间 */
    public java.sql.Timestamp getUpdateTime() {
        return updateTime;
    }

    /* set 更新时间 */
    public void setUpdateTime(java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    /* get 0未删除，1已删除 */
    public java.lang.Integer getDropState() {
        return dropState;
    }

    /* set 0未删除，1已删除 */
    public void setDropState(java.lang.Integer dropState) {
        this.dropState = dropState;
    }

    public String toString() {
        return "SysMenuFunction {" +
                    " id = " + id +
                    " , menuId = " + menuId +
                    " , name = " + name +
                    " , code = " + code +
                    " , enabled = " + enabled +
                    " , creatorId = " + creatorId +
                    " , createName = " + createName +
                    " , createTime = " + createTime +
                    " , updaterId = " + updaterId +
                    " , updateName = " + updateName +
                    " , updateTime = " + updateTime +
                    " , dropState = " + dropState +
            "}";
        }
}