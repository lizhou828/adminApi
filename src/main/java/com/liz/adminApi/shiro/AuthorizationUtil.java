/*
 *Project: glorypty-framework
 *File: com.glorypty.framework.shiro.AuthorizationUtil.java <2015年8月4日>
 ****************************************************************
 * 版权所有@2015 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.liz.adminApi.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hardy 
 * @Date 2015年8月4日 下午4:19:19
 * @version 1.0
 */
public class AuthorizationUtil {
	/**至高无上的全局账号*/
	@SuppressWarnings("serial")
	public static final List<String> AUTHORIZ_SOVEREIGN_ACCOUNT = new ArrayList<String>(){{add("developer");add("dev");add("superadmin");}};
	
	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * @author hardy<2015年9月23日>
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 * @param key
	 * @param value
	 */
	public static void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
	
	/**
	 * 获取菜单编码
	 *  1.截取以"/"开始后、最后一个"/"间的编码串,
	 *  2.转换所有"/"为"_"
	 * @author hardy<2015年8月4日>
	 * @param permissionUrl
	 * @return
	 */
	public static String getMenuCodeByUrl(String permissionUrl){
		if(StringUtils.isEmpty(permissionUrl))
			return null;
		
		// 模块标记:
		String permissionMark = null;
		if(permissionUrl.startsWith("/")){
			permissionUrl = permissionUrl.substring(1);
		}
		if(permissionUrl.lastIndexOf("/") > -1){			
			permissionMark = permissionUrl.substring(0, permissionUrl.lastIndexOf("/"));			
		}	
		return StringUtils.isNotEmpty(permissionMark)
				? permissionMark.replace("/", "_")
				: ("#".equalsIgnoreCase(permissionUrl)?"#":null);
	}
	
	/**
	 * 获取权限编码： admin_centre_user:add,admin_centre_user:addSave
	 * @author LiuLei<2015年8月24日>
	 * @param permissionUrl:菜单URL ，如：/admin/centre/menu/fmindex.shtml
	 * @param funcCodes:操作代码    ，如：edit,editSave
	 * @return
	 */
	public static String getPermissionCode(String permissionUrl, String funcCodes){
		StringBuffer buf = new StringBuffer();
		permissionUrl = getMenuCodeByUrl(permissionUrl);
		for(String funcCode: funcCodes.split(",")){
			buf.append(permissionUrl).append(':').append(funcCode).append(',');
		}
		buf.deleteCharAt(buf.length() - 1);
		return buf.toString();
	}
}
