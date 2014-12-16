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

import com.prj.entity.Account;
import com.prj.service.AccountService;
import com.prj.util.AccountAccess;
import com.prj.util.AccountCharacter;
import com.prj.util.AuthorityException;
import com.prj.util.DataWrapper;
import com.prj.util.PasswordReset;

@Controller
@RequestMapping(value = "/Account")
public class AccountController {

	@Resource(name = "AccountServiceImpl")
	AccountService vs;

//	@RequestMapping(value = "/table", method = RequestMethod.GET)
//	public String IndexView(Model model) {
//		return "Account/table";
//	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Account> login(@RequestBody DataWrapper<Account> account) {
		return vs.login(account.getData(), account.getAccountCharacter());
	}
	
	@AccountAccess
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> logout(@RequestBody DataWrapper<?> wrapper) {
		vs.logout(wrapper.getAccountId(), wrapper.getAccountCharacter());
		return new DataWrapper<Void>();
	}

//	@AccountAccess
//	@RequestMapping(value = "/profile", method = RequestMethod.POST)
//	@ResponseBody
//	public DataWrapper<Account> getAccount(@RequestBody DataWrapper<?> wrapper) {
//		return vs.getAccountById(wrapper.getAccountId());
//	}

//	@AccountAccess
//	@RequestMapping(value = "/reset", method = RequestMethod.POST)
//	@ResponseBody
//	public DataWrapper<Account> resetPassword(@RequestBody DataWrapper<PasswordReset> wrapper) {
//		wrapper.getData().setId(wrapper.getAccountId());
//		return vs.reset(wrapper.getData());
//	}
	
//	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	@ResponseBody
//	public DataWrapper<Account> add(@RequestBody DataWrapper<Account> account) {
//		return vs.addAccount(account.getData());
//	}
	
//	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
//	@RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
//	@ResponseBody
//	public DataWrapper<Account> getAccount(@RequestBody DataWrapper<?> wrapper, @PathVariable int id) {
//		return vs.getAccountById(id);
//	}
//	
//	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
//	@RequestMapping(value = "/all", method = RequestMethod.POST)
//	@ResponseBody
//	public DataWrapper<List<Account>> getAccountList(@RequestBody DataWrapper<?> wrapper) {
//		return vs.getAllAccount();
//	}
//	
//	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//	@ResponseBody
//	public DataWrapper<Account> deleteAccount(@PathVariable int id) {
//		return vs.disableAccountById(id);
//	}
	
//	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
//	@RequestMapping(value = "/updateAccountCharacter/{id}", method = RequestMethod.POST)
//	@ResponseBody
//	public DataWrapper<Account> updateAccountCharacter(@RequestBody DataWrapper<Account> account,  @PathVariable int id) {
//		return vs.updateAccountCharacter(id, account.getData().getAccountCharacter());
//	}
	
//	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
//	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
//	@ResponseBody
//	public DataWrapper<Account> updateAccount(@RequestBody DataWrapper<Account> account,  @PathVariable int id) {
//		return vs.updateAccount(account.getData());
//	}
	
	@ExceptionHandler(AuthorityException.class)
	@ResponseBody
	public DataWrapper<Void> handleAuthorityException(AuthorityException ex) {
		System.out.println(ex.getErrorCode().getLabel());
		DataWrapper<Void> ret = new DataWrapper<Void>();
		ret.setErrorCode(ex.getErrorCode());
		return ret;
	}
	
}
