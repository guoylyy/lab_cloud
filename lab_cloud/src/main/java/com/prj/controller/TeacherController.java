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

import com.prj.entity.Teacher;
import com.prj.service.TeacherService;
import com.prj.util.AccountAccess;
import com.prj.util.AccountCharacter;
import com.prj.util.AuthorityException;
import com.prj.util.DataWrapper;
import com.prj.util.PasswordReset;

@Controller
@RequestMapping(value = "/Teacher")
public class TeacherController {
	@Resource(name = "TeacherServiceImpl")
	TeacherService vs;
	
//	@RequestMapping(value = "/table", method = RequestMethod.GET)
//	public String IndexView(Model model) {
//		return "Teacher/table";
//	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.TEACHER)
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Teacher> getTeacher(@RequestBody DataWrapper<?> wrapper) {
		return vs.getTeacherById(wrapper.getAccountId());
	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.TEACHER)
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Teacher> resetPassword(@RequestBody DataWrapper<PasswordReset> wrapper) {
		wrapper.getData().setId(wrapper.getAccountId());
		return vs.reset(wrapper.getData());
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Teacher> add(@RequestBody DataWrapper<Teacher> teacher) {
		return vs.addTeacher(teacher.getData());
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Teacher> getTeacher(@RequestBody DataWrapper<?> wrapper, @PathVariable int id) {
		return vs.getTeacherById(id);
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<List<Teacher>> getTeacherList(@RequestBody DataWrapper<?> wrapper) {
		return vs.getAllTeacher();
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Teacher> deleteTeacher(@PathVariable int id) {
		return vs.disableTeacherById(id);
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Teacher> updateTeacher(@RequestBody DataWrapper<Teacher> teacher,  @PathVariable int id) {
		return vs.updateTeacher(teacher.getData());
	}
	
	@ExceptionHandler(AuthorityException.class)
	@ResponseBody
	public DataWrapper<Void> handleAuthorityException(AuthorityException ex) {
		System.out.println(ex.getErrorCode().getLabel());
		DataWrapper<Void> ret = new DataWrapper<Void>();
		ret.setErrorCode(ex.getErrorCode());
		return ret;
	}
}
