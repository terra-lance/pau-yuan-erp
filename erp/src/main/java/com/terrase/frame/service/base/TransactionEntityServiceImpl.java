package com.terrase.frame.service.base;

import com.terrase.frame.data.User;
import com.terrase.frame.data.base.TransactionEntity;
import com.terrase.frame.data.structure.ValidationException;
import com.terrase.util.StringUtil;

public abstract class TransactionEntityServiceImpl<T extends TransactionEntity> extends EntityServiceImpl<T>
		implements TransactionEntityService<T> {

	@Override
	public void transact(T object, User user) throws Exception {
		validateTransact(object);
		getClassDAO().insert(object, user);
	}

	protected void validateTransact(T object) throws Exception {
		validateFormat(object);
	}

	@Override
	public void revise(T object, User user) throws Exception {
		validateRevise(object);
		getClassDAO().update(object, user);
	}

	protected void validateRevise(T object) throws Exception {
		validateFormat(object);
	}

	@Override
	public void cancel(T object, User user) throws Exception {
		validateCancel(object);
		getClassDAO().delete(object, user);
	}

	protected void validateCancel(T object) throws Exception {

	}

	@Override
	protected void validateFormat(T object) throws Exception {
		super.validateFormat(object);

		if (!object.isAutoNumber() && StringUtil.isEmpty(object.getDocumentNo())) {
			throw new ValidationException("Please enter document number or set it to auto");
		}
	}
}
