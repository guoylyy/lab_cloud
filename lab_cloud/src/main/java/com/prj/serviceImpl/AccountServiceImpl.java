package com.prj.serviceImpl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.AccountDao;
import com.prj.entity.Account;
import com.prj.service.AccountService;
import com.prj.util.Page;
import com.prj.util.TokenTool;

@Service("AccountServiceImpl")
public class AccountServiceImpl implements AccountService {

	@Resource(name = "AccountDaoImpl")
	AccountDao dao;

	public List<Account> getAllAccount() {
		return dao.getAllAccount();
	}

	public boolean deleteAccount(Account v) {
		return dao.deleteAccount(v);
	}

	public boolean addAccount(Account v) {
		return dao.addAccount(v);
	}

	public Account updateAccount(Account v) {
		return dao.updateAccount(v);
	}

	public Account getAccountById(int id) {
		return dao.findAccountbyId(id);
	}

	public Page<Account> getAccountbyPage(int pagenumber, int pagesize) {
		return dao.getAccountbyPage(pagenumber, pagesize);
	}

	public Page<Account> searchAccount(int pagenumber, int pagesize,
			String name) {
		//return dao.searchAccount(pagenumber, pagesize, name);
		return null;
	}

	public Page<Account> getByPageWithConditions(int pagenumber,
			int pagesize, List<SimpleExpression> list) {
		return dao.getByPageWithConditions(pagenumber, pagesize, list);
	}

	public Account login(Account account) {
		Account r = dao.getAccountByNumber(account.getAccountNumber());
		if (r != null && r.getIsActive() && r.getAccountPassword().equals(account.getAccountPassword())) {
			r.setLastLoginTime(Calendar.getInstance().getTime());
			r.setLoginToken(TokenTool.generateToken(account));
			return updateAccount(r);
		}
		return null;
	}
}
