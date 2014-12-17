package com.prj.service;

import java.util.List;

import org.hibernate.criterion.SimpleExpression;

import com.prj.entity.Experiment;
import com.prj.entity.Lab;
import com.prj.util.DataWrapper;
import com.prj.util.Page;

public interface LabService {
	DataWrapper<Lab> addLab(Lab lab);

	public DataWrapper<Lab> deleteLabById(Integer id);

	DataWrapper<List<Lab>> getAllLab();

	DataWrapper<List<Lab>> getAllActiveLab();
	
	DataWrapper<Lab> getLabById(int id);

	DataWrapper<Lab> updateLab(Lab entity);

	Page<Lab> getLabByPage(int pagenumber, int pagesize);

	Page<Lab> searchLab(int pagenumber, int pagesize, String name);

	Page<Lab> getByPageWithConditions(int pagenumber, int pagesize,
			List<SimpleExpression> list);
	
	DataWrapper<Lab> addExperiment(int labid, int experimentid);
	
	DataWrapper<Lab> deleteExperiment(int labid, int experimentid);
	
	DataWrapper<List<Experiment>> getExperimentList(int labid);
}
