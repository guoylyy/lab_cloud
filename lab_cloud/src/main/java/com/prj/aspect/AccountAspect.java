package com.prj.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.prj.dao.AccountDao;
import com.prj.entity.Account;
import com.prj.util.AccountAccess;
import com.prj.util.AuthorityException;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;
import com.prj.util.TokenTool;

@Component
@Aspect
public class AccountAspect {
	
	@Resource(name = "AccountDaoImpl")
	AccountDao dao;
	
//	@Before(value = "execution (* com.prj.controller.*.*(..)) "
//			+ "&& !execution (* com.prj.controller.AccountController.login(..)) "
//			+ "&& !execution (* com.prj.controller.AccountController.register(..)) "
//			+ "&& args(dataWrapper,..)",
//			argNames = "dataWrapper")
//	public void checkBefore(DataWrapper<?> dataWrapper) throws AuthorityException {
//		System.out.println(dataWrapper.getToken());
//		checkToken(dataWrapper);
//	}
//
//	@Before(value = "execution (* com.prj.controller.*.*(..)) "
//			+ "&& !execution (* com.prj.controller.AccountController.login(..)) "
//			+ "&& !execution (* com.prj.controller.AccountController.register(..)) "
//			+ "&& args(id,dataWrapper,..)",
//			argNames = "id, dataWrapper")
//	public void checkIdBefore(Integer id, DataWrapper<?> dataWrapper) throws Exception {
//		System.out.println(dataWrapper.getToken());
////		String token = dataWrapper.getToken();
////		if (!id.equals(TokenTool.getId(token)))
////			throw new Exception("different id");
//		checkToken(dataWrapper);
//	}

	@Pointcut("@annotation(com.prj.util.AccountAccess)")
	public void accountAccess() {}
	
	@Before(value = "accountAccess() "
			+ "&& @annotation(accountAccess)"
			+ "&& args(dataWrapper,..)",
			argNames = "accountAccess, dataWrapper")
	public void checkBefore(AccountAccess accountAccess, 
			DataWrapper<?> dataWrapper) {
		check(dataWrapper, accountAccess);
	}
	
//	@Before(value = "accountAccess() "
//			+ "&& @annotation(accountAccess)"
//			+ "&& args(id,dataWrapper,..)",
//			argNames = "accountAccess, id, dataWrapper")
//	public void checkIdBefore(AccountAccess accountAccess, 
//			Integer id, DataWrapper<?> dataWrapper) {
//		check(dataWrapper, accountAccess);
//	}
	
	private void check(DataWrapper<?> dataWrapper, AccountAccess as) {
		String token = dataWrapper.getToken();
		Account a = dao.findAccountbyToken(token);
		if (token != null && a != null && token.equals(a.getLoginToken())) {
			if (TokenTool.isTokenValid(token)) {
				dataWrapper.setAccountId(a.getId());
			} else {
				throw new AuthorityException(ErrorCodeEnum.Token_Expired);
			}
		} else {
			throw new AuthorityException(ErrorCodeEnum.Token_Invalid);
		}
		
		if (as.checkAdmin()) {
			if (a.getAccountCharacter() != Account.Character.ADMINISTRATOR) {
				throw new AuthorityException(ErrorCodeEnum.Access_Denied);
			}
		}
	}
}
