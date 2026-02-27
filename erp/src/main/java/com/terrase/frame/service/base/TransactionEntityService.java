package com.terrase.frame.service.base;

import com.terrase.frame.data.User;
import com.terrase.frame.data.base.TransactionEntity;

public interface TransactionEntityService<T extends TransactionEntity> extends EntityService<T> {

	public void transact(T object, User user) throws Exception;
	
	public void revise(T object, User user) throws Exception;
	
	public void cancel(T object, User user) throws Exception;
}
