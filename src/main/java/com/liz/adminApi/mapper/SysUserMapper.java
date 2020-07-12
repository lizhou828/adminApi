/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.mapper;


import com.liz.adminApi.model.SysUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SysUserMapper extends GenericIBatisMapper <SysUser, Integer>{

        int batchUpdate(@Param("idList") List<java.lang.Long> idList, @Param("sysUser") SysUser sysUser);

}