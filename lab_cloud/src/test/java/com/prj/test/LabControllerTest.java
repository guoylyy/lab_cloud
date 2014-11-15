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
import com.prj.controller.LabController;
import com.prj.entity.Account;
import com.prj.entity.Lab;
import com.prj.util.CallStatusEnum;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("/proj-test.xml") 
@Transactional() 
public class LabControllerTest {
	@Autowired
	AccountController accountCtrl;
	@Autowired
	LabController ctrl;
	
	private Account admin;
	private String adminToken;
	private DataWrapper<Lab> postData;
	private DataWrapper<Lab> ret;
	
	private Lab lab;
	
	@Before
	public void initialize() {
		this.admin = login("1435000", "admin").getData();
		this.adminToken = admin.getLoginToken();
		this.postData = new DataWrapper<Lab>(adminToken);
	}

	@Test
	public void testAddLab() {
		lab = new Lab();
		lab.setCapacity(40);
		lab.setIsActive(true);
		lab.setLabNumber("328");
		lab.setStatus(Lab.Status.OPEN);
		postData.setData(lab);
		ret = ctrl.add(postData);
		assertEquals(CallStatusEnum.SUCCEED, ret.getCallStatus());
		assertEquals(ErrorCodeEnum.No_Error, ret.getErrorCode());
		lab = ret.getData();
	}
	
	private DataWrapper<Account> login(String number, String password) {
		Account loginAdmin = new Account();
		loginAdmin.setAccountNumber(number);
		loginAdmin.setAccountPassword(password);
		return accountCtrl.login(new DataWrapper<Account>(loginAdmin));
	}
}
