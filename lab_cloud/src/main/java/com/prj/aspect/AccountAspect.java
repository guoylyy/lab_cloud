package com.prj.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.prj.dao.AccountDao;
import com.prj.entity.Account;
import com.prj.util.DataWrapper;
import com.prj.util.TokenTool;

@Component
@Aspect
public class AccountAspect {
	
	@Resource(name = "AccountDaoImpl")
	AccountDao dao;
	
	@Before(value = "execution (* com.prj.controller.*.*(..)) "
			+ "&& !execution (* com.prj.controller.AccountController.login(..)) "
			+ "&& !execution (* com.prj.controller.AccountController.register(..)) "
			+ "&& args(dataWrapper,..)",
			argNames = "dataWrapper")
	public void checkBefore(DataWrapper<?> dataWrapper) throws Exception {
		System.out.println(dataWrapper.getToken());
		System.out.println("================");
		String token = dataWrapper.getToken();
		Account a = dao.findAccountbyId(TokenTool.getId(token));
		if (a.getLoginToken().equals(token)) {
			if (TokenTool.isTokenValid(token)) {
			} else {
				throw new Exception("Not Valid");
			}
		} else {
			throw new Exception("token wrong");
		}
	}
	
	@Before(value = "execution (* com.prj.controller.*.*(..)) "
			+ "&& !execution (* com.prj.controller.AccountController.login(..)) "
			+ "&& !execution (* com.prj.controller.AccountController.register(..)) "
			+ "&& args(id,dataWrapper,..)",
			argNames = "id, dataWrapper")
	public void checkIdBefore(Integer id, DataWrapper<?> dataWrapper) throws Exception {
		System.out.println(dataWrapper.getToken());
		System.out.println("================");
		String token = dataWrapper.getToken();
		if (!id.equals(TokenTool.getId(token)))
			throw new Exception("different id");
		Account a = dao.findAccountbyId(TokenTool.getId(token));
		if (a.getLoginToken().equals(token)) {
			if (TokenTool.isTokenValid(token)) {
			} else {
				throw new Exception("Not Valid");
			}
		} else {
			throw new Exception("token wrong");
		}
	}
}
