package com.prj.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.AdministratorDao;
import com.prj.entity.Account.Status;
import com.prj.entity.Administrator;
import com.prj.service.AdministratorService;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;
import com.prj.util.MD5Tool;
import com.prj.util.Page;
import com.prj.util.PasswordReset;

@Service("AdministratorServiceImpl")
public class AdministratorServiceImpl implements AdministratorService {

	@Resource(name = "AdministratorDaoImpl")
	AdministratorDao dao;

	public DataWrapper<Administrator> addAdministrator(Administrator administrator) {
		DataWrapper<Administrator> ret = new DataWrapper<Administrator>();
		Administrator a = dao.getAdministratorByNumber(administrator.getNumber());
		administrator.setPassword(MD5Tool.GetMd5(administrator.getPassword()));
		if (a != null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Exist);
		} else if (dao.addAdministrator(administrator) != null) {
			ret.setData(administrator);
		} else {
			ret.setErrorCode(ErrorCodeEnum.Unknown_Error);
		}
		return ret;
	}

	public DataWrapper<Administrator> disableAdministratorById(Integer id) {
		Administrator a = dao.disableAdministratorById(id);
		DataWrapper<Administrator> ret = new DataWrapper<Administrator>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<List<Administrator>> getAllAdministrator() {
		return dao.getAllAdministrator();
	}

	public DataWrapper<Administrator> getAdministratorById(int id) {
		DataWrapper<Administrator> ret = new DataWrapper<Administrator>();
		Administrator a = dao.findAdministratorbyId(id);
		ret.setData(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<Administrator> updateAdministrator(Administrator v) {
		Administrator a = dao.updateAdministrator(v);
		DataWrapper<Administrator> ret = new DataWrapper<Administrator>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<Administrator> reset(PasswordReset reset) {
		Administrator a = dao.findAdministratorbyId(reset.getId());
		DataWrapper<Administrator> ret = new DataWrapper<Administrator>();
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		} else if (!a.getPassword().equals(
				MD5Tool.GetMd5(reset.getOldPassword()))) {
			ret.setErrorCode(ErrorCodeEnum.Password_Wrong);
		} else {
			a.setPassword(MD5Tool.GetMd5(reset.getNewPassword()));
			a.setLoginToken(null);
			a.setStatus(Status.ACTIVE);
			dao.updateAdministrator(a);
		}
		return ret;
	}

	// Methods Following Are Not Checked... YET!
	public Page<Administrator> getAdministratorbyPage(int pagenumber, int pagesize) {
		return dao.getAdministratorbyPage(pagenumber, pagesize);
	}

	public Page<Administrator> searchAdministrator(int pagenumber, int pagesize, String name) {
		// return dao.searchAdministrator(pagenumber, pagesize, name);
		return null;
	}

	public Page<Administrator> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list) {
		return dao.getByPageWithConditions(pagenumber, pagesize, list);
	}
}
