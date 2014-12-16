package com.prj.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.TeacherDao;
import com.prj.entity.Account.Status;
import com.prj.entity.Teacher;
import com.prj.service.TeacherService;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;
import com.prj.util.MD5Tool;
import com.prj.util.Page;
import com.prj.util.PasswordReset;

@Service("TeacherServiceImpl")
public class TeacherServiceImpl implements TeacherService {

	@Resource(name = "TeacherDaoImpl")
	TeacherDao dao;

	public DataWrapper<Teacher> addTeacher(Teacher teacher) {
		DataWrapper<Teacher> ret = new DataWrapper<Teacher>();
		Teacher a = dao.getTeacherByNumber(teacher.getNumber());
		teacher.setPassword(MD5Tool.GetMd5(teacher.getPassword()));
		if (a != null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Exist);
		} else if (dao.addTeacher(teacher) != null) {
			ret.setData(teacher);
		} else {
			ret.setErrorCode(ErrorCodeEnum.Unknown_Error);
		}
		return ret;
	}

	public DataWrapper<Teacher> disableTeacherById(Integer id) {
		Teacher a = dao.disableTeacherById(id);
		DataWrapper<Teacher> ret = new DataWrapper<Teacher>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<List<Teacher>> getAllTeacher() {
		return dao.getAllTeacher();
	}

	public DataWrapper<Teacher> getTeacherById(int id) {
		DataWrapper<Teacher> ret = new DataWrapper<Teacher>();
		Teacher a = dao.findTeacherbyId(id);
		ret.setData(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<Teacher> updateTeacher(Teacher v) {
		Teacher a = dao.updateTeacher(v);
		DataWrapper<Teacher> ret = new DataWrapper<Teacher>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<Teacher> reset(PasswordReset reset) {
		Teacher a = dao.findTeacherbyId(reset.getId());
		DataWrapper<Teacher> ret = new DataWrapper<Teacher>();
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		} else if (!a.getPassword().equals(
				MD5Tool.GetMd5(reset.getOldPassword()))) {
			ret.setErrorCode(ErrorCodeEnum.Password_Wrong);
		} else {
			a.setPassword(MD5Tool.GetMd5(reset.getNewPassword()));
			a.setLoginToken(null);
			a.setStatus(Status.ACTIVE);
			dao.updateTeacher(a);
		}
		return ret;
	}

	// Methods Following Are Not Checked... YET!
	public Page<Teacher> getTeacherbyPage(int pagenumber, int pagesize) {
		return dao.getTeacherbyPage(pagenumber, pagesize);
	}

	public Page<Teacher> searchTeacher(int pagenumber, int pagesize, String name) {
		// return dao.searchTeacher(pagenumber, pagesize, name);
		return null;
	}

	public Page<Teacher> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list) {
		return dao.getByPageWithConditions(pagenumber, pagesize, list);
	}
}
