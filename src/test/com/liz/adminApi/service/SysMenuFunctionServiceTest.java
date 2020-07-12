package com.liz.adminApi.service;
import com.liz.adminApi.ApplicationTests;
import com.liz.adminApi.model.SysMenuFunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 */
public class SysMenuFunctionServiceTest extends ApplicationTests {

    private static final Logger log = LogManager.getLogger(SysMenuFunctionServiceTest.class);
    @Autowired
    private SysMenuFunctionService sysMenuFunctionService;


    @Test
    public void getByPKTest() throws Exception {
        SysMenuFunction sysMenuFunction  = sysMenuFunctionService.getByPK(1);
        System.out.println(sysMenuFunction);
    }

}
