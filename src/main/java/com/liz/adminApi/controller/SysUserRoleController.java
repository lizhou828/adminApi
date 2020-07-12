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
import com.liz.adminApi.model.SysUserRole;
import com.liz.adminApi.model.dto.SysUserRoleBatchDto;
import com.liz.adminApi.model.query.SysUserRoleQuery;
import com.liz.adminApi.service.SysUserRoleService;
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
 * @date 创建于2020-07-12 03:56:212,028  
 * 用户角色关联表管理
 */
@RestController
@RequestMapping(value = "/api/sysUserRole",name = "用户角色关联表管理")
@Api(value="用户角色关联表",tags = "用户角色关联表接口")
public class SysUserRoleController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(SysUserRoleController.class);

    @Autowired
    private SysUserRoleService sysUserRoleService;


    @ApiOperation(value = "按条件的分页查询")
    @RequestMapping(value="/findByPage",method = RequestMethod.POST,name = "按条件的分页查询")
    public ResponseObject<PageInfoDto<SysUserRole>> findByPage(@RequestBody SysUserRoleQuery sysUserRoleQuery) {
        PageInfoDto<SysUserRole> pageInfoDto = null;
        if(null == sysUserRoleQuery){
            pageInfoDto = new PageInfoDto<SysUserRole>(1,10,0,0);
            pageInfoDto.setList(new ArrayList<>());
            return new ResponseObject<>(pageInfoDto);
        }
        if(sysUserRoleQuery.getPageNum() == null || sysUserRoleQuery.getPageNum() <= 0 ){
            sysUserRoleQuery.setPageNum(1);
        }
        if(sysUserRoleQuery.getPageSize() == null || sysUserRoleQuery.getPageSize() <= 0 ){
            sysUserRoleQuery.setPageSize(20);
        }
        pageInfoDto  = sysUserRoleService.findByPage(sysUserRoleQuery);

        if(null == pageInfoDto || CollectionUtils.isEmpty(pageInfoDto.getList())){
            pageInfoDto = new PageInfoDto<SysUserRole>(sysUserRoleQuery.getPageNum(),sysUserRoleQuery.getPageSize(),0,0);
            pageInfoDto.setList(new ArrayList<>());
        }
        return new ResponseObject<>(pageInfoDto);
    }

    @ApiOperation(value = "根据主键查询")
    @GetMapping("/getByPK/{id}")
    public ResponseObject<SysUserRole> getByPK(@PathVariable("id") Integer id){
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysUserRole sysUserRole = sysUserRoleService.getByPK(id);
        return new ResponseObject<>(sysUserRole);
    }

    @ApiOperation(value = "按条件的查询所有")
    @RequestMapping(value="/listByProperty",method = RequestMethod.POST,name = "按条件的查询所有")
    public ResponseObject<List<SysUserRole>> listByProperty(@RequestBody SysUserRole sysUserRole) {
        List<SysUserRole> sysUserRoleList =  sysUserRoleService.listByProperty(sysUserRole);
        return new ResponseObject(sysUserRoleList);
    }

    @ApiOperation(value = "新增")
    @RequestMapping(value="add",method = RequestMethod.POST,name = "新增操作")
    public ResponseObject add(@RequestBody SysUserRole sysUserRole) {
        if(null == sysUserRole ){
            return ResponseObject.error("非法参数");
        }
        sysUserRole.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysUserRole.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        int resultCount = sysUserRoleService.save(sysUserRole);
        long id = resultCount > 0 ? sysUserRole.getId() : 0L;
        Map map = new HashMap<>();
        map.put("id",id);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value="edit",method = RequestMethod.POST,name = "修改操作")
    public ResponseObject edit(@RequestBody SysUserRole sysUserRole) {
        if(null == sysUserRole || StringUtils.isEmpty(sysUserRole.getId()) ){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysUserRoleService.update(sysUserRole);
        return resultCount > 0 ? ResponseObject.ok("修改成功") : ResponseObject.error("修改失败");
    }

    @ApiOperation(value = "详情")
    @RequestMapping(value="detail/{id}",method = RequestMethod.GET,name = "查看详情")
    public ResponseObject<SysUserRole> detail(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysUserRole sysUserRole = sysUserRoleService.getByPK(id);
        return new ResponseObject(sysUserRole);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value="delete/{id}",method = RequestMethod.GET,name = "删除操作")
    public ResponseObject delete(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysUserRoleService.deleteByPK(id);
        return resultCount > 0 ? ResponseObject.ok("删除成功") : ResponseObject.error("删除失败");
    }

    @ApiOperation(value = "按条件统计")
    @RequestMapping(value="findByCount",method = RequestMethod.POST,name = "按条件统计")
    public ResponseObject findByCount(@RequestBody SysUserRole sysUserRole) {
        if(null == sysUserRole || ObjectUtils.allFieldWithoutValue(sysUserRole)){
            log.error("非法参数,sysUserRole=" + sysUserRole);
            return ResponseObject.error("非法参数");
        }
        int count = sysUserRoleService.findByCount(sysUserRole);
        Map map = new HashMap<>();
        map.put("count",count);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "批量更新字段")
    @RequestMapping(value="batchUpdate",method = RequestMethod.POST,name = "批量更新字段")
    public ResponseObject batchUpdate(@RequestBody SysUserRoleBatchDto sysUserRoleBatchDto){
        if(null == sysUserRoleBatchDto || CollectionUtils.isEmpty(sysUserRoleBatchDto.getIdList())
                || null == sysUserRoleBatchDto.getSysUserRole()  || ObjectUtils.allFieldWithoutValue(sysUserRoleBatchDto.getSysUserRole())){
            log.error("非法参数！sysUserRoleBatchDto=" + sysUserRoleBatchDto);
            return ResponseObject.error("非法参数");
        }
        SysUserRole sysUserRole = sysUserRoleBatchDto.getSysUserRole();
        sysUserRole.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int resultCount =sysUserRoleService.batchUpdate(sysUserRoleBatchDto.getIdList(),sysUserRole);
        return resultCount > 0 ? ResponseObject.ok("批量更新成功") : ResponseObject.error("批量更新失败");
    }

    @ApiOperation(value = "批量新增")
    @RequestMapping(value="batchInsert",method = RequestMethod.POST,name = "批量新增")
    public ResponseObject batchInsert(@RequestBody SysUserRoleBatchDto sysUserRoleBatchDto) {
        if(null == sysUserRoleBatchDto || CollectionUtils.isEmpty(sysUserRoleBatchDto.getSysUserRoleList())){
            log.error("非法参数,sysUserRoleBatchDto=" + sysUserRoleBatchDto);
            return ResponseObject.error("非法参数");
        }
        for(SysUserRole sysUserRole : sysUserRoleBatchDto.getSysUserRoleList()){
            sysUserRole.setCreateTime(new Timestamp(System.currentTimeMillis()));
            sysUserRole.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        }
        int resultCount = sysUserRoleService.batchInsert(sysUserRoleBatchDto.getSysUserRoleList());
        return resultCount > 0 ? ResponseObject.ok("批量新增成功") : ResponseObject.error("批量新增失败");
    }

}
