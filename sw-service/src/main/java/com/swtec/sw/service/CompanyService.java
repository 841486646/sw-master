package com.swtec.sw.service;

import java.util.List;

import com.swtec.sw.persist.model.Company;

public interface CompanyService extends BaseService{
	/**
	 * 单位计量查询列表
	 * @param company
	 * @return
	 */
	List<Company> list(Company company);
}
