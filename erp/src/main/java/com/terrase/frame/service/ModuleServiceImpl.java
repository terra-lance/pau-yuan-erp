package com.terrase.frame.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrase.frame.dao.ModuleDAO;
import com.terrase.frame.dao.base.MasterEntityDAO;
import com.terrase.frame.data.Module;
import com.terrase.frame.service.base.MasterEntityServiceImpl;

@Service("com.terrase.frame.service.ModuleService")
public class ModuleServiceImpl extends MasterEntityServiceImpl<Module> implements ModuleService {

	@Autowired
	private ModuleDAO classDAO;

	@Override
	public MasterEntityDAO<Module> getClassDAO() {
		return classDAO;
	}

	@Override
	public Module findByName(String name) {
		Map<String, Object> filters = new LinkedHashMap<>();
		filters.put("name", name);
		return findSingle(filters, null);
	}
}
