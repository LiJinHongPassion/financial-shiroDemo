package com.cqut.financial.ssm.service.Impl;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

@Service
public class ShiroService {


	/**
	 * 普通的service都可以添加这个注解
	 * @RequiresRoles({"admin,"user"},logical=Logical.AND):表示当前需要admin和user的角色
	 * @RequiresRolesUser:表示已经身份认证或者通过记住我登录的
	 * @RequiresAuthentication:表示已经通过了身份认证
	 * @RequiresPermissions(value={"user:a","user:b"},logical=Logical.OR)
	 *
	 */

//	@RequiresRoles({"admin"})
	public void testMethod(){
		System.out.println("testMethod, time: " + new Date());
		
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("key");
		
		System.out.println("Service SessionVal: " + val);
	}
	
}
 