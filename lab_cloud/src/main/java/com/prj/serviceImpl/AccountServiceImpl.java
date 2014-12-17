package com.prj.serviceImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
import com.prj.util.ApplicationContextUtils;
import com.prj.util.CallStatusEnum;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;
import com.prj.util.MD5Tool;
import com.prj.util.Page;
import com.prj.util.PasswordReset;
import com.prj.util.SearchCriteria;
import com.prj.util.TokenTool;

@Service("AccountServiceImpl")
public class AccountServiceImpl implements AccountService {

	@Resource(name = "AccountDaoImpl")
	AccountDao accountDao;
	@Resource(name = "StudentDaoImpl")
	StudentDao studentDao;
	@Resource(name = "TeacherDaoImpl")
	TeacherDao teacherDao;
	@Resource(name = "AdministratorDaoImpl")
	AdministratorDao administratorDao;
	
	public Account getAccountByNumber(String number, AccountCharacter ac) {
		Account ret = null;
		switch (ac) {
		case STUDENT:
			ret = studentDao.getStudentByNumber(number);
			break;
		case ADMINISTRATOR:
			ret =  administratorDao.getAdministratorByNumber(number);
			break;
		case TEACHER:
			ret = teacherDao.getTeacherByNumber(number);
			break;
		default:
			return null;
		}
//		if (ret != null)
//			ret.setAccountCharacter(ac);
		return ret;
	}
	
	private Account getByNumber(String number, AccountCharacter ac) {
		try {
			Class<?> clazz = this.getClass();
			Field field = clazz.getDeclaredField(ac.getLowerCaseLabel()+"Dao");
			Object dao = field.get(this);
			Method method = dao.getClass().getMethod("get"+ac.getCapitalizedLabel()+"ByNumber", String.class);
			Account ret = (Account)method.invoke(dao, number);
			return ret;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public DataWrapper<Account> updateAccountByChar(Account account, AccountCharacter ac) {
		Account a = updateByChar(account, ac);
		DataWrapper<Account> ret = new DataWrapper<Account>();
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		ret.setData(a);
		return ret;
	}
	
	private Account updateByChar(Account account, AccountCharacter ac) {
		Account ret = null;
		switch (ac) {
		case STUDENT:
			ret = studentDao.updateStudent((Student) account);
			break;
		case ADMINISTRATOR:
			ret = administratorDao.updateAdministrator((Administrator) account);
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
		Account a = getByNumber(account.getNumber(), ac);
		
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
			ret.setData(updateByChar(a, ac));
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
			a = administratorDao.findAdministratorbyId(id);
			break;
		case TEACHER:
			a = teacherDao.findTeacherbyId(id);
			break;
		default:
			break;
		}
		if (a != null) {
			a.setLoginToken(null);
			accountDao.updateAccount(a);
		}
	}

	public DataWrapper<Account> addAccount(Account account) {
		DataWrapper<Account> ret = new DataWrapper<Account>();
		Account a = accountDao.getAccountByNumber(account.getNumber());
		account.setPassword(MD5Tool.GetMd5(account.getPassword()));
		if (a != null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Exist);
		} else if (accountDao.addAccount(account) != null) {
			ret.setData(account);
		} else {
			ret.setErrorCode(ErrorCodeEnum.Unknown_Error);
		}
		return ret;
	}

	public DataWrapper<Account> disableAccountById(Integer id) {
		Account a = accountDao.disableAccountById(id);
		DataWrapper<Account> ret = new DataWrapper<Account>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<List<Account>> getAllAccount() {
		return accountDao.getAllAccount();
	}

	public DataWrapper<Account> getAccountByIdChar(Integer id, AccountCharacter ac) {
		Account a = null;
		DataWrapper<Account> ret = new DataWrapper<Account>();
		
		switch (ac) {
		case STUDENT:
			a = studentDao.findStudentbyId(id);
			break;
		case ADMINISTRATOR:
			a = administratorDao.findAdministratorbyId(id);
			break;
		case TEACHER:
			a = teacherDao.findTeacherbyId(id);
			break;
		default:
			break;
		}
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		} else {
			ret.setData(a);
		}
		return ret;
	}
	
//	public DataWrapper<Account> getAccountById(int id) {
//		DataWrapper<Account> ret = new DataWrapper<Account>();
//		Account a = dao.findAccountbyId(id);
//		ret.setData(a);
//		if (a == null) {
//			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
//		}
//		return ret;
//	}

//	public DataWrapper<Account> updateAccount(Account v) {
//		Account a = dao.updateAccount(v);
//		DataWrapper<Account> ret = new DataWrapper<Account>(a);
//		if (a == null) {
//			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
//		}
//		return ret;
//	}

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
		Account a = accountDao.findAccountbyId(reset.getId());
		DataWrapper<Account> ret = new DataWrapper<Account>();
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
		} else if (!a.getPassword().equals(
				MD5Tool.GetMd5(reset.getOldPassword()))) {
			ret.setErrorCode(ErrorCodeEnum.Password_Wrong);
		} else {
			a.setPassword(MD5Tool.GetMd5(reset.getNewPassword()));
			a.setLoginToken(null);
			accountDao.updateAccount(a);
		}
		return ret;
	}

	// Methods Following Are Not Checked... YET!
	public Page<Account> getAccountbyPage(int pagenumber, int pagesize) {
		return accountDao.getAccountbyPage(pagenumber, pagesize);
	}

	public Page<Account> searchAccount(int pagenumber, int pagesize, String name) {
		// return dao.searchAccount(pagenumber, pagesize, name);
		return null;
	}

	public Page<Account> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list) {
		return accountDao.getByPageWithConditions(pagenumber, pagesize, list);
	}

