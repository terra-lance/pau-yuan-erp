package com.terrase.frame.dao;

import org.springframework.stereotype.Repository;

import com.terrase.frame.dao.base.EntityDAOImpl;
import com.terrase.frame.data.Company;

@Repository("com.terrase.frame.dao.CompanyDAO")
public class CompanyDAOImpl extends EntityDAOImpl<Company> implements CompanyDAO {

	@Override
	public Class<Company> getClassParam() {
		return Company.class;
	}
}
