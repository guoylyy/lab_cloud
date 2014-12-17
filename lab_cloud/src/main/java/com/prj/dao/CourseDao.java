package com.prj.dao;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Course;
import com.prj.util.DataWrapper;
import com.prj.util.Page;

public interface CourseDao {
	Integer addCourse(Course v);

	Course deleteCourseById(Integer id);

	Course findCourseById(int id);

	DataWrapper<List<Course>> getAllCourse();

	DataWrapper<List<Course>> getAllActiveCourse();

	Course updateCourse(Course v);

	Course getCourseByNumber(String number);

	Page<Course> getCourseByPage(int pagenumber, int pagesize);

	List<Course> getByCondition(List<SimpleExpression> list);

	Page<Course> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);
	
	
}
