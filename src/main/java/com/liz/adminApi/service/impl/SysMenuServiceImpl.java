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
import com.liz.adminApi.mapper.SysMenuMapper;
import com.liz.adminApi.model.SysMenu;
import com.liz.adminApi.model.query.SysMenuQuery;
import com.liz.adminApi.model.PageInfoDto;

import java.util.ArrayList;
import java.util.List;

import com.liz.adminApi.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysMenuServiceImpl extends GenericService<SysMenu, Integer> implements SysMenuService {

    private static final Logger log = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    private SysMenuMapper sysMenuMapper;

    @Autowired
    public void setSysMenuMapper(SysMenuMapper sysMenuMapper) {
        this.sysMenuMapper = sysMenuMapper;
    }

    /**
     * 通过主键查询实体对象
     * @param primaryKey
     * @return
     */
    public SysMenu getByPK(java.lang.Integer primaryKey) {
        return sysMenuMapper.getByPK(primaryKey);
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<SysMenu> list() {
        return sysMenuMapper.list();
    }

    /**
     * 根据查询条件查询所有记录
     * @return
     */
    public List<SysMenu> listByProperty(SysMenu sysMenu){
        return sysMenuMapper.listByProperty(sysMenu);
    }


    /**
     * 根据主键删除记录
     * @param primaryKey
     * @return
     */
    public int deleteByPK(java.lang.Integer primaryKey) {
        return sysMenuMapper.deleteByPK(primaryKey);
    }

    /**
     * 根据多个主键删除记录
     * @param primaryKeys
     */
    public void deleteByPKeys(List<java.lang.Integer> primaryKeys) {
        sysMenuMapper.deleteByPKeys(primaryKeys);
    }

    /**
     * 根据传入参数删除记录
     * @param sysMenu
     * @return
     */
    public int deleteByProperty(SysMenu sysMenu){
        return sysMenuMapper.deleteByProperty(sysMenu);
    }

    /**
     * 保存记录
     * @param sysMenu
     * @return
     */
    public int save(SysMenu sysMenu){
        return sysMenuMapper.save(sysMenu);
    }

    /**
     * 更新记录
     * @param sysMenu
     * @return
     */
    public int update(SysMenu sysMenu){
        return sysMenuMapper.update(sysMenu);
    }

    /**
     * 根据条件查询记录条数
     * @param sysMenu
     * @return
     */
    public int findByCount(SysMenu sysMenu){
        return sysMenuMapper.findByCount(sysMenu);
    }

    public int batchInsert(List<SysMenu> sysMenuList){
        return sysMenuMapper.batchInsert(sysMenuList);
    }

    /**
     * 根据查询条件查询分页记录
     * @return
     */
    @Override
    public PageInfoDto<SysMenu> findByPage(SysMenuQuery sysMenuQuery) {
        PageHelper.startPage(sysMenuQuery.getPageNum(), sysMenuQuery.getPageSize());
        List<SysMenu> sysMenuList = sysMenuMapper.listByProperty(sysMenuQuery);
        PageInfoDto pageInfoDto = new PageInfoDto<SysMenu>(sysMenuQuery.getPageNum(),sysMenuQuery.getPageSize(),0,0);
        if(null == sysMenuList || sysMenuList.size() == 0){
            pageInfoDto.setList(new ArrayList<SysMenu>());
            return pageInfoDto;
        }
        Page pageList = (Page<SysMenu>) sysMenuList;
        pageInfoDto.setList(pageList.getResult());
        pageInfoDto.setPageSize(pageList.getPageSize());
        pageInfoDto.setPageNum(pageList.getPageNum());
        pageInfoDto.setPages(pageList.getPages());
        pageInfoDto.setTotal(Integer.valueOf(pageList.getTotal()+""));
        return pageInfoDto;
    }

    @Override
    public int batchUpdate(List<java.lang.Long> idList, SysMenu sysMenu) {
        return sysMenuMapper.batchUpdate( idList, sysMenu);
    }
}
