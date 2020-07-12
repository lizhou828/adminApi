package com.liz.adminApi.service;
import com.liz.adminApi.ApplicationTests;
import com.liz.adminApi.model.SysMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 */
public class SysMenuServiceTest extends ApplicationTests {

    private static final Logger log = LogManager.getLogger(SysMenuServiceTest.class);
    @Autowired
    private SysMenuService sysMenuService;


    @Test
    public void getByPKTest() throws Exception {
        SysMenu sysMenu  = sysMenuService.getByPK(1);
        System.out.println(sysMenu);
    }

}
