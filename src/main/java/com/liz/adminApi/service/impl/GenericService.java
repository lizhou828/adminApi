/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */

package com.liz.adminApi.service.impl;


import com.liz.adminApi.mapper.GenericIBatisMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 业务底层 公共接口
 * @author lizhou
 * @version 1.0
 * @Date 2017年05月27日 15时21分
 */
public class GenericService<T,PK extends Serializable>{


    @Autowired
    private GenericIBatisMapper<T, PK> genericIBatisMapper;

    /**
     * 通过主键查询实体对象
     *
     * @param primaryKey
     * @return
     * @throws Exception
     */

    public T getByPK(PK primaryKey) {
        return genericIBatisMapper.getByPK(primaryKey);
    }

    /**
     * 查询所有记录
     * @return
     * @throws Exception
     */
    public List<T> list(){
        return genericIBatisMapper.list();
    }

    /**
     * 根据查询条件查询所有记录
     * @return
     * @throws Exception
     */
    public List<T> listByProperty(T t){
        return genericIBatisMapper.listByProperty(t);
    }


    /**
     * 根据主键删除记录
     * @param primaryKey
     * @return
     * @throws Exception
     */
    public int deleteByPK(PK primaryKey) {
        return genericIBatisMapper.deleteByPK(primaryKey);
    }

    /**
     * 根据多个主键删除记录
     * @param primaryKeys
     * @throws Exception
     */
    public void deleteByPKeys(List<PK> primaryKeys){
        genericIBatisMapper.deleteByPKeys(primaryKeys);
    }

    /**
     * 根据传入参数删除记录
     * @param t
     * @return
     * @throws Exception
     */
    public int deleteByProperty(T t) {
        return genericIBatisMapper.deleteByProperty(t);
    };

    /**
     * 保存记录
     * @param t
     * @return
     * @throws Exception
     */
    public int save(T t) {
        return genericIBatisMapper.save(t);
    }

    /**
     * 更新记录
     * @param t
     * @return
     * @throws Exception
     */
    public int update(T t) {
        return genericIBatisMapper.update(t);
    };

    /**
     * 根据条件查询记录条数
     * @param t
     * @return
     * @throws Exception
     */
    public int findByCount(T t){
        return genericIBatisMapper.findByCount(t);
    }

    public int batchInsert(List<T> list){
        return genericIBatisMapper.batchInsert(list);
    }



}
