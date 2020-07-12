/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.service;

import com.liz.adminApi.model.SysRoleMenu;
import com.liz.adminApi.model.PageInfoDto;
import com.liz.adminApi.model.query.SysRoleMenuQuery;
import java.util.List;

/**
 */
public interface SysRoleMenuService extends GenericIService<SysRoleMenu,Integer>{

        PageInfoDto<SysRoleMenu> findByPage(SysRoleMenuQuery sysRoleMenuQuery);

        int batchUpdate(List<java.lang.Long> idList, SysRoleMenu sysRoleMenu);

}