//	private DataWrapper<List<Account>> toAccountList(DataWrapper<List<?>> wrapper) {
//		List<Account> al = new ArrayList<Account>();
//		List<?> sl = wrapper.getData();
//		for (Object o : sl) {
//			al.
//		}
//	}
	
	@Override
	public DataWrapper<List<? extends Account>> searchAccount(SearchCriteria sc) {
		DataWrapper<List<? extends Account>> ret = new DataWrapper<List<? extends Account>>();
		String number = sc.getNumber();
		AccountCharacter ac = sc.getCharacter();
		Account a = null;
		if (number == null && ac == null) {
			ret.setErrorCode(ErrorCodeEnum.Search_Criteria_Null);
		} else if (number == null) {
			switch (ac) {
			case ADMINISTRATOR:
				ret.setData(administratorDao.getAllAdministrator().getData());
				break;
			case STUDENT:
				ret.setData(studentDao.getAllStudent().getData());
				break;
			case TEACHER:
				ret.setData(teacherDao.getAllTeacher().getData());
				break;
			default:
				ret.setErrorCode(ErrorCodeEnum.Search_Character_Wrong);
				break;
			}
		} else if (ac == null){
			a = administratorDao.getAdministratorByNumber(number);
			if (a == null) {
				a = studentDao.getStudentByNumber(number);
				if (a == null) {
					a = teacherDao.getTeacherByNumber(number);
					if (a == null) {
						ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
					}
				}
			}
			if (ret.getCallStatus().equals(CallStatusEnum.SUCCEED)) {
				List<Account> l = new ArrayList<Account>();
				l.add(a);
				ret.setData(l);
			}
		} else {
			switch (ac) {
			case ADMINISTRATOR:
				a = administratorDao.getAdministratorByNumber(number);
				break;
			case STUDENT:
				a = studentDao.getStudentByNumber(number);
				break;
			case TEACHER:
				a = teacherDao.getTeacherByNumber(number);
				break;
			default:
				ret.setErrorCode(ErrorCodeEnum.Account_Not_Exist);
				break;
			}
			if (ret.getCallStatus() == CallStatusEnum.SUCCEED) {
				List<Account> l = new ArrayList<Account>();
				l.add(a);
				ret.setData(l);
			}
		}
		return ret;
	}

	@Override
	public DataWrapper<List<? extends Account>> getAccountByRole(AccountCharacter ac) {
		// TODO Auto-generated method stub 
		try {
			Class<?> clazz = this.getClass();
			Field field = clazz.getDeclaredField(ac.getLowerCaseLabel()+"Dao");
			Object dao = field.get(this);
			Method method = dao.getClass().getMethod("getAll"+ac.getCapitalizedLabel());
			@SuppressWarnings("unchecked")
			DataWrapper<List<? extends Account>> list = (DataWrapper<List<? extends Account>>)method.invoke(dao);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public DataWrapper<List<? extends Account>> getAccountByStatus(Status status) {
		// TODO Auto-generated method stub
		DataWrapper<List<? extends Account>> ret = new DataWrapper<List<? extends Account>>();
		List<Account> list = new ArrayList<Account>();
		
		list.addAll(administratorDao.getAdministratorByStatus(status));
		list.addAll(teacherDao.getTeacherByStatus(status));
		list.addAll(studentDao.getStudentByStatus(status));
		
		ret.setData(list);
		
		return ret;
	}
}
