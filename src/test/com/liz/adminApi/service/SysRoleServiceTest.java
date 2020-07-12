package com.liz.adminApi.service;
import com.liz.adminApi.ApplicationTests;
import com.liz.adminApi.model.SysRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 */
public class SysRoleServiceTest extends ApplicationTests {

    private static final Logger log = LogManager.getLogger(SysRoleServiceTest.class);
    @Autowired
    private SysRoleService sysRoleService;


    @Test
    public void getByPKTest() throws Exception {
        SysRole sysRole  = sysRoleService.getByPK(1);
        System.out.println(sysRole);
    }

}
