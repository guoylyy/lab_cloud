package com.prj.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class AccountAspect {
	
//	@Resource(name = "AccountDaoImp")
//	AccountDao dao;
	
	@Before("execution (* com.prj.serviceImpl.*.*(..))")
	public void checkBefore() throws Exception {
		@SuppressWarnings("unused")
		String token = "";
		token = "asd";
		System.out.println("=ddd=");
		//String token = dataWrapper.getToken();
//		Account a = dao.findAccountbyId(TokenTool.getId(token));
//		if (a.getLoginToken().equals(token)) {
//			if (TokenTool.isTokenValid(token)) {
//			} else {
//				
//			}
//		} else {
//			throw new Exception("test");
//		}
	}
}
