package com.terrase.frame.dao;

import org.springframework.stereotype.Repository;

import com.terrase.frame.dao.base.EntityDAOImpl;
import com.terrase.frame.data.AutoNumber;

@Repository("com.terrase.frame.dao.AutoNumberDAO")
public class AutoNumberDAOImpl extends EntityDAOImpl<AutoNumber> implements AutoNumberDAO {

	@Override
	public Class<AutoNumber> getClassParam() {
		return AutoNumber.class;
	}
}
