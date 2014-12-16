package com.prj.dao;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Student;
import com.prj.util.DataWrapper;
import com.prj.util.Page;

public interface StudentDao {
	Integer addStudent(Student v);

	Student disableStudentById(Integer id);

	Student findStudentbyId(int id);

	DataWrapper<List<Student>> getAllStudent();

	Student updateStudent(Student v);

	Page<Student> getStudentbyPage(int pagenumber, int pagesize);

	List<Student> getByCondition(List<SimpleExpression> list);

	Page<Student> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);

	Student getStudentByNumber(String number);

	Student findStudentbyToken(String token);

}
