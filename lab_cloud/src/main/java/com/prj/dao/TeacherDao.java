package com.prj.dao;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Teacher;
import com.prj.util.DataWrapper;
import com.prj.util.Page;

public interface TeacherDao {
	Integer addTeacher(Teacher v);

	Teacher disableTeacherById(Integer id);

	Teacher findTeacherbyId(int id);

	DataWrapper<List<Teacher>> getAllTeacher();

	Teacher updateTeacher(Teacher v);

	Page<Teacher> getTeacherbyPage(int pagenumber, int pagesize);

	List<Teacher> getByCondition(List<SimpleExpression> list);

	Page<Teacher> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

	Teacher getTeacherByNumber(String number);

	Teacher findTeacherbyToken(String token);

}
