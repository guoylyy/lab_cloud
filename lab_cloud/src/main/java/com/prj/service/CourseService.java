package com.prj.service;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Course;
import com.prj.util.DataWrapper;
import com.prj.util.Page;

public interface CourseService {
	DataWrapper<Course> addCourse(Course course);

	public DataWrapper<Course> deleteCourseById(Integer id);

	DataWrapper<List<Course>> getAllCourse();

	DataWrapper<List<Course>> getAllActiveCourse();
	
	DataWrapper<Course> getCourseById(int id);

	DataWrapper<Course> updateCourse(Course entity);

	Page<Course> getCourseByPage(int pagenumber, int pagesize);

	Page<Course> searchCourse(int pagenumber, int pagesize, String name);

	Page<Course> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);
}
