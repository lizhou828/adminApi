/*
 *Project: glorypty-framework
 *File: com.glorypty.framework.shiro.AuthorizationJhFilter.java <2015年8月4日>
 ****************************************************************
 * 版权所有@2015 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.liz.adminApi.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 *
 * @author hardy 
 * @Date 2015年8月4日 下午4:23:39
 * @version 1.0
 */
public class AuthorizationJhFilter extends AuthorizationFilter {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationJhFilter.class);

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object handler) throws Exception {
		return this.isAccessURL(request, response, handler);
	}

	
	/**
	 * 动态URL过滤
	 * @author hardy<2015年8月3日>
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 */
	private boolean isAccessURL(ServletRequest request, ServletResponse response, Object handler){
		String permissionUrl = ((HttpServletRequest)request).getServletPath();

		if(StringUtils.isEmpty(permissionUrl))
			return false;
		
		// 模块操作(忽略功能模块首页)
		String permissionMethod = permissionUrl.substring(permissionUrl.lastIndexOf("/")==-1 ? 0 : permissionUrl.lastIndexOf("/")+1).split("\\.")[0];
		if(StringUtils.isNotEmpty(permissionMethod) && permissionMethod.equalsIgnoreCase("index")){
			return true;
		}
		String lower = permissionMethod.toLowerCase();
		if( lower.contains("list") || lower.contains("query") || lower.contains("search") || lower.contains("find")|| lower.contains("get")  || lower.contains("detail")){
			return true;//查询的 不做鉴权处理
		}

		
		// 模块标记
		String permissionMark = AuthorizationUtil.getMenuCodeByUrl(permissionUrl);
		if(StringUtils.isNotEmpty(permissionMark)){
			logger.debug(MessageFormat.format("Authorization Filter ==> {0}:{1}", permissionMark, permissionMethod));
			//以“ign”、“ignore”开头的方法名，不做鉴权处理
			if(permissionMethod.startsWith("ign") || permissionMethod.startsWith("ignore") || StringUtils.isEmpty(permissionMethod.trim())){//个别方法名公用的、以“/”结尾的，不做处理
				logger.debug("Authorization Filter ==> Result:Ignore Method");
				return true;
			}
			// 权限校验
			if (this.getSubject(request, response).isPermitted(MessageFormat.format("{0}:{1}", permissionMark, permissionMethod))) {
				return true;
			}
		}
		logger.debug("Authorization Filter==>Result:UnAuthorized");
		return false;
	}

}
