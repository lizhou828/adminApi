package com.liz.adminApi.service;
import com.liz.adminApi.ApplicationTests;
import com.liz.adminApi.model.SysRoleMenuFunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 */
public class SysRoleMenuFunctionServiceTest extends ApplicationTests {

    private static final Logger log = LogManager.getLogger(SysRoleMenuFunctionServiceTest.class);
    @Autowired
    private SysRoleMenuFunctionService sysRoleMenuFunctionService;


    @Test
    public void getByPKTest() throws Exception {
        SysRoleMenuFunction sysRoleMenuFunction  = sysRoleMenuFunctionService.getByPK(1);
        System.out.println(sysRoleMenuFunction);
    }

}
