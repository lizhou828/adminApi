/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.service;

import com.liz.adminApi.model.SysMenuFunction;
import com.liz.adminApi.model.PageInfoDto;
import com.liz.adminApi.model.query.SysMenuFunctionQuery;
import java.util.List;

/**
 */
public interface SysMenuFunctionService extends GenericIService<SysMenuFunction,Integer>{

        PageInfoDto<SysMenuFunction> findByPage(SysMenuFunctionQuery sysMenuFunctionQuery);

        int batchUpdate(List<java.lang.Long> idList, SysMenuFunction sysMenuFunction);

}
