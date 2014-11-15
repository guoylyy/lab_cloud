package com.prj.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.CourseDao;
import com.prj.entity.Course;
import com.prj.service.CourseService;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;
import com.prj.util.Page;

@Service("CourseServiceImpl")
public class CourseServiceImpl implements CourseService {

	@Resource(name = "CourseDaoImpl")
	CourseDao dao;

	public DataWrapper<Course> addCourse(Course course) {
		DataWrapper<Course> ret = new DataWrapper<Course>();
		Course a = dao.getCourseByNumber(course.getCourseNumber());
		if (a != null) {
			ret.setErrorCode(ErrorCodeEnum.Course_Exist);
		} else if (dao.addCourse(course) != null) {
			ret.setData(course);
		} else {
			ret.setErrorCode(ErrorCodeEnum.Unknown_Error);
		}
		return ret;
	}

	public DataWrapper<Course> deleteCourseById(Integer id) {
		Course a = dao.deleteCourseById(id);
		DataWrapper<Course> ret = new DataWrapper<Course>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Course_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<List<Course>> getAllCourse() {
		return dao.getAllCourse();
	}

	public DataWrapper<List<Course>> getAllActiveCourse() {
		return dao.getAllActiveCourse();
	}

	public DataWrapper<Course> getCourseById(int id) {
		DataWrapper<Course> ret = new DataWrapper<Course>();
		Course a = dao.findCourseById(id);
		ret.setData(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Course_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<Course> updateCourse(Course v) {
		Course a = dao.updateCourse(v);
		DataWrapper<Course> ret = new DataWrapper<Course>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Course_Not_Exist);
		}
		return ret;
	}

	// Methods Following Are Not Checked... YET!
	public Page<Course> getCourseByPage(int pagenumber, int pagesize) {
		return dao.getCourseByPage(pagenumber, pagesize);
	}

	public Page<Course> searchCourse(int pagenumber, int pagesize,
			String name) {
		//return dao.searchCourse(pagenumber, pagesize, name);
		return null;
	}

	public Page<Course> getByPageWithConditions(int pagenumber,
			int pagesize, List<SimpleExpression> list) {
		return dao.getByPageWithConditions(pagenumber, pagesize, list);
	}
}
