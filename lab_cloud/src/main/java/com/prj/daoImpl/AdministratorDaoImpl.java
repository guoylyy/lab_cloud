package com.prj.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.AbstractHibernateDao;
import com.prj.dao.AdministratorDao;
import com.prj.entity.Account.Status;
import com.prj.entity.Account;
import com.prj.entity.Administrator;
import com.prj.util.DataWrapper;
import com.prj.util.Page;
import com.prj.util.PageResult;

@Service("AdministratorDaoImpl")
public class AdministratorDaoImpl extends AbstractHibernateDao<Administrator, Integer>
		implements AdministratorDao {
	// private static Logger logger = Logger.getLogger(AdministratorDaoImpl.class);

	protected AdministratorDaoImpl() {
		super(Administrator.class);
	}

	public Integer addAdministrator(Administrator v) {
		return add(v);
	}

	public Administrator disableAdministratorById(Integer id) {
		Administrator a = findById(id);
		if (a == null)
			return null;
		a.setStatus(Status.INACTIVE);
//		a.setLoginToken(null);
		return a;
	}

	@SuppressWarnings("unchecked")
	public DataWrapper<List<Administrator>> getAllAdministrator() {
		List<Administrator> result = getCurrentSession()
				.createCriteria(Administrator.class)
				.addOrder(Order.asc("number"))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
		DataWrapper<List<Administrator>> ret = new DataWrapper<List<Administrator>>();
		ret.setData(result);
		return ret;
	}

	public Administrator findAdministratorbyId(int id) {
		return findById(id);
	}

	public Administrator getAdministratorByNumber(String number) {
		Criteria criteria = getCurrentSession().createCriteria(Administrator.class);
		criteria.add(Restrictions.eq("number", number));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Administrator) ret.get(0);
		}
		return null;
	}

	public Administrator findAdministratorbyToken(String token) {
		Criteria criteria = getCurrentSession().createCriteria(Administrator.class);
		criteria.add(Restrictions.eq("loginToken", token));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Administrator) ret.get(0);
		}
		return null;
	}

	public Administrator updateAdministrator(Administrator v) {
		return saveOrUpdate(v);
	}

	public DataWrapper<List<Administrator>> getAdministratorbyPage(int pagenumber, int pagesize) {
		PageResult<Administrator> pr = findByCriteriaByPage(null, pagenumber, pagesize);
		DataWrapper<List<Administrator>> ret = new DataWrapper<List<Administrator>>();
		ret.setData(pr.getData());
		ret.setCurrPageNum(pr.getCurrPageNum());
		ret.setNumPerPage(pr.getNumPerPage());
		ret.setTotalItemNum(pr.getTotalItemNum());
		ret.setTotalPageNum(pr.getTotalPageNum());
		return ret;
	}

	public List<Administrator> getByCondition(List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Administrator> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Administrator> getAdministratorByStatus(Account.Status as) {
		// TODO Auto-generated method stub
		Criteria criteria = getCurrentSession().createCriteria(Administrator.class);
		criteria.add(Restrictions.eq("status", as));
		@SuppressWarnings("unchecked")
		List<Administrator> ret = criteria.list();
		return ret;
	}
}
