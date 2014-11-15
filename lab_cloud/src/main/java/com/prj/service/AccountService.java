package com.prj.service;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Account;
import com.prj.util.AccountCharacter;
import com.prj.util.DataWrapper;
import com.prj.util.Page;
import com.prj.util.PasswordReset;

public interface AccountService {
	DataWrapper<Account> login(Account account);

	void logout(Integer id);

	DataWrapper<Account> addAccount(Account account);

	public DataWrapper<Account> disableAccountById(Integer id);

	DataWrapper<List<Account>> getAllAccount();

	DataWrapper<Account> getAccountById(int id);

	DataWrapper<Account> updateAccount(Account entity);

	DataWrapper<Account> updateAccountCharacter(Integer accountId,
	AccountCharacter accountCharacter);

	DataWrapper<Account> reset(PasswordReset data);

	Page<Account> getAccountbyPage(int pagenumber, int pagesize);

	Page<Account> searchAccount(int pagenumber, int pagesize, String name);

	Page<Account> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

}
