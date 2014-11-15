package com.prj.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.AbstractHibernateDao;
import com.prj.dao.CourseDao;
import com.prj.entity.Course;
import com.prj.util.DataWrapper;
import com.prj.util.Page;

@Service("CourseDaoImpl")
public class CourseDaoImpl extends AbstractHibernateDao<Course, Integer>implements CourseDao {

	protected CourseDaoImpl() {
		super(Course.class);
	}

	public Integer addCourse(Course v) {
		return add(v);
	}

	public Course deleteCourseById(Integer id) {
		Course a = findById(id);
		if (a == null)
			return null;
		a.setIsActive(false);
		return a;
	}

	@SuppressWarnings("unchecked")
	public DataWrapper<List<Course>> getAllCourse() {
		List<Course> result = getCurrentSession().createCriteria(Course.class)
				.addOrder(Order.asc("courseNumber"))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
		DataWrapper<List<Course>> ret = new DataWrapper<List<Course>>();
		ret.setData(result);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public DataWrapper<List<Course>> getAllActiveCourse() {
		List<Course> result = getCurrentSession().createCriteria(Course.class)
				.add(Restrictions.eq("isActive", true))
				.addOrder(Order.asc("courseNumber"))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
		DataWrapper<List<Course>> ret = new DataWrapper<List<Course>>();
		ret.setData(result);
		return ret;
	}

	public Course findCourseById(int id) {
		return findById(id);
	}

	public Course updateCourse(Course v) {
		return saveOrUpdate(v);
	}

	public Course getCourseByNumber(String number) {
		Criteria criteria = getCurrentSession().createCriteria(Course.class);
		criteria.add(Restrictions.eq("courseNumber", number));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Course)ret.get(0);
		}
		return null;
	}

	public Page<Course> getCourseByPage(int pagenumber, int pagesize) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Course> getByCondition(List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Course> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}
}
