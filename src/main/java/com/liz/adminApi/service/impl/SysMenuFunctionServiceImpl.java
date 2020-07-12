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
import com.liz.adminApi.mapper.SysMenuFunctionMapper;
import com.liz.adminApi.model.SysMenuFunction;
import com.liz.adminApi.model.query.SysMenuFunctionQuery;
import com.liz.adminApi.model.PageInfoDto;

import java.util.ArrayList;
import java.util.List;

import com.liz.adminApi.service.SysMenuFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysMenuFunctionServiceImpl extends GenericService<SysMenuFunction, Integer> implements SysMenuFunctionService {

    private static final Logger log = LoggerFactory.getLogger(SysMenuFunctionServiceImpl.class);

    private SysMenuFunctionMapper sysMenuFunctionMapper;

    @Autowired
    public void setSysMenuFunctionMapper(SysMenuFunctionMapper sysMenuFunctionMapper) {
        this.sysMenuFunctionMapper = sysMenuFunctionMapper;
    }

    /**
     * 通过主键查询实体对象
     * @param primaryKey
     * @return
     */
    public SysMenuFunction getByPK(java.lang.Integer primaryKey) {
        return sysMenuFunctionMapper.getByPK(primaryKey);
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<SysMenuFunction> list() {
        return sysMenuFunctionMapper.list();
    }

    /**
     * 根据查询条件查询所有记录
     * @return
     */
    public List<SysMenuFunction> listByProperty(SysMenuFunction sysMenuFunction){
        return sysMenuFunctionMapper.listByProperty(sysMenuFunction);
    }


    /**
     * 根据主键删除记录
     * @param primaryKey
     * @return
     */
    public int deleteByPK(java.lang.Integer primaryKey) {
        return sysMenuFunctionMapper.deleteByPK(primaryKey);
    }

    /**
     * 根据多个主键删除记录
     * @param primaryKeys
     */
    public void deleteByPKeys(List<java.lang.Integer> primaryKeys) {
        sysMenuFunctionMapper.deleteByPKeys(primaryKeys);
    }

    /**
     * 根据传入参数删除记录
     * @param sysMenuFunction
     * @return
     */
    public int deleteByProperty(SysMenuFunction sysMenuFunction){
        return sysMenuFunctionMapper.deleteByProperty(sysMenuFunction);
    }

    /**
     * 保存记录
     * @param sysMenuFunction
     * @return
     */
    public int save(SysMenuFunction sysMenuFunction){
        return sysMenuFunctionMapper.save(sysMenuFunction);
    }

    /**
     * 更新记录
     * @param sysMenuFunction
     * @return
     */
    public int update(SysMenuFunction sysMenuFunction){
        return sysMenuFunctionMapper.update(sysMenuFunction);
    }

    /**
     * 根据条件查询记录条数
     * @param sysMenuFunction
     * @return
     */
    public int findByCount(SysMenuFunction sysMenuFunction){
        return sysMenuFunctionMapper.findByCount(sysMenuFunction);
    }

    public int batchInsert(List<SysMenuFunction> sysMenuFunctionList){
        return sysMenuFunctionMapper.batchInsert(sysMenuFunctionList);
    }

    /**
     * 根据查询条件查询分页记录
     * @return
     */
    @Override
    public PageInfoDto<SysMenuFunction> findByPage(SysMenuFunctionQuery sysMenuFunctionQuery) {
        PageHelper.startPage(sysMenuFunctionQuery.getPageNum(), sysMenuFunctionQuery.getPageSize());
        List<SysMenuFunction> sysMenuFunctionList = sysMenuFunctionMapper.listByProperty(sysMenuFunctionQuery);
        PageInfoDto pageInfoDto = new PageInfoDto<SysMenuFunction>(sysMenuFunctionQuery.getPageNum(),sysMenuFunctionQuery.getPageSize(),0,0);
        if(null == sysMenuFunctionList || sysMenuFunctionList.size() == 0){
            pageInfoDto.setList(new ArrayList<SysMenuFunction>());
            return pageInfoDto;
        }
        Page pageList = (Page<SysMenuFunction>) sysMenuFunctionList;
        pageInfoDto.setList(pageList.getResult());
        pageInfoDto.setPageSize(pageList.getPageSize());
        pageInfoDto.setPageNum(pageList.getPageNum());
        pageInfoDto.setPages(pageList.getPages());
        pageInfoDto.setTotal(Integer.valueOf(pageList.getTotal()+""));
        return pageInfoDto;
    }

    @Override
    public int batchUpdate(List<java.lang.Long> idList, SysMenuFunction sysMenuFunction) {
        return sysMenuFunctionMapper.batchUpdate( idList, sysMenuFunction);
    }
}
