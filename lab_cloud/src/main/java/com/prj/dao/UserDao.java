package com.prj.dao;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;
import com.prj.entity.User;
import com.prj.util.Page;

public interface UserDao {
	boolean addUser(User v);

	boolean deleteUser(User v);

	User findUserbyId(int id);

	List<User> getAllUser();

	User updateUser(User v);

	Page<User> getUserbyPage(int pagenumber, int pagesize);

	List<User> getByCondition(List<SimpleExpression> list);

	Page<User> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

}
