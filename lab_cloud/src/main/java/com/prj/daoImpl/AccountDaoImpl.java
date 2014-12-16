package com.prj.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.AbstractHibernateDao;
import com.prj.dao.AccountDao;
import com.prj.entity.Account;
import com.prj.entity.Account.Status;
import com.prj.entity.Administrator;
import com.prj.entity.Student;
import com.prj.entity.Teacher;
import com.prj.util.AccountCharacter;
import com.prj.util.DataWrapper;
import com.prj.util.Page;

@Service("AccountDaoImpl")
public class AccountDaoImpl extends AbstractHibernateDao<Account, Integer>
		implements AccountDao {
	// private static Logger logger = Logger.getLogger(AccountDaoImpl.class);

	protected AccountDaoImpl() {
		super(Account.class);
	}

	public Integer addAccount(Account v) {
		return add(v);
	}

	public Account disableAccountById(Integer id) {
		Account a = findById(id);
		if (a == null)
			return null;
		a.setStatus(Status.INACTIVE);
//		a.setLoginToken(null);
		return a;
	}

	@SuppressWarnings("unchecked")
	public DataWrapper<List<Account>> getAllAccount() {
		List<Account> result = getCurrentSession()
				.createCriteria(Account.class)
				.addOrder(Order.asc("accountNumber"))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
		DataWrapper<List<Account>> ret = new DataWrapper<List<Account>>();
		ret.setData(result);
		return ret;
	}

	public Account findAccountbyId(int id) {
		return findById(id);
	}

	public Account getAccountByNumber(String number) {
		Criteria criteria = getCurrentSession().createCriteria(Account.class);
		criteria.add(Restrictions.eq("accountNumber", number));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Account) ret.get(0);
		}
		return null;
	}

	public Account findAccountbyToken(String token, AccountCharacter ac) {
		Criteria criteria = null;
		switch(ac) {
		case STUDENT:
			criteria = getCurrentSession().createCriteria(Student.class);
			break;
		case TEACHER:
			criteria = getCurrentSession().createCriteria(Teacher.class);
			break;
		case ADMINISTRATOR:
			criteria = getCurrentSession().createCriteria(Administrator.class);
		default:
			return null;
		}
		criteria.add(Restrictions.eq("loginToken", token));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Account) ret.get(0);
		}
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
}
