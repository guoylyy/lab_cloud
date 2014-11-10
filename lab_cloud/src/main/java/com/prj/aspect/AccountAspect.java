package com.prj.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.prj.dao.AccountDao;
import com.prj.entity.Account;
import com.prj.util.TokenTool;

@Aspect
public class AccountAspect {
	
//	@Resource(name = "AccountDaoImp")
//	AccountDao dao;
	
	@Before("execution(* *(..))")
	public void checkBefore() throws Exception {
		@SuppressWarnings("unused")
		String token = "";
		token = "asd";
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
