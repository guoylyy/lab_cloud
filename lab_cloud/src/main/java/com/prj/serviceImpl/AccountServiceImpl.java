package com.prj.serviceImpl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.AccountDao;
import com.prj.entity.Account;
import com.prj.service.AccountService;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;
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

	public DataWrapper<Account> getAccountById(int id) {
		DataWrapper<Account> ret = new DataWrapper<Account>();
		Account a = dao.findAccountbyId(id);
		ret.setData(a);
		return ret;
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

	public DataWrapper<Account> login(Account account) {
		DataWrapper<Account> ret = new DataWrapper<Account>();
		Account a = dao.getAccountByNumber(account.getAccountNumber());
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		} else if (!a.getIsActive()) {
			ret.setErrorCode(ErrorCodeEnum.Employ_Not_Active);
		} else if (!a.getAccountPassword().equals(account.getAccountPassword())) {
			ret.setErrorCode(ErrorCodeEnum.Password_Wrong);
		} else {
			a.setLastLoginTime(Calendar.getInstance().getTime());
			a.setLoginToken(TokenTool.generateToken(a));
			ret.setToken(a.getLoginToken());
			ret.setData(updateAccount(a));
		}
		return ret;
	}
	
	public DataWrapper<Account> register(Account account) {
		DataWrapper<Account> ret = new DataWrapper<Account>();
		Account a = dao.getAccountByNumber(account.getAccountNumber());
		if (a == null) {
			if (dao.addAccount(account)) {
				ret.setData(account);
			} else {
				ret.setErrorCode(ErrorCodeEnum.Unknown_Error);
			}
		} else {
			ret.setErrorCode(ErrorCodeEnum.Account_Exist);
		}
		return ret;
	}
}
