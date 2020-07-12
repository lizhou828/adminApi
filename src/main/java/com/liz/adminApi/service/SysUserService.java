/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.service;

import com.liz.adminApi.model.SysUser;
import com.liz.adminApi.model.PageInfoDto;
import com.liz.adminApi.model.query.SysUserQuery;
import java.util.List;

/**
 */
public interface SysUserService extends GenericIService<SysUser,Integer>{

        PageInfoDto<SysUser> findByPage(SysUserQuery sysUserQuery);

        int batchUpdate(List<java.lang.Long> idList, SysUser sysUser);

}
