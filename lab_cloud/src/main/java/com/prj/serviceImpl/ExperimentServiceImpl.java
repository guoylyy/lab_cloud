package com.prj.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import com.prj.dao.ExperimentDao;
import com.prj.entity.Experiment;
import com.prj.service.ExperimentService;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;
import com.prj.util.Page;

@Service("ExperimentServiceImpl")
public class ExperimentServiceImpl implements ExperimentService {

	@Resource(name = "ExperimentDaoImpl")
	ExperimentDao dao;

	public DataWrapper<Experiment> addExperiment(Experiment experiment) {
		DataWrapper<Experiment> ret = new DataWrapper<Experiment>();
		Experiment a = dao.getExperimentByNumber(experiment.getExperimentNumber());
		if (a != null) {
			ret.setErrorCode(ErrorCodeEnum.Experiment_Exist);
		} else if (dao.addExperiment(experiment) != null) {
			ret.setData(experiment);
		} else {
			ret.setErrorCode(ErrorCodeEnum.Unknown_Error);
		}
		return ret;
	}

	public DataWrapper<Experiment> deleteExperimentById(Integer id) {
		Experiment a = dao.deleteExperimentById(id);
		DataWrapper<Experiment> ret = new DataWrapper<Experiment>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Experiment_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<List<Experiment>> getAllExperiment() {
		return dao.getAllExperiment();
	}

	public DataWrapper<List<Experiment>> getAllActiveExperiment() {
		return dao.getAllActiveExperiment();
	}

	public DataWrapper<Experiment> getExperimentById(int id) {
		DataWrapper<Experiment> ret = new DataWrapper<Experiment>();
		Experiment a = dao.findExperimentById(id);
		ret.setData(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Experiment_Not_Exist);
		}
		return ret;
	}

	public DataWrapper<Experiment> updateExperiment(Experiment v) {
		Experiment a = dao.updateExperiment(v);
		DataWrapper<Experiment> ret = new DataWrapper<Experiment>(a);
		if (a == null) {
			ret.setErrorCode(ErrorCodeEnum.Experiment_Not_Exist);
		}
		return ret;
	}

	// Methods Following Are Not Checked... YET!
	public Page<Experiment> getExperimentByPage(int pagenumber, int pagesize) {
		return dao.getExperimentByPage(pagenumber, pagesize);
	}

	public Page<Experiment> searchExperiment(int pagenumber, int pagesize,
			String name) {
		//return dao.searchExperiment(pagenumber, pagesize, name);
		return null;
	}

	public Page<Experiment> getByPageWithConditions(int pagenumber,
			int pagesize, List<SimpleExpression> list) {
		return dao.getByPageWithConditions(pagenumber, pagesize, list);
	}
}
