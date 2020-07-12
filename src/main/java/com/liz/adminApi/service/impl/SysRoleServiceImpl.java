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
import com.liz.adminApi.mapper.SysRoleMapper;
import com.liz.adminApi.model.SysRole;
import com.liz.adminApi.model.query.SysRoleQuery;
import com.liz.adminApi.model.PageInfoDto;

import java.util.ArrayList;
import java.util.List;

import com.liz.adminApi.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysRoleServiceImpl extends GenericService<SysRole, Integer> implements SysRoleService {

    private static final Logger log = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    private SysRoleMapper sysRoleMapper;

    @Autowired
    public void setSysRoleMapper(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    /**
     * 通过主键查询实体对象
     * @param primaryKey
     * @return
     */
    public SysRole getByPK(java.lang.Integer primaryKey) {
        return sysRoleMapper.getByPK(primaryKey);
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<SysRole> list() {
        return sysRoleMapper.list();
    }

    /**
     * 根据查询条件查询所有记录
     * @return
     */
    public List<SysRole> listByProperty(SysRole sysRole){
        return sysRoleMapper.listByProperty(sysRole);
    }


    /**
     * 根据主键删除记录
     * @param primaryKey
     * @return
     */
    public int deleteByPK(java.lang.Integer primaryKey) {
        return sysRoleMapper.deleteByPK(primaryKey);
    }

    /**
     * 根据多个主键删除记录
     * @param primaryKeys
     */
    public void deleteByPKeys(List<java.lang.Integer> primaryKeys) {
        sysRoleMapper.deleteByPKeys(primaryKeys);
    }

    /**
     * 根据传入参数删除记录
     * @param sysRole
     * @return
     */
    public int deleteByProperty(SysRole sysRole){
        return sysRoleMapper.deleteByProperty(sysRole);
    }

    /**
     * 保存记录
     * @param sysRole
     * @return
     */
    public int save(SysRole sysRole){
        return sysRoleMapper.save(sysRole);
    }

    /**
     * 更新记录
     * @param sysRole
     * @return
     */
    public int update(SysRole sysRole){
        return sysRoleMapper.update(sysRole);
    }

    /**
     * 根据条件查询记录条数
     * @param sysRole
     * @return
     */
    public int findByCount(SysRole sysRole){
        return sysRoleMapper.findByCount(sysRole);
    }

    public int batchInsert(List<SysRole> sysRoleList){
        return sysRoleMapper.batchInsert(sysRoleList);
    }

    /**
     * 根据查询条件查询分页记录
     * @return
     */
    @Override
    public PageInfoDto<SysRole> findByPage(SysRoleQuery sysRoleQuery) {
        PageHelper.startPage(sysRoleQuery.getPageNum(), sysRoleQuery.getPageSize());
        List<SysRole> sysRoleList = sysRoleMapper.listByProperty(sysRoleQuery);
        PageInfoDto pageInfoDto = new PageInfoDto<SysRole>(sysRoleQuery.getPageNum(),sysRoleQuery.getPageSize(),0,0);
        if(null == sysRoleList || sysRoleList.size() == 0){
            pageInfoDto.setList(new ArrayList<SysRole>());
            return pageInfoDto;
        }
        Page pageList = (Page<SysRole>) sysRoleList;
        pageInfoDto.setList(pageList.getResult());
        pageInfoDto.setPageSize(pageList.getPageSize());
        pageInfoDto.setPageNum(pageList.getPageNum());
        pageInfoDto.setPages(pageList.getPages());
        pageInfoDto.setTotal(Integer.valueOf(pageList.getTotal()+""));
        return pageInfoDto;
    }

    @Override
    public int batchUpdate(List<java.lang.Long> idList, SysRole sysRole) {
        return sysRoleMapper.batchUpdate( idList, sysRole);
    }
}
