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
import com.liz.adminApi.model.SysRole;
import com.liz.adminApi.model.dto.SysRoleBatchDto;
import com.liz.adminApi.model.query.SysRoleQuery;
import com.liz.adminApi.service.SysRoleService;
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
 * @date 创建于2020-07-12 03:56:849,026  
 * 角色表管理
 */
@RestController
@RequestMapping(value = "/api/sysRole",name = "角色表管理")
@Api(value="角色表",tags = "角色表接口")
public class SysRoleController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService sysRoleService;


    @ApiOperation(value = "按条件的分页查询")
    @RequestMapping(value="/findByPage",method = RequestMethod.POST,name = "按条件的分页查询")
    public ResponseObject<PageInfoDto<SysRole>> findByPage(@RequestBody SysRoleQuery sysRoleQuery) {
        PageInfoDto<SysRole> pageInfoDto = null;
        if(null == sysRoleQuery){
            pageInfoDto = new PageInfoDto<SysRole>(1,10,0,0);
            pageInfoDto.setList(new ArrayList<>());
            return new ResponseObject<>(pageInfoDto);
        }
        if(sysRoleQuery.getPageNum() == null || sysRoleQuery.getPageNum() <= 0 ){
            sysRoleQuery.setPageNum(1);
        }
        if(sysRoleQuery.getPageSize() == null || sysRoleQuery.getPageSize() <= 0 ){
            sysRoleQuery.setPageSize(20);
        }
        pageInfoDto  = sysRoleService.findByPage(sysRoleQuery);

        if(null == pageInfoDto || CollectionUtils.isEmpty(pageInfoDto.getList())){
            pageInfoDto = new PageInfoDto<SysRole>(sysRoleQuery.getPageNum(),sysRoleQuery.getPageSize(),0,0);
            pageInfoDto.setList(new ArrayList<>());
        }
        return new ResponseObject<>(pageInfoDto);
    }

    @ApiOperation(value = "根据主键查询")
    @GetMapping("/getByPK/{id}")
    public ResponseObject<SysRole> getByPK(@PathVariable("id") Integer id){
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysRole sysRole = sysRoleService.getByPK(id);
        return new ResponseObject<>(sysRole);
    }

    @ApiOperation(value = "按条件的查询所有")
    @RequestMapping(value="/listByProperty",method = RequestMethod.POST,name = "按条件的查询所有")
    public ResponseObject<List<SysRole>> listByProperty(@RequestBody SysRole sysRole) {
        List<SysRole> sysRoleList =  sysRoleService.listByProperty(sysRole);
        return new ResponseObject(sysRoleList);
    }

    @ApiOperation(value = "新增")
    @RequestMapping(value="add",method = RequestMethod.POST,name = "新增操作")
    public ResponseObject add(@RequestBody SysRole sysRole) {
        if(null == sysRole ){
            return ResponseObject.error("非法参数");
        }
        sysRole.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysRole.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        int resultCount = sysRoleService.save(sysRole);
        long id = resultCount > 0 ? sysRole.getId() : 0L;
        Map map = new HashMap<>();
        map.put("id",id);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value="edit",method = RequestMethod.POST,name = "修改操作")
    public ResponseObject edit(@RequestBody SysRole sysRole) {
        if(null == sysRole || StringUtils.isEmpty(sysRole.getId()) ){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysRoleService.update(sysRole);
        return resultCount > 0 ? ResponseObject.ok("修改成功") : ResponseObject.error("修改失败");
    }

    @ApiOperation(value = "详情")
    @RequestMapping(value="detail/{id}",method = RequestMethod.GET,name = "查看详情")
    public ResponseObject<SysRole> detail(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysRole sysRole = sysRoleService.getByPK(id);
        return new ResponseObject(sysRole);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value="delete/{id}",method = RequestMethod.GET,name = "删除操作")
    public ResponseObject delete(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysRoleService.deleteByPK(id);
        return resultCount > 0 ? ResponseObject.ok("删除成功") : ResponseObject.error("删除失败");
    }

    @ApiOperation(value = "按条件统计")
    @RequestMapping(value="findByCount",method = RequestMethod.POST,name = "按条件统计")
    public ResponseObject findByCount(@RequestBody SysRole sysRole) {
        if(null == sysRole || ObjectUtils.allFieldWithoutValue(sysRole)){
            log.error("非法参数,sysRole=" + sysRole);
            return ResponseObject.error("非法参数");
        }
        int count = sysRoleService.findByCount(sysRole);
        Map map = new HashMap<>();
        map.put("count",count);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "批量更新字段")
    @RequestMapping(value="batchUpdate",method = RequestMethod.POST,name = "批量更新字段")
    public ResponseObject batchUpdate(@RequestBody SysRoleBatchDto sysRoleBatchDto){
        if(null == sysRoleBatchDto || CollectionUtils.isEmpty(sysRoleBatchDto.getIdList())
                || null == sysRoleBatchDto.getSysRole()  || ObjectUtils.allFieldWithoutValue(sysRoleBatchDto.getSysRole())){
            log.error("非法参数！sysRoleBatchDto=" + sysRoleBatchDto);
            return ResponseObject.error("非法参数");
        }
        SysRole sysRole = sysRoleBatchDto.getSysRole();
        sysRole.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int resultCount =sysRoleService.batchUpdate(sysRoleBatchDto.getIdList(),sysRole);
        return resultCount > 0 ? ResponseObject.ok("批量更新成功") : ResponseObject.error("批量更新失败");
    }

    @ApiOperation(value = "批量新增")
    @RequestMapping(value="batchInsert",method = RequestMethod.POST,name = "批量新增")
    public ResponseObject batchInsert(@RequestBody SysRoleBatchDto sysRoleBatchDto) {
        if(null == sysRoleBatchDto || CollectionUtils.isEmpty(sysRoleBatchDto.getSysRoleList())){
            log.error("非法参数,sysRoleBatchDto=" + sysRoleBatchDto);
            return ResponseObject.error("非法参数");
        }
        for(SysRole sysRole : sysRoleBatchDto.getSysRoleList()){
            sysRole.setCreateTime(new Timestamp(System.currentTimeMillis()));
            sysRole.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        }
        int resultCount = sysRoleService.batchInsert(sysRoleBatchDto.getSysRoleList());
        return resultCount > 0 ? ResponseObject.ok("批量新增成功") : ResponseObject.error("批量新增失败");
    }

}
