/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.service;

import com.liz.adminApi.model.SysRoleMenuFunction;
import com.liz.adminApi.model.PageInfoDto;
import com.liz.adminApi.model.query.SysRoleMenuFunctionQuery;
import java.util.List;

/**
 */
public interface SysRoleMenuFunctionService extends GenericIService<SysRoleMenuFunction,Integer>{

        PageInfoDto<SysRoleMenuFunction> findByPage(SysRoleMenuFunctionQuery sysRoleMenuFunctionQuery);

        int batchUpdate(List<java.lang.Long> idList, SysRoleMenuFunction sysRoleMenuFunction);

}
