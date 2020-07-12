package com.liz.adminApi.service;
import com.liz.adminApi.ApplicationTests;
import com.liz.adminApi.model.SysUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 */
public class SysUserServiceTest extends ApplicationTests {

    private static final Logger log = LogManager.getLogger(SysUserServiceTest.class);
    @Autowired
    private SysUserService sysUserService;


    @Test
    public void getByPKTest() throws Exception {
        SysUser sysUser  = sysUserService.getByPK(1);
        System.out.println(sysUser);
    }

}
