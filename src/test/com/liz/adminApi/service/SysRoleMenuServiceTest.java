package com.liz.adminApi.service;
import com.liz.adminApi.ApplicationTests;
import com.liz.adminApi.model.SysRoleMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 */
public class SysRoleMenuServiceTest extends ApplicationTests {

    private static final Logger log = LogManager.getLogger(SysRoleMenuServiceTest.class);
    @Autowired
    private SysRoleMenuService sysRoleMenuService;


    @Test
    public void getByPKTest() throws Exception {
        SysRoleMenu sysRoleMenu  = sysRoleMenuService.getByPK(1);
        System.out.println(sysRoleMenu);
    }

}
