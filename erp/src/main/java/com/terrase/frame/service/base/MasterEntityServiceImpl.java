package com.terrase.frame.service.base;

import java.util.List;

import com.terrase.frame.data.base.MasterEntity;
import com.terrase.frame.data.structure.ValidationException;
import com.terrase.util.StringUtil;

public abstract class MasterEntityServiceImpl<T extends MasterEntity> extends EntityServiceImpl<T>
		implements MasterEntityService<T> {

	public List<T> findByActive(boolean active) {
		return getClassDAO().findByActive(active);
	}

	@Override
	protected void validateFormat(T object) throws Exception {
		super.validateFormat(object);

		if (StringUtil.isEmpty(object.getCode())) {
			throw new ValidationException("Please enter code");
		}
	}
}
