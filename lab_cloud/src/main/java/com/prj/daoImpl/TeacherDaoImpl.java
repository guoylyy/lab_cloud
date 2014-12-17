package com.prj.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.AbstractHibernateDao;
import com.prj.dao.TeacherDao;
import com.prj.entity.Account.Status;
import com.prj.entity.Teacher;
import com.prj.util.DataWrapper;
import com.prj.util.Page;
import com.prj.util.PageResult;

@Service("TeacherDaoImpl")
public class TeacherDaoImpl extends AbstractHibernateDao<Teacher, Integer>
		implements TeacherDao {
	// private static Logger logger = Logger.getLogger(TeacherDaoImpl.class);

	protected TeacherDaoImpl() {
		super(Teacher.class);
	}

	public Integer addTeacher(Teacher v) {
		return add(v);
	}

	public Teacher disableTeacherById(Integer id) {
		Teacher a = findById(id);
		if (a == null)
			return null;
		a.setStatus(Status.INACTIVE);
//		a.setLoginToken(null);
		return a;
	}

	@SuppressWarnings("unchecked")
	public DataWrapper<List<Teacher>> getAllTeacher() {
		List<Teacher> result = getCurrentSession()
				.createCriteria(Teacher.class)
				.addOrder(Order.asc("number"))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
		DataWrapper<List<Teacher>> ret = new DataWrapper<List<Teacher>>();
		ret.setData(result);
		return ret;
	}

	public Teacher findTeacherbyId(int id) {
		return findById(id);
	}

	public Teacher getTeacherByNumber(String number) {
		Criteria criteria = getCurrentSession().createCriteria(Teacher.class);
		criteria.add(Restrictions.eq("number", number));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Teacher) ret.get(0);
		}
		return null;
	}

	public Teacher findTeacherbyToken(String token) {
		Criteria criteria = getCurrentSession().createCriteria(Teacher.class);
		criteria.add(Restrictions.eq("loginToken", token));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Teacher) ret.get(0);
		}
		return null;
	}

	public Teacher updateTeacher(Teacher v) {
		return saveOrUpdate(v);
	}

	public DataWrapper<List<Teacher>> getTeacherbyPage(int pagenumber, int pagesize) {
		PageResult<Teacher> pr = findByCriteriaByPage(null, pagenumber, pagesize);
		DataWrapper<List<Teacher>> ret = new DataWrapper<List<Teacher>>();
		ret.setData(pr.getData());
		ret.setCurrPageNum(pr.getCurrPageNum());
		ret.setNumPerPage(pr.getNumPerPage());
		ret.setTotalItemNum(pr.getTotalItemNum());
		ret.setTotalPageNum(pr.getTotalPageNum());
		return ret;
	}

	public List<Teacher> getByCondition(List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Teacher> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> getTeacherByStatus(Status as) {
		// TODO Auto-generated method stub
		Criteria criteria = getCurrentSession().createCriteria(Teacher.class);
		criteria.add(Restrictions.eq("status", as));
		@SuppressWarnings("unchecked")
		List<Teacher> ret = criteria.list();
		return ret;
	}
}
