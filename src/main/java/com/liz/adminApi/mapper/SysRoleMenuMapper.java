/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.mapper;


import com.liz.adminApi.model.SysRoleMenu;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SysRoleMenuMapper extends GenericIBatisMapper <SysRoleMenu, Integer>{

        int batchUpdate(@Param("idList") List<java.lang.Long> idList, @Param("sysRoleMenu") SysRoleMenu sysRoleMenu);

}