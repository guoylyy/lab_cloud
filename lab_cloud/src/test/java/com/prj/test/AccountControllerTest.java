package com.prj.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prj.controller.AccountController;
import com.prj.entity.Account;
import com.prj.util.AccountCharacter;
import com.prj.util.AuthorityException;
import com.prj.util.CallStatusEnum;
import com.prj.util.DataWrapper;
import com.prj.util.DateUtils;
import com.prj.util.ErrorCodeEnum;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("/proj-test.xml") 
@Transactional() 
public class AccountControllerTest {
	@Autowired 
	private AccountController ctrl; 
	
	private Account admin;
	private Account teacher;
	private Account student;
	
//	private String adminToken;
//	private String teacherToken;
//	private String studentToken;
	
	@Before
	public void initialize() {
		this.admin = new Account();
	}
//	@Test 
//	public void testGetAcccountById() { 
//		Account a = new Account();
//		a.setAccountNumber("130");
//		a.setAccountPassword("123");
//		DataWrapper<Account> ret = controller.login(new DataWrapper<Account>(a));
//		assertEquals("2ca358ec562e1e2f6cda17390739cb62:2014/12/13", ret.getToken()); 
//	}
	
	@Test
	public void testLogin() {
		DataWrapper<Account> ret = login("1435000", "admin");
//		System.out.println(ret.getToken());
//		System.out.println(admin.getAccountPassword());
		assertEquals(CallStatusEnum.SUCCEED, ret.getCallStatus());
		assertEquals(ErrorCodeEnum.No_Error, ret.getErrorCode());
		admin = ret.getData();
	}

	@Test 
	public void testLoginError() {
		DataWrapper<Account> ret = login("1435099", "admin");
		assertEquals(CallStatusEnum.FAILED, ret.getCallStatus());
		assertEquals(ErrorCodeEnum.Account_Not_Exist, ret.getErrorCode());
		ret = login("1435002", "admin");
		assertEquals(CallStatusEnum.FAILED, ret.getCallStatus());
		assertEquals(ErrorCodeEnum.Account_Not_Active, ret.getErrorCode());
		ret = login("1435000", "123");
		assertEquals(CallStatusEnum.FAILED, ret.getCallStatus());
		assertEquals(ErrorCodeEnum.Password_Wrong, ret.getErrorCode());
	}
	
	@Test
	public void testAddStudent() {
		testLogin();
		DataWrapper<Account> postData = new DataWrapper<Account>();
		student = new Account();
		student.setAccountCharacter(AccountCharacter.STUDENT);
		student.setAccountEmail("1435600@tongji.edu.cn");
		student.setAccountName("李三");
		student.setAccountNumber("1435600");
		student.setAccountPassword("student");
		student.setEntranceYearMonth(DateUtils.getDateFormString("2014-9-1 0:0:0"));
		student.setStudentGrade("2014"); 
		postData.setData(student);
		postData.setToken(admin.getLoginToken());
		DataWrapper<Account> ret = ctrl.add(postData);
		assertEquals(CallStatusEnum.SUCCEED, ret.getCallStatus());
		assertEquals(ErrorCodeEnum.No_Error, ret.getErrorCode());
//		student = ret.getData();
	}
	
	@Test
	public void testAddTeacher() {
		testLogin();
		DataWrapper<Account> postData = new DataWrapper<Account>();
		teacher = new Account();
		teacher.setAccountCharacter(AccountCharacter.TEACHER);
		teacher.setAccountEmail("1435100@tongji.edu.cn");
		teacher.setAccountName("赵老师");
		teacher.setAccountNumber("1435100");
		teacher.setAccountPassword("teacher");
		postData.setData(teacher);
		postData.setToken(admin.getLoginToken());
		DataWrapper<Account> ret = ctrl.add(postData);
		assertEquals(CallStatusEnum.SUCCEED, ret.getCallStatus());
		assertEquals(ErrorCodeEnum.No_Error, ret.getErrorCode());
//		teacher = ret.getData();
	}
	
	@Test
	public void testAccountAccessAspect() {
		testLogin();
		testLogin();
		
		testAddStudent();
		student = login("1435600", "student").getData();
		testAddTeacher();
		teacher = login("1435100", "teacher").getData();
		DataWrapper<Account> postData = new DataWrapper<Account>(student);
		try {
			ctrl.add(postData);
		} catch (AuthorityException ae) {
			assertEquals(ErrorCodeEnum.Token_Invalid, ae.getErrorCode());
		}
		postData.setToken("9e0bc2b483d7953224cc0b9a5afa7754:2014/10/14");
		try {
			ctrl.add(postData);
		} catch (AuthorityException ae) {
			assertEquals(ErrorCodeEnum.Token_Expired, ae.getErrorCode());
		}
		postData.setToken(admin.getLoginToken().toUpperCase());
		try {
			ctrl.add(postData);
		} catch (AuthorityException ae) {
			assertEquals(ErrorCodeEnum.Token_Invalid, ae.getErrorCode());
		}
		
		postData.setToken(student.getLoginToken());
		try {
			ctrl.add(postData);
		} catch (AuthorityException ae) {
			assertEquals(ErrorCodeEnum.Access_Denied, ae.getErrorCode());
		}
		postData.setToken(teacher.getLoginToken());
		try {
			ctrl.add(postData);
		} catch (AuthorityException ae) {
			assertEquals(ErrorCodeEnum.Access_Denied, ae.getErrorCode());
		}
	}
	
	@Test
	public void testAddError() {
		testAddStudent();
		DataWrapper<Account> postData = new DataWrapper<Account>(student);
		postData.setToken(admin.getLoginToken());
		DataWrapper<Account> ret = ctrl.add(postData);
		assertEquals(CallStatusEnum.FAILED, ret.getCallStatus());
		assertEquals(ErrorCodeEnum.Account_Exist, ret.getErrorCode());
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testGetAccount() {
		testLogin();
		DataWrapper postData = new DataWrapper(admin.getLoginToken());
		DataWrapper<Account> ret = ctrl.getAccount(postData);
		assertEquals(CallStatusEnum.SUCCEED, ret.getCallStatus());
		assertEquals(ErrorCodeEnum.No_Error, ret.getErrorCode());
		assertEquals(AccountCharacter.ADMINISTRATOR, ret.getData().getAccountCharacter());
		assertEquals("admin@tongji.edu.cn", ret.getData().getAccountEmail());
		assertEquals("admin", ret.getData().getAccountName());
		assertEquals("1435000", ret.getData().getAccountNumber());
		assertEquals("c06839f4d24fa0cf1f72ec06d7c3b4d9", ret.getData().getAccountPassword());
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testLogout() {
		testLogin();
		DataWrapper postData = new DataWrapper(admin.getLoginToken());
		ctrl.logout(postData);
		try {
			ctrl.getAccount(postData);
		} catch (AuthorityException ae) {
			assertEquals(ErrorCodeEnum.Token_Invalid, ae.getErrorCode());
		}
	}
	
	private DataWrapper<Account> login(String number, String password) {
		Account loginAdmin = new Account();
		loginAdmin.setAccountNumber(number);
		loginAdmin.setAccountPassword(password);
		DataWrapper<Account> ret = ctrl.login(new DataWrapper<Account>(loginAdmin));
		return ret;
	}
}
