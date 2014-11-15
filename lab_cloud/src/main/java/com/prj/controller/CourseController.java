package com.prj.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prj.entity.Course;
import com.prj.service.CourseService;
import com.prj.util.AccountAccess;
import com.prj.util.AccountCharacter;
import com.prj.util.AuthorityException;
import com.prj.util.DataWrapper;

@Controller
@RequestMapping(value = "/Course")
public class CourseController {

	@Resource(name = "CourseServiceImpl")
	CourseService vs;
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Course> add(@RequestBody DataWrapper<Course> course) {
		return vs.addCourse(course.getData());
	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Course> deleteCourse(@RequestBody DataWrapper<?> wrapper, @PathVariable int id) {
		return vs.deleteCourseById(id);
	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<List<Course>> getCourseList(@RequestBody DataWrapper<?> wrapper) {
		return vs.getAllActiveCourse();
	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Course> getCourse(@RequestBody DataWrapper<?> wrapper, @PathVariable int id) {
		return vs.getCourseById(id);
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Course> updateCourse(@RequestBody DataWrapper<Course> course,  @PathVariable int id) {
		return vs.updateCourse(course.getData());
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(AuthorityException.class)
	@ResponseBody
	public DataWrapper handleAuthorityException(AuthorityException ex) {
		System.out.println(ex.getErrorCode().getLabel());
		DataWrapper ret = new DataWrapper();
		ret.setErrorCode(ex.getErrorCode());
		return ret;
	}
}
