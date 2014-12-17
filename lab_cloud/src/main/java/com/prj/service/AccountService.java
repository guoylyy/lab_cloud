package com.prj.service;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Account;
import com.prj.util.AccountCharacter;
import com.prj.util.DataWrapper;
import com.prj.util.Page;
import com.prj.util.PasswordReset;
import com.prj.util.SearchCriteria;

public interface AccountService {
	DataWrapper<Account> login(Account account, AccountCharacter ac);

	void logout(Integer id, AccountCharacter ac);

	DataWrapper<Account> addAccount(Account account);

	DataWrapper<Account> disableAccountById(Integer id);

	DataWrapper<List<Account>> getAllAccount();

	DataWrapper<Account> getAccountById(int id);

	DataWrapper<Account> updateAccount(Account entity);

//	DataWrapper<Account> updateAccountCharacter(Integer accountId,
//	AccountCharacter accountCharacter);

	DataWrapper<Account> reset(PasswordReset data);

	Page<Account> getAccountbyPage(int pagenumber, int pagesize);

	Page<Account> searchAccount(int pagenumber, int pagesize, String name);

	Page<Account> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

	DataWrapper<List<? extends Account>> searchAccount(SearchCriteria sc);
	
	Account getAccountByChar(Account account, AccountCharacter ac);

	DataWrapper<Account> getAccountByIdChar(Integer id, AccountCharacter ac);
	
	DataWrapper<Account> updateAccountByChar(Account account, AccountCharacter ac);
	
	DataWrapper<List<? extends Account>> getAccountByRole(AccountCharacter ac);
	
	DataWrapper<List<? extends Account>> getAccountByStatus(Account.Status status);
}
