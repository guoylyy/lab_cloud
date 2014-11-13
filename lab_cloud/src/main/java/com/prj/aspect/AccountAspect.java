package com.prj.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.prj.dao.AccountDao;
import com.prj.entity.Account;
import com.prj.util.AccountAccess;
import com.prj.util.AccountCharacter;
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
		if (token == null) {
			throw new AuthorityException(ErrorCodeEnum.Token_Invalid);
		} else {
			Account a = dao.findAccountbyToken(token);
			if (a == null) {
				throw new AuthorityException(ErrorCodeEnum.Token_Invalid);
			} else if (!TokenTool.isTokenValid(token)) {
				throw new AuthorityException(ErrorCodeEnum.Token_Expired);
			} else {
				dataWrapper.setAccountId(a.getId());
				if (as.checkAccountCharacter() != AccountCharacter.ANY) {
					if (a.getAccountCharacter() != as.checkAccountCharacter()) {
						throw new AuthorityException(ErrorCodeEnum.Access_Denied);
					}
				}
			}
		}
	}
}
