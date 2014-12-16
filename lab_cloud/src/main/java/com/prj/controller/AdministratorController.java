package com.prj.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prj.entity.Administrator;
import com.prj.service.AdministratorService;
import com.prj.util.AccountAccess;
import com.prj.util.AccountCharacter;
import com.prj.util.AuthorityException;
import com.prj.util.DataWrapper;
import com.prj.util.PasswordReset;

@Controller
@RequestMapping(value = "/Administrator")
public class AdministratorController {
	@Resource(name = "AdministratorServiceImpl")
	AdministratorService vs;
	
//	@RequestMapping(value = "/table", method = RequestMethod.GET)
//	public String IndexView(Model model) {
//		return "Administrator/table";
//	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Administrator> getAdministrator(@RequestBody DataWrapper<?> wrapper) {
		return vs.getAdministratorById(wrapper.getAccountId());
	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Administrator> resetPassword(@RequestBody DataWrapper<PasswordReset> wrapper) {
		wrapper.getData().setId(wrapper.getAccountId());
		return vs.reset(wrapper.getData());
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Administrator> add(@RequestBody DataWrapper<Administrator> administrator) {
		return vs.addAdministrator(administrator.getData());
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Administrator> getAdministrator(@RequestBody DataWrapper<?> wrapper, @PathVariable int id) {
		return vs.getAdministratorById(id);
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<List<Administrator>> getAdministratorList(@RequestBody DataWrapper<?> wrapper) {
		return vs.getAllAdministrator();
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Administrator> deleteAdministrator(@PathVariable int id) {
		return vs.disableAdministratorById(id);
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Administrator> updateAdministrator(@RequestBody DataWrapper<Administrator> administrator,  @PathVariable int id) {
		return vs.updateAdministrator(administrator.getData());
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/page/{pageSize}/{pageNumber}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<List<Administrator>> getAdministratorList(@RequestBody DataWrapper<?> wrapper, @PathVariable Integer pageSize, @PathVariable Integer pageNumber) {
		return vs.getAdministratorbyPage(pageNumber, pageSize);
	}
	
	@ExceptionHandler(AuthorityException.class)
	@ResponseBody
	public DataWrapper<Void> handleAuthorityException(AuthorityException ex) {
		System.out.println(ex.getErrorCode().getLabel());
		DataWrapper<Void> ret = new DataWrapper<Void>();
		ret.setErrorCode(ex.getErrorCode());
		return ret;
	}
}
