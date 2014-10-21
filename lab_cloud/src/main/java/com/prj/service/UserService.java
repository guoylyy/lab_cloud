package com.prj.service;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.User;
import com.prj.util.Page;

public interface UserService {
	List<User> getAllUser();

	boolean deleteUser(User entity);

	boolean addUser(User entity);

	User updateUser(User entity);

	User getUserById(int id);

	Page<User> getUserbyPage(int pagenumber, int pagesize);

	Page<User> searchUser(int pagenumber, int pagesize, String name);

	Page<User> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

}
