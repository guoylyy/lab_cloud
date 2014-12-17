package com.prj.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.ExperimentDao;
import com.prj.dao.LabDao;
import com.prj.entity.Experiment;
import com.prj.entity.Lab;
import com.prj.service.LabService;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;
import com.prj.util.Page;

@Service("LabServiceImpl")
public class LabServiceImpl implements LabService {

	@Resource(name = "LabDaoImpl")
	LabDao dao;
	@Resource(name = "ExperimentDaoImpl")
	ExperimentDao experimentdao;

	public DataWrapper<Lab> addLab(Lab lab) {
		DataWrapper<Lab> ret = new DataWrapper<Lab>();
		Lab a = dao.getActiveLabByNumber(lab.getLabNumber());
		if (a != null) {
			ret.setErrorCode(ErrorCodeEnum.Lab_Exist);
		} else if (dao.isFull()) {
			ret.setErrorCode(ErrorCodeEnum.Reach_Lab_Limit);
		} else if (dao.addLab(lab) != null) {
			ret.setData(lab);
		} else {
			ret.setErrorCode(ErrorCodeEnum.Unknown_Error);
		}
		return ret;
	}

	public DataWrapper<Lab> deleteLabById(Integer id) {
		Lab a = dao.deleteLabById(id);
		DataWrapper<Lab> ret = new DataWrapper<Lab>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Lab_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<List<Lab>> getAllLab() {
		return dao.getAllLab();
	}

	public DataWrapper<List<Lab>> getAllActiveLab() {
		return dao.getAllActiveLab();
	}

	public DataWrapper<Lab> getLabById(int id) {
		DataWrapper<Lab> ret = new DataWrapper<Lab>();
		Lab a = dao.findLabById(id);
		ret.setData(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Lab_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<Lab> updateLab(Lab v) {
		Lab a = dao.updateLab(v);
		DataWrapper<Lab> ret = new DataWrapper<Lab>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Lab_Not_Exist);
		}
		return ret;
	}

	// Methods Following Are Not Checked... YET!
	public Page<Lab> getLabByPage(int pagenumber, int pagesize) {
		return dao.getLabByPage(pagenumber, pagesize);
	}

	public Page<Lab> searchLab(int pagenumber, int pagesize,
			String name) {
		//return dao.searchLab(pagenumber, pagesize, name);
		return null;
	}

	public Page<Lab> getByPageWithConditions(int pagenumber,
			int pagesize, List<SimpleExpression> list) {
		return dao.getByPageWithConditions(pagenumber, pagesize, list);
	}

	@Override
	public DataWrapper<Lab> addExperiment(int labid, int experimentid) {
		// TODO Auto-generated method stub
		DataWrapper<Lab> ret =  new DataWrapper<Lab>();
		Experiment experiment = experimentdao.findExperimentById(experimentid);
		if(experiment == null)
			ret.setErrorCode(ErrorCodeEnum.Experiment_Not_Exist);
		else
			if(!dao.addExperiment(labid, experiment))
				ret.setErrorCode(ErrorCodeEnum.Lab_Not_Exist);
		return ret;
	}

	@Override
	public DataWrapper<Lab> deleteExperiment(int labid, int experimentid) {
		// TODO Auto-generated method stub
		DataWrapper<Lab> ret = new DataWrapper<Lab>();
		Experiment experiment = experimentdao.findExperimentById(experimentid);
		if(experiment == null)
			ret.setErrorCode(ErrorCodeEnum.Experiment_Not_Exist);
		else
			if(!dao.deleteExperiment(labid, experiment));
				ret.setErrorCode(ErrorCodeEnum.Lab_Not_Exist);
				
		return ret;
	}

	@Override
	public DataWrapper<List<Experiment>> getExperimentList(int labid) {
		// TODO Auto-generated method stub
		DataWrapper<List<Experiment>> ret = new DataWrapper<List<Experiment>>();
		List<Experiment> list = dao.getExperimentsOfLab(labid);
		if(list == null)
			ret.setErrorCode(ErrorCodeEnum.Lab_Not_Exist);
		else
			ret.setData(list);
		return ret;
	}
}
