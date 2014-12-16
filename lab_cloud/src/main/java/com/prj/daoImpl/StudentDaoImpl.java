package com.prj.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.AbstractHibernateDao;
import com.prj.dao.StudentDao;
import com.prj.entity.Account.Status;
import com.prj.entity.Student;
import com.prj.util.DataWrapper;
import com.prj.util.Page;

@Service("StudentDaoImpl")
public class StudentDaoImpl extends AbstractHibernateDao<Student, Integer>
		implements StudentDao {
	// private static Logger logger = Logger.getLogger(StudentDaoImpl.class);

	protected StudentDaoImpl() {
		super(Student.class);
	}

	public Integer addStudent(Student v) {
		return add(v);
	}

	public Student disableStudentById(Integer id) {
		Student a = findById(id);
		if (a == null)
			return null;
		a.setStatus(Status.INACTIVE);
//		a.setLoginToken(null);
		return a;
	}

	@SuppressWarnings("unchecked")
	public DataWrapper<List<Student>> getAllStudent() {
		List<Student> result = getCurrentSession()
				.createCriteria(Student.class)
				.addOrder(Order.asc("number"))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
		DataWrapper<List<Student>> ret = new DataWrapper<List<Student>>();
		ret.setData(result);
		return ret;
	}

	public Student findStudentbyId(int id) {
		return findById(id);
	}

	public Student getStudentByNumber(String number) {
		Criteria criteria = getCurrentSession().createCriteria(Student.class);
		criteria.add(Restrictions.eq("number", number));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Student) ret.get(0);
		}
		return null;
	}

	public Student findStudentbyToken(String token) {
		Criteria criteria = getCurrentSession().createCriteria(Student.class);
		criteria.add(Restrictions.eq("loginToken", token));
		List<?> ret = criteria.list();
		if (ret != null && ret.size() > 0) {
			return (Student) ret.get(0);
		}
		return null;
	}

	public Student updateStudent(Student v) {
		return saveOrUpdate(v);
	}

	public Page<Student> getStudentbyPage(int pagenumber, int pagesize) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Student> getByCondition(List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Student> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list) {
		// TODO Auto-generated method stub
		return null;
	}
}
