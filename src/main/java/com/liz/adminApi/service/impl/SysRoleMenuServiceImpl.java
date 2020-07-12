/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liz.adminApi.mapper.SysRoleMenuMapper;
import com.liz.adminApi.model.SysRoleMenu;
import com.liz.adminApi.model.query.SysRoleMenuQuery;
import com.liz.adminApi.model.PageInfoDto;

import java.util.ArrayList;
import java.util.List;

import com.liz.adminApi.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysRoleMenuServiceImpl extends GenericService<SysRoleMenu, Integer> implements SysRoleMenuService {

    private static final Logger log = LoggerFactory.getLogger(SysRoleMenuServiceImpl.class);

    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    public void setSysRoleMenuMapper(SysRoleMenuMapper sysRoleMenuMapper) {
        this.sysRoleMenuMapper = sysRoleMenuMapper;
    }

    /**
     * 通过主键查询实体对象
     * @param primaryKey
     * @return
     */
    public SysRoleMenu getByPK(java.lang.Integer primaryKey) {
        return sysRoleMenuMapper.getByPK(primaryKey);
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<SysRoleMenu> list() {
        return sysRoleMenuMapper.list();
    }

    /**
     * 根据查询条件查询所有记录
     * @return
     */
    public List<SysRoleMenu> listByProperty(SysRoleMenu sysRoleMenu){
        return sysRoleMenuMapper.listByProperty(sysRoleMenu);
    }


    /**
     * 根据主键删除记录
     * @param primaryKey
     * @return
     */
    public int deleteByPK(java.lang.Integer primaryKey) {
        return sysRoleMenuMapper.deleteByPK(primaryKey);
    }

    /**
     * 根据多个主键删除记录
     * @param primaryKeys
     */
    public void deleteByPKeys(List<java.lang.Integer> primaryKeys) {
        sysRoleMenuMapper.deleteByPKeys(primaryKeys);
    }

    /**
     * 根据传入参数删除记录
     * @param sysRoleMenu
     * @return
     */
    public int deleteByProperty(SysRoleMenu sysRoleMenu){
        return sysRoleMenuMapper.deleteByProperty(sysRoleMenu);
    }

    /**
     * 保存记录
     * @param sysRoleMenu
     * @return
     */
    public int save(SysRoleMenu sysRoleMenu){
        return sysRoleMenuMapper.save(sysRoleMenu);
    }

    /**
     * 更新记录
     * @param sysRoleMenu
     * @return
     */
    public int update(SysRoleMenu sysRoleMenu){
        return sysRoleMenuMapper.update(sysRoleMenu);
    }

    /**
     * 根据条件查询记录条数
     * @param sysRoleMenu
     * @return
     */
    public int findByCount(SysRoleMenu sysRoleMenu){
        return sysRoleMenuMapper.findByCount(sysRoleMenu);
    }

    public int batchInsert(List<SysRoleMenu> sysRoleMenuList){
        return sysRoleMenuMapper.batchInsert(sysRoleMenuList);
    }

    /**
     * 根据查询条件查询分页记录
     * @return
     */
    @Override
    public PageInfoDto<SysRoleMenu> findByPage(SysRoleMenuQuery sysRoleMenuQuery) {
        PageHelper.startPage(sysRoleMenuQuery.getPageNum(), sysRoleMenuQuery.getPageSize());
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuMapper.listByProperty(sysRoleMenuQuery);
        PageInfoDto pageInfoDto = new PageInfoDto<SysRoleMenu>(sysRoleMenuQuery.getPageNum(),sysRoleMenuQuery.getPageSize(),0,0);
        if(null == sysRoleMenuList || sysRoleMenuList.size() == 0){
            pageInfoDto.setList(new ArrayList<SysRoleMenu>());
            return pageInfoDto;
        }
        Page pageList = (Page<SysRoleMenu>) sysRoleMenuList;
        pageInfoDto.setList(pageList.getResult());
        pageInfoDto.setPageSize(pageList.getPageSize());
        pageInfoDto.setPageNum(pageList.getPageNum());
        pageInfoDto.setPages(pageList.getPages());
        pageInfoDto.setTotal(Integer.valueOf(pageList.getTotal()+""));
        return pageInfoDto;
    }

    @Override
    public int batchUpdate(List<java.lang.Long> idList, SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.batchUpdate( idList, sysRoleMenu);
    }
}
