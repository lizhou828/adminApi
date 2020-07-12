///*
// *Project: glorypty-framework
// *File: com.glorypty.framework.shiro.AuthenticationRealm.java <2015年8月4日>
// ****************************************************************
// * 版权所有@2015 国裕网络科技  保留所有权利.
// ***************************************************************/
package com.liz.adminApi.shiro;

import com.github.pagehelper.util.StringUtil;
import com.liz.adminApi.enums.ResponseCode;
import com.liz.adminApi.model.*;
import com.liz.adminApi.service.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author hardy
 * @Date 2015年8月4日 下午4:19:43
 * @version 1.0
 */
@Component
public class CustomizeRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(CustomizeRealm.class);
	@Autowired
	private LoginIService loginIService;
	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	private SysRoleMenuService roleMenuIService;

	@Autowired
	private SysRoleMenuFunctionService roleMenuFunctionIService;

	@Autowired
	private SysMenuFunctionService menuFunctionIService;
	@Autowired
	private SysMenuService menuIService;


	/**
	 * 所支持的token类型：UsernamePasswordToken用户名密码类型的，UsbKeyToken自定义的token类型的
	 * @param token
	 * @return
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken ;
	}


	/**
	 * 认证信息 本例中该方法的调用时机为执行Subject.login()时
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken){
		String account = null;
		String password = null;

		String message = "";
		/* 用户名密码登陆 */
		if(authcToken instanceof UsernamePasswordToken){
			UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
			account = token.getUsername();
			password =  String.valueOf(token.getPassword());
			logger.info("登陆认证:account=" + account +",password=" + password);
			message = loginIService.login(account,password);

		/* 硬件UsbKey登陆 */
//		}else if(authcToken instanceof UsbKeyToken){


		}

		if(StringUtil.isEmpty(message)){
			throw new AuthenticationException("登陆服务异常！");
		}
		if(!ResponseCode.SUCCESS.equals(message)){
			throw new AuthenticationException(message);
		}

		if(authcToken instanceof UsernamePasswordToken){
			return new SimpleAuthenticationInfo(account, password.toCharArray(), getName());
//		}else if(authcToken instanceof UsbKeyToken){
//			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//			HttpSession session = request.getSession();
//			account = (String) session.getAttribute(Constant.LOGIN_SESSION_USERNAME);
//			return new SimpleAuthenticationInfo(account, signValue, getName());
		}else{
			throw new AuthenticationException("登陆失败，未知类型的token！");
		}
	}

	/**
	 * org.apache.shiro.realm.AuthorizingRealm.getAuthorizationInfo()会调用该方法：
	 * 其内部策略：
	 * 1、先从Cache<Object, AuthorizationInfo> authorizationCache中去查找先关授权信息
	 * 2、如果有，则直接返回，不再执行下面的方法
	 * 3、如果没有，则执行下面的方法
	 *
	 *
	 * 授权信息 为当前登录的Subject授予角色和权限
	 * 会进入授权方法一共有三种情况：
	 1、subject.hasRole(“admin”) 或 subject.isPermitted(“admin”)：自己去调用这个是否有什么角色或者是否有什么权限的时候；
	 2、@RequiresRoles("admin") ：在方法上加注解的时候；
	 3、<shiro.hasPermission name = "admin">....</shiro.hasPermission>在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候。
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principals) {
		String account = (String) principals.getPrimaryPrincipal();

		logger.info("登陆授权:account=" + account );
		if (StringUtil.isEmpty(account)){
			logger.error("登陆授权:失败account=" + account);
			return null;
		}

		SysUser user = sysUserService.getByUserName
				(account);
		if(null == user){
			logger.error("登陆授权:失败user=null");
			return null;
		}


		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		if(null == companySubaccount.getCompanyRoleId() || companySubaccount.getCompanyRoleId() == 0L){
//			//主账户拥有该企业的所有权限
//			info.addStringPermission("*:*");
//			return info;
//		}
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUserId(user.getId());
		List<SysUserRole> sysUserRoleList =  sysUserRoleService.listByProperty(sysUserRole);
		if(CollectionUtils.isEmpty(sysUserRoleList)  || sysUserRoleList.size() != 1){
			logger.error("登陆授权:失败role=null");
			return null;
		}
		sysUserRole = sysUserRoleList.get(0);
		SysRole role = sysRoleService.getByPK(sysUserRole.getRoleId().intValue());
		if(role == null ){
			logger.error("登陆授权:失败role=null");
			return null;
		}
		/* 赋予角色 */
		info.addRole(role.getRoleName());



		/* 赋予左侧主菜单的权限 */
		List<String> menuCodes = new ArrayList<String>();
		SysRoleMenu roleMenuQuery = new SysRoleMenu();
		roleMenuQuery.setRoleId(role.getId());
		List<SysRoleMenu> roleMenuList = roleMenuIService.listByProperty(roleMenuQuery);
		if(null == roleMenuList || roleMenuList.size() == 0){
			logger.error("登陆授权:失败roleMenuList=" + roleMenuList);
			return info;
		}
		SysMenu menu = null;
		for(SysRoleMenu roleMenu : roleMenuList){
			if(null == roleMenu || roleMenu.getMenuId() <= 0){
				continue;
			}
			menu = menuIService.getByPK(roleMenu.getMenuId().intValue());
			if(null == menu  || StringUtil.isEmpty(menu.getUrl())){
				continue;
			}
			menuCodes.add(menu.getUrl());
		}
		if(menuCodes.size() > 0){
			info.addStringPermissions(menuCodes);
		}



		/* 赋予按钮的权限 */
		SysRoleMenuFunction roleMenuFunctionQuery = new SysRoleMenuFunction();
		roleMenuFunctionQuery.setRoleId(role.getId());
		List<SysRoleMenuFunction> roleMenuFunctionList  = roleMenuFunctionIService.listByProperty(roleMenuFunctionQuery);
		if(null == roleMenuFunctionList || roleMenuFunctionList.size() == 0){
			logger.error("登陆授权:失败roleMenuFunctionList=" + roleMenuFunctionList);
			return info;
		}

		List<String> menuFunctionCodes = new ArrayList<String>();
		SysMenuFunction menuFunction = null;
		for(SysRoleMenuFunction roleMenuFunction : roleMenuFunctionList){
			if(null == roleMenuFunction || roleMenuFunction.getMenuFunctionId() <= 0){
				continue;
			}
			menuFunction = menuFunctionIService.getByPK(roleMenuFunction.getMenuFunctionId().intValue());
			if(null == menuFunction || StringUtil.isEmpty(menuFunction.getCode())){
				continue;
			}
			if(menuFunction.getCode().contains(",")){
				for(String code : menuFunction.getCode().split(",")){
					if(StringUtil.isNotEmpty(code)){
						menuFunctionCodes.add(code);
					}
				}
			}else{
				menuFunctionCodes.add(menuFunction.getCode());
			}
		}

		if(menuFunctionCodes.size() > 0){
			info.addStringPermissions(menuFunctionCodes);
		}
		return info;
	}

}
