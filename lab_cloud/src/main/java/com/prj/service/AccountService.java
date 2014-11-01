package com.prj.service;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Account;
import com.prj.util.Page;

public interface AccountService {
	List<Account> getAllAccount();

	boolean deleteAccount(Account entity);

	boolean addAccount(Account entity);

	Account updateAccount(Account entity);

	Account getAccountById(int id);

	Page<Account> getAccountbyPage(int pagenumber, int pagesize);

	Page<Account> searchAccount(int pagenumber, int pagesize, String name);

	Page<Account> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

	Account login(Account account);

}
