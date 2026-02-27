package com.terrase.frame.dao;

import org.springframework.stereotype.Repository;

import com.terrase.frame.dao.base.MasterEntityDAOImpl;
import com.terrase.frame.data.Module;

@Repository("com.terrase.frame.dao.ModuleDAO")
public class ModuleDAOImpl extends MasterEntityDAOImpl<Module> implements ModuleDAO {
	@Override
	public Class<Module> getClassParam() {
		return Module.class;
	}
}
