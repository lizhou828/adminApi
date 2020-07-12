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
import com.liz.adminApi.model.SysRoleMenu;
import com.liz.adminApi.model.dto.SysRoleMenuBatchDto;
import com.liz.adminApi.model.query.SysRoleMenuQuery;
import com.liz.adminApi.service.SysRoleMenuService;
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
 * @date 创建于2020-07-12 03:56:257,027  
 * 角色菜单关联表管理
 */
@RestController
@RequestMapping(value = "/api/sysRoleMenu",name = "角色菜单关联表管理")
@Api(value="角色菜单关联表",tags = "角色菜单关联表接口")
public class SysRoleMenuController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(SysRoleMenuController.class);

    @Autowired
    private SysRoleMenuService sysRoleMenuService;


    @ApiOperation(value = "按条件的分页查询")
    @RequestMapping(value="/findByPage",method = RequestMethod.POST,name = "按条件的分页查询")
    public ResponseObject<PageInfoDto<SysRoleMenu>> findByPage(@RequestBody SysRoleMenuQuery sysRoleMenuQuery) {
        PageInfoDto<SysRoleMenu> pageInfoDto = null;
        if(null == sysRoleMenuQuery){
            pageInfoDto = new PageInfoDto<SysRoleMenu>(1,10,0,0);
            pageInfoDto.setList(new ArrayList<>());
            return new ResponseObject<>(pageInfoDto);
        }
        if(sysRoleMenuQuery.getPageNum() == null || sysRoleMenuQuery.getPageNum() <= 0 ){
            sysRoleMenuQuery.setPageNum(1);
        }
        if(sysRoleMenuQuery.getPageSize() == null || sysRoleMenuQuery.getPageSize() <= 0 ){
            sysRoleMenuQuery.setPageSize(20);
        }
        pageInfoDto  = sysRoleMenuService.findByPage(sysRoleMenuQuery);

        if(null == pageInfoDto || CollectionUtils.isEmpty(pageInfoDto.getList())){
            pageInfoDto = new PageInfoDto<SysRoleMenu>(sysRoleMenuQuery.getPageNum(),sysRoleMenuQuery.getPageSize(),0,0);
            pageInfoDto.setList(new ArrayList<>());
        }
        return new ResponseObject<>(pageInfoDto);
    }

    @ApiOperation(value = "根据主键查询")
    @GetMapping("/getByPK/{id}")
    public ResponseObject<SysRoleMenu> getByPK(@PathVariable("id") Integer id){
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysRoleMenu sysRoleMenu = sysRoleMenuService.getByPK(id);
        return new ResponseObject<>(sysRoleMenu);
    }

    @ApiOperation(value = "按条件的查询所有")
    @RequestMapping(value="/listByProperty",method = RequestMethod.POST,name = "按条件的查询所有")
    public ResponseObject<List<SysRoleMenu>> listByProperty(@RequestBody SysRoleMenu sysRoleMenu) {
        List<SysRoleMenu> sysRoleMenuList =  sysRoleMenuService.listByProperty(sysRoleMenu);
        return new ResponseObject(sysRoleMenuList);
    }

    @ApiOperation(value = "新增")
    @RequestMapping(value="add",method = RequestMethod.POST,name = "新增操作")
    public ResponseObject add(@RequestBody SysRoleMenu sysRoleMenu) {
        if(null == sysRoleMenu ){
            return ResponseObject.error("非法参数");
        }
        sysRoleMenu.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysRoleMenu.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        int resultCount = sysRoleMenuService.save(sysRoleMenu);
        long id = resultCount > 0 ? sysRoleMenu.getId() : 0L;
        Map map = new HashMap<>();
        map.put("id",id);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value="edit",method = RequestMethod.POST,name = "修改操作")
    public ResponseObject edit(@RequestBody SysRoleMenu sysRoleMenu) {
        if(null == sysRoleMenu || StringUtils.isEmpty(sysRoleMenu.getId()) ){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysRoleMenuService.update(sysRoleMenu);
        return resultCount > 0 ? ResponseObject.ok("修改成功") : ResponseObject.error("修改失败");
    }

    @ApiOperation(value = "详情")
    @RequestMapping(value="detail/{id}",method = RequestMethod.GET,name = "查看详情")
    public ResponseObject<SysRoleMenu> detail(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        SysRoleMenu sysRoleMenu = sysRoleMenuService.getByPK(id);
        return new ResponseObject(sysRoleMenu);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value="delete/{id}",method = RequestMethod.GET,name = "删除操作")
    public ResponseObject delete(@PathVariable("id") Integer id) {
        if(null == id || id < 0){
            return ResponseObject.error("非法参数");
        }
        int resultCount = sysRoleMenuService.deleteByPK(id);
        return resultCount > 0 ? ResponseObject.ok("删除成功") : ResponseObject.error("删除失败");
    }

    @ApiOperation(value = "按条件统计")
    @RequestMapping(value="findByCount",method = RequestMethod.POST,name = "按条件统计")
    public ResponseObject findByCount(@RequestBody SysRoleMenu sysRoleMenu) {
        if(null == sysRoleMenu || ObjectUtils.allFieldWithoutValue(sysRoleMenu)){
            log.error("非法参数,sysRoleMenu=" + sysRoleMenu);
            return ResponseObject.error("非法参数");
        }
        int count = sysRoleMenuService.findByCount(sysRoleMenu);
        Map map = new HashMap<>();
        map.put("count",count);
        return new ResponseObject(map);
    }

    @ApiOperation(value = "批量更新字段")
    @RequestMapping(value="batchUpdate",method = RequestMethod.POST,name = "批量更新字段")
    public ResponseObject batchUpdate(@RequestBody SysRoleMenuBatchDto sysRoleMenuBatchDto){
        if(null == sysRoleMenuBatchDto || CollectionUtils.isEmpty(sysRoleMenuBatchDto.getIdList())
                || null == sysRoleMenuBatchDto.getSysRoleMenu()  || ObjectUtils.allFieldWithoutValue(sysRoleMenuBatchDto.getSysRoleMenu())){
            log.error("非法参数！sysRoleMenuBatchDto=" + sysRoleMenuBatchDto);
            return ResponseObject.error("非法参数");
        }
        SysRoleMenu sysRoleMenu = sysRoleMenuBatchDto.getSysRoleMenu();
        sysRoleMenu.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int resultCount =sysRoleMenuService.batchUpdate(sysRoleMenuBatchDto.getIdList(),sysRoleMenu);
        return resultCount > 0 ? ResponseObject.ok("批量更新成功") : ResponseObject.error("批量更新失败");
    }

    @ApiOperation(value = "批量新增")
    @RequestMapping(value="batchInsert",method = RequestMethod.POST,name = "批量新增")
    public ResponseObject batchInsert(@RequestBody SysRoleMenuBatchDto sysRoleMenuBatchDto) {
        if(null == sysRoleMenuBatchDto || CollectionUtils.isEmpty(sysRoleMenuBatchDto.getSysRoleMenuList())){
            log.error("非法参数,sysRoleMenuBatchDto=" + sysRoleMenuBatchDto);
            return ResponseObject.error("非法参数");
        }
        for(SysRoleMenu sysRoleMenu : sysRoleMenuBatchDto.getSysRoleMenuList()){
            sysRoleMenu.setCreateTime(new Timestamp(System.currentTimeMillis()));
            sysRoleMenu.setDropState(DropStateEnum.NOT_DELETEED.getCode());
        }
        int resultCount = sysRoleMenuService.batchInsert(sysRoleMenuBatchDto.getSysRoleMenuList());
        return resultCount > 0 ? ResponseObject.ok("批量新增成功") : ResponseObject.error("批量新增失败");
    }

}
