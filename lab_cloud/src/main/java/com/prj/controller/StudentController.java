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

import com.prj.entity.Student;
import com.prj.service.StudentService;
import com.prj.util.AccountAccess;
import com.prj.util.AccountCharacter;
import com.prj.util.AuthorityException;
import com.prj.util.DataWrapper;
import com.prj.util.PasswordReset;

@Controller
@RequestMapping(value = "/Student")
public class StudentController {
	@Resource(name = "StudentServiceImpl")
	StudentService vs;
	
//	@RequestMapping(value = "/table", method = RequestMethod.GET)
//	public String IndexView(Model model) {
//		return "Student/table";
//	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.STUDENT)
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Student> getStudent(@RequestBody DataWrapper<?> wrapper) {
		return vs.getStudentById(wrapper.getAccountId());
	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.STUDENT)
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Student> resetPassword(@RequestBody DataWrapper<PasswordReset> wrapper) {
		wrapper.getData().setId(wrapper.getAccountId());
		return vs.reset(wrapper.getData());
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Student> add(@RequestBody DataWrapper<Student> student) {
		return vs.addStudent(student.getData());
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Student> getStudent(@RequestBody DataWrapper<?> wrapper, @PathVariable int id) {
		return vs.getStudentById(id);
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<List<Student>> getStudentList(@RequestBody DataWrapper<?> wrapper) {
		return vs.getAllStudent();
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Student> deleteStudent(@PathVariable int id) {
		return vs.disableStudentById(id);
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Student> updateStudent(@RequestBody DataWrapper<Student> student,  @PathVariable int id) {
		return vs.updateStudent(student.getData());
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
