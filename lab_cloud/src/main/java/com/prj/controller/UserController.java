package com.prj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prj.entity.User;
import com.prj.service.UserService;
import com.prj.util.JsonUtil;
import com.prj.util.Page;
import com.prj.util.RequestHelper;

@Controller
@RequestMapping(value = "/User")
public class UserController {

	@Resource(name = "UserServiceImpl")
	UserService vs;

	@Autowired
	JsonUtil jsonutil;

	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public String IndexView(Model model) {
		return "User/table";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<User> ListUser(Model model) {
		return vs.getAllUser();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable int id) {
		return vs.getUserById(id);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteUser(@PathVariable int id) {
		User v = new User();
		v.setId(id);
		return vs.deleteUser(v);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public boolean addUser(@RequestBody String data) {
		System.out.println("json string:" + data);
		User v = jsonutil.toObject(data, User.class);
		return vs.addUser(v);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public User updateUser(@RequestBody String data) {
		User v = jsonutil.toObject(data, User.class);
		return vs.updateUser(v);
	}

	@RequestMapping(value = "/conditions/{page_number}/{page_size}", method = RequestMethod.GET)
	@ResponseBody
	public Page<User> getTaxByConditions(@PathVariable int page_number,
			@PathVariable int page_size, HttpServletRequest request) {
		String[] parameters = { "year", "month" };

		ArrayList<SimpleExpression> list = RequestHelper.parseParameters(
				request, parameters);

		return vs.getByPageWithConditions(page_number, page_size, list);
	}
}
