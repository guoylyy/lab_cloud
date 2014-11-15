package com.prj.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.AbstractHibernateDao;
import com.prj.dao.ExperimentDao;
import com.prj.entity.Experiment;
import com.prj.util.DataWrapper;
import com.prj.util.Page;

@Service("ExperimentDaoImpl")
public class ExperimentDaoImpl extends AbstractHibernateDao<Experiment, Integer>implements ExperimentDao {

	protected ExperimentDaoImpl() {
		super(Experiment.class);
	}

	public Integer addExperiment(Experiment v) {
		return add(v);
	}

	public Experiment deleteExperimentById(Integer id) {
		Experiment a = findById(id);
		if (a == null)
			return null;
		a.setIsActive(false);
		return a;
	}

	@SuppressWarnings("unchecked")
	public DataWrapper<List<Experiment>> getAllExperiment() {
		List<Experiment> result = getCurrentSession().createCriteria(Experiment.class)
				.addOrder(Order.asc("experimentNumber"))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
		DataWrapper<List<Experiment>> ret = new DataWrapper<List<Experiment>>();
		ret.setData(result);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public DataWrapper<List<Experiment>> getAllActiveExperiment() {
		List<Experiment> result = getCurrentSession().createCriteria(Experiment.class)
				.add(Restrictions.eq("isActive", true))
				.addOrder(Order.asc("experimentNumber"))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
		DataWrapper<List<Experiment>> ret = new DataWrapper<List<Experiment>>();
		ret.setData(result);
		return ret;
	}

	public Experiment findExperimentById(int id) {
		return findById(id);
	}

	public Experiment updateExperiment(Experiment v) {
		return saveOrUpdate(v);
	}

	public Experiment getExperimentByNumber(String number) {
		Criteria criteria = getCurrentSession().createCriteria(Experiment.class);
		criteria.add(Restrictions.eq("experimentNumber", number));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Experiment)ret.get(0);
		}
		return null;
	}

	public Page<Experiment> getExperimentByPage(int pagenumber, int pagesize) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Experiment> getByCondition(List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Experiment> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}
}
