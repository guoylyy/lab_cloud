package com.prj.service;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Account;
import com.prj.util.DataWrapper;
import com.prj.util.Page;
import com.prj.util.PasswordReset;

public interface AccountService {
	List<Account> getAllAccount();

	boolean deleteAccount(Account entity);

	Integer addAccount(Account entity);

	Account updateAccount(Account entity);

	DataWrapper<Account> getAccountById(int id);

	Page<Account> getAccountbyPage(int pagenumber, int pagesize);

	Page<Account> searchAccount(int pagenumber, int pagesize, String name);

	Page<Account> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

	DataWrapper<Account> login(Account account);

	DataWrapper<Account> register(Account account);

	DataWrapper<Account> reset(PasswordReset data);

	void logout(Integer id);

}
