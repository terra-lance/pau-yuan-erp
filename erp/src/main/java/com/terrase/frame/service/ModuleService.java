package com.terrase.frame.service;

import com.terrase.frame.data.Module;
import com.terrase.frame.service.base.MasterEntityService;

public interface ModuleService extends MasterEntityService<Module> {
	
	public Module findByName(String name);
}