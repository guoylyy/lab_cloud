package com.prj.service;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Account;
import com.prj.util.AccountCharacter;
import com.prj.util.DataWrapper;
import com.prj.util.Page;
import com.prj.util.PasswordReset;

public interface AccountService {
	DataWrapper<List<Account>> getAllAccount();

	public DataWrapper<Account> deleteAccountById(Integer id);

//	Integer addAccount(Account entity);

	DataWrapper<Account> updateAccount(Account entity);

	DataWrapper<Account> getAccountById(int id);

	Page<Account> getAccountbyPage(int pagenumber, int pagesize);

	Page<Account> searchAccount(int pagenumber, int pagesize, String name);

	Page<Account> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

	DataWrapper<Account> login(Account account);

	DataWrapper<Account> addAccount(Account account);

	DataWrapper<Account> reset(PasswordReset data);

	void logout(Integer id);

	DataWrapper<Account> updateAccountCharacter(Integer accountId,
			AccountCharacter accountCharacter);

}
