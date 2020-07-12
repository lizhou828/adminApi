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
import com.liz.adminApi.model.SysMenuFunction;
import com.liz.adminApi.model.dto.SysMenuFunctionBatchDto;
import com.liz.adminApi.model.query.SysMenuFunctionQuery;
import com.liz.adminApi.service.SysMenuFunctionService;
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
 * @date 创建于2020-07-12 03:56:287,026  
 * 菜单功能表管理
 */
@RestController
@RequestMapping(value = "/api/sysMenuFunction",name = "菜单功能表管理")
@Api(value="菜单功能表",tags = "菜单功能表接口")
public class SysMenuFunctionController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(SysMenuFunctionController.class);

    @Autowired
    private SysMenuFunctionService sysMenuFunctionService;


    @ApiOperation(value = "按条件的分页查询")
    @RequestMapping(value="/findByPage",method = RequestMethod.POST,name = "按条件的分页查询")
    public ResponseObject<PageInfoDto<SysMenuFunction>> findByPage(@RequestBody SysMenuFunctionQuery sysMenuFunctionQuery) {
        PageInfoDto<SysMenuFunction> pageInfoDto = null;
        if(null == sysMenuFunctionQuery){
            pageInfoDto = new PageInfoDto<SysMenuFunction>(1,10,0,0);
            pageInfoDto.setList(new ArrayList<>());
            return new ResponseObject<>(pageInfoDto);
        }
        if(sysMenuFunctionQuery.getPageNum() == null || sysMenuFunctionQuery.getPageNum() <= 0 ){
            sysMenuFunctionQuery.setPageNum(1);
        }
        if(sysMenuFunctionQuery.getPageSize() == null || sysMenuFunctionQuery.getPageSize() <= 0 ){
            sysMenuFunctionQuery.setPageSize(20);
        }
        pageInfoDto  = sysMenuFunctionService.findByPage(sysMenuFunctionQuery);

        if(null == pageInfoDto || CollectionUtils.isEmpty(pageInfoDto.getList())){
            pageInfoDto = new PageInfoDto<SysMenuFunction>(sysMenuFunctionQuery.getPageNum(),sysMenuFunctionQuery.getPageSize(),0,0);
            pageInfoDto.setList(new ArrayList<>());
        }
        return new ResponseObject<>(pageInfoDto);
    }

    @ApiOperation(value = "根据主键查询")
    @GetMapping("/getByPK/{id}")
    public ResponseObject<SysMenuFunction> getByPK(@PathVariable("id") Integer id){
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysMenuFunction sysMenuFunction = sysMenuFunctionService.getByPK(id);
        return new ResponseObject<>(sysMenuFunction);
    }

    @ApiOperation(value = "按条件的查询所有")
    @RequestMapping(value="/listByProperty",method = RequestMethod.POST,name = "按条件的查询所有")
    public ResponseObject<List<SysMenuFunction>> listByProperty(@RequestBody SysMenuFunction sysMenuFunction) {
        List<SysMenuFunction> sysMenuFunctionList =  sysMenuFunctionService.listByProperty(sysMenuFunction);
        return new ResponseObject(sysMenuFunctionList);
    }

    @ApiOperation(value = "新增")
    @RequestMapping(value="add",method = RequestMethod.POST,name = "新增操作")
    public ResponseObject add(@RequestBody SysMenuFunction sysMenuFunction) {
        if(null == sysMenuFunction ){
            return ResponseObject.error("非法参数");
        }
        sysMenuFunction.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysMenuFunction.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        int resultCount = sysMenuFunctionService.save(sysMenuFunction);
        long id = resultCount > 0 ? sysMenuFunction.getId() : 0L;
        Map map = new HashMap<>();
        map.put("id",id);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value="edit",method = RequestMethod.POST,name = "修改操作")
    public ResponseObject edit(@RequestBody SysMenuFunction sysMenuFunction) {
        if(null == sysMenuFunction || StringUtils.isEmpty(sysMenuFunction.getId()) ){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysMenuFunctionService.update(sysMenuFunction);
        return resultCount > 0 ? ResponseObject.ok("修改成功") : ResponseObject.error("修改失败");
    }

    @ApiOperation(value = "详情")
    @RequestMapping(value="detail/{id}",method = RequestMethod.GET,name = "查看详情")
    public ResponseObject<SysMenuFunction> detail(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysMenuFunction sysMenuFunction = sysMenuFunctionService.getByPK(id);
        return new ResponseObject(sysMenuFunction);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value="delete/{id}",method = RequestMethod.GET,name = "删除操作")
    public ResponseObject delete(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysMenuFunctionService.deleteByPK(id);
        return resultCount > 0 ? ResponseObject.ok("删除成功") : ResponseObject.error("删除失败");
    }

    @ApiOperation(value = "按条件统计")
    @RequestMapping(value="findByCount",method = RequestMethod.POST,name = "按条件统计")
    public ResponseObject findByCount(@RequestBody SysMenuFunction sysMenuFunction) {
        if(null == sysMenuFunction || ObjectUtils.allFieldWithoutValue(sysMenuFunction)){
            log.error("非法参数,sysMenuFunction=" + sysMenuFunction);
            return ResponseObject.error("非法参数");
        }
        int count = sysMenuFunctionService.findByCount(sysMenuFunction);
        Map map = new HashMap<>();
        map.put("count",count);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "批量更新字段")
    @RequestMapping(value="batchUpdate",method = RequestMethod.POST,name = "批量更新字段")
    public ResponseObject batchUpdate(@RequestBody SysMenuFunctionBatchDto sysMenuFunctionBatchDto){
        if(null == sysMenuFunctionBatchDto || CollectionUtils.isEmpty(sysMenuFunctionBatchDto.getIdList())
                || null == sysMenuFunctionBatchDto.getSysMenuFunction()  || ObjectUtils.allFieldWithoutValue(sysMenuFunctionBatchDto.getSysMenuFunction())){
            log.error("非法参数！sysMenuFunctionBatchDto=" + sysMenuFunctionBatchDto);
            return ResponseObject.error("非法参数");
        }
        SysMenuFunction sysMenuFunction = sysMenuFunctionBatchDto.getSysMenuFunction();
        sysMenuFunction.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int resultCount =sysMenuFunctionService.batchUpdate(sysMenuFunctionBatchDto.getIdList(),sysMenuFunction);
        return resultCount > 0 ? ResponseObject.ok("批量更新成功") : ResponseObject.error("批量更新失败");
    }

    @ApiOperation(value = "批量新增")
    @RequestMapping(value="batchInsert",method = RequestMethod.POST,name = "批量新增")
    public ResponseObject batchInsert(@RequestBody SysMenuFunctionBatchDto sysMenuFunctionBatchDto) {
        if(null == sysMenuFunctionBatchDto || CollectionUtils.isEmpty(sysMenuFunctionBatchDto.getSysMenuFunctionList())){
            log.error("非法参数,sysMenuFunctionBatchDto=" + sysMenuFunctionBatchDto);
            return ResponseObject.error("非法参数");
        }
        for(SysMenuFunction sysMenuFunction : sysMenuFunctionBatchDto.getSysMenuFunctionList()){
            sysMenuFunction.setCreateTime(new Timestamp(System.currentTimeMillis()));
            sysMenuFunction.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        }
        int resultCount = sysMenuFunctionService.batchInsert(sysMenuFunctionBatchDto.getSysMenuFunctionList());
        return resultCount > 0 ? ResponseObject.ok("批量新增成功") : ResponseObject.error("批量新增失败");
    }

}
