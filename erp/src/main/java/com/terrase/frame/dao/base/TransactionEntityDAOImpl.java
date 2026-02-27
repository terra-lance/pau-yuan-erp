package com.terrase.frame.dao.base;

import com.terrase.frame.data.base.TransactionEntity;

public abstract class TransactionEntityDAOImpl<T extends TransactionEntity> extends EntityDAOImpl<T>
		implements TransactionEntityDAO<T> {

}
