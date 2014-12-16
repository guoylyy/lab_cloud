package com.prj.dao;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Account;
import com.prj.util.AccountCharacter;
import com.prj.util.DataWrapper;
import com.prj.util.Page;

public interface AccountDao {
	Integer addAccount(Account v);

	Account disableAccountById(Integer id);

	Account findAccountbyId(int id);

	DataWrapper<List<Account>> getAllAccount();

	Account updateAccount(Account v);

	Page<Account> getAccountbyPage(int pagenumber, int pagesize);

	List<Account> getByCondition(List<SimpleExpression> list);

	Page<Account> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

	Account getAccountByNumber(String number);

	Account findAccountbyToken(String token, AccountCharacter ac);

}
