package com.prj.serviceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.CourseDao;
import com.prj.dao.ExperimentDao;
import com.prj.entity.Course;
import com.prj.entity.CourseExperiment;
import com.prj.entity.Experiment;
import com.prj.service.CourseService;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;
import com.prj.util.Page;

@Service("CourseServiceImpl")
public class CourseServiceImpl implements CourseService {

	@Resource(name = "CourseDaoImpl")
	CourseDao dao;
	@Resource(name = "ExperimentDaoImpl")
	ExperimentDao experimentdao;

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

	@Override
	public DataWrapper<Course> addExperiment(int courseid, int experimentid,
			int seq) {
		// TODO Auto-generated method stub
		DataWrapper<Course> ret = new DataWrapper<Course>();
		Experiment experiment = experimentdao.findExperimentById(experimentid);
		if(experiment == null)
		{
			ret.setErrorCode(ErrorCodeEnum.Experiment_Not_Exist);
			return ret;
		}
		Course course = dao.findCourseById(courseid);
		if(course == null)
		{
			ret.setErrorCode(ErrorCodeEnum.Course_Not_Exist);
			return ret;
		}
		Set<CourseExperiment> ces = course.getCourseExperiment();
		Iterator<CourseExperiment> it = ces.iterator();
		while(it.hasNext())
		{
			CourseExperiment ce = it.next();
			if(ce.getSequence()>=seq)
			{
				dao.updateExperimentSequence(courseid, ce.getExperiment().getId(), ce.getSequence()+1);
			}
		}
		
		if(!dao.addExperiment(courseid, experiment, seq))
			ret.setErrorCode(ErrorCodeEnum.Course_Not_Exist);
		ret.setData(course);
		return ret;
	}

	@Override
	public DataWrapper<List<Experiment>> getExperimentsOfCourse(int courseid) {
		// TODO Auto-generated method stub
		DataWrapper<List<Experiment>> ret = new DataWrapper<List<Experiment>>(dao.getExperimentsOfCourse(courseid));
		return ret;
	}
}
