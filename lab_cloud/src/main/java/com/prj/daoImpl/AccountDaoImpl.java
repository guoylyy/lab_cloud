package com.prj.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.AbstractHibernateDao;
import com.prj.dao.AccountDao;
import com.prj.entity.Account;
import com.prj.util.Page;

@Service("AccountDaoImpl")
public class AccountDaoImpl extends AbstractHibernateDao<Account, Integer>implements AccountDao {

	protected AccountDaoImpl() {
		super(Account.class);
	}

	public Integer addAccount(Account v) {
		return add(v);
	}

	public boolean deleteAccount(Account v) {
		// TODO Auto-generated method stub
		return false;
	}

	public Account findAccountbyId(int id) {
		return findById(id);
	}

	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	public Account updateAccount(Account v) {
		return saveOrUpdate(v);
	}

	public Page<Account> getAccountbyPage(int pagenumber, int pagesize) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> getByCondition(List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Account> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Autowired
//	protected SessionFactory sessionFactory;

//	@Autowired
//	CommonDao commonDao;

//	private static Logger logger = Logger.getLogger(AccountDaoImpl.class);

//	public boolean addAccount(Account v) {
//		return commonDao.addObject(v);
//	}
//
//	public boolean deleteAccount(Account v) {
//		// TODO Auto-generated method stub
//		return commonDao.deleteObject(v);
//	}
//
//	public Account findAccountbyId(int id) {
//		// TODO Auto-generated method stub
//		Session session = sessionFactory.getCurrentSession();
//		Account v = null;
//		v = (Account) session.get(Account.class, id);
//		return v;
//
//	}
//
//	public List<Account> getAllAccount() {
//		// TODO Auto-generated method stub
//
//		return commonDao.getAllObject("com.prj.entity.Account");
//	}
//
//	public Account updateAccount(Account v) {
//		// TODO Auto-generated method stub
//		return (Account) commonDao.updateObject(v);
//	}
//
//	public Page<Account> getAccountbyPage(int pagenumber, int pagesize) {
//		// TODO Auto-generated method stub
//
//		return commonDao.getObjectbyPage("com.prj.entity.Account",
//				pagenumber, pagesize);
//	}
//
//	public Page<Account> getByPageWithConditions(int pagenumber,
//			int pagesize, List<SimpleExpression> list) {
//		return commonDao.getByPageWithConditions("com.prj.entity.Account",
//				pagenumber, pagesize, list);
//	}
//
//	public List<Account> getByCondition(List<SimpleExpression> list) {
//		return commonDao.getByConditions("com.prj.entity.Account", list);
//	}
//
	public Account getAccountByNumber(String number) {
		Criteria criteria = getCurrentSession().createCriteria(Account.class);
		criteria.add(Restrictions.eq("accountNumber", number));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Account)ret.get(0);
		}
		return null;
	}

	public Account findAccountbyToken(String token) {
		Criteria criteria = getCurrentSession().createCriteria(Account.class);
		criteria.add(Restrictions.eq("loginToken", token));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Account)ret.get(0);
		}
		return null;
	}
}
