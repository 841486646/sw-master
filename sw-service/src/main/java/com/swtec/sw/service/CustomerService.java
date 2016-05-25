package com.swtec.sw.service;

import java.util.List;

import com.swtec.sw.persist.model.Customer;
import com.swtec.sw.persist.model.ext.CustomerExt;

/**
 * 
 * @author chengkang
 *
 */
public interface CustomerService extends BaseService{
	/**
	 * 查询列表
	 * @param customer
	 * @return
	 */
	List<Customer> list(CustomerExt customerExt);
	
	/**
	 * 增加
	 * @param customer
	 */
	public void insert(Customer customer);
	
	/**
	 * 更新客户资料
	 * @param customer
	 */
	public void update(Customer customer);
	
	/**
	 * 删除客户资料
	 * @param id
	 */
	public void delete(Integer id);

}
