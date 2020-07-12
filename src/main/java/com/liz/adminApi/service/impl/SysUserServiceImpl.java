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
import com.liz.adminApi.mapper.SysUserMapper;
import com.liz.adminApi.model.SysUser;
import com.liz.adminApi.model.query.SysUserQuery;
import com.liz.adminApi.model.PageInfoDto;

import java.util.ArrayList;
import java.util.List;

import com.liz.adminApi.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysUserServiceImpl extends GenericService<SysUser, Integer> implements SysUserService {

    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    private SysUserMapper sysUserMapper;

    @Autowired
    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    /**
     * 通过主键查询实体对象
     * @param primaryKey
     * @return
     */
    public SysUser getByPK(java.lang.Integer primaryKey) {
        return sysUserMapper.getByPK(primaryKey);
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<SysUser> list() {
        return sysUserMapper.list();
    }

    /**
     * 根据查询条件查询所有记录
     * @return
     */
    public List<SysUser> listByProperty(SysUser sysUser){
        return sysUserMapper.listByProperty(sysUser);
    }


    /**
     * 根据主键删除记录
     * @param primaryKey
     * @return
     */
    public int deleteByPK(java.lang.Integer primaryKey) {
        return sysUserMapper.deleteByPK(primaryKey);
    }

    /**
     * 根据多个主键删除记录
     * @param primaryKeys
     */
    public void deleteByPKeys(List<java.lang.Integer> primaryKeys) {
        sysUserMapper.deleteByPKeys(primaryKeys);
    }

    /**
     * 根据传入参数删除记录
     * @param sysUser
     * @return
     */
    public int deleteByProperty(SysUser sysUser){
        return sysUserMapper.deleteByProperty(sysUser);
    }

    /**
     * 保存记录
     * @param sysUser
     * @return
     */
    public int save(SysUser sysUser){
        return sysUserMapper.save(sysUser);
    }

    /**
     * 更新记录
     * @param sysUser
     * @return
     */
    public int update(SysUser sysUser){
        return sysUserMapper.update(sysUser);
    }

    /**
     * 根据条件查询记录条数
     * @param sysUser
     * @return
     */
    public int findByCount(SysUser sysUser){
        return sysUserMapper.findByCount(sysUser);
    }

    public int batchInsert(List<SysUser> sysUserList){
        return sysUserMapper.batchInsert(sysUserList);
    }

    /**
     * 根据查询条件查询分页记录
     * @return
     */
    @Override
    public PageInfoDto<SysUser> findByPage(SysUserQuery sysUserQuery) {
        PageHelper.startPage(sysUserQuery.getPageNum(), sysUserQuery.getPageSize());
        List<SysUser> sysUserList = sysUserMapper.listByProperty(sysUserQuery);
        PageInfoDto pageInfoDto = new PageInfoDto<SysUser>(sysUserQuery.getPageNum(),sysUserQuery.getPageSize(),0,0);
        if(null == sysUserList || sysUserList.size() == 0){
            pageInfoDto.setList(new ArrayList<SysUser>());
            return pageInfoDto;
        }
        Page pageList = (Page<SysUser>) sysUserList;
        pageInfoDto.setList(pageList.getResult());
        pageInfoDto.setPageSize(pageList.getPageSize());
        pageInfoDto.setPageNum(pageList.getPageNum());
        pageInfoDto.setPages(pageList.getPages());
        pageInfoDto.setTotal(Integer.valueOf(pageList.getTotal()+""));
        return pageInfoDto;
    }

    @Override
    public int batchUpdate(List<java.lang.Long> idList, SysUser sysUser) {
        return sysUserMapper.batchUpdate( idList, sysUser);
    }

    @Override
    public SysUser getByUserName(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        List<SysUser>  sysUsers = sysUserMapper.listByProperty(sysUser);
        if(CollectionUtils.isEmpty(sysUsers ) || sysUsers.size() > 1 ){
            return null;
        }
        return sysUsers.get(0);
    }
}
