package com.prj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prj.entity.Account;
import com.prj.service.AccountService;
import com.prj.util.AccountAccess;
import com.prj.util.AccountCharacter;
import com.prj.util.AuthorityException;
import com.prj.util.DataWrapper;
import com.prj.util.JsonUtil;
import com.prj.util.Page;
import com.prj.util.PasswordReset;
import com.prj.util.RequestHelper;

@Controller
@RequestMapping(value = "/Account")
public class AccountController {

	@Resource(name = "AccountServiceImpl")
	AccountService vs;

	@Autowired
	JsonUtil jsonutil;

	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public String IndexView(Model model) {
		return "Account/table";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Account> ListAccount(Model model) {
		return vs.getAllAccount();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteAccount(@PathVariable int id) {
		Account v = new Account();
		v.setId(id);
		return vs.deleteAccount(v);
	}

//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	@ResponseBody
//	public DataWrapper<Account> addAccount(@RequestBody String data) {
//		System.out.println("json string:" + data);
//		Account v = jsonutil.toObject(data, Account.class);
//		return vs.addAccount(v);
//	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public Account updateAccount(@RequestBody String data) {
		Account v = jsonutil.toObject(data, Account.class);
		return vs.updateAccount(v);
	}

	@RequestMapping(value = "/conditions/{page_number}/{page_size}", method = RequestMethod.GET)
	@ResponseBody
	public Page<Account> getTaxByConditions(@PathVariable int page_number,
			@PathVariable int page_size, HttpServletRequest request) {
		String[] parameters = { "year", "month" };

		ArrayList<SimpleExpression> list = RequestHelper.parseParameters(
				request, parameters);

		return vs.getByPageWithConditions(page_number, page_size, list);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Account> login(@RequestBody DataWrapper<Account> account) {
		return vs.login(account.getData());
	}
	
	@SuppressWarnings("rawtypes")
	@AccountAccess
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper logout(@RequestBody DataWrapper<Account> wrapper) {
		vs.logout(wrapper.getAccountId());
		return new DataWrapper();
	}

	@AccountAccess
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Account> getAccount(@RequestBody DataWrapper<?> wrapper) {
		return vs.getAccountById(wrapper.getAccountId());
	}

	@AccountAccess
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Account> resetPassword(@RequestBody DataWrapper<PasswordReset> wrapper) {
		wrapper.getData().setId(wrapper.getAccountId());
		return vs.reset(wrapper.getData());
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Account> add(@RequestBody DataWrapper<Account> account) {
		return vs.register(account.getData());
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Account> getAccount(@RequestBody DataWrapper<Account> account, @PathVariable int id) {
		return vs.getAccountById(id);
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
