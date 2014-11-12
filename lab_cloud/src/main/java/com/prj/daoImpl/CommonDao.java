package com.prj.daoImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prj.entity.BaseEntity;
import com.prj.util.Page;

@Service("commonDao")
public class CommonDao {

	@Autowired
	protected SessionFactory sessionFactory;

	private static Logger logger = Logger.getLogger(CommonDao.class);

	public boolean addObject(Object v) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		logger.info("add Object");
		BaseEntity be = (BaseEntity) v;
		if (be instanceof BaseEntity) {
			be.setCreate_time(new Date());
			be.setModify_time(new Date());
		}
		try {
			session.beginTransaction();
			session.save(v);
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			session.getTransaction().rollback();
			return false;
		}
		return true;
	}

	public List getAllObject(String classname) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(classname);
		Disjunction re = Restrictions.or();
		crit.add(re);
		return crit.list();
	}

	public Object updateObject(Object v) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		BaseEntity be = (BaseEntity) v;
		BaseEntity o = (BaseEntity) session2.get(be.getClass().getName(),
				be.getId());
		if (be instanceof BaseEntity) {
			be.setModify_time(new Date());
			be.setCreate_time(o.getCreate_time());
		}
		session2.close();
		try {
			session.beginTransaction();
			session.update(v);
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			session.getTransaction().rollback();
			return null;
		}
		return v;
	}

	public Page getObjectbyPage(String classname, int pagenumber, int pagesize) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(classname);
		Page page = new Page();
		// page.total = ((Integer) crit.setProjection(Projections.rowCount())
		// .uniqueResult()).intValue();
		page.total = Integer.parseInt(crit
				.setProjection(Projections.rowCount()).uniqueResult()
				.toString());

		crit.setProjection(null);
		crit.setFirstResult((pagenumber - 1) * pagesize);
		crit.setMaxResults(pagesize);
		Disjunction re = Restrictions.or();
		crit.add(re);
		page.content = crit.list();
		page.pagenumber = pagenumber;
		return page;
	}

	public Page getByPageWithConditions(String classname, int pagenumber,
			int pagesize, List<SimpleExpression> list) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(classname);
		Page page = new Page();
		for (SimpleExpression se : list) {
			crit.add(se);
		}
		page.total = Integer.parseInt(crit
				.setProjection(Projections.rowCount()).uniqueResult()
				.toString());

		crit.setProjection(null);
		crit.setFirstResult((pagenumber - 1) * pagesize);
		crit.setMaxResults(pagesize);
		page.content = crit.list();
		page.pagenumber = pagenumber;

		return page;
	}

	public List getByConditions(String classname, List<SimpleExpression> list){
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(classname);
		for (SimpleExpression se : list) {
			crit.add(se);
		}
		return crit.list();
	}
	
	public List<Map> getListBySql(String sql) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		List list = query.list();
		return list;
	}

	public boolean deleteObject(BaseEntity o) {
		Session session = sessionFactory.openSession();
		o = (BaseEntity) session.get(o.getClass().getName(), o.getId());

		try {
			session.beginTransaction();
			session.delete(o);
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			session.getTransaction().rollback();
			return false;
		}
		return true;

	}

}