package com.terrase.frame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrase.frame.dao.ConfigurationDAO;
import com.terrase.frame.dao.base.EntityDAO;
import com.terrase.frame.data.Configuration;
import com.terrase.frame.service.base.EntityServiceImpl;

@Service("com.terrase.frame.service.ConfigurationService")
public class ConfigurationServiceImpl extends EntityServiceImpl<Configuration> implements ConfigurationService {

	@Autowired
	private ConfigurationDAO classDAO;

	@Override
	public EntityDAO<Configuration> getClassDAO() {
		return classDAO;
	}
}
