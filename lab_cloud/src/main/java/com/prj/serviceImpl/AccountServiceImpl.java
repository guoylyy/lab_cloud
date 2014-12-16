package com.prj.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.AccountDao;
import com.prj.dao.AdministratorDao;
import com.prj.dao.StudentDao;
import com.prj.dao.TeacherDao;
import com.prj.entity.Account;
import com.prj.entity.Account.Status;
import com.prj.entity.Administrator;
import com.prj.entity.Student;
import com.prj.entity.Teacher;
import com.prj.service.AccountService;
import com.prj.util.AccountCharacter;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;
import com.prj.util.MD5Tool;
import com.prj.util.Page;
import com.prj.util.PasswordReset;
import com.prj.util.TokenTool;

@Service("AccountServiceImpl")
public class AccountServiceImpl implements AccountService {

	@Resource(name = "AccountDaoImpl")
	AccountDao dao;
	@Resource(name = "StudentDaoImpl")
	StudentDao studentDao;
	@Resource(name = "TeacherDaoImpl")
	TeacherDao teacherDao;
	@Resource(name = "AdministratorDaoImpl")
	AdministratorDao administractorDao;
	
	private Account getAccountByChar(Account account, AccountCharacter ac) {
		Account ret = null;
		switch (ac) {
		case STUDENT:
			ret = studentDao.getStudentByNumber(account.getNumber());
			break;
		case ADMINISTRATOR:
			ret =  administractorDao.getAdministratorByNumber(account.getNumber());
			break;
		case TEACHER:
			ret = teacherDao.getTeacherByNumber(account.getNumber());
			break;
		default:
			return null;
		}
//		if (ret != null)
//			ret.setAccountCharacter(ac);
		return ret;
	}
	
	private Account updateAccountByChar(Account account, AccountCharacter ac) {
		Account ret = null;
		switch (ac) {
		case STUDENT:
			ret = studentDao.updateStudent((Student) account);
			break;
		case ADMINISTRATOR:
			ret = administractorDao.updateAdministrator((Administrator) account);
			break;
		case TEACHER:
			ret = teacherDao.updateTeacher((Teacher) account);
			break;
		default:
			return null;
		}
//		ret.setAccountCharacter(ac);
		return ret;
	}
	
	public DataWrapper<Account> login(Account account, AccountCharacter ac) {
		DataWrapper<Account> ret = new DataWrapper<Account>();
		Account a = getAccountByChar(account, ac);
		
//		Account a = dao.getAccountByNumber(account.getNumber());
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		} else if (a.getStatus() == Status.INACTIVE) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Active);
		} else if (!a.getPassword().equals(
				MD5Tool.GetMd5(account.getPassword()))) {
			// System.out.println("POST:" +
			// MD5Tool.GetMd5(account.getAccountPassword()) + "\nDB  :" +
			// a.getAccountPassword());
			ret.setErrorCode(ErrorCodeEnum.Password_Wrong);
		} else {
//			a.setLastLoginTime(Calendar.getInstance().getTime());
			a.setLoginToken(TokenTool.generateToken(a));
			ret.setToken(a.getLoginToken());
			ret.setData(updateAccountByChar(a, ac));
		}
		return ret;
	}

	public void logout(Integer id, AccountCharacter ac) {
		Account a = null;
		switch (ac) {
		case STUDENT:
			a = studentDao.findStudentbyId(id);
			break;
		case ADMINISTRATOR:
			a = administractorDao.findAdministratorbyId(id);
			break;
		case TEACHER:
			a = teacherDao.findTeacherbyId(id);
			break;
		default:
			break;
		}
		if (a != null) {
			a.setLoginToken(null);
			dao.updateAccount(a);
		}
	}

	public DataWrapper<Account> addAccount(Account account) {
		DataWrapper<Account> ret = new DataWrapper<Account>();
		Account a = dao.getAccountByNumber(account.getNumber());
		account.setPassword(MD5Tool.GetMd5(account.getPassword()));
		if (a != null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Exist);
		} else if (dao.addAccount(account) != null) {
			ret.setData(account);
		} else {
			ret.setErrorCode(ErrorCodeEnum.Unknown_Error);
		}
		return ret;
	}

	public DataWrapper<Account> disableAccountById(Integer id) {
		Account a = dao.disableAccountById(id);
		DataWrapper<Account> ret = new DataWrapper<Account>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<List<Account>> getAllAccount() {
		return dao.getAllAccount();
	}

	public DataWrapper<Account> getAccountById(int id) {
		DataWrapper<Account> ret = new DataWrapper<Account>();
		Account a = dao.findAccountbyId(id);
		ret.setData(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<Account> updateAccount(Account v) {
		Account a = dao.updateAccount(v);
		DataWrapper<Account> ret = new DataWrapper<Account>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

//	public DataWrapper<Account> updateAccountCharacter(Integer accountId,
//			AccountCharacter accountCharacter) {
//		Account a = dao.findAccountbyId(accountId);
//		DataWrapper<Account> ret = new DataWrapper<Account>(a);
//		if (a == null) {
//			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
//		} else {
//			a.setAccountCharacter(accountCharacter);
//			dao.updateAccount(a);
//		}
//		return ret;
//	}

	public DataWrapper<Account> reset(PasswordReset reset) {
		Account a = dao.findAccountbyId(reset.getId());
		DataWrapper<Account> ret = new DataWrapper<Account>();
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		} else if (!a.getPassword().equals(
				MD5Tool.GetMd5(reset.getOldPassword()))) {
			ret.setErrorCode(ErrorCodeEnum.Password_Wrong);
		} else {
			a.setPassword(MD5Tool.GetMd5(reset.getNewPassword()));
			a.setLoginToken(null);
			dao.updateAccount(a);
		}
		return ret;
	}

	// Methods Following Are Not Checked... YET!
	public Page<Account> getAccountbyPage(int pagenumber, int pagesize) {
		return dao.getAccountbyPage(pagenumber, pagesize);
	}

	public Page<Account> searchAccount(int pagenumber, int pagesize, String name) {
		// return dao.searchAccount(pagenumber, pagesize, name);
		return null;
	}

	public Page<Account> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list) {
		return dao.getByPageWithConditions(pagenumber, pagesize, list);
	}
}
