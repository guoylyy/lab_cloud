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
import com.prj.entity.Lab;
import com.prj.service.LabService;
import com.prj.util.AccountAccess;
import com.prj.util.AccountCharacter;
import com.prj.util.AuthorityException;
import com.prj.util.DataWrapper;

@Controller
@RequestMapping(value = "/Lab")
public class LabController {

	@Resource(name = "LabServiceImpl")
	LabService vs;
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Lab> add(@RequestBody DataWrapper<Lab> lab) {
		return vs.addLab(lab.getData());
	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public DataWrapper<Lab> deleteLab(@PathVariable int id) {
		return vs.deleteLabById(id);
	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<List<Lab>> getLabList(@RequestBody DataWrapper<?> wrapper) {
		return vs.getAllActiveLab();
	}

	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Lab> getLab(@RequestBody DataWrapper<?> wrapper, @PathVariable int id) {
		return vs.getLabById(id);
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Lab> updateLab(@RequestBody DataWrapper<Lab> lab,  @PathVariable int id) {
		return vs.updateLab(lab.getData());
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/{labid}/experiment/add/{experimentid}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Lab> addExperiment(@RequestBody DataWrapper<?> wrapper,  @PathVariable int labid, @PathVariable int experimentid) {
		return vs.addExperiment(labid, experimentid);
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/{labid}/experiment/delete/{experimentid}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Lab> deleteExperiment(@RequestBody DataWrapper<?> wrapper,  @PathVariable int labid, @PathVariable int experimentid) {
		return vs.deleteExperiment(labid, experimentid);
	}
	
	@AccountAccess(checkAccountCharacter = AccountCharacter.ADMINISTRATOR)
	@RequestMapping(value = "/{labid}/experiment/list", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<List<Experiment>> getExperimentList(@RequestBody DataWrapper<?> wrapper, @PathVariable int labid) {
		return vs.getExperimentList(labid);
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
