package com.liz.adminApi.service.impl;

import com.liz.adminApi.enums.ResponseCode;
import com.liz.adminApi.model.SysUser;
import com.liz.adminApi.service.LoginIService;
import com.liz.adminApi.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by lizhou on 2020/7/12.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class LoginService implements LoginIService{
    @Autowired
    private SysUserService sysUserService;
    @Override
    public String login(String username, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        List<SysUser>  sysUsers = sysUserService.listByProperty(sysUser);
        if(CollectionUtils.isEmpty(sysUsers) || sysUsers.size() != 1){
            return "用户不存在";
        }
        sysUser = sysUsers.get(0);
        if(!sysUser.getPassword().equals(password)){
            return "密码错误";
        }
        return ResponseCode.SUCCESS;
    }
}
