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
import com.liz.adminApi.model.SysRoleMenuFunction;
import com.liz.adminApi.model.dto.SysRoleMenuFunctionBatchDto;
import com.liz.adminApi.model.query.SysRoleMenuFunctionQuery;
import com.liz.adminApi.service.SysRoleMenuFunctionService;
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
 * @date 创建于2020-07-12 03:56:541,027  
 * 角色与菜单功能表管理
 */
@RestController
@RequestMapping(value = "/api/sysRoleMenuFunction",name = "角色与菜单功能表管理")
@Api(value="角色与菜单功能表",tags = "角色与菜单功能表接口")
public class SysRoleMenuFunctionController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(SysRoleMenuFunctionController.class);

    @Autowired
    private SysRoleMenuFunctionService sysRoleMenuFunctionService;


    @ApiOperation(value = "按条件的分页查询")
    @RequestMapping(value="/findByPage",method = RequestMethod.POST,name = "按条件的分页查询")
    public ResponseObject<PageInfoDto<SysRoleMenuFunction>> findByPage(@RequestBody SysRoleMenuFunctionQuery sysRoleMenuFunctionQuery) {
        PageInfoDto<SysRoleMenuFunction> pageInfoDto = null;
        if(null == sysRoleMenuFunctionQuery){
            pageInfoDto = new PageInfoDto<SysRoleMenuFunction>(1,10,0,0);
            pageInfoDto.setList(new ArrayList<>());
            return new ResponseObject<>(pageInfoDto);
        }
        if(sysRoleMenuFunctionQuery.getPageNum() == null || sysRoleMenuFunctionQuery.getPageNum() <= 0 ){
            sysRoleMenuFunctionQuery.setPageNum(1);
        }
        if(sysRoleMenuFunctionQuery.getPageSize() == null || sysRoleMenuFunctionQuery.getPageSize() <= 0 ){
            sysRoleMenuFunctionQuery.setPageSize(20);
        }
        pageInfoDto  = sysRoleMenuFunctionService.findByPage(sysRoleMenuFunctionQuery);

        if(null == pageInfoDto || CollectionUtils.isEmpty(pageInfoDto.getList())){
            pageInfoDto = new PageInfoDto<SysRoleMenuFunction>(sysRoleMenuFunctionQuery.getPageNum(),sysRoleMenuFunctionQuery.getPageSize(),0,0);
            pageInfoDto.setList(new ArrayList<>());
        }
        return new ResponseObject<>(pageInfoDto);
    }

    @ApiOperation(value = "根据主键查询")
    @GetMapping("/getByPK/{id}")
    public ResponseObject<SysRoleMenuFunction> getByPK(@PathVariable("id") Integer id){
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysRoleMenuFunction sysRoleMenuFunction = sysRoleMenuFunctionService.getByPK(id);
        return new ResponseObject<>(sysRoleMenuFunction);
    }

    @ApiOperation(value = "按条件的查询所有")
    @RequestMapping(value="/listByProperty",method = RequestMethod.POST,name = "按条件的查询所有")
    public ResponseObject<List<SysRoleMenuFunction>> listByProperty(@RequestBody SysRoleMenuFunction sysRoleMenuFunction) {
        List<SysRoleMenuFunction> sysRoleMenuFunctionList =  sysRoleMenuFunctionService.listByProperty(sysRoleMenuFunction);
        return new ResponseObject(sysRoleMenuFunctionList);
    }

    @ApiOperation(value = "新增")
    @RequestMapping(value="add",method = RequestMethod.POST,name = "新增操作")
    public ResponseObject add(@RequestBody SysRoleMenuFunction sysRoleMenuFunction) {
        if(null == sysRoleMenuFunction ){
            return ResponseObject.error("非法参数");
        }
        sysRoleMenuFunction.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysRoleMenuFunction.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        int resultCount = sysRoleMenuFunctionService.save(sysRoleMenuFunction);
        long id = resultCount > 0 ? sysRoleMenuFunction.getId() : 0L;
        Map map = new HashMap<>();
        map.put("id",id);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value="edit",method = RequestMethod.POST,name = "修改操作")
    public ResponseObject edit(@RequestBody SysRoleMenuFunction sysRoleMenuFunction) {
        if(null == sysRoleMenuFunction || StringUtils.isEmpty(sysRoleMenuFunction.getId()) ){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysRoleMenuFunctionService.update(sysRoleMenuFunction);
        return resultCount > 0 ? ResponseObject.ok("修改成功") : ResponseObject.error("修改失败");
    }

    @ApiOperation(value = "详情")
    @RequestMapping(value="detail/{id}",method = RequestMethod.GET,name = "查看详情")
    public ResponseObject<SysRoleMenuFunction> detail(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysRoleMenuFunction sysRoleMenuFunction = sysRoleMenuFunctionService.getByPK(id);
        return new ResponseObject(sysRoleMenuFunction);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value="delete/{id}",method = RequestMethod.GET,name = "删除操作")
    public ResponseObject delete(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysRoleMenuFunctionService.deleteByPK(id);
        return resultCount > 0 ? ResponseObject.ok("删除成功") : ResponseObject.error("删除失败");
    }

    @ApiOperation(value = "按条件统计")
    @RequestMapping(value="findByCount",method = RequestMethod.POST,name = "按条件统计")
    public ResponseObject findByCount(@RequestBody SysRoleMenuFunction sysRoleMenuFunction) {
        if(null == sysRoleMenuFunction || ObjectUtils.allFieldWithoutValue(sysRoleMenuFunction)){
            log.error("非法参数,sysRoleMenuFunction=" + sysRoleMenuFunction);
            return ResponseObject.error("非法参数");
        }
        int count = sysRoleMenuFunctionService.findByCount(sysRoleMenuFunction);
        Map map = new HashMap<>();
        map.put("count",count);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "批量更新字段")
    @RequestMapping(value="batchUpdate",method = RequestMethod.POST,name = "批量更新字段")
    public ResponseObject batchUpdate(@RequestBody SysRoleMenuFunctionBatchDto sysRoleMenuFunctionBatchDto){
        if(null == sysRoleMenuFunctionBatchDto || CollectionUtils.isEmpty(sysRoleMenuFunctionBatchDto.getIdList())
                || null == sysRoleMenuFunctionBatchDto.getSysRoleMenuFunction()  || ObjectUtils.allFieldWithoutValue(sysRoleMenuFunctionBatchDto.getSysRoleMenuFunction())){
            log.error("非法参数！sysRoleMenuFunctionBatchDto=" + sysRoleMenuFunctionBatchDto);
            return ResponseObject.error("非法参数");
        }
        SysRoleMenuFunction sysRoleMenuFunction = sysRoleMenuFunctionBatchDto.getSysRoleMenuFunction();
        sysRoleMenuFunction.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int resultCount =sysRoleMenuFunctionService.batchUpdate(sysRoleMenuFunctionBatchDto.getIdList(),sysRoleMenuFunction);
        return resultCount > 0 ? ResponseObject.ok("批量更新成功") : ResponseObject.error("批量更新失败");
    }

    @ApiOperation(value = "批量新增")
    @RequestMapping(value="batchInsert",method = RequestMethod.POST,name = "批量新增")
    public ResponseObject batchInsert(@RequestBody SysRoleMenuFunctionBatchDto sysRoleMenuFunctionBatchDto) {
        if(null == sysRoleMenuFunctionBatchDto || CollectionUtils.isEmpty(sysRoleMenuFunctionBatchDto.getSysRoleMenuFunctionList())){
            log.error("非法参数,sysRoleMenuFunctionBatchDto=" + sysRoleMenuFunctionBatchDto);
            return ResponseObject.error("非法参数");
        }
        for(SysRoleMenuFunction sysRoleMenuFunction : sysRoleMenuFunctionBatchDto.getSysRoleMenuFunctionList()){
            sysRoleMenuFunction.setCreateTime(new Timestamp(System.currentTimeMillis()));
            sysRoleMenuFunction.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        }
        int resultCount = sysRoleMenuFunctionService.batchInsert(sysRoleMenuFunctionBatchDto.getSysRoleMenuFunctionList());
        return resultCount > 0 ? ResponseObject.ok("批量新增成功") : ResponseObject.error("批量新增失败");
    }

}
