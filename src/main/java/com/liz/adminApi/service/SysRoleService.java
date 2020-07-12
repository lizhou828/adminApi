/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.service;

import com.liz.adminApi.model.SysRole;
import com.liz.adminApi.model.PageInfoDto;
import com.liz.adminApi.model.query.SysRoleQuery;
import java.util.List;

/**
 */
public interface SysRoleService extends GenericIService<SysRole,Integer>{

        PageInfoDto<SysRole> findByPage(SysRoleQuery sysRoleQuery);

        int batchUpdate(List<java.lang.Long> idList, SysRole sysRole);

}
