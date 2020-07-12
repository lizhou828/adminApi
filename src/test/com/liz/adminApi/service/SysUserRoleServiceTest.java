package com.liz.adminApi.service;
import com.liz.adminApi.ApplicationTests;
import com.liz.adminApi.model.SysUserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 */
public class SysUserRoleServiceTest extends ApplicationTests {

    private static final Logger log = LogManager.getLogger(SysUserRoleServiceTest.class);
    @Autowired
    private SysUserRoleService sysUserRoleService;


    @Test
    public void getByPKTest() throws Exception {
        SysUserRole sysUserRole  = sysUserRoleService.getByPK(1);
        System.out.println(sysUserRole);
    }

}
