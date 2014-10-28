package com.prj.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prj.dao.AccountDao;
import com.prj.entity.Account;
import com.prj.util.Page;

@Service("AccountDaoImpl")
public class AccountDaoImpl implements AccountDao {

	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	CommonDao commonDao;

	private static Logger logger = Logger.getLogger(AccountDaoImpl.class);

	public boolean addAccount(Account v) {
		// TODO Auto-generated method stub
		return commonDao.addObject(v);
	}

	public boolean deleteAccount(Account v) {
		// TODO Auto-generated method stub
		return commonDao.deleteObject(v);
	}

	public Account findAccountbyId(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Account v = null;
		v = (Account) session.get(Account.class, id);
		return v;

	}

	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub

		return commonDao.getAllObject("com.prj.entity.Account");
	}

	public Account updateAccount(Account v) {
		// TODO Auto-generated method stub
		return (Account) commonDao.updateObject(v);
	}

	public Page<Account> getAccountbyPage(int pagenumber, int pagesize) {
		// TODO Auto-generated method stub

		return commonDao.getObjectbyPage("com.prj.entity.Account",
				pagenumber, pagesize);
	}

	public Page<Account> getByPageWithConditions(int pagenumber,
			int pagesize, List<SimpleExpression> list) {
		return commonDao.getByPageWithConditions("com.prj.entity.Account",
				pagenumber, pagesize, list);
	}

	public List<Account> getByCondition(List<SimpleExpression> list) {
		return commonDao.getByConditions("com.prj.entity.Account", list);
	}

}
