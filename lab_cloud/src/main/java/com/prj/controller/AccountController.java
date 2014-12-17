package com.prj.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.prj.entity.Account;
import com.prj.service.AccountService;
import com.prj.service.FileUploadService;
import com.prj.util.AccountAccess;
import com.prj.util.AccountCharacter;
import com.prj.util.AuthorityException;
import com.prj.util.CallStatusEnum;
import com.prj.util.DataWrapper;
import com.prj.util.SearchCriteria;

@Controller
@RequestMapping(value = "/Account")
public class AccountController {

	@Resource(name = "AccountServiceImpl")
	AccountService as;
	@Resource(name = "FileUploadServiceImpl")
	FileUploadService fs;

//	@RequestMapping(value = "/table", method = RequestMethod.GET)
//	public String IndexView(Model model) {
//		return "Account/table";
//	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Account> login(@RequestBody DataWrapper<Account> account) {
		return as.login(account.getData(), account.getAccountCharacter());
	}
	
	@AccountAccess
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> logout(@RequestBody DataWrapper<?> wrapper) {
		as.logout(wrapper.getAccountId(), wrapper.getAccountCharacter());
		return new DataWrapper<Void>();
	}

	@AccountAccess
	@RequestMapping(value = "/search", method = RequestMethod.POST) 
	@ResponseBody
	public DataWrapper<List<? extends Account>> search(@RequestBody DataWrapper<SearchCriteria> wrapper) {
		SearchCriteria sc = wrapper.getData();
		return as.searchAccount(sc);
	}
	
	
	@RequestMapping(value = "/accountlist/role/{role}", method = RequestMethod.GET) 
	@ResponseBody
	public DataWrapper<List<? extends Account>> accountlistrole(@PathVariable AccountCharacter role) {
		
		return as.getAccountByRole(role);
	}
	
	@RequestMapping(value = "/accountlist/status/{status}", method = RequestMethod.GET) 
	@ResponseBody
	public DataWrapper<List<? extends Account>> accountliststatus(@PathVariable Account.Status status) {
		
		return as.getAccountByStatus(status);
	}
	
	@AccountAccess
	@RequestMapping(value = "/upload/icon", method = RequestMethod.POST) 
	@ResponseBody
	public DataWrapper<Void> uploadIcon(@RequestBody DataWrapper<MultipartFile> wrapper, HttpServletRequest request) {
		DataWrapper<Void> ret = new DataWrapper<Void>();
		MultipartFile file = wrapper.getData();
		String path = request.getSession().getServletContext().getRealPath("/files/"+wrapper.getAccountCharacter()+"/"+wrapper.getAccountId()+"/icon");
		DataWrapper<String> fsRet = fs.saveIcon(path, file, "icon");
		if (fsRet.getCallStatus().equals(CallStatusEnum.FAILED)) {
			ret.setErrorCode(fsRet.getErrorCode());
			return ret;
		}
		DataWrapper<Account> asRet = as.getAccountByIdChar(wrapper.getAccountId(), wrapper.getAccountCharacter());
		if (asRet.getCallStatus().equals(CallStatusEnum.FAILED)) {
			ret.setErrorCode(asRet.getErrorCode());
			return ret;
		}
		Account a = asRet.getData();
		a.setIconPath(fsRet.getData());
		asRet = as.updateAccountByChar(a, wrapper.getAccountCharacter());
		if (asRet.getCallStatus().equals(CallStatusEnum.FAILED)) {
			ret.setErrorCode(asRet.getErrorCode());
			return ret;
		}
		return ret;
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
