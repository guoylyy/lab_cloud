package com.prj.service;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Administrator;
import com.prj.entity.Teacher;
import com.prj.util.DataWrapper;
import com.prj.util.Page;
import com.prj.util.PasswordReset;

public interface TeacherService {
//	DataWrapper<Teacher> login(Teacher teacher);

//	void logout(Integer id);

	DataWrapper<Teacher> addTeacher(Teacher teacher);

	public DataWrapper<Teacher> disableTeacherById(Integer id);

	DataWrapper<List<Teacher>> getAllTeacher();

	DataWrapper<Teacher> getTeacherById(int id);

	DataWrapper<Teacher> updateTeacher(Teacher entity);

	DataWrapper<Teacher> reset(PasswordReset data);

	DataWrapper<List<Teacher>> getTeacherbyPage(int pagenumber, int pagesize);

	Page<Teacher> searchTeacher(int pagenumber, int pagesize, String name);

	Page<Teacher> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

}
