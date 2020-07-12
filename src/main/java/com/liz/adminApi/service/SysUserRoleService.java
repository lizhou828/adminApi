/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.service;

import com.liz.adminApi.model.SysUserRole;
import com.liz.adminApi.model.PageInfoDto;
import com.liz.adminApi.model.query.SysUserRoleQuery;
import java.util.List;

/**
 */
public interface SysUserRoleService extends GenericIService<SysUserRole,Integer>{

        PageInfoDto<SysUserRole> findByPage(SysUserRoleQuery sysUserRoleQuery);

        int batchUpdate(List<java.lang.Long> idList, SysUserRole sysUserRole);

}
