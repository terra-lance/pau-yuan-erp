package com.terrase.frame.dao;

import org.springframework.stereotype.Repository;

import com.terrase.frame.dao.base.EntityDAOImpl;
import com.terrase.frame.data.Configuration;

@Repository("com.terrase.stingray.dao.ConfigurationDAO")
public class ConfigurationDAOImpl extends EntityDAOImpl<Configuration> implements ConfigurationDAO {

	@Override
	public Class<Configuration> getClassParam() {
		return Configuration.class;
	}
}
