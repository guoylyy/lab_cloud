package com.prj.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao {
	@SuppressWarnings("rawtypes")
	public List searchByColumn(String classname, Map<String, String> eq,
			Map<String, String> like, Map<String, String> less,
			Map<String, String> greater);
}
