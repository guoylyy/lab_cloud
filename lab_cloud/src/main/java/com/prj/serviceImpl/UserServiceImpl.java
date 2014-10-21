package com.prj.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.UserDao;
import com.prj.entity.User;
import com.prj.service.UserService;
import com.prj.util.Page;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

	@Resource(name = "UserDaoImpl")
	UserDao dao;


	public List<User> getAllUser() {
		return dao.getAllUser();
	}

	public boolean deleteUser(User v) {
		return dao.deleteUser(v);
	}

	public boolean addUser(User v) {
		return dao.addUser(v);
	}

	public User updateUser(User v) {
		return dao.updateUser(v);
	}

	public User getUserById(int id) {
		return dao.findUserbyId(id);
	}

	public Page<User> getUserbyPage(int pagenumber, int pagesize) {
		return dao.getUserbyPage(pagenumber, pagesize);
	}

	public Page<User> searchUser(int pagenumber, int pagesize,
			String name) {
		//return dao.searchUser(pagenumber, pagesize, name);
		return null;
	}

	public Page<User> getByPageWithConditions(int pagenumber,
			int pagesize, List<SimpleExpression> list) {
		return dao.getByPageWithConditions(pagenumber, pagesize, list);
	}

}
