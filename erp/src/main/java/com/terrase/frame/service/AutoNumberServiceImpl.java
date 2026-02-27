package com.terrase.frame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrase.frame.dao.AutoNumberDAO;
import com.terrase.frame.dao.base.EntityDAO;
import com.terrase.frame.data.AutoNumber;
import com.terrase.frame.service.base.EntityServiceImpl;

@Service("com.terrase.frame.service.AutoNumberService")
public class AutoNumberServiceImpl extends EntityServiceImpl<AutoNumber> implements AutoNumberService {

	@Autowired
	private AutoNumberDAO classDAO;

	@Override
	public EntityDAO<AutoNumber> getClassDAO() {
		return classDAO;
	}
}
