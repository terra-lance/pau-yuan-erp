package com.terrase.frame.service.base;

import java.util.List;

import com.terrase.frame.dao.base.MasterEntityDAO;
import com.terrase.frame.data.base.MasterEntity;

public interface MasterEntityService<T extends MasterEntity> extends EntityService<T> {

	public MasterEntityDAO<T> getClassDAO();

	public List<T> findByActive(boolean active);
}
