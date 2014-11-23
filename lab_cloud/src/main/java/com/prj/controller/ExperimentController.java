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

import com.prj.entity.Experiment;
import com.prj.service.ExperimentService;
import com.prj.util.AccountAccess;
import com.prj.util.AccountCharacter;
import com.prj.util.AuthorityException;
import com.prj.util.DataWrapper;

@Controller
@RequestMapping(value = "/Experiment")
public class ExperimentController {

	@Resource(name = "ExperimentServiceImpl")
	ExperimentService vs;
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Experiment> add(@RequestBody DataWrapper<Experiment> experiment) {
		return vs.addExperiment(experiment.getData());
	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Experiment> deleteExperiment(@PathVariable int id) {
		return vs.deleteExperimentById(id);
	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<List<Experiment>> getExperimentList(@RequestBody DataWrapper<?> wrapper) {
		return vs.getAllActiveExperiment();
	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Experiment> getExperiment(@RequestBody DataWrapper<?> wrapper, @PathVariable int id) {
		return vs.getExperimentById(id);
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Experiment> updateExperiment(@RequestBody DataWrapper<Experiment> experiment,  @PathVariable int id) {
		return vs.updateExperiment(experiment.getData());
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
