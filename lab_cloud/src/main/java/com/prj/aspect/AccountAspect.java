package com.prj.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
		
	@Before("@annotation(accountAccess) && args(dataWrapper,..)")
	public void checkBefore(DataWrapper<?> dataWrapper, AccountAccess accountAccess) {
		check(dataWrapper, accountAccess);
	}
	
	private void check(DataWrapper<?> dataWrapper, AccountAccess as) {
		String token = dataWrapper.getToken();
		AccountCharacter ac = dataWrapper.getAccountCharacter();
		if (token == null) {
			System.err.println("Token NULL");
			throw new AuthorityException(ErrorCodeEnum.Token_Invalid);
		} else if (ac == null) {
			throw new AuthorityException(ErrorCodeEnum.Account_Character_Null);
		} else {
			Account a = dao.findAccountbyToken(token, ac);
			if (a == null) {
				System.err.println("Token: " + token);
				throw new AuthorityException(ErrorCodeEnum.Token_Invalid);
			} else if (!TokenTool.isTokenValid(token)) {
				throw new AuthorityException(ErrorCodeEnum.Token_Expired);
			} else {
				dataWrapper.setAccountId(a.getId());
				if (as.checkAccountCharacter() != AccountCharacter.ANY) {
					if (dataWrapper.getAccountCharacter() != as.checkAccountCharacter()) {
						throw new AuthorityException(ErrorCodeEnum.Access_Denied);
					}
				}
			}
		}
	}
}
