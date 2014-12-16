package com.prj.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.StudentDao;
import com.prj.entity.Account.Status;
import com.prj.entity.Administrator;
import com.prj.entity.Student;
import com.prj.service.StudentService;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;
import com.prj.util.MD5Tool;
import com.prj.util.Page;
import com.prj.util.PasswordReset;

@Service("StudentServiceImpl")
public class StudentServiceImpl implements StudentService {

	@Resource(name = "StudentDaoImpl")
	StudentDao dao;

	public DataWrapper<Student> addStudent(Student student) {
		DataWrapper<Student> ret = new DataWrapper<Student>();
		Student a = dao.getStudentByNumber(student.getNumber());
		student.setPassword(MD5Tool.GetMd5(student.getPassword()));
		if (a != null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Exist);
		} else if (dao.addStudent(student) != null) {
			ret.setData(student);
		} else {
			ret.setErrorCode(ErrorCodeEnum.Unknown_Error);
		}
		return ret;
	}

	public DataWrapper<Student> disableStudentById(Integer id) {
		Student a = dao.disableStudentById(id);
		DataWrapper<Student> ret = new DataWrapper<Student>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<List<Student>> getAllStudent() {
		return dao.getAllStudent();
	}

	public DataWrapper<Student> getStudentById(int id) {
		DataWrapper<Student> ret = new DataWrapper<Student>();
		Student a = dao.findStudentbyId(id);
		ret.setData(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<Student> updateStudent(Student v) {
		Student a = dao.updateStudent(v);
		DataWrapper<Student> ret = new DataWrapper<Student>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<Student> reset(PasswordReset reset) {
		Student a = dao.findStudentbyId(reset.getId());
		DataWrapper<Student> ret = new DataWrapper<Student>();
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		} else if (!a.getPassword().equals(
				MD5Tool.GetMd5(reset.getOldPassword()))) {
			ret.setErrorCode(ErrorCodeEnum.Password_Wrong);
		} else {
			a.setPassword(MD5Tool.GetMd5(reset.getNewPassword()));
			a.setLoginToken(null);
			a.setStatus(Status.ACTIVE);
			dao.updateStudent(a);
		}
		return ret;
	}

	public DataWrapper<List<Student>> getStudentbyPage(int pagenumber, int pagesize) {
		return dao.getStudentbyPage(pagenumber, pagesize);
	}

	// Methods Following Are Not Checked... YET!
	public Page<Student> searchStudent(int pagenumber, int pagesize, String name) {
		// return dao.searchStudent(pagenumber, pagesize, name);
		return null;
	}

	public Page<Student> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list) {
		return dao.getByPageWithConditions(pagenumber, pagesize, list);
	}
}
