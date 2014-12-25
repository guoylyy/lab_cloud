package com.prj.service;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Student;
import com.prj.util.DataWrapper;
import com.prj.util.Page;
import com.prj.util.PasswordReset;

public interface StudentService {
	DataWrapper<Student> addStudent(Student student);

	public DataWrapper<Student> disableStudentById(Integer id);

	DataWrapper<List<Student>> getAllStudent();

	DataWrapper<Student> getStudentById(int id);

	DataWrapper<Student> updateStudent(Student entity);

	DataWrapper<Student> reset(PasswordReset data);

	DataWrapper<List<Student>> getStudentbyPage(int pagenumber, int pagesize);

	Page<Student> searchStudent(int pagenumber, int pagesize, String name);

	Page<Student> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

}
