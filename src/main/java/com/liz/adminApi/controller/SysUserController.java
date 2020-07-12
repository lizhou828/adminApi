/*
 * Powered By [Frank-Liz-Lee]
 * Copyright(C) 2018-2020 diyuns Company
 * All rights reserved.
 * -----------------------------------------------
 */
package com.liz.adminApi.controller;

import com.liz.adminApi.model.PageInfoDto;
import com.liz.adminApi.enums.DropStateEnum;
import com.liz.adminApi.model.ResponseObject;
import com.liz.adminApi.model.SysUser;
import com.liz.adminApi.model.dto.SysUserBatchDto;
import com.liz.adminApi.model.query.SysUserQuery;
import com.liz.adminApi.service.SysUserService;
import com.liz.adminApi.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.*;
import java.sql.Timestamp;


/**
 * @author Code Generator
 * @date 创建于2020-07-12 03:56:939,027  
 * 系统用户管理
 */
@RestController
@RequestMapping(value = "/api/sysUser",name = "系统用户管理")
@Api(value="系统用户",tags = "系统用户接口")
public class SysUserController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;


    @ApiOperation(value = "按条件的分页查询")
    @RequestMapping(value="/findByPage",method = RequestMethod.POST,name = "按条件的分页查询")
    public ResponseObject<PageInfoDto<SysUser>> findByPage(@RequestBody SysUserQuery sysUserQuery) {
        PageInfoDto<SysUser> pageInfoDto = null;
        if(null == sysUserQuery){
            pageInfoDto = new PageInfoDto<SysUser>(1,10,0,0);
            pageInfoDto.setList(new ArrayList<>());
            return new ResponseObject<>(pageInfoDto);
        }
        if(sysUserQuery.getPageNum() == null || sysUserQuery.getPageNum() <= 0 ){
            sysUserQuery.setPageNum(1);
        }
        if(sysUserQuery.getPageSize() == null || sysUserQuery.getPageSize() <= 0 ){
            sysUserQuery.setPageSize(20);
        }
        pageInfoDto  = sysUserService.findByPage(sysUserQuery);

        if(null == pageInfoDto || CollectionUtils.isEmpty(pageInfoDto.getList())){
            pageInfoDto = new PageInfoDto<SysUser>(sysUserQuery.getPageNum(),sysUserQuery.getPageSize(),0,0);
            pageInfoDto.setList(new ArrayList<>());
        }
        return new ResponseObject<>(pageInfoDto);
    }

    @ApiOperation(value = "根据主键查询")
    @GetMapping("/getByPK/{id}")
    public ResponseObject<SysUser> getByPK(@PathVariable("id") Integer id){
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysUser sysUser = sysUserService.getByPK(id);
        return new ResponseObject<>(sysUser);
    }

    @ApiOperation(value = "按条件的查询所有")
    @RequestMapping(value="/listByProperty",method = RequestMethod.POST,name = "按条件的查询所有")
    public ResponseObject<List<SysUser>> listByProperty(@RequestBody SysUser sysUser) {
        List<SysUser> sysUserList =  sysUserService.listByProperty(sysUser);
        return new ResponseObject(sysUserList);
    }

    @ApiOperation(value = "新增")
    @RequestMapping(value="add",method = RequestMethod.POST,name = "新增操作")
    public ResponseObject add(@RequestBody SysUser sysUser) {
        if(null == sysUser ){
            return ResponseObject.error("非法参数");
        }
        sysUser.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysUser.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        int resultCount = sysUserService.save(sysUser);
        long id = resultCount > 0 ? sysUser.getId() : 0L;
        Map map = new HashMap<>();
        map.put("id",id);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value="edit",method = RequestMethod.POST,name = "修改操作")
    public ResponseObject edit(@RequestBody SysUser sysUser) {
        if(null == sysUser || StringUtils.isEmpty(sysUser.getId()) ){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysUserService.update(sysUser);
        return resultCount > 0 ? ResponseObject.ok("修改成功") : ResponseObject.error("修改失败");
    }

    @ApiOperation(value = "详情")
    @RequestMapping(value="detail/{id}",method = RequestMethod.GET,name = "查看详情")
    public ResponseObject<SysUser> detail(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysUser sysUser = sysUserService.getByPK(id);
        return new ResponseObject(sysUser);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value="delete/{id}",method = RequestMethod.GET,name = "删除操作")
    public ResponseObject delete(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysUserService.deleteByPK(id);
        return resultCount > 0 ? ResponseObject.ok("删除成功") : ResponseObject.error("删除失败");
    }

    @ApiOperation(value = "按条件统计")
    @RequestMapping(value="findByCount",method = RequestMethod.POST,name = "按条件统计")
    public ResponseObject findByCount(@RequestBody SysUser sysUser) {
        if(null == sysUser || ObjectUtils.allFieldWithoutValue(sysUser)){
            log.error("非法参数,sysUser=" + sysUser);
            return ResponseObject.error("非法参数");
        }
        int count = sysUserService.findByCount(sysUser);
        Map map = new HashMap<>();
        map.put("count",count);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "批量更新字段")
    @RequestMapping(value="batchUpdate",method = RequestMethod.POST,name = "批量更新字段")
    public ResponseObject batchUpdate(@RequestBody SysUserBatchDto sysUserBatchDto){
        if(null == sysUserBatchDto || CollectionUtils.isEmpty(sysUserBatchDto.getIdList())
                || null == sysUserBatchDto.getSysUser()  || ObjectUtils.allFieldWithoutValue(sysUserBatchDto.getSysUser())){
            log.error("非法参数！sysUserBatchDto=" + sysUserBatchDto);
            return ResponseObject.error("非法参数");
        }
        SysUser sysUser = sysUserBatchDto.getSysUser();
        sysUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int resultCount =sysUserService.batchUpdate(sysUserBatchDto.getIdList(),sysUser);
        return resultCount > 0 ? ResponseObject.ok("批量更新成功") : ResponseObject.error("批量更新失败");
    }

    @ApiOperation(value = "批量新增")
    @RequestMapping(value="batchInsert",method = RequestMethod.POST,name = "批量新增")
    public ResponseObject batchInsert(@RequestBody SysUserBatchDto sysUserBatchDto) {
        if(null == sysUserBatchDto || CollectionUtils.isEmpty(sysUserBatchDto.getSysUserList())){
            log.error("非法参数,sysUserBatchDto=" + sysUserBatchDto);
            return ResponseObject.error("非法参数");
        }
        for(SysUser sysUser : sysUserBatchDto.getSysUserList()){
            sysUser.setCreateTime(new Timestamp(System.currentTimeMillis()));
            sysUser.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        }
        int resultCount = sysUserService.batchInsert(sysUserBatchDto.getSysUserList());
        return resultCount > 0 ? ResponseObject.ok("批量新增成功") : ResponseObject.error("批量新增失败");
    }

}
