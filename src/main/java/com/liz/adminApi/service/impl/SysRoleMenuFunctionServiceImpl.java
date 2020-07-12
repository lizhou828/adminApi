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
import com.liz.adminApi.mapper.SysRoleMenuFunctionMapper;
import com.liz.adminApi.model.SysRoleMenuFunction;
import com.liz.adminApi.model.query.SysRoleMenuFunctionQuery;
import com.liz.adminApi.model.PageInfoDto;

import java.util.ArrayList;
import java.util.List;

import com.liz.adminApi.service.SysRoleMenuFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysRoleMenuFunctionServiceImpl extends GenericService<SysRoleMenuFunction, Integer> implements SysRoleMenuFunctionService {

    private static final Logger log = LoggerFactory.getLogger(SysRoleMenuFunctionServiceImpl.class);

    private SysRoleMenuFunctionMapper sysRoleMenuFunctionMapper;

    @Autowired
    public void setSysRoleMenuFunctionMapper(SysRoleMenuFunctionMapper sysRoleMenuFunctionMapper) {
        this.sysRoleMenuFunctionMapper = sysRoleMenuFunctionMapper;
    }

    /**
     * 通过主键查询实体对象
     * @param primaryKey
     * @return
     */
    public SysRoleMenuFunction getByPK(java.lang.Integer primaryKey) {
        return sysRoleMenuFunctionMapper.getByPK(primaryKey);
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<SysRoleMenuFunction> list() {
        return sysRoleMenuFunctionMapper.list();
    }

    /**
     * 根据查询条件查询所有记录
     * @return
     */
    public List<SysRoleMenuFunction> listByProperty(SysRoleMenuFunction sysRoleMenuFunction){
        return sysRoleMenuFunctionMapper.listByProperty(sysRoleMenuFunction);
    }


    /**
     * 根据主键删除记录
     * @param primaryKey
     * @return
     */
    public int deleteByPK(java.lang.Integer primaryKey) {
        return sysRoleMenuFunctionMapper.deleteByPK(primaryKey);
    }

    /**
     * 根据多个主键删除记录
     * @param primaryKeys
     */
    public void deleteByPKeys(List<java.lang.Integer> primaryKeys) {
        sysRoleMenuFunctionMapper.deleteByPKeys(primaryKeys);
    }

    /**
     * 根据传入参数删除记录
     * @param sysRoleMenuFunction
     * @return
     */
    public int deleteByProperty(SysRoleMenuFunction sysRoleMenuFunction){
        return sysRoleMenuFunctionMapper.deleteByProperty(sysRoleMenuFunction);
    }

    /**
     * 保存记录
     * @param sysRoleMenuFunction
     * @return
     */
    public int save(SysRoleMenuFunction sysRoleMenuFunction){
        return sysRoleMenuFunctionMapper.save(sysRoleMenuFunction);
    }

    /**
     * 更新记录
     * @param sysRoleMenuFunction
     * @return
     */
    public int update(SysRoleMenuFunction sysRoleMenuFunction){
        return sysRoleMenuFunctionMapper.update(sysRoleMenuFunction);
    }

    /**
     * 根据条件查询记录条数
     * @param sysRoleMenuFunction
     * @return
     */
    public int findByCount(SysRoleMenuFunction sysRoleMenuFunction){
        return sysRoleMenuFunctionMapper.findByCount(sysRoleMenuFunction);
    }

    public int batchInsert(List<SysRoleMenuFunction> sysRoleMenuFunctionList){
        return sysRoleMenuFunctionMapper.batchInsert(sysRoleMenuFunctionList);
    }

    /**
     * 根据查询条件查询分页记录
     * @return
     */
    @Override
    public PageInfoDto<SysRoleMenuFunction> findByPage(SysRoleMenuFunctionQuery sysRoleMenuFunctionQuery) {
        PageHelper.startPage(sysRoleMenuFunctionQuery.getPageNum(), sysRoleMenuFunctionQuery.getPageSize());
        List<SysRoleMenuFunction> sysRoleMenuFunctionList = sysRoleMenuFunctionMapper.listByProperty(sysRoleMenuFunctionQuery);
        PageInfoDto pageInfoDto = new PageInfoDto<SysRoleMenuFunction>(sysRoleMenuFunctionQuery.getPageNum(),sysRoleMenuFunctionQuery.getPageSize(),0,0);
        if(null == sysRoleMenuFunctionList || sysRoleMenuFunctionList.size() == 0){
            pageInfoDto.setList(new ArrayList<SysRoleMenuFunction>());
            return pageInfoDto;
        }
        Page pageList = (Page<SysRoleMenuFunction>) sysRoleMenuFunctionList;
        pageInfoDto.setList(pageList.getResult());
        pageInfoDto.setPageSize(pageList.getPageSize());
        pageInfoDto.setPageNum(pageList.getPageNum());
        pageInfoDto.setPages(pageList.getPages());
        pageInfoDto.setTotal(Integer.valueOf(pageList.getTotal()+""));
        return pageInfoDto;
    }

    @Override
    public int batchUpdate(List<java.lang.Long> idList, SysRoleMenuFunction sysRoleMenuFunction) {
        return sysRoleMenuFunctionMapper.batchUpdate( idList, sysRoleMenuFunction);
    }
}
