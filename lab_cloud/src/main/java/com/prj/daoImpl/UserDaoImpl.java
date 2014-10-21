package com.prj.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prj.dao.UserDao;
import com.prj.entity.User;
import com.prj.util.Page;

@Service("UserDaoImpl")
public class UserDaoImpl implements UserDao {

	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	CommonDao commonDao;

	private static Logger logger = Logger.getLogger(UserDaoImpl.class);

	public boolean addUser(User v) {
		// TODO Auto-generated method stub
		return commonDao.addObject(v);
	}

	public boolean deleteUser(User v) {
		// TODO Auto-generated method stub
		return commonDao.deleteObject(v);
	}

	public User findUserbyId(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		User v = null;
		v = (User) session.get(User.class, id);
		return v;

	}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub

		return commonDao.getAllObject("com.prj.entity.User");
	}

	public User updateUser(User v) {
		// TODO Auto-generated method stub
		return (User) commonDao.updateObject(v);
	}

	public Page<User> getUserbyPage(int pagenumber, int pagesize) {
		// TODO Auto-generated method stub

		return commonDao.getObjectbyPage("com.prj.entity.User",
				pagenumber, pagesize);
	}

	public Page<User> getByPageWithConditions(int pagenumber,
			int pagesize, List<SimpleExpression> list) {
		return commonDao.getByPageWithConditions("com.prj.entity.User",
				pagenumber, pagesize, list);
	}

	public List<User> getByCondition(List<SimpleExpression> list) {
		return commonDao.getByConditions("com.prj.entity.User", list);
	}

}
