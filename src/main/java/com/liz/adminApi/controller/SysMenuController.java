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
import com.liz.adminApi.model.SysMenu;
import com.liz.adminApi.model.dto.SysMenuBatchDto;
import com.liz.adminApi.model.query.SysMenuQuery;
import com.liz.adminApi.service.SysMenuService;
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
 * @date 创建于2020-07-12 03:56:666,025  
 * 菜单表管理
 */
@RestController
@RequestMapping(value = "/api/sysMenu",name = "菜单表管理")
@Api(value="菜单表",tags = "菜单表接口")
public class SysMenuController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(SysMenuController.class);

    @Autowired
    private SysMenuService sysMenuService;


    @ApiOperation(value = "按条件的分页查询")
    @RequestMapping(value="/findByPage",method = RequestMethod.POST,name = "按条件的分页查询")
    public ResponseObject<PageInfoDto<SysMenu>> findByPage(@RequestBody SysMenuQuery sysMenuQuery) {
        PageInfoDto<SysMenu> pageInfoDto = null;
        if(null == sysMenuQuery){
            pageInfoDto = new PageInfoDto<SysMenu>(1,10,0,0);
            pageInfoDto.setList(new ArrayList<>());
            return new ResponseObject<>(pageInfoDto);
        }
        if(sysMenuQuery.getPageNum() == null || sysMenuQuery.getPageNum() <= 0 ){
            sysMenuQuery.setPageNum(1);
        }
        if(sysMenuQuery.getPageSize() == null || sysMenuQuery.getPageSize() <= 0 ){
            sysMenuQuery.setPageSize(20);
        }
        pageInfoDto  = sysMenuService.findByPage(sysMenuQuery);

        if(null == pageInfoDto || CollectionUtils.isEmpty(pageInfoDto.getList())){
            pageInfoDto = new PageInfoDto<SysMenu>(sysMenuQuery.getPageNum(),sysMenuQuery.getPageSize(),0,0);
            pageInfoDto.setList(new ArrayList<>());
        }
        return new ResponseObject<>(pageInfoDto);
    }

    @ApiOperation(value = "根据主键查询")
    @GetMapping("/getByPK/{id}")
    public ResponseObject<SysMenu> getByPK(@PathVariable("id") Integer id){
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysMenu sysMenu = sysMenuService.getByPK(id);
        return new ResponseObject<>(sysMenu);
    }

    @ApiOperation(value = "按条件的查询所有")
    @RequestMapping(value="/listByProperty",method = RequestMethod.POST,name = "按条件的查询所有")
    public ResponseObject<List<SysMenu>> listByProperty(@RequestBody SysMenu sysMenu) {
        List<SysMenu> sysMenuList =  sysMenuService.listByProperty(sysMenu);
        return new ResponseObject(sysMenuList);
    }

    @ApiOperation(value = "新增")
    @RequestMapping(value="add",method = RequestMethod.POST,name = "新增操作")
    public ResponseObject add(@RequestBody SysMenu sysMenu) {
        if(null == sysMenu ){
            return ResponseObject.error("非法参数");
        }
        sysMenu.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysMenu.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        int resultCount = sysMenuService.save(sysMenu);
        long id = resultCount > 0 ? sysMenu.getId() : 0L;
        Map map = new HashMap<>();
        map.put("id",id);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value="edit",method = RequestMethod.POST,name = "修改操作")
    public ResponseObject edit(@RequestBody SysMenu sysMenu) {
        if(null == sysMenu || StringUtils.isEmpty(sysMenu.getId()) ){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysMenuService.update(sysMenu);
        return resultCount > 0 ? ResponseObject.ok("修改成功") : ResponseObject.error("修改失败");
    }

    @ApiOperation(value = "详情")
    @RequestMapping(value="detail/{id}",method = RequestMethod.GET,name = "查看详情")
    public ResponseObject<SysMenu> detail(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysMenu sysMenu = sysMenuService.getByPK(id);
        return new ResponseObject(sysMenu);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value="delete/{id}",method = RequestMethod.GET,name = "删除操作")
    public ResponseObject delete(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysMenuService.deleteByPK(id);
        return resultCount > 0 ? ResponseObject.ok("删除成功") : ResponseObject.error("删除失败");
    }

    @ApiOperation(value = "按条件统计")
    @RequestMapping(value="findByCount",method = RequestMethod.POST,name = "按条件统计")
    public ResponseObject findByCount(@RequestBody SysMenu sysMenu) {
        if(null == sysMenu || ObjectUtils.allFieldWithoutValue(sysMenu)){
            log.error("非法参数,sysMenu=" + sysMenu);
            return ResponseObject.error("非法参数");
        }
        int count = sysMenuService.findByCount(sysMenu);
        Map map = new HashMap<>();
        map.put("count",count);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "批量更新字段")
    @RequestMapping(value="batchUpdate",method = RequestMethod.POST,name = "批量更新字段")
    public ResponseObject batchUpdate(@RequestBody SysMenuBatchDto sysMenuBatchDto){
        if(null == sysMenuBatchDto || CollectionUtils.isEmpty(sysMenuBatchDto.getIdList())
                || null == sysMenuBatchDto.getSysMenu()  || ObjectUtils.allFieldWithoutValue(sysMenuBatchDto.getSysMenu())){
            log.error("非法参数！sysMenuBatchDto=" + sysMenuBatchDto);
            return ResponseObject.error("非法参数");
        }
        SysMenu sysMenu = sysMenuBatchDto.getSysMenu();
        sysMenu.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int resultCount =sysMenuService.batchUpdate(sysMenuBatchDto.getIdList(),sysMenu);
        return resultCount > 0 ? ResponseObject.ok("批量更新成功") : ResponseObject.error("批量更新失败");
    }

    @ApiOperation(value = "批量新增")
    @RequestMapping(value="batchInsert",method = RequestMethod.POST,name = "批量新增")
    public ResponseObject batchInsert(@RequestBody SysMenuBatchDto sysMenuBatchDto) {
        if(null == sysMenuBatchDto || CollectionUtils.isEmpty(sysMenuBatchDto.getSysMenuList())){
            log.error("非法参数,sysMenuBatchDto=" + sysMenuBatchDto);
            return ResponseObject.error("非法参数");
        }
        for(SysMenu sysMenu : sysMenuBatchDto.getSysMenuList()){
            sysMenu.setCreateTime(new Timestamp(System.currentTimeMillis()));
            sysMenu.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        }
        int resultCount = sysMenuService.batchInsert(sysMenuBatchDto.getSysMenuList());
        return resultCount > 0 ? ResponseObject.ok("批量新增成功") : ResponseObject.error("批量新增失败");
    }

}
