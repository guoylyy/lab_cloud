package com.prj.service;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Administrator;
import com.prj.util.DataWrapper;
import com.prj.util.Page;
import com.prj.util.PasswordReset;

public interface AdministratorService {
//	DataWrapper<Administrator> login(Administrator administrator);

//	void logout(Integer id);

	DataWrapper<Administrator> addAdministrator(Administrator administrator);

	public DataWrapper<Administrator> disableAdministratorById(Integer id);

	DataWrapper<List<Administrator>> getAllAdministrator();

	DataWrapper<Administrator> getAdministratorById(int id);

	DataWrapper<Administrator> updateAdministrator(Administrator entity);

	DataWrapper<Administrator> reset(PasswordReset data);

	DataWrapper<List<Administrator>> getAdministratorbyPage(int pagenumber, int pagesize);

	Page<Administrator> searchAdministrator(int pagenumber, int pagesize, String name);

	Page<Administrator> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

}
