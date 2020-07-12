/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.service;

import com.liz.adminApi.model.SysMenu;
import com.liz.adminApi.model.PageInfoDto;
import com.liz.adminApi.model.query.SysMenuQuery;
import java.util.List;

/**
 */
public interface SysMenuService extends GenericIService<SysMenu,Integer>{

        PageInfoDto<SysMenu> findByPage(SysMenuQuery sysMenuQuery);

        int batchUpdate(List<java.lang.Long> idList, SysMenu sysMenu);

}
