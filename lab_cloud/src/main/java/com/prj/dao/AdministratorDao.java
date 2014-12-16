package com.prj.dao;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Administrator;
import com.prj.util.DataWrapper;
import com.prj.util.Page;

public interface AdministratorDao {
	Integer addAdministrator(Administrator v);

	Administrator disableAdministratorById(Integer id);

	Administrator findAdministratorbyId(int id);

	DataWrapper<List<Administrator>> getAllAdministrator();

	Administrator updateAdministrator(Administrator v);

	DataWrapper<List<Administrator>> getAdministratorbyPage(int pagenumber, int pagesize);

	List<Administrator> getByCondition(List<SimpleExpression> list);

	Page<Administrator> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

	Administrator getAdministratorByNumber(String number);

	Administrator findAdministratorbyToken(String token);

}
