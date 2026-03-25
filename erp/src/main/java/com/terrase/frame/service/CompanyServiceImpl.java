package com.terrase.frame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrase.frame.dao.CompanyDAO;
import com.terrase.frame.dao.base.EntityDAO;
import com.terrase.frame.data.Company;
import com.terrase.frame.service.base.EntityServiceImpl;

@Service("com.terrase.frame.service.CompanyService")
public class CompanyServiceImpl extends EntityServiceImpl<Company> implements CompanyService {

	@Autowired
	private CompanyDAO classDAO;

	@Override
	public EntityDAO<Company> getClassDAO() {
		return classDAO;
	}
}
