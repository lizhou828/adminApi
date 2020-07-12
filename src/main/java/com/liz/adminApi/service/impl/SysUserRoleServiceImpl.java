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
import com.liz.adminApi.mapper.SysUserRoleMapper;
import com.liz.adminApi.model.SysUserRole;
import com.liz.adminApi.model.query.SysUserRoleQuery;
import com.liz.adminApi.model.PageInfoDto;

import java.util.ArrayList;
import java.util.List;

import com.liz.adminApi.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysUserRoleServiceImpl extends GenericService<SysUserRole, Integer> implements SysUserRoleService {

    private static final Logger log = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    public void setSysUserRoleMapper(SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    /**
     * 通过主键查询实体对象
     * @param primaryKey
     * @return
     */
    public SysUserRole getByPK(java.lang.Integer primaryKey) {
        return sysUserRoleMapper.getByPK(primaryKey);
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<SysUserRole> list() {
        return sysUserRoleMapper.list();
    }

    /**
     * 根据查询条件查询所有记录
     * @return
     */
    public List<SysUserRole> listByProperty(SysUserRole sysUserRole){
        return sysUserRoleMapper.listByProperty(sysUserRole);
    }


    /**
     * 根据主键删除记录
     * @param primaryKey
     * @return
     */
    public int deleteByPK(java.lang.Integer primaryKey) {
        return sysUserRoleMapper.deleteByPK(primaryKey);
    }

    /**
     * 根据多个主键删除记录
     * @param primaryKeys
     */
    public void deleteByPKeys(List<java.lang.Integer> primaryKeys) {
        sysUserRoleMapper.deleteByPKeys(primaryKeys);
    }

    /**
     * 根据传入参数删除记录
     * @param sysUserRole
     * @return
     */
    public int deleteByProperty(SysUserRole sysUserRole){
        return sysUserRoleMapper.deleteByProperty(sysUserRole);
    }

    /**
     * 保存记录
     * @param sysUserRole
     * @return
     */
    public int save(SysUserRole sysUserRole){
        return sysUserRoleMapper.save(sysUserRole);
    }

    /**
     * 更新记录
     * @param sysUserRole
     * @return
     */
    public int update(SysUserRole sysUserRole){
        return sysUserRoleMapper.update(sysUserRole);
    }

    /**
     * 根据条件查询记录条数
     * @param sysUserRole
     * @return
     */
    public int findByCount(SysUserRole sysUserRole){
        return sysUserRoleMapper.findByCount(sysUserRole);
    }

    public int batchInsert(List<SysUserRole> sysUserRoleList){
        return sysUserRoleMapper.batchInsert(sysUserRoleList);
    }

    /**
     * 根据查询条件查询分页记录
     * @return
     */
    @Override
    public PageInfoDto<SysUserRole> findByPage(SysUserRoleQuery sysUserRoleQuery) {
        PageHelper.startPage(sysUserRoleQuery.getPageNum(), sysUserRoleQuery.getPageSize());
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.listByProperty(sysUserRoleQuery);
        PageInfoDto pageInfoDto = new PageInfoDto<SysUserRole>(sysUserRoleQuery.getPageNum(),sysUserRoleQuery.getPageSize(),0,0);
        if(null == sysUserRoleList || sysUserRoleList.size() == 0){
            pageInfoDto.setList(new ArrayList<SysUserRole>());
            return pageInfoDto;
        }
        Page pageList = (Page<SysUserRole>) sysUserRoleList;
        pageInfoDto.setList(pageList.getResult());
        pageInfoDto.setPageSize(pageList.getPageSize());
        pageInfoDto.setPageNum(pageList.getPageNum());
        pageInfoDto.setPages(pageList.getPages());
        pageInfoDto.setTotal(Integer.valueOf(pageList.getTotal()+""));
        return pageInfoDto;
    }

    @Override
    public int batchUpdate(List<java.lang.Long> idList, SysUserRole sysUserRole) {
        return sysUserRoleMapper.batchUpdate( idList, sysUserRole);
    }
}
