package com.terrase.frame.dao.base;

import java.util.List;

import com.terrase.frame.data.base.MasterEntity;

public interface MasterEntityDAO<T extends MasterEntity> extends EntityDAO<T> {

	public List<T> findByActive(boolean active);
}
