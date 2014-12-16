package com.prj.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.AbstractHibernateDao;
import com.prj.dao.LabDao;
import com.prj.entity.Lab;
import com.prj.entity.Variable;
import com.prj.util.DataWrapper;
import com.prj.util.Page;

@Service("LabDaoImpl")
public class LabDaoImpl extends AbstractHibernateDao<Lab, Integer>implements LabDao {

	protected LabDaoImpl() {
		super(Lab.class);
	}

	public Integer addLab(Lab v) {
		return add(v);
	}

	public Lab deleteLabById(Integer id) {
		Lab a = findById(id);
		if (a == null)
			return null;
		a.setIsActive(false);
		return a;
	}

	@SuppressWarnings("unchecked")
	public DataWrapper<List<Lab>> getAllLab() {
		List<Lab> result = getCurrentSession().createCriteria(Lab.class)
				.addOrder(Order.asc("labNumber"))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
		DataWrapper<List<Lab>> ret = new DataWrapper<List<Lab>>();
		ret.setData(result);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public DataWrapper<List<Lab>> getAllActiveLab() {
		List<Lab> result = getCurrentSession().createCriteria(Lab.class)
				.add(Restrictions.eq("isActive", true))
				.addOrder(Order.asc("labNumber"))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
		DataWrapper<List<Lab>> ret = new DataWrapper<List<Lab>>();
		ret.setData(result);
		return ret;
	}

	public Lab findLabById(int id) {
		return findById(id);
	}

	public Lab updateLab(Lab v) {
		return saveOrUpdate(v);
	}

	public Lab getLabByNumber(String labNumber) {
		Criteria criteria = getCurrentSession().createCriteria(Lab.class);
		criteria.add(Restrictions.eq("labNumber", labNumber));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Lab)ret.get(0);
		}
		return null;
	}

	@Override
	public Lab getActiveLabByNumber(String labNumber) {
		List<?> ret = getCurrentSession().createCriteria(Lab.class)
				.add(Restrictions.eq("labNumber", labNumber))
				.add(Restrictions.eq("isActive", true))
				.list();
		if (ret != null && ret.size() > 0) {
			return (Lab)ret.get(0);
		}
		return null;
	}

	@Override
	public boolean isFull() {
		return getCurrentSession().createCriteria(Lab.class)
				.add(Restrictions.eq("isActive", true))
				.list()
				.size() >= Variable.maxLabNumber;
	}
	
	public Page<Lab> getLabByPage(int pagenumber, int pagesize) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Lab> getByCondition(List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Lab> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}
}
