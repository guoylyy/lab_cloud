package com.prj.serviceImpl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.AccountDao;
import com.prj.entity.Account;
import com.prj.service.AccountService;
import com.prj.util.AccountCharacter;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;
import com.prj.util.MD5Tool;
import com.prj.util.Page;
import com.prj.util.PasswordReset;
import com.prj.util.TokenTool;

@Service("AccountServiceImpl")
public class AccountServiceImpl implements AccountService {

	@Resource(name = "AccountDaoImpl")
	AccountDao dao;

	public DataWrapper<Account> login(Account account) {
		DataWrapper<Account> ret = new DataWrapper<Account>();
		Account a = dao.getAccountByNumber(account.getAccountNumber());
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		} else if (!a.getIsActive()) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Active);
		} else if (!a.getAccountPassword().equals(
				MD5Tool.GetMd5(account.getAccountPassword()))) {
			// System.out.println("POST:" +
			// MD5Tool.GetMd5(account.getAccountPassword()) + "\nDB  :" +
			// a.getAccountPassword());
			ret.setErrorCode(ErrorCodeEnum.Password_Wrong);
		} else {
			a.setLastLoginTime(Calendar.getInstance().getTime());
			a.setLoginToken(TokenTool.generateToken(a));
			ret.setToken(a.getLoginToken());
			ret.setData(dao.updateAccount(a));
		}
		return ret;
	}

	public void logout(Integer id) {
		Account a = dao.findAccountbyId(id);
		a.setLoginToken(null);
		dao.updateAccount(a);
	}

	public DataWrapper<Account> addAccount(Account account) {
		DataWrapper<Account> ret = new DataWrapper<Account>();
		Account a = dao.getAccountByNumber(account.getAccountNumber());
		account.setAccountPassword(MD5Tool.GetMd5(account.getAccountPassword()));
		if (a != null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Exist);
		} else if (dao.addAccount(account) != null) {
			ret.setData(account);
		} else {
			ret.setErrorCode(ErrorCodeEnum.Unknown_Error);
		}
		return ret;
	}

	public DataWrapper<Account> disableAccountById(Integer id) {
		Account a = dao.disableAccountById(id);
		DataWrapper<Account> ret = new DataWrapper<Account>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<List<Account>> getAllAccount() {
		return dao.getAllAccount();
	}

	public DataWrapper<Account> getAccountById(int id) {
		DataWrapper<Account> ret = new DataWrapper<Account>();
		Account a = dao.findAccountbyId(id);
		ret.setData(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<Account> updateAccount(Account v) {
		Account a = dao.updateAccount(v);
		DataWrapper<Account> ret = new DataWrapper<Account>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<Account> updateAccountCharacter(Integer accountId,
			AccountCharacter accountCharacter) {
		Account a = dao.findAccountbyId(accountId);
		DataWrapper<Account> ret = new DataWrapper<Account>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		} else {
			a.setAccountCharacter(accountCharacter);
			dao.updateAccount(a);
		}
		return ret;
	}

	public DataWrapper<Account> reset(PasswordReset reset) {
		Account a = dao.findAccountbyId(reset.getId());
		DataWrapper<Account> ret = new DataWrapper<Account>();
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		} else if (!a.getAccountPassword().equals(
				MD5Tool.GetMd5(reset.getOldPassword()))) {
			ret.setErrorCode(ErrorCodeEnum.Password_Wrong);
		} else {
			a.setAccountPassword(MD5Tool.GetMd5(reset.getNewPassword()));
			a.setLoginToken(null);
			dao.updateAccount(a);
		}
		return ret;
	}

	// Methods Following Are Not Checked... YET!
	public Page<Account> getAccountbyPage(int pagenumber, int pagesize) {
		return dao.getAccountbyPage(pagenumber, pagesize);
	}

	public Page<Account> searchAccount(int pagenumber, int pagesize, String name) {
		// return dao.searchAccount(pagenumber, pagesize, name);
		return null;
	}

	public Page<Account> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list) {
		return dao.getByPageWithConditions(pagenumber, pagesize, list);
	}
}
