package com.liz.adminApi.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Administrator
 */
@ApiModel(value = "用户登录后身份信息Dto")
public class UserLoginInfoDto implements Serializable {

    private static final long serialVersionUID = -7154309008848446747L;

    @ApiModelProperty(name = "token",value = "登录后获取的token")
    private String token;

    @ApiModelProperty(name = "userId",value = "用户ID")
    private String userId;

    @ApiModelProperty(name = "userType",value = "用户类型（1表示管理员，2表示商户,3表示用户）",example = "3")
    private Integer userType;

    @ApiModelProperty(name = "realName",value = "真实用户名")
    private String realName;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }



    @Override
    public String toString() {
        return "UserLoginInfoDto{" +
                "token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                ", userType=" + userType +
                ", realName='" + realName + '\'' +
                '}';
    }
}
